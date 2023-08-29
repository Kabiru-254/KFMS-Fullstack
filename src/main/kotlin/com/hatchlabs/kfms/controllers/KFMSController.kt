package com.hatchlabs.kfms.controllers

import com.hatchlabs.kfms.dtos.KfmsResponse
import com.hatchlabs.kfms.dtos.cowDTO
import com.hatchlabs.kfms.services.KFMSService
import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping("api/kfms/")
class KFMSController(
        private val kfmsService: KFMSService
) {

    @PostMapping("saveCow")
    @ResponseBody
    fun saveCow(@RequestBody cowDetails: cowDTO): KfmsResponse{
        return kfmsService.saveCow(cowDetails)
    }

    @GetMapping("getAllCows")
    fun getAllCows(): List<cowDTO>{
        return kfmsService.getAllCows()
    }

}