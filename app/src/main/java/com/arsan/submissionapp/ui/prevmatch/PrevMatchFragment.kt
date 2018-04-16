package com.arsan.submissionapp.ui.prevmatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.arsan.submissionapp.R
import com.arsan.submissionapp.data.network.ApiRepository
import com.arsan.submissionapp.data.network.model.Match
import com.arsan.submissionapp.util.invisible
import com.arsan.submissionapp.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_prev_match.*
import org.jetbrains.anko.support.v4.onRefresh


/**
 * A simple [Fragment] subclass.
 *
 */
class PrevMatchFragment : Fragment(), PrevMatchView {

    private var prevMatch: MutableList<Match> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var prevMatchAdapter: PrevMatchAdapter
    private lateinit var prevMatchPresenter: PrevMatchPresenter
    private val leagueId: String? = "4328"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipe_refresh.isRefreshing = false
        progressBar = this.progress_bar


        prevMatchAdapter = PrevMatchAdapter(requireContext(), prevMatch)
        rv_schedule.adapter = prevMatchAdapter
        rv_schedule.layoutManager = LinearLayoutManager(requireContext())

        val apiRequest = ApiRepository()
        val gson = Gson()
        prevMatchPresenter = PrevMatchPresenter(this, apiRequest, gson)
        prevMatchPresenter.getPrevMatch(leagueId)

        swipe_refresh.onRefresh {
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
        swipe_refresh.isRefreshing = false
        prevMatch.clear()
        prevMatch.addAll(data)
        prevMatchAdapter.notifyDataSetChanged()
    }

}
