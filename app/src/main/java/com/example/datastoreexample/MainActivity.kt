package com.example.datastoreexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import proto.ProtoDataStoreActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        buttonPreference.setOnClickListener {
            startActivity(Intent(this@MainActivity, PreferenceDataStoreActivity::class.java))
        }

       buttonProto.setOnClickListener {
            startActivity(Intent(this@MainActivity, ProtoDataStoreActivity::class.java))
        }
    }
}
