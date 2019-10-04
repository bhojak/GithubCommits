package com.bhupen.dailynews.features.post

/**
 * Created by Bhupen
 */

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bhupen.dailynews.R
import com.bhupen.dailynews.dataType.model.Git_commit
import com.bhupen.dailynews.shared.utils.dateConversion
import com.bumptech.glide.Glide


/**
 * Adapter for the list of the posts
 * @property context Context in which the application is running
 */
class PostAdapter(private val context: Context) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    /**
     * The list of posts of the adapter
     */

    private var gitCommit: List<Git_commit> = listOf()

    /**
     * Updates the list of posts of the adapter
     * @param posts the new list of posts of the adapter
     */
    fun updatePosts(posts: List<Git_commit>) {
        this.gitCommit = posts
        notifyDataSetChanged()
    }

    override fun getItemCount()= gitCommit!!.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.item_commit,
                        parent,
                        false
                )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val gitCommit = gitCommit!!.get(position);

        if (gitCommit.author.avatar_url != null) {
            Glide.with(context!!)
                    .load(gitCommit.author.avatar_url)
                    .into(holder.ivThumbnail);
        }
        if (gitCommit.commit.message != null) {
            holder.tvTitle.setText(gitCommit.commit.message);
        }

        if (gitCommit.commit.author.name != null) {
            holder.tvName.setText(gitCommit.commit.author.name);
        }

        if (gitCommit.commit.author.date != null) {
            holder.tvDate.setText(dateConversion(gitCommit.commit.author.date));
        }


    }

    /**
     * The ViewHolder of the adapter
     */


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val ivThumbnail: ImageView = itemView.findViewById(R.id.ivThumbnail);
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle);
        val tvName: TextView = itemView.findViewById(R.id.tvName);
        val tvDate: TextView = itemView.findViewById(R.id.tvDate);
    }
}