package de.eventsourcingbook.cart.application

import org.springframework.stereotype.Component

interface CartSessionIdProvider {
    fun provideCartSession(): String?

    companion object {
        const val SESSION_ID = "cartSessionId"
    }
}

@Component
class ThreadLocalCartSessionIdProvider : CartSessionIdProvider {
    private val threadLocal = ThreadLocal<String>()

    override fun provideCartSession(): String? = threadLocal.get()

    fun setSessionId(sessionId: String) {
        this.threadLocal.set(sessionId)
    }

    fun reset() {
        threadLocal.remove()
    }
}
