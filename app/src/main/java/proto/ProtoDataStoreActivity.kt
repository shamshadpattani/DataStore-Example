package proto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.datastoreexample.R
import kotlinx.android.synthetic.main.activity_data_store_porto.*

class ProtoDataStoreActivity : AppCompatActivity() {

    private lateinit var foodPreferenceManager: FoodPreferenceManager
    private val foodListAdapter by lazy { FoodListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_store_porto)

         observePreferences()
            initAdapter()
         initViews()
        loadData()
    }

    private fun observePreferences() {
        foodPreferenceManager.userFoodPreference.asLiveData().observe(this) {
            filterFoodList(it.type, it.taste)
        }
    }

    private fun filterFoodList(type: FoodType?, taste: FoodTaste?) {

    }


    private fun initAdapter() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ProtoDataStoreActivity)
            adapter = foodListAdapter
        }
    }


    private fun initViews() {
        foodTaste.setOnCheckedChangeListener { group, checkedId ->
            val taste = when (checkedId) {
                R.id.sweet -> FoodTaste.SWEET
                R.id.spicy -> FoodTaste.SPICY
                else -> null
            }
        }
    }

            /*lifecycleScope.launch { foodPreferenceManager.updateUserFoodTastePreference(taste) }
        }

        foodType.setOnCheckedChangeListener { group, checkedId ->
            val type = when (checkedId) {
                R.id.veg -> FoodType.VEG
                R.id.nonVeg -> FoodType.NON_VEG
                else -> null
            }

            lifecycleScope.launch { foodPreferenceManager.updateUserFoodTypePreference(type) }
        }*/

    private fun loadData() {
        foodListAdapter.submitList(getFoodList())
    }

}

