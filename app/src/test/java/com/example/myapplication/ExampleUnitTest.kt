package com.example.myapplication

import com.example.myapplication.example_study.dto.MaskDTO
import com.example.myapplication.example_study.repository.MaskService
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import kotlin.jvm.Throws

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 3)
    }
}