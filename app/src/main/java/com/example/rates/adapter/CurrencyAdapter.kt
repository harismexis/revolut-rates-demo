package com.example.rates.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rates.R
import com.example.rates.model.CurrencyModel
import com.example.rates.viewholder.BaseResponderViewHolder
import com.example.rates.viewholder.FirstResponderViewHolder
import com.example.rates.viewholder.ResponderViewHolder

class CurrencyAdapter(
    private var models: List<CurrencyModel>,
    private var responderListener: FirstResponderViewHolder.FirstResponderListener,
    private var itemClickListener: ResponderViewHolder.ItemClickListener
) : RecyclerView.Adapter<BaseResponderViewHolder>() {

    companion object {
        const val VIEW_TYPE_RESPONDER = 0
        const val VIEW_TYPE_CLIENT = 1
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseResponderViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.responder_item, parent, false)
        return when (viewType) {
            VIEW_TYPE_RESPONDER -> FirstResponderViewHolder(view, responderListener)
            else -> ResponderViewHolder(view, itemClickListener)
        }
    }

    override fun onBindViewHolder(
        viewHolder: BaseResponderViewHolder,
        position: Int
    ) {
        viewHolder.bind(models[position], position)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_RESPONDER
            else -> VIEW_TYPE_CLIENT
        }
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun getItemId(position: Int): Long {
        return models[position].currencyCode.id
    }

    override fun onViewDetachedFromWindow(holder: BaseResponderViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

    fun notifyDataChanged() {
        notifyItemRangeChanged(1, itemCount - 1)
    }
}