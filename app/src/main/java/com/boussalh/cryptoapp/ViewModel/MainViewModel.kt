package com.boussalh.cryptoapp.ViewModel

import androidx.lifecycle.ViewModel
import com.boussalh.cryptoapp.Repository.MainRepository

class MainViewModel(val repository: MainRepository):ViewModel() {
    constructor():this(MainRepository())

    fun loadData() = repository.items
}