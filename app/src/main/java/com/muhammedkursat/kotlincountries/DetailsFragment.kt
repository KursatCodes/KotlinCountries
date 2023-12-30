package com.muhammedkursat.kotlincountries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.muhammedkursat.kotlincountries.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
private lateinit var binding: FragmentDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_details,container,false)
        //return inflater.inflate(R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            var gelen = DetailsFragmentArgs.fromBundle(it).detailArg
            binding.textMy.setText(gelen)
        }
        binding.button2.setOnClickListener {
            var action = DetailsFragmentDirections.actionDetailsFragmentToFeedFragment("as")
            Navigation.findNavController(it).navigate(action)

        }
    }
}