package com.github.minimalistic.presentation.core.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nullgr.core.adapter.DynamicAdapter
import com.nullgr.core.adapter.bindTo
import com.github.minimalistic.presentation.R
import com.github.minimalistic.presentation.core.pm.BaseListPm
import javax.inject.Inject

abstract class BaseListActivity<T : BaseListPm> : BaseActivity<T>() {

    @Inject
    lateinit var adapter: DynamicAdapter

    protected var itemsView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        itemsView = findViewById(R.id.itemsView)
        itemsView?.layoutManager = provideLayoutManager(this)
        itemsView?.adapter = adapter
    }

    override fun onBindPresentationModel(pm: T) {
        super.onBindPresentationModel(pm)
        pm.items.observable.bindTo(adapter, compositeUnbind)
    }

    protected open fun provideLayoutManager(context: Context?): RecyclerView.LayoutManager =
        LinearLayoutManager(context)
}