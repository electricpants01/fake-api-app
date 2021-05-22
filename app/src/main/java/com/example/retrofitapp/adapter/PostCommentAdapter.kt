package com.example.retrofitapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.Interfaces.IPost
import com.example.retrofitapp.R
import com.example.retrofitapp.databinding.PostItemBinding
import com.example.retrofitapp.model.Comment

class PostCommentAdapter(val comments: List<Comment.CommentItem>): RecyclerView.Adapter<PostCommentAdapter.CommentHolder>() {
    class CommentHolder(view: View): RecyclerView.ViewHolder(view) {
        val postItemBinding = PostItemBinding.bind(view)

        fun render(comment: Comment.CommentItem){
            postItemBinding.tvPostId.text = comment.email
            postItemBinding.tvPostTitle.text = comment.body
        }
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): PostCommentAdapter.CommentHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return CommentHolder(layoutInflater.inflate(R.layout.post_item,parent,false))
    }

    override fun onBindViewHolder(holder: PostCommentAdapter.CommentHolder, position: Int) {
        val comment = comments[position]
        holder.render(comment)
    }

    override fun getItemCount(): Int = comments.size
}