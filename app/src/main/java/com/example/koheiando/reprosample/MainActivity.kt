package com.example.koheiando.reprosample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.koheiando.reprosample.repro.ReproEvent
import com.example.koheiando.reprosample.repro.ReproTrigger

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            ReproTrigger.getInstance().send(1, 100, ReproEvent.CAMPAIGN_A)
        }, 200)
    }
}
