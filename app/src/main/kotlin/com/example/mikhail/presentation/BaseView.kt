package com.example.mikhail.presentation

import com.arellomobile.mvp.MvpView

interface BaseView : MvpView {
    fun showMessage(message: String)
}