package io.cygnus.exchange.core.common.api.reports;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.slf4j.Logger;

import io.cygnus.exchange.core.common.Order;
import io.cygnus.exchange.core.common.UserProfile;
import io.cygnus.exchange.core.processors.MatchingEngineRouter;
import io.cygnus.exchange.core.processors.RiskEngine;
import io.mercury.common.log.CommonLoggerFactory;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.openhft.chronicle.bytes.BytesIn;
import net.openhft.chronicle.bytes.BytesOut;

@ToString
@EqualsAndHashCode
public final class SingleUserReportQuery implements ReportQuery<SingleUserReportResult> {

	private static final Logger log = CommonLoggerFactory.getLogger(SingleUserReportQuery.class);

	public final long uid;

	public SingleUserReportQuery(long uid) {
		this.uid = uid;
	}

	public SingleUserReportQuery(final BytesIn<?> bytesIn) {
		this.uid = bytesIn.readLong();
	}

	@Override
	public int getReportTypeCode() {
		return ReportType.SINGLE_USER_REPORT.getCode();
	}

	@Override
	public SingleUserReportResult createResult(final Stream<BytesIn<?>> sections) {
		return SingleUserReportResult.merge(sections);
	}

	@Override
	public Optional<SingleUserReportResult> process(final MatchingEngineRouter matchingEngine) {

		log.info("Process MatchingEngineRouter...");

		final IntObjectHashMap<List<Order>> orders = new IntObjectHashMap<>();

		matchingEngine.getOrderBooks().forEach(orderBook -> {
			final List<Order> userOrders = orderBook.findUserOrders(this.uid);
			// dont put empty results, so that the report result merge procedure would be
			// simple
			if (!userOrders.isEmpty()) {
				orders.put(orderBook.getSymbolSpec().symbolId, userOrders);
			}
		});

		// log.debug("ME{}: orders: {}", matchingEngine.getShardId(), orders);
		return Optional.of(SingleUserReportResult.createFromMatchingEngine(uid, orders));
	}

	@Override
	public Optional<SingleUserReportResult> process(final RiskEngine riskEngine) {

		log.info("Process RiskEngine...");

		if (!riskEngine.uidForThisHandler(this.uid)) {
			return Optional.empty();
		}

		final UserProfile userProfile = riskEngine.getUserProfileService().getUserProfile(this.uid);

		if (userProfile != null) {
			final IntObjectHashMap<SingleUserReportResult.Position> positions = new IntObjectHashMap<>(
					userProfile.positions.size());
			userProfile.positions.forEachKeyValue((symbol, pos) -> positions.put(symbol,
					new SingleUserReportResult.Position(pos.currency, pos.direction, pos.openVolume, pos.openPriceSum,
							pos.profit, pos.pendingSellSize, pos.pendingBuySize)));

			return Optional.of(SingleUserReportResult.createFromRiskEngineFound(uid, userProfile.userStatus,
					userProfile.accounts, positions));
		} else {
			// not found
			return Optional.of(SingleUserReportResult.createFromRiskEngineNotFound(uid));
		}
	}

	@Override
	public void writeMarshallable(@SuppressWarnings("rawtypes") final BytesOut bytes) {
		bytes.writeLong(uid);
	}
}
