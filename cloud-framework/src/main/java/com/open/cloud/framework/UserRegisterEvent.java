package com.open.cloud.framework;

import org.springframework.context.ApplicationEvent;

public class UserRegisterEvent extends ApplicationEvent {
    public UserRegisterEvent(String name) { //name即source
        super(name);
    }
}