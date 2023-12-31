package com.muhammedkursat.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammedkursat.kotlincountries.R
import com.muhammedkursat.kotlincountries.adapter.CountryAdapter
import com.muhammedkursat.kotlincountries.databinding.FragmentFeedBinding
import com.muhammedkursat.kotlincountries.viewmodel.FeedViewModel

class FeedFragment : Fragment() {
    private lateinit var bindingComponent: FragmentFeedBinding
    private lateinit var viewModel: FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingComponent=
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_feed,container,false)
        return bindingComponent.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshCountries()

        bindingComponent.countryListem.layoutManager = LinearLayoutManager(context)
        bindingComponent.countryListem.adapter = countryAdapter
        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.countryList.observe(viewLifecycleOwner, Observer {
            it?.let {
                  bindingComponent.countryListem.visibility= View.VISIBLE
                bindingComponent.errorMessage.visibility= View.GONE
                bindingComponent.progressBar.visibility= View.GONE
                countryAdapter.updateCountryList(it)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner,Observer{
            if(it) {
                bindingComponent.errorMessage.visibility= View.VISIBLE
                bindingComponent.countryListem.visibility= View.GONE
                bindingComponent.progressBar.visibility= View.GONE

            }
        })
        viewModel.countryLoading.observe(viewLifecycleOwner,Observer{
            if(it){
                bindingComponent.errorMessage.visibility= View.GONE
                bindingComponent.countryListem.visibility= View.GONE
                bindingComponent.progressBar.visibility= View.VISIBLE
            }

        })

    }
}