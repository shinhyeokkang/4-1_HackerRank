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
    	//�����
    	
    	int[] distance = new int[n+1];
    	
    	
    	LinkedList<Integer> nodeQueue = new LinkedList<Integer>(); // �湮 node üũ�� ���� queue
    	// �ʱ�ȭ
    	for(int i=1;i<=n;i++) {
    			distance[i] = -1;
    			// �ڱ� ���� ���� 0ó�� �ؾ��ϳ�?
    		}
    	
    	
    	
    	
    	// s�� queue �� ����
    	nodeQueue.push(s);
    	distance[s] = 0;

    	//graph[s][s] = 0; // �ڱ� �ڽ������� �Ÿ��� 0
    	// while(!queue.isEmpty()) ������ ť�� �������� ���� ��� Ž��
    	while(!nodeQueue.isEmpty()) {
    		int pollNum = nodeQueue.poll(); // queue���� ���� ���� ���� ��� ����
    		for(int j=0; j<2 ; j++) {
    		for(int i=0; i<m ; i++){

    				if(pollNum == edges[i][j]) { // edges��� ��ü���� pollNum�� ������ ��� Ž���ϰ� '�湮����' ������� �����ϸ� queue�� ���� 
    					
    					if(j==0) { // 
    						if(distance[edges[i][1]] == -1) {
    							// �湮 ���� ����� ���
    							nodeQueue.add(edges[i][1]); // queue�� �ְ� / Queue���� ����!!!!push ��������....
    							distance[edges[i][1]] = distance[pollNum] + 6; 
    							 //s���� ���� �������� �Ÿ� + 6 
    							
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
    	}// while �� ���� 
    
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
                bufferedWriter.write(String.valueOf(result[i])); // 6 6 -1 ���

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