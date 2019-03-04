package com.example.test.main

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test.R
import com.example.test.second.SecondActivity

class MainActivity : AppCompatActivity(),MainFragment.OnFragmentInteractionListener{

    private val mainFragment = MainFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, mainFragment)
                .commitNow()
        }
    }

    fun onStartSecond() {
        val intent = Intent(this,SecondActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onFragmentInteraction() {

    }
}
