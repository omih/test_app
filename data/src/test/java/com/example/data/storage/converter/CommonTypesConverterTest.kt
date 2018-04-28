package com.example.data.storage.converter

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.joda.time.DateTime
import org.junit.Assert.assertEquals
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import java.util.*

@RunWith(JUnitPlatform::class)
object CommonTypesConverterTest: Spek({
    describe("common types converter") {
        val converter = CommonTypesConverter()
        val uuidTest = UUID.randomUUID()
        val uuidStringSample = uuidTest.toString()

        on("convert uuid to string") {
            val convertResult = converter.fromUUID(uuidTest)
            it ("$uuidStringSample == $convertResult") {
                assertEquals(uuidStringSample, convertResult)
            }
        }

        on("convert string to uuid") {
            val convertResult = converter.toUUID(uuidStringSample)

            it("$convertResult == $uuidTest") {
                assertEquals(uuidTest, convertResult)
            }
        }

        on("convert datetime to long") {
            val dateTime = DateTime()
            val convertResult = converter.fromDateTime(dateTime)

            it("${dateTime.millis} == $convertResult") {
                assertEquals(dateTime.millis, convertResult)
            }
        }

        on("convert millis to DateTime") {
            val millis = System.currentTimeMillis()
            val dateTimeSample = DateTime(millis)
            val convertResult = converter.toDateTime(millis)
            it("$dateTimeSample == $convertResult") {
                assertEquals(dateTimeSample, convertResult)
            }
        }
    }
})