package com.example.retrofitapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitapp.ServiceApi.APIService
import com.example.retrofitapp.ServiceApi.ServiceGenerator
import com.example.retrofitapp.adapter.PostCommentAdapter
import com.example.retrofitapp.adapter.PostHorizontalAdapter
import com.example.retrofitapp.databinding.FragmentPostDetailsBinding
import com.example.retrofitapp.model.Comment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostDetailsFragment : Fragment() {

    private var postId: Int? = null
    lateinit var fragmentPostDetailsBinding: FragmentPostDetailsBinding
    lateinit var postCommentAdapter: PostCommentAdapter
    var myComments = mutableListOf<Comment.CommentItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_post_details, container, false)
        fragmentPostDetailsBinding = FragmentPostDetailsBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariables()
        Log.d("chris","el post id presiona es ${postId}")
        initRecycler()
        postId?.let { getCommentsById(it) }
    }

    fun initVariables(){
        arguments?.let {
            postId = it.getInt(PostVFragment.POST_ID)
        }
    }

    fun initRecycler(){
        postCommentAdapter = PostCommentAdapter(myComments)
        val recycler = fragmentPostDetailsBinding.rvComments
        recycler.layoutManager = LinearLayoutManager(view?.context)
        recycler.adapter = postCommentAdapter
    }

    fun getCommentsById(postId: Int){
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("chris","el id es ${postId}")
            val call = ServiceGenerator.getRetrofit().create(APIService::class.java).getPostsById(postId)
            if ( call.isSuccessful){
                val response = call.body()
                response?.let {
                    withContext(Dispatchers.Main){
                        Log.d("chris","si entro al with context")
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