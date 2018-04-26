package com.example.mikhail.ui.fragment.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.mikhail.R
import com.example.mikhail.presentation.presenter.CoursesPresenter
import com.example.mikhail.presentation.view.CoursesView
import com.example.mikhail.ui.adapter.CoursesViewPagerAdapter
import com.example.mikhail.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_courses.*


class CoursesFragment : BaseFragment(), CoursesView {

    companion object {
        fun newInstance() = CoursesFragment()
    }

    @InjectPresenter
    internal lateinit var presenter: CoursesPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_courses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courses_viewpager.adapter = CoursesViewPagerAdapter(childFragmentManager, context)
        courses_tabs.setupWithViewPager(courses_viewpager)

        toolbar.inflateMenu(R.menu.search)
    }

}