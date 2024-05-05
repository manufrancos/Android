package com.manui.myapplication.ui.teamcontainer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.manui.myapplication.R
import com.manui.myapplication.databinding.FragmentPlayersBinding
import com.manui.myapplication.databinding.FragmentTeamContainerBinding



class TeamContainerFragment : Fragment() {

    private lateinit var binding: FragmentTeamContainerBinding
    private val args: TeamContainerFragmentArgs by navArgs()

    val tabs = arrayOf(
        "Jugadores",
        "Partidos"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamContainerBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner

            val adapter = ViewPagerAdapter(getParentFragmentManager(), lifecycle,args.idTeam)
            viewPager.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabs[position]
            }.attach()

        }


        return binding.root}


}