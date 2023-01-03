package com.upuphone.testlandscapemode

import android.app.ActivityOptions
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentDisplayId = display?.displayId ?: 0
        findViewById<Button>(R.id.btnStartWindowService).setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                AddAlertWindowService.startService(this@MainActivity, currentDisplayId)
            }
        })
        findViewById<Button>(R.id.btnStartTiktok).setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                Intent().apply {
                    // putExtra("id", displayId)
                    component = ComponentName("com.ss.android.ugc.aweme", "com.ss.android.ugc.aweme.main.MainActivity")
                    val options = ActivityOptions.makeBasic()
                    // options.launchDisplayId = displayId
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                    startActivity(this, options.toBundle())
                }
            }
        })
    }
}