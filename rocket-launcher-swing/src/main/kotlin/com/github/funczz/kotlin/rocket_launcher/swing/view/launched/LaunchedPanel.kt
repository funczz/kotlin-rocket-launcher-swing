package com.github.funczz.kotlin.rocket_launcher.swing.view.launched

import com.github.funczz.kotlin.rocket_launcher.swing.UiState
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewId
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewPanel
import javax.swing.*

class LaunchedPanel : JPanel(), ViewPanel {

    override val viewId: ViewId = ViewId.Launched

    override fun render(output: UiState) {
        if (output.viewId != viewId) return
        if (output.samModel.isTransitioned) {
            LaunchedViewCommand.rebuildView(panel = this)
            readyButton.isEnabled = true
        }
    }

    private val stateLabel: JLabel = JLabel().apply {
        name = "stateLabel"
    }.also {
        it.text = "Launched."
    }

    private val readyButton: JButton = JButton().apply {
        name = "readyButton"
    }.also {
        it.text = "ready"
    }

    init {
        readyButton.addActionListener {
            readyButton.isEnabled = false
            LaunchedViewCommand.ready()
        }

        layout = BoxLayout(this, BoxLayout.Y_AXIS).apply {
            add(stateLabel)
            add(readyButton)
            add(Box.createVerticalBox())
        }
    }

}