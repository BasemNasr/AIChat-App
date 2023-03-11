package com.bn.aichatchatgpt.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class ParentActivity<out VB : ViewBinding> : AppCompatActivity() {
    private var _binding: ViewBinding? = null
    protected val binding: VB get() = _binding as VB
    abstract val bindingInflater: (LayoutInflater) -> VB
    protected abstract fun initializeComponents()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(_binding?.root)


        initializeComponents()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}