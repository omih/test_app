package com.example.mikhail.ui.fragment.courses

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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
import com.example.mikhail.viewmodel.SharedStringViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_courses_list.*

class FavoriteCoursesFragment : BaseFragment() {

    companion object {
        fun newInstance() = FavoriteCoursesFragment()
    }

    private lateinit var viewModel: FavoriteCoursesViewModel
    private lateinit var sharedStringViewModel: SharedStringViewModel
    private val coursesAdapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DI.componentManager.courseComponent.inject(this)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel = viewModel(viewModelFactory) {
            success(courses, ::showFavoriteCourses)
            failure(failure, ::showCoursesAbsent)
        }

        sharedStringViewModel =
                ViewModelProviders.of(requireActivity())[SharedStringViewModel::class.java]

        sharedStringViewModel.searchQuery.observe(this, Observer(::searchQuery))
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

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (this::viewModel.isInitialized) viewModel.refreshCourses()
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

    private fun searchQuery(query: String?) {
        query?.let {
            viewModel.loadFavoriteCourses(it)
        }
    }
}
