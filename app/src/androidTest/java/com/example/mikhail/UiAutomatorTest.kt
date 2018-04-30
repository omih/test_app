package com.example.mikhail

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UiAutomatorTest {
    private val packageApp = "com.example.mikhail"

    private val timeOut = 5000L

    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Before
    fun startFromHomeScreen() {
        device.pressHome()

        device.wait(Until.hasObject(By.pkg(device.launcherPackageName).depth(0)), timeOut)

        val context = InstrumentationRegistry.getContext()
        val intent = context.packageManager
                .getLaunchIntentForPackage(packageApp)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        device.wait(Until.hasObject(By.pkg(packageApp).depth(0)), timeOut)
    }

    @Test
    fun testFavoriteIsEmpty() {
        var firstCourse = getItemFromDisplayedCourses(0)
        while (courseIsFavorite(firstCourse)) {
            firstCourse.clickAndWaitForNewWindow()
            firstCourse = getItemFromDisplayedCourses(0)
        }

        val viewPager = getUiObjectById("courses_viewpager")
        viewPager.swipeLeft(10)
        assertTrue(!getListDisplayedCourses().exists())
    }

    @Test
    fun testAddFavorite() {
        val firstCourse = getItemFromDisplayedCourses(0)
        if (!courseIsFavorite(firstCourse)) {
            firstCourse.clickAndWaitForNewWindow()
        }
        val viewPager = getUiObjectById("courses_viewpager")
        viewPager.swipeLeft(10)

        val count = getListDisplayedCourses().childCount

        assertEquals(1, count)
    }

    @Test
    fun testSearchCourses() {
        getUiObjectById("search_button").clickAndWaitForNewWindow()
        getUiObjectById("search_plate").legacySetText("linux")
        device.pressBack()
        device.waitForIdle(timeOut)

        val firstCourse = getItemFromDisplayedCourses(0)
        val firstCourseTitle = firstCourse.getChildById("course_title")
        assertEquals("Intro to linux", firstCourseTitle.text)
    }

    @Test
    fun testSearchFavoriteCourses() {
        device.waitForIdle(timeOut)
        val resultTitle = listOf("TOEFL vocabulary", "Common English Verbs", "Data Structures", "Easy Way to Technical Writing", "Common English Adjectives")
        val searchString = listOf("toefl", "verbs", "data", "way", "adjectives")

        for (i in 0..4) {
            val course = getItemFromDisplayedCourses(i)
            if (!courseIsFavorite(course)) {
                course.clickAndWaitForNewWindow()
            }
        }

        val viewPager = getUiObjectById("courses_viewpager")
        viewPager.swipeLeft(10)

        getUiObjectById("search_button").clickAndWaitForNewWindow()
        val searchPlace = getUiObjectById("search_plate")


        for (i in 0..4) {
            searchPlace.legacySetText(searchString[i])
            device.pressBack()

            val firstCourse = getItemFromDisplayedCourses(0)
            val firstCourseTitle = firstCourse.getChildById("course_title")
            assertEquals(resultTitle[i], firstCourseTitle.text)
        }
    }

    @Test
    fun rotateScreen() {
        device.setOrientationLeft()
        device.setOrientationRight()
        device.setOrientationNatural()
    }


    private fun getListDisplayedCourses(): UiCollection {
        return UiCollection(UiSelector().resourceId("com.example.mikhail:id/list_courses"))
    }

    private fun getItemFromDisplayedCourses(index: Int): UiObject {
        return getListDisplayedCourses().getChildByInstance(UiSelector().resourceId("com.example.mikhail:id/course_card"), index)
    }

    private fun courseIsFavorite(course: UiObject): Boolean {
        return course.getChild(UiSelector().resourceId("com.example.mikhail:id/course_favorite")).isChecked
    }

    private fun getUiObjectById(id: String): UiObject {
        return device.findObject(UiSelector().resourceId("com.example.mikhail:id/$id"))
    }

    private fun UiObject.getChildById(id: String): UiObject {
        return this.getChild(UiSelector().resourceId("com.example.mikhail:id/$id"))
    }
}