package com.example.demo.payment.adapter.outbound.entitiy

import org.springframework.data.repository.CrudRepository

interface PaymentEntityRepository : CrudRepository<PaymentEntity, String>
