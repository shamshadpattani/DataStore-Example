package com.example.datastoreexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ProtoDataStoreActivity : AppCompatActivity() {
    private lateinit var bookmarkPreferences: BookmarkPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_store_porto)
        init()
        subscribe()
    }

    private fun subscribe() {
        TODO("Not yet implemented")
    }

    private fun init() {
        TODO("Not yet implemented")
    }
}

