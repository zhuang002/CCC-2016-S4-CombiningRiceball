/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combiningriceball;

import java.util.Scanner;

/**
 *
 * @author huang
 */
public class CombiningRiceball {

    static int[] riceballs;
    static int[][] getRiceballBackup;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        riceballs = new int[n];
        getRiceballBackup = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                getRiceballBackup[i][j] = -1;
            }
        }
        for (int i = 0; i < n; i++) {
            riceballs[i] = sc.nextInt();
        }
        System.out.println(getMaxRiceball());
    }

    private static int getMaxRiceball() {
        int maxRiceball=0;
        for (int len=riceballs.length;len>=1;len--) {
            for (int start=0;start<=riceballs.length-len;start++) {
                int riceball=getRiceball(start,start+len-1);
                if (maxRiceball<riceball) maxRiceball=riceball;
            }
        }
        return maxRiceball;
    }

    private static int getRiceball(int start, int end) {
        if (start == end) {
            return riceballs[start];
        }
        int backup = getRiceballBackup[start][end];
        if (backup != -1) {
            return backup;
        }

        for (int i = start; i <= end - 1; i++) {
            int riceball1 = getRiceball(start, i);
            if (riceball1 > 0) {
                int riceball2 = getRiceball(i + 1, end);
                if (riceball1 == riceball2) {
                    int riceball = riceball1 + riceball2;
                    getRiceballBackup[start][end] = riceball;
                    return riceball;
                }
            }
        }
        for (int i = start; i <= end - 2; i++) {
            int riceball1 = getRiceball(start, i);
            if (riceball1<=0) continue;
            for (int j = i + 1; j <= end - 1; j++) {
                int riceball2 = getRiceball(i + 1, j);
                if (riceball2<=0) continue;
                int riceball3 = getRiceball(j + 1, end);
                if (riceball1 == riceball3) {
                    int riceball = riceball1 + riceball2 + riceball3;
                    getRiceballBackup[start][end] = riceball;
                    return riceball;
                }
            }
        }
        getRiceballBackup[start][end] = 0;
        return 0;
    }
}
