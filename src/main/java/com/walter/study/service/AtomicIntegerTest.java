/**
 * Copyright (c) 2018-2020 四川健康久远科技有限公司 All Rights Reserved.
 * FileName: AtomicInteger
 *
 * @author: liucr
 * @date: 2022/8/11 22:02
 * @version: 1.0.0
 */
package com.walter.study.service;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Title: 
 * Description: 
 *
 * @author liucr
 * @data 2022/8/11
 */
public class AtomicIntegerTest {



        private static final int THREADS_CONUT = 20;
        public static AtomicInteger  count = new AtomicInteger(0);

        public static void increase() {
            count.incrementAndGet();
        }

        public static void main(String[] args) {
            Thread[] threads = new Thread[THREADS_CONUT];
            for (int i = 0; i < THREADS_CONUT; i++) {
                threads[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 1000; i++) {
                            increase();
                        }
                    }
                });
                threads[i].start();
            }

            while (Thread.activeCount() > 1) {
                Thread.yield();
            }
            System.out.println(count);
        }

}
