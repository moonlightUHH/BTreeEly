import datastructures.BTree;
import datastructures.BTreeNode;
import datastructures.MinTree;

import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        MinTree<Integer> t = new MinTree<>();
        final int MAX = 4;
        System.out.print("Die Liste der Zahlen ist: ");
        int values[] = {21, 20, 23, 16/*, 8, 9, 26, 23, 5, 22, 0, 5, 17, 10, 5*/};
        for (int i = 0; i < MAX; i++) {
            /*int tmp = (int) Math.round(2*MAX * Math.random());*/
            int tmp = values[i];
            System.out.print(tmp + (i <MAX-1 ? ", " : ".\n"));
            t.insert(tmp);
        }
        /*System.out.print("Die Sortierung ergibt: ");
        for(int j = 0; j < MAX; j++){
            System.out.print(t.takeMin() + (j <MAX-1 ? ", " : ".\n"));
        }*/

        System.out.println(t);

        /*Set<List<BTreeNode<Integer>>> test = t.allPaths();
        System.out.print("Pfade"+test);

        for (List<BTreeNode<Integer>> path: test) {
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i).value + ", ");
            }
            System.out.print(System.lineSeparator());
        }*/



    }
}
