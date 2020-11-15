package com.example.rates.viewholder

import android.view.View
import com.example.rates.model.RateItem
import com.example.rates.model.getAmount

class ResponderViewHolder(
    view: View,
    private var itemClickListener: ItemClickListener?
) : BaseResponderViewHolder(view) {

    interface ItemClickListener {
        fun onItemClick(item: RateItem, position: Int)
    }

    override fun bind(
        item: RateItem?,
        position: Int
    ) {
        super.bind(item, position)
        etxtAmount.setText(item.getAmount().toString())
        etxtAmount.isEnabled = false
        itemView.setOnClickListener {
            item?.let {
                itemClickListener?.onItemClick(it, position)
            }
        }
    }

}