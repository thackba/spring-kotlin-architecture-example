package com.example.demo.payment.domain.outbound

import com.example.demo.payment.model.Payment

interface StorageGetGateway {

    fun getPayment(reference: String): Payment?
}
