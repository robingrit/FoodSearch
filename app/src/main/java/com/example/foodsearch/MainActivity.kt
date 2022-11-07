package com.example.foodsearch

import android.Manifest
import android.app.Activity

import android.content.Intent

import android.content.pm.PackageManager
import android.os.Bundle

import android.speech.RecognizerIntent
import android.text.Editable
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.example.foodsearch.Model.Food
import com.example.foodsearch.databinding.ActivityMainBinding

import kotlinx.android.synthetic.main.fragment_first.*

import java.util.*

 class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val REQUEST_CODE_SPEECH_INPUT = 100
    val RecordAudioRequestCode : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)



        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "robin.nilsson@gritacademy.se", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)!=
            PackageManager.PERMISSION_GRANTED){
            checkPermission()

        }

    }

        companion object Json{


            var input : String = "taco"

            var foods : Food? = null

            @JvmName("setFoods1")
            fun setFoods(f : Food){


                foods = f


            }


        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onSaveInstanceState(outState: Bundle) {
        // Save the user's current game state
        outState?.run {

            putString("Key_value", input)


        }

        // Always call the superclass so it can save the view hierarchy state
        if (outState != null) {
            super.onSaveInstanceState(outState)
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }




     fun speak(){
        val mIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something")
        try {

            startActivityIfNeeded(mIntent, REQUEST_CODE_SPEECH_INPUT)
            Log.d("testar", "StartActivity")

        }
        catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            Log.d("testar", e.message.toString())

        }

    }
    private fun checkPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO)
            ,RecordAudioRequestCode
        )
    }





    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_CODE_SPEECH_INPUT ->{

                if (resultCode== Activity.RESULT_OK && null != data) {

                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

                    input = result?.get(0).toString()
                    textInput.text = Editable.Factory.getInstance().newEditable(input)
                    textInputLayout.setHint("")

                    //fetchJson()

                    
                }


            }

        }
    }



}