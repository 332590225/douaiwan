package com.douaiwan.eliminate.dispathers.base;

import com.douaiwan.eliminate.dispathers.interfaces.IListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BaseDispather<T> {
    public final Set<IListener<T>> listeners = new HashSet<>();
    public void addEventListener( IListener<T> listener ){
        listeners.add( listener );
    }

    public void dispatherEvent(T t){
        listeners.forEach(listener->listener.call(t));
    }
}
