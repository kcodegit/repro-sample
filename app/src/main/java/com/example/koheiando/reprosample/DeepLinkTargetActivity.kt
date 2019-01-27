package com.example.koheiando.reprosample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class DeepLinkTargetActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TEXT = "text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.text_view).text = intent.getStringExtra(EXTRA_TEXT) ?: "text not passed"
    }
}