package com.example.myfirstapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =inflater.inflate(R.layout.fragment_splash,container,false)
        val navController=NavHostFragment.findNavController(this)
        val registrationData=requireActivity().getSharedPreferences("registrationData", Context.MODE_PRIVATE)
        val automaticEnter=requireActivity().getSharedPreferences("automaticLogin",Context.MODE_PRIVATE)
        val login = registrationData.getString("login", "def")
       if(login!="def") {
           if (automaticEnter.getBoolean("automaticEnterFlag", false)) {
               navController.navigate(R.id.oneFragment)
           } else {
               navController.navigate(R.id.loginFragment)
           }
       }
       else{
            navController.navigate(R.id.registrationFragment)
        }
        return root
    }


}