package com.github.funczz.kotlin.rocket_launcher.swing.view.counting

import com.github.funczz.kotlin.rocket_launcher.core.event.Decrement
import com.github.funczz.kotlin.rocket_launcher.core.event.RocketLauncherEvent
import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamAction
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting
import com.github.funczz.kotlin.rocket_launcher.swing.UiPresenter
import com.github.funczz.kotlin.rocket_launcher.swing.UiRepresentation
import com.github.funczz.kotlin.rocket_launcher.swing.job.CountingJobSubscriber
import java.util.concurrent.Flow

class CountingPanelCountingJobSubscriber : CountingJobSubscriber() {

    private val rocketLauncherSamModel = RocketLauncherSamModel()

    private fun action(event: RocketLauncherEvent?) {
        output(message = "action: event=%s".format(event))
        when (event) {
            is RocketLauncherEvent -> {
                val inputData = InputData(
                    initialCounter = rocketLauncherSamModel.initialCounter,
                    currentCounter = rocketLauncherSamModel.currentCounter,
                    state = Counting,
                    event = event,
                )
                RocketLauncherSamAction.accept(input = inputData, present = rocketLauncherSamModel::present)
                output("Updated: %s".format(rocketLauncherSamModel.toString()))
            }

            else -> {}
        }
        UiRepresentation.representation(model = rocketLauncherSamModel, render = UiPresenter::render)
    }

    override fun onSetUp(item: Any) {
        output(message = "SetUp")
        super.onSetUp(item)
        val rocketLauncher = RocketLauncher(
            initialCounter = counter,
            currentCounter = counter,
            state = Counting,
            isTransitioned = false
        )
        rocketLauncherSamModel.present(data = rocketLauncher)
        output("Created: %s".format(rocketLauncherSamModel.toString()))
        action(event = null)
    }

    override fun onIsContinue(): Boolean {
        output(message = "Is continue: counter=%d, isStarted=%b, isBreak=%b".format(counter, isStarted, isBreak))
        return super.onIsContinue()
    }

    override fun onDoWhile() {
        output(message = "While: [%d] %s".format(counter, rocketLauncherSamModel.toString()))
        super.onDoWhile()
        output(message = "While: [%d:result] %s".format(counter, rocketLauncherSamModel.toString()))
        if (counter >= 0) {
            action(event = Decrement)
        } else {
            throw IllegalStateException("counter is less than zero.")
        }
    }

    override fun onTearDown() {
        output(message = "TearDown")
        super.onTearDown()
    }

    override fun onSubscribe(subscription: Flow.Subscription?) {
        output(message = "Subscribe")
        super.onSubscribe(subscription)
    }

    override fun onError(throwable: Throwable?) {
        output(message = throwable?.stackTraceToString() ?: "")
        super.onError(throwable)
    }

    override fun onComplete() {
        output(message = "Complete")
        super.onComplete()
    }

    private fun output(message: String) {
        println(message = message)
    }

}