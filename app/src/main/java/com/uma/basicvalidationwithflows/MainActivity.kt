package com.uma.basicvalidationwithflows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    var viewModel = MainViewModel()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Initilizers()
        collectFlow()
    }

    @ExperimentalCoroutinesApi
    private fun Initilizers() {
        etxtName.addTextChangedListener {
            viewModel.setName(it.toString())
        }
        etxtPassword.addTextChangedListener {
            viewModel.sePassword(it.toString())
        }
        etxtId.addTextChangedListener{
            viewModel.setUserId(it.toString())
        }
    }

    @ExperimentalCoroutinesApi
    private fun collectFlow(){
        lifecycleScope.launch {
            viewModel.isSubmitButtonEnabled.collect {
                btnValidate.isEnabled = it
            }
        }
    }
}