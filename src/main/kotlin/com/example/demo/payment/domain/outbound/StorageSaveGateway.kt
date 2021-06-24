package com.example.demo.payment.domain.outbound

import com.example.demo.payment.model.Payment

interface StorageSaveGateway {

    fun storePayment(payment: Payment)
}
