package com.itGamePart.supervictoringame

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.activity_web_view.*
import java.util.prefs.Preferences
import java.util.prefs.PreferencesFactory

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        var  mSettings = getSharedPreferences("Prefer", MODE_PRIVATE)
        webView.loadUrl(mSettings.getString("wv",""))
    }
}


