package com.open.cloud.framework;

import org.springframework.context.ApplicationEvent;

public class RefreshContextEvent extends ApplicationEvent {

	/**
	 * Create a new ApplicationEvent.
	 * @param source the object on which the event initially occurred (never {@code null})
	 */
	public RefreshContextEvent(Object source) {
		super(source);
	}

}