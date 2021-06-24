package com.example.demo.payment.adapter.inbound

import com.example.demo.payment.adapter.inbound.dto.ReferenceDto
import com.example.demo.payment.domain.inbound.CancelPayment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CancelPaymentController(val cancelPayment: CancelPayment) {

    @PostMapping("/cancel")
    fun invoke(@RequestBody referenceDto: ReferenceDto): ResponseEntity<Unit> {
        val success = cancelPayment.cancel(referenceDto.reference)
        if (success) {
            return ResponseEntity.ok().build()
        } else {
            return ResponseEntity.badRequest().build()
        }
    }
}
