package com.manui.myapplication.ui.teamcontainer.players

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.manui.myapplication.databinding.FragmentPlayersBinding
import com.manui.myapplication.databinding.FragmentTeamBinding
import com.manui.myapplication.model.Match
import com.manui.myapplication.model.Player
import com.manui.myapplication.ui.teamcontainer.TeamContainerFragment
import com.manui.myapplication.ui.teamcontainer.TeamContainerFragmentDirections
import com.manui.myapplication.ui.teams.TeamAdapter
import com.manui.myapplication.ui.teams.TeamFragmentDirections
import com.manui.myapplication.ui.teams.TeamViewModel

class PlayersFragment : Fragment() {

    private lateinit var binding: FragmentPlayersBinding
    private val vm: PlayerViewModel by viewModels()

    lateinit var adapter: PlayerAdapter

    var idTeam: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayersBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = vm
            vm.playerCreate.postValue(Player())

            idTeam = requireArguments().getInt("idTeam")
            vm.loadPlayers(idTeam)

            adapter = PlayerAdapter()
            list.adapter = adapter
        }

        subscribeUI()
        onBackPressed()
        return binding.root
    }


    fun subscribeUI() {
        vm.teams.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.addPlayer.setOnClickListener {
            findNavController().navigate(TeamContainerFragmentDirections.actionTeamContainerFragmentToCreatePlayerFragment(idTeam))
        }
    }

    fun onBackPressed() {

    }
}