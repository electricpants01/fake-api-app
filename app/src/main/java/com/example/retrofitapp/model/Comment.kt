package com.example.retrofitapp.model

import com.google.gson.annotations.SerializedName

class Comment : ArrayList<Comment.CommentItem>(){
    data class CommentItem(
        @SerializedName("body") val body: String?,
        @SerializedName("email") val email: String?,
        @SerializedName("id") val id: Int?,
        @SerializedName("name") val name: String?,
        @SerializedName("postId") val postId: Int?
    )
}