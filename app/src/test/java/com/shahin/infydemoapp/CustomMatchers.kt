package com.shahin.infydemoapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.junit.runner.Description
import java.util.regex.Matcher

class CustomMatchers {
    companion object {
        fun withItemCount(count: Int): Matcher<View> {
            return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
                 fun describeTo(description: Description?) {
                    description?.appendText("RecyclerView with item count: $count")
                }

                 fun matchesSafely(item: RecyclerView?): Boolean {
                    return item?.adapter?.itemCount == count
                }
            }
        }
    }
}

