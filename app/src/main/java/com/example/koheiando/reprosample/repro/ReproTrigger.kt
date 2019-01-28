package com.example.koheiando.reprosample.repro
import io.repro.android.Repro

class ReproTrigger {
    companion object {
        private var instance: ReproTrigger? = null
        fun getInstance(): ReproTrigger {
            if (instance == null)
                instance =
                        ReproTrigger()
            return instance!!
        }
    }

    // initialize the closure
    var send = send()

    private fun send(): (span: Int, probability: Int, event: ReproEvent?) -> Unit {
        var cntSinceLastTrigger = 0
        return { span, probability, event ->
            cntSinceLastTrigger++
            val percent = if (probability > 100) 100 else if (probability < 0) 0 else probability
            if (cntSinceLastTrigger != 0 && cntSinceLastTrigger % span == 0) {
                if ((1..100).shuffled().last() <= percent) {
                    Repro.track(event?.raw ?:randomEvent().raw)
                    cntSinceLastTrigger = 0
                }
            }
        }
    }

    private fun randomEvent(): ReproEvent {
        return ReproEvent.values().let {
            it[(0 until it.size).shuffled().last()]
        }
    }
}

/**
 * has to be updated whenever you add a new message on Repro GUI
 */
enum class ReproEvent(val raw: String) {
    CAMPAIGN_A("camp_a"),
    CAMPAIGN_B("camp_b"),
    CAMPAIGN_C("camp_c")
}