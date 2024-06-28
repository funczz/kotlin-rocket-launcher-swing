package com.github.funczz.kotlin.rocket_launcher.swing.job

import java.util.concurrent.Flow.Subscriber

abstract class WhileJobSubscriber : Subscriber<Any> {

    abstract fun onSetUp(item: Any)

    abstract fun onIsContinue(): Boolean

    abstract fun onDoWhile()

    abstract fun onTearDown()

    override fun onNext(item: Any) = try {
        onSetUp(item)
        while (onIsContinue()) onDoWhile()
    } catch (th: Throwable) {
        throw th
    } finally {
        onTearDown()
    }

}