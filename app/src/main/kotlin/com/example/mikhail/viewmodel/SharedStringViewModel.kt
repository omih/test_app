package com.example.mikhail.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class SharedStringViewModel : ViewModel() {
    var searchQuery: MutableLiveData<String> = MutableLiveData()
}