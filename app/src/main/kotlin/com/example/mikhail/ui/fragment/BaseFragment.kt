package com.example.mikhail.ui.fragment

import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

}