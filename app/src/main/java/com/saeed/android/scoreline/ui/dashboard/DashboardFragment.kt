package com.saeed.android.scoreline.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.saeed.android.scoreline.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : DaggerFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_teams.setOnClickListener{
            findNavController().navigate(DashboardFragmentDirections.actionNavigationDashboardToNavigationMatches())
        }
    }
}
