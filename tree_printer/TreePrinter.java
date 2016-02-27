package tree_printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

class Node {
	public int data;
	public Node left;
	public Node right;
	public Node(int data, Node left, Node right) {
		this.data = data;
		this.left = left;
		this.right=right;
	}
	public void prettyPrint(int height) {
		System.out.println(prettyPrint(this, 1, height));
	}
	private StringBuilder prettyPrint(Node root, int currentHeight, int totalHeight) {
		//		if(currentHeight>totalHeight || root == null) return;
		if(currentHeight==totalHeight) return new StringBuilder(root.data+"");
		int spaces = getSpaceCount(totalHeight-currentHeight + 1);
		int slashes = getSlashCount(totalHeight-currentHeight +1);
		StringBuilder sb = new StringBuilder();
//		for(int i=0; i<spaces; i++) sb.append(" ");
//		sb.append(root.data+"");
		sb.append(String.format("%"+(spaces+1)+"s%"+spaces+"s", root.data+"", ""));
//		for(int i=0; i<spaces; i++) sb.append(" ");
		sb.append("\n");
		//now print / and \
		// but make sure that left and right exists
		char leftSlash = '/';
		char rightSlash = '\\';
		int spaceInBetween = 1;
		for(int i=0, space = spaces-1; i<slashes; i++, space --, spaceInBetween+=2) {
			for(int j=0; j<space; j++) sb.append(" ");
			sb.append(leftSlash);
			for(int j=0; j<spaceInBetween; j++) sb.append(" ");
			sb.append(rightSlash+"");
			for(int j=0; j<space; j++) sb.append(" ");
			sb.append("\n");
		}
		//sb.append("\n");

		//now get string representations of left and right subtrees
		StringBuilder leftTree = prettyPrint(root.left, currentHeight+1, totalHeight);
		StringBuilder rightTree = prettyPrint(root.right, currentHeight+1, totalHeight);
		// now line by line print the trees side by side
		Scanner leftScanner = new Scanner(leftTree.toString());
		Scanner rightScanner = new Scanner(rightTree.toString());
//		spaceInBetween+=1;
		while(leftScanner.hasNextLine()) {
			if(currentHeight==totalHeight-1) {
				sb.append(String.format("%-2s %2s", leftScanner.nextLine(), rightScanner.nextLine()));
				sb.append("\n");
				spaceInBetween-=2;				
			}
			else {
				sb.append(leftScanner.nextLine());
				sb.append(" ");
				sb.append(rightScanner.nextLine()+"\n");
			}
		}

		return sb;

	}
	private int getSlashCount(int height) {
		if(height <= 3) return height -1;
		return (int) (3*Math.pow(2, height-3)-1);
	}
	private int getSpaceCount(int height) {
		return (int) (3*Math.pow(2, height-2)-1);
	}
	public void print() {
		inorder(this);
		System.out.println();
	}
	private void inorder(Node root) {
		if (root==null) return;
		inorder(root.left);
		System.out.print(root.data+" ");
		inorder(root.right);
	}
	public int getHeight() {
		return getHeight(this);
	}
	private int getHeight(Node root) {
		if (root == null) return 0;
		return Math.max(getHeight(root.left), getHeight(root.right))+1;
	}
	@Override
	public String  toString() {
		return this.data+"";
	}
}
public class TreePrinter {

	int N, height;
	Node root =  new Node(1,null,null);
	public static void main(String[] args) {
		new TreePrinter();
	}
	public TreePrinter() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			N = Integer.parseInt(br.readLine());
			ArrayList<Node> q = new ArrayList<Node>();
			q.add(root);
			for(int i=0; i<N && !q.isEmpty(); i++) {
				String ab[] = br.readLine().split(" ");
				int a = Integer.parseInt(ab[0]);
				int b = Integer.parseInt(ab[1]);
				Node current = q.remove(0);
				if(a==-1) current.left = null;
				else {
					current.left = new Node(a,null,null);
					q.add(current.left);
				}
				
				if(b==-1) current.right=null;
				else {
					current.right = new Node(b,null,null);
					q.add(current.right);
				}
			}
			//root.print();
			height = root.getHeight();
			root.prettyPrint(height);

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

/*

3
2 3
-1 -1
-1 -1

3
12 23
-1 -1
-1 -1

7
12 13
4 5
6 7
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1

7
12 13
14 15
16 17
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1

15
2 3
4 5
6 7
8 9
0 1
2 3
4 5
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1

15
12 13
24 25
26 27
38 39
30 31
32 33
34 35
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1

31
2 3
4 5
6 7
8 9
0 1
2 3
4 5
6 7
8 9
0 1
2 3
4 5
6 7
8 9
0 1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1

31
2 3
4 5
6 7
8 9
10 11
12 13
14 15
16 17
18 19
20 21
22 23
24 25
26 27
28 29
30 31
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1
-1 -1

*/
