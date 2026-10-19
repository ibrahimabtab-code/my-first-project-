package com.pythonsikho.app

import android.app.Activity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.Engine
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.content.Intent
import java.util.Locale

class MainActivity : Activity(), TextToSpeech.OnInitListener {
    private lateinit var webView: WebView
    private var tts: TextToSpeech? = null
    private var ttsReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tts = TextToSpeech(this, this)

        webView = WebView(this)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.addJavascriptInterface(VoiceBridge(), "AndroidVoice")
        setContentView(webView)
        webView.loadUrl("file:///android_asset/index.html")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)
            ttsReady = result != TextToSpeech.LANG_MISSING_DATA &&
                       result != TextToSpeech.LANG_NOT_SUPPORTED
            runOnUiThread {
                webView.evaluateJavascript(
                    "window.setVoiceStatus(${if (ttsReady) "true" else "false"})", null
                )
            }
        }
    }

    inner class VoiceBridge {
        @JavascriptInterface
        fun speak(text: String) {
            runOnUiThread {
                if (ttsReady) {
                    tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "pythonsikho-lesson")
                }
            }
        }

        @JavascriptInterface
        fun stop() {
            runOnUiThread { tts?.stop() }
        }

        @JavascriptInterface
        fun openVoiceSettings() {
            runOnUiThread {
                try {
                    startActivity(Intent(Engine.ACTION_INSTALL_TTS_DATA))
                } catch (_: Exception) {
                    startActivity(Intent("com.android.settings.TTS_SETTINGS"))
                }
            }
        }
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        webView.destroy()
        super.onDestroy()
    }
}
