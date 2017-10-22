package com.example.tyz.latte.delegate.web.event;

import android.support.annotation.Nullable;

import java.util.HashMap;

/**
 * Created by TYZ on 2017/10/19.
 */

public class EventManager {

    private static final HashMap<String, Event> EVENTS = new HashMap<>();


    private EventManager() {
    }

    private static  class holder{
        private static final EventManager INSTANCE=new EventManager();
    }
    public static EventManager getInstance(){
        return holder.INSTANCE;
    }

    public EventManager addEvent(@Nullable String name,@Nullable Event event){
        EVENTS.put(name,event);
        return this;
    }
    public Event createEvent(@Nullable String action){
        final Event event=EVENTS.get(action);
        if (event == null) {
            return new UndefineEvent();
        }
        return event;
    }
}
