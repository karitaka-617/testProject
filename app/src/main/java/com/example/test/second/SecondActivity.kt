package com.example.test.second

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.test.R

class SecondActivity : AppCompatActivity(),GitItemFragment.OnFragmentInteractionListener, FragmentSecond.FragmentSecondListener {

    private var fragmentSecond = FragmentSecond.newInstance()
    private var gitItemFragment = GitItemFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragmentSecond)
                .commitNow()
        }
    }

    override fun onBack() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragmentSecond)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onBack()
    }

    override fun onStartDetail(name: String, projectName: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, GitItemFragment.newInstance(name,projectName))
            .commit()
    }
}
