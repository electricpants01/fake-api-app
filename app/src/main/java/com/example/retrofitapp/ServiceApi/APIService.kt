package com.example.retrofitapp.ServiceApi

import com.example.retrofitapp.model.Comment
import com.example.retrofitapp.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface APIService {
    @GET("/comments")
    suspend fun getPostsById(@Query("postId") postId: Int): Response<List<Comment.CommentItem>>

    @GET("/posts")
    suspend fun getPosts(): Response<List<Post.PostItem>>
}