package com.github.funczz.kotlin.rocket_launcher.swing.view

import com.github.funczz.kotlin.rocket_launcher.swing.UiState
import java.util.concurrent.Flow
import java.util.concurrent.Flow.Subscriber

class ViewSubscriber(

    val viewPanel: ViewPanel,

    ) : Subscriber<Any> {

    override fun onSubscribe(subscription: Flow.Subscription?) {
    }

    override fun onError(throwable: Throwable?) {
        System.err.println(throwable?.stackTrace)
    }

    override fun onComplete() {
    }

    override fun onNext(item: Any?) {
        when (item) {
            is UiState -> viewPanel.render(output = item)
        }
    }
}