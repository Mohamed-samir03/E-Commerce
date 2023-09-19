package com.mosamir.e_commerce.shopping.presentation

import android.content.Context
import android.content.res.Resources
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mosamir.e_commerce.R
import com.mosamir.e_commerce.shopping.domain.model.DataX
import com.mosamir.e_commerce.util.SessionManager.hide
import com.mosamir.e_commerce.util.SessionManager.show

class ProductAdapter(val context: Context, val productList: ArrayList<DataX>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var favoriteClickListener: OnFavoriteClickListener? = null

    fun setOnFavoriteClickListener(listener: OnFavoriteClickListener) {
        favoriteClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProductName = itemView.findViewById<TextView>(R.id.product_name)
        val tvPrice = itemView.findViewById<TextView>(R.id.product_price)
        val tvOldPrice = itemView.findViewById<TextView>(R.id.product_oldPrice)
        val imgProduct = itemView.findViewById<ImageView>(R.id.product_image)
        val tvDiscount = itemView.findViewById<TextView>(R.id.product_offer)
        val imgIsFavourite = itemView.findViewById<ImageView>(R.id.product_favourite)
        var isFavorite = false


        fun bind(product:DataX){

            tvProductName.text = product.name
            tvDiscount.text = "${product.discount}%"
            Glide.with(context).load(product.image).centerCrop().placeholder(R.drawable.logo).into(imgProduct)

            if (product.in_favorites){
                imgIsFavourite.setImageResource(R.drawable.favorite_icon)
            }else{
                imgIsFavourite.setImageResource(R.drawable.unfavorite_icon)
            }

            if (product.discount != 0){
                tvDiscount.show()
                tvOldPrice.show()
                tvPrice.text = product.price.toString()+"$"
                tvOldPrice.setText(Html.fromHtml("<strike>${product.old_price}$</strike>"), TextView.BufferType.SPANNABLE)
                tvOldPrice.paintFlags = tvOldPrice.paintFlags
            }else{
                tvDiscount.hide()
                tvPrice.text = product.price.toString()+"$"
                tvOldPrice.hide()
            }

            imgIsFavourite.setOnClickListener {
                // Perform the action when imgIsFavourite is clicked
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val product = productList[position]

                    // Update the UI based on the new state
                    if (!product.in_favorites) {
                        isFavorite = !isFavorite
                        if (isFavorite) {
                            imgIsFavourite.setImageResource(R.drawable.favorite_icon)
                        } else {
                            imgIsFavourite.setImageResource(R.drawable.unfavorite_icon)
                        }
                    } else {
                        if (isFavorite) {
                            imgIsFavourite.setImageResource(R.drawable.favorite_icon)
                        } else {
                            imgIsFavourite.setImageResource(R.drawable.unfavorite_icon)
                        }
                        isFavorite = !isFavorite
                    }
                    favoriteClickListener?.onFavoriteClick(product, adapterPosition)

                }
            }
        }
    }
    interface OnFavoriteClickListener {
        fun onFavoriteClick(product: DataX, position: Int)

    }
}


