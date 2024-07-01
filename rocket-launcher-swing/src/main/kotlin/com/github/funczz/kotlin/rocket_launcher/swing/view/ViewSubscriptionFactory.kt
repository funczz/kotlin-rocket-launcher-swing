package com.github.funczz.kotlin.rocket_launcher.swing.view

import com.github.funczz.kotlin.notifier.DefaultNotifierSubscription
import java.util.*
import java.util.concurrent.Executor

object ViewSubscriptionFactory {

    fun new(viewPanel: ViewPanel, executor: Optional<Executor>): DefaultNotifierSubscription {
        val subscriber = ViewSubscriber(viewPanel = viewPanel)
        return DefaultNotifierSubscription(
            subscriber = subscriber,
            id = viewPanel.viewId.id,
            executor = executor,
        )
    }

}
