package Chapter29;

import java.util.LinkedList;
import java.util.Queue;

/**
 ********************************************************************** 
 * @Title: FooEventDrivenExample.java
 * @Description: Evnet Driven 简单的框架
 * @author ankie
 * @date 2018年12月22日
 * @version 1.0
 **********************************************************************
 */
public class FooEventDrivenExample {

	public static void handleEventA(final Event1 e) {
		System.out.println(e.getData().toLowerCase());
	}

	public static void handleEventB(final Event1 e) {
		System.out.println(e.getData().toUpperCase());
	}

	public static void main(final String[] args) {
		final Queue<Event1> events = new LinkedList<>();
		events.add(new Event1("A", "Hello"));
		events.add(new Event1("A", "I am Event A"));
		events.add(new Event1("B", "I am Event B"));
		events.add(new Event1("B", "World"));
		Event1 event;
		while (!events.isEmpty()) {
			event = events.remove();
			switch (event.getType()) {
			case "A":
				handleEventA(event);
				break;
			case "B":
				handleEventB(event);
				break;
			default:
				break;
			}
		}
	}

	static class Event1 {
		private final String type;
		private final String data;

		public Event1(final String type, final String data) {
			this.type = type;
			this.data = data;
		}

		public String getType() {
			return type;
		}

		public String getData() {
			return data;
		}
	}
}
