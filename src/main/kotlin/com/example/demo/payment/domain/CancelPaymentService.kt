package com.example.demo.payment.domain

import com.example.demo.payment.domain.inbound.CancelPayment
import com.example.demo.payment.domain.outbound.CancelPSPGateway
import com.example.demo.payment.domain.outbound.StorageGetGateway
import com.example.demo.payment.domain.outbound.StorageRemoveGateway
import com.example.demo.payment.model.PaymentId
import org.springframework.stereotype.Component

@Component
class CancelPaymentService(
    private val storageGetGateway: StorageGetGateway,
    private val cancelPSPGateway: CancelPSPGateway,
    private val storageRemoveGateway: StorageRemoveGateway
) : CancelPayment {

    override fun cancel(reference: String): Boolean {
        val maybePayment = storageGetGateway.getPayment(reference)
        return maybePayment?.let { payment ->
            val pspResult = cancelPSPGateway.cancel(payment.paymentId)
            if (pspResult) {
                storageRemoveGateway.removePayment(reference)
            }
            pspResult
        } ?: true
    }
}
