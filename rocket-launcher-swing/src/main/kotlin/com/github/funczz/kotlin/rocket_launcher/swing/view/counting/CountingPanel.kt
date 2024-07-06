package com.github.funczz.kotlin.rocket_launcher.swing.view.counting

import com.github.funczz.kotlin.notifier.Notifier
import com.github.funczz.kotlin.notifier.property.RONotifierProperty
import com.github.funczz.kotlin.notifier.property.ReadOnlyNotifierProperty
import com.github.funczz.kotlin.rocket_launcher.swing.UiPresenter
import com.github.funczz.kotlin.rocket_launcher.swing.UiState
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewId
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewPanel
import java.util.*
import javax.swing.*

class CountingPanel(

    notifier: Notifier = Notifier.getDefault()

) : JPanel(), ViewPanel {

    override val viewId: ViewId = ViewId.Counting

    override fun render(output: UiState) {
        if (output.viewId != viewId) return
        if (output.samModel.isTransitioned) {
            CountingViewCommand.startView(panel = this)
            abortButton.isEnabled = true
            CountingViewCommand.start(initialCounter = initialCounter.getValue())
        }
    }

    val subscriber = CountingPanelCountingJobSubscriber()

    private var initialCounter: ReadOnlyNotifierProperty<Int> = RONotifierProperty(
        initialValue = 0,
        name = "%s%s".format(
            UiPresenter.INITIAL_COUNTER_NOTIFIER_NAME,
            ViewId.Counting.id)
        ,
        notifier = notifier,
        executor = Optional.empty()
    )

    private var currentCounter: ReadOnlyNotifierProperty<Int> = RONotifierProperty(
        initialValue = 0,
        name = "%s%s".format(
            UiPresenter.CURRENT_COUNTER_NOTIFIER_NAME,
            ViewId.Counting.id
        ),
        notifier = notifier,
        executor = Optional.empty()
    ).apply {
        subscriber.onNext { counterLabel.text = it.toString() }
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
            if (subscriber.isStarted) subscriber.breakNow()
            CountingViewCommand.abort(
                initialCounter = initialCounter.getValue(),
                currentCounter = currentCounter.getValue(),
            )
        }

        layout = BoxLayout(this, BoxLayout.Y_AXIS).apply {
            add(counterLabel)
            add(abortButton)
            add(Box.createVerticalBox())
        }
    }

}