package com.deadrudolph.profile.navigation

import com.deadrudolph.navigation.NavTarget

class ProfileGlobalNavTarget : NavTarget(PROFILE_NAV_TARGET) {

    companion object {
        const val PROFILE_NAV_TARGET_KEY = "PROFILE"
        private const val PROFILE_NAV_TARGET = "profile_module"
    }
}
