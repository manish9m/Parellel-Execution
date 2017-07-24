/**
 * 
 */
package com.monys.parellel.execution.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.monys.parellel.execution.ParallelCallHelper;
import com.monys.parellel.execution.ResultMerger;
import com.monys.parellel.execution.ServiceHolder;
import com.monys.parellel.execution.ServiceHolder.ServiceHolderBuilder;

/**
 * @author manish.m
 *
 */
public class TestCalls {

    
    public static void main(String ...arr) throws InterruptedException, ExecutionException{
        
        Class1 one = new Class1();
        Class2 two = new Class2();
        Class3 three = new Class3();
        Class4 four = new Class4();
        Class5 five = new Class5();
       
        
        ResultMerger<Integer> mergeResult = new ResultMerger<Integer>() {
            @Override
            public Integer merge(List<ServiceHolder<?>> inputResults) {
                return inputResults.stream().mapToInt(p -> (Integer)(p.getOutput())).sum();
            }
        };
        
        ServiceHolder<Integer> sr1 = ServiceHolderBuilder.builder(() -> one.method1(),"ONE").priority(1).build();
        ServiceHolder<Integer> sr2 = ServiceHolderBuilder.builder(() -> two.method2(),"TWO").priority(1).build();
        ServiceHolder<Integer> sr3 = ServiceHolderBuilder.builder(() -> three.method3(mergeResult.merge(Arrays.asList(sr1,sr2))),"THREE").priority(2).build();
        ServiceHolder<Integer> sr4 = ServiceHolderBuilder.builder(() -> four.method4(),"FOUR").priority(3).build();
        ServiceHolder<Integer> sr5 = ServiceHolderBuilder.builder(() -> five.method5(),"FIVE").priority(3).build();

        new ParallelCallHelper(5).process(Arrays.asList(sr1,sr2,sr3,sr4,sr5));
        
    }



}
