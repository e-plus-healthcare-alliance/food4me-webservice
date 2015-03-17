package eu.qualify.food4me.algorithm.SOP3

import eu.qualify.food4me.ModifiedProperty
import eu.qualify.food4me.Property
import eu.qualify.food4me.interfaces.Measurable
import eu.qualify.food4me.interfaces.StatusComputer
import eu.qualify.food4me.measurements.MeasuredNumericValue
import eu.qualify.food4me.measurements.MeasuredValue
import eu.qualify.food4me.measurements.Measurement
import eu.qualify.food4me.measurements.MeasurementStatus
import eu.qualify.food4me.measurements.Measurements
import eu.qualify.food4me.measurements.Status
import eu.qualify.food4me.reference.ReferenceValue
import grails.transaction.Transactional

@Transactional
class ComputeStatusService implements StatusComputer {

	@Override
	public MeasurementStatus computeStatus(Measurements measurements) {
		// Return if no values are given
		if( !measurements )
			return null;
	
		MeasurementStatus measurementStatus = new MeasurementStatus()
			
		// Check the status for all nutrients
		measurements.all.each { measurement ->
			Status status = getStatus( measurement, measurements )
			
			if( status != null )
				measurementStatus.addStatus( status.entity, status )
		}
			
		return measurementStatus;
	}
	
	public Status getStatus( Measurement measurement, Measurements measurements ) {
		log.info "Determine status for " + measurement
		MeasuredValue value = measurement.value
		
		def status = determineStatus( measurement.property, measurements )
		status?.value = value
		return status
	}
	
	protected Status determineStatus( Property property, Measurements measurements ) {
		log.debug "  - normal property"
		determineStatusForProperty( property, property, measurements )
	}
	
	/**
	 * Determine the status for a property including modifier
	 * @param property
	 * @param measurements
	 * @return
	 */
	protected Status determineStatus( ModifiedProperty property, Measurements measurements ) {
		log.debug "  - modified property"
		
		// A status can only be determined for properties with modifier 'from food'
		// For those properties, the same reference values apply as for the total
		// value of the (root) properties.
		if( property.modifier != ModifiedProperty.Modifier.INTAKE_DIETARY.id )
			return null
		
		determineStatusForProperty( property, property.rootProperty, measurements )
	}
	
	/**
	 * Determines the status for some referenceproperty
	 * @param valueProperty		Property to retrieve the value for
	 * @param referenceProperty	Property to determine the reference
	 * @param measurements		Set of measurements used as input
	 * @return
	 */
	protected Status determineStatusForProperty( Measurable valueProperty, Property referenceProperty, Measurements measurements ) {
		def status = new Status( entity: valueProperty )

		// First determine the conditions applicable for the given property
		// Most probably that includes the property value itself, but it could
		// be dependent on age or gender as well
		def properties = ReferenceValue.getConditionProperties( referenceProperty )
		
		// If no properties are found, no reference values are known. Returning immediately
		if( !properties ) {
			log.warn "No references apply for any value of ${referenceProperty}. Please check the database"
			status.status = Status.STATUS_UNKNOWN
			return status
		}
		
		// Create a query that includes all values and retrieve the id and status
		def hql = "SELECT reference.id, reference.status, reference.color FROM ReferenceValue as reference INNER JOIN reference.conditions as condition"
		hql += " WHERE reference.subject = :referenceProperty "
		
		// First determine the whereclause for all properties, except for the referenceproperty
		// because the value for this property could be determined by another property (e.g. a ModifiedProperty)
		def (whereClause, hqlParams) = generateWhereClause( properties - referenceProperty, measurements )
		extendWhereClauses( whereClause, hqlParams, referenceProperty, measurements.getValueFor( valueProperty ), whereClause.size() + 1 )
		
		if( whereClause ) {
			hql += " AND ( " + whereClause.join( " OR " ) + " )"
		}
			
		hql += " GROUP BY reference.id, reference.status, reference.color HAVING COUNT(*) = reference.numConditions"
		
		hqlParams[ "referenceProperty" ] = referenceProperty
		
		def statuses = ReferenceValue.executeQuery( hql, hqlParams )
		
		if( statuses.size() == 0 ) {
			log.warn "No references apply for ${referenceProperty}. Retrieval parameters are " + hqlParams
			status.status = Status.STATUS_UNKNOWN
			return status
		}
			
		if( statuses.size() > 1 ) {
			log.warn "Multiple references apply for ${referenceProperty}. Retrieval parameters are " + hqlParams
		}
		
		// Return the first status found
		status.status = statuses[0][1]
		status.color = statuses[0][2]
		return status
	}

	
	protected def generateWhereClause( List<Property> properties, Measurements measurements, int index = 0 ) {
		List<String> whereClause = []
		def whereParams = [:]
		
		properties.each { Property property ->
			MeasuredValue measuredValue = measurements.getValueFor( property )
			extendWhereClauses( whereClause, whereParams, property, measuredValue, index++ )
		}
		
		[ whereClause, whereParams ]
	}
	
	protected void extendWhereClauses( List whereClause, Map whereParams, Property property, MeasuredValue measuredValue, int index = 0 ) {
		String condition = " ( condition.subject = :property" + index + " AND "
		
		// There is a difference between text and numeric values
		if( measuredValue instanceof MeasuredNumericValue )
			condition += "( ( low IS NULL or low < :value" + index + " ) AND ( high IS NULL OR high >= :value" + index + " ) )"
		 else
			 condition += "( value IS NULL or value = :value" + index + " )"
		 
		whereClause << condition + " )"
		
		whereParams[ "property" + index ] = property
		whereParams[ "value" + index ] = measuredValue.value
	}
}
