package com.example.myfirstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PhonesAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mPhoneList:ArrayList<PhoneModel> =ArrayList()
    fun setupPhones(phoneList:ArrayList<PhoneModel>){
        mPhoneList.clear()
        mPhoneList.addAll(phoneList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val itemView=layoutInflater.inflate(R.layout.recyclerview_item,parent,false)
        return PhonesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is PhonesViewHolder){
            holder.bind(mPhones=mPhoneList[position])
        }
    }

    override fun getItemCount(): Int {
        return mPhoneList.count()
    }
    class PhonesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(mPhones: PhoneModel){
            itemView.findViewById<TextView>(R.id.name).text=mPhones.name
            itemView.findViewById<TextView>(R.id.price).text=mPhones.price
            itemView.findViewById<TextView>(R.id.date).text=mPhones.date
            itemView.findViewById<TextView>(R.id.score).text=mPhones.score
        }
    }
}



