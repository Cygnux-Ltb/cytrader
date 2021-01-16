package io.cygnus.exchange.core.common.api.reports;

import net.openhft.chronicle.bytes.BytesIn;
import net.openhft.chronicle.bytes.WriteBytesMarshallable;

import java.util.Optional;
import java.util.stream.Stream;

import io.cygnus.exchange.core.processors.MatchingEngineRouter;
import io.cygnus.exchange.core.processors.RiskEngine;

/**
 * Reports query interface.
 *
 * @param <T> corresponding result type
 */
public interface ReportQuery<T extends ReportResult> extends WriteBytesMarshallable {

    /**
     * @return report type code (integer)
     */
    int getReportTypeCode();

    /**
     * @return report map-reduce constructor
     */
    T createResult(Stream<BytesIn<?>> sections);

    /**
     * Report main logic.
     * This method is executed by matcher engine thread.
     *
     * @param matchingEngine matcher engine instance
     * @return custom result
     */
    Optional<T> process(MatchingEngineRouter matchingEngine);

    /**
     * Report main logic
     * This method is executed by risk engine thread.
     *
     * @param riskEngine risk engine instance.
     * @return custom result
     */
    Optional<T> process(RiskEngine riskEngine);
}
