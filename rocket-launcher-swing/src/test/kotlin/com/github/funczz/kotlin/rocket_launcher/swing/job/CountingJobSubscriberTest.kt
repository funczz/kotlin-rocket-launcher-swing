package com.github.funczz.kotlin.rocket_launcher.swing.job

import com.github.funczz.kotlin.notifier.DefaultNotifierSubscription
import com.github.funczz.kotlin.notifier.Notifier
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.Flow
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

@Suppress("NonAsciiCharacters")
class CountingJobSubscriberTest {

    @Test
    fun `0 以上の値を post 後、カウンターが 0 になり処理が完了する`() {
        notifier.post(item = 3, name = name.toRegex())
        TimeUnit.MILLISECONDS.sleep(500L)
        assertEquals(0, subscriber.counter)
    }

    @Test
    fun `0 以上の値を post して処理が完了後、再度 0 以上の値を post する`() {
        notifier.post(item = 3, name = name.toRegex())
        TimeUnit.MILLISECONDS.sleep(500L)
        assertEquals(0, subscriber.counter)
        notifier.post(item = 3, name = name.toRegex())
        TimeUnit.MILLISECONDS.sleep(500L)
        assertEquals(0, subscriber.counter)
    }

    @Test
    fun `0 以上の値を post 後にキャンセルすると、カウンターが 0 にならずに処理が完了する`() {
        notifier.post(item = 3, name = name.toRegex())
        TimeUnit.MILLISECONDS.sleep(100L)
        subscriber.breakNow()
        TimeUnit.MILLISECONDS.sleep(400L)
        assertNotEquals(0, subscriber.counter)
    }

    @Test
    fun `0 未満の値を post すると、サブスクライバの onError が呼び出される`() {
        notifier.post(item = -1, name = name.toRegex())
        TimeUnit.MILLISECONDS.sleep(100L)
        assertEquals(IllegalArgumentException::class.java, subscriber.error.get()::class.java)
    }

    @Test
    fun `二重に post すると、サブスクライバの onError が呼び出される`() {
        notifier.post(item = 3, name = name.toRegex())
        TimeUnit.MILLISECONDS.sleep(50L)
        notifier.post(item = 3, name = name.toRegex())
        TimeUnit.MILLISECONDS.sleep(500L)
        assertNotEquals(0, subscriber.counter)
        assertEquals(IllegalStateException::class.java, subscriber.error.get()::class.java)
        assertEquals(0, notifier.subscriptions.size)
    }

    @BeforeEach
    fun beforeEach() {
        notifier = Notifier()
        executor = Executors.newCachedThreadPool() as ThreadPoolExecutor
        subscriber = StdoutCountingJobSubscriber()
        notifier.subscribe(
            subscription = DefaultNotifierSubscription(
                subscriber = subscriber,
                name = name,
                executor = Optional.ofNullable(executor)
            ),
            executor = executor
        )
        TimeUnit.MILLISECONDS.sleep(500L)
    }

    @AfterEach
    fun afterEach() {
        notifier.cancelAll()
        executor.shutdownNow()
    }

    private lateinit var notifier: Notifier

    private lateinit var executor: ThreadPoolExecutor

    private val name = "job/counting"

    private lateinit var subscriber: StdoutCountingJobSubscriber

    private class StdoutCountingJobSubscriber : CountingJobSubscriber() {

        override val timeout: Long = 100L

        override fun onSetUp(item: Any) {
            output(message = "SetUp")
            super.onSetUp(item)
        }

        override fun onIsContinue(): Boolean {
            output(message = "Is continue: counter=%d, isStarted=%b, isBreak=%b".format(counter, isStarted, isBreak))
            return super.onIsContinue()
        }

        override fun onDoWhile() {
            output(message = "While: [%d]".format(counter))
            super.onDoWhile()
            output(message = "While: [%d:result]".format(counter))
        }

        override fun onTearDown() {
            output(message = "TearDown")
            super.onTearDown()
        }

        override fun onSubscribe(subscription: Flow.Subscription?) {
            output(message = "Subscribe")
            super.onSubscribe(subscription)
        }

        override fun onError(throwable: Throwable?) {
            output(message = "Error: throwable=$throwable")
            super.onError(throwable)
        }

        override fun onComplete() {
            output(message = "Complete")
            super.onComplete()
        }

        private fun output(message: String) {
            println(message = message)
        }

    }
}