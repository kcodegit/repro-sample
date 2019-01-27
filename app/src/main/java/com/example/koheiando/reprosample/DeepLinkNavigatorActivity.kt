package com.example.koheiando.reprosample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.koheiando.reprosample.DeepLinkTargetActivity.Companion.EXTRA_TEXT

/**
 * called by Repro
 * does not have UI, just start the right activity requested by Repro
 */
class DeepLinkNavigatorActivity : AppCompatActivity() {
    companion object {
        private const val DL_TARGET_PATH = "target"
        private const val QUERY_TEXT = "text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.data?.let {
            when (it.lastPathSegment) {
                DL_TARGET_PATH -> openTarget(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        finish()
    }

    private fun openTarget(uri: Uri) {
        uri.getQueryParameter(QUERY_TEXT)?.let {
            startActivity(Intent(this, DeepLinkTargetActivity::class.java).apply {
                putExtra(EXTRA_TEXT, it)
            })
        }
    }
}