package com.bhupen.dailynews.features.post

/**
 * Created by Bhupen
 */
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.bhupen.dailynews.dataType.model.Git_commit
import com.bhupen.dailynews.databinding.ActivityPostBinding
import com.bhupen.dailynews.features.details.DetailsActivity
import com.bhupen.dailynews.shared.base.BaseActivity

/**
 * Activity displaying the list of posts
 */
class PostActivity : BaseActivity<PostPresenter>(), PostView {
    /**
     * DataBinding instance
     */
    private lateinit var binding: ActivityPostBinding

    /**
     * The adapter for the list of posts
     */
    private val postsAdapter = PostAdapter(this)
    private var postList: List<Git_commit> = listOf()

    interface ClickListener {
        fun onClick(view: View, position: Int)
        fun onLongClick(view: View, position: Int)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, com.bhupen.dailynews.R.layout.activity_post)
        binding.adapter = postsAdapter
        binding.layoutManager = LinearLayoutManager(this)
        binding.dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)

        presenter.onViewCreated()


        binding.posts.addOnItemTouchListener(RecyclerTouchListener(this,
                binding.posts, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                // Launch second activity
                openNewViewScreen(position)

            }

            override fun onLongClick(view: View, position: Int) {
                Toast.makeText(this@PostActivity, "Long press on position : $position",
                        Toast.LENGTH_LONG).show()
            }
        }))


    }

    private fun openNewViewScreen(position: Int) {
        val showDetailActivityIntent = Intent(this, DetailsActivity::class.java)
        showDetailActivityIntent.putExtra("title", postList[position].node_id)
        showDetailActivityIntent.putExtra("body",  postList[position].comments_url)
        startActivity(showDetailActivityIntent)
    }




    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun updateCommits(posts: List<Git_commit>) {
        postsAdapter.updatePosts(posts)
        postList = posts
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun instantiatePresenter(): PostPresenter {
        return PostPresenter(this)
    }

    internal inner class RecyclerTouchListener(context: Context, recycleView: RecyclerView,
                                               private val clicklistener: ClickListener?) :
                                                            RecyclerView.OnItemTouchListener {
        private val gestureDetector: GestureDetector

        init {
            gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val child = recycleView.findChildViewUnder(e.x, e.y)
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child))
                    }
                }
            })
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child))
            }

            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    }
}