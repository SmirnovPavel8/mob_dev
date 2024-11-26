package com.example.myfirstapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =inflater.inflate(R.layout.fragment_login,container,false)
        val swapRegistrationTypeButton=root.findViewById<Button>(R.id.phoneNumberButton)
        val navController= NavHostFragment.findNavController(this)
        val enterRegisterEditText=root.findViewById<EditText>(R.id.registerEditText)
        val loginButton=root.findViewById<Button>(R.id.loginButton)
        val registrationData=requireActivity().getSharedPreferences("registrationData", Context.MODE_PRIVATE)
        val automaticEnterCheckBox=root.findViewById<CheckBox>(R.id.automaticEnterCheckBox)
        val automaticEnter=requireActivity().getSharedPreferences("automaticLogin", Context.MODE_PRIVATE)
        fun checkRegistrationData(){
            if(root.findViewById<EditText>(R.id.registerEditText).text.toString()==registrationData.getString("login","default")
                &&root.findViewById<EditText>(R.id.passwordEditText).text.toString()==registrationData.getString("password","default")) {
                automaticEnter.edit().putBoolean("automaticEnterFlag",automaticEnterCheckBox.isChecked).apply()
                navController.navigate(R.id.oneFragment)
            }
            else
                Toast.makeText(this.requireContext(),"incorrcet data",Toast.LENGTH_LONG).show()
        }
        swapRegistrationTypeButton.setOnClickListener {
            if (swapRegistrationTypeButton.text.toString() == "by phone") {
                swapRegistrationTypeButton.setText("by email");
                enterRegisterEditText.setHint("enter phone")
            } else {
                swapRegistrationTypeButton.setText("by phone");
                enterRegisterEditText.setHint("enter email")
            }
        }
        loginButton.setOnClickListener(){
            checkRegistrationData()
        }
        // Inflate the layout for this fragment
        return root
    }


}