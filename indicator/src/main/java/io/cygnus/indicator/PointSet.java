package io.apollo.indicator;

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

	private MutableList<P> pointList;
	private MutableLongObjectMap<P> pointMap;

	private PointSet(Capacity capacity) {
		this.pointList = MutableLists.newFastList(capacity.size());
		this.pointMap = MutableMaps.newLongObjectHashMap(capacity);
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
		long serialId = point.serial().serialId();
		if (pointMap.containsKey(serialId))
			return false;
		pointMap.put(serialId, point);
		return pointList.add(point);
	}

	/**
	 * 
	 * @return
	 */
	public int size() {
		return pointList.size();
	}

	/**
	 * 
	 * @return
	 */
	public P getLast() {
		return pointList.getLast();
	}

	/**
	 * 
	 * @return
	 */
	public P getFirst() {
		return pointList.getFirst();
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public Optional<P> get(int index) {
		return index < pointList.size() ? Optional.ofNullable(pointList.get(index)) : Optional.empty();
	}

	/**
	 * TODO 需要修改
	 * 
	 * @param point
	 * @return
	 */
	public Optional<P> nextOf(P point) {
		int index = point.index();
		return get(++index);
	}

	/**
	 * 
	 * @param serialId
	 * @return
	 */
	@CheckForNull
	public P getPoint(long serialId) {
		return pointMap.get(serialId);
	}

	/**
	 * 
	 * @return
	 */
	public MutableList<P> getPointList() {
		return pointList;
	}

	/**
	 * 
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 */
	public MutableList<P> getSubPointList(int fromIndex, int toIndex) {
		return pointList.subList(fromIndex, toIndex);
	}

}
