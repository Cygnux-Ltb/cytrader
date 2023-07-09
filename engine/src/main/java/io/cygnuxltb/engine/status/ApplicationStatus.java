package io.cygnuxltb.engine.status;

import io.mercury.common.collections.ImmutableMaps;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import static io.mercury.common.collections.MutableLists.newFastList;

public final class ApplicationStatus {

    private static final AtomicInteger CurrentStatus = new AtomicInteger(AppStatus.Unknown.getCode());

    private ApplicationStatus() {
    }

    public static AppStatus getStatus() {
        return AppStatus.valueOf(CurrentStatus.get());
    }

    public static void setInitialization() {
        CurrentStatus.set(AppStatus.Initialization.getCode());
    }

    public static void setOnline() {
        CurrentStatus.set(AppStatus.Online.getCode());
    }

    public static void setOffline() {
        CurrentStatus.set(AppStatus.Offline.getCode());
    }

    public static void setUnknown() {
        CurrentStatus.set(AppStatus.Unknown.getCode());
    }

    public enum AppStatus {

        Initialization(0), Online(1), Offline(2), Unknown(-1);

        private final int code;

        private static final ImmutableIntObjectMap<AppStatus> Map = ImmutableMaps.getIntObjectMapFactory()
                .from(newFastList(AppStatus.values()), AppStatus::getCode, status -> status);

        AppStatus(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static AppStatus valueOf(int code) {
            var obj = Map.get(code);
            return obj == null ? Unknown : obj;
        }

    }

    public static void main(String[] args) {

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);

        long zoned2018_1 = ZonedDateTime.of(LocalDate.of(2018, 11, 8),
                        LocalTime.MIN, ZoneId.systemDefault())
                .toInstant().getEpochSecond();

        System.out.println(zoned2018_1);

        long epochMilli = Instant.now().getEpochSecond();

        System.out.println(epochMilli);
        System.out.println(epochMilli - zoned2018_1);

        System.out.println();

    }

}
