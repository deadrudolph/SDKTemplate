package com.deadrudolph.home.initializer

import android.content.Context
import androidx.startup.Initializer
import com.deadrudolph.home.navigation.HomeGlobalNavTarget
import com.deadrudolph.home.navigation.HomeGlobalNavTarget.Companion.HOME_NAV_TARGET_KEY
import com.deadrudolph.navigation.manager.NavigationManager

class HomeInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        NavigationManager.registerNavTarget(
            key = HOME_NAV_TARGET_KEY,
            destination = HomeGlobalNavTarget()
        )
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}
