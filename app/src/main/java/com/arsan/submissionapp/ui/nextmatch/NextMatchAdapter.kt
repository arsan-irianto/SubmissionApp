package com.arsan.submissionapp.ui.prevmatch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arsan.submissionapp.R
import com.arsan.submissionapp.data.network.model.Match
import kotlinx.android.synthetic.main.prevmatch_list.view.*

class NextMatchAdapter(private val context: Context,
                       private val matchList: List<Match>)
    : RecyclerView.Adapter<NextMatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder =
            MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.nextmatch_list, parent, false))

    override fun getItemCount(): Int = matchList.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindMatchItem(matchList[position])
    }


    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindMatchItem(items: Match) {
            itemView.event_date.text = items.dateEvent
            itemView.home_team.text = items.homeTeam
            itemView.away_team.text = items.awayTeam
        }
    }

}