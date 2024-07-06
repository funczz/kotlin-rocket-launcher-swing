package com.github.funczz.kotlin.rocket_launcher.swing

import com.github.funczz.kotlin.notifier.Notifier
import com.github.funczz.kotlin.notifier.property.WONotifierProperty
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewId

object UiPresenter {

    private val notifier = Notifier.getDefault()

    const val INITIAL_COUNTER_NOTIFIER_NAME = "/UiState/initialCounter@"
    private val initialCounterRegex = "^$INITIAL_COUNTER_NOTIFIER_NAME.*".toRegex()
    private val initialCounter = WONotifierProperty(
        initialValue = 0,
        notifier = notifier
    )

    const val CURRENT_COUNTER_NOTIFIER_NAME = "/UiState/currentCounter@"
    private val currentCounterRegex = "^$CURRENT_COUNTER_NOTIFIER_NAME.*".toRegex()
    private val currentCounter = WONotifierProperty(
        initialValue = 0,
        notifier = notifier
    )

    const val UI_STATE_NOTIFIER_NAME = "/UiState@"
    private val uiStateRegex = "^$UI_STATE_NOTIFIER_NAME.*".toRegex()
    private val uiState = WONotifierProperty(
        initialValue = UiState(viewId = ViewId.Ready),
        notifier = notifier,
    )

    fun render(output: UiState) {
        //initialCounterの値を、各PanelのRONotifierPropertyへ通知する
        initialCounter.setValue(
            value = output.samModel.initialCounter,
            name = initialCounterRegex,
        )
        //currentCounterの値を、各PanelのRONotifierPropertyへ通知する
        currentCounter.setValue(
            value = output.samModel.currentCounter,
            name = currentCounterRegex,
        )
        //UiStateの値を、ViewSubscriberへ通知する
        uiState.setValue(
            value = output,
            name = uiStateRegex,
        )
    }
}