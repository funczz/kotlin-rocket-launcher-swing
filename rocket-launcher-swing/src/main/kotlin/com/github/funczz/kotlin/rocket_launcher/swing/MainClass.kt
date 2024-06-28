package com.github.funczz.kotlin.rocket_launcher.swing

import javax.swing.JLabel
import javax.swing.JPanel

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            MainJFrame.getInstance().also {
                it.invokeAndWait {
                    it.rebuildContentPane(JPanel().add(JLabel().also { it.text = "hello world." })) {
                        it.pack()
                        it.isVisible = true
                    }
                }
            }
        }
    }
}