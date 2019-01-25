package com.example.koheiando.reprosample

import android.app.Application
import android.util.Log
import io.repro.android.Repro
import org.xmlpull.v1.XmlPullParser
import java.lang.Exception

class ReproApplication : Application() {
    companion object {
        private const val REPRO_TOKEN_KEY = "repro_sdk_token"
    }

    override fun onCreate() {
        super.onCreate()

        try {
            setUpRepro()
        } catch (e: Exception) {
            Log.e("ReproApplication", "error onCreate", e)
        }
    }

    /**
     * for Repro setting
     * if reproToken() returns null, it won't be activated
     */
    private fun setUpRepro() {
        reproToken()?.let {
            Repro.disableInAppMessageOnActive() // this let the app trigger the dialog arbitrarily
            Repro.setup(this, it)
            Repro.startRecording()
        } ?: Log.w("ReproApplication", "Repro token is null. Repro could not be setup.")
    }

    /**
     * if the token is not fetched, returns null
     * @return { String? }
     */
    private fun reproToken(): String? {
        resources.getXml(R.xml.repro_sdk_token).let { parser ->
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        Log.d(
                            "ReproApplication",
                            "reproToken name ${parser.name}, name attr ${parser.getAttributeValue(
                                null,
                                "name"
                            )}, text ${parser.text}"
                        )
                        if (parser.getAttributeValue(null, "name") == REPRO_TOKEN_KEY) {
                            if (parser.next() == XmlPullParser.TEXT) {
                                Log.d(
                                    "ReproApplication",
                                    "reproToken name ${parser.name}, name attr ${parser.getAttributeValue(
                                        null,
                                        "name"
                                    )}, text ${parser.text}"
                                )
                                return parser.text
                            }
                        }
                    }
                }
                eventType = parser.next()
            }
            return null
        }
    }
}