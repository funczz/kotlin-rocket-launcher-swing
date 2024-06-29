package com.github.funczz.kotlin.rocket_launcher.swing.view.aborted

import javax.swing.*

class AbortedPanel : JPanel() {

    private val stateLabel: JLabel = JLabel().apply {
        name = "stateLabel"
    }.also {
        it.text = "Aborted."
    }

    private val readyButton: JButton = JButton().apply {
        name = "readyButton"
    }.also {
        it.text = "ready"
        it.isEnabled = true
    }

    init {
        readyButton.addActionListener {
            AbortedCommand.ready()
        }

        layout = BoxLayout(this, BoxLayout.Y_AXIS).apply {
            add(stateLabel)
            add(readyButton)
            add(Box.createVerticalBox())
        }
    }

}