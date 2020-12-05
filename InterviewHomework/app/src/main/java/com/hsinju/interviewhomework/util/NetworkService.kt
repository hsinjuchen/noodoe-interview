package com.hsinju.interviewhomework.util

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    @FormUrlEncoded
    @POST("/api/login")
    fun login(@Header("X-Parse-Application-Id") applicationId: String
              , @Field("username") userName: String
              , @Field("password") pwd: String): Call<User>

    @Headers("Content-Type: application/json")
    @PUT("/api/users/{objectId}")
    fun updateTimezone(@Header("X-Parse-Application-Id") applicationId: String
                       , @Header("X-Parse-Session-Token") sessionToken: String
                       , @Path("objectId") objectId: String
                       , @Body timezone: RequestBody): Call<ResponseBody>
}