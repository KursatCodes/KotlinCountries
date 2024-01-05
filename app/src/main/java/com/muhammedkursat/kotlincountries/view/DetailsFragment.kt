package com.muhammedkursat.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.muhammedkursat.kotlincountries.R
import com.muhammedkursat.kotlincountries.databinding.FragmentDetailsBinding
import com.muhammedkursat.kotlincountries.util.downloadImagesFromUrl
import com.muhammedkursat.kotlincountries.util.placeHolderProgressBar
import com.muhammedkursat.kotlincountries.viewmodel.DetailViewModel

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_details,container,false)
        //return inflater.inflate(R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var position = 0
        arguments?.let {
            position = DetailsFragmentArgs.fromBundle(it).detailArg
        }
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.refreshDetails(position)
        observerLiveData()
    }

    private fun observerLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.nameTextDetail.setText(it.countryName)
                binding.capitalTextDetail.setText(it.countryCapital)
                binding.currencyTextDetail.setText(it.countryCurrency)
                binding.languageTextDetail.setText(it.countryLanguage)
                binding.regionTextDetail.setText(it.countryRegion)
                binding.flagImage.downloadImagesFromUrl(
                    it.countryFlag,
                    placeHolderProgressBar(viewModel.getApplication())
                )
            }
        })

    }
}