package com.example.mikhail.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.content.ContextCompat
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import com.example.mikhail.R
import com.example.mikhail.ui.fragment.courses.AllCoursesFragment
import com.example.mikhail.ui.fragment.courses.FavoriteCoursesFragment


class CoursesViewPagerAdapter(fm: FragmentManager, val context: Context?) : FragmentStatePagerAdapter(fm) {

    private val fragments = arrayListOf<Fragment>()
    private val imageResId = arrayListOf<Int>()

    init {
        fragments.add(AllCoursesFragment.newInstance())
        fragments.add(FavoriteCoursesFragment.newInstance())

        imageResId.add(R.drawable.ic_format_list_bulleted_black_24dp)
        imageResId.add(R.drawable.ic_favorite_black_24dp)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val sb = SpannableString(" ")
        val image = context?.let { ContextCompat.getDrawable(it, imageResId[position]) }
        image?.let {
            it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
            val imageSpan = ImageSpan(it, ImageSpan.ALIGN_BOTTOM)
            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return sb
    }
}