package com.hatchlabs.kfms.services

import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.common.PDRectangle
import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.apache.pdfbox.pdmodel.font.Standard14Fonts
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject
import java.io.ByteArrayOutputStream
import java.io.IOException

data class InvoiceItem(
        val standardNumber: String,
        val standardName: String,
        val unitPrice: Double,
        val quantity: Int,
        val origin: String,
        val discount: Double,
        val amount: Double
)

@Configuration
@Service
class pdfService {

    @Throws(IOException::class)
    fun generateSimplePDF(): ByteArray {
        // Create a new PDF document
        val document = PDDocument()

        try {
            // Create a page and add it to the document
            val page = PDPage(PDRectangle.A4)
            document.addPage(page)

            // Create a content stream for adding text
            val contentStream = PDPageContentStream(document, page)

            // Set font and font size
//            contentStream.setFont(PDType1Font.HELVETICA, 12f)

            contentStream.setFont(PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 12F)
            // Add some text to the page
            contentStream.beginText()
            contentStream.newLineAtOffset(50f, 700f)
            contentStream.showText("Hello, PDFBox in Kotlin Waaah!")
            contentStream.endText()

            // Close the content stream
            contentStream.close()

            // Create a byte array output stream to store the PDF content
            val byteArrayOutputStream = ByteArrayOutputStream()

            // Save the PDF to the byte array output stream
            document.save(byteArrayOutputStream)

            // Close the document
            document.close()

            // Return the generated PDF as a byte array
            return byteArrayOutputStream.toByteArray()
        } catch (e: IOException) {
            // Handle exceptions or log errors here
            throw e
        }
    }

