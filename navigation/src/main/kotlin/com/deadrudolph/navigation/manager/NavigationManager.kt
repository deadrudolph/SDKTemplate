package com.deadrudolph.navigation.manager

import com.deadrudolph.navigation.NavTarget
import timber.log.Timber

object NavigationManager {

    private val navTargetsRegistry = hashMapOf<String, NavTarget>()

    fun registerNavTarget(key: String, destination: NavTarget) {
        navTargetsRegistry[key] = destination
    }

    fun getNavTargetOrEmpty(key: String): NavTarget {
        return navTargetsRegistry[key] ?: run {
            Timber.e("Nav Target with key $key should be registered")
            NavTarget("")
        }
    }
}
