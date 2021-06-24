package com.example.demo.payment.model

import java.math.BigDecimal

typealias PaymentId = String

data class Payment(
    val paymentId: PaymentId,
    val reference: String,
    val customer: String,
    val amount: BigDecimal
)
