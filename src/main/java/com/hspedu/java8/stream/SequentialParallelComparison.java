package com.hspedu.java8.stream;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/17
 * https://www.logicbig.com/how-to/java-8-streams.html
 * https://www.logicbig.com/tutorials/core-java-tutorial/java-util-stream/sequential-vs-parallel.html
 * https://www.baeldung.com/java-when-to-use-parallel-stream
 **/
public class SequentialParallelComparison {
    
    public static void main(String[] args) {
        String[] strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "B", "C", "D"};
        
        System.out.println("-------\nRunning sequential\n-------");
        run(Arrays.stream(strings).sequential());
        System.out.println("-------\nRunning parallel\n-------");
        run(Arrays.stream(strings).parallel());
    }
    
    public static void run(Stream<String> stream) {
        
        stream.forEach(s -> {
            System.out.println(LocalTime.now() + " - value: " + s +
                    " - thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
