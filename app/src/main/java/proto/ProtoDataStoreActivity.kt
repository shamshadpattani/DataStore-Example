package proto

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.datastoreexample.R
import kotlinx.android.synthetic.main.activity_data_store_porto.*
import kotlinx.coroutines.launch

class ProtoDataStoreActivity : AppCompatActivity() {

    private lateinit var foodPreferenceManager: FoodPreferenceManager
    private val foodListAdapter by lazy { FoodListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_store_porto)


        foodPreferenceManager = FoodPreferenceManager(applicationContext)
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
        var filteredList = getFoodList()
        type?.let { foodType ->
            filteredList = filteredList.filter { it.type == foodType }
        }
        taste?.let { foodTaste ->
            filteredList = filteredList.filter { it.taste == foodTaste }
        }

        foodListAdapter.submitList(filteredList)

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No results!", Toast.LENGTH_SHORT).show()
        }

        updateViews(type, taste)
    }

    private fun updateViews(type: FoodType?, taste: FoodTaste?) {
        when (type) {
            FoodType.VEG -> veg.isChecked = true
            FoodType.NON_VEG -> nonVeg.isChecked = true
        }

        when (taste) {
            FoodTaste.SWEET -> sweet.isChecked = true
            FoodTaste.SPICY -> spicy.isChecked = true
        }
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

            lifecycleScope.launch { foodPreferenceManager.updateUserFoodTastePreference(taste) }
        }
        foodType.setOnCheckedChangeListener { group, checkedId ->
            val type = when (checkedId) {
                R.id.veg -> FoodType.VEG
                R.id.nonVeg -> FoodType.NON_VEG
                else -> null
            }

            lifecycleScope.launch { foodPreferenceManager.updateUserFoodTypePreference(type) }
        }
    }

    private fun loadData() {
        foodListAdapter.submitList(getFoodList())
    }

}

