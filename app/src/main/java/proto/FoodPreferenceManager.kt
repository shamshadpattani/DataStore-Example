package proto

import android.content.Context
import android.util.Log
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.createDataStore
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.example.datastoreexample.FoodPreferences
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


class FoodPreferenceManager(context: Context) {

    companion object {
        const val TAG = "FoodPreferenceManager"
    }
    /**
     * create DB
     */
    private val dataStore: DataStore<FoodPreferences> =
        context.createDataStore(
            fileName = "food_prefs.pb",
            serializer = FoodPreferenceSerializer
        )

    /**
     * Catches exceptions in the flow completion and calls a specified action with the caught exception.
     * using {dataStore.data.catch} in coroutines
     */
    val userFoodPreference = dataStore.data.catch {
        if (it is IOException) {
            Log.e(TAG, "Error reading sort order preferences.", it)
            emit(FoodPreferences.getDefaultInstance())
        } else {
            throw it
        }
    }.map {
        val type = when (it.type) {
            FoodPreferences.FoodType.TYPE_VEG -> FoodType.VEG
            FoodPreferences.FoodType.TYPE_NON_VEG -> FoodType.NON_VEG
            else -> null
        }
        val taste = when (it.taste) {
            FoodPreferences.FoodTaste.TASTE_SWEET -> FoodTaste.SWEET
            FoodPreferences.FoodTaste.TASTE_SPICY -> FoodTaste.SPICY
            else -> null
        }

        UserFoodPreference(type, taste)

    }

    object FoodPreferenceSerializer : Serializer<FoodPreferences> {
        override val defaultValue: FoodPreferences
            get() = TODO("Not yet implemented")

        override fun readFrom(input: InputStream): FoodPreferences {
            try {
                return FoodPreferences.parseFrom(input)
            } catch (exception: InvalidProtocolBufferException) {
                throw CorruptionException("Cannot read proto.", exception)
            }
        }

        override fun writeTo(t: FoodPreferences, output: OutputStream) {
            t.writeTo(output)
        }
    }
}
 data class  UserFoodPreference(
       val type: FoodType?,
       val taste: FoodTaste?
      )
