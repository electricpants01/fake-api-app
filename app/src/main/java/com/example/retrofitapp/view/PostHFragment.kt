package com.example.retrofitapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitapp.Interfaces.IPost
import com.example.retrofitapp.R
import com.example.retrofitapp.ServiceApi.APIService
import com.example.retrofitapp.ServiceApi.ServiceGenerator
import com.example.retrofitapp.adapter.PostCommentAdapter
import com.example.retrofitapp.adapter.PostHorizontalAdapter
import com.example.retrofitapp.databinding.FragmentPostHBinding
import com.example.retrofitapp.model.Comment
import com.example.retrofitapp.model.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostHFragment : Fragment(), IPost {

    lateinit var fragmentPostHBinding: FragmentPostHBinding
    lateinit var postHorizontalAdapter: PostHorizontalAdapter
    lateinit var postCommentAdapter: PostCommentAdapter
    var myPosts = mutableListOf<Post.PostItem>()
    var myComments = mutableListOf<Comment.CommentItem>( Comment.CommentItem("body cuerpo",
            "chris@gmail.com",12,"christian",100
    ))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_post_h, container, false)
        fragmentPostHBinding = FragmentPostHBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initCommentsRecycler()
        getAllPosts()
    }

    fun initRecycler() {
        postHorizontalAdapter = PostHorizontalAdapter(myPosts,this)
        val recycler = fragmentPostHBinding.rvPostHorizontal
        // asi es como seteamos horizontalmente
        recycler.layoutManager = LinearLayoutManager(view?.context,LinearLayoutManager.HORIZONTAL,false)
        recycler.adapter = postHorizontalAdapter
    }

    fun initCommentsRecycler(){
        postCommentAdapter = PostCommentAdapter(myComments)
        val commentsRecycler = fragmentPostHBinding.rvHorizontalComments
        commentsRecycler.layoutManager = LinearLayoutManager(view?.context)
        commentsRecycler.adapter = postCommentAdapter
    }


    fun getAllPosts(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = ServiceGenerator.getRetrofit().create(APIService::class.java).getPosts()
            if ( call.isSuccessful){
                val response = call.body()
                response?.let {
                    withContext(Dispatchers.Main){
                        // añadir posts
                        myPosts.clear()
                        myPosts.addAll(it)
                        // reload data
                        postHorizontalAdapter.notifyDataSetChanged()
                    }
                }
            }else{
                Log.d("chris","horizontal getall posts failed")
            }
        }
    }
    override fun didSelectPost(postId: Int) {
        getCommentsById(postId)
    }

    fun getCommentsById(postId: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val call = ServiceGenerator.getRetrofit().create(APIService::class.java).getPostsById(postId)
            if ( call.isSuccessful){
                val response = call.body()
                response?.let {
                    withContext(Dispatchers.Main){
                        // añadir posts
                        myComments.clear()
                        myComments.addAll(it)
                        // reload data
                        postCommentAdapter.notifyDataSetChanged()
                    }
                }
            }else{
                Log.d("chris","horizontal detail posts failed")
            }
        }
    }
}