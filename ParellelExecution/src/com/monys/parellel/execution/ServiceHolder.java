/**
 * 
 */
package com.monys.parellel.execution;

import java.util.concurrent.Callable;

/**
 * @author Manish Kumar Mishra
 *
 */
public class ServiceHolder<U> implements Comparable<ServiceHolder<U>>{

    
    private ServiceHolder() {
    }
    
    Callable<U> task;
    int priority;
    String name;
    Object output;
    
    public Callable<U> getTask() {
        return task;
    }
    public int getPriority() {
        return priority;
    }
    
    public String getName() {
        return name;
    }
    
    public Object getOutput() {
        return output;
    }
    public void setOutput(Object output) {
        this.output= output;
    }
    @Override
    public int compareTo(ServiceHolder<U> o) {
        int x = this.priority;
        int y = this.priority;
        return Integer.compare(x, y);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ServiceHolder<?> other = (ServiceHolder<?>) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }



    public static class ServiceHolderBuilder<T>{
        
        private Callable<T> task;
        private int priority = -1;
        private String name ;
        
        private ServiceHolderBuilder(){}
        
        public static <T> ServiceHolderBuilder<T> builder(Callable<T> task,String name){
            ServiceHolderBuilder<T> builder = new ServiceHolderBuilder<>();
            builder.task = task;
            builder.name = name;
            return builder;
        }
        
        public ServiceHolderBuilder<T> priority(int priority){
            this.priority = priority;
            return this;
        }
        
        public ServiceHolder<T> build() {
            
            ServiceHolder<T> sr = new ServiceHolder<>();
            sr.task = this.task;
            sr.priority = this.priority;
            sr.name = this.name;
            return sr;
        }
        
    }



}
