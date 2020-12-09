package com.example.datastoreexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        val buttonPreference:Button=findViewById(R.id.buttonPreference)
        buttonPreference.setOnClickListener {
            startActivity(Intent(this@MainActivity, PreferenceDataStoreActivity::class.java))
        }

        /*buttonProto.setOnClickListener {
            startActivity(Intent(this@MainActivity, ProtoDatastoreActivity::class.java))
        }*/
    }
}
