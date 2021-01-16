package io.cygnus.exchange.core.common.api.reports;

import io.cygnus.exchange.core.processors.MatchingEngineRouter;
import io.cygnus.exchange.core.processors.RiskEngine;
import io.cygnus.exchange.core.utils.HashingUtils;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.openhft.chronicle.bytes.BytesIn;
import net.openhft.chronicle.bytes.BytesOut;

import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Slf4j
public final class StateHashReportQuery implements ReportQuery<StateHashReportResult> {

	public StateHashReportQuery(BytesIn<?> bytesIn) {
		// do nothing
	}

	@Override
	public int getReportTypeCode() {
		return ReportType.STATE_HASH.getCode();
	}

	@Override
	public StateHashReportResult createResult(Stream<BytesIn<?>> sections) {
		return StateHashReportResult.merge(sections);
	}

	@Override
	public Optional<StateHashReportResult> process(MatchingEngineRouter matchingEngine) {
		
		log.info("Process MatchingEngineRouter...");
		
		final SortedMap<StateHashReportResult.SubmoduleKey, Integer> hashCodes = new TreeMap<>();

		final int moduleId = matchingEngine.getShardId();

		hashCodes.put(
				StateHashReportResult.createKey(moduleId,
						StateHashReportResult.SubmoduleType.MATCHING_BINARY_CMD_PROCESSOR),
				matchingEngine.getBinaryCommandsProcessor().stateHash());

		hashCodes.put(
				StateHashReportResult.createKey(moduleId, StateHashReportResult.SubmoduleType.MATCHING_ORDER_BOOKS),
				HashingUtils.stateHash(matchingEngine.getOrderBooks()));

		hashCodes.put(
				StateHashReportResult.createKey(moduleId, StateHashReportResult.SubmoduleType.MATCHING_SHARD_MASK),
				Long.hashCode(matchingEngine.getShardMask()));

		return Optional.of(new StateHashReportResult(hashCodes));
	}

	@Override
	public Optional<StateHashReportResult> process(RiskEngine riskEngine) {

		log.info("Process RiskEngine...");
		
		final SortedMap<StateHashReportResult.SubmoduleKey, Integer> hashCodes = new TreeMap<>();

		final int moduleId = riskEngine.getShardId();

		hashCodes.put(
				StateHashReportResult.createKey(moduleId,
						StateHashReportResult.SubmoduleType.RISK_SYMBOL_SPEC_PROVIDER),
				riskEngine.getBinaryCommandsProcessor().stateHash());

		hashCodes.put(
				StateHashReportResult.createKey(moduleId,
						StateHashReportResult.SubmoduleType.RISK_USER_PROFILE_SERVICE),
				riskEngine.getUserProfileService().stateHash());

		hashCodes.put(
				StateHashReportResult.createKey(moduleId,
						StateHashReportResult.SubmoduleType.RISK_BINARY_CMD_PROCESSOR),
				riskEngine.getBinaryCommandsProcessor().stateHash());

		hashCodes.put(
				StateHashReportResult.createKey(moduleId, StateHashReportResult.SubmoduleType.RISK_LAST_PRICE_CACHE),
				HashingUtils.stateHash(riskEngine.getLastPriceCache()));

		hashCodes.put(StateHashReportResult.createKey(moduleId, StateHashReportResult.SubmoduleType.RISK_FEES),
				riskEngine.getFees().hashCode());

		hashCodes.put(StateHashReportResult.createKey(moduleId, StateHashReportResult.SubmoduleType.RISK_ADJUSTMENTS),
				riskEngine.getAdjustments().hashCode());

		hashCodes.put(StateHashReportResult.createKey(moduleId, StateHashReportResult.SubmoduleType.RISK_SUSPENDS),
				riskEngine.getSuspends().hashCode());

		hashCodes.put(StateHashReportResult.createKey(moduleId, StateHashReportResult.SubmoduleType.RISK_SHARD_MASK),
				Long.hashCode(riskEngine.getShardMask()));

		return Optional.of(new StateHashReportResult(hashCodes));
	}

	@Override
	public void writeMarshallable(@SuppressWarnings("rawtypes") BytesOut bytes) {
		// do nothing
	}
}
