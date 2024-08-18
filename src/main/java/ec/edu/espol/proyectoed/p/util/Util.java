package ec.edu.espol.proyectoed.p.util;

import ec.edu.espol.proyectoed.p.modelo.BinaryTree;
import ec.edu.espol.proyectoed.p.modelo.NodeBinaryTree;
import javafx.scene.Node;

import java.util.List;
import java.util.Map;

public class Util {
    
    
    public static BinaryTree<String> buildDecisionTree(List<String> questions, Map<String, List<String>> animalsWithAnswers) {
        BinaryTree<String> tree = new BinaryTree<>();
        for (Map.Entry<String, List<String>> entry : animalsWithAnswers.entrySet()) {
            tree.setRoot(addAnimal(tree.getRoot(), questions, entry.getKey(), entry.getValue(), 0));
        }
        return tree;
    }

    private static NodeBinaryTree<String> addAnimal(NodeBinaryTree node, List<String> questions, String animal, List<String> answers, int index) {
        if (index == questions.size()) {
            return new NodeBinaryTree<>(animal);
        }

        if (node == null) {
            node = new NodeBinaryTree<>(questions.get(index));
        }

        if (answers.get(index).equalsIgnoreCase("yes")) {
            if (node.getLeft() == null) {
                node.setLeft(new BinaryTree<String>());
            }
            node.getLeft().setRoot(addAnimal(node.getLeft().getRoot(), questions, animal, answers, index + 1));
        } else {
            if (node.getRight() == null) {
                node.setRight(new BinaryTree<>());
            }
            node.getRight().setRoot(addAnimal(node.getRight().getRoot(), questions, animal, answers, index + 1));
        }

        return node;
    }



}
