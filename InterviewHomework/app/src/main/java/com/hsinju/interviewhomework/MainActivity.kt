package com.hsinju.interviewhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.hsinju.interviewhomework.fragments.LoginFragment
import com.hsinju.interviewhomework.fragments.ShowInfoFragment
import com.hsinju.interviewhomework.util.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        var ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, LoginFragment())
            .commit()
//        accountEdit = findViewById(R.id.user_account_edit)
//        pwdEdit = findViewById(R.id.user_pwd_edit)
//        loginBtn = findViewById(R.id.log_in_btn)
//        errorMsgText = findViewById(R.id.login_err_text)
//
//        val model =
//            MainViewModel(applicationContext)
//        val observerStr = Observer<String> {
//            newMsg -> {
//                errorMsgText.text = newMsg
//                errorMsgText.visibility = View.VISIBLE
//            }
//        }
//
//        val observerUser = Observer<User> {
//            newUser -> {
//                //TODO start another Activity
//
//
//            }
//        }
//
//        model.errorMsg.observe(this, observerStr)
//        model.user.observe(this, observerUser)
//
//        loginBtn.setOnClickListener {
//            val account = accountEdit.text.toString()
//            val pwd = pwdEdit.text.toString()
//            model.runOnClick(account, pwd)
//            //TODO add dialog
//        }
    }

    fun replaceFragment2Show() {
        val ft = supportFragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, ShowInfoFragment())
            .commit()
    }
}