package com.example.rates.viewholder

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.rates.R
import com.example.rates.extensions.setTextOrUnknown
import com.example.rates.model.CurrencyModel
import com.example.rates.model.getFlag


open class BaseResponderViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private var iconFlag: ImageView = itemView.findViewById(R.id.iconFlag)
    private var txtKey: TextView = itemView.findViewById(R.id.txtKey)
    private var txtName: TextView = itemView.findViewById(R.id.txtName)
    protected var etxtAmount: EditText = itemView.findViewById(R.id.etxtAmount)

    open fun bind(
        item: CurrencyModel?,
        position: Int
    ) {
        Glide.with(itemView.context)
            .asBitmap()
            .load(item.getFlag())
            .error(item.getFlag())
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .dontAnimate()
            .skipMemoryCache(true)
            .placeholder(item.getFlag())
            .into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    iconFlag.setImageBitmap(resource)
                }
                override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
            })

//        Glide
//            .with(itemView.context)
//            .asBitmap()
//            .load(item.getFlag())
//            .diskCacheStrategy(DiskCacheStrategy.NONE)
//            .skipMemoryCache(true)
//            .dontAnimate()
//            .into(object : SimpleTarget<Bitmap?>() {
//                override fun onResourceReady(
//                    resource: Bitmap,
//                    transition: Transition<in Bitmap?>?
//                ) {
//                    iconFlag.setImageBitmap(resource)
//                }
//            })

        // iconFlag.setImageDrawable(ContextCompat.getDrawable(itemView.context, item.getFlag()))
        txtKey.setTextOrUnknown(item?.currencyCode?.key)
        txtName.setTextOrUnknown(item?.currencyCode?.description)
    }
}