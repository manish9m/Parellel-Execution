# Parellel-Execution

This code can be used to make a parellel call eithr REST API or method .

For example : 
If we have a scenario where a method is there something like this

    class ProcessingService { 
        private SomeOtherClassOne one;
        private SomeOtherClassTwo two;
        private SomeOtherClassThree three;
        private SomeOtherClassFour four;
        private SomeOtherClassFive five;
  
      public void process(){
        Integer outputOne = one.execute(1);                                                   //Line 1
        Integer outputTwo = two.execute(2);                                                   //Line 2
        Integer sum = outputOne + outputTwo ;                                                 //Line 3
        Integer outputThree = three.execute(sum);                                             //Line 4
        Integer outputFour = four.execute(outputThree);                                       //Line 5
        Integer outputfive = five.execute(outputThree);                                       //Line 6
        Integer sumUp = outputOne + outputTwo + outputThree + outputFour + outputfive;        //Line 7
        return sumUp;                                                                         //Line 8
      }
    }

In the above code , **Line 1** and **Line 2** can be executed parellally , One we receive theire ouput **Line 4** can be executed and once we receive from **Line 4** , **Line 5** and **Line 6** can be executed parellaly .
In this repository , we made this parellel call in a very efficient and easy way. 
