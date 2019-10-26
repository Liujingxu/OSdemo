package com.company;

public class Producer implements Runnable {

    Q q = null;

    public Producer(Q q) {
        this.q = q;
        this.q.name = "producer";
    }


    @Override
    public void run() {
        while (true){
            synchronized (q) {
                if (q.num < q.size){
                    q.num++;
                    System.out.println("producer已经生产了第" + q.num + "个产品");
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    q.notifyAll();
                }else{
                    try {
                        System.out.println(" producer stop !");
                        q.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
