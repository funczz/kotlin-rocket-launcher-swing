package com.github.funczz.kotlin.rocket_launcher.swing.view

import com.github.funczz.kotlin.rocket_launcher.swing.UiState

interface ViewPanel {

    val viewId: ViewId

    fun render(output: UiState)

}