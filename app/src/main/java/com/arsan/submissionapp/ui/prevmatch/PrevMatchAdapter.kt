package com.arsan.submissionapp.ui.prevmatch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arsan.submissionapp.R
import com.arsan.submissionapp.data.network.model.Match
import kotlinx.android.synthetic.main.prevmatch_list.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class PrevMatchAdapter(private val context: Context,
                       private val matchList: List<Match>,
                       private val listener: (Match) -> Unit)
    : RecyclerView.Adapter<PrevMatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder =
            MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.prevmatch_list, parent, false))

    override fun getItemCount(): Int = matchList.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindMatchItem(matchList[position], listener)
    }


    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindMatchItem(items: Match, listener: (Match) -> Unit) {
            itemView.event_date.text = items.dateEvent
            itemView.home_team.text = items.homeTeam
            itemView.home_score.text = items.homeScore
            itemView.away_team.text = items.awayTeam
            itemView.away_score.text = items.awayScore
            itemView.onClick { listener(items) }
        }
    }

}