package com.example.rates.viewholder

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.rates.model.CurrencyModel

class FirstResponderViewHolder(
    view: View,
    private var changeListener: FirstResponderListener
) : BaseResponderViewHolder(view), TextWatcher {

    interface FirstResponderListener {
        fun beforeResponderTextChanged(text: String)
        fun onResponderTextChanged(text: String)
        fun afterResponderTextChanged(text: String)
    }

    override fun bind(
        item: CurrencyModel?,
        position: Int
    ) {
        super.bind(item, position)
        val currentBase = item?.baseAmount.toString()
        etxtAmount.visibility = View.VISIBLE
        if (etxtAmount.text.toString() != currentBase) {
            etxtAmount.setText(item?.baseAmount.toString())
        }
        etxtAmount.addTextChangedListener(this)
    }

    override fun unbind() {
        // Not implemented
    }

    override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
        if (!etxtAmount.hasFocus()) return
        changeListener.beforeResponderTextChanged(text.toString())
    }

    override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
        if (!etxtAmount.hasFocus()) return
        changeListener.onResponderTextChanged(text.toString())
    }

    override fun afterTextChanged(text: Editable?) {
        if (!etxtAmount.hasFocus()) return
        changeListener.afterResponderTextChanged(text.toString())
    }

}