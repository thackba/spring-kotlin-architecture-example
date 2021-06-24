package com.example.demo.payment.domain.inbound

interface CancelPayment {

    fun cancel(reference: String): Boolean
}
