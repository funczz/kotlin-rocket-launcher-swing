package com.github.funczz.kotlin.rocket_launcher.swing

import com.github.funczz.kotlin.notifier.Notifier
import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewSubscriptionFactory
import com.github.funczz.kotlin.rocket_launcher.swing.view.aborted.AbortedPanel
import com.github.funczz.kotlin.rocket_launcher.swing.view.counting.CountingPanel
import com.github.funczz.kotlin.rocket_launcher.swing.view.launched.LaunchedPanel
import com.github.funczz.kotlin.rocket_launcher.swing.view.ready.ReadyPanel
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val executor = Executors.newCachedThreadPool() as ThreadPoolExecutor

            MainJFrame.getInstance().windowClosing {
                Notifier.getDefault().unsubscribeAll()
                executor.shutdownNow()
            }

            Notifier.getDefault().subscribe(
                ViewSubscriptionFactory.new(
                    viewPanel = ReadyPanel(),
                    executor = Optional.ofNullable(executor)
                )
            )
            Notifier.getDefault().subscribe(
                ViewSubscriptionFactory.new(
                    viewPanel = CountingPanel(),
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