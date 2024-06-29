package com.github.funczz.kotlin.rocket_launcher.swing.view.counting

import com.github.funczz.kotlin.rocket_launcher.swing.MainJFrame
import com.github.funczz.kotlin.rocket_launcher.swing.invokeAndWait
import com.github.funczz.kotlin.rocket_launcher.swing.rebuildContentPane
import com.github.funczz.kotlin.rocket_launcher.swing.view.aborted.AbortedPanel

object CountingCommand {

    fun abort() {
        MainJFrame
            .getInstance()
            .invokeAndWait {
                it.rebuildContentPane(AbortedPanel()) {
                    it.pack()
                    it.isVisible = true
                }
            }
    }

}