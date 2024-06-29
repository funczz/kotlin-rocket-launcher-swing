package com.github.funczz.kotlin.rocket_launcher.swing.view.ready

import com.github.funczz.kotlin.rocket_launcher.swing.MainJFrame
import com.github.funczz.kotlin.rocket_launcher.swing.invokeAndWait
import com.github.funczz.kotlin.rocket_launcher.swing.rebuildContentPane
import com.github.funczz.kotlin.rocket_launcher.swing.view.counting.CountingPanel

object ReadyCommand {

    fun start(text: String) {
        MainJFrame
            .getInstance()
            .invokeAndWait {
                it.rebuildContentPane(CountingPanel()) {
                    it.pack()
                    it.isVisible = true
                }
            }
    }

}