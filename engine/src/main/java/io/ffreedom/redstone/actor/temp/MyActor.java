package io.ffreedom.redstone.actor.temp;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class MyActor extends AbstractActor {

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(String.class, str -> {
			System.out.println("matchString -> " + str);
		}).match(int.class, i -> {
			System.out.println();
		}).matchAny(o -> {
			System.out.println("matchAny -> " + o);
		}).build();
	}

	public static void main(String[] args) {

		Props props = Props.create(MyActor.class);
		props.producer();

	}

}
