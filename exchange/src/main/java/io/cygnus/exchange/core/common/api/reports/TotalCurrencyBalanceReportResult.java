package io.cygnus.exchange.core.common.api.reports;


import java.util.stream.Stream;

import org.eclipse.collections.api.map.primitive.MutableIntLongMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntLongHashMap;

import io.cygnus.exchange.core.utils.SerializationUtils;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import net.openhft.chronicle.bytes.BytesIn;
import net.openhft.chronicle.bytes.BytesOut;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public final class TotalCurrencyBalanceReportResult implements ReportResult {

    // currency -> balance
    final private MutableIntLongMap accountBalances;
    final private MutableIntLongMap fees;
    final private MutableIntLongMap adjustments;
    final private MutableIntLongMap suspends;
    final private MutableIntLongMap ordersBalances;

    // symbol -> volume
    // We have to keep shorts and longs separately because for multi-core processing different risk engine instances will give non-matching results.
    // They should match when aggregated though.
    final private MutableIntLongMap openInterestLong;
    final private MutableIntLongMap openInterestShort;

    public static TotalCurrencyBalanceReportResult createEmpty() {
        return new TotalCurrencyBalanceReportResult(
                null, null, null, null, null, null, null);
    }

    public static TotalCurrencyBalanceReportResult ofOrderBalances(final IntLongHashMap currencyBalance) {
        return new TotalCurrencyBalanceReportResult(
                null, null, null, null, currencyBalance, null, null);
    }

    private TotalCurrencyBalanceReportResult(final BytesIn<?> bytesIn) {
        this.accountBalances = SerializationUtils.readNullable(bytesIn, SerializationUtils::readIntLongHashMap);
        this.fees = SerializationUtils.readNullable(bytesIn, SerializationUtils::readIntLongHashMap);
        this.adjustments = SerializationUtils.readNullable(bytesIn, SerializationUtils::readIntLongHashMap);
        this.suspends = SerializationUtils.readNullable(bytesIn, SerializationUtils::readIntLongHashMap);
        this.ordersBalances = SerializationUtils.readNullable(bytesIn, SerializationUtils::readIntLongHashMap);
        this.openInterestLong = SerializationUtils.readNullable(bytesIn, SerializationUtils::readIntLongHashMap);
        this.openInterestShort = SerializationUtils.readNullable(bytesIn, SerializationUtils::readIntLongHashMap);
    }

    @Override
    public void writeMarshallable(@SuppressWarnings("rawtypes") final BytesOut bytes) {
        SerializationUtils.marshallNullable(accountBalances, bytes, SerializationUtils::marshallIntLongHashMap);
        SerializationUtils.marshallNullable(fees, bytes, SerializationUtils::marshallIntLongHashMap);
        SerializationUtils.marshallNullable(adjustments, bytes, SerializationUtils::marshallIntLongHashMap);
        SerializationUtils.marshallNullable(suspends, bytes, SerializationUtils::marshallIntLongHashMap);
        SerializationUtils.marshallNullable(ordersBalances, bytes, SerializationUtils::marshallIntLongHashMap);
        SerializationUtils.marshallNullable(openInterestLong, bytes, SerializationUtils::marshallIntLongHashMap);
        SerializationUtils.marshallNullable(openInterestShort, bytes, SerializationUtils::marshallIntLongHashMap);
    }

    public MutableIntLongMap getGlobalBalancesSum() {
        return SerializationUtils.mergeSum(accountBalances, ordersBalances, fees, adjustments, suspends);
    }

    public MutableIntLongMap getClientsBalancesSum() {
        return SerializationUtils.mergeSum(accountBalances, ordersBalances, suspends);
    }

    public boolean isGlobalBalancesAllZero() {
        return getGlobalBalancesSum().allSatisfy(amount -> amount == 0L);
    }

    public static TotalCurrencyBalanceReportResult merge(final Stream<BytesIn<?>> pieces) {
        return pieces
                .map(TotalCurrencyBalanceReportResult::new)
                .reduce(
                        TotalCurrencyBalanceReportResult.createEmpty(),
                        (a, b) -> new TotalCurrencyBalanceReportResult(
                                SerializationUtils.mergeSum(a.accountBalances, b.accountBalances),
                                SerializationUtils.mergeSum(a.fees, b.fees),
                                SerializationUtils.mergeSum(a.adjustments, b.adjustments),
                                SerializationUtils.mergeSum(a.suspends, b.suspends),
                                SerializationUtils.mergeSum(a.ordersBalances, b.ordersBalances),
                                SerializationUtils.mergeSum(a.openInterestLong, b.openInterestLong),
                                SerializationUtils.mergeSum(a.openInterestShort, b.openInterestShort)));
    }

}
