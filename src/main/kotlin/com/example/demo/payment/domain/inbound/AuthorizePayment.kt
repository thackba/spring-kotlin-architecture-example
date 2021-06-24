package com.example.demo.payment.domain.inbound

import com.example.demo.payment.model.AuthorizeRequest

interface AuthorizePayment {

    fun authorize(authorizeRequest: AuthorizeRequest): Boolean
}
