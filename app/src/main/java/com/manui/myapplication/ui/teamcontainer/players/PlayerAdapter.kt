package com.manui.myapplication.ui.teamcontainer.players

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manui.myapplication.databinding.ItemPlayersBinding
import com.manui.myapplication.databinding.ItemTeamBinding
import com.manui.myapplication.model.Player
import com.manui.myapplication.model.Team

class PlayerAdapter() : ListAdapter<Player, PlayerAdapter.ItemsViewHolder>(PlayerDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemsViewHolder(
            ItemPlayersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(h: ItemsViewHolder, p: Int) = h.bind(getItem(p))


    inner class ItemsViewHolder(val binding: ItemPlayersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
        }

        fun bind(item: Player) {
            binding.setClickListener {
                //binding.root.findNavController().navigate(TeamFragmentDirections.actionTeamFragmentToPlayersFragment(item.idTeam!!))
            }
            binding.item = item
            binding.executePendingBindings()
        }
    }

    class PlayerDiffUtil : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player) =
            oldItem.idPlayer == newItem.idPlayer

        override fun areContentsTheSame(oldItem: Player, newItem: Player) =
            oldItem == newItem
    }
}