import java.util.Scanner;

class Node {
    int value;
    Node left, right;

    Node(int value) {
        this.value = value;
        left = right = null;
    }
}

class BinaryTree {
    Node root;

    void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    void showAll(Node node) {
        if (node != null) {
            System.out.print(node.value);
            if (node.left != null || node.right != null) {
                System.out.print("(");
                if (node.left != null) {
                    showAll(node.left);
                }
                if (node.right != null) {
                    if (node.left != null) System.out.print(" ");
                    showAll(node.right);
                }
                System.out.print(")");
            }
        }
    }

    void showRightSubtree() {
        if (root != null && root.right != null) {
            showAll(root.right);
        }
    }

    void showLeftSubtree() {
        if (root != null && root.left != null) {
            showAll(root.left);
        }
    }

    void showParentAndChildren(int value) {
        Node parent = null;
        Node node = root;
        while (node != null && node.value != value) {
            parent = node;
            if (value < node.value) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        if (node == null) {
            System.out.println("Nó não encontrado.");
        } else {
            if (parent != null) {
                System.out.println("Nó Pai: " + parent.value);
            } else {
                System.out.println("O nó é a raiz e não tem pai.");
            }
            System.out.print("Nós Filhos: ");
            if (node.left != null) {
                System.out.print(node.left.value + " ");
            }
            if (node.right != null) {
                System.out.print(node.right.value);
            }
            if (node.left == null && node.right == null) {
                System.out.print("Nenhum (nó folha)");
            }
            System.out.println();
        }
    }
}

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Inserir número");
            System.out.println("2 - Mostrar todos");
            System.out.println("3 - Mostrar a subárvore da direita");
            System.out.println("4 - Mostrar a subárvore da esquerda");
            System.out.println("5 - Mostrar o nó pai e os nós filhos de um nó");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Informe o número a ser inserido: ");
                    int value = scanner.nextInt();
                    tree.insert(value);
                    break;
                case 2:
                    tree.showAll(tree.root);
                    System.out.println();
                    break;
                case 3:
                    tree.showRightSubtree();
                    System.out.println();
                    break;
                case 4:
                    tree.showLeftSubtree();
                    System.out.println();
                    break;
                case 5:
                    System.out.print("Informe o valor do nó: ");
                    int nodeValue = scanner.nextInt();
                    tree.showParentAndChildren(nodeValue);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
