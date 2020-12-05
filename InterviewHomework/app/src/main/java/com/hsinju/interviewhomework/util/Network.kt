package com.hsinju.interviewhomework.util

import android.util.Log
import com.google.gson.JsonObject
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class Network {
    private val baseUrl = "https://watch-master-staging.herokuapp.com"
    private val applicationId = "vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD"
    private var retrofit: Retrofit
    private var service: NetworkService

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(NetworkService::class.java)
    }

    fun userLogin(userAcc: String, pwd: String): User? {
        try {
            val response = service.login(applicationId, userAcc, pwd).execute()
            if (response.isSuccessful) {
                return response.body()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun updateTimezone(sessionToken: String, objectId: String, timezone: Int): Boolean {
        try {
            val timezoneStr = String.format("{\"timezone\":%d}", timezone)
            val request = RequestBody.create(MediaType.parse("application/json"), timezoneStr)

            val response = service.updateTimezone(applicationId, sessionToken, objectId, request)
            val result = response.execute()
            if (result.isSuccessful) {
                return true
            } else {
                Log.d("test", "error code = " + result.code())
                Log.d("test", "error reason = " + result.message())
                val body = result.errorBody()
                if (body != null) {
                    Log.d("test", "body = " + body.string())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}