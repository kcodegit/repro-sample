package com.example.koheiando.reprosample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.koheiando.reprosample.DeepLinkTargetTWOActivity.Companion.EXTRA_TEXT

/**
 * called by Repro
 * does not have UI, just start the right activity requested by Repro
 */
class DeepLinkNavigatorActivity : AppCompatActivity() {
    companion object {
        private val TAG = DeepLinkNavigatorActivity::class.java.simpleName
        private const val DL_TARGET_ONE_PATH = "targetONE"
        private const val DL_TARGET_TWO_PATH = "targetTWO"
        private const val QUERY_TEXT = "text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.data?.let {
            when (it.lastPathSegment) {
                DL_TARGET_ONE_PATH -> toTargetONE()
                DL_TARGET_TWO_PATH -> toTargetTWO(it)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        finish()
    }

    private fun toTargetONE() {
        startActivity(Intent(this, DeepLinkTargetONEActivity::class.java))
    }

    /**
     * if the query param is not passed, it won't do anything
     */
    private fun toTargetTWO(uri: Uri) {
        uri.getQueryParameter(QUERY_TEXT)?.let {
            startActivity(Intent(this, DeepLinkTargetTWOActivity::class.java).apply {
                putExtra(EXTRA_TEXT, it)
            })
        } ?: Log.w(
            TAG,
            "target ${DeepLinkTargetTWOActivity::class.java.simpleName}, uri $uri, query param is not passed. not starting the activity"
        )
    }
}