package com.hatchlabs.kfms.controllers

import com.hatchlabs.kfms.dtos.*
import com.hatchlabs.kfms.services.KFMSService
import com.hatchlabs.kfms.services.pdfService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import com.google.gson.Gson
import com.google.gson.GsonBuilder

@RestController
@RequestMapping("api/kfms/")
class KFMSController(
        private val kfmsService: KFMSService,
        private val pdfService: pdfService
) {

//    @GetMapping("anonymous/viewSicPdf")
//    @ResponseBody
//    fun viewPDF(response: HttpServletResponse) {
//        // Generate the PDF using your PDFBox service
//        val pdfByteArray = pdfService.generateSimplePDF()
//
//        // Set response headers for PDF content
//        response.contentType = "application/pdf"
//        response.setHeader("Content-Disposition", "inline; filename=sample.pdf")
//
//        // Write the PDF content to the response output stream
//        response.outputStream.write(pdfByteArray)
//    }
//
//    @GetMapping("anonymous/viewSicPdf2")
//    @ResponseBody
//    fun viewPDF2(response: HttpServletResponse) {
//        // Generate the PDF using your PDFBox service
//        val pdfByteArray = pdfService.generatePDFWithHeader()
//
//        // Set response headers for PDF content
//        response.contentType = "application/pdf"
//        response.setHeader("Content-Disposition", "inline; filename=sample.pdf")
//
//        // Write the PDF content to the response output stream
//        response.outputStream.write(pdfByteArray)
//    }
//    @PostMapping("saveCow")
//    @ResponseBody
//    fun saveCow(@RequestBody cowDetails: cowDTO): KfmsResponse{
//        return kfmsService.saveCow(cowDetails)
//    }


    @PostMapping("saveCow")
    @ResponseBody
    fun saveCow(
            @RequestParam("data") cowDetailsJson: String,
            @RequestParam("docFile") docFiles: List<MultipartFile>?
    ): KfmsResponse {
        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create()
        val cowDetails = gson.fromJson(cowDetailsJson, cowDTO::class.java)
        return kfmsService.saveCow(cowDetails, docFiles)
    }

    @GetMapping("getAllCows")
    fun getAllCows(): List<cowDTO>{
        return kfmsService.getAllCows()
    }

    @PostMapping("saveMilkDetails")
    @ResponseBody
    fun saveMilkDetails(
            @RequestParam("data") milkDetailsJson: String,
            @RequestParam("docFile") docFiles: List<MultipartFile>?
    ): KfmsResponse {
        val gson = Gson()
        val milkDetails = gson.fromJson(milkDetailsJson, MilkProductionDTO::class.java)
        return kfmsService.saveMilkProduction(milkDetails, docFiles)
    }

    @GetMapping("getAllMilkDetails")
    fun getAllMilkDetails(): List<MilkProductionDTO>{
        return kfmsService.getAllMilkProductionDetails()
    }

    @PostMapping("saveHealthDetails")
    @ResponseBody
    fun saveHealthDetails(
            @RequestParam("data") healthDetailsJson: String,
            @RequestParam("docFile") docFiles: List<MultipartFile>?
    ): KfmsResponse {
        val gson = Gson()
        val healthDetails = gson.fromJson(healthDetailsJson, HealthRecordDTO::class.java)
        return kfmsService.saveHealthDetails(healthDetails, docFiles)
    }

    @GetMapping("getAllHealthDetails")
    fun getAllHealthDetails(): List<HealthRecordDTO>{
        return kfmsService.getAllHealthDetails()
    }

    @PostMapping("saveBreedingDetails")
    @ResponseBody
    fun saveBreedingDetails(
            @RequestParam("data") breedingDetailsJson: String,
            @RequestParam("docFile") docFiles: List<MultipartFile>?
    ): KfmsResponse {
        val gson = Gson()
        val breedingDetails = gson.fromJson(breedingDetailsJson, BreedingDTO::class.java)
        return kfmsService.saveBreedingDetails(breedingDetails, docFiles)
    }

    @GetMapping("getAllBreedingDetails")
    fun getAllBreedingDetails(): List<BreedingDTO> {
        return kfmsService.getAllBreedingDetails()
    }

    @PostMapping("saveSalesRecord")
    @ResponseBody
    fun saveSalesRecord(
            @RequestParam("data") salesDetailsJson: String,
            @RequestParam("docFile") docFiles: List<MultipartFile>?
    ): KfmsResponse {
        val gson = Gson()
        val salesDetails = gson.fromJson(salesDetailsJson, SalesRecordsDto::class.java)
        return kfmsService.saveSalesRecord(salesDetails, docFiles)
    }

    @GetMapping("getAllSalesRecords")
    fun getAllSalesRecords(): List<SalesRecordsDto> {
        return kfmsService.getAllSalesRecords()
    }

    @GetMapping("getAllExpenseDetails")
    fun getAllExpenseDetails(): List<ExpenseDto> {
        return kfmsService.getAllExpenseDetails()
    }

    @PostMapping("saveExpenseDetails")
    @ResponseBody
    fun saveExpenseDetails(
            @RequestParam("data") expenseDetailsJson: String,
            @RequestParam("docFile") docFiles: List<MultipartFile>?
    ): KfmsResponse {
        val gson = Gson()
        val expenseDetails = gson.fromJson(expenseDetailsJson, ExpenseDto::class.java)
        return kfmsService.saveExpenseDetails(expenseDetails, docFiles)
    }

    @PostMapping("saveAppraisalDetails")
    @ResponseBody
    fun saveAppraisalDetails(
            @RequestParam("data") appraisalDetailsJson: String,
            @RequestParam("docFile") docFiles: List<MultipartFile>?
    ): KfmsResponse {
        val gson = Gson()
        val appraisalDetails = gson.fromJson(appraisalDetailsJson, AppraisalDto::class.java)
        return kfmsService.saveAppraisalDetails(appraisalDetails, docFiles)
    }

    @GetMapping("getAllAppraisalDetails")
    fun getAllAppraisalDetails(): List<AppraisalDto> {
        return kfmsService.getAllAppraisalDetails()
    }

    @PostMapping("saveEmployeeDetails")
    @ResponseBody
    fun saveEmployeeDetails(
            @RequestParam("data") employeeDetailsJson: String,
            @RequestParam("docFile") docFiles: List<MultipartFile>?
    ): KfmsResponse {
        val gson = Gson()
        val employeeDetails = gson.fromJson(employeeDetailsJson, EmployeeDto::class.java)
        return kfmsService.saveEmployeeDetails(employeeDetails, docFiles)
    }

    @GetMapping("getAllEmployeeDetails")
    fun getAllEmployeeDetails(): List<EmployeeDto> {
        return kfmsService.getAllEmployeeDetails()
    }

}