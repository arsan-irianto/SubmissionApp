package com.arsan.submissionapp.ui.teamspage

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arsan.submissionapp.R
import kotlinx.android.synthetic.main.team_list.view.*
import com.arsan.submissionapp.data.network.model.Team
import com.bumptech.glide.Glide
import org.jetbrains.anko.sdk25.coroutines.onClick

class TeamsPageAdapter(private val context: Context,
                       private val teamList: List<Team>,
                       private val listener: (Team) -> Unit)
    : RecyclerView.Adapter<TeamsPageAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder =
            TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.team_list, parent, false))

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindTeamItem(teamList[position], listener)
    }


    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindTeamItem(items: Team, listener: (Team) -> Unit) {
            Glide.with(itemView.context).load(items.teamBadge).into(itemView.image_team)
            itemView.name_team.text = items.teamName
            itemView.onClick { listener(items) }
        }
    }

}