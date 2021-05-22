package com.example.retrofitapp.model

import com.google.gson.annotations.SerializedName

class Post : ArrayList<Post.PostItem>(){
    data class PostItem(
        @SerializedName("body") val body: String?,
        @SerializedName("id") val id: Int?,
        @SerializedName("title") val title: String?,
        @SerializedName("userId") val userId: Int?
    )
}