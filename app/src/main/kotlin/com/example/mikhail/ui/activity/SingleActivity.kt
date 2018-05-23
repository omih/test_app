package com.example.mikhail.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.mikhail.R
import com.example.mikhail.ui.fragment.courses.CoursesFragment

class SingleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, CoursesFragment.newInstance()).commit()
    }

}