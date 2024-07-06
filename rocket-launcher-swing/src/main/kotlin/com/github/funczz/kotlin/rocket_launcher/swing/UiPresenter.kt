package com.github.funczz.kotlin.rocket_launcher.swing

import com.github.funczz.kotlin.notifier.Notifier
import com.github.funczz.kotlin.notifier.property.WONotifierProperty
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewId
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object UiPresenter {

    //イベントバス
    val notifier = Notifier()

    //イベントを処理するスレッドプールを生成する
    val executor: ExecutorService = Executors.newFixedThreadPool(
        Runtime.getRuntime().availableProcessors()
    )

    const val INITIAL_COUNTER_NOTIFIER_NAME = "/UiState/initialCounter@"
    private val initialCounterRegex = "^$INITIAL_COUNTER_NOTIFIER_NAME.*".toRegex()
    private val initialCounter = WONotifierProperty(
        initialValue = 0,
        notifier = notifier,
    )

    const val CURRENT_COUNTER_NOTIFIER_NAME = "/UiState/currentCounter@"
    private val currentCounterRegex = "^$CURRENT_COUNTER_NOTIFIER_NAME.*".toRegex()
    private val currentCounter = WONotifierProperty(
        initialValue = 0,
        notifier = notifier,
    )

    const val UI_STATE_NOTIFIER_NAME = "/UiState@"
    private val uiStateRegex = "^$UI_STATE_NOTIFIER_NAME.*".toRegex()
    private val uiState = WONotifierProperty(
        initialValue = UiState(viewId = ViewId.Ready),
        notifier = notifier,
    )

    fun render(output: UiState) {
        //initialCounterの値を通知する
        initialCounter.setValue(
            value = output.samModel.initialCounter,
            name = initialCounterRegex,
            executor = executor,
        )
        //currentCounterの値を通知する
        currentCounter.setValue(
            value = output.samModel.currentCounter,
            name = currentCounterRegex,
            executor = executor,
        )
        //UiStateの値を通知する
        uiState.setValue(
            value = output,
            name = uiStateRegex,
            executor = executor,
        )
    }
}