    @Throws(IOException::class)
    fun generatePDFWithHeader(): ByteArray {
        // Create a new PDF document
        val document = PDDocument()

        try {
            // Create a page and add it to the document
            val page = PDPage(PDRectangle.A4)
            document.addPage(page)

            // Create a content stream for adding text and images
            val contentStream = PDPageContentStream(document, page)

            contentStream.setStrokingColor(0.4f, 0.6f, 0.6f) // Border color
            contentStream.setLineWidth(5f) // Border width

            val pageWidth = PDRectangle.A4.width
            val pageHeight = PDRectangle.A4.height
            contentStream.addRect(0f, 0f, pageWidth, pageHeight) // Create a rectangle that covers the entire page
            contentStream.stroke()

            // Set font for company details using the enum approach
            contentStream.setFont(PDType1Font(Standard14Fonts.FontName.TIMES_BOLD), 10f)

            // Logo: Replace "logoImagePath" with the actual path to your company's logo image
            // val logoImagePath = "/path/to/your/logo.png"
            // val logoImage = PDImageXObject.createFromFile(logoImagePath, document)
            // contentStream.drawImage(logoImage, 50f, 750f, 100f, 50f) // Adjust coordinates and dimensions

            // Company Details: Define each line of the company details
            val companyDetails = listOf(
                    "KEBS HEAD OFFICE ADDRESS",
                    "POPO ROAD OFF MOMBASA ROAD",
                    "P.O.BOX 00200 54974",
                    "NAIROBI, KENYA",
                    "TEL (+254 02)6948000/605490",
                    "FAX (+254 02)604031/609660",
                    "Phone: +254 0123456789",
                    "Email: info@kebs.org"
            )

            // Set initial position for company details
            var yPos = 750f

            // Write each line of company details with reduced padding
            for (line in companyDetails) {
                contentStream.beginText()
                contentStream.newLineAtOffset(200f, yPos) // Adjust the horizontal offset as needed
                contentStream.showText(line)
                contentStream.endText()
                yPos -= 14f // Adjust the vertical spacing as needed
            }

            // ProForma Invoice: Set font, font size, and position
            contentStream.setFont(PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 14f)
            val textWidth = PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
                    .getStringWidth("ProForma Invoice") * 14f / 1000f
            contentStream.beginText()
            contentStream.newLineAtOffset(550f - textWidth, 730f) // Adjust the position to the right
            contentStream.showText("ProForma Invoice")
            contentStream.endText()

            // Add E-Citizen Ref Number
            contentStream.setFont(PDType1Font(Standard14Fonts.FontName.HELVETICA), 12f)
            val citizenTextWidth = PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
                    .getStringWidth("E-Citizen Ref Number") * 14f / 1000f
            contentStream.beginText()
            contentStream.newLineAtOffset(550f - citizenTextWidth, 680f) // Adjust the position
            contentStream.showText("E-Citizen Ref Number: ")
            contentStream.endText()

            // Add E-Citizen Ref Number
            contentStream.setStrokingColor(0.4f, 0.6f, 0.6f)
            contentStream.setFont(PDType1Font(Standard14Fonts.FontName.HELVETICA), 12f)
            val citizenValWidth = PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
                    .getStringWidth("E-Citizen Ref Number") * 12f / 1000f
            contentStream.beginText()
            contentStream.newLineAtOffset(550f - citizenValWidth, 665f) // Adjust the position
            contentStream.showText("QRFG9005")
            contentStream.endText()

            // Add a line to separate the header
            contentStream.setStrokingColor(0.4f, 0.6f, 0.6f)
            contentStream.setLineWidth(1f)
            contentStream.moveTo(50f, 630f) // Adjust the line start position
            contentStream.lineTo(560f, 630f) // Adjust the line end position
            contentStream.stroke()

            // Second Part: Labels and Values
            contentStream.setFont(PDType1Font(Standard14Fonts.FontName.HELVETICA), 12f)
            val labels = listOf("Name:", "Reference Number:", "Date of Purchase:", "Company Name:", "Type of Customer:", "Phone Number:", "Email:")
            val values = listOf("John Doe", "123456789", "2023-09-13", "Acme Inc.", "Preferred", "+254 0123456789", "info@acme.com")

            // Set initial position for labels and values
            val labelX = 50f
            var labelY = 590f
            val valueX = 200f

            contentStream.setStrokingColor(0f, 0f, 1f)

            // Write labels and values aligned vertically
            for (i in labels.indices) {
                contentStream.beginText()
                contentStream.newLineAtOffset(labelX, labelY)
                contentStream.showText(labels[i])
                contentStream.endText()
                contentStream.setStrokingColor(0f, 0f, 1f)

                contentStream.beginText()
                contentStream.newLineAtOffset(valueX, labelY)
                contentStream.showText(values[i])
                contentStream.endText()
                contentStream.setStrokingColor(0f, 0f, 0f)
                labelY -= 20f // Adjust the vertical spacing as needed
            }

            // Create a list of dummy data for the table
            val tableData = listOf(
                    InvoiceItem("STD001", "Product A", 10.00, 5, "Origin A", 2.00, 50.00),
                    InvoiceItem("STD002", "Product B", 15.00, 3, "Origin B", 1.50, 45.00),
                    InvoiceItem("STD003", "Product C", 8.00, 7, "Origin C", 1.20, 56.00)
            )

            // Define table headers
            val tableHeaders = listOf("No.", "Description", "Unit Price", "Quantity", "Origin", "Discount", "Amount")

            // Define cell widths for each column
            val columnWidths = listOf(50f, 180f, 65f, 45f, 55f, 45f, 55f) // Adjust the widths as needed

            // Set the initial position for the table
            var tableX = 50f
            var tableY = 400f

            // Set cell height
            val cellHeight = 20f

            // Set font for table headers
            contentStream.setFont(PDType1Font(Standard14Fonts.FontName.HELVETICA), 10f)
            contentStream.setStrokingColor(0.7f, 0.7f, 0.7f)

            // Create table headers
            for ((index, header) in tableHeaders.withIndex()) {
                contentStream.setStrokingColor(0f, 0f, 0f) // Black color for text
                contentStream.setLineWidth(0.5f)
                contentStream.addRect(tableX, tableY, columnWidths[index], cellHeight)
                contentStream.stroke()
                contentStream.beginText()
                contentStream.newLineAtOffset(tableX + 2, tableY + cellHeight - 12)
                contentStream.showText(header)
                contentStream.endText()
                tableX += columnWidths[index]
            }

            var tableDataY = tableY - cellHeight

            // Populate the table with data with light grey borders
            for (item in tableData) {
                tableX = 50f // Reset X-coordinate for each row

                contentStream.setStrokingColor(0.7f, 0.7f, 0.7f) // Light grey color for borders

                for ((index, value) in
                listOf(
                        item.standardNumber,
                        item.standardName,
                        item.unitPrice.toString(),
                        item.quantity.toString(),
                        item.origin,
                        item.discount.toString(),
                        item.amount.toString()
                ).withIndex()) {
                    contentStream.setLineWidth(0.5f) // Set border line width
                    contentStream.addRect(tableX, tableDataY, columnWidths[index], cellHeight)
                    contentStream.stroke()
                    contentStream.beginText()
                    contentStream.newLineAtOffset(tableX + 2, tableDataY + cellHeight - 12)
                    contentStream.showText(value)
                    contentStream.endText()
                    tableX += columnWidths[index]
                }

                tableDataY -= cellHeight
            }

            // Calculate and display totals
            val subtotal = tableData.sumByDouble { it.amount }
            val convenienceFee = 50.00
            val total = subtotal + convenienceFee

            contentStream.setFont(PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12f)

            // Set initial position for totals
            val totalsX = 300f
            var totalsY = 290f

            val labelValuePairs = listOf(
                    Pair("Total:", "$subtotal"),
                    Pair("Convenience Fee:", "$convenienceFee"),
                    Pair("Total Amount Payable:", "$total")
            )

// Set font and stroking color for labels
            contentStream.setFont(PDType1Font(Standard14Fonts.FontName.HELVETICA), 12f)
            contentStream.setStrokingColor(0f, 0f, 0f)

// Iterate through label-value pairs and display them in two columns
            for ((label, value) in labelValuePairs) {
                contentStream.beginText()
                contentStream.newLineAtOffset(totalsX, totalsY)
                contentStream.showText(label)
                contentStream.endText()

                contentStream.beginText()
                contentStream.newLineAtOffset(totalsX + 200f, totalsY)
                contentStream.showText(value)
                contentStream.endText()

                totalsY -= 20f // Adjust vertical spacing as needed
            }
            // Prepared On and Prepared By
            contentStream.setFont(PDType1Font(Standard14Fonts.FontName.HELVETICA), 12f)

            val preparedOnX = 50f
            val preparedOnY = 200f
            val preparedByX = 300f
            val preparedByY = 200f

            val preparedOnValue = "23/6/23" // You can replace this with actual data
            val preparedByValue = "Kabiru" // You can replace this with actual data

            contentStream.beginText()
            contentStream.newLineAtOffset(preparedOnX, preparedOnY)
            contentStream.showText("Prepared On:")
            contentStream.endText()

            contentStream.beginText()
            contentStream.setFont(PDType1Font(Standard14Fonts.FontName.HELVETICA), 12f)
            contentStream.setStrokingColor(0f, 0f, 0f)
            contentStream.newLineAtOffset(preparedOnX + 100f, preparedOnY)
            contentStream.showText(preparedOnValue)
            contentStream.endText()

            contentStream.beginText()
            contentStream.newLineAtOffset(preparedByX, preparedByY)
            contentStream.showText("Prepared By:")
            contentStream.endText()

            contentStream.beginText()
            contentStream.setFont(PDType1Font(Standard14Fonts.FontName.HELVETICA), 12f)
            contentStream.setStrokingColor(0f, 0f, 0f)
            contentStream.newLineAtOffset(preparedByX + 100f, preparedByY)
            contentStream.showText(preparedByValue)
            contentStream.endText()

            // Define the coordinates for the payment details box
            val boxX = 50f
            val boxY = 100f
            val boxWidth = pageWidth - 100f // Adjust the width to fit the page
            val boxHeight = 60f // Increase the height to accommodate the sentences and columns

// Set the border color and width for the box
            contentStream.setNonStrokingColor(0.7f, 0.7f, 0.7f) // Light grey color for border
            contentStream.setLineWidth(0.5f)
            contentStream.addRect(boxX, boxY, boxWidth, boxHeight)
            contentStream.stroke()

// Set font and text color for the payment details sentences
            contentStream.setFont(PDType1Font(Standard14Fonts.FontName.HELVETICA), 10f)
            contentStream.setNonStrokingColor(0f, 0f, 0f) // Black color for text

// Define the payment details sentences
            val sentence1 = "Note that eCitizen does not accept Electronic Funds Transfer Fund (EFT),"
            val sentence2 = "E-Money paid to other paybill number (not to 222222), Cheques and any other deferred payment"

// Set initial position for the first sentence
            var sentenceX = boxX + 10f
            var sentenceY = boxY + boxHeight - 20f // Adjust vertical position as needed

// Display each sentence inside the box
            contentStream.beginText()
            contentStream.newLineAtOffset(sentenceX, sentenceY)
            contentStream.showText(sentence1)
            contentStream.newLineAtOffset(0f, -18f) // Adjust vertical spacing as needed
            contentStream.showText(sentence2)
            contentStream.endText()

// Define the positions for the columns
            var column1X = boxX + 10f
            val column2X = boxX + boxWidth / 3
            val column3X = boxX + 2 * (boxWidth / 3)
            var columnY = boxY - 20f // Adjust vertical position for the columns

// Set font and text color for the columns
            contentStream.setFont(PDType1Font(Standard14Fonts.FontName.HELVETICA), 8f)
            contentStream.setNonStrokingColor(0f, 0f, 0f) // Black color for text

// Define the content for each column
            val column1Content = listOf(
                    "KCB BANK/RTGS/CASH",
                    "A/C Name: E-Citizen Collection Account",
                    "Bank Name: Kenya Commercial Bank",
                    "A/C Number: 123654",
                    "E-Citizen Ref No: BGTRY"
            )

            val column2Content = listOf(
                    "EQUITY BANK/RTGS/CASH",
                    "1. Visit Equity Branch",
                    "2. Ask to make Ecitizen payment",
                    "3. Bill Reference: BHGRTY",
                    "4. Amount: 6789"
            )

            val column3Content = listOf(
                    "MPESA/AIRTEL MONEY",
                    "Paybill Number: 22222",
                    "A/C Number: BHGRTY"
            )

// Display content in each column
            for (contentList in listOf(column1Content, column2Content, column3Content)) {
                for (line in contentList) {
                    contentStream.beginText()
                    contentStream.newLineAtOffset(column1X, columnY)
                    contentStream.showText(line)
                    contentStream.endText()
                    columnY -= 15f // Adjust vertical spacing as needed
                }
                columnY = boxY - 20f // Reset vertical position for the next column
                column1X += boxWidth / 3 // Adjust horizontal position for the next column
            }

            // Close the content stream
            contentStream.close()

            // Create a byte array output stream to store the PDF content
            val byteArrayOutputStream = ByteArrayOutputStream()

            // Save the PDF to the byte array output stream
            document.save(byteArrayOutputStream)

            // Close the document
            document.close()

            // Return the generated PDF as a byte array
            return byteArrayOutputStream.toByteArray()

        } catch (e: IOException) {
            // Handle exceptions or log errors here
            throw e
        }
    }



}