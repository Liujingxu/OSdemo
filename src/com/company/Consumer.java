package com.company;

public class Consumer implements Runnable{

    Q q = null;


    public Consumer(Q q) {
        this.q = q;
        this.q.name = "consumer";
    }

    @Override
    public void run() {
        while (true){
            synchronized (q){
                if (q.num > 0){
                    System.out.println("consumer 要消费第" + q.num + "个产品!");
                    q.num--;
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    q.notifyAll();
                }else{
                    System.out.println("consumer stop!");
                    try {
                        q.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
