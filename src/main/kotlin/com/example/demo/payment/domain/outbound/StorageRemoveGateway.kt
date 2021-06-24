package com.example.demo.payment.domain.outbound

interface StorageRemoveGateway {

    fun removePayment(reference: String)
}
