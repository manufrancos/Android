package com.manui.myapplication.ui.teams.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.manui.myapplication.R
import com.manui.myapplication.databinding.FragmentTeamBinding
import com.manui.myapplication.databinding.FragmentTeamCreateBinding
import com.manui.myapplication.ui.teams.TeamAdapter
import com.manui.myapplication.ui.teams.TeamFragmentDirections
import com.manui.myapplication.ui.teams.TeamViewModel


class TeamCreateFragment : Fragment() {

    private lateinit var binding: FragmentTeamCreateBinding
    private val vm: TeamViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamCreateBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = vm

        }

        subscribeUI()
        onBackPressed()
        return binding.root
    }

    fun subscribeUI() {
        binding.addTeam.setOnClickListener {
            vm.createTeam()
        }
    }

    fun onBackPressed() {

    }
}