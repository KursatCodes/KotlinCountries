package com.muhammedkursat.kotlincountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.muhammedkursat.kotlincountries.R
import com.muhammedkursat.kotlincountries.model.Country

class CountryAdapter(val countryList: ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    class CountryViewHolder(var view:View) :ViewHolder(view){//holder sinifimiz
        val nametext= view.findViewById<TextView>(R.id.nameText)
        val regiontext= view.findViewById<TextView>(R.id.regionText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {// layout ile adapteri bagliyoruz
        val inflater = LayoutInflater.from(parent.context)
        val asd = inflater.inflate(R.layout.item_country,parent,false)
        return CountryViewHolder(asd)
    }

    override fun getItemCount(): Int {// RecyclerView'de kac adet row olacak onu belitiyoruz
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {// adapterdeki itemlara ulasıyoruz
        holder.nametext.text = countryList[position].countryName
        holder.regiontext.text = countryList[position].countryRegion
    }
    fun updateCountryList(newList: List<Country>){
        countryList.clear()
        countryList.addAll(newList)
        notifyDataSetChanged() // adaptoru yenilemek icin kullaniyoruz
    }
}