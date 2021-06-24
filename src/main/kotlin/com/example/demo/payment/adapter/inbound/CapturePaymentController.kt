package com.example.demo.payment.adapter.inbound

import com.example.demo.payment.adapter.inbound.dto.ReferenceDto
import com.example.demo.payment.domain.inbound.CapturePayment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CapturePaymentController(val capturePayment: CapturePayment) {

    @PostMapping("/capture")
    fun invoke(@RequestBody referenceDto: ReferenceDto): ResponseEntity<Unit> {
        val success = capturePayment.capture(referenceDto.reference)
        if (success) {
            return ResponseEntity.ok().build()
        } else {
            return ResponseEntity.badRequest().build()
        }
    }
}
