package com.example.customcollections.task2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task2 {
    private final int n;
    private int current = 1;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public Task2(int n) {
        this.n = n;
    }

    public void fizz() {
        process(i -> i % 3 == 0 && i % 5 != 0, "fizz");
    }

    public void buzz() {
        process(i -> i % 5 == 0 && i % 3 != 0, "buzz");
    }

    public void fizzbuzz() {
        process(i -> i % 15 == 0, "fizzbuzz");
    }

    public void number() {
        process(i -> i % 3 != 0 && i % 5 != 0, null);
    }

    private void process(java.util.function.Predicate<Integer> conditionCheck, String output) {
        while (true) {
            lock.lock();
            try {
                while (current <= n && !conditionCheck.test(current)) {
                    condition.await();
                }
                if (current > n) {
                    condition.signalAll();
                    return;
                }
                if (output != null) {
                    System.out.println(output);
                } else {
                    System.out.println(current);
                }
                current++;
                condition.signalAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Task2 fb = new Task2(15);

        Thread fizzThread = new Thread(fb::fizz);
        Thread buzzThread = new Thread(fb::buzz);
        Thread fizzbuzzThread = new Thread(fb::fizzbuzz);
        Thread numberThread = new Thread(fb::number);

        fizzThread.start();
        buzzThread.start();
        fizzbuzzThread.start();
        numberThread.start();

        try {
            fizzThread.join();
            buzzThread.join();
            fizzbuzzThread.join();
            numberThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}