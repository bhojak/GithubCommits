package com.bhupen.dailynews.features.details

import android.os.Bundle
import com.bhupen.dailynews.R
import com.bhupen.dailynews.shared.base.BaseActivity
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity :  BaseActivity<DetailsPresenter>(), DetailsView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        post_title.text = intent.getStringExtra("title")
        post_body.text = intent.getStringExtra("body")


    }

    override fun instantiatePresenter(): DetailsPresenter {
        return DetailsPresenter(this)
    }
}
