package com.github.funczz.kotlin.rocket_launcher.swing.view.ready

import javax.swing.*

class ReadyPanel : JPanel() {

    private val stateLabel: JLabel = JLabel().apply {
        name = "stateLabel"
    }.also {
        it.text = "Ready."
    }

    private val inputField: JTextField = JTextField().apply {
        name = "inputField"
    }

    private val startButton: JButton = JButton().apply {
        name = "startButton"
    }.also {
        it.text = "start"
        it.isEnabled = false
    }

    init {
        inputField.document.addDocumentListener(
            InputFieldDocumentListener(
                inputField = inputField,
                startButton = startButton,
            )
        )

        startButton.addActionListener {
            ReadyCommand.start(text = inputField.text)
        }

        layout = BoxLayout(this, BoxLayout.Y_AXIS).apply {
            add(stateLabel)
            add(inputField)
            add(startButton)
            add(Box.createVerticalBox())
        }
    }

}