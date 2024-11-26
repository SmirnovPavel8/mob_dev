package com.example.myfirstapp




import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Fragment1 : Fragment() {
private val myAdapter =PhonesAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root=inflater.inflate(R.layout.fragment_1,container,false)
        loadData()
        root.findViewById<RecyclerView>(R.id.recyclerView).layoutManager=LinearLayoutManager(requireContext())
        root.findViewById<RecyclerView>(R.id.recyclerView).adapter=myAdapter
        // Inflate the layout for this fragment
        return root
    }
    private fun loadData(){
        myAdapter.setupPhones(PhonesData.phonesArr)
    }
}