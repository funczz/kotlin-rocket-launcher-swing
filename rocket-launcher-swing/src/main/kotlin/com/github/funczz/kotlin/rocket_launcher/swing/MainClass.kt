package com.github.funczz.kotlin.rocket_launcher.swing

import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.swing.view.aborted.AbortedPanel
import com.github.funczz.kotlin.rocket_launcher.swing.view.counting.CountingPanel
import com.github.funczz.kotlin.rocket_launcher.swing.view.launched.LaunchedPanel
import com.github.funczz.kotlin.rocket_launcher.swing.view.ready.ReadyPanel
import java.util.*

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            //イベントバスを取得する
            val notifier = UiPresenter.notifier

            //Executorを取得する
            val executor = UiPresenter.executor

            //JFrameを初期化する
            MainJFrame.getInstance().windowClosing {
                notifier.unsubscribeAll()
                executor.shutdownNow()
            }

            /**
             * 各Viewを初期化して、イベントバスにサブスクリプションを追加する
             */
            ReadyPanel(notifier = notifier, executor = Optional.of(executor))
            CountingPanel(notifier = notifier, executor = Optional.of(executor))
            LaunchedPanel(notifier = notifier, executor = Optional.of(executor))
            AbortedPanel(notifier = notifier, executor = Optional.of(executor))

            /**
             * アクションを開始する
             */
            val rocketLauncher = RocketLauncher().apply {
                isTransitioned = true
            }
            val rocketLauncherSamModel = RocketLauncherSamModel().apply {
                present(rocketLauncher)
            }
            UiRepresentation.representation(model = rocketLauncherSamModel, render = UiPresenter::render)
        }
    }
}