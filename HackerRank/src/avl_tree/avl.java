	/* Class node is defined as :
    class Node 
    	int val;	//Value
    	int ht;		//Height
    	Node left;	//Left child
    	Node right;	//Right child

	*/

static Node insert(Node root,int val)
{
    Node newNode = new Node();
    newNode.val = val;
    newNode.ht = Math.max(getHeight(newNode.left),getHeight(newNode.right))+1;
    //��ͷ� �̺�Ž��Ʈ������ ������ ��ġ ã�� �ڵ� 
        if(root.val > val) { //val �� ���ذ����� ���� ���
            if(root.left == null) {// ���� ���� �ڽĳ�尡 null�̸�
                root.left = newNode; // �װ��� �űԳ�� ����
            }
            else {
            root.left = insert(root.left, val);// ������ ���� �ڽĳ��� insert()
            }
            // balance
             if(Math.abs(getHeight(root.left) - getHeight(root.right)) == 2 ){
                 if(val < root.left.val){ //exceed == leftNode -> leftNode
                     root = singleRotateRight(root);
                 }else{ //exceed == leftNode -> rightNode
                     root = doubleRotateRight(root);
                 }
             }
        }
        else { // val�� ���ذ����� Ŭ ��� 
            if(root.right == null) {
                root.right = newNode;
            }
            else {
            root.right = insert(root.right, val);// ������ ������ �ڽĳ��� insert()
            }
            // balance
             if(Math.abs(getHeight(root.left) - getHeight(root.right)) == 2 ){
                 if(val > root.right.val){ //exceed == RR
                     root = singleRotateLeft(root);
                 }else{ //exceed == RL
                     root = doubleRotateLeft(root);
                 }
             }
        }
    //��������� Height ����
    root.ht = Math.max(getHeight(root.left),getHeight(root.right))+1;
    
    return root;
}

static int getHeight(Node node){
    if(node == null) {
        return -1;
    }
    return node.ht; 
}

//rotate left
static private Node singleRotateLeft(Node startNode){
    Node priviousNode = startNode;
    Node nextNode = startNode.right;
    
    priviousNode.right = nextNode.left;
    nextNode.left = priviousNode;
    
    priviousNode.ht = Math.max(getHeight(priviousNode.left),getHeight(priviousNode.right))+1;
    nextNode.ht = Math.max(getHeight(nextNode.left),getHeight(nextNode.right))+1;
                                
    return nextNode;
}
//rotate right
static private Node singleRotateRight(Node startNode){
    Node priviousNode = startNode;
    Node nextNode = startNode.left;
    
    priviousNode.left = nextNode.right;
    nextNode.right = priviousNode;
    
    priviousNode.ht = Math.max(getHeight(priviousNode.left),getHeight(priviousNode.right))+1;
    nextNode.ht = Math.max(getHeight(nextNode.left),getHeight(nextNode.right))+1;
    
    return nextNode;
}   

//rotate right -> rotate left
static private Node doubleRotateLeft(Node startNode){
    Node priviousNode = startNode;
    Node nextNode = startNode.right;
    
    priviousNode.right = singleRotateRight(nextNode);
    
    return singleRotateLeft(priviousNode);
}
//rotate left -> rotate right
static private Node doubleRotateRight(Node startNode){
    Node priviousNode = startNode;
    Node nextNode = startNode.left;
    
    priviousNode.left = singleRotateLeft(nextNode);
    
    return singleRotateRight(priviousNode);
}