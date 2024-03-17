package com.deadrudolph.profile.initializer

import android.content.Context
import androidx.startup.Initializer
import com.deadrudolph.navigation.manager.NavigationManager
import com.deadrudolph.profile.navigation.ProfileGlobalNavTarget
import com.deadrudolph.profile.navigation.ProfileGlobalNavTarget.Companion.PROFILE_NAV_TARGET_KEY

class ProfileInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        NavigationManager.registerNavTarget(
            key = PROFILE_NAV_TARGET_KEY,
            destination = ProfileGlobalNavTarget()
        )
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}
