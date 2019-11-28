package com.github.minimalistic.presentation.core.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nullgr.core.adapter.DynamicAdapter
import com.nullgr.core.adapter.bindTo
import com.nullgr.core.ui.extensions.toggleView
import kotlinx.android.synthetic.main.layout_toolbar.*
import com.github.minimalistic.presentation.R
import com.github.minimalistic.presentation.core.pm.BaseListPm
import javax.inject.Inject

abstract class BaseListFragment<T : BaseListPm> : BaseFragment<T>() {

    @Inject
    lateinit var adapter: DynamicAdapter

    protected var itemsView: RecyclerView? = null
    protected var toolbarView: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemsView = view.findViewById(R.id.itemsView)
        toolbarView = view.findViewById(R.id.toolbarView)
        itemsView?.layoutManager = provideLayoutManager(activity)
        itemsView?.adapter = adapter
        itemsView?.let {
            it.viewTreeObserver.addOnScrollChangedListener {
                val canScroll = it.canScrollVertically(RecyclerView.NO_POSITION)
                dividerView?.let { divider ->
                    if (divider.visibility != View.INVISIBLE) divider.toggleView(!canScroll)
                }
                toolbarView?.isSelected = canScroll
            }
        }
    }

    override fun onBindPresentationModel(pm: T) {
        super.onBindPresentationModel(pm)
        pm.items.observable.bindTo(adapter, compositeUnbind)
    }

    protected open fun provideLayoutManager(context: Context?): RecyclerView.LayoutManager =
        LinearLayoutManager(context)
}