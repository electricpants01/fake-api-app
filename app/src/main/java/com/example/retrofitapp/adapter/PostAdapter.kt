package com.example.retrofitapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.Interfaces.IPost
import com.example.retrofitapp.R
import com.example.retrofitapp.databinding.FragmentPostVBinding
import com.example.retrofitapp.databinding.PostItemBinding
import com.example.retrofitapp.model.Post

class PostAdapter(val posts: List<Post.PostItem>, val postListener: IPost): RecyclerView.Adapter<PostAdapter.PostHolder>() {
    class PostHolder(view: View): RecyclerView.ViewHolder(view){
        val postItemBinding = PostItemBinding.bind(view)

        fun render(postItem: Post.PostItem){
            postItemBinding.tvPostId.text = "ID: ${postItem.id}"
            postItemBinding.tvPostTitle.text = "Title: ${postItem.title}"
        }
    }
      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
          return PostHolder(layoutInflater.inflate(R.layout.post_item,parent,false))
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val postItem = posts[position]
        holder.render(postItem)
        val cardView = holder.postItemBinding.cvPostVertical
        cardView.setOnClickListener {
            postItem.id?.let { it1 -> postListener.didSelectPost(it1) }
        }
    }

    override fun getItemCount(): Int = posts.size

}