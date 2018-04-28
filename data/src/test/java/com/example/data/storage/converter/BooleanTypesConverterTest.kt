package com.example.data.storage.converter

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
object BooleanTypesConverterTest: Spek({
    describe("boolean converter") {
        val booleanConverter = BooleanTypesConverter()

        on("true convert") {
            val trueSample = 1
            val trueConvertResult = booleanConverter.fromBoolean(true)

            it("true == 1") {
                assertEquals(trueSample, trueConvertResult)
            }
        }

        on("false convert") {
            val falseSample = 0
            val falseConvertResult = booleanConverter.fromBoolean(false)

            it("false == 0") {
                assertEquals(falseSample, falseConvertResult)
            }
        }

        on("1 convert") {
            val pseudoTrue = 1
            val convertResult = booleanConverter.toBoolean(pseudoTrue)

            it("1 == true") {
                assertEquals(true, convertResult)
            }
        }

        on("0 convert") {
            val pseudoFalse = 0
            val convertResult = booleanConverter.toBoolean(pseudoFalse)

            it("0 == false") {
                assertEquals(false, convertResult)
            }
        }
    }
})