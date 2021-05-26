package kruskals;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'kruskals' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts WEIGHTED_INTEGER_GRAPH g as parameter.
     */

    /*
     * For the weighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] and <name>To[i]. The weight of the edge is <name>Weight[i].
     *
     */
    static int[] parent;
	private static PriorityQueue<A> pq;
	
	
	static class A implements Comparable<A>{
		int s;
		int e;
		int v;
		public A(int s,int e,int v) {
			super();
			this.s = s;
			this.e = e;
			this.v = v;
		}
		@Override
		public int compareTo(A o) {
			// TODO Auto-generated method stub
			return o.v >=this.v ? -1:1; // o가 더 크면 -1 내가 더 크면 1 반환 
		}
	}

	public static int find(int a) {
		if(a==parent[a]) return a;
		parent[a] = find(parent[a]);
		return parent[a];
	}
	
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot != bRoot) {
			parent[aRoot] = b;
			
		}else {
			return;
		}
	}

    public static int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {

    	parent = new int[gNodes+1];
    	int result = 0;
    	pq = new PriorityQueue<A>();
    	for(int i=0;i<gWeight.size();i++) {
    		pq.add(new A(gFrom.get(i),gTo.get(i),gWeight.get(i))); // pq에 비용 오름차순으로 정렬
    	}
    	
    	for(int i=0;i<=gNodes;i++) {
    		parent[i]=i; 
    	} // union-find의 초기화로 자기자신을 부모노드로 설정
    	
    	for(int i=0;i<gWeight.size();i++) {
    		A oneEdge = pq.poll();
    		int start = oneEdge.s;
    		int end = oneEdge.e;
    		int a = find(start);
    		int b = find(end);
    		if(a == b) continue;
    		
    		union(start,end);
    		result += oneEdge.v;
    	}
    	
    	
    	return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] gNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int gNodes = Integer.parseInt(gNodesEdges[0]);
        int gEdges = Integer.parseInt(gNodesEdges[1]);

        List<Integer> gFrom = new ArrayList<>();
        List<Integer> gTo = new ArrayList<>();
        List<Integer> gWeight = new ArrayList<>();

        IntStream.range(0, gEdges).forEach(i -> {
            try {
                String[] gFromToWeight = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                gFrom.add(Integer.parseInt(gFromToWeight[0]));
                gTo.add(Integer.parseInt(gFromToWeight[1]));
                gWeight.add(Integer.parseInt(gFromToWeight[2]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int res = Result.kruskals(gNodes, gFrom, gTo, gWeight);
        String sRes = Integer.toString(res);
        // Write your code here.
        bufferedWriter.append(sRes);
        bufferedWriter.flush();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
