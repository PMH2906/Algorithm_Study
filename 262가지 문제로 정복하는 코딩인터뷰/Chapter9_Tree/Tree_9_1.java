public class Tree_9_1 {

    public static boolean isBalanced = true;

    public static class BinaryTreeNode<T> {
        T data;
        BinaryTreeNode<T> left, right;

        public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {

        BinaryTreeNode<Character> E = new BinaryTreeNode<>('E',null,null);
        BinaryTreeNode<Character> F = new BinaryTreeNode<>('F',null,null);
        BinaryTreeNode<Character> G = new BinaryTreeNode<>('G',null,null);
        BinaryTreeNode<Character> I = new BinaryTreeNode<>('I',null,null);
        BinaryTreeNode<Character> J = new BinaryTreeNode<>('J',null,null);

        // 균형 트리의 H 노드
        BinaryTreeNode<Character> H = new BinaryTreeNode<>('H',I,J);

        // 불균형 트리의 H 노드
//        BinaryTreeNode<Character> H = new BinaryTreeNode<>('H',null,null);


        BinaryTreeNode<Character> D = new BinaryTreeNode<>('D',E,F);
        BinaryTreeNode<Character> C = new BinaryTreeNode<>('C',D,G);
        BinaryTreeNode<Character> B = new BinaryTreeNode<>('B',C,H);

        BinaryTreeNode<Character> M = new BinaryTreeNode<>('M',null,null);
        BinaryTreeNode<Character> N = new BinaryTreeNode<>('N',null,null);
        BinaryTreeNode<Character> O = new BinaryTreeNode<>('O',null,null);
        BinaryTreeNode<Character> L = new BinaryTreeNode<>('L',M,N);
        BinaryTreeNode<Character> K = new BinaryTreeNode<>('K',L,O);

        BinaryTreeNode<Character> A = new BinaryTreeNode<>('A',B,K);

        System.out.println(checkBalancedTree(A) != -1);
    }

    // 불균형 트리일 경우 -1 return
    // 균형 트리일 경우 height return
    private static int checkBalancedTree(BinaryTreeNode<Character>  root) {

        if(root== null) return 0;

        // 왼쪽 트리 탐색 후 불균형 트리일 경우, return -1
        int leftHeight = checkBalancedTree(root.left);
        if(leftHeight==-1) return -1;

        // 오른쪽 트리 탐색 후 불균형 트리일 경우, return -1
        int rightHeight = checkBalancedTree(root.right);
        if(rightHeight==-1) return -1;

        // 불균형 트리일 경우 -1 return
        if(Math.abs(leftHeight - rightHeight)>1) {
            return -1;
        }

        // 균형 트리일 경우 height return
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
