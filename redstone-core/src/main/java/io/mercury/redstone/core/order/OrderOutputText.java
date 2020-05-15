package io.mercury.redstone.core.order;

public interface OrderOutputText {

	String OrderOutputText = "{} :: {}, Order : ordSysId==[{}], ordStatus==[{}], direction==[{}], "
			+ "ordType==[{}], instrument -> {} ordPrice -> {}, ordQty -> {}, ordTimestamps -> {}";

	String ParentOrderOutputText = "{} :: {}, ParentOrder : ordSysId==[{}], ownerOrdId==[{}] ordStatus==[{}], "
			+ "direction==[{}], action==[{}], ordType==[{}], instrument -> {} ordPrice -> {}, ordQty -> {}, "
			+ "ordTimestamps -> {}";

	String ChildOrderOutputText = "{} :: {}, ChildOrder : ordSysId==[{}], ownerOrdId==[{}] ordStatus==[{}], "
			+ "direction==[{}], action==[{}], ordType==[{}], instrument -> {} ordPrice -> {}, ordQty -> {}, "
			+ "ordTimestamps -> {} trdList -> {}";

}
