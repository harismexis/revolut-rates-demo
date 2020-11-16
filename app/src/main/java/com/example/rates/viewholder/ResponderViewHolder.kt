package com.example.rates.viewholder

import android.view.View
import com.example.rates.model.CurrencyModel
import com.example.rates.model.getAmount
import com.example.rates.util.playAnimation

class ResponderViewHolder(
    view: View,
    private var itemClickListener: ItemClickListener?
) : BaseResponderViewHolder(view) {

    interface ItemClickListener {
        fun onItemClick(item: CurrencyModel, position: Int)
    }

    override fun bind(
        item: CurrencyModel?,
        position: Int
    ) {
        super.bind(item, position)
        txtAmount.visibility = View.VISIBLE
        txtAmount.text = item.getAmount().toString()
        itemView.setOnClickListener {
            item?.let {
                playAnimation(clickIndicator) { itemClickListener?.onItemClick(item, position) }
            }
        }
    }

    override fun unbind() {
        // Not implemented
    }
}