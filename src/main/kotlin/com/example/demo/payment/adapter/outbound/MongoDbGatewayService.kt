package com.example.demo.payment.adapter.outbound

import com.example.demo.payment.adapter.outbound.entitiy.PaymentEntity
import com.example.demo.payment.adapter.outbound.entitiy.PaymentEntityRepository
import com.example.demo.payment.domain.outbound.StorageGetGateway
import com.example.demo.payment.domain.outbound.StorageRemoveGateway
import com.example.demo.payment.domain.outbound.StorageSaveGateway
import com.example.demo.payment.model.Payment
import org.springframework.stereotype.Component

@Component
class MongoDbGatewayService(
    private val paymentEntityRepository: PaymentEntityRepository
) : StorageGetGateway, StorageRemoveGateway, StorageSaveGateway {

    override fun getPayment(reference: String): Payment? {
        return paymentEntityRepository.findById(reference).map { it.toModel() }.orElse(null)
    }

    override fun removePayment(reference: String) {
        paymentEntityRepository.deleteById(reference)
    }

    override fun storePayment(payment: Payment) {
        paymentEntityRepository.save(PaymentEntity.fromModel(payment))
    }
}
