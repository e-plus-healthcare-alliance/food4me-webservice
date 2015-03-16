package eu.qualify.food4me.exampledata

import eu.qualify.food4me.ModifiedProperty
import eu.qualify.food4me.Property
import eu.qualify.food4me.Unit
import eu.qualify.food4me.decisiontree.Advice
import eu.qualify.food4me.decisiontree.AdviceCondition
import eu.qualify.food4me.measurements.Status
import eu.qualify.food4me.reference.ReferenceCondition
import eu.qualify.food4me.reference.ReferenceValue

class ExampleData {

	static def initializeGenericData() {
		// Initialize units
		def years = Unit.findByCode( "yr" ) ?: new Unit( name: "years", externalId: "258707000", code: "yr" )
			years.save(failOnError: true)
		def gPerKgBodyWeight = Unit.findByCode( "g/kg bw" ) ?: new Unit( name: "gram per kilogram body weight", externalId: "228919004", code: "g/kg bw" )
			gPerKgBodyWeight.save(failOnError: true)
		def grams = Unit.findByCode( "g" ) ?: new Unit( name: "gram", externalId: "258682000", code: "g" )
			grams.save(failOnError: true)
		def micrograms = Unit.findByCode( "ug" ) ?: new Unit( name: "microgram", externalId: "258685003", code: "ug" )
			micrograms.save(failOnError: true)
		def mmolPerL= Unit.findByCode( "mmol/L" ) ?: new Unit( name: "millimole/liter", externalId: "258813002", code: "mmol/L" )
			mmolPerL.save(failOnError: true)
		def percentageEnergyIntake = Unit.findByCode( "% energy intake" ) ?: new Unit( name: "% of total energy intake", externalId: "288493004", code: "% energy intake" )
			percentageEnergyIntake.save(failOnError: true)
		def percentage = Unit.findByCode( "%" ) ?: new Unit( name: "percent", externalId: "415067009", code: "%" )
			percentage.save(failOnError: true)
		def kgPerm2 = Unit.findByCode( "kg/m2" ) ?: new Unit( name: "kilogram per square meter", externalId: "258896009", code: "kg/m2" )
			kgPerm2.save(failOnError: true)
		def cm = Unit.findByCode( "cm" ) ?: new Unit( name: "centimeter", externalId: "258672001", code: "cm" )
			cm.save(failOnError: true)

		// Initialize properties needed
		def age = Property.findByEntity( "Age" ) ?: new Property(propertyGroup: Property.PROPERTY_GROUP_GENERIC, entity: "Age", externalId: "397669002", unit: years)
			age.save(failOnError: true)
		def gender = Property.findByEntity( "Gender" ) ?: new Property(propertyGroup: Property.PROPERTY_GROUP_GENERIC, entity: "Gender", externalId: "263495000" )
			gender.save(failOnError: true)
		def protein = Property.findByEntity( "Protein" ) ?: new Property(propertyGroup: Property.PROPERTY_GROUP_NUTRIENT, entity: "Protein", externalId: "88878007", unit: gPerKgBodyWeight)
			protein.save(failOnError: true)
		def carbohydrate = Property.findByEntity( "Carbohydrate" ) ?: new Property(propertyGroup: Property.PROPERTY_GROUP_NUTRIENT, entity: "Carbohydrate", externalId: "2331003", unit: percentageEnergyIntake)
			carbohydrate.save(failOnError: true)
		def fibre = Property.findByEntity( "Fibre" ) ?: new Property(propertyGroup: Property.PROPERTY_GROUP_NUTRIENT, entity: "Fibre", externalId: "400293002", unit: grams)
			fibre.save(failOnError: true)
		def folate = Property.findByEntity( "Folate" ) ?: new Property(propertyGroup: Property.PROPERTY_GROUP_NUTRIENT, entity: "Folate", externalId: "286594003", unit: micrograms)
			folate.save(failOnError: true)

		def cholesterol = Property.findByEntity( "Cholesterol" ) ?: new Property(propertyGroup: Property.PROPERTY_GROUP_BIOMARKER, entity: "Cholesterol", externalId: "84698008", unit: mmolPerL)
			cholesterol.save(failOnError: true)
		def glucose = Property.findByEntity( "Glucose" ) ?: new Property(propertyGroup: Property.PROPERTY_GROUP_BIOMARKER, entity: "Glucose", externalId: "67079006", unit: mmolPerL)
			glucose.save(failOnError: true)
		def vitaminA = Property.findByEntity( "Vitamin A" ) ?: new Property(propertyGroup: Property.PROPERTY_GROUP_NUTRIENT, entity: "Vitamin A", externalId: "82622003", unit: micrograms)
			vitaminA.save(failOnError: true)
			
		def geneFADS1 = Property.findByEntity("FADS1") ?: new Property(propertyGroup: Property.PROPERTY_GROUP_SNP, entity: "FADS1", externalId: "rs174546")
			geneFADS1.save(failOnError: true)
		def geneFTO = Property.findByEntity("FTO") ?: new Property(propertyGroup: Property.PROPERTY_GROUP_SNP, entity: "FTO", externalId: "rs9939609")
			geneFTO.save(failOnError: true)

		def omega3Intake = Property.findByEntityAndPropertyGroup("Omega-3", Property.PROPERTY_GROUP_NUTRIENT) ?: new Property(propertyGroup: Property.PROPERTY_GROUP_NUTRIENT, entity: "Omega-3", externalId: "226332006", unit: percentageEnergyIntake)
			omega3Intake.save(failOnError: true)
		def omega3Biomarker = Property.findByEntityAndPropertyGroup("Omega-3", Property.PROPERTY_GROUP_BIOMARKER) ?: new Property(propertyGroup: Property.PROPERTY_GROUP_BIOMARKER, entity: "Omega-3", externalId: "226365003", unit: percentage)
			omega3Biomarker.save(failOnError: true)
			
		def bmi = Property.findByEntity( "Body Mass Index" ) ?: new Property(propertyGroup: Property.PROPERTY_GROUP_PHYSICAL, entity: "Body Mass Index", externalId: "60621009", unit: kgPerm2)
			bmi.save(failOnError: true)
		def waistCircumference = Property.findByEntity( "Waist circumference" ) ?: new Property(propertyGroup: Property.PROPERTY_GROUP_PHYSICAL, entity: "Waist circumference", externalId: "276361009", unit: cm)
			waistCircumference.save(failOnError: true)
		def physicalActivity = Property.findByEntity( "Physical activity" ) ?: new Property(propertyGroup: Property.PROPERTY_GROUP_PHYSICAL, entity: "Physical activity", externalId: "68130003")
			physicalActivity.save(failOnError: true)
	}
	
