package com.deadrudolph.commondi.component.app

import com.deadrudolph.commondi.holder.DataComponentHolder

object ApplicationComponentHolder :
    DataComponentHolder<ApplicationComponent, ApplicationComponentDependencies>() {

    override fun build(data: ApplicationComponentDependencies): ApplicationComponent =
        DaggerApplicationComponentInternal.factory().create(data)
}
