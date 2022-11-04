package com.example.foodsearch

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.foodsearch.Model.Food
import com.example.foodsearch.databinding.FragmentFirstBinding
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_second.*
import okhttp3.*
import java.io.IOException
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    //var mainActivity: MainActivity = activity as MainActivity

    private var _binding: FragmentFirstBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonSearch.setOnClickListener {
            MainActivity.input = binding.textInput.text.toString()
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.imagemic.setOnClickListener {// mainActivity.speak()
           (activity as MainActivity).speak()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}