import java.util.Scanner;

class Node {
    int value;
    Node left, right;

    Node(int item) {
        value = item;
        left = right = null;
    }
}

class BinarySearchTree {
    Node root;

    void insert(int value) {
        root = insertRec(root, value);
    }

    Node insertRec(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        if (value < root.value)
            root.left = insertRec(root.left, value);
        else if (value > root.value)
            root.right = insertRec(root.right, value);

        return root;
    }

    void printLeafNodes(Node node) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            System.out.println(node.value);
            return;
        }
        if (node.left != null) printLeafNodes(node.left);
        if (node.right != null) printLeafNodes(node.right);
    }

    boolean printAncestors(Node node, int target) {
        if (node == null) return false;

        if (node.value == target) return true;

        if (printAncestors(node.left, target) || printAncestors(node.right, target)) {
            System.out.print(node.value + " ");
            return true;
        }
        return false;
    }

    void printDescendants(Node node) {
        if (node == null) return;

        if (node.left != null) {
            System.out.print(node.left.value + " ");
            printDescendants(node.left);
        }
        if (node.right != null) {
            System.out.print(node.right.value + " ");
            printDescendants(node.right);
        }
    }

    Node findNode(Node root, int value) {
        if (root == null || root.value == value) return root;

        Node temp = findNode(root.left, value);
        if (temp == null) temp = findNode(root.right, value);
        return temp;
    }
}

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Inserir número");
            System.out.println("2 - Mostrar os nós folha");
            System.out.println("3 - Mostrar os nós ancestrais de um nó");
            System.out.println("4 - Mostrar os nós descendentes de um nó");
            System.out.println("5 - [Função de exibir o nó pai e filhos não implementada]");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Digite um número para inserir: ");
                    int num = scanner.nextInt();
                    bst.insert(num);
                    break;
                case 2:
                    System.out.println("Nós folha:");
                    bst.printLeafNodes(bst.root);
                    break;
                case 3:
                    System.out.print("Digite o valor do nó para mostrar seus ancestrais: ");
                    int target = scanner.nextInt();
                    System.out.println("Ancestrais do nó " + target + ":");
                    if (!bst.printAncestors(bst.root, target)) System.out.println("Nó não encontrado ou não possui ancestrais.");
                    else System.out.println(); // Para nova linha após a lista de ancestrais
                    break;
                case 4:
                    System.out.print("Digite o valor do nó para mostrar seus descendentes: ");
                    int desc = scanner.nextInt();
                    Node foundNode = bst.findNode(bst.root, desc);
                    if (foundNode == null) {
                        System.out.println("Nó não encontrado.");
                    } else {
                        System.out.println("Descendentes do nó " + desc + ":");
                        bst.printDescendants(foundNode);
                        System.out.println(); // Para nova linha após a lista de descendentes
                    }
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
