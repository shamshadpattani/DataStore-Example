package com.example.datastoreexample

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookmarkPreferences(context: Context) {

    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences>

    companion object {
        val KEY_BOOKMARK = preferencesKey<String>("user_text")
    }
    init {
        dataStore = applicationContext.createDataStore(name = "user_bookmark_preferences")
    }
 
    val bookmark: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_BOOKMARK]
        }
 
    suspend fun saveBookmark(bookmark: String) {
        dataStore.edit { preferences ->
            preferences[KEY_BOOKMARK] = bookmark
        }
    }
 
 

}