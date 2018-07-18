package com.arsan.submissionapp.ui.favoritespage

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arsan.submissionapp.R
import com.arsan.submissionapp.data.db.model.FavoritesTeam
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.favoritesteam_list.view.*
import kotlinx.android.synthetic.main.team_list.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class FavoritesTeamAdapter(private val context: Context,
                           private val teamList: List<FavoritesTeam>,
                           private val listener: (FavoritesTeam) -> Unit)
    : RecyclerView.Adapter<FavoritesTeamAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder =
            MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.favoritesteam_list, parent, false))

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindMatchItem(teamList[position], listener)
    }


    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindMatchItem(items: FavoritesTeam, listener: (FavoritesTeam) -> Unit) {
            Glide.with(itemView.context).load(items.teamBadge).into(itemView.image_fav_team)
            itemView.name_fav_team.text = items.teamName
            itemView.onClick { listener(items) }
        }
    }

}