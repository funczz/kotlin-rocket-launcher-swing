package com.github.funczz.kotlin.rocket_launcher.swing.view

import com.github.funczz.kotlin.notifier.DefaultNotifierSubscription
import com.github.funczz.kotlin.rocket_launcher.swing.UiPresenter
import java.util.*
import java.util.concurrent.Executor

object ViewSubscriptionFactory {

    fun new(viewPanel: ViewPanel, executor: Optional<Executor>): DefaultNotifierSubscription {
        val subscriber = ViewSubscriber(viewPanel = viewPanel)
        return DefaultNotifierSubscription(
            subscriber = subscriber,
            name = "%s%s".format(
                UiPresenter.UI_STATE_NOTIFIER_NAME,
                viewPanel.viewId.id
            ),
            executor = executor,
        )
    }

}
