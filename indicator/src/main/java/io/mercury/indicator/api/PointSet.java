package io.mercury.polaris.indicator.api;

import java.util.Optional;

import javax.annotation.CheckForNull;
import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableLists;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.sequence.Serial;

@NotThreadSafe
public final class PointSet<P extends Point<? extends Serial<?>>> {

	private MutableList<P> points;
	private MutableLongObjectMap<P> pointMap;

	private PointSet(Capacity capacity) {
		this.points = MutableLists.newFastList(capacity.size());
		this.pointMap = MutableMaps.newLongObjectHashMap(capacity);
	}

	public static <P extends Point<? extends Serial<?>>> PointSet<P> newEmpty(Capacity capacity) {
		return new PointSet<>(capacity);
	}

	public boolean add(P point) {
		long serialNumber = point.serial().serialNumber();
		if (pointMap.containsKey(serialNumber))
			return false;
		pointMap.put(serialNumber, point);
		return points.add(point);
	}

	public int size() {
		return points.size();
	}

	public P getLast() {
		return points.getLast();
	}

	public P getFirst() {
		return points.getFirst();
	}

	public Optional<P> get(int index) {
		return index < points.size() ? Optional.ofNullable(points.get(index)) : Optional.empty();
	}

	public Optional<P> nextOf(P point) {
		int index = point.index();
		return get(++index);
	}

	@CheckForNull
	public P getPoint(long serialNumber) {
		return pointMap.get(serialNumber);
	}

	public MutableList<P> getPoints() {
		return points;
	}

	public MutableList<P> getSubPoints(int fromIndex, int toIndex) {
		return points.subList(fromIndex, toIndex);
	}

}
