package com.example.demo.payment.domain.outbound

interface CreateInvoicePDF {

    fun createPDF(reference: String): String
}
