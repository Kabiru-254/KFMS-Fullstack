package com.hatchlabs.kfms.repositories

import com.hatchlabs.kfms.models.*
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface KFMSRepository: CrudRepository<CowEntity, Long> {

    fun findById(id: Long?): CowEntity


}


@Repository
interface MilkRepository: CrudRepository<MilkProductionEntity, Long>{

}

@Repository
interface HealthRepository: CrudRepository<HealthEntity, Long>{

}

@Repository
interface BreedingRepository: CrudRepository<BreedingEntity, Long>{

}


@Repository
interface SalesRepository: CrudRepository<SalesEntity, Long>{

}

@Repository
interface ExpensesRepository: CrudRepository<ExpenseEntity, Long>{

}

@Repository
interface AppraisalRepository: CrudRepository<AppraisalEntity, Long>{

}

@Repository
interface EmployeeRepository: CrudRepository<EmployeeEntity, Long>{

    fun findById(id: Long?): EmployeeEntity?
}

@Repository
interface UploadsRepository: CrudRepository<KfmsUploadsEntity, Long>{

    fun findById(id: Long?): KfmsUploadsEntity

    fun findByEntryIdAndTableName(entryId: Long?, tableName: String?): KfmsUploadsEntity

}