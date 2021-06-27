package com.example.demo.payment.domain.inbound

interface CreateInvoice {

    fun createInvoice(reference: String): Boolean
}
