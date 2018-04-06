package com.arsan.submissionapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list.view.*

class RvClubAdapter(private val context: Context,
                    private val items : List<ItemClub>,
                    val listener : (ItemClub) -> Unit)
    : RecyclerView.Adapter<RvClubAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bindItem(items: ItemClub, listener: (ItemClub) -> Unit) {
            itemView.club_name.text = items.clubName
            Glide.with(itemView.context).load(items.clubLogo).into(itemView.club_logo)
            itemView.setOnClickListener { listener(items) }
        }
    }
}