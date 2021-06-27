package com.example.demo.payment.domain

import com.example.demo.payment.domain.inbound.CreateInvoice
import com.example.demo.payment.domain.outbound.CreateInvoicePDF
import org.springframework.stereotype.Component

@Component
class CreateInvoiceService(val createInvoicePDF: CreateInvoicePDF) : CreateInvoice {

    override fun createInvoice(reference: String): Boolean {
        val pdf = createInvoicePDF.createPDF(reference)
        // send email invoice
        return true
    }
}
