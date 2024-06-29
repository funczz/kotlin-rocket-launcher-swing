package com.github.funczz.kotlin.rocket_launcher.swing.view.ready

import javax.swing.JButton
import javax.swing.JTextField
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class InputFieldDocumentListener(

    private val inputField: JTextField,

    private val startButton: JButton,

    ) : DocumentListener {

    override fun insertUpdate(p0: DocumentEvent?) {
        update()
    }

    override fun removeUpdate(p0: DocumentEvent?) {
        update()
    }

    override fun changedUpdate(p0: DocumentEvent?) {
        update()
    }

    private fun update() {
        startButton.isEnabled = when {
            inputField.text.isNotBlank() -> {
                try {
                    inputField.text.toInt()
                    true
                } catch (ex: NumberFormatException) {
                    false
                }
            }

            else -> false
        }
    }

}

