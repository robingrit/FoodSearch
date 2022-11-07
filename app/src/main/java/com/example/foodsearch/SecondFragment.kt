package com.example.foodsearch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodsearch.MainActivity.Json.input
import com.example.foodsearch.Model.Food
import com.example.foodsearch.databinding.FragmentSecondBinding
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_second.*
import okhttp3.*
import java.io.IOException

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    lateinit var MainAdapter: MainAdapter


    private var _binding: FragmentSecondBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerViewMain
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        fetchJson()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            Log.d("Nisse",MainActivity.foods.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun fetchJson(){
        val url = "https://api.edamam.com/api/recipes/v2?type=public&q=${input}&app_id=e5dfc7a6&app_key=96fa1fb0936391888172849d9db1dac6"
        //val request = Request.Builder().url(url).build()

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

                println("Failed to execute")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println("Failed to execute")
                println(body)
                val gson = GsonBuilder().create()

                var food = gson.fromJson(body, Food::class.java)
                MainAdapter = MainAdapter(food)
                MainActivity.setFoods(food)


                (activity as MainActivity).runOnUiThread{recyclerView.adapter= MainAdapter  }



            }


    })}






}