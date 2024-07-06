package com.github.funczz.kotlin.rocket_launcher.swing.view.aborted

import com.github.funczz.kotlin.notifier.Notifier
import com.github.funczz.kotlin.notifier.property.RONotifierProperty
import com.github.funczz.kotlin.rocket_launcher.swing.UiPresenter
import com.github.funczz.kotlin.rocket_launcher.swing.UiState
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewId
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewPanel
import java.util.*
import java.util.concurrent.Executor
import javax.swing.*

class AbortedPanel(

    notifier: Notifier = Notifier.getDefault(),

    executor: Optional<Executor> = Optional.empty(),

    ) : JPanel(), ViewPanel {

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

        /**
         * イベントバスに画面遷移サブスクリプションを追加する
         */
        RONotifierProperty(
            initialValue = UiState(viewId = ViewId.Ready),
            name = "%s%s".format(
                UiPresenter.UI_STATE_NOTIFIER_NAME,
                viewId.id
            ),
            notifier = notifier,
            executor = executor,
        ).subscriber.onNext { render(output = it) }
    }

}