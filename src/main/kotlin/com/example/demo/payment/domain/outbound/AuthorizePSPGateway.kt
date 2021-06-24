package com.example.demo.payment.domain.outbound

import com.example.demo.payment.model.AuthorizeRequest
import com.example.demo.payment.model.PaymentId

interface AuthorizePSPGateway {

    fun authorize(authorizeRequest: AuthorizeRequest): PaymentId?
}
