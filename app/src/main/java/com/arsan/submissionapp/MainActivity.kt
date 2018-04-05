package com.arsan.submissionapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.arsan.submissionapp.R.array.res_club_image
import com.arsan.submissionapp.R.array.res_club_name
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var items : MutableList<ItemClub> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        rv_club_list.layoutManager = LinearLayoutManager(this)
        rv_club_list.adapter = RvClubAdapter(this, items,
                {itemClub: ItemClub -> clubItemClicked(itemClub)  })
    }

    private fun clubItemClicked(clubItem : ItemClub){
        Toast.makeText(this, "Clicked: ${clubItem.clubName}", Toast.LENGTH_SHORT).show()
    }

    private fun initData() {
        val clubName = resources.getStringArray(res_club_name)
        val clubLogo = resources.obtainTypedArray(res_club_image)

        items.clear()
        for (i in clubName.indices){
            items.add(ItemClub(clubName[i], clubLogo.getResourceId(i,0)))
        }

        clubLogo.recycle()
    }
}
