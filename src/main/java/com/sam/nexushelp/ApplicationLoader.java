package com.sam.nexushelp;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

//@Component
public class ApplicationLoader {
    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady() {

    }
}
