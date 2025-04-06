package com.android.mr.worldt2.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mr.worldt2.data.Team
import com.android.mr.worldt2.databinding.ItemTeamBinding
import com.bumptech.glide.Glide

class TeamAdapter(
    private val teams: List<Team>,
    private val onSelectionChanged: (List<Team>) -> Unit
) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    private val selectedTeams = mutableListOf<Team>()

    inner class TeamViewHolder(private val binding: ItemTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(team: Team) {
            binding.teamNameText.text = team.name
            Glide.with(binding.flagImage.context)
                .load(team.flag)
                .into(binding.flagImage)

            binding.root.setBackgroundColor(
                if (selectedTeams.contains(team)) Color.LTGRAY else Color.WHITE
            )

            binding.root.setOnClickListener {
                toggleSelection(team)
                notifyItemChanged(adapterPosition)
                onSelectionChanged(selectedTeams)
            }
        }

        private fun toggleSelection(team: Team) {
            if (selectedTeams.contains(team)) {
                selectedTeams.remove(team)
            } else if (selectedTeams.size < 2) {
                selectedTeams.add(team)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = ItemTeamBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teams[position])
    }

    override fun getItemCount(): Int = teams.size

    fun getSelectedTeams(): List<Team> = selectedTeams.toList()
}

