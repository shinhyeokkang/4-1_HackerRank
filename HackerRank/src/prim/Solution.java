package prim;

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
     * Complete the 'prims' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY edges
     *  3. INTEGER start
     */
    static int[] parent;
	private static PriorityQueue<A> pq;
	
	 static class A {
		int s;
		int e;
		int v;
		public A(int s,int e,int v) {
			super();
			this.s = s;
			this.e = e;
			this.v = v;
		}

	}
	 static class Comp implements Comparator<A>{
		@Override
		public int compare(A arg0,A arg1) {
			// TODO Auto-generated method stub
			return arg0.v > arg1.v ? 1:-1;// o가 더 크면 -1 내가 더 크면 1 반환 
		}
	 }
    @SuppressWarnings("unchecked")
	public static int prims(int n, List<List<Integer>> edges, int start) {
    	
    	int ans = 0;
    	boolean[] visit = new boolean[n+1];
    	ArrayList<A>[] nodeList;
    	Comp cp = new Comp();
    	pq = new PriorityQueue<>(cp);
    	Deque<Integer> dq = new ArrayDeque<>();
    	dq.add(start);
    	
    	nodeList = new ArrayList[n+1];
    	
    	for(int i=1;i<=n;i++) {
    		nodeList[i] = new ArrayList<A>();
     	}
    	int st;
    	int ed;
    	int va;
    	for(int i=0;i<edges.size();i++) {
    		st = edges.get(i).get(0);
    		ed = edges.get(i).get(1);
    		va = edges.get(i).get(2);
    		nodeList[st].add(new A(st,ed,va));
    		nodeList[ed].add(new A(ed,st,va));
    		
    	}
    	ArrayList<A> tempList;
    	A tempNode;
    	while(!dq.isEmpty()) {
    		int currentNode = dq.poll();
    		visit[currentNode] = true;
    		tempList = nodeList[currentNode];
    		for(int i=0;i<tempList.size();i++) {
    			if(!visit[tempList.get(i).e]) {
    				pq.add(tempList.get(i));
    			}
    		}
    		
        	while(!pq.isEmpty()) {
        		tempNode = pq.poll();
        		if(!visit[tempNode.e]) {
        			visit[tempNode.e] = true;
        			ans+=tempNode.v;
        			dq.add(tempNode.e);
        			break;
        		}
        	}
    	}
    	

    	return ans;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> edges = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                edges.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int start = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.prims(n, edges, start);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}