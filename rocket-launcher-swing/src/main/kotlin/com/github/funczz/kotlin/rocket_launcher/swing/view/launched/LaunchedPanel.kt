package com.github.funczz.kotlin.rocket_launcher.swing.view.launched

import javax.swing.*

class LaunchedPanel : JPanel() {

    private val stateLabel: JLabel = JLabel().apply {
        name = "stateLabel"
    }.also {
        it.text = "Launched."
    }

    private val readyButton: JButton = JButton().apply {
        name = "readyButton"
    }.also {
        it.text = "ready"
        it.isEnabled = true
    }

    init {
        readyButton.addActionListener {
            LaunchedCommand.ready()
        }

        layout = BoxLayout(this, BoxLayout.Y_AXIS).apply {
            add(stateLabel)
            add(readyButton)
            add(Box.createVerticalBox())
        }
    }

}