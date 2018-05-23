package com.example.mikhail.ui.fragment.courses

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikhail.R
import com.example.mikhail.ui.adapter.CoursesViewPagerAdapter
import com.example.mikhail.ui.fragment.BaseFragment
import com.example.mikhail.viewmodel.SharedStringViewModel
import kotlinx.android.synthetic.main.fragment_courses.*


class CoursesFragment : BaseFragment() {

    companion object {
        fun newInstance() = CoursesFragment()
    }

    lateinit var sharedStringViewModel: SharedStringViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedStringViewModel =
                ViewModelProviders.of(requireActivity())[SharedStringViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            val searchItem = menu.findItem(R.id.courses_search)
            val searchView = searchItem.actionView as SearchView

            restoreSearchView(searchView)

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String) = false

                override fun onQueryTextChange(newText: String): Boolean {
                    sharedStringViewModel.searchQuery.value = newText
                    return true
                }
            })
        }
    }

    private fun restoreSearchView(searchView: SearchView) {
        sharedStringViewModel.searchQuery.value?.let {
            if (it.isNotEmpty()) {
                searchView.isIconified = false
                searchView.setQuery(it, false)
                searchView.clearFocus()
            }
        }
    }

    private fun initViewPager() {
        with(courses_viewpager) {
            val adapter = CoursesViewPagerAdapter(childFragmentManager, context)
            courses_viewpager.adapter = adapter
        }

        courses_tabs.setupWithViewPager(courses_viewpager)
    }

}