package com.example.demo.payment.adapter.inbound.dto

import java.math.BigDecimal

data class AuthorizeRequestDto(
    var reference: String?,
    var customer: String?,
    var amount: BigDecimal?
) {
    fun isValid(): Boolean {
        return (this.amount != null && this.amount!! > BigDecimal.ZERO)
    }
}
