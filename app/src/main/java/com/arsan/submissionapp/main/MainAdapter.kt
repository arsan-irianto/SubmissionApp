package com.arsan.submissionapp.main

import android.content.ClipData
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arsan.submissionapp.R
import com.arsan.submissionapp.model.Match
import kotlinx.android.synthetic.main.item_list.view.*

class MainAdapter(private val context: Context,
                  private val matchList: List<Match>)
    : RecyclerView.Adapter<MainAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder =
            MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list,parent,false))

    override fun getItemCount(): Int = matchList.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindMatchItem(matchList[position])
    }


    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindMatchItem(items : Match){
            itemView.event_date.text = items.dateEvent
            itemView.home_team.text = items.homeTeam
            itemView.home_score.text = items.homeScore
            itemView.away_team.text = items.awayTeam
            itemView.away_score.text = items.awayScore
        }
    }

}