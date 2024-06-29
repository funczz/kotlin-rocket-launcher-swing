package com.github.funczz.kotlin.rocket_launcher.swing

import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewId

data class UiState(

    val samModel: RocketLauncherSamModel = RocketLauncherSamModel(),

    val viewId: ViewId,

    )
