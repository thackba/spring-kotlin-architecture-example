package com.example.demo.payment.domain

import com.example.demo.payment.domain.inbound.CapturePayment
import com.example.demo.payment.domain.outbound.CapturePSPGateway
import com.example.demo.payment.domain.outbound.StorageGetGateway
import com.example.demo.payment.domain.outbound.StorageRemoveGateway
import org.springframework.stereotype.Component

@Component
class CapturePaymentService(
    private val storageGetGateway: StorageGetGateway,
    private val capturePSPGateway: CapturePSPGateway,
    private val storageRemoveGateway: StorageRemoveGateway
) : CapturePayment {

    override fun capture(reference: String): Boolean {
        val maybePayment = storageGetGateway.getPayment(reference)
        return maybePayment?.let { payment ->
            val pspResult = capturePSPGateway.capture(payment.paymentId)
            if (pspResult) {
                storageRemoveGateway.removePayment(reference)
            }
            pspResult
        } ?: true
    }
}
