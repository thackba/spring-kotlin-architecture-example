package com.example.demo.payment.adapter.inbound

import com.example.demo.payment.adapter.inbound.dto.AuthorizeRequestDto
import com.example.demo.payment.adapter.inbound.mapper.toModel
import com.example.demo.payment.domain.inbound.AuthorizePayment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthorizationController(val authorizePayment: AuthorizePayment) {

    @PostMapping("/authorize")
    fun invoke(@RequestBody request: AuthorizeRequestDto): ResponseEntity<Unit> {
        val success = if (request.isValid()) {
            val model = request.toModel()
            authorizePayment.authorize(model)
        } else {
            false
        }

        if (success) {
            return ResponseEntity.accepted().build()
        } else {
            return ResponseEntity.badRequest().build()
        }
    }
}
