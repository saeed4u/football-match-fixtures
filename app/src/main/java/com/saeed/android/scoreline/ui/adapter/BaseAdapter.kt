package com.saeed.android.scoreline.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import com.saeed.android.scoreline.ui.viewholder.BaseViewHolder

/**
 * Created by Saeed on 2019-07-19.
 */
abstract class BaseAdapter : RecyclerView.Adapter<BaseViewHolder>(), LifecycleObserver {

    private val data = arrayListOf<Any>()

    fun addData(data: List<Any>) {
        this.data.addAll(data)
    }

    fun addOne(data: Any) {
        this.data.add(data)
    }

    fun getData(): ArrayList<Any> {
        return this.data
    }

    private fun clearData() {
        this.data.clear()
    }

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    protected abstract fun createViewHolder(view: View): BaseViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return createViewHolder(inflateView(parent))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val data = this.data[position]
        holder.bindData(data)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    private fun inflateView(viewGroup: ViewGroup): View {
        return LayoutInflater.from(viewGroup.context).inflate(getLayoutRes(), viewGroup, false)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public fun onDestroyed() {
        this.clearData()
    }
}