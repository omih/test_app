package com.example.mikhail.ui.fragment.courses

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
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

    private lateinit var searchListener: SearchView.OnQueryTextListener
    private var searchString = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_courses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager()
        initToolbar()
    }

    private fun initToolbar() {
        with(toolbar) {
            inflateMenu(R.menu.search)
            val searchView = menu.findItem(R.id.courses_search).actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String) = searchListener.onQueryTextSubmit(query)

                override fun onQueryTextChange(newText: String): Boolean {
                    searchString = newText
                    return searchListener.onQueryTextChange(newText)
                }

            })
        }
    }

    private fun initViewPager() {
        with(courses_viewpager) {
            val adapter = CoursesViewPagerAdapter(childFragmentManager, context)
            courses_viewpager.adapter = adapter

            courses_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

                override fun onPageSelected(position: Int) {
                    searchListener = adapter.getItem(position) as SearchView.OnQueryTextListener
                    searchListener.onQueryTextChange(searchString)
                }
            })

            searchListener = adapter.getFirstSearchListener()
        }

        courses_tabs.setupWithViewPager(courses_viewpager)
    }

}