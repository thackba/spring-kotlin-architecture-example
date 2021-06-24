package com.example.demo.payment.model

import java.math.BigDecimal

data class AuthorizeRequest(
    val reference: String,
    val customer: String,
    val amount: BigDecimal
)
