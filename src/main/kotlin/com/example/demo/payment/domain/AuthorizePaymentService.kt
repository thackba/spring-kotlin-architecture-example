package com.example.demo.payment.domain

import com.example.demo.payment.domain.inbound.AuthorizePayment
import com.example.demo.payment.domain.outbound.AuthorizePSPGateway
import com.example.demo.payment.domain.outbound.StorageSaveGateway
import com.example.demo.payment.model.AuthorizeRequest
import com.example.demo.payment.model.Payment
import com.example.demo.payment.model.PaymentId
import org.springframework.stereotype.Component

@Component
class AuthorizePaymentService(
    private val authorizePSPGateway: AuthorizePSPGateway,
    private val authorizeStorageSaveGateway: StorageSaveGateway
) : AuthorizePayment {

    override fun authorize(authorizeRequest: AuthorizeRequest): Boolean {
        val paymentId = authorizePSPGateway.authorize(authorizeRequest)
        return paymentId?.let {
            val payment = createPayment(it, authorizeRequest)
            authorizeStorageSaveGateway.storePayment(payment)
            return true
        } ?: false
    }

    private fun createPayment(paymentId: PaymentId, authorizeRequest: AuthorizeRequest): Payment {
        return Payment(
            paymentId,
            authorizeRequest.reference,
            authorizeRequest.customer,
            authorizeRequest.amount
        )
    }
}
