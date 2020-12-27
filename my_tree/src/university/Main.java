package university;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        RedBlackTree myRedBlackTree = new RedBlackTree();
        Scanner scanner = new Scanner(System.in);

        int[] arr = {1, 2, 3, 5, 6, 0, 4};

        for (int i = 0; i < arr.length; i++) {
            myRedBlackTree.add(arr[i], i);
        }

        System.out.println(myRedBlackTree.toString());
    }
}
