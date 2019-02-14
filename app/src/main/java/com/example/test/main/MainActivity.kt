package com.example.test.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test.R
import com.example.test.second.SecondActivity

class MainActivity : AppCompatActivity(){

    private val mMainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("jetProcess","StartStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("jetProcess","StartResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("jetProcess","StartPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("jetProcess","StartStop")
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun onStartSecond() {
        val intent = Intent(this,SecondActivity::class.java)
        startActivity(intent)
    }
}
