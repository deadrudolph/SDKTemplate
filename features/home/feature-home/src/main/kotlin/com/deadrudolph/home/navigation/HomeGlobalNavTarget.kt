package com.deadrudolph.home.navigation

import com.deadrudolph.navigation.NavTarget

class HomeGlobalNavTarget : NavTarget(HOME_NAV_TARGET) {

    companion object {
        const val HOME_NAV_TARGET_KEY = "HOME"
        private const val HOME_NAV_TARGET = "home_module"
    }
}
