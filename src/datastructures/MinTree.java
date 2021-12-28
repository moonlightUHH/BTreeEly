package datastructures;

import java.util.*;

/**
 * Implementierung für Bäume, bei denen jedes Kind-Element
 * größer als das Eltern-Element ist.
 *
 * @author DM, PerlSystem it solutions
 * @version 1.0
 */
public class MinTree<T extends Comparable<T>> {

    public BTreeNode<T> root = null;

    public void insert(T value) {
        if (root == null) {
            this.root = new BTreeNode<>(value);
            return;
        }
        insert(this.root, value);
    }

    /**
     * Fügt dem Baum einen neuen Knoten hinzu, indem dieser so eingeordnet wird, dass alle seine
     * Kind-Elemente größer sind.
     *
     * @param node  Der Knoten der aktuell bearbeitet wird
     * @param value Der Wer der eingefügt werden soll
     */
    private void insert(BTreeNode<T> node, T value) {
        /*Zunächst muss geprüft werden, ob der einzufügende Wert kleiner oder größer/gleich
            des aktuellen Knotenwertes ist
        */
        if (value.compareTo(node.value) < 0) {
            //Neuen Knoten erstellen und den alten mit diesem ersetzen
            BTreeNode<T> newNode = new BTreeNode<>(node.value);
            newNode.left = node.left;
            newNode.right = node.right;
            node.value = value;
            node.left = null;
            node.right = null;
            if (Math.random() < 0.5) {
                node.right = newNode;
            } else {
                node.left = newNode;
            }
        } else {
            //Wert ist größer/gleich, also einfügen oder delegieren
            if (Math.random() < 0.5) {
                if (node.right == null) {
                    node.right = new BTreeNode<>(value);
                } else {
                    insert(node.right, value);
                }
            } else {
                if (node.left == null) {
                    node.left = new BTreeNode<>(value);
                } else {
                    insert(node.left, value);
                }
            }
        }
    }


    /**
     * Liefert das Wurzel-Element des Baumes zurück und entfernt dieses aus dem Baum
     *
     * @return Wert des Wurzel-Elementes
     */
    public T takeMin() {
        T returnValue = this.root.value;
        this.root = deleteNode(root);
        return returnValue;
    }

    /**
     * Entfernt einen gegebenen Knoten aus dem Baum und gibt die Wurzel des Teilbaums zurück
     *
     * @param node zu entfernender Knoten
     * @return Wurzel-Element des Teilbaums
     */
    private BTreeNode<T> deleteNode(BTreeNode<T> node) {
        //Grundsätzlich muss immer der kleinere der beiden Knoten nach oben verschoben werden
        //Abbruchbedingung ist, wenn beide childs leer sind, wenn nur eines vorhanden ist kann direkt dieses genommen werden
        if (node.left == null && node.right == null) return null;
        if (node.left == null) return node.right;
        if (node.right == null) return node.left;

        //Es wird immer links mit rechts verglichen
        //3 Fälle - kleiner, größer oder gleich
        int compRes = node.left.value.compareTo(node.right.value);
        if (compRes < 0) {
            //Links ist kleiner
            node.value = node.left.value;
            node.left = deleteNode(node.left);
        } else if (compRes > 0) {
            //Rechts ist kleiner
            node.value = node.right.value;
            node.right = deleteNode(node.right);
        } else {
            //Beide gleich - also zufällig wählen
            if (Math.random() < 0.5) {
                node.value = node.left.value;
                node.left = deleteNode(node.left);
            } else {
                node.value = node.right.value;
                node.right = deleteNode(node.right);
            }

        }

        return node;
    }

    public Set<List<BTreeNode<T>>> allPaths() {
        System.out.println("In function");
        Set<List<BTreeNode<T>>> paths = new HashSet<>();
        List<BTreeNode<T>> pathParts = new ArrayList<>();
        if (this.root == null) return paths;
        System.out.println("Root was not null, call recursive function");

        getPathsRec(root, pathParts, paths);
        System.out.println("Return paths");
        return paths;
    }

    private void getPathsRec(BTreeNode<T> node, List<BTreeNode<T>> path, Set<List<BTreeNode<T>>> paths) {
        path.add(node);
        if (node.left == null && node.right == null) {
            paths.add(path);
            return;
        }

        if (node.left != null) {
            getPathsRec(node.left, new ArrayList<>(path), paths);
        }
        if (node.right != null) {
            getPathsRec(node.right, new ArrayList<>(path), paths);
        }
    }
}
