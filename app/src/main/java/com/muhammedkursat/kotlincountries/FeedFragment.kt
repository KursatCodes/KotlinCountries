package com.muhammedkursat.kotlincountries

import android.app.DirectAction
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.muhammedkursat.kotlincountries.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {
    private lateinit var bindingComponent: FragmentFeedBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingComponent=
            DataBindingUtil.inflate(layoutInflater,R.layout.fragment_feed,container,false)
        return bindingComponent.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingComponent.feedButton.setOnClickListener{
            var arg= bindingComponent.myEdittext.text.toString()
            var action = FeedFragmentDirections.actionFeedFragmentToDetailsFragment(arg)
            it.findNavController().navigate(action) //Navigation.findNavController(it).navigate(action)
        }
    }
}