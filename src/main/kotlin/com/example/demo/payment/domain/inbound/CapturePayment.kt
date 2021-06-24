package com.example.demo.payment.domain.inbound

interface CapturePayment {

    fun capture(reference: String): Boolean
}
