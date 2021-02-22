package io.cygnus.exchange.core.utils;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

import org.agrona.collections.MutableLong;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.cygnus.exchange.core.common.StateHash;

public final class HashingUtils {


	/**
	 * 
	 * @param set
	 * @return
	 */
	public static int stateHash(final BitSet set) {
		return Arrays.hashCode(set.toLongArray());
	}

	/**
	 * 
	 * @param <T>
	 * @param hashMap
	 * @return
	 */
	public static <T extends StateHash> int stateHash(final MutableLongObjectMap<T> map) {
		final MutableLong mutableLong = new MutableLong();
		map.forEachKeyValue((k, v) -> mutableLong.addAndGet(Objects.hash(k, v.stateHash())));
		return Long.hashCode(mutableLong.value);
	}

	/**
	 * 
	 * @param <T>
	 * @param hashMap
	 * @return
	 */
	public static <T extends StateHash> int stateHash(final MutableIntObjectMap<T> set) {
		final MutableLong mutableLong = new MutableLong();
		set.forEachKeyValue((k, v) -> mutableLong.addAndGet(Objects.hash(k, v.stateHash())));
		return Long.hashCode(mutableLong.value);
	}

	/**
	 * 
	 * @param stream
	 * @return
	 */
	public static int stateHashStream(final Stream<? extends StateHash> stream) {
		int h = 0;
		final Iterator<? extends StateHash> iterator = stream.iterator();
		while (iterator.hasNext()) {
			h = h * 31 + iterator.next().stateHash();
		}
		return h;
	}

	/**
	 * Checks if both streams contain same elements in same order
	 *
	 * @param s1 stream 1
	 * @param s2 stream 2
	 * @return true if streams contain same elements in same order
	 */
	public static boolean checkStreamsEqual(final Stream<?> s1, final Stream<?> s2) {
		final Iterator<?> iter1 = s1.iterator(), iter2 = s2.iterator();
		while (iter1.hasNext() && iter2.hasNext()) {
			if (!iter1.next().equals(iter2.next())) {
				return false;
			}
		}
		return !iter1.hasNext() && !iter2.hasNext();
	}

}