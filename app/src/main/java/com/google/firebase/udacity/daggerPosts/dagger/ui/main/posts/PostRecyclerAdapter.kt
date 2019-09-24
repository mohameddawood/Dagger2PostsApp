package com.google.firebase.udacity.daggerPosts.dagger.ui.main.posts

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.udacity.daggerPosts.R
import com.google.firebase.udacity.daggerPosts.dagger.models.Post
import java.util.*


class PostRecyclerAdapter : RecyclerView.Adapter<ViewHolder>() {
    private var posts: List<Post> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(
                R.layout.layout_post_list_item, parent, false
        )
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = posts.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as PostViewHolder).bind(posts[position])
    }

    fun setPosts(posts: List<Post?>?) {
        this.posts = posts as List<Post>
        notifyDataSetChanged()
    }


    class PostViewHolder(itemView: View) : ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        fun bind(post: Post) {
            title.text = post.title
        }

    }

}