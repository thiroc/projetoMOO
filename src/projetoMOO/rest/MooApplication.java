package projetoMOO.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class MooApplication extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> hashSet = new HashSet<Class<?>>();
        // hashSet.add(MooResource.class);
        System.out.println("começou");
        
        return hashSet;
    }
    
}
