package com.example.datastoreexample

import android.os.Bundle
import android.service.autofill.Validators.and
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_data_store_prefence.*
import kotlinx.android.synthetic.main.activity_data_store_prefence.view.*
import kotlinx.coroutines.launch
class PreferenceDataStoreActivity : AppCompatActivity() {
    private lateinit var bookmarkPreferences: BookmarkPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_store_prefence)
        init()
        subscribe()
    }

    private fun subscribe() {
        bookmarkPreferences.bookmark.asLiveData().observe(this, Observer {
            savedText.text = it
        })
    }

    private fun init() {
        bookmarkPreferences = BookmarkPreferences(this)
        saveBtn.setOnClickListener {
            val bookmark =userText.text.toString()
            lifecycleScope.launch {
                bookmarkPreferences.saveBookmark(bookmark)
            }
        }
    }

}
