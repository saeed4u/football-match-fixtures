package com.saeed.android.scoreline.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
/**
 * Created by Saeed on 2019-07-19.
 */
abstract class BaseViewHolder(private val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{


    interface Delegate{
        fun onItemClick(item: Any)
    }

    init {
        view.setOnClickListener(this)
    }

    fun view(): View{
        return view
    }

    abstract fun bindData(data: Any)
}