package com.arsan.submissionapp.ui.favoritespage

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arsan.submissionapp.R
import com.arsan.submissionapp.data.db.model.FavoritesMatch
import kotlinx.android.synthetic.main.favoritesmatch_list.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class FavoritesMatchAdapter(private val context: Context,
                            private val matchList: List<FavoritesMatch>,
                            private val listener: (FavoritesMatch) -> Unit)
    : RecyclerView.Adapter<FavoritesMatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder =
            MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.favoritesmatch_list, parent, false))

    override fun getItemCount(): Int = matchList.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindMatchItem(matchList[position], listener)
    }


    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindMatchItem(items: FavoritesMatch, listener: (FavoritesMatch) -> Unit) {
            itemView.event_date.text = items.eventDate
            itemView.home_team.text = items.homeTeam
            itemView.home_score.text = items.homeScore
            itemView.away_team.text = items.awayTeam
            itemView.away_score.text = items.awayScore
            itemView.onClick { listener(items) }
        }
    }

}