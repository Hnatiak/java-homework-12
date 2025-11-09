package com.example.customcollections.task2;

public class Task2 {
    private final int n;

    public Task2(int n) {
        this.n = n;
    }

    public void fizz() {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                System.out.println("fizz");
            }
        }
    }

    public void buzz() {
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0 && i % 3 != 0) {
                System.out.println("buzz");
            }
        }
    }

    public void fizzbuzz() {
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                System.out.println("fizzbuzz");
            }
        }
    }

    public void number() {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                System.out.println(i);
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
            e.printStackTrace();
        }
    }
}