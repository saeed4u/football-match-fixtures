package com.saeed.android.scoreline.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.saeed.android.scoreline.R
import com.saeed.android.scoreline.ui.BaseFragment

class DashboardFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        (viewModel as DashboardViewModel).text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}
