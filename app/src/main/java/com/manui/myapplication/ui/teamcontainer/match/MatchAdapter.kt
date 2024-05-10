package com.manui.myapplication.ui.teamcontainer.match

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manui.myapplication.databinding.ItemMathBinding
import com.manui.myapplication.databinding.ItemPlayersBinding
import com.manui.myapplication.model.Match
import com.manui.myapplication.model.Player

class MatchAdapter() : ListAdapter<Match, MatchAdapter.ItemsViewHolder>(MatchDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemsViewHolder(
            ItemMathBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(h: ItemsViewHolder, p: Int) = h.bind(getItem(p))


    inner class ItemsViewHolder(val binding: ItemMathBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
        }

        fun bind(item: Match) {
            binding.setClickListener {
                //binding.root.findNavController().navigate(TeamFragmentDirections.actionTeamFragmentToPlayersFragment(item.idTeam!!))
            }
            binding.item = item
            binding.executePendingBindings()
        }
    }

    class MatchDiffUtil : DiffUtil.ItemCallback<Match>() {
        override fun areItemsTheSame(oldItem: Match, newItem: Match) =
            oldItem.idMatch == newItem.idMatch

        override fun areContentsTheSame(oldItem: Match, newItem: Match) =
            oldItem == newItem
    }
}