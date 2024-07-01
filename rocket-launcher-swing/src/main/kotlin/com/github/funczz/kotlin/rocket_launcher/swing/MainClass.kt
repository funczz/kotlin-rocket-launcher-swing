package com.github.funczz.kotlin.rocket_launcher.swing

import com.github.funczz.kotlin.notifier.DefaultNotifierSubscription
import com.github.funczz.kotlin.notifier.Notifier
import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.swing.job.JobId
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewSubscriptionFactory
import com.github.funczz.kotlin.rocket_launcher.swing.view.aborted.AbortedPanel
import com.github.funczz.kotlin.rocket_launcher.swing.view.counting.CountingPanel
import com.github.funczz.kotlin.rocket_launcher.swing.view.launched.LaunchedPanel
import com.github.funczz.kotlin.rocket_launcher.swing.view.ready.ReadyPanel
import java.util.*
import java.util.concurrent.Executors

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            //イベントを処理するスレッドプールを生成する
            val executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

            //JFrameを初期化する
            MainJFrame.getInstance().windowClosing {
                Notifier.getDefault().unsubscribeAll()
                executor.shutdownNow()
            }

            /**
             * イベントバスに View サブスクリプションを追加する
             */
            Notifier.getDefault().subscribe(
                ViewSubscriptionFactory.new(
                    viewPanel = ReadyPanel(),
                    executor = Optional.ofNullable(executor)
                )
            )
            val countingPanel = CountingPanel()
            Notifier.getDefault().subscribe(
                ViewSubscriptionFactory.new(
                    viewPanel = countingPanel,
                    executor = Optional.ofNullable(executor)
                )
            )
            Notifier.getDefault().subscribe(
                ViewSubscriptionFactory.new(
                    viewPanel = LaunchedPanel(),
                    executor = Optional.ofNullable(executor)
                )
            )
            Notifier.getDefault().subscribe(
                ViewSubscriptionFactory.new(
                    viewPanel = AbortedPanel(),
                    executor = Optional.ofNullable(executor)
                )
            )


            /**
             * イベントバスに Job サブスクリプションを追加する
             */
            Notifier.getDefault().subscribe(
                DefaultNotifierSubscription(
                    subscriber = countingPanel.subscriber,
                    id = JobId.Counting.id,
                    executor = Optional.ofNullable(executor)
                )
            )

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