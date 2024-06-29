package com.github.funczz.kotlin.rocket_launcher.swing.view.launched

import com.github.funczz.kotlin.rocket_launcher.swing.MainJFrame
import com.github.funczz.kotlin.rocket_launcher.swing.invokeAndWait
import com.github.funczz.kotlin.rocket_launcher.swing.rebuildContentPane
import com.github.funczz.kotlin.rocket_launcher.swing.view.ready.ReadyPanel

object LaunchedCommand {

    fun ready() {
        MainJFrame
            .getInstance()
            .invokeAndWait {
                it.rebuildContentPane(ReadyPanel()) {
                    it.pack()
                    it.isVisible = true
                }
            }
    }

}