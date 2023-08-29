package com.hatchlabs.kfms.repositories

import com.hatchlabs.kfms.models.CowEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface KFMSRepository: CrudRepository<CowEntity, Long> {


}