package com.shahin.infydemoapp

import com.shahin.infydemoapp.CustomMatchers.Companion.withItemCount
import com.shahin.infydemoapp.ui.activity.main.MainActivity
import org.junit.Rule
import org.junit.Test
import java.util.regex.Pattern.matches


class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
fun countPrograms() {
    onView(withId(R.id.recycler_view))
        .check(matches(withItemCount(100)))
}

}
