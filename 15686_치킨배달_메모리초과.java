package com.company;
import java.io.*;
import java.util.*;
public class Main {
    public static int n, m;
    public static int[][] board;
    public static ArrayList<int[]> chickenList = new ArrayList<>();
    public static boolean[] visited;
    public static int[] out;
    public static ArrayList<int[]> combiList = new ArrayList<>();
    public static int minValue = Integer.MAX_VALUE;
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
            }
        }
        // 제거해야할 치킨집 수
        int r = chickenList.size() - m;
        visited = new boolean[chickenList.size()];
        out = new int[r];
        combi(chickenList, out, visited, 0, r, 0);
        // 조합 돌면서 치킨거리 구하기
        for(int[] ints : combiList){
            int value = deltChicken(ints);
            minValue = Math.min(minValue, value);
        }
        System.out.println(minValue);
    }

    // 치킨집 제거
    public static int deltChicken(int[] ints){
        // 깊은 복사
        int[][] tBoard = new int[n][n];
        for(int x=0; x<n; x++){
            tBoard[x] = board[x].clone();
        }
        // 치킨집 제거
        for(int i : ints){
            tBoard[chickenList.get(i)[0]][chickenList.get(i)[1]] = 0;
        }
        //치킨거리 구하기
        int value = findChickenDistance(tBoard);
        return value;
    }

    // 치킨거리 구하기
    public static int findChickenDistance(int[][] tBoard){
        int sum = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(tBoard[i][j] == 1){
                    sum += bfs(tBoard, i, j);
                }
            }
        }
        return sum;
    }

    // 지점 별로 bfs 실행
    public static int bfs(int[][] tBoard, int y, int x){
        int[] dy = {0,-1,0,1};
        int[] dx = {1,0,-1,0};
        int[][] disntance = new int[n][n];
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        while (!queue.isEmpty()){
            int[] t = queue.poll();
            y = t[0];
            x = t[1];
            for(int i=0; i<4; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];
                if(ny >= n || nx >= n || ny < 0 || nx < 0){
                    continue;
                }
                // 이동거리 증가
                disntance[ny][nx] = disntance[y][x] + 1;
                // 도착한 곳이 치킨집
                if(tBoard[ny][nx] == 2){
                    return disntance[ny][nx];
                }
                else {
                    queue.add(new int[]{ny, nx});
                }
            }
        }
        return 0;
    }

    // 제거해야할 치킨집 조합 구하기
    public static void combi(ArrayList<int[]> chickenList, int[] out, boolean[] visited, int start, int r, int depth){
        if(depth == r){
            combiList.add(out.clone());
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


