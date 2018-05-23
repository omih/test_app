package com.example.mikhail.ui.fragment.courses

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikhail.R
import com.example.mikhail.core.di.DI
import com.example.mikhail.core.extension.failure
import com.example.mikhail.core.extension.gone
import com.example.mikhail.core.extension.success
import com.example.mikhail.core.extension.viewModel
import com.example.mikhail.ui.adapter.CourseItemAdapter
import com.example.mikhail.ui.fragment.BaseFragment
import com.example.mikhail.viewmodel.FavoriteCoursesViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_courses_list.*

class FavoriteCoursesFragment : BaseFragment(), SearchView.OnQueryTextListener {

    companion object {
        fun newInstance() = FavoriteCoursesFragment()
    }

    private lateinit var viewModel: FavoriteCoursesViewModel
    private val coursesAdapter = GroupAdapter<ViewHolder>()

    private var searchString: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DI.componentManager.courseComponent.inject(this)

        viewModel = viewModel(viewModelFactory) {
            success(courses, ::showFavoriteCourses)
            failure(failure, ::showCoursesAbsent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_courses_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(list_courses) {
            adapter = coursesAdapter
            layoutManager = LinearLayoutManager(context)
        }

        coursesAdapter.setOnItemClickListener { item, _ ->
            viewModel.removeFromFavorite((item as CourseItemAdapter).course)
        }

        absent_repeat.gone()

        loadCourses()
    }

    private fun loadCourses() {
        progressShow()
        viewModel.loadFavoriteCourses()
    }

    private fun progressShow() {
        loading_courses_state.showLoading()
    }

    private fun contentShow() {
        loading_courses_state.showContent()
    }

    private fun showCoursesAbsent() {
        loading_courses_state.showStub()
    }

    private fun showFavoriteCourses(courses: List<Item<ViewHolder>>?) {
        coursesAdapter.clear()
        courses?.let {
            if (it.isEmpty()) {
                showCoursesAbsent()
            } else {
                contentShow()
                coursesAdapter.addAll(it)
            }
        } ?: showCoursesAbsent()
    }

    override fun onQueryTextSubmit(query: String?) = false

    override fun onQueryTextChange(newText: String): Boolean {
        searchString = newText
        if (this::viewModel.isInitialized) {
            viewModel.loadFavoriteCourses(newText)
        }
        return true
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        onQueryTextChange(searchString)
    }
}
