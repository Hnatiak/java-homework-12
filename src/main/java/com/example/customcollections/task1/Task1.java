package com.example.customcollections.task1;

public class Task1 {
    public static void main(String[] args) {
        Thread timerThread = new Thread(new TimerTask());
        Thread messageThread = new Thread(new MessageTask());

        timerThread.start();
        messageThread.start();
    }
}

class TimerTask implements Runnable {
    private static int seconds = 0;

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000); // 1 секунда
                seconds++;
                System.out.println("Минуло часу: " + seconds + " сек.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static int getSeconds() {
        return seconds;
    }
}

class MessageTask implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(5000); // кожні 5 секунд
                System.out.println("Минуло 5 секунд!");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}