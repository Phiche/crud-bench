package ru.phicher.crudbench.utils

import io.micrometer.core.instrument.Measurement
import io.micrometer.core.instrument.Meter
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Statistic
import mu.KotlinLogging
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.BiFunction
import java.util.function.Consumer


@Component
class StartUpHandler(
        private val meterRegistry: MeterRegistry
) {
    companion object {
        private val METRICS = listOf(
                "jvm.memory.used",
                "jvm.classes.loaded",
                "jvm.threads.live",
                "process.cpu.usage")

        private const val METRIC_MSG_FORMAT = "Startup Metric >> {}={}"
        private val logger = KotlinLogging.logger {}
    }

    @EventListener
    fun getAndLogStartupMetrics(
            event: ApplicationReadyEvent?) {
        METRICS.forEach(this::getAndLogActuatorMetric)
    }

    private fun getAndLogActuatorMetric(metric: String) {
        val meter = meterRegistry.find(metric).meter()
        val stats = getSamples(meter)
        logger.info(METRIC_MSG_FORMAT, metric, stats[Statistic.VALUE]!!.toLong())
    }

    private fun getSamples(meter: Meter?): Map<Statistic, Double> {
        val samples: MutableMap<Statistic, Double> = LinkedHashMap()
        mergeMeasurements(samples, meter)
        return samples
    }

    private fun mergeMeasurements(samples: MutableMap<Statistic, Double>, meter: Meter?) {
        meter!!.measure().forEach(Consumer { measurement: Measurement ->
            samples.merge(measurement.statistic,
                    measurement.value, mergeFunction(measurement.statistic))
        })
    }

    private fun mergeFunction(statistic: Statistic): BiFunction<Double, Double, Double?> {
        return if (Statistic.MAX == statistic) BiFunction { a: Double?, b: Double? -> java.lang.Double.max(a!!, b!!) } else BiFunction { a: Double?, b: Double? -> java.lang.Double.sum(a!!, b!!) }
    }
}