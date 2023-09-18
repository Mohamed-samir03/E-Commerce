package com.mosamir.e_commerce.shopping.presentation

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mosamir.e_commerce.R
import com.mosamir.e_commerce.shopping.domain.model.favourite.DataX
import com.mosamir.e_commerce.shopping.domain.model.favourite.Product
import com.mosamir.e_commerce.util.SessionManager.hide
import com.mosamir.e_commerce.util.SessionManager.show

class FavouriteAdapter(val context: Context, val productList: ArrayList<DataX>): RecyclerView.Adapter<FavouriteAdapter.ProductViewHolder>() {

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
        holder.bind(product.product)
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProductName = itemView.findViewById<TextView>(R.id.product_name)
        val tvPrice = itemView.findViewById<TextView>(R.id.product_price)
        val tvOldPrice = itemView.findViewById<TextView>(R.id.product_oldPrice)
        val imgProduct = itemView.findViewById<ImageView>(R.id.product_image)
        val tvDiscount = itemView.findViewById<TextView>(R.id.product_offer)
        val imgIsFavourite = itemView.findViewById<ImageView>(R.id.product_favourite)

        fun bind(product: Product){

            tvProductName.text = product.name
            tvDiscount.text = "${product.discount}%"
            Glide.with(context).load(product.image).centerCrop().placeholder(R.drawable.logo).into(imgProduct)
            imgIsFavourite.setImageResource(R.drawable.favorite_icon)

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

        }
    }

}