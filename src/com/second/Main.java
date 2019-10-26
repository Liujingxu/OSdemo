package com.second;

import java.util.Date;
import java.util.Random;

public class Main {



    public static void main(String[] args) {
        Simulator simulator = new Simulator();
        simulator.initProcess();
        simulator.runProcess();
        simulator.endProcess();
    }

}
