package com.hatchlabs.kfms.services

import com.hatchlabs.kfms.controllers.AuthController
import com.hatchlabs.kfms.dtos.*
import com.hatchlabs.kfms.models.*
import com.hatchlabs.kfms.repositories.*
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.sql.Date

@Service
class KFMSService(
        private val kfmsRepo: KFMSRepository,
        private val milkRepo: MilkRepository,
        private val healthRepo: HealthRepository,
        private val breedingRepo: BreedingRepository,
        private val salesRepo: SalesRepository,
        private val expensesRepo: ExpensesRepository,
        private val appraisalRepo: AppraisalRepository,
        private val employeeRepo: EmployeeRepository,
        private val authController: AuthController,
        private val userRepo: UserRepository,
        private val uploadsRepo: UploadsRepository,

) {

    val cowTableName = "KFMS_COW_DETAILS"
    val appraisalTableName = "KFMS_APPRAISAL_DETAILS"
    val breedingTableName = "KFMS_BREEDING_DETAILS"
    val employeesTableName = "KFMS_EMPLOYEES_DETAILS"
    val expensesTableName = "KFMS_EXPENSES_DETAILS"
    val healthTableName = "KFMS_HEALTH_DETAILS"
    val milkTableName = "KFMS_MILK_PRODUCTION_DETAILS"
    val salesTableName = "KFMS_SALES_DETAILS"
    val usersTableName = "KFMS_USERS_DETAILS"

    fun getCowNameFromId(id: Long?): String?{
        return kfmsRepo.findById(id).name
    }
    fun getUserNameFromId(id: Long?): String?{
        val userEntity = userRepo.findById(id)
        return "${userEntity?.firstName} ${userEntity?.lastName}"
    }
    fun getEmployeeNameFromID(id: Long?): String?{
        val employeeEntity = employeeRepo.findById(id)
        return "${employeeEntity?.firstName} ${employeeEntity?.lastName}"
    }

    fun saveCow(cowDetails: cowDTO, docFiles: List<MultipartFile>?): KfmsResponse{

        val cowEntity = CowEntity()
        cowEntity.name = cowDetails.cowName
        cowEntity.earTag = cowDetails.earTag
        cowEntity.age = cowDetails.age
        cowEntity.color = cowDetails.color
        cowEntity.breed = cowDetails.breed
        cowEntity.dateOfBirth = cowDetails.dateOfBirth
        cowEntity.weight = cowDetails.currentWeight
        cowEntity.dateAdded = Date(System.currentTimeMillis())
        cowEntity.status = 1
        cowEntity.comments = cowDetails.comments
        cowEntity.actionBy = cowDetails.actionBy

        val savedCowEntity = kfmsRepo.save(cowEntity)

        if (docFiles != null) {
            for(docFile in docFiles){

                val uploadsEntity = KfmsUploadsEntity()
                uploadsEntity.contentType = docFile.contentType
                uploadsEntity.nameOfFile = docFile.originalFilename
//                uploadsEntity.createdBy = Id of the logged in user
                uploadsEntity.description = "Cow Upload"
                uploadsEntity.document = docFile.bytes
                uploadsEntity.tableName = cowTableName
                uploadsEntity.entryId = savedCowEntity.id
                uploadsEntity.createdOn = Date(System.currentTimeMillis())

                uploadsRepo.save(uploadsEntity)

            }
        }
        val httpStatus: Any? = HttpStatus.OK
        val responseText: String = "Success!"
        val body: Any? = "Successfully Saved the Cow!"

        return KfmsResponse(httpStatus, responseText, body )
    }

    fun getAllCows(): List<cowDTO> {
        val allCows: MutableIterable<CowEntity> = kfmsRepo.findAll()

        val cowDTOList: List<cowDTO> = allCows.map { cowEntity ->
            cowDTO().apply {
                id = cowEntity.id
                cowName = cowEntity.name
                earTag = cowEntity.earTag
                age = cowEntity.age
                color = cowEntity.color
                breed = cowEntity.breed
                dateOfBirth = cowEntity.dateOfBirth
                currentWeight = cowEntity.weight
                dateAdded = cowEntity.dateAdded
                status = cowEntity.status
                comments = cowEntity.comments
                fileUploadedId = uploadsRepo.findByEntryIdAndTableName(cowEntity.id, cowTableName).id
                actionBy = cowEntity.actionBy
//                userName = actionBy?.let { getUserNameFromId(it) }
            }
        }

        return cowDTOList
    }

    fun saveMilkProduction(milkDetails: MilkProductionDTO, docFiles: List<MultipartFile>?): KfmsResponse{

        val milkEntity = MilkProductionEntity()
        milkEntity.cowId = milkDetails.cowId
        milkEntity.productionDate = milkDetails.productionDate
        milkEntity.morningMilkKgs = milkDetails.morningMilkKgs
        milkEntity.noonMilkKgs = milkDetails.noonMilkKgs
        milkEntity.eveningMilkKgs = milkDetails.eveningMilkKgs
        milkEntity.totalMilkKgs = milkDetails.totalMilkKgs
        milkEntity.lactationWeek = milkDetails.lactationWeek
        milkEntity.fatContent = milkDetails.fatContent
        milkEntity.createdAt = Date(System.currentTimeMillis())
        milkEntity.comments = milkDetails.comments
//        milkEntity.fileUploadedId = milkDetails.fileUploadedId
//        milkEntity.actionBy = authController.getLoggedOnUser()?.id

        val savedMilkEntity = milkRepo.save(milkEntity)

        if (docFiles != null) {
            for(docFile in docFiles){

                val uploadsEntity = KfmsUploadsEntity()
                uploadsEntity.contentType = docFile.contentType
                uploadsEntity.nameOfFile = docFile.originalFilename
//                uploadsEntity.createdBy = Id of the logged in user
                uploadsEntity.description = "Milk Upload"
                uploadsEntity.document = docFile.bytes
                uploadsEntity.tableName = milkTableName
                uploadsEntity.entryId = savedMilkEntity.id
                uploadsEntity.createdOn = Date(System.currentTimeMillis())

                uploadsRepo.save(uploadsEntity)

            }
        }

        val httpStatus: Any? = HttpStatus.OK
        val responseText: String = "Success!"
        val body: Any? = "Successfully Saved the Milk Details!"

        return KfmsResponse(httpStatus, responseText, body )
    }

    fun getAllMilkProductionDetails(): List<MilkProductionDTO>{
        val allMilkDetails: MutableIterable<MilkProductionEntity> = milkRepo.findAll()

        val milkDtoList: List<MilkProductionDTO> = allMilkDetails.map {milkEntity ->
            MilkProductionDTO().apply {
                id = milkEntity.id
                cowId = milkEntity.cowId
                cowName = milkEntity.cowId?.let { getCowNameFromId(it) }
                productionDate = milkEntity.productionDate
                morningMilkKgs = milkEntity.morningMilkKgs
                noonMilkKgs = milkEntity.noonMilkKgs
                eveningMilkKgs = milkEntity.eveningMilkKgs
                totalMilkKgs = milkEntity.totalMilkKgs
                lactationWeek = milkEntity.lactationWeek
                fatContent = milkEntity.fatContent
                createdAt = milkEntity.createdAt
                updatedAt = milkEntity.updatedAt
                comments = milkEntity.comments
                fileUploadedId = uploadsRepo.findByEntryIdAndTableName(milkEntity.id, milkTableName).id
                actionBy = milkEntity.actionBy
//                userName = actionBy?.let { getUserNameFromId(it) }
            }
        }

        return milkDtoList
    }

    fun saveHealthDetails(healthDetails: HealthRecordDTO, docFiles: List<MultipartFile>?): KfmsResponse{
        val healthEntity = HealthEntity()
        healthEntity.cowID = healthDetails.cowID
        healthEntity.event = healthDetails.event
        healthEntity.diagnosis = healthDetails.diagnosis
        healthEntity.vet = healthDetails.vet
        healthEntity.recordDate = healthDetails.recordDate
        healthEntity.treatmentCost = healthDetails.treatmentCost
        healthEntity.followUp = healthDetails.followUp
        healthEntity.createdAt = Date(System.currentTimeMillis())
        healthEntity.status = 1
        healthEntity.comments = healthDetails.comments
//        healthEntity.fileUploadedId = healthDetails.fileUploadedId
//        healthEntity.actionBy = authController.getLoggedOnUser()?.id


        val savedHealthEntity = healthRepo.save(healthEntity)

        if (docFiles != null) {
            for(docFile in docFiles){
                val uploadsEntity = KfmsUploadsEntity()
                uploadsEntity.contentType = docFile.contentType
                uploadsEntity.nameOfFile = docFile.originalFilename
//                uploadsEntity.createdBy = Id of the logged in user
                uploadsEntity.description = "Health Upload"
                uploadsEntity.document = docFile.bytes
                uploadsEntity.tableName = healthTableName
                uploadsEntity.entryId = savedHealthEntity.id
                uploadsEntity.createdOn = Date(System.currentTimeMillis())

                uploadsRepo.save(uploadsEntity)

            }
        }

        return KfmsResponse(HttpStatus.OK, "Success!", "Successfully saved the health details!")
    }

    fun getAllHealthDetails(): List<HealthRecordDTO>{
        val healthDetailsEntities: MutableIterable<HealthEntity> = healthRepo.findAll()

        val healthDtoList: List<HealthRecordDTO> = healthDetailsEntities.map { healthEntity ->
            HealthRecordDTO().apply {
                id = healthEntity.id
                cowID = healthEntity.cowID
                cowName = healthEntity.cowID?.let { getCowNameFromId(it) }
                event = healthEntity.event
                diagnosis = healthEntity.diagnosis
                vet = healthEntity.vet
                recordDate = healthEntity.recordDate
                treatmentCost = healthEntity.treatmentCost
                followUp = healthEntity.followUp
                createdAt = healthEntity.createdAt
                updatedAt = healthEntity.updatedAt
                status = healthEntity.status
                comments = healthEntity.comments
                fileUploadedId = uploadsRepo.findByEntryIdAndTableName(healthEntity.id, healthTableName).id
                actionBy = healthEntity.actionBy
//                userName = actionBy?.let { getUserNameFromId(it) }
            }
        }

        return healthDtoList

    }

    fun saveBreedingDetails(breedingDetails: BreedingDTO, docFiles: List<MultipartFile>?): KfmsResponse {
        val breedingEntity = BreedingEntity()
        breedingEntity.cowId = breedingDetails.cowId
        breedingEntity.heatDate = breedingDetails.heatDate
        breedingEntity.breedingDate = breedingDetails.breedingDate
        breedingEntity.expectedDeliveryDate = breedingDetails.expectedDeliveryDate
        breedingEntity.actualDeliveryDate = breedingDetails.actualDeliveryDate
        breedingEntity.calfName = breedingDetails.calfName
        breedingEntity.calfAge = breedingDetails.calfAge
        breedingEntity.sireName = breedingDetails.sireName
        breedingEntity.calvingDifficulty = breedingDetails.calvingDifficulty
        breedingEntity.createdAt = Date(System.currentTimeMillis())
        breedingEntity.comments = breedingDetails.comments
        breedingEntity.fileUploadedId = breedingDetails.fileUploadedId
        breedingEntity.actionBy = breedingDetails.actionBy

        val savedBreedingEntity = breedingRepo.save(breedingEntity)

        if (docFiles != null) {
            for(docFile in docFiles){

                val uploadsEntity = KfmsUploadsEntity()
                uploadsEntity.contentType = docFile.contentType
                uploadsEntity.nameOfFile = docFile.originalFilename
//                uploadsEntity.createdBy = Id of the logged in user
                uploadsEntity.description = "Breeding Upload"
                uploadsEntity.document = docFile.bytes
                uploadsEntity.tableName = cowTableName
                uploadsEntity.entryId = savedBreedingEntity.id
                uploadsEntity.createdOn = Date(System.currentTimeMillis())

                uploadsRepo.save(uploadsEntity)

            }
        }

        return KfmsResponse(HttpStatus.OK, "Success!", "Successfully saved the breeding details!")
    }

    fun getAllBreedingDetails(): List<BreedingDTO> {
        val allBreedingEntities: MutableIterable<BreedingEntity> = breedingRepo.findAll()

        val breedingDtoList: List<BreedingDTO> = allBreedingEntities.map { breedingEntity ->
            BreedingDTO().apply {
                id = breedingEntity.id
                cowId = breedingEntity.cowId
                cowName = breedingEntity.cowId?.let { getCowNameFromId(it) }
                heatDate = breedingEntity.heatDate
                breedingDate = breedingEntity.breedingDate
                expectedDeliveryDate = breedingEntity.expectedDeliveryDate
                actualDeliveryDate = breedingEntity.actualDeliveryDate
                calfName = breedingEntity.calfName
                calfAge = breedingEntity.calfAge
                sireName = breedingEntity.sireName
                calvingDifficulty = breedingEntity.calvingDifficulty
                createdAt = breedingEntity.createdAt
                updatedAt = breedingEntity.updatedAt
                comments = breedingEntity.comments
                fileUploadedId = uploadsRepo.findByEntryIdAndTableName(breedingEntity.id, breedingTableName).id
                actionBy = breedingEntity.actionBy
//                userName = actionBy?.let { getUserNameFromId(it) }
            }
        }

        return breedingDtoList
    }

    fun saveSalesRecord(salesDetails: SalesRecordsDto, docFiles: List<MultipartFile>?): KfmsResponse {
        val salesEntity = SalesEntity()
        salesEntity.saleDate = salesDetails.saleDate
        salesEntity.milkSoldKgs = salesDetails.milkSoldKgs
        salesEntity.totalPrice = salesDetails.totalPrice
        salesEntity.userId = salesDetails.userId
        salesEntity.clientName = salesDetails.clientName
        salesEntity.clientPhone = salesDetails.clientPhone
        salesEntity.createdAt = Date(System.currentTimeMillis())
        salesEntity.comments = salesDetails.comments
        salesEntity.fileUploadedId = salesDetails.fileUploadedId
        salesEntity.actionBy = salesDetails.actionBy

        val savedSalesEntity = salesRepo.save(salesEntity)
        if (docFiles != null) {
            for(docFile in docFiles){

                val uploadsEntity = KfmsUploadsEntity()
                uploadsEntity.contentType = docFile.contentType
                uploadsEntity.nameOfFile = docFile.originalFilename
//                uploadsEntity.createdBy = Id of the logged in user
                uploadsEntity.description = "Sales Upload"
                uploadsEntity.document = docFile.bytes
                uploadsEntity.tableName = cowTableName
                uploadsEntity.entryId = savedSalesEntity.id
                uploadsEntity.createdOn = Date(System.currentTimeMillis())

                uploadsRepo.save(uploadsEntity)

            }
        }

        return KfmsResponse(HttpStatus.OK, "Success!", "Successfully saved the sales record!")
    }

    fun getAllSalesRecords(): List<SalesRecordsDto> {
        val allSalesEntities: MutableIterable<SalesEntity> = salesRepo.findAll()

        val salesDtoList: List<SalesRecordsDto> = allSalesEntities.map { salesEntity ->
            SalesRecordsDto().apply {
                id = salesEntity.id
                saleDate = salesEntity.saleDate
                milkSoldKgs = salesEntity.milkSoldKgs
                totalPrice = salesEntity.totalPrice
                userId = salesEntity.userId
//                userName = userId?.let { getUserNameFromId(it) }
                clientName = salesEntity.clientName
                clientPhone = salesEntity.clientPhone
                createdAt = salesEntity.createdAt
                updatedAt = salesEntity.updatedAt
                comments = salesEntity.comments
                fileUploadedId = uploadsRepo.findByEntryIdAndTableName(salesEntity.id, salesTableName).id
                actionBy = salesEntity.actionBy
            }
        }

        return salesDtoList
    }

    fun saveExpenseDetails(expenseDetails: ExpenseDto, docFiles: List<MultipartFile>?): KfmsResponse {
        val expenseEntity = ExpenseEntity()
        expenseEntity.expenseDescription = expenseDetails.expenseDescription
        expenseEntity.expenseDate = expenseDetails.expenseDate
        expenseEntity.userId = expenseDetails.userId
        expenseEntity.amount = expenseDetails.amount
        expenseEntity.purpose = expenseDetails.purpose
        expenseEntity.expenseCategory = expenseDetails.expenseCategory
        expenseEntity.createdAt = Date(System.currentTimeMillis())
        expenseEntity.comments = expenseDetails.comments
        expenseEntity.fileUploadedId = expenseDetails.fileUploadedId
        expenseEntity.actionBy = expenseDetails.actionBy

        val savedExpenseEntity = expensesRepo.save(expenseEntity)
        if (docFiles != null) {
            for(docFile in docFiles){

                val uploadsEntity = KfmsUploadsEntity()
                uploadsEntity.contentType = docFile.contentType
                uploadsEntity.nameOfFile = docFile.originalFilename
//                uploadsEntity.createdBy = Id of the logged in user
                uploadsEntity.description = "Expense Upload"
                uploadsEntity.document = docFile.bytes
                uploadsEntity.tableName = expensesTableName
                uploadsEntity.entryId = savedExpenseEntity.id
                uploadsEntity.createdOn = Date(System.currentTimeMillis())

                uploadsRepo.save(uploadsEntity)

            }
        }

        return KfmsResponse(HttpStatus.OK, "Success!", "Successfully saved the expense record!")
    }

    fun getAllExpenseDetails(): List<ExpenseDto> {
        val allExpenseEntities: MutableIterable<ExpenseEntity> = expensesRepo.findAll()

        val expenseDtoList: List<ExpenseDto> = allExpenseEntities.map { expenseEntity ->
            ExpenseDto().apply {
                id = expenseEntity.id
                expenseDescription = expenseEntity.expenseDescription
                expenseDate = expenseEntity.expenseDate
                userId = expenseEntity.userId
//                userName = userId?.let { getUserNameFromId(it) }
                amount = expenseEntity.amount
                purpose = expenseEntity.purpose
                expenseCategory = expenseEntity.expenseCategory
                createdAt = expenseEntity.createdAt
                updatedAt = expenseEntity.updatedAt
                comments = expenseEntity.comments
                fileUploadedId = uploadsRepo.findByEntryIdAndTableName(expenseEntity.id, expensesTableName).id
                actionBy = expenseEntity.actionBy
            }
        }

        return expenseDtoList
    }

    fun saveAppraisalDetails(appraisalDetails: AppraisalDto, docFiles: List<MultipartFile>?): KfmsResponse {
        val appraisalEntity = AppraisalEntity()
        appraisalEntity.employeeID = appraisalDetails.employeeID
        appraisalEntity.appraisalDate = appraisalDetails.appraisalDate
        appraisalEntity.appraisalOfficer = appraisalDetails.appraisalOfficer
        appraisalEntity.performanceRating = appraisalDetails.performanceRating
        appraisalEntity.comments = appraisalDetails.comments
        appraisalEntity.fileUploadedId = appraisalDetails.fileUploadedId
        appraisalEntity.actionBy = appraisalDetails.actionBy

        val savedAppraisalEntity = appraisalRepo.save(appraisalEntity)
        if (docFiles != null) {
            for(docFile in docFiles){

                val uploadsEntity = KfmsUploadsEntity()
                uploadsEntity.contentType = docFile.contentType
                uploadsEntity.nameOfFile = docFile.originalFilename
//                uploadsEntity.createdBy = Id of the logged in user
                uploadsEntity.description = "Appraisal Upload"
                uploadsEntity.document = docFile.bytes
                uploadsEntity.tableName = appraisalTableName
                uploadsEntity.entryId = savedAppraisalEntity.id
                uploadsEntity.createdOn = Date(System.currentTimeMillis())

                uploadsRepo.save(uploadsEntity)

            }
        }

        return KfmsResponse(HttpStatus.OK, "Success!", "Successfully saved the appraisal details!")
    }

    fun getAllAppraisalDetails(): List<AppraisalDto> {
        val allAppraisalEntities: MutableIterable<AppraisalEntity> = appraisalRepo.findAll()

        val appraisalDtoList: List<AppraisalDto> = allAppraisalEntities.map { appraisalEntity ->
            AppraisalDto().apply {
                id = appraisalEntity.id
                employeeID = appraisalEntity.employeeID
                appraisalDate = appraisalEntity.appraisalDate
                appraisalOfficer = appraisalEntity.appraisalOfficer
                performanceRating = appraisalEntity.performanceRating
                comments = appraisalEntity.comments
                fileUploadedId = uploadsRepo.findByEntryIdAndTableName(appraisalEntity.id, appraisalTableName).id
                actionBy = appraisalEntity.actionBy
//                appraisalOfficerName = appraisalEntity.appraisalOfficer?.let { getUserNameFromId(it) }
//                employeeName = appraisalEntity.employeeID?.let { getEmployeeNameFromID(it) }
            }
        }

        return appraisalDtoList
    }

    fun saveEmployeeDetails(employeeDetails: EmployeeDto, docFiles: List<MultipartFile>?): KfmsResponse {
        val employeeEntity = EmployeeEntity()
        employeeEntity.firstName = employeeDetails.firstName
        employeeEntity.lastName = employeeDetails.lastName
        employeeEntity.position = employeeDetails.position
        employeeEntity.hireDate = employeeDetails.hireDate
        employeeEntity.terminationDate = employeeDetails.terminationDate
        employeeEntity.salary = employeeDetails.salary
        employeeEntity.contactNumber = employeeDetails.contactNumber
        employeeEntity.email = employeeDetails.email
        employeeEntity.address = employeeDetails.address
        employeeEntity.comments = employeeDetails.comments
//        employeeEntity.fileUploadedId = employeeDetails.fileUploadedId
//        employeeEntity.actionBy = employeeDetails.actionBy

        val savedEmployeeEntity = employeeRepo.save(employeeEntity)
        if (docFiles != null) {
            for(docFile in docFiles){

                val uploadsEntity = KfmsUploadsEntity()
                uploadsEntity.contentType = docFile.contentType
                uploadsEntity.nameOfFile = docFile.originalFilename
//                uploadsEntity.createdBy = Id of the logged in user
                uploadsEntity.description = "Employee Upload"
                uploadsEntity.document = docFile.bytes
                uploadsEntity.tableName = employeesTableName
                uploadsEntity.entryId = savedEmployeeEntity.id
                uploadsEntity.createdOn = Date(System.currentTimeMillis())

                uploadsRepo.save(uploadsEntity)

            }
        }

        return KfmsResponse(HttpStatus.OK, "Success!", "Successfully saved the employee details!")
    }

    fun getAllEmployeeDetails(): List<EmployeeDto> {
        val allEmployeeEntities: MutableIterable<EmployeeEntity> = employeeRepo.findAll()

        val employeeDtoList: List<EmployeeDto> = allEmployeeEntities.map { employeeEntity ->
            EmployeeDto().apply {
                id = employeeEntity.id
                firstName = employeeEntity.firstName
                lastName = employeeEntity.lastName
                position = employeeEntity.position
                hireDate = employeeEntity.hireDate
                terminationDate = employeeEntity.terminationDate
                salary = employeeEntity.salary
                contactNumber = employeeEntity.contactNumber
                email = employeeEntity.email
                address = employeeEntity.address
                comments = employeeEntity.comments
                fileUploadedId = uploadsRepo.findByEntryIdAndTableName(employeeEntity.id, employeesTableName).id
                actionBy = employeeEntity.actionBy
//                userName = actionBy?.let { getUserNameFromId(it) }
            }
        }

        return employeeDtoList
    }




}