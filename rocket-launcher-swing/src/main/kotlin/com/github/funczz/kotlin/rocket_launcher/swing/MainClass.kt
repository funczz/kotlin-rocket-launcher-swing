package com.github.funczz.kotlin.rocket_launcher.swing

import com.github.funczz.kotlin.rocket_launcher.swing.view.ready.ReadyPanel

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            MainJFrame.getInstance().also {
                it.invokeAndWait {
                    it.rebuildContentPane(ReadyPanel()) {
                        it.pack()
                        it.isVisible = true
                    }
                }
            }
        }
    }
}