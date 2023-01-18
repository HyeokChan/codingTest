package com.company;
import java.io.*;
import java.util.*;
public class Main {
    public static int n, m;
    public static boolean[] brokens;
    public static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        brokens = new boolean[10];
        if(m != 0){
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<m; i++){
                int idx = Integer.parseInt(st.nextToken());
                brokens[idx] = true;
            }
        }
        // 100에서 다 누르는 경우
        result = Math.abs(n - 100);
        for(int i=0; i<=999999; i++){
            int num = i;
            String snum = Integer.toString(num);
            // 고장난 번호 체크
            if(!isBrokenNum(snum)){
                result = Math.min(snum.length()+Math.abs(num-n), result);
            }
        }
        System.out.println(result);
    }

    // 고장난 번호 체크
    public static boolean isBrokenNum(String snum){
        for(int j=0; j<snum.length(); j++){
            // 체크하는 숫자에 고장난 번호가 포함되어 있으면 넘어감
            if(brokens[Integer.parseInt(snum.charAt(j)+"")]){
                return true;
            }
        }
        return false;
    }
}


