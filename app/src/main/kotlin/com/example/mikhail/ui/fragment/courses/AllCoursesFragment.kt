package com.example.mikhail.ui.fragment.courses

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.mikhail.R
import com.example.mikhail.presentation.presenter.AllCoursesPresenter
import com.example.mikhail.presentation.view.AllCoursesView
import com.example.mikhail.ui.adapter.CourseItemAdapter
import com.example.mikhail.ui.fragment.BaseFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_courses_list.*

class AllCoursesFragment: BaseFragment(), AllCoursesView, SearchView.OnQueryTextListener {
    companion object {

        fun newInstance() = AllCoursesFragment()
    }
    @InjectPresenter
    internal lateinit var presenter: AllCoursesPresenter

    private val coursesAdapter = GroupAdapter<ViewHolder>()

    private var searchString: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_courses_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(list_courses) {
            adapter = coursesAdapter
            layoutManager = LinearLayoutManager(context)
        }

        coursesAdapter.setOnItemClickListener { item, _ ->
            presenter.addOrRemoveFromFavorite((item as CourseItemAdapter).course)
        }

        absent_repeat.setOnClickListener { presenter.loadCourses() }
    }

    override fun progressShow() {
        loading_courses_state.showLoading()
    }

    override fun contentShow() {
        loading_courses_state.showContent()
    }

    override fun showCoursesAbsent() {
        loading_courses_state.showStub()
    }

    override fun showAllCourses(courses: List<Item<ViewHolder>>) {
        coursesAdapter.clear()
        coursesAdapter.addAll(courses)
    }

    override fun onQueryTextSubmit(query: String?) = false

    override fun onQueryTextChange(newText: String): Boolean {
        searchString = newText
        if (this::presenter.isInitialized) {
            presenter.loadCourses(newText)
        }
        return true
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        onQueryTextChange(searchString)
    }

}
