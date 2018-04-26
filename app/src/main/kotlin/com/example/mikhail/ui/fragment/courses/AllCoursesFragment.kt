package com.example.mikhail.ui.fragment.courses

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.mikhail.R
import com.example.mikhail.presentation.presenter.AllCoursesPresenter
import com.example.mikhail.presentation.view.AllCoursesView
import com.example.mikhail.ui.fragment.BaseFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_courses_list.*

class AllCoursesFragment: BaseFragment(), AllCoursesView {

    companion object {
        fun newInstance() = AllCoursesFragment()
    }

    @InjectPresenter
    internal lateinit var presenter: AllCoursesPresenter

    private val coursesAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_courses_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(list_courses) {
            adapter = coursesAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun showAllCourses(courses: List<Item<ViewHolder>>) {
        coursesAdapter.clear()
        coursesAdapter.addAll(courses)
    }
}