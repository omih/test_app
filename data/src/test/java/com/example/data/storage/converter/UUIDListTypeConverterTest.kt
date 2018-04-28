package com.example.data.storage.converter

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import java.util.*

@RunWith(JUnitPlatform::class)
object UUIDListTypeConverterTest: Spek({
    describe("local date time converter") {
        val converter = UUIDListTypeConverter()
        val list = listOf(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())
        val sample = "${list.map { "\"$it\"" }}".replace(" ", "")

        on("convert List<UUID> to string") {
            val convertResult = converter.fromList(list)

            it("$sample == $convertResult") {
                assertEquals(sample, convertResult)
            }
        }

        on("convert string to List<UUID>") {
            val convertResult = converter.toList(sample)
            it("$list == $convertResult") {
                assertEquals(list, convertResult)
            }
        }
    }
})