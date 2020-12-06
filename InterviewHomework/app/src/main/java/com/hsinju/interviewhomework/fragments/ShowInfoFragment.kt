package com.hsinju.interviewhomework.fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hsinju.interviewhomework.MainViewModel
import com.hsinju.interviewhomework.R
import java.util.*

class ShowInfoFragment: Fragment(R.layout.fragment_show), OnItemSelectedListener {
    private lateinit var emailText: TextView
    private lateinit var timeZoneSpinner: Spinner
    private lateinit var updateMsgText: TextView
    private lateinit var viewModel: MainViewModel

    private val timeZones: ArrayList<String> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailText = view.findViewById(R.id.user_email_text)
        updateMsgText = view.findViewById(R.id.update_msg)
        timeZoneSpinner = view.findViewById(R.id.timezone_selector)

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        emailText.text = resources.getString(R.string.email_pretext) + " " + viewModel.email.value
        val observer = Observer<String>  {
            newMsg -> run {
                when(newMsg) {
                    MainViewModel.TAG_FAIL -> {
                        updateMsgText.text = resources.getString(R.string.update_failed)
                        updateMsgText.visibility = View.VISIBLE
                    }
                    MainViewModel.TAG_SUCCESS -> {
                        updateMsgText.text = resources.getString(R.string.update_success)

                        updateMsgText.visibility = View.VISIBLE
                    }
                    MainViewModel.TAG_CLEAR -> {
                        updateMsgText.visibility = View.INVISIBLE
                    }
                }
            }
        }
        viewModel.timeZoneMsg.observe(viewLifecycleOwner, observer)
        val tmp = resources.getStringArray(R.array.timezone)

        timeZones.add("Time Zone")
        for(t in tmp) {
            timeZones.add(t)
        }

        val adapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_dropdown_item_1line, timeZones)
        timeZoneSpinner.adapter = adapter
        timeZoneSpinner.onItemSelectedListener =  this
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val str = timeZones.get(p2)
        viewModel.runOnSelect(str)
    }
}