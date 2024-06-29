package com.github.funczz.kotlin.rocket_launcher.swing

import java.awt.Component
import java.awt.EventQueue
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame
import javax.swing.SwingUtilities
import kotlin.system.exitProcess

class MainJFrame(title: String = "") : JFrame(title) {

    private var _windowClosing: () -> Unit = {}

    fun windowClosing(function: () -> Unit) {
        _windowClosing = function
    }

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                _windowClosing()
                exitProcess(0) //System.exit(0)
            }
        })
        setLocationRelativeTo(null)
        pack()
        isVisible = true
    }

    companion object {

        private var instance: MainJFrame? = null

        @JvmStatic
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MainJFrame().also {
                instance = it
            }
        }

    }
}

/**
 * jFrame.invokeLater {
 *     jFrame.rebuildContentPane(fooPanel) {
 *         it.pack()
 *         it.isVisible = true
 *     }
 * }
 */
@Suppress("Unused")
fun JFrame.invokeLater(function: (JFrame) -> Unit) {
    SwingUtilities.invokeLater {
        function(this)
        this.revalidate()
    }
}

/**
 * jFrame.invokeAndWait {
 *     jFrame.rebuildContentPane(fooPanel) {
 *         it.pack()
 *         it.isVisible = true
 *     }
 * }
 */
@Suppress("Unused")
fun JFrame.invokeAndWait(function: (JFrame) -> Unit) {
    when (EventQueue.isDispatchThread()) {
        true -> {
            function(this)
            this.revalidate()
        }

        else -> EventQueue.invokeAndWait {
            function(this)
            this.revalidate()
        }
    }
}

@Suppress("Unused")
fun JFrame.rebuildContentPane(component: Component, function: (JFrame) -> Unit) {
    this.contentPane.also {
        it.removeAll()
        it.add(component)
        function(this)
    }
}

@Suppress("Unused")
fun JFrame.rebuildContentPane(component: Component, constraints: Any, function: (JFrame) -> Unit) {
    this.contentPane.also {
        it.removeAll()
        it.add(component, constraints)
        function(this)
    }
}