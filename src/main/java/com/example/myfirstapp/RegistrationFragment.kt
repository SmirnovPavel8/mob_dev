package com.example.myfirstapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistrationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistrationFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =inflater.inflate(R.layout.fragment_registration,container,false)
        val swapRegistrationTypeButton=root.findViewById<Button>(R.id.phoneNumberButton)
        val enterRegisterEditText=root.findViewById<EditText>(R.id.registerEditText)
        val registerButton=root.findViewById<Button>(R.id.registerButton)
        val registrationData=requireActivity().getSharedPreferences("registrationData", Context.MODE_PRIVATE)
        val navController= NavHostFragment.findNavController(this)
        fun registrationDataSaveFun(login:String,password:String){
            registrationData.edit().putString("login",login).apply()
            registrationData.edit().putString("password",password).apply()
        }
        swapRegistrationTypeButton.setOnClickListener {
            if (swapRegistrationTypeButton.text.toString() == "by phone") {
                swapRegistrationTypeButton.text = "by email"
                enterRegisterEditText.hint = "enter phone"
            } else {
                swapRegistrationTypeButton.text = "by phone"
                enterRegisterEditText.hint = "enter email"
            }
        }
        registerButton.setOnClickListener{
            val myText=enterRegisterEditText.text.toString();
            val passwordText=root.findViewById<EditText>(R.id.passwordEditText).text.toString()
            val repeatPasswordText=root.findViewById<EditText>(R.id.repeatPasswordEditText).text.toString()
            var caseValue=0;
            if(enterRegisterEditText.hint.toString()=="enter email")
                if(!myText.contains("@")) caseValue=1
            if(enterRegisterEditText.hint.toString()=="enter phone")
                if (myText[0]!='+') caseValue = 2
            if(passwordText.length<8) caseValue=3
            if(passwordText!=repeatPasswordText) caseValue=4
            when (caseValue){
                0->{
                    Toast.makeText(this.requireContext(),"registration successful", Toast.LENGTH_LONG).show()
                    registrationDataSaveFun(requireActivity().findViewById<EditText>(R.id.registerEditText).text.toString(),requireActivity().findViewById<EditText>(R.id.passwordEditText).text.toString())
                    navController.navigate(R.id.oneFragment)
                }
                1-> Toast.makeText(this.requireContext(),"use @ in email", Toast.LENGTH_LONG).show()
                2-> Toast.makeText(this.requireContext(),"use + in number", Toast.LENGTH_LONG).show()
                3-> Toast.makeText(this.requireContext(),"password less than 8 characters", Toast.LENGTH_LONG).show()
                4-> Toast.makeText(this.requireContext(),"passwords do not match", Toast.LENGTH_LONG).show()
            }
        }

        return root
    }


}

