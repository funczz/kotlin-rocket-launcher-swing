package com.github.funczz.kotlin.rocket_launcher.swing.job

sealed interface JobId {

    val id: String

    object Counting : JobId {
        override val id: String = "/job/counting"
    }

}