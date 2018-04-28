package com.example.data.storage.converter

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
object LongListTypeConverterTest: Spek({
    describe("local date time converter") {
        val converter = LongListTypeConverter()
        val sample = "[1,2,4,2,2,1,4,43,23,23,89,234,12,3]"
        val list = listOf<Long>(1,2,4,2,2,1,4,43,23,23,89,234,12,3)

        on("convert List<Long> to string") {
            val convertResult = converter.fromList(list)

            it("$sample == $convertResult") {
                assertEquals(sample, convertResult)
            }
        }

        on("convert string to List<Long>") {
            val convertResult = converter.toList(sample)
            it("$list == $convertResult") {
                assertEquals(list, convertResult)
            }
        }
    }
})