package com.example.demo.payment.domain.outbound

import com.example.demo.payment.model.PaymentId

interface CancelPSPGateway {

    fun cancel(paymentId: PaymentId): Boolean
}
