package com.arsan.submissionapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            imageView(intent.getIntExtra("CLUB_IMAGE",0)).
                    lparams {
                        margin = dip(20)
                        width = dip(70)
                        height = dip(70)
                    }
            textView(intent.getStringExtra("CLUB_NAME")).lparams{
                bottomMargin = dip(10)
                setGravity(1)

            }
            textView(intent.getStringExtra("CLUB_DESCRIPTION")).lparams{
                leftMargin = dip(10)
                rightMargin = dip(10)
            }
        }
    }
}