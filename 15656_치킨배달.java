package com.company;
import java.io.*;
import java.util.*;
public class Main {
    /***
     * 2차원 좌표에 집의 위치와 치킨집의 위치가 주어졌을 때, 치킨집의 개수를 M개만 남겼을 때 최소 치킨거리 구하기
     * 1. 처음 생각
     *  1) 제거할 치킨집의 모든 위치를 조합으로 구한다.
     *  2) 치킨집을 제거한다
     *  3) 2차원 반복문으로 board에서 값이 1(집)일 때 bfs로 가장 가까운 치킨집을 찾고 거리를 계산하며 더한다.
     * => 메모리 초과
     * 2. 풀이 : 처음 생각의 1단계에서 조합을 구하는 과정에서 좌표 계산으로 거리를 구할 수 있다.
     *  1) 치킨집의 위치를 저장하면서 집의 위치도 저장한다
     *  2) 남길 치킨집의 조합을 구하면서 depth == r 일 때,
     *     bfs가 아닌 단순 이중 반복문으로 좌표 차이의 최솟값을 구한다.
     */
    public static int n, m;
    public static int[][] board;
    public static ArrayList<int[]> chickenList = new ArrayList<>();
    public static boolean[] visited;
    public static int[] out;
    public static int minValue = Integer.MAX_VALUE;
    public static ArrayList<int[]> personList = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        // init
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;
                if(num == 2){
                    chickenList.add(new int[]{i, j});
                }
                // 집의 위치도 저장
                if(num == 1){
                    personList.add(new int[]{i, j});
                }
            }
        }
        // 남겨야할 치킨집 수
        int r = m;
        visited = new boolean[chickenList.size()];
        out = new int[r];
        combi(chickenList, out, visited, 0, r, 0);
        System.out.println(minValue);
    }

    // 제거해야할 치킨집 조합 구하기
    public static void combi(ArrayList<int[]> chickenList, int[] out, boolean[] visited, int start, int r, int depth){
        if(depth == r){
            // 도시의 치킨거리 구하기
            int sum = 0;
            // 집의 위치와 현재 구한 치킨집 조합의 거리차 단순 계산, bfs X
            for(int i=0; i<personList.size(); i++){
                int tMin = Integer.MAX_VALUE;
                for(int j=0; j<out.length; j++){
                    tMin = Math.min(tMin, Math.abs(personList.get(i)[0]-chickenList.get(out[j])[0])+
                            Math.abs(personList.get(i)[1]-chickenList.get(out[j])[1]));
                }
                sum += tMin;
            }
            minValue = Math.min(minValue, sum);
            return;
        }
        for(int i=start; i<chickenList.size(); i++){
            if(!visited[i]){
                visited[i] = true;
                out[depth] = i;
                combi(chickenList, out, visited, i + 1, r, depth + 1);
                visited[i] = false;
            }
        }
    }
}


