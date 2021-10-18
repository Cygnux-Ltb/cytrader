package io.cygnus.indicator;

import java.util.Optional;

import javax.annotation.CheckForNull;
import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableLists;
import io.mercury.common.collections.MutableMaps;

@NotThreadSafe
public final class PointSet<P extends Point<?>> {

	private MutableList<P> list;
	private MutableLongObjectMap<P> map;

	private PointSet(Capacity capacity) {
		this.list = MutableLists.newFastList(capacity.value());
		this.map = MutableMaps.newLongObjectHashMap(capacity);
	}

	/**
	 * 
	 * @param <P>
	 * @param capacity
	 * @return
	 */
	public static <P extends Point<?>> PointSet<P> newEmpty(Capacity capacity) {
		return new PointSet<>(capacity);
	}

	/**
	 * 
	 * @param point
	 * @return
	 */
	public boolean add(P point) {
		long serialId = point.getSerial().getSerialId();
		if (map.containsKey(serialId))
			return false;
		map.put(serialId, point);
		return list.add(point);
	}

	/**
	 * 
	 * @return
	 */
	public int size() {
		return list.size();
	}

	/**
	 * 
	 * @return
	 */
	public P getLast() {
		return list.getLast();
	}

	/**
	 * 
	 * @return
	 */
	public P getFirst() {
		return list.getFirst();
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public Optional<P> get(int index) {
		return index < list.size() ? Optional.ofNullable(list.get(index)) : Optional.empty();
	}

	/**
	 * TODO 需要修改
	 * 
	 * @param point
	 * @return
	 */
	public Optional<P> nextOf(P point) {
		int index = point.getIndex();
		return get(++index);
	}

	/**
	 * 
	 * @param serialId
	 * @return
	 */
	@CheckForNull
	public P getPoint(long serialId) {
		return map.get(serialId);
	}

	/**
	 * 
	 * @return
	 */
	public MutableList<P> getPointList() {
		return list;
	}

	/**
	 * 
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 */
	public MutableList<P> getSubPointList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

}
