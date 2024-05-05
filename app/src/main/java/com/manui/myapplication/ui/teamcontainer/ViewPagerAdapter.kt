package com.manui.myapplication.ui.teamcontainer

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.gson.Gson
import com.manui.myapplication.ui.teamcontainer.match.MatchFragment
import com.manui.myapplication.ui.teamcontainer.players.PlayersFragment


private const val NUM_TABS = 2

public class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, val idTeam: Int) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {

        val bundle = Bundle()

        bundle.putInt("idTeam",idTeam)


        when (position) {
            0 -> {
                val p = PlayersFragment()
                p.arguments = bundle
                return p
            }
        }
        val m = MatchFragment()
        m.arguments = bundle
        return m
    }
}