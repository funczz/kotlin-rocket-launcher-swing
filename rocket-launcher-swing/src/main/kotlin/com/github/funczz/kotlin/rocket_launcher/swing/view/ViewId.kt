package com.github.funczz.kotlin.rocket_launcher.swing.view

sealed interface ViewId {

    val id: String

    object Ready : ViewId {
        override val id: String = "/view/ready"
    }

    object Counting : ViewId {
        override val id: String = "/view/counting"
    }

    object Launched : ViewId {
        override val id: String = "/view/launched"
    }

    object Aborted : ViewId {
        override val id: String = "/view/aborted"
    }

}