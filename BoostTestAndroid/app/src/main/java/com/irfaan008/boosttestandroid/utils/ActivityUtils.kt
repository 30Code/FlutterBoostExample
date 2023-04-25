package com.mei.myhost.utils

import android.app.Activity
import java.util.*

/**
 * @date 2022/8/26
 * @author mxb
 * @desc
 * @desired
 */
class ActivityUtils {
    companion object {
        private val activityStack = LinkedList<Activity>()

        fun push(activity: Activity) {
            activityStack.push(activity)
        }

        fun pop(): Activity {
            return activityStack.pop()
        }

        fun current(): Activity? = activityStack.peek()
    }
}