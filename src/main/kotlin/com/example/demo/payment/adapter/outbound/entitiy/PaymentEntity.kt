package com.example.demo.payment.adapter.outbound.entitiy

import com.example.demo.payment.model.Payment
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Instant

@Document
class PaymentEntity {
    @Id
    var reference: String? = null
    var paymentId: String? = null
    var customer: String? = null
    var amount: BigDecimal? = null

    @CreatedDate
    var createdAt: Instant? = null

    companion object {
        fun fromModel(payment: Payment): PaymentEntity {
            val entity = PaymentEntity()
            entity.reference = payment.reference
            entity.paymentId = payment.paymentId
            entity.customer = payment.customer
            entity.amount = payment.amount
            return entity
        }
    }

    fun toModel(): Payment {
        return Payment(
            reference ?: "",
            paymentId ?: "",
            customer ?: "",
            amount ?: BigDecimal.ZERO
        )
    }
}
