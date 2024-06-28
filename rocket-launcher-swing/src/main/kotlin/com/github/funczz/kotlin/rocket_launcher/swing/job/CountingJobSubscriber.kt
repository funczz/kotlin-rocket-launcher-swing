package com.github.funczz.kotlin.rocket_launcher.swing.job

import java.util.*
import java.util.concurrent.Flow
import java.util.concurrent.TimeUnit

open class CountingJobSubscriber : WhileJobSubscriber() {

    open val timeout: Long = 1000L

    val counter: Int
        get() = _counter

    val isStarted: Boolean
        get() = _isStarted

    val isBreak: Boolean
        get() = _isBreak

    val error: Optional<Throwable>
        get() = _error

    private var _counter = 0

    private var _isStarted = false

    private var _isBreak = false

    private var _error = Optional.empty<Throwable>()

    @Suppress("KotlinConstantConditions")
    fun breakNow() {
        if (!_isStarted) {
            throw IllegalStateException("this job has already been stopped. isStarted=$_isStarted")
        }
        _isBreak = true
    }

    @Suppress("KotlinConstantConditions")
    override fun onSetUp(item: Any) {
        require(item is Int)
        if (item < 0) {
            throw IllegalArgumentException("item is less than zero. item=$item")
        }
        if (_isStarted) {
            throw IllegalStateException("this job is already started. isStarted=$_isStarted")
        }
        if (_isBreak) {
            throw IllegalStateException("this job is already started. isBreak=$_isBreak")
        }
        _counter = item
        _isStarted = true
    }

    override fun onIsContinue(): Boolean {
        return !_isBreak && _counter > 0
    }

    override fun onDoWhile() {
        if (_error.isPresent) {
            _isBreak = true
            return
        }
        TimeUnit.MILLISECONDS.sleep(timeout)
        _counter -= 1
    }

    override fun onTearDown() {
        _isStarted = false
        _isBreak = false
    }

    override fun onSubscribe(subscription: Flow.Subscription?) {
    }

    override fun onError(throwable: Throwable?) {
        _error = Optional.ofNullable(throwable)
    }

    override fun onComplete() {
    }

}