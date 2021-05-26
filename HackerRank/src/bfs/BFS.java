package bfs;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BFS {
	

    // Complete the bfs function below.
    static int[] bfs(int n, int m, int[][] edges, int s) {
    	//선언부
    	
    	int[] distance = new int[n+1];
    	
    	
    	LinkedList<Integer> nodeQueue = new LinkedList<Integer>(); // 방문 node 체크를 위한 queue
    	// 초기화
    	for(int i=1;i<=n;i++) {
    			distance[i] = -1;
    			// 자기 노드는 따로 0처리 해야하나?
    		}
    	
    	
    	
    	
    	// s를 queue 에 삽입
    	nodeQueue.push(s);
    	distance[s] = 0;

    	//graph[s][s] = 0; // 자기 자신으로의 거리는 0
    	// while(!queue.isEmpty()) 문으로 큐가 빌때까지 인접 노드 탐색
    	while(!nodeQueue.isEmpty()) {
    		int pollNum = nodeQueue.poll(); // queue에서 가장 먼저 넣은 노드 꺼냄
    		for(int j=0; j<2 ; j++) {
    		for(int i=0; i<m ; i++){

    				if(pollNum == edges[i][j]) { // edges행렬 전체에서 pollNum에 인접한 노드 탐색하고 '방문안한' 인접노드 존재하면 queue에 저장 
    					
    					if(j==0) { // 
    						if(distance[edges[i][1]] == -1) {
    							// 방문 안한 노드일 경우
    							nodeQueue.add(edges[i][1]); // queue에 넣고 / Queue에서 절대!!!!push 하지마라....
    							distance[edges[i][1]] = distance[pollNum] + 6; 
    							 //s부터 이전 노드까지의 거리 + 6 
    							
    						}
    				}
    					else {
    						if(distance[edges[i][0]] == -1) {
    							nodeQueue.add(edges[i][0]);
    							distance[edges[i][0]] = distance[pollNum] + 6;
    						
    						}
    					}
    				}	
    			}
    	
    		}
    	}// while 문 종료 
    
		int[] result = new int[n-1];
		int t = 0;
    	for(int x=1; x<=n; x++) {
    		if(x != s) {
    			result[t] = distance[x];
    			t++;
    		}
    		
    	}
    	
    	return result;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = bfs(n, m, edges, s);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i])); // 6 6 -1 출력

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }   

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}