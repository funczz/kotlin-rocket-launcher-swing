package com.github.funczz.kotlin.rocket_launcher.swing

import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamState
import com.github.funczz.kotlin.rocket_launcher.swing.view.ViewId
import com.github.funczz.kotlin.sam.SamStateRepresentation

object UiRepresentation : SamStateRepresentation<RocketLauncherSamModel, UiState> {

    override fun representation(model: RocketLauncherSamModel): UiState {
        val id = when {
            RocketLauncherSamState.isReady(model = model) -> ViewId.Ready
            RocketLauncherSamState.isCounting(model = model) -> ViewId.Counting
            RocketLauncherSamState.isLaunched(model = model) -> ViewId.Launched
            RocketLauncherSamState.isAborted(model = model) -> ViewId.Aborted
            else -> throw IllegalStateException("model=$model")
        }
        return UiState(samModel = model, viewId = id)
    }

}