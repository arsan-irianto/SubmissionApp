package com.arsan.submissionapp.ui.prevmatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.arsan.submissionapp.R
import com.arsan.submissionapp.R.id.swipe_refresh_prev
import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.model.Match
import com.arsan.submissionapp.data.network.model.SpinnerItem
import com.arsan.submissionapp.ui.matchdetail.MatchDetailActivity
import com.arsan.submissionapp.util.invisible
import com.arsan.submissionapp.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_prev_match.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 *
 */
class PrevMatchFragment : Fragment(), PrevMatchView {

    private var prevMatch: MutableList<Match> = mutableListOf()
    private var spinnerItems: MutableList<SpinnerItem> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var prevMatchAdapter: PrevMatchAdapter
    private lateinit var prevMatchPresenter: PrevMatchPresenter
    private val leagueId: String? = "4328"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        swipe_refresh_prev.isRefreshing = false
        progressBar = this.progress_bar_prev


        prevMatchAdapter = PrevMatchAdapter(requireContext(), prevMatch) {
            startActivity<MatchDetailActivity>("id" to "${it.idEvent}",
                    "homeTeam" to "${it.idHomeTeam}",
                    "awayTeam" to "${it.idAwayTeam}")
        }
        this.rv_schedule_prev.adapter = prevMatchAdapter
        this.rv_schedule_prev.layoutManager = LinearLayoutManager(requireContext())

        val apiRequest = ApiRepository()
        val gson = Gson()
        prevMatchPresenter = PrevMatchPresenter(this, apiRequest, gson)
        prevMatchPresenter.getPrevMatch(leagueId)

        spinner = this.spinner_league
        spinnerItems.add(SpinnerItem("4328", "English Premier League"))
        spinnerItems.add(SpinnerItem("4331", "German Bundesliga"))
        spinnerItems.add(SpinnerItem("4332", "Italian Serie A"))
        spinnerItems.add(SpinnerItem("4334", "French Ligue 1"))
        spinnerItems.add(SpinnerItem("4335", "Spanish La Liga"))
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //val item : SpinnerItem = parent?.selectedItem as SpinnerItem
                val idleague = spinnerItems[position].leagueCode
                prevMatchPresenter.getPrevMatch(idleague.toString())
            }

        }

        swipe_refresh_prev.onRefresh {
            prevMatchPresenter.getPrevMatch(leagueId)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prev_match, container, false)

    }

    companion object {
        fun newInstance(): PrevMatchFragment = PrevMatchFragment()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        swipe_refresh_prev.isRefreshing = false
        prevMatch.clear()
        prevMatch.addAll(data)
        prevMatchAdapter.notifyDataSetChanged()
    }

}
