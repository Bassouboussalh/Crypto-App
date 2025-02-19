package com.boussalh.cryptoapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toolbar.LayoutParams
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.boussalh.cryptoapp.Adapter.CryptoListAdapter
import com.boussalh.cryptoapp.ViewModel.MainViewModel
import com.boussalh.cryptoapp.databinding.ActivityMainBinding
import com.boussalh.cryptoapp.databinding.ViewholderWalletBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        initRecyclerviewCrypto()
    }

    private fun initRecyclerviewCrypto() {
        binding.view.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.view.adapter = CryptoListAdapter(mainViewModel.loadData())
    }
}