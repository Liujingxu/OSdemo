package com.third;

public class Main {



    public static void main(String[] args) {
        int[][] claim = {{7,5,3},{3,2,2},{9,0,2},{2,2,2}, {4,3,3}};
        int[][] allocation = {{0,1,0}, {2,0,0}, {3,0,2},{2,1,1},{0,0,2}};
        int k, l =0, count = 0, m = 0;

        int[][] C_A = new int[5][3];
        int[] result = {-1,-1,-1,-1,-1};
        int[] currentavail = {3, 3, 2};
        System.out.print("银行家总共拥有的各类资源的总数：\n");
        System.out.print("银行家总共拥有的各类资源的总数：\n     A  B  C\n     10 5  7\n");
        System.out.print("银行目前仍剩下的各类资源的数量：\n     A  B  C\n     3  3  2\n");
        System.out.print("各进程对各类资源的最大需求量：\n     A  B  C\n");

        for (int i = 0; i < 5; i++){
            System.out.print("P" + i);
            for (int j = 0; j < 3; j++){
                System.out.print("  " + claim[i][j] + " ");
                C_A[i][j] = claim[i][j] - allocation[i][j];
            }
            System.out.println();
        }

        System.out.print("各进程已分配到的各类资源：\n     A  B  C\n");

        for (int i = 0; i < 5; i++){
            System.out.print("P" + i);
            for (int j = 0; j < 3; j++){
                System.out.print("  " + allocation[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("各进程仍需的各类资源数量：\n     A  B  C\n");
        for (int i = 0; i < 5; i++){
            System.out.print("P" + i);
            for (int j = 0; j < 3; j++){
                System.out.print("  " + C_A[i][j] + " ");
            }
            System.out.println();
        }

        int i, j;

        while(result[l]==-1) {
            for(k=0;k<5;k++)
                if(result[k]==-1) {
                    for(j=0;j<3;j++)
                        if(C_A[k][j]<=currentavail[j]&&C_A[k][j]>=0) {
                            currentavail[j]=currentavail[j]+allocation[k][j];
                            m++;
                            if(m==3) {
                                result[l]=k;
                                m=0;
                            }
                        }
                        else break;
                    l++;
                }
            for(i=0;i<l;i++)
                if(result[i]!=-1) {
                    System.out.printf("P%d->",result[i]);//把预分配成功的先打印出来
                    count++;
                }
            l=0;//清零,为下一轮的预分配做准备
        }
        if(count==5)
            System.out.print("\n系统安全!上行所示为其中一个进程安全序列\n");
        else
            System.out.print("\n系统不安全!\n");




    }




}
