package com.example.demo.payment.adapter.inbound

import com.example.demo.events.envelope.v1.EnvelopeProto
import com.example.demo.events.external.v1.ExternalProto as V1ExternalProto
import com.example.demo.events.external.v2.ExternalProto as V2ExternalProto
import com.example.demo.payment.domain.inbound.CancelPayment
import com.example.demo.payment.domain.inbound.CapturePayment
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ExternalEventListener(
    private val cancelPayment: CancelPayment,
    private val capturePayment: CapturePayment
) {

    @KafkaListener(topics = ["messages"], groupId = "consumer")
    fun listen(message: ByteArray) {
        val envelope = EnvelopeProto.Envelope.parseFrom(message)
        when (envelope.header.content) {
            "cancelled" -> handleCancelled(envelope)
            "captured" -> handleFulfilled(envelope)
            else -> {
                println("Unknown Event ${envelope.header.content}")
            }
        }
    }

    private fun handleCancelled(envelope: EnvelopeProto.Envelope) {
        when (envelope.header.version) {
            1 -> {
                val event = V1ExternalProto.Cancelled.parseFrom(envelope.payload)
                if (!cancelPayment.cancel(event.reference)) {
                    println("Could not cancel payment!")
                }
            }
            2 -> {
                val event = V2ExternalProto.Cancelled.parseFrom(envelope.payload)
                if (!cancelPayment.cancel(event.order.reference)) {
                    println("Could not cancel payment!")
                }
            }
            else -> {
                println("Unknown Cancelled Version ${envelope.header.version}")
            }
        }
    }

    private fun handleFulfilled(envelope: EnvelopeProto.Envelope) {
        when (envelope.header.version) {
            1 -> {
                val event = V1ExternalProto.Fulfilled.parseFrom(envelope.payload)
                if (!capturePayment.capture(event.reference)) {
                    println("Could not capture payment!")
                }
            }
            2 -> {
                val event = V2ExternalProto.Fulfilled.parseFrom(envelope.payload)
                if (!capturePayment.capture(event.order.reference)) {
                    println("Could not capture payment!")
                }
            }
            else -> {
                println("Unknown Fulfilled Version ${envelope.header.version}")
            }
        }
    }
}
