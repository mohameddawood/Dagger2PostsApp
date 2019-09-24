package com.google.firebase.udacity.daggerPosts.dagger.ui.main.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.firebase.udacity.daggerPosts.R
import com.google.firebase.udacity.daggerPosts.VerticalSpaceItemDecoration
import com.google.firebase.udacity.daggerPosts.dagger.di.daggerViewModelHandling.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_posts.*
import javax.inject.Inject


class PostsFragment : DaggerFragment() {
    private val TAG = "PostsFragment"
    @Inject lateinit var factory: ViewModelProviderFactory // user the injected Factory to solve dagger/viewModel issue
    @Inject lateinit var adapter: PostRecyclerAdapter //  use the injected adapter in MainModule
    @Inject lateinit var layoutManager: LayoutManager // user the injected layoutManager in MainModule
    private lateinit var postsViewModel: PostsViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postsViewModel = ViewModelProvider(this, factory).get(PostsViewModel::class.java)
        observePosts()
        initRecycler()
        //Toast.makeText(context, "DDDDDDDDDDDDDDDDDdd", Toast.LENGTH_LONG).show()
    }

    private fun initRecycler() {
        recycler_view.layoutManager = layoutManager
        val decoration = VerticalSpaceItemDecoration(15)
        recycler_view.addItemDecoration(decoration)
        recycler_view.adapter = adapter
    }

    private fun observePosts() {
        postsViewModel.observeMyPosts().removeObservers(viewLifecycleOwner)
        postsViewModel.observeMyPosts().observe(this, Observer { listResource ->
            adapter.setPosts(listResource)
        })
    }


}