	static def initializeReferences() {
		// Initialize properties needed
		def protein = Property.findByEntity( "Protein" )
		def carbohydrate = Property.findByEntity( "Carbohydrate" )
		def fibre = Property.findByEntity( "Fibre" )
		def folate = Property.findByEntity( "Folate" )
		def cholesterol = Property.findByEntity( "Cholesterol" )
		def vitaminA = Property.findByEntity( "Vitamin A" )

		def omega3Intake = Property.findByEntityAndPropertyGroup("Omega-3", Property.PROPERTY_GROUP_NUTRIENT)
		def omega3Biomarker = Property.findByEntityAndPropertyGroup("Omega-3", Property.PROPERTY_GROUP_BIOMARKER)

		def age = Property.findByEntity( "Age" )
		def gender = Property.findByEntity( "Gender" )

		def geneFADS1 = Property.findByEntity("FADS1")
						
		// Initialize a few simple references
		if( ReferenceValue.count == 0 ) {
			def proteinVeryLow = new ReferenceValue(subject: protein, status: Status.STATUS_VERY_LOW, color: Status.Color.RED )
				proteinVeryLow.addToConditions( new ReferenceCondition( subject: protein, high: 0.52 ) )
				proteinVeryLow.save(failOnError: true)
				
			def proteinLow = new ReferenceValue(subject: protein, status: Status.STATUS_LOW, color: Status.Color.AMBER )
				proteinLow.addToConditions( new ReferenceCondition( subject: protein, low: 0.52, high: 0.66 ) )
				proteinLow.save(failOnError: true)
	
			def proteinOK = new ReferenceValue(subject: protein, status: Status.STATUS_OK, color: Status.Color.GREEN )
				proteinOK.addToConditions( new ReferenceCondition( subject: protein, low: 0.66, high: 2.4 ) )
				proteinOK.save(failOnError: true)
	
			def proteinHigh = new ReferenceValue(subject: protein, status: Status.STATUS_HIGH, color: Status.Color.RED )
				proteinHigh.addToConditions( new ReferenceCondition( subject: protein, low: 2.4 ) )
				proteinHigh.save(failOnError: true)
				
			def carbohydrateVeryLow = new ReferenceValue(subject: carbohydrate, status: Status.STATUS_VERY_LOW, color: Status.Color.RED )
				carbohydrateVeryLow.addToConditions( new ReferenceCondition( subject: carbohydrate, high: 40 ) )
				carbohydrateVeryLow.save(failOnError: true)
				
			def carbohydrateLow = new ReferenceValue(subject: carbohydrate, status: Status.STATUS_LOW, color: Status.Color.AMBER )
				carbohydrateLow.addToConditions( new ReferenceCondition( subject: carbohydrate, low: 40, high: 45 ) )
				carbohydrateLow.save(failOnError: true)
	
			def carbohydrateOK = new ReferenceValue(subject: carbohydrate, status: Status.STATUS_OK, color: Status.Color.GREEN )
				carbohydrateOK.addToConditions( new ReferenceCondition( subject: carbohydrate, low: 45, high: 65 ) )
				carbohydrateOK.save(failOnError: true)
	
			def carbohydrateHigh = new ReferenceValue(subject: carbohydrate, status: Status.STATUS_HIGH, color: Status.Color.AMBER )
				carbohydrateHigh.addToConditions( new ReferenceCondition( subject: carbohydrate, low: 65, high: 70 ) )
				carbohydrateHigh.save(failOnError: true)
				
			def carbohydrateVeryHigh = new ReferenceValue(subject: carbohydrate, status: Status.STATUS_VERY_HIGH, color: Status.Color.RED )
				carbohydrateVeryHigh.addToConditions( new ReferenceCondition( subject: carbohydrate, low: 70 ) )
				carbohydrateVeryHigh.save(failOnError: true)
	
			def fibreVeryLow1 = new ReferenceValue(subject: fibre, status: Status.STATUS_VERY_LOW, color: Status.Color.RED )
				fibreVeryLow1.addToConditions( new ReferenceCondition( subject: fibre, high: 28 ) )
				fibreVeryLow1.addToConditions( new ReferenceCondition( subject: gender, value: "male") )
				fibreVeryLow1.addToConditions( new ReferenceCondition( subject: age, low: 17, high: 50 ) )
				fibreVeryLow1.save(failOnError: true)
				
			def fibreLow1 = new ReferenceValue(subject: fibre, status: Status.STATUS_LOW, color: Status.Color.AMBER )
				fibreLow1.addToConditions( new ReferenceCondition( subject: fibre, low: 28, high: 38 ) )
				fibreLow1.addToConditions( new ReferenceCondition( subject: gender, value: "male") )
				fibreLow1.addToConditions( new ReferenceCondition( subject: age, low: 17, high: 50 ) )
				fibreLow1.save(failOnError: true)
	
			def fibreOK1 = new ReferenceValue(subject: fibre, status: Status.STATUS_OK, color: Status.Color.GREEN )
				fibreOK1.addToConditions( new ReferenceCondition( subject: fibre, low: 38 ) )
				fibreOK1.addToConditions( new ReferenceCondition( subject: gender, value: "male") )
				fibreOK1.addToConditions( new ReferenceCondition( subject: age, low: 17, high: 50 ) )
				fibreOK1.save(failOnError: true)
	
			def fibreVeryLow2 = new ReferenceValue(subject: fibre, status: Status.STATUS_VERY_LOW, color: Status.Color.RED )
				fibreVeryLow2.addToConditions( new ReferenceCondition( subject: fibre, high: 20 ) )
				fibreVeryLow2.addToConditions( new ReferenceCondition( subject: gender, value: "male") )
				fibreVeryLow2.addToConditions( new ReferenceCondition( subject: age, low: 50 ) )
				fibreVeryLow2.save(failOnError: true)
				
			def fibreLow2 = new ReferenceValue(subject: fibre, status: Status.STATUS_LOW, color: Status.Color.AMBER )
				fibreLow2.addToConditions( new ReferenceCondition( subject: fibre, low: 20, high: 30 ) )
				fibreLow2.addToConditions( new ReferenceCondition( subject: gender, value: "male") )
				fibreLow2.addToConditions( new ReferenceCondition( subject: age, low: 50 ) )
				fibreLow2.save(failOnError: true)
	
			def fibreOK2 = new ReferenceValue(subject: fibre, status: Status.STATUS_OK, color: Status.Color.GREEN )
				fibreOK2.addToConditions( new ReferenceCondition( subject: fibre, low: 30 ) )
				fibreOK2.addToConditions( new ReferenceCondition( subject: gender, value: "male") )
				fibreOK2.addToConditions( new ReferenceCondition( subject: age, low: 50 ) )
				fibreOK2.save(failOnError: true)
	
			def fibreVeryLow3 = new ReferenceValue(subject: fibre, status: Status.STATUS_VERY_LOW, color: Status.Color.RED )
				fibreVeryLow3.addToConditions( new ReferenceCondition( subject: fibre, high: 15 ) )
				fibreVeryLow3.addToConditions( new ReferenceCondition( subject: gender, value: "female") )
				fibreVeryLow3.addToConditions( new ReferenceCondition( subject: age, low: 17, high: 50 ) )
				fibreVeryLow3.save(failOnError: true)
				
			def fibreLow3 = new ReferenceValue(subject: fibre, status: Status.STATUS_LOW, color: Status.Color.AMBER )
				fibreLow3.addToConditions( new ReferenceCondition( subject: fibre, low: 15, high: 25 ) )
				fibreLow3.addToConditions( new ReferenceCondition( subject: gender, value: "female") )
				fibreLow3.addToConditions( new ReferenceCondition( subject: age, low: 17, high: 50 ) )
				fibreLow3.save(failOnError: true)
	
			def fibreOK3 = new ReferenceValue(subject: fibre, status: Status.STATUS_OK, color: Status.Color.GREEN )
				fibreOK3.addToConditions( new ReferenceCondition( subject: fibre, low: 25 ) )
				fibreOK3.addToConditions( new ReferenceCondition( subject: gender, value: "female") )
				fibreOK3.addToConditions( new ReferenceCondition( subject: age, low: 17, high: 50 ) )
				fibreOK3.save(failOnError: true)
	
			def fibreVeryLow4 = new ReferenceValue(subject: fibre, status: Status.STATUS_VERY_LOW, color: Status.Color.RED )
				fibreVeryLow4.addToConditions( new ReferenceCondition( subject: fibre, high: 14 ) )
				fibreVeryLow4.addToConditions( new ReferenceCondition( subject: gender, value: "female") )
				fibreVeryLow4.addToConditions( new ReferenceCondition( subject: age, low: 50 ) )
				fibreVeryLow4.save(failOnError: true)
				
			def fibreLow4 = new ReferenceValue(subject: fibre, status: Status.STATUS_LOW, color: Status.Color.AMBER )
				fibreLow4.addToConditions( new ReferenceCondition( subject: fibre, low: 14, high: 21 ) )
				fibreLow4.addToConditions( new ReferenceCondition( subject: gender, value: "female") )
				fibreLow4.addToConditions( new ReferenceCondition( subject: age, low: 50 ) )
				fibreLow4.save(failOnError: true)
	
			def fibreOK4 = new ReferenceValue(subject: fibre, status: Status.STATUS_OK, color: Status.Color.GREEN )
				fibreOK4.addToConditions( new ReferenceCondition( subject: fibre, low: 21 ) )
				fibreOK4.addToConditions( new ReferenceCondition( subject: gender, value: "female") )
				fibreOK4.addToConditions( new ReferenceCondition( subject: age, low: 50 ) )
				fibreOK4.save(failOnError: true)
				
			def cholesterolOK = new ReferenceValue(subject: cholesterol, status: Status.STATUS_OK, color: Status.Color.GREEN )
				cholesterolOK.addToConditions( new ReferenceCondition( subject: cholesterol, high: 5 ) )
				cholesterolOK.save(failOnError: true)

			def cholesterolHigh = new ReferenceValue(subject: cholesterol, status: Status.STATUS_HIGH, color: Status.Color.AMBER )
				cholesterolHigh.addToConditions( new ReferenceCondition( subject: cholesterol, low: 5, high: 8 ) )
				cholesterolHigh.save(failOnError: true)

			def cholesterolVeryHigh = new ReferenceValue(subject: cholesterol, status: Status.STATUS_VERY_HIGH, color: Status.Color.RED )
				cholesterolVeryHigh.addToConditions( new ReferenceCondition( subject: cholesterol, low: 8 ) )
				cholesterolVeryHigh.save(failOnError: true)

			def folateOK = new ReferenceValue(subject: folate, status: Status.STATUS_OK, color: Status.Color.GREEN )
				folateOK.addToConditions( new ReferenceCondition( subject: folate, low: 320, high: 1000 ) )
				folateOK.save(failOnError: true)

			def folateLow = new ReferenceValue(subject: folate, status: Status.STATUS_OK, color: Status.Color.AMBER )
				folateLow.addToConditions( new ReferenceCondition( subject: folate, low: 240, high: 320 ) )
				folateLow.save(failOnError: true)
				
			def folateVeryLow = new ReferenceValue(subject: folate, status: Status.STATUS_OK, color: Status.Color.RED )
				folateVeryLow.addToConditions( new ReferenceCondition( subject: folate, high: 240 ) )
				folateVeryLow.save(failOnError: true)
				
			def folateHigh = new ReferenceValue(subject: folate, status: Status.STATUS_HIGH, color: Status.Color.RED )
				folateHigh.addToConditions( new ReferenceCondition( subject: folate, low: 1000 ) )
				folateHigh.save(failOnError: true)

			def vitaminAVeryLow1 = new ReferenceValue(subject: vitaminA, status: Status.STATUS_VERY_LOW, color: Status.Color.RED )
				vitaminAVeryLow1.addToConditions( new ReferenceCondition( subject: vitaminA, high: 350 ) )
				vitaminAVeryLow1.addToConditions( new ReferenceCondition( subject: gender, value: "male" ) )
				vitaminAVeryLow1.save(failOnError: true)

			def vitaminALow1 = new ReferenceValue(subject: vitaminA, status: Status.STATUS_LOW, color: Status.Color.AMBER )
				vitaminALow1.addToConditions( new ReferenceCondition( subject: vitaminA, low: 350, high: 625 ) )
				vitaminALow1.addToConditions( new ReferenceCondition( subject: gender, value: "male" ) )
				vitaminALow1.save(failOnError: true)
				
			def vitaminAOK1 = new ReferenceValue(subject: vitaminA, status: Status.STATUS_OK, color: Status.Color.GREEN )
				vitaminAOK1.addToConditions( new ReferenceCondition( subject: vitaminA, low: 625, high: 3000 ) )
				vitaminAOK1.addToConditions( new ReferenceCondition( subject: gender, value: "male" ) )
				vitaminAOK1.save(failOnError: true)
				
			def vitaminAHigh1 = new ReferenceValue(subject: vitaminA, status: Status.STATUS_HIGH, color: Status.Color.RED )
				vitaminAHigh1.addToConditions( new ReferenceCondition( subject: vitaminA, low: 3000 ) )
				vitaminAHigh1.addToConditions( new ReferenceCondition( subject: gender, value: "male" ) )
				vitaminAHigh1.save(failOnError: true)
				
			def vitaminAVeryLow2 = new ReferenceValue(subject: vitaminA, status: Status.STATUS_VERY_LOW, color: Status.Color.RED )
				vitaminAVeryLow2.addToConditions( new ReferenceCondition( subject: vitaminA, high: 300 ) )
				vitaminAVeryLow2.addToConditions( new ReferenceCondition( subject: gender, value: "female" ) )
				vitaminAVeryLow2.save(failOnError: true)

			def vitaminALow2 = new ReferenceValue(subject: vitaminA, status: Status.STATUS_LOW, color: Status.Color.AMBER )
				vitaminALow2.addToConditions( new ReferenceCondition( subject: vitaminA, low: 300, high: 500 ) )
				vitaminALow2.addToConditions( new ReferenceCondition( subject: gender, value: "female" ) )
				vitaminALow2.save(failOnError: true)
				
			def vitaminAOK2 = new ReferenceValue(subject: vitaminA, status: Status.STATUS_OK, color: Status.Color.GREEN )
				vitaminAOK2.addToConditions( new ReferenceCondition( subject: vitaminA, low: 500, high: 3000 ) )
				vitaminAOK2.addToConditions( new ReferenceCondition( subject: gender, value: "female" ) )
				vitaminAOK2.save(failOnError: true)
				
			def vitaminAHigh2 = new ReferenceValue(subject: vitaminA, status: Status.STATUS_HIGH, color: Status.Color.RED )
				vitaminAHigh2.addToConditions( new ReferenceCondition( subject: vitaminA, low: 3000 ) )
				vitaminAHigh2.addToConditions( new ReferenceCondition( subject: gender, value: "female" ) )
				vitaminAHigh2.save(failOnError: true)
				
			def geneFADS1Risk = new ReferenceValue(subject: geneFADS1, status: Status.STATUS_RISK, color: Status.Color.RED )
				geneFADS1Risk.addToConditions( new ReferenceCondition( subject: geneFADS1, value: "CC" ) )
				geneFADS1Risk.save(failOnError: true)
				
			def geneFADS1NonRisk1 = new ReferenceValue(subject: geneFADS1, status: Status.STATUS_NON_RISK, color: Status.Color.GREEN )
				geneFADS1NonRisk1.addToConditions( new ReferenceCondition( subject: geneFADS1, value: "TT" ) )
				geneFADS1NonRisk1.save(failOnError: true)
				
			def geneFADS1NonRisk2 = new ReferenceValue(subject: geneFADS1, status: Status.STATUS_NON_RISK, color: Status.Color.GREEN )
				geneFADS1NonRisk2.addToConditions( new ReferenceCondition( subject: geneFADS1, value: "TC" ) )
				geneFADS1NonRisk2.save(failOnError: true)

			// Omega3 intake
			def omega3IntakeOK = new ReferenceValue(subject: omega3Intake, status: Status.STATUS_OK, color: Status.Color.GREEN )
				omega3IntakeOK.addToConditions( new ReferenceCondition( subject: omega3Intake, low: 0.6 ) )
				omega3IntakeOK.save(failOnError: true)

			def omega3IntakeLow = new ReferenceValue(subject: omega3Intake, status: Status.STATUS_LOW, color: Status.Color.AMBER )
				omega3IntakeLow.addToConditions( new ReferenceCondition( subject: omega3Intake, low: 0.2, high: 0.6 ) )
				omega3IntakeLow.save(failOnError: true)

			def omega3IntakeVeryLow = new ReferenceValue(subject: omega3Intake, status: Status.STATUS_VERY_LOW, color: Status.Color.RED )
				omega3IntakeVeryLow.addToConditions( new ReferenceCondition( subject: omega3Intake, high: 0.2 ) )
				omega3IntakeVeryLow.save(failOnError: true)

			// Omega3 biomarker
			def omega3BiomarkerOK = new ReferenceValue(subject: omega3Biomarker, status: Status.STATUS_OK, color: Status.Color.GREEN )
				omega3BiomarkerOK.addToConditions( new ReferenceCondition( subject: omega3Biomarker, low: 8 ) )
				omega3BiomarkerOK.save(failOnError: true)

			def omega3BiomarkerIntermediate = new ReferenceValue(subject: omega3Intake, status: "Intermediate", color: Status.Color.AMBER )
				omega3BiomarkerIntermediate.addToConditions( new ReferenceCondition( subject: omega3Biomarker, low: 4, high: 8 ) )
				omega3BiomarkerIntermediate.save(failOnError: true)

			def omega3BiomarkerLow = new ReferenceValue(subject: omega3Intake, status: Status.STATUS_LOW, color: Status.Color.RED )
				omega3BiomarkerLow.addToConditions( new ReferenceCondition( subject: omega3Biomarker, high: 4 ) )
				omega3BiomarkerLow.save(failOnError: true)
		}
	}
	
	static def initializeAdvices() {
		initializeBMIAdviceL3_1()
		initializeOmega3AdviceL3_4()
	}
	
	static def initializeBMIAdviceL3_1() {
		def geneFTO = Property.findByEntity("FTO")
		def cholesterol = Property.findByEntity( "Cholesterol" )
		def glucose = Property.findByEntity( "Glucose" )
		def bmi = Property.findByEntity( "Body Mass Index" )
		def waistCircumference = Property.findByEntity( "Waist circumference" )
		def physicalActivity = Property.findByEntity( "Physical activity" )

		if( Advice.countBySubject( bmi ) == 0 ) {
			def advicesOnBMI = []
			
			def index = 1
			[ Status.STATUS_RISK, Status.STATUS_NON_RISK ].each { ftoStatus ->
				[ Status.STATUS_LOW, Status.STATUS_OK, Status.STATUS_HIGH ].each { bmiStatus ->
					[ Status.STATUS_OK, Status.STATUS_HIGH ].each { waistCircumferenceStatus ->
						[ "Sedentary", "Lightly active", "Active" ].each { physicalActivityValue ->
							[ Status.STATUS_LOW, Status.STATUS_OK, Status.STATUS_HIGH ].each { glucoseStatus ->
								[ Status.STATUS_LOW, Status.STATUS_OK, Status.STATUS_HIGH ].each { cholesterolStatus ->
									advicesOnBMI << new Advice(subject: bmi, code: sprintf( "L3.1.%03d", index ), text: sprintf( "Advice with code L3.1.%03d", index ) )
										.addToConditions( new AdviceCondition( subject: geneFTO, status: ftoStatus ) )
										.addToConditions( new AdviceCondition( subject: bmi, status: bmiStatus ) )
										.addToConditions( new AdviceCondition( subject: waistCircumference, status: waistCircumferenceStatus ) )
										.addToConditions( new AdviceCondition( subject: physicalActivity, value: physicalActivityValue ) )
										.addToConditions( new AdviceCondition( subject: glucose, status: glucoseStatus ) )
										.addToConditions( new AdviceCondition( subject: cholesterol, status: cholesterolStatus ) )

									index++
								}
							}
						}
					}
				}
			}
				
			// Save all advices on BMI
			advicesOnBMI.each {
				it.save(failOnError: true)
			}
		}
	}
	
	static def initializeOmega3AdviceL3_4() {
		def geneFADS1 = Property.findByEntity("FADS1")
		def omega3Intake = Property.findByEntityAndPropertyGroup("Omega-3", Property.PROPERTY_GROUP_NUTRIENT)
		def omega3Biomarker = Property.findByEntityAndPropertyGroup("Omega-3", Property.PROPERTY_GROUP_BIOMARKER)

		if( Advice.countBySubject( omega3Intake ) == 0 ) {
			def advicesOnOmega3Intake = []
			
			def index = 1
			[ Status.STATUS_RISK, Status.STATUS_NON_RISK ].each { fads1Status ->
				[ Status.STATUS_LOW, "Intermediate", Status.STATUS_OK ].each { omega3BiomarkerStatus ->
					[ Status.STATUS_LOW, Status.STATUS_OK ].each { omega3IntakeTotalStatus ->
						[ Status.STATUS_LOW, Status.STATUS_OK ].each { omega3IntakeDietaryStatus ->
							advicesOnOmega3Intake << new Advice(subject: omega3Intake, code: sprintf( "L3.4.%03d", index ), text: sprintf( "Advice with code L3.4.%03d", index ) )
								.addToConditions( new AdviceCondition( subject: geneFADS1, status: fads1Status ) )
								.addToConditions( new AdviceCondition( subject: omega3Biomarker, status: omega3BiomarkerStatus ) )
								.addToConditions( new AdviceCondition( subject: omega3Intake, status: omega3IntakeTotalStatus ) )
								.addToConditions( new AdviceCondition( subject: omega3Intake, modifier: ModifiedProperty.Modifier.INTAKE_DIETARY, status: omega3IntakeDietaryStatus ) )
								
							index++
						}
					}
				}
			}
				
			// Save all advices
			advicesOnOmega3Intake.each {
				it.save(failOnError: true)
			}
		}
	}

	
}