package com.github.funczz.kotlin.rocket_launcher.swing.view.aborted

import com.github.funczz.kotlin.rocket_launcher.swing.UiState
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewId
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewPanel
import javax.swing.*

class AbortedPanel : JPanel(), ViewPanel {

    override val viewId: ViewId = ViewId.Aborted

    override fun render(output: UiState) {
        if (output.viewId != viewId) return
        if (output.samModel.isTransitioned) {
            AbortedViewCommand.startView(panel = this)
            readyButton.isEnabled = true
            repaint()
        }
    }

    private val stateLabel: JLabel = JLabel().apply {
        name = "stateLabel"
    }.also {
        it.text = "Aborted."
    }

    private val readyButton: JButton = JButton().apply {
        name = "readyButton"
    }.also {
        it.text = "ready"
    }

    init {
        readyButton.addActionListener {
            readyButton.isEnabled = false
            AbortedViewCommand.ready()
        }

        layout = BoxLayout(this, BoxLayout.Y_AXIS).apply {
            add(stateLabel)
            add(readyButton)
            add(Box.createVerticalBox())
        }
    }

}