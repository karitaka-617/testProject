package com.example.test.second

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.test.R

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FragmentSecond.newInstance())
                .commitNow()
        }
    }
}
