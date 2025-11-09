package com.example.customcollections.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Task2 {

    private final int n;
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public Task2(int n) {
        this.n = n;
    }

    public void fizz() {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                queue.add("fizz");
            }
        }
    }

    public void buzz() {
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0 && i % 3 != 0) {
                queue.add("buzz");
            }
        }
    }

    public void fizzbuzz() {
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                queue.add("fizzbuzz");
            }
        }
    }

    public void number() {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                queue.add(String.valueOf(i));
            }
        }
    }

    public void printAll() {
        try { Thread.sleep(500); } catch (InterruptedException e) { }
        queue.forEach(item -> System.out.print(item + ", "));
    }

    public static void main(String[] args) {
        Task2 fb = new Task2(15);

        Thread fizzThread = new Thread(fb::fizz);
        Thread buzzThread = new Thread(fb::buzz);
        Thread fizzbuzzThread = new Thread(fb::fizzbuzz);
        Thread numberThread = new Thread(() -> {
            fb.number();
            fb.printAll();
        });

        fizzThread.start();
        buzzThread.start();
        fizzbuzzThread.start();
        numberThread.start();
    }
}
