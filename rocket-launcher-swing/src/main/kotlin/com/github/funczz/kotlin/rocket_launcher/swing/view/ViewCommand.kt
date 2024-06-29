package com.github.funczz.kotlin.rocket_launcher.swing.view

import com.github.funczz.kotlin.rocket_launcher.swing.MainJFrame
import com.github.funczz.kotlin.rocket_launcher.swing.invokeAndWait
import com.github.funczz.kotlin.rocket_launcher.swing.rebuildContentPane
import javax.swing.JPanel

object ViewCommand {

    fun rebuildView(panel: JPanel) {
        MainJFrame.getInstance().invokeAndWait {
            it.rebuildContentPane(panel) {
                it.pack()
                it.isVisible = true
            }
        }
    }

}