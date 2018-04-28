package com.example.data.storage.converter

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
object StringListTypeConverterTest: Spek({
    describe("local date time converter") {
        val converter = StringListTypeConverter()
        val sample = "[\"asd\",\"errth\",\"poi\"]"
        val list = listOf("asd", "errth", "poi")

        on("convert List<String> to string") {
            val convertResult = converter.fromList(list)

            it("$sample == $convertResult") {
                assertEquals(sample, convertResult)
            }
        }

        on("convert string to List<String>") {
            val convertResult = converter.toList(sample)
            it("$list == $convertResult") {
                assertEquals(list, convertResult)
            }
        }
    }
})