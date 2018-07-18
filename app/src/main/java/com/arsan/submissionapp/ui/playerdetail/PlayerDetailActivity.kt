package com.arsan.submissionapp.ui.playerdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ProgressBar
import com.arsan.submissionapp.R
import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.model.Player
import com.arsan.submissionapp.util.invisible
import com.arsan.submissionapp.util.visible
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity(), PlayerDetailView {

    private lateinit var playerDetailPresenter: PlayerDetailPresenter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        progressBar = this.progress_bar_player

        val intent = intent
        val id = intent.getStringExtra("id")
        val playerName = intent.getStringExtra("player")

        supportActionBar?.title = playerName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val apiRequest = ApiRepository()
        val gson = Gson()
        playerDetailPresenter = PlayerDetailPresenter(this, apiRequest, gson)
        playerDetailPresenter.getPlayerProfile(id)

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showProfile(data: List<Player>) {
        Glide.with(this).load(data[0].fanArt).into(player_fanart)
        text_weight.text = data[0].weight
        text_height.text = data[0].height
        text_position.text = data[0].position
        text_playerdesc.text = data[0].playerDescr
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
