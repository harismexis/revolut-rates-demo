package com.example.rates.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rates.R
import com.example.rates.adapter.RateAdapter
import com.example.rates.model.RateItem
import com.example.rates.viewholder.FirstResponderViewHolder
import com.example.rates.viewholder.ResponderViewHolder
import com.example.rates.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), ResponderViewHolder.ItemClickListener,
    FirstResponderViewHolder.FirstResponderListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    private var rates: MutableList<RateItem> = mutableListOf()
    private lateinit var adapter: RateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        initRecycler()
        observeLiveData()
    }

    override fun onResume() {
        super.onResume()
        viewModel.startRateUpdate()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopRateUpdate()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stopRateUpdate()
    }

    override fun onItemClick(
        item: RateItem,
        position: Int
    ) {
        viewModel.stopRateUpdate()
        viewModel.setBaseCurrency(item.currencyCode.key)
        viewModel.startRateUpdate()
    }

    override fun beforeResponderTextChanged(text: String) {
        viewModel.stopRateUpdate()
    }

    override fun onResponderTextChanged(text: String) {
        val amount = viewModel.filterResponderText(text)
        viewModel.setBaseAmount(amount.toFloat())
        adapter.notifyClientsChanged()
    }

    override fun afterResponderTextChanged(text: String) {
        val amount = viewModel.filterResponderText(text)
        viewModel.setBaseAmount(amount.toFloat())
        viewModel.startRateUpdate()
    }

    private fun initRecycler() {
        enableAnimationOnRecycler(false)
        adapter = RateAdapter(rates, this, this)
        adapter.setHasStableIds(true)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }

    private fun observeLiveData() {
        viewModel.rates.observe(this, { uiModels ->
            uiModels?.let {
                updateRecycler(it)
            }
        })
    }

    private fun updateRecycler(list: List<RateItem>) {
        rates.clear()
        rates.addAll(list)
        adapter.notifyClientsChanged()
    }

    private fun enableAnimationOnRecycler(enable: Boolean) {
        (recycler.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = enable
    }

}