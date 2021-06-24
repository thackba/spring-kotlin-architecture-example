package com.example.demo.payment.domain.outbound

import com.example.demo.payment.model.PaymentId

interface CapturePSPGateway {

    fun capture(paymentId: PaymentId): Boolean
}
