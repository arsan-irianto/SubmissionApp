package com.arsan.submissionapp.ui.nextmatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.arsan.submissionapp.R
import com.arsan.submissionapp.R.id.swipe_refresh_next
import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.model.Match
import com.arsan.submissionapp.data.network.model.SpinnerItem
import com.arsan.submissionapp.ui.matchdetail.MatchDetailActivity
import com.arsan.submissionapp.util.invisible
import com.arsan.submissionapp.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import kotlin.math.log


/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment(), NextMatchView {

    private var nextMatch: MutableList<Match> = mutableListOf()
    private var spinnerItems: MutableList<SpinnerItem> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var nextMatchAdapter: NextMatchAdapter
    private lateinit var nextMatchPresenter: NextMatchPresenter
    private var leagueId: String? = "4328"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipe_refresh_next.isRefreshing = false
        progressBar = this.progress_bar_next


        nextMatchAdapter = NextMatchAdapter(requireContext(), nextMatch) {
            startActivity<MatchDetailActivity>("id" to "${it.idEvent}",
                    "homeTeam" to "${it.idHomeTeam}",
                    "awayTeam" to "${it.idAwayTeam}")
        }
        this.rv_schedule_next.adapter = nextMatchAdapter
        this.rv_schedule_next.layoutManager = LinearLayoutManager(requireContext())

        val apiRequest = ApiRepository()
        val gson = Gson()
        nextMatchPresenter = NextMatchPresenter(this, apiRequest, gson)
        nextMatchPresenter.getNextMatch(leagueId)

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
                nextMatchPresenter.getNextMatch(idleague.toString())
            }

        }

        swipe_refresh_next.onRefresh {
            nextMatchPresenter.getNextMatch(leagueId)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    companion object {
        fun newInstance(): NextMatchFragment = NextMatchFragment()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        swipe_refresh_next.isRefreshing = false
        nextMatch.clear()
        nextMatch.addAll(data)
        nextMatchAdapter.notifyDataSetChanged()
    }


}
