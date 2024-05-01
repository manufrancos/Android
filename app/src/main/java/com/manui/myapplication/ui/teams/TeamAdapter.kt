package com.manui.myapplication.ui.teams

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manui.myapplication.databinding.ItemTeamBinding
import com.manui.myapplication.model.Team

class TeamAdapter  () : ListAdapter<Team, TeamAdapter.ItemsViewHolder>(TeamDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemsViewHolder(
            ItemTeamBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(h: ItemsViewHolder, p: Int) = h.bind(getItem(p))


    inner class ItemsViewHolder(val binding: ItemTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
        }

        fun bind(item: Team) {
            binding.setClickListener {
            }
            binding.item = item
            binding.executePendingBindings()
        }
    }

    class TeamDiffUtil : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team) =
            oldItem.idTeam == newItem.idTeam

        override fun areContentsTheSame(oldItem: Team, newItem: Team) =
            oldItem == newItem
    }
}