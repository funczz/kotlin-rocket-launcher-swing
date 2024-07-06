package com.github.funczz.kotlin.rocket_launcher.swing.view.ready

import com.github.funczz.kotlin.rocket_launcher.swing.UiState
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewCommand
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewId
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewPanel
import javax.swing.*

class ReadyPanel : JPanel(), ViewPanel {

    override val viewId: ViewId = ViewId.Ready

    override fun render(output: UiState) {
        if (output.viewId != viewId) return
        if (output.samModel.isTransitioned) {
            ViewCommand.rebuildView(panel = this)
            startButton.isEnabled = false
            inputField.text = ""
        }
    }

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
    }

    init {
        inputField.document.addDocumentListener(
            InputFieldDocumentListener(
                inputField = inputField,
                startButton = startButton,
            )
        )

        startButton.addActionListener {
            startButton.isEnabled = false
            ReadyViewCommand.start(text = inputField.text)
        }

        layout = BoxLayout(this, BoxLayout.Y_AXIS).apply {
            add(stateLabel)
            add(inputField)
            add(startButton)
            add(Box.createVerticalBox())
        }
    }

}