package com.github.funczz.kotlin.rocket_launcher.swing

import com.github.funczz.kotlin.notifier.Notifier

object UiPresenter {

    fun render(output: UiState) {
        Notifier.getDefault().post(item = output, name = output.viewId.id.toRegex())
    }
}