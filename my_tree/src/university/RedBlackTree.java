package university;

//Реалізувати клас червоно-чорного дерева
public class RedBlackTree {

    private static class Node {
        //Також реалізувати всі необхідні допоміжні методи: клас вузла
        int key;
        boolean isRed;
        Object inf;
        Node left;
        Node right;
        Node parent;

        public Node(int key, Object inf, Node parent) {
            //Також реалізувати всі необхідні допоміжні методи: конструктор
            this.parent = parent;
            this.key = key;
            this.inf = inf;
            this.isRed = true;
        }

        @Override
        public String toString() {
            //Також реалізувати всі необхідні допоміжні методи: конструктор, toString
            return "Node{" +
                    "key=" + key +
                    ", isRed=" + isRed +
                    ", inf=" + inf +
                    ", left=" + left +
                    ", right=" + right +
                    ", parent key=" + (parent == null ? "null" : parent.key) +
                    '}';
        }
    }

    private Node root;

    public Node getRoot() {
        return root;
    }

    private Node getGrandfather(Node n){
        //Також реалізувати всі необхідні допоміжні методи: пошук «діда»
        if (n.parent != null){
            return n.parent.parent;
        } else {
            return null;
        }
    }

    private Node getUncle(Node n){
        //Також реалізувати всі необхідні допоміжні методи: пошук «дядька»
        Node g = n.parent.parent;
        if (g.left == n.parent){
            return g.right;
        } else {
            return g.left;
        }
    }

    public void add(int key, Object inf) {
        //метод додавання нового вузла до дерева
        Node n;
        if (root == null) {
            n = root = new Node(key, inf, null);
        } else {
            Node parent = findParentForNode(root, key);
            n = new Node(key, inf, parent);
            if (key < parent.key) {
                parent.left = new Node(key, inf, parent);
            } else {
                parent.right = new Node(key, inf, parent);
            }
        }
        //з виконанням операції балансування
        case1(n);
        System.out.println(root + "\n");
    }

    private void case1(Node n) {
        //(передбачити усі можливі випадки)
        if (n == root){
            n.isRed = false;
        } else {
            case2(n);
        }
    }

    private void case2(Node n){
        //(передбачити усі можливі випадки)
        if (n.parent.isRed){
            case3(n);
        }
    }

    private void case3(Node n) {
        //(передбачити усі можливі випадки)
        Node u = getUncle(n);
        Node g = getGrandfather(n);
        if (u != null && u.isRed){
            g.isRed = true;
            n.parent.isRed = false;
            u.isRed = false;
            case1(g);
        } else {
            case4(n);
        }
    }

    private void case4(Node n) {
        //(передбачити усі можливі випадки)
        Node g = getGrandfather(n);
        Node p = n.parent;
        if (n == p.left && g.right == p){
            Node tmp = n.right;

            g.right = n;
            n.parent = g;

            n.right = p;
            p.parent = n;

            p.left = tmp;
            if (tmp != null){tmp.parent = p;}
            case5(p);
        }
        if (n == n.parent.right && g.left == n.parent){

            Node tmp = n.left;

            g.left = n;
            n.parent = g;

            n.left = p;
            p.parent = n;

            p.right = tmp;
            if (tmp != null){tmp.parent = p;}
            case5(p);
        }
        case5(n);
    }

    private void case5(Node n) {
        //(передбачити усі можливі випадки)
        Node p = n.parent;
        Node g = getGrandfather(n);
        Node gg = getGrandfather(p);

        if (g.right == p){
            p.isRed = false;
            g.isRed = true;

            Node tmp = p.left;

            p.left = g;
            g.parent = p;

            g.right = tmp;
            if (tmp != null){tmp.parent = g;}

            if (gg == null){
                root = p;
            }
            else {
                if (gg.right == g){
                    gg.right = p;
                } else {
                    gg.left = p;
                }
                p.parent = gg;
            }
        } else {
            p.isRed = false;
            g.isRed = true;

            Node tmp = p.right;

            p.right = g;
            g.parent = p;

            g.left = tmp;
            if (tmp != null){tmp.parent = g;}

            if (gg == null){
                root = p;
            }
            else {
                if (gg.left == g){
                    gg.left = p;
                } else {
                    gg.right = p;
                }
                p.parent = gg;
            }
        }
    }

    public Node findParentForNode(Node current, int key) {
        if (key < current.key) {
            if (current.left == null) {
                return current;
            } else {
                return findParentForNode(current.left, key);
            }
        } else {
            if (current.right == null) {
                return current;
            } else {
                return findParentForNode(current.right, key);
            }
        }
    }

    @Override
    public String toString() {
        return "RedBlackTree{" +
                "root=" + root +
                '}';
    }
}
