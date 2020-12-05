package com.hsinju.interviewhomework

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hsinju.interviewhomework.R
import com.hsinju.interviewhomework.util.Network
import com.hsinju.interviewhomework.util.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel: ViewModel() {
    companion object {
        val TAG_MISS_ACCOUNT = "miss_account"
        val TAG_MISS_PWD = "miss_password"
        val TAG_INCORRECT_INFO = "incorrect_info"
        val TAG_SUCCESS = "success"
        val TAG_FAIL = "fail"
        val TAG_CLEAR = "clear"
    }

    val errorMsg: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    val user: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }

    val email: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val timeZoneMsg: MutableLiveData<String> by lazy {
        MutableLiveData<String> ()
    }

    private val netWork = Network()

    fun runOnClick(userName: String, userPwd: String) {
        if (userName.equals("")) {
            errorMsg.value = TAG_MISS_ACCOUNT
            return
        } else if (userPwd.equals("")) {
            errorMsg.value = TAG_MISS_PWD
            return
        }
        GlobalScope.launch(Dispatchers.IO) {
            val result = netWork.userLogin(userName, userPwd)
            if (result == null) {
                errorMsg.postValue(TAG_INCORRECT_INFO)
            } else {
                user.postValue(result)
                email.postValue(userName)
                errorMsg.postValue(TAG_SUCCESS)
            }
        }
    }

    fun runOnSelect(select: String) {
        if (select.equals("")) {
            timeZoneMsg.value = TAG_FAIL
            return
        } else if (select.equals("Time Zone")) {
            timeZoneMsg.value = TAG_CLEAR
            return
        }
        val timezoneInt: Int = Integer.parseInt(select.replace("GMT", ""))
        Log.d("test", "" + timezoneInt)
        val mUser = user.value
        if (mUser != null) {
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val result =
                        netWork.updateTimezone(mUser.sessionToken, mUser.objectId, timezoneInt)
                    if (result) {
                        timeZoneMsg.postValue(TAG_SUCCESS)
                        return@launch
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                timeZoneMsg.postValue(TAG_FAIL)
            }
        } else {
            Log.d("test", "user is null")
            timeZoneMsg.postValue(TAG_FAIL)
        }
    }
}
