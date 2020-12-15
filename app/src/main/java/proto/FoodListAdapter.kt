package proto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.datastoreexample.R

class FoodListAdapter : ListAdapter<Food, FoodListAdapter.FoodItemViewHolder>(DIFF_UTIL) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        return FoodItemViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FoodItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(food: Food) {
            itemView.findViewById<TextView>(R.id.textFoodName).text = food.name
            itemView.findViewById<TextView>(R.id.textFoodType).run {
                text = food.type.name
                setTextColor(
                        ContextCompat.getColor(
                                itemView.context, when (food.type) {
                            FoodType.NON_VEG -> android.R.color.holo_red_dark
                            FoodType.VEG -> android.R.color.holo_green_dark
                        }
                        )
                )
            }

            itemView.findViewById<TextView>(R.id.textFoodTaste).run {
                text = food.taste.name
                setTextColor(
                        ContextCompat.getColor(
                                itemView.context, when (food.taste) {
                            FoodTaste.SWEET -> android.R.color.holo_blue_light
                            FoodTaste.SPICY -> android.R.color.holo_orange_dark
                        }
                        )
                )
            }
        }
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(oldItem: Food, newItem: Food) =
                    oldItem == newItem

            override fun areContentsTheSame(oldItem: Food, newItem: Food) =
                    oldItem.name == newItem.name
        }
    }
}
