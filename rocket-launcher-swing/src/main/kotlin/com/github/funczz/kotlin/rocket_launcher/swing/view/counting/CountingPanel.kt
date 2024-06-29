package com.github.funczz.kotlin.rocket_launcher.swing.view.counting

import javax.swing.*

class CountingPanel : JPanel() {

    private val stateLabel: JLabel = JLabel().apply {
        name = "stateLabel"
    }.also {
        it.text = "Counting."
    }

    private val counterLabel: JLabel = JLabel().apply {
        name = "counterLabel"
    }.also {
        it.text = "N/A"
    }

    private val abortButton: JButton = JButton().apply {
        name = "abortButton"
    }.also {
        it.text = "abort"
        it.isEnabled = true
    }

    init {
        abortButton.addActionListener {
            CountingCommand.abort()
        }

        layout = BoxLayout(this, BoxLayout.Y_AXIS).apply {
            add(stateLabel)
            add(counterLabel)
            add(abortButton)
            add(Box.createVerticalBox())
        }
    }

}