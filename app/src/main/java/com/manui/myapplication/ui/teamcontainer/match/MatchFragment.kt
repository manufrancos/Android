package com.manui.myapplication.ui.teamcontainer.match

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.manui.myapplication.databinding.FragmentMatchBinding
import com.manui.myapplication.ui.teamcontainer.TeamContainerFragmentDirections

class MatchFragment : Fragment() {

    private lateinit var binding: FragmentMatchBinding
    private val vm: MatchViewModel by viewModels()

    lateinit var adapter: MatchAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = vm

            vm.loadMatchs(requireArguments().getInt("idTeam"))

            adapter = MatchAdapter()
            list.adapter = adapter
        }

        subscribeUI()
        onBackPressed()
        return binding.root
    }


    fun subscribeUI() {
        vm.matchs.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.addMatch.setOnClickListener {
            findNavController().navigate(TeamContainerFragmentDirections.actionTeamContainerFragmentToCreateMatchFragment())
        }
    }

    fun onBackPressed() {

    }
}