package com.bn.aichatchatgpt.presentation

import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.bn.aichatchatgpt.base.ParentActivity
import com.bn.aichatchatgpt.databinding.ActivityMainBinding

class MainActivity : ParentActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate


    private var messagesAdapter: ChatAdapter? = null


    override fun initializeComponents() {
        messagesAdapter = ChatAdapter(this, 0)
        setUpRecyclerView()
    }


    private fun setUpRecyclerView() {
        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.reverseLayout = true
        mLayoutManager.stackFromEnd = false
        binding.rvMessageRecyclerview.apply {
            adapter = messagesAdapter
            layoutManager = mLayoutManager
        }

    }

}