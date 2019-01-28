package com.example.koheiando.reprosample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class DeepLinkTargetTWOActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TEXT = "text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deeplink_two)
        findViewById<TextView>(R.id.tv_to_replace).text = intent.getStringExtra(EXTRA_TEXT) ?: "text not passed"
    }
}