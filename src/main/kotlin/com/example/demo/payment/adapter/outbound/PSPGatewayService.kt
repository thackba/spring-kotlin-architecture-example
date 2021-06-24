package com.example.demo.payment.adapter.outbound

import com.example.demo.payment.domain.outbound.AuthorizePSPGateway
import com.example.demo.payment.domain.outbound.CancelPSPGateway
import com.example.demo.payment.domain.outbound.CapturePSPGateway
import com.example.demo.payment.model.AuthorizeRequest
import com.example.demo.payment.model.PaymentId
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.UUID

@Component
class PSPGatewayService : AuthorizePSPGateway, CancelPSPGateway, CapturePSPGateway {

    // Demo Implementation
    override fun authorize(authorizeRequest: AuthorizeRequest): PaymentId? {
        if (authorizeRequest.amount > BigDecimal("2000")) {
            return null
        }
        return UUID.randomUUID().toString()
    }

    // Demo Implementation
    override fun cancel(paymentId: PaymentId): Boolean {
        return true
    }

    // Demo Implementation
    override fun capture(paymentId: PaymentId): Boolean {
        return true
    }
}
