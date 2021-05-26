package huffman;

import java.util.*;

	 
	abstract class Node implements Comparable<Node> {
	    public  int frequency; // the frequency of this tree
	    public  char data;
	    public  Node left, right; 
	    public Node(int freq) { 
	      frequency = freq; 
	    }
	 
	    // compares on the frequency
	    public int compareTo(Node tree) {
	        return frequency - tree.frequency;
	    }
	}
	 
	class HuffmanLeaf extends Node {
	    
	 
	    public HuffmanLeaf(int freq, char val) {
	        super(freq);
	        data = val;
	    }
	}
	 
	class HuffmanNode extends Node {
	    
	    public HuffmanNode(Node l, Node r) {
	        super(l.frequency + r.frequency);
	        left = l;
	        right = r;
	    }

	}


	class Decoding {/*  
	class Node
		public  int frequency; // the frequency of this tree
    	public  char data;
    	public  Node left, right;
    
*/ 

	void decode(String s, Node root) {
		StringBuffer text = new StringBuffer();
		Node basicRoot = root;
		System.out.print(root.data);
		for(char c: s.toCharArray()) {
			switch(c) {
			case '0': 
				System.out.print("0");
				root = root.left; // 기준노드를 왼쪽 자식노드로 변경
				if(root.data != 0) {// data가 빈값이 아니면, 즉 리프노드이면
					text.append(root.data); //해당 문자를 출력
					System.out.print(root.data);
					root = basicRoot; // 다시 처음 노드로 돌아감
					
				}
				break;
			case '1': 
				System.out.print("1");
				root = root.right; // 기준노드를 왼쪽 자식노드로 변경
				if(root.data != 0) {// data가 빈값이 아니면, 즉 리프노드이면
					text.append(root.data); //해당 문자를 출력
					System.out.print(root.data);
					root = basicRoot; // 다시 처음 노드로 돌아감
				}
				break;
			}
       }
		
		System.out.println(text);


		}
	}

 
  public class Solution {
  
    // input is an array of frequencies, indexed by character code
    public static Node buildTree(int[] charFreqs) {
      
        PriorityQueue<Node> trees = new PriorityQueue<Node>();
        // initially, we have a forest of leaves
        // one for each non-empty character
        for (int i = 0; i < charFreqs.length; i++)
            if (charFreqs[i] > 0)
                trees.offer(new HuffmanLeaf(charFreqs[i], (char)i)); //우선순위 큐에 HuffmanLeaf 노드(빈도수, 글자)를 빈도수 작은 순서대로 삽입
 
        assert trees.size() > 0;
      
        // loop until there is only one tree left
        while (trees.size() > 1) {
            // two trees with least frequency 가장 빈도수 적은 두 문자 추출
            Node a = trees.poll();
            Node b = trees.poll();
 
            // put into new node and re-insert into queue 두 문자를 합쳐 하나의 노드로 생성
            trees.offer(new HuffmanNode(a, b));
        }
      
        return trees.poll();
    }
  
    public static Map<Character,String> mapA=new HashMap<Character ,String>();
  
    public static void printCodes(Node tree, StringBuffer prefix) {
      
        assert tree != null;
      
        if (tree instanceof HuffmanLeaf) { //넣은 노드가 leaf 노드이면
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
 
            // print out character, frequency, and code for this leaf (which is just the prefix)
            //System.out.println(leaf.data + "\t" + leaf.frequency + "\t" + prefix);
            mapA.put(leaf.data,prefix.toString());

        } else if (tree instanceof HuffmanNode) { // 넣은 노드가 내부 노드이면
            HuffmanNode node = (HuffmanNode)tree;
 
            // traverse left
            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);
 
            // traverse right
            prefix.append('1');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
    
        String test= input.next();
 
        // we will assume that all our characters will have
        // code less than 256, for simplicity
        int[] charFreqs = new int[256];
      
        // read each character and record the frequencies
        for (char c : test.toCharArray()) {
            charFreqs[c]++;
        	System.out.println("c: " + c + " char[]: " + charFreqs[c]);
        	}
 
        // build tree
        Node tree = buildTree(charFreqs);
        
 
        // print out results
        printCodes(tree, new StringBuffer());
        StringBuffer s = new StringBuffer();
      
        for(int i = 0; i < test.length(); i++) {
          	char c = test.charAt(i);
            s.append(mapA.get(c));
        }
      
        System.out.println(s);
        Decoding d = new Decoding();
        d.decode(s.toString(), tree);

    }
}

