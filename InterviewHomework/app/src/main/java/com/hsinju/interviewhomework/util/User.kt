package com.hsinju.interviewhomework.util

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("username")
    var userName: String = ""

    @SerializedName("phone")
    var userPhone: String = ""

    @SerializedName("createdAt")
    var createAt: String = ""

    @SerializedName("updatedAt")
    var updateAt: String = ""

    @SerializedName("objectId")
    var objectId: String = ""

    @SerializedName("sessionToken")
    var sessionToken: String = ""
}