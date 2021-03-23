package com.harismexis.rates.viewholder

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
import com.harismexis.rates.R
import com.harismexis.rates.extensions.setTextOrUnknown
import com.harismexis.rates.model.CurrencyModel
import com.harismexis.rates.model.getFlag

abstract class BaseResponderViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private var iconFlag: ImageView = itemView.findViewById(R.id.iconFlag)
    private var txtKey: TextView = itemView.findViewById(R.id.txtKey)
    private var txtName: TextView = itemView.findViewById(R.id.txtName)
    protected var etxtAmount: EditText = itemView.findViewById(R.id.etxtAmount)
    protected var txtAmount: TextView = itemView.findViewById(R.id.txtAmount)
    protected var clickIndicator: View = itemView.findViewById(R.id.clickIndicator)

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

        txtKey.setTextOrUnknown(item?.currencyCode?.key)
        txtName.setTextOrUnknown(item?.currencyCode?.description)
    }

    abstract fun unbind()
}