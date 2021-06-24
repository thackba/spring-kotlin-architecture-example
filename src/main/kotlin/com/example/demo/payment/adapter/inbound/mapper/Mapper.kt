package com.example.demo.payment.adapter.inbound.mapper

import com.example.demo.payment.adapter.inbound.dto.AuthorizeRequestDto
import com.example.demo.payment.model.AuthorizeRequest

fun <T> simpleMapper(element: T?, name: String): T {
    return element ?: throw IllegalArgumentException("Missing $name")
}

fun AuthorizeRequestDto.toModel(): AuthorizeRequest {
    return AuthorizeRequest(
        reference = simpleMapper(reference, "reference"),
        customer = simpleMapper(customer, "customer"),
        amount = simpleMapper(amount, "amount")
    )
}
