package com.example.mikhail.ui.fragment.courses

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.mikhail.R
import com.example.mikhail.presentation.presenter.FavoriteCoursesPresenter
import com.example.mikhail.presentation.view.FavoriteCoursesView
import com.example.mikhail.ui.adapter.CourseItemAdapter
import com.example.mikhail.ui.fragment.BaseFragment
import com.example.mikhail.ui.gone
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_courses_list.*

class FavoriteCoursesFragment: BaseFragment(), FavoriteCoursesView {

    companion object {
        fun newInstance() = FavoriteCoursesFragment()
    }

    @InjectPresenter
    internal lateinit var presenter: FavoriteCoursesPresenter

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

        coursesAdapter.setOnItemClickListener { item, _ ->
            presenter.removeFromFavorite((item as CourseItemAdapter).course)
        }

        absent_repeat.gone()
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


    override fun showFavoriteCourses(courses: List<Item<ViewHolder>>) {
        coursesAdapter.clear()
        coursesAdapter.addAll(courses)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            presenter.loadFavoriteCourses()
        }
    }
}