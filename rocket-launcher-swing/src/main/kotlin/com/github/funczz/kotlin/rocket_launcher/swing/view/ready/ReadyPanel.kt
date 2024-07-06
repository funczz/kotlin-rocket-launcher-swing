package com.github.funczz.kotlin.rocket_launcher.swing.view.ready

import com.github.funczz.kotlin.notifier.Notifier
import com.github.funczz.kotlin.notifier.property.RONotifierProperty
import com.github.funczz.kotlin.rocket_launcher.swing.UiPresenter
import com.github.funczz.kotlin.rocket_launcher.swing.UiState
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewId
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewPanel
import java.util.*
import java.util.concurrent.Executor
import javax.swing.*

class ReadyPanel(

    notifier: Notifier = Notifier.getDefault(),

    executor: Optional<Executor> = Optional.empty(),


    ) : JPanel(), ViewPanel {

    override val viewId: ViewId = ViewId.Ready

    override fun render(output: UiState) {
        if (output.viewId != viewId) return
        if (output.samModel.isTransitioned) {
            ReadyViewCommand.startView(panel = this)
            startButton.isEnabled = false
            inputField.text = ""
        }
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
            add(inputField)
            add(startButton)
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