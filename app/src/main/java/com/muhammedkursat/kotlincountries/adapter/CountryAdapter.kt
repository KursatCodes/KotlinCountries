package com.muhammedkursat.kotlincountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.muhammedkursat.kotlincountries.R
import com.muhammedkursat.kotlincountries.databinding.ItemCountryBinding
import com.muhammedkursat.kotlincountries.model.Country
import com.muhammedkursat.kotlincountries.util.downloadImagesFromUrl
import com.muhammedkursat.kotlincountries.util.placeHolderProgressBar
import com.muhammedkursat.kotlincountries.view.FeedFragmentDirections

class CountryAdapter(var countryList: ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener {
    class CountryViewHolder(var view: ItemCountryBinding) :ViewHolder(view.root){//holder sinifimiz
//        val nametext= view.findViewById<TextView>(R.id.nameText)
//        val regiontext= view.findViewById<TextView>(R.id.regionText)
//        val imageView = view.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {// layout ile adapteri bagliyoruz
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.item_country,parent,false)
        val view = DataBindingUtil.inflate<ItemCountryBinding>(inflater,R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {// RecyclerView'de kac adet row olacak onu belitiyoruz
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {// adapterdeki itemlara ulasıyoruz

        holder.view.country = countryList[position]
        holder.view.clickListener = this


//        holder.nametext.text = countryList[position].countryName
//        holder.regiontext.text = countryList[position].countryRegion
//        holder.view.setOnClickListener {
//            var action = FeedFragmentDirections.actionFeedFragmentToDetailsFragment(countryList[position].uuID)
//            Navigation.findNavController(it).navigate(action)
//        }
//        holder.imageView.downloadImagesFromUrl( // extention olarak tanimladigimiz icin hazir fonksiyon
//            countryList[position].countryFlag, //   gibi kullanabiliyoruz
//            placeHolderProgressBar(holder.view.context)
//        )
    }
    fun updateCountryList(newList: List<Country>){
        countryList.clear()
        countryList.addAll(newList)
        notifyDataSetChanged() // adaptoru yenilemek icin kullaniyoruz
    }

    override fun onCountryClicked(v: View) {
        //var uuID = ItemCountryBinding.bind(v).uuIDText.toString().toInt()
        val binding = DataBindingUtil.getBinding<ItemCountryBinding>(v)
        var uuID = binding?.uuIDText?.text.toString().toInt()
        var action = FeedFragmentDirections.actionFeedFragmentToDetailsFragment(uuID)
        Navigation.findNavController(v).navigate(action)
    }
}