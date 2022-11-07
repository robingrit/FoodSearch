package com.example.foodsearch

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodsearch.Model.Food

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_row.view.*
import kotlinx.android.synthetic.main.fragment_second.view.*

class MainAdapter(val food: Food) : RecyclerView.Adapter<CustomViewHolder>(){


    //number of items
    override fun getItemCount(): Int {
        return food.hits.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater =LayoutInflater.from(parent.context)
        val cellForTableRow = layoutInflater.inflate(R.layout.food_row, parent, false)
        return CustomViewHolder(cellForTableRow)

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val hits = food.hits.get(position)
        val pictureurl = hits.recipe.images.REGULAR.url
        val ingredients = hits.recipe.ingredientLines


        holder?.veiw?.textView?.text = hits.recipe.label
        holder?.veiw?.textView2?.text = hits.recipe.healthLabels.toString()
        holder?.veiw?.textView4?.text = ingredients.toString()

        val thumbnailImageView = holder.veiw.imageView
        Picasso.get().load(pictureurl).into(thumbnailImageView)


    }
}
class CustomViewHolder (val veiw : View): RecyclerView.ViewHolder(veiw){

}