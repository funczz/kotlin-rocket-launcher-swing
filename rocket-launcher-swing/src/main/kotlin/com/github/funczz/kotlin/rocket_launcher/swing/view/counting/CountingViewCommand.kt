package com.github.funczz.kotlin.rocket_launcher.swing.view.counting

import com.github.funczz.kotlin.notifier.Notifier
import com.github.funczz.kotlin.rocket_launcher.core.event.Abort
import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamAction
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting
import com.github.funczz.kotlin.rocket_launcher.swing.UiPresenter
import com.github.funczz.kotlin.rocket_launcher.swing.UiRepresentation
import com.github.funczz.kotlin.rocket_launcher.swing.job.JobId
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewCommand

object CountingViewCommand : ViewCommand {

    fun start(initialCounter: Int) {
        Notifier.getDefault().post(item = initialCounter, id = JobId.Counting.id.toRegex())
    }

    fun abort(initialCounter: Int, currentCounter: Int) {
        val rocketLauncherSamModel = RocketLauncherSamModel()
        val inputData = InputData(
            initialCounter = initialCounter,
            currentCounter = currentCounter,
            state = Counting,
            event = Abort,
        )
        RocketLauncherSamAction.accept(input = inputData, present = rocketLauncherSamModel::present)
        UiRepresentation.representation(model = rocketLauncherSamModel, render = UiPresenter::render)
    }

}