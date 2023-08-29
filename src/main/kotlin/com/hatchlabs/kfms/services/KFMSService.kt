package com.hatchlabs.kfms.services

import com.hatchlabs.kfms.controllers.AuthController
import com.hatchlabs.kfms.dtos.KfmsResponse
import com.hatchlabs.kfms.dtos.cowDTO
import com.hatchlabs.kfms.models.CowEntity
import com.hatchlabs.kfms.repositories.KFMSRepository
import org.springframework.stereotype.Service
import java.sql.Date

@Service
class KFMSService(
        private val kfmsRepo: KFMSRepository,
        private val authController: AuthController,
) {

    fun saveCow(cowDetails: cowDTO): KfmsResponse{

        val cowEntity = CowEntity()
        cowEntity.name = cowDetails.name
        cowEntity.earTag = cowDetails.ear_tag
        cowEntity.age = cowDetails.age
        cowEntity.color = cowDetails.color
        cowEntity.breed = cowDetails.breed
        cowEntity.dateOfBirth = cowDetails.date_of_birth
        cowEntity.weight = cowDetails.weight
        cowEntity.dateAdded = Date(System.currentTimeMillis())
        cowEntity.status = cowDetails.status
        cowEntity.comments = cowDetails.comments
//        cowEntity.file_uploaded_id = cowDetails.file_uploaded_id
        cowEntity.actionBy = cowDetails.action_by

        kfmsRepo.save(cowEntity)


        var httpStatus: Any? = 'K'
        var responseText: String = "Success!"
        var body: Any? = "Successfully Saved the Cow!"

        return KfmsResponse(httpStatus, responseText, body )
    }

    fun getAllCows(): List<cowDTO> {
        val allCows: MutableIterable<CowEntity> = kfmsRepo.findAll()

        val cowDTOList: List<cowDTO> = allCows.map { cowEntity ->
            cowDTO().apply {
                id = cowEntity.id
                name = cowEntity.name
                ear_tag = cowEntity.earTag
                age = cowEntity.age
                color = cowEntity.color
                breed = cowEntity.breed
                date_of_birth = cowEntity.dateOfBirth
                weight = cowEntity.weight
                date_added = cowEntity.dateAdded
                status = cowEntity.status
                comments = cowEntity.comments
                file_uploaded_id = cowEntity.file_uploaded_id
                action_by = cowEntity.actionBy
            }
        }

        return cowDTOList
    }


}