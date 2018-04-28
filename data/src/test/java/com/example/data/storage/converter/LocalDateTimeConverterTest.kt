package com.example.data.storage.converter

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.joda.time.DateTime
import org.junit.Assert.assertEquals
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
object LocalDateTimeConverterTest: Spek({
    describe("local date time converter") {
        val converter = LocalDateTimeConverter()

        on("convert DateTimeLocal to long") {
            val dateTime = DateTime().toLocalDateTime()
            val sample = dateTime.toDateTime().millis
            val convertResult = converter.fromDateTime(dateTime)

            it("$sample == $convertResult") {
                assertEquals(sample, convertResult)
            }
        }

        on("convert millis to DateTimeLocal") {
            val millis = System.currentTimeMillis()
            val sample = DateTime(millis).toLocalDateTime()
            val convertResult = converter.toDateTime(millis)
            it("$sample == $convertResult") {
                assertEquals(sample, convertResult)
            }
        }
    }
})