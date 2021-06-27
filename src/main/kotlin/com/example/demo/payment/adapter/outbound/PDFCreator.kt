package com.example.demo.payment.adapter.outbound

import com.example.demo.payment.domain.outbound.CreateInvoicePDF
import org.springframework.stereotype.Component

@Component
class PDFCreator() : CreateInvoicePDF {
    override fun createPDF(reference: String): String {
        return "PDF"
    }
}
