package com.example.mikhail.ui.adapter

import com.example.mikhail.R
import com.example.mikhail.internal.GlideApp
import com.example.model.model.CourseMainData
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_course_main_data.view.*
import kotlin.math.roundToInt

class CourseItemAdapter(val course: CourseMainData): Item<ViewHolder>() {
    override fun getLayout() = R.layout.item_course_main_data

    override fun bind(viewHolder: ViewHolder, position: Int) {
        with(viewHolder.itemView) {
            course_title.text = course.title
            course_score.text = course.score.roundToInt().toString()
            course_favorite.isChecked = course.favorite

            GlideApp.with(this)
                    .load(course.logo)
                    .placeholder(R.drawable.ic_image_black_blank_opacity50_24dp)
                    .error(R.drawable.ic_image_black_blank_opacity50_24dp)
                    .into(course_logo)
        }
    }
}