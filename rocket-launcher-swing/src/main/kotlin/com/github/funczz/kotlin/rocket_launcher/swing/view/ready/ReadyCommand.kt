package com.github.funczz.kotlin.rocket_launcher.swing.view.ready

import com.github.funczz.kotlin.rocket_launcher.core.event.Start
import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamAction
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.core.state.Ready
import com.github.funczz.kotlin.rocket_launcher.swing.UiPresenter
import com.github.funczz.kotlin.rocket_launcher.swing.UiRepresentation

object ReadyCommand {

    fun start(text: String) {
        val counter = text.toInt()
        val rocketLauncherSamModel = RocketLauncherSamModel()
        val inputData = InputData(
            initialCounter = 0,
            currentCounter = 0,
            state = Ready,
            event = Start(initialCounter = counter)
        )
        RocketLauncherSamAction.accept(input = inputData, present = rocketLauncherSamModel::present)
        UiRepresentation.representation(model = rocketLauncherSamModel, render = UiPresenter::render)
    }

}