package com.hsinju.interviewhomework.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.hsinju.interviewhomework.MainActivity
import com.hsinju.interviewhomework.MainViewModel
import com.hsinju.interviewhomework.R

class LoginFragment: Fragment(R.layout.fragment_login) {
    private lateinit var accountEdit: TextInputEditText
    private lateinit var pwdEdit: TextInputEditText
    private lateinit var loginBtn: Button
    private lateinit var errorMsgText: TextView

    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountEdit = view.findViewById(R.id.user_account_edit)
        pwdEdit = view.findViewById(R.id.user_pwd_edit)
        loginBtn = view.findViewById(R.id.log_in_btn)
        errorMsgText = view.findViewById(R.id.login_err_text)

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val observerMsg = Observer<String>(){
            newMsg -> run {
                when(newMsg) {
                    MainViewModel.TAG_MISS_ACCOUNT ->  {
                        errorMsgText.text = resources.getString(R.string.user_name_empty)
                        errorMsgText.visibility = View.VISIBLE
                    }
                    MainViewModel.TAG_MISS_PWD -> {
                        errorMsgText.text = resources.getString(R.string.user_pwd_empty)
                        errorMsgText.visibility = View.VISIBLE
                    }
                    MainViewModel.TAG_INCORRECT_INFO -> {
                        errorMsgText.text = resources.getString(R.string.user_info_incorrect)
                        errorMsgText.visibility = View.VISIBLE
                    }
                    MainViewModel.TAG_SUCCESS -> {
                        var activity = requireActivity() as MainActivity
                        activity.replaceFragment2Show()
                    }
                }
            }
        }
        viewModel.errorMsg.observe(viewLifecycleOwner, observerMsg)
        loginBtn.setOnClickListener {
            val acc = accountEdit.text.toString()
            val pwd = pwdEdit.text.toString()
            viewModel.runOnClick(acc, pwd)
        }
    }
}