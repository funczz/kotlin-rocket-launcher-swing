package com.github.funczz.kotlin.rocket_launcher.swing.view.aborted

import com.github.funczz.kotlin.rocket_launcher.core.event.Initialize
import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamAction
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.core.state.Aborted
import com.github.funczz.kotlin.rocket_launcher.swing.UiPresenter
import com.github.funczz.kotlin.rocket_launcher.swing.UiRepresentation

object AbortedCommand {

    fun ready() {
        val rocketLauncherSamModel = RocketLauncherSamModel()
        val inputData = InputData(
            initialCounter = 0,
            currentCounter = 0,
            state = Aborted,
            event = Initialize,
        )
        RocketLauncherSamAction.accept(input = inputData, present = rocketLauncherSamModel::present)
        UiRepresentation.representation(model = rocketLauncherSamModel, render = UiPresenter::render)
    }

}