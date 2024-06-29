package com.github.funczz.kotlin.rocket_launcher.swing.view.counting

import com.github.funczz.kotlin.rocket_launcher.swing.UiState
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewCommand
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewId
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewPanel
import javax.swing.*

class CountingPanel : JPanel(), ViewPanel {

    override val viewId: ViewId = ViewId.Counting

    override fun render(output: UiState) {
        if (output.viewId != viewId) return

        if (initialCounter != output.samModel.initialCounter)
            initialCounter = output.samModel.initialCounter
        if (currentCounter != output.samModel.currentCounter)
            currentCounter = output.samModel.currentCounter

        if (output.samModel.isTransitioned) {
            ViewCommand.rebuildView(this)
            abortButton.isEnabled = true
        }
    }

    private var initialCounter = 0

    private var currentCounter = 0

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
    }

    init {
        abortButton.addActionListener {
            abortButton.isEnabled = false
            CountingCommand.abort(initialCounter = initialCounter, currentCounter = currentCounter)
        }

        layout = BoxLayout(this, BoxLayout.Y_AXIS).apply {
            add(stateLabel)
            add(counterLabel)
            add(abortButton)
            add(Box.createVerticalBox())
        }
    }

}