/**
 * 
 */
package com.monys.parellel.execution;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Manish Kumar Mishra
 *
 */
public final class ParallelCallHelper {
    
	public ParallelCallHelper(){}
    
    public ParallelCallHelper(int poolSize){pool =  Executors.newFixedThreadPool(poolSize < 1 ? 10 : poolSize);}


    private ExecutorService pool =  Executors.newFixedThreadPool(10);
    
    public void process(List<ServiceHolder<?>> cores) throws InterruptedException, ExecutionException {
        if(cores == null || cores.isEmpty() ){
            return ;
        }
        Collections.sort(cores);
        int prev = cores.get(0).getPriority();
        List<ServiceHolder<?>> here = new LinkedList<>();
        for(ServiceHolder<?> ss : cores){
            int priority = ss.getPriority();
            if(priority != prev) {
                internProcess(here);
                prev = priority;
                here = new LinkedList<>();
            }
            here.add(ss);
        }
        internProcess(here);
    }
    
    public <U> Optional<U> process(List<ServiceHolder<?>> cores,ResultMerger<U> merger) throws InterruptedException, ExecutionException {
        if(cores == null || cores.isEmpty() ){
            return  Optional.empty();
        }
        Collections.sort(cores);
        int prev = cores.get(0).getPriority();
        List<ServiceHolder<?>> here = new LinkedList<>();
        for(ServiceHolder<?> ss : cores){
            int priority = ss.getPriority();
            if(priority != prev) {
                internProcess(here);
                prev = priority;
                here = new LinkedList<>();
            }
            here.add(ss);
        }
        internProcess(here);
        if(merger != null){
        	return Optional.of(merger.merge(cores));
        }
        return Optional.empty();
    }

    private void internProcess(List<ServiceHolder<?>> tasks) throws InterruptedException, ExecutionException {
        if(tasks == null || tasks.isEmpty()){
            return ;
        }
        Map<String,Future<?>> futures = new HashMap<>();
        for(ServiceHolder<?> sr : tasks) {
            futures.put(sr.getName(),pool.submit(sr.getTask()));
        }

        for(ServiceHolder<?> rec : tasks){
            rec.setOutput(futures.get(rec.getName()).get());
        }
    }

}
