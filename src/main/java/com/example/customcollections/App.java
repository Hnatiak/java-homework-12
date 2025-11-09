package com.example.customcollections;

import com.example.customcollections.task1.Task1;
import com.example.customcollections.task2.Task2;

public class App {
    public static void main(String[] args) {

        System.out.println("=== Завдання 1: Таймер з двома потоками ===");
        Thread task1Thread = new Thread(() -> {
            Task1.main(null);
        });
        task1Thread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n=== Завдання 2: Багатопотоковий FizzBuzz ===");
        Task2 task2 = new Task2(15);

        Thread fizzThread = new Thread(task2::fizz);
        Thread buzzThread = new Thread(task2::buzz);
        Thread fizzbuzzThread = new Thread(task2::fizzbuzz);
        Thread numberThread = new Thread(() -> {
            task2.number();
            task2.printAll();
        });

        fizzThread.start();
        buzzThread.start();
        fizzbuzzThread.start();
        numberThread.start();
        
        try {
            task1Thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}