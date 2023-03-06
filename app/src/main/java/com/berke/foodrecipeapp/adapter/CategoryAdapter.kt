package com.berke.foodrecipeapp.adapter



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.berke.foodrecipeapp.R
import com.berke.foodrecipeapp.helper.downloadFromUrl
import com.berke.foodrecipeapp.helper.placHolderProgressBar
import com.berke.foodrecipeapp.model.Category
import kotlinx.android.synthetic.main.category_recycler_raw.view.*
import kotlinx.android.synthetic.main.food_recycler_raw.view.categoryName



class CategoryAdapter ( val categoryList : ArrayList<Category>
): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    var listener: OnItemClickListener? = null


    class CategoryViewHolder(var view:View): RecyclerView.ViewHolder(view) {
    }

    fun setClickListener(listener1: OnItemClickListener){
        listener =listener1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.category_recycler_raw,parent,false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int )  {


            holder.view.categoryName.text = categoryList[position].strCategory
            holder.view.categoryImage.downloadFromUrl(categoryList[position].strCategoryThumb, placHolderProgressBar(holder.view.context))

        holder.itemView.rootView.setOnClickListener{
            listener!!.onClicked(categoryList[position].strCategory)
        }


    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun updateDataCategory ( newCatedory: List<Category>){
        categoryList.clear()
        categoryList.addAll(newCatedory)
        notifyDataSetChanged()
    }


    interface OnItemClickListener {
        fun onClicked(categoriName:String)
    }

}