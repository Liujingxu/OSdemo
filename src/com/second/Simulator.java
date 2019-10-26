package com.second;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;

public class Simulator {

    private PCB_info[] g_queue;
    private int g_time = 0;

    public Simulator(){
        g_queue = new PCB_info[Constant.PRO_NUM];
        for (int i = 0; i < Constant.PRO_NUM; i++){
            g_queue[i] = new PCB_info();
        }
    }

    public void initProcess(){
        int id;
        initQueue();
        Random random = new Random();
        random.setSeed((new Date()).getTime());
        for (int i = 0; i < Constant.PRO_NUM; i++){
            
            id = CreateProcess(random.nextInt(10)%4, random.nextInt(10)%10 + 1);
            if (id != Constant.ID_ERROR){
                System.out.println("**********************************");
                System.out.println("创建进程成功");
                System.out.println("进程ID号为:" + id);
                System.out.println("进程的静态优先权为: " + g_queue[id].getS_static_prior());
                System.out.println("进程的动态优先权为: " + g_queue[id].getS_dynamic_prior());
                System.out.println("进程的到达时间为: " + g_queue[id].getS_start_time());
                System.out.println("进程需要时间为: " + g_queue[id].getS_need_time());
                System.out.println("进程已用CPU时间为: " + g_queue[id].getS_used_time());
                System.out.println("进程的状态为: " + g_queue[id].getS_state());
                System.out.println();
            }else {
                System.out.println("创建进程失败");
            }
        }
    }

    private int CreateProcess(int pri, int needtime) {
        int id = Constant.ID_ERROR;
        for (int i = 0; i < Constant.PRO_NUM; i++){
            if (g_queue[i].getS_state() == Constant.FINISH){
                id = g_queue[i].getS_id();
                g_queue[i].setS_dynamic_prior(Constant.MIN_PRIOR);
                g_queue[i].setS_need_time(needtime);
                g_queue[i].setS_start_time(g_time);
                g_queue[i].setS_state(Constant.WAIT);
                g_queue[i].setS_static_prior(pri);
                g_queue[i].setS_used_time(0x0);
                break;
            }
        }

        return id;
    }

    public void runProcess(){
        int id;
        while ((id = getPriProcess()) != Constant.ID_ERROR){
            workProcess(id);
            changeProcess(id);
        }

    }

    private int getPriProcess(){
        int id = Constant.ID_ERROR;
        int prev_id = Constant.ID_ERROR;
        int prior = Constant.MIN_PRIOR * 2, temp_prior;

        for (int i = 0; i < Constant.PRO_NUM; i++){
            if (g_queue[i].getS_state() != Constant.FINISH){
                temp_prior = g_queue[i].getS_dynamic_prior() + g_queue[i].getS_static_prior();
                if (temp_prior <= prior){
                    id = i;
                    prior = temp_prior;
                }
            }
        }
        return id;
    }

    private void workProcess(int id){
        g_time++;
        g_queue[id].setS_state(Constant.RUN);
        g_queue[id].setS_used_time(g_queue[id].getS_used_time() + 1);
        printState();

    }

    private void printState(){
        System.out.println("时间 进程ID\t状态 已用时间 需要时间 开始时间 静优先级 动优先级");
        for (int i = 0; i < Constant.PRO_NUM; i++){
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\t%d\t%d\n", g_time, g_queue[i].getS_id(), g_queue[i].getS_state(), g_queue[i].getS_used_time(), g_queue[i].getS_need_time(), g_queue[i].getS_start_time(), g_queue[i].getS_static_prior(), g_queue[i].getS_dynamic_prior());
        }
    }

    private void changeProcess(int id){
        if (g_queue[id].getS_need_time() == g_queue[id].getS_used_time()){
            g_queue[id].setS_state(Constant.FINISH);
        }else{
            g_queue[id].setS_dynamic_prior(Constant.MIN_PRIOR);
            g_queue[id].setS_state(Constant.WAIT);
        }

        for (int i = 0; i < Constant.PRO_NUM; i++){
            if ((i != id) && (g_queue[i].getS_state() != Constant.FINISH)){
                if ((g_queue[i].getS_dynamic_prior() > 0)) {
                    g_queue[i].setS_dynamic_prior(g_queue[i].getS_dynamic_prior() - 1);
                } else {
                    g_queue[i].setS_dynamic_prior(0);
                }
            }
        }
    }

    public void endProcess(){
        System.out.println("所有进程结束状态:");
        printState();
        System.out.println("所有进程已经结束！");

    }

    private void initQueue(){
        for (int i = 0; i < Constant.PRO_NUM; i++){
            g_queue[i].setS_id(i);
            g_queue[i].setS_dynamic_prior(Constant.MIN_PRIOR);
            g_queue[i].setS_need_time(0);
            g_queue[i].setS_start_time(0);
            g_queue[i].setS_static_prior(Constant.MIN_PRIOR);
            g_queue[i].setS_used_time(0);
            g_queue[i].setS_state(Constant.FINISH);
        }
    }
}
