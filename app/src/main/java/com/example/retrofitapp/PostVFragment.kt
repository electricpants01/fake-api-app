package com.example.retrofitapp

import android.app.Service
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.Interfaces.IPost
import com.example.retrofitapp.ServiceApi.APIService
import com.example.retrofitapp.ServiceApi.ServiceGenerator
import com.example.retrofitapp.adapter.PostAdapter
import com.example.retrofitapp.databinding.FragmentPostVBinding
import com.example.retrofitapp.model.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostVFragment : Fragment(), IPost {

    lateinit var postVBinding: FragmentPostVBinding
    lateinit var postAdapter: PostAdapter
    var myPosts = mutableListOf<Post.PostItem>()
    companion object{
        val POST_ID = "postID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_post_v, container, false)
        postVBinding = FragmentPostVBinding.bind(view)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getAllPosts()
    }
    fun initRecyclerView(){
        postAdapter = PostAdapter(myPosts,this)
        val recycler = postVBinding.rvPostVertical
        recycler?.layoutManager = LinearLayoutManager(view?.context)
        recycler?.adapter =  postAdapter
    }
    fun getAllPosts(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = ServiceGenerator.getRetrofit().create(APIService::class.java).getPosts()
            if( call.isSuccessful){
                val response = call.body()
                response?.let {
                    withContext(Dispatchers.Main){
                        myPosts.clear()
                        myPosts.addAll(it)
                        postAdapter.notifyDataSetChanged()
                    }
                }
            }else{
                Log.d("chris","No se pudo traer a los posts")
            }
        }
    }

    override fun didSelectPost(postId: Int) {
        val navController = findNavController()
        val bundle = Bundle()
        bundle.putInt(POST_ID,postId)
        navController.navigate(R.id.action_postVFragment_to_postDetailsFragment,bundle)
    }
}