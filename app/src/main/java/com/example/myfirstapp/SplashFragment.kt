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
        val enterType=requireActivity().getSharedPreferences("enterType",Context.MODE_PRIVATE)
        if(enterType.getString("enterType","default")=="default")
            navController.navigate(R.id.registrationFragment)
        else if(enterType.getString("enterType","default")=="login")
            navController.navigate(R.id.loginFragment)
        else if(enterType.getString("enterType","default")=="autologin")
            navController.navigate(R.id.oneFragment)
        return root
    }


}