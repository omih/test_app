package com.example.mikhail.cicerone

import android.support.v4.app.Fragment
import com.example.mikhail.ui.activity.SingleActivity
import com.example.mikhail.ui.fragment.courses.CoursesFragment
import ru.terrakok.cicerone.android.SupportFragmentNavigator


class MainNavigator(private val activity: SingleActivity, containerId: Int) :
        SupportFragmentNavigator(activity.supportFragmentManager, containerId) {
    override fun showSystemMessage(message: String?) {

    }

    private val fragmentManager = activity.supportFragmentManager

    override fun createFragment(screenKey: String?, data: Any?): Fragment {
        return when (screenKey) {
            Screens.COURSES -> CoursesFragment.newInstance()

            else -> throw IllegalArgumentException("Unknown screen $screenKey")
        }
    }

    override fun exit() {
        activity.finish()
    }

}