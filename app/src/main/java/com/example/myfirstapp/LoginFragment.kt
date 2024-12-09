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
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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
        val automaticEnterCheckBox=root.findViewById<CheckBox>(R.id.automaticEnterCheckBox)
        val enterType=requireActivity().getSharedPreferences("enterType",Context.MODE_PRIVATE)


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
            val auth=FirebaseAuth.getInstance()
            if(automaticEnterCheckBox.isChecked)
                enterType.edit().putString("enterType","autologin").apply()
            auth.signInWithEmailAndPassword(root.findViewById<EditText>(R.id.registerEditText).text.toString(),
            root.findViewById<EditText>(R.id.passwordEditText).text.toString()).addOnCompleteListener{ task->
                if(task.isSuccessful){
                    navController.navigate(R.id.oneFragment)
                }
            }.addOnFailureListener{exception ->
                Toast.makeText(requireContext(),exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
        // Inflate the layout for this fragment
        return root
    }


}