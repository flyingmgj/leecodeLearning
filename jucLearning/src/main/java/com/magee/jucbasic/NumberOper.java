package com.magee.jucbasic;

public class NumberOper {

    private int number = 0;

    public synchronized void add() {
        if(number != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;
        System.out.println("线程" + Thread.currentThread().getName() + "执行了add()，number====>" + number);
        this.notifyAll();
    }

    public synchronized void sub() {
        if(number == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number--;
        System.out.println("线程" + Thread.currentThread().getName() + "执行了sub()，number====>" + number);
        this.notifyAll();
    }




}
