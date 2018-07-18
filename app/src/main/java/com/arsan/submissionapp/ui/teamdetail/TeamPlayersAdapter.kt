package com.arsan.submissionapp.ui.teamdetail

import android.content.Context
import com.arsan.submissionapp.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arsan.submissionapp.data.network.model.Player
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.player_list.view.*
import kotlinx.android.synthetic.main.player_list.view.image_player
import org.jetbrains.anko.sdk25.coroutines.onClick

class TeamPlayersAdapter(private val context: Context,
                         private val playerList: List<Player>,
                         private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<TeamPlayersAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder =
            PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.player_list, parent, false))

    override fun getItemCount(): Int=playerList.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindPlayerItem(playerList[position], listener)
    }

    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bindPlayerItem(items:Player, listener: (Player) -> Unit){
            itemView.name_player.text = items.player
            itemView.playing_position.text = items.position
            Glide.with(itemView.context).load(items.playerPhoto).into(itemView.image_player)
            itemView.onClick { listener(items) }
        }
    }
}