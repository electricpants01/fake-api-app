package com.example.retrofitapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.Interfaces.IPost
import com.example.retrofitapp.R
import com.example.retrofitapp.databinding.PostHItemBinding
import com.example.retrofitapp.model.Post

class PostHorizontalAdapter(val posts: List<Post.PostItem>, val postListener: IPost): RecyclerView.Adapter<PostHorizontalAdapter.PostHorizontalHolder>() {
    class PostHorizontalHolder(view: View): RecyclerView.ViewHolder(view) {
        val postHItemBinding = PostHItemBinding.bind(view)
        fun render(postItem: Post.PostItem){
            postHItemBinding.tvPostHorizontalId.text = "ID: ${postItem.id}"
            postHItemBinding.tvPostHorizontalTitle.text = "Title: ${postItem.title}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHorizontalAdapter.PostHorizontalHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PostHorizontalHolder(layoutInflater.inflate(R.layout.post_h_item,parent,false))
    }

    override fun onBindViewHolder(holder: PostHorizontalAdapter.PostHorizontalHolder, position: Int) {
        val post = posts[position]
        holder.render(post)
        val cardView = holder.postHItemBinding.cvPostHorizontal
        cardView.setOnClickListener {
            post.id?.let { it1 -> postListener.didSelectPost(it1) }
        }
    }

    override fun getItemCount(): Int = posts.size

}