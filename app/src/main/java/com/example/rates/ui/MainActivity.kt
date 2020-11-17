package com.example.rates.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rates.R
import com.example.rates.adapter.CurrencyAdapter
import com.example.rates.model.CurrencyModel
import com.example.rates.util.NetworkConnectionListener
import com.example.rates.viewholder.FirstResponderViewHolder
import com.example.rates.viewholder.ResponderViewHolder
import com.example.rates.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    ResponderViewHolder.ItemClickListener,
    FirstResponderViewHolder.FirstResponderListener,
    NetworkConnectionListener.NetworkConnectionClient {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel
    private var currencies: MutableList<CurrencyModel> = mutableListOf()
    private lateinit var adapter: CurrencyAdapter
    private var networkListener: NetworkConnectionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        initRecycler()
        networkListener = NetworkConnectionListener(this)
        observeLiveData()
    }

    override fun onResume() {
        super.onResume()
        networkListener?.startListen()
    }

    override fun onPause() {
        super.onPause()
        networkListener?.stopListen()
        viewModel.stopRateUpdate()
    }

    override fun onDestroy() {
        super.onDestroy()
        networkListener = null
        viewModel.stopRateUpdate()
    }

    override fun onItemClick(
        item: CurrencyModel,
        position: Int
    ) {
        viewModel.onFirstResponderChange(item)
    }

    override fun beforeResponderTextChanged(text: String) {
        viewModel.stopRateUpdate()
    }

    override fun onResponderTextChanged(text: String) {
        viewModel.updateBaseAmount(text)
        adapter.notifyDataChanged()
    }

    override fun afterResponderTextChanged(text: String) {
        viewModel.updateBaseAmount(text)
        viewModel.startRateUpdate()
    }

    private fun initRecycler() {
        enableAnimationOnRecycler(false)
        currencies.addAll(viewModel.getInitialUiModels())
        adapter = CurrencyAdapter(currencies, this, this)
        adapter.setHasStableIds(true)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }

    private fun observeLiveData() {
        viewModel.rates.observe(this, { uiModels ->
            uiModels?.let {
                if (it.isNotEmpty()) {
                    updateRecycler(it)
                }
            }
        })
    }

    private fun updateRecycler(list: List<CurrencyModel>) {
        currencies.clear()
        currencies.addAll(list)
        adapter.notifyDataChanged()
    }

    private fun enableAnimationOnRecycler(enable: Boolean) {
        (recycler.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = enable
    }

    override fun onNetworkConnectionAvailable() {
        viewModel.startRateUpdate()
    }

    override fun onNetworkConnectionLost() {
        viewModel.stopRateUpdate()
    }

}