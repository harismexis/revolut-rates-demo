package com.harismexis.rates.viewholder

import android.text.method.ScrollingMovementMethod
import android.view.View
import com.harismexis.rates.model.CurrencyModel
import com.harismexis.rates.model.amountAsString
import com.harismexis.rates.util.playAnimation

class ResponderViewHolder(
    view: View,
    private var itemClickListener: ItemClickListener?
) : BaseResponderViewHolder(view) {

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun bind(
        item: CurrencyModel?,
        position: Int
    ) {
        super.bind(item, position)
        txtAmount.visibility = View.VISIBLE
        txtAmount.text = item.amountAsString()
        txtAmount.movementMethod = ScrollingMovementMethod()
        txtAmount.setHorizontallyScrolling(true)
        itemView.setOnClickListener {
            item?.let {
                playAnimation(clickIndicator) { itemClickListener?.onItemClick(position) }
            }
        }
    }

    override fun unbind() {
        // Not implemented
    }
}