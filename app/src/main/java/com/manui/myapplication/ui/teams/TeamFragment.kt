package com.manui.myapplication.ui.teams

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.manui.myapplication.R
import com.manui.myapplication.databinding.FragmentTeamBinding


class TeamFragment : Fragment() {

    private lateinit var binding: FragmentTeamBinding
    private val vm: TeamViewModel by viewModels()

    lateinit var teamAdapter: TeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = vm
            vm.loadTeams()

            teamAdapter = TeamAdapter()
            list.adapter = teamAdapter
        }

        subscribeUI()
        onBackPressed()
        return binding.root
    }

    fun subscribeUI() {
        vm.teams.observe(viewLifecycleOwner, Observer {
            teamAdapter.submitList(it)
        })
    }

    fun onBackPressed() {

    }
}