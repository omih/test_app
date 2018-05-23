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
import com.example.mikhail.core.extension.success
import com.example.mikhail.core.extension.viewModel
import com.example.mikhail.ui.adapter.CourseItemAdapter
import com.example.mikhail.ui.fragment.BaseFragment
import com.example.mikhail.viewmodel.AllCoursesViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_courses_list.*

class AllCoursesFragment : BaseFragment(), SearchView.OnQueryTextListener {
    companion object {
        fun newInstance() = AllCoursesFragment()
    }

    private val coursesAdapter = GroupAdapter<ViewHolder>()

    private lateinit var allCoursesViewModel: AllCoursesViewModel

    private var searchString: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DI.componentManager.courseComponent.inject(this)

        allCoursesViewModel = viewModel(viewModelFactory) {
            success(courses, ::showAllCourses)
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
            progressShow()
            allCoursesViewModel.addOrRemoveFromFavorite((item as CourseItemAdapter).course)
        }

        absent_repeat.setOnClickListener {
            loadAllCourses()
        }

        loadAllCourses()
    }

    private fun loadAllCourses() {
        progressShow()
        allCoursesViewModel.loadCourses()
    }

    private fun progressShow() {
        loading_courses_state.showLoading()
    }

    private fun contentShow() {
        loading_courses_state.showContent()
    }

    fun showCoursesAbsent() {
        loading_courses_state.showStub()
    }

    fun showAllCourses(courses: List<Item<ViewHolder>>?) {
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
        if (this::allCoursesViewModel.isInitialized) {
            allCoursesViewModel.loadCourses(newText)
        }
        return true
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        onQueryTextChange(searchString)
    }

}
