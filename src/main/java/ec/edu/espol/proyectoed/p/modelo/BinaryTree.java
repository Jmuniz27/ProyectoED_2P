package ec.edu.espol.proyectoed.p.modelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BinaryTree<E extends Comparable<E>> {
    private NodeBinaryTree<E> root;
    public BinaryTree(List<E> preguntas, Map<E, List<String>> animales){
        this.root = null;
        crearArbol(preguntas, animales);
    }
    public BinaryTree() {
        this.root=null;
    }
    
    public BinaryTree(NodeBinaryTree<E> root) {
        this.root=root;
    }
    public BinaryTree(E content) {
        this.root = new NodeBinaryTree<>(content);
    }
    
    public void recorrerPreOrden(){
        if (!this.isEmpty()) {
            System.out.println(this.root.getContent());
            if (root.getLeft()!=null) {
                root.getLeft().recorrerPreOrden(); 
            }
            if (root.getRight()!=null) {
                root.getRight().recorrerPreOrden(); 
            }
        }
    }
    
    public void recorrerPosOrden(){
        if (!this.isEmpty()){
            if (root.getLeft()!=null){
                root.getLeft().recorrerPosOrden(); 
            }
            if (root.getRight()!=null){
                root.getRight().recorrerPosOrden(); 
            }
            System.out.println(this.root.getContent());
        }
    }
    
    public void recorrerEnorden(){
        if (!this.isEmpty()){
            if (root.getLeft()!=null){
                root.getLeft().recorrerEnorden(); 
            }
            System.out.println(this.root.getContent());
            if (root.getRight()!=null){
                root.getRight().recorrerEnorden(); 
            }
        }
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public boolean isLeaf(){
        if (!this.isEmpty()){
            return root.getLeft() == null && root.getRight() == null;
        }
        return false;
    }

    public NodeBinaryTree<E> getRoot() {
        return root;
    }

    public void setRoot(NodeBinaryTree<E> root) {
        this.root = root;
    }
    
    public int countDescendantsRecursive(){
        if(this.isEmpty()){
            return 0;
        } else if(this.isLeaf()){
            return 0;
        } else{
            int leftDescendants = 0;
            int rightDescendants = 0;
            if(this.root.getLeft() != null)
                leftDescendants = this.root.getLeft().countDescendantsRecursive() + 1;
            if(this.root.getRight() != null)
                rightDescendants = this.root.getRight().countDescendantsRecursive() +1;
            return leftDescendants + rightDescendants;
        }
    }

    public int countDescendantsIterative(){
        int descendants = -1;
        Stack<BinaryTree<E>> stack = new Stack<>();
        if(this.isEmpty())
            return 0;
        else{
            stack.push(this);
            while(!stack.isEmpty()){
                BinaryTree<E> tree = stack.pop();
                descendants ++;
                if(tree.getRoot().getLeft() != null)
                    stack.push(tree.getRoot().getLeft());
                if(tree.getRoot().getRight() != null)
                    stack.push(tree.getRoot().getRight());
            }
            return descendants;
        }
    }

    public NodeBinaryTree<E> findParentIterative(NodeBinaryTree<E> node){
        Stack<BinaryTree<E>> stack = new Stack<>();
        if(this.isEmpty())
            return null;
        else{
            stack.push(this);
            while(!stack.isEmpty()){
                BinaryTree<E> tree = stack.pop();
                BinaryTree<E> left = tree.getRoot().getLeft();
                BinaryTree<E> right = tree.getRoot().getRight();
                if(left != null){
                    if(left.getRoot().getContent().equals(node.getContent()))
                        return tree.root;
                    else
                        stack.push(left);
                }
                if(right != null){
                    if(right.getRoot().getContent().equals(node.getContent()))
                        return tree.root;
                    else
                        stack.push(right);
                }
            }
            return null;
        }
    }

    public NodeBinaryTree<E> findParentRecursive(NodeBinaryTree<E> node, BinaryTree<E> parent){
        NodeBinaryTree<E> left = null;
        NodeBinaryTree<E> right = null;
        if(node == null){
            return null;
        }
        if(parent.getRoot().getLeft() != null){
            if(parent.getRoot().getLeft().getRoot().getContent().equals(node.getContent()))
                return parent.getRoot();
            left = findParentRecursive(node, parent.getRoot().getLeft());
        }
        if(parent.getRoot().getRight() != null){
            if(parent.getRoot().getRight().getRoot().getContent().equals(node.getContent()))
                return parent.getRoot();
            right = findParentRecursive(node, parent.getRoot().getRight());
        }
        if(left != null)
            return left;
        return right;
    }
    
    public NodeBinaryTree<E> findParentRecursive(NodeBinaryTree<E> node){
        return this.findParentRecursive(node, new BinaryTree<>(this.root));
    }

    public int countLevelsIterative(){
        if(this.isEmpty())
            return 0;
        else if(this.isLeaf())
            return 1;
        else{
            int leftLevels = 0;
            int rightLevels = 0;
            if(this.getRoot().getLeft() != null)
                leftLevels = this.getRoot().getLeft().countDescendantsRecursive();
            if(this.getRoot().getRight() != null)
                rightLevels = this.getRoot().getRight().countDescendantsRecursive();
            return Integer.max(leftLevels, rightLevels)+1;
        }
    }

    public int countLevelsRecursive(){
        if(this.isEmpty())
            return 0;
        else{
            int left = 0;
            int right = 0;
            if(this.getRoot().getLeft()!= null)
                left = this.getRoot().getLeft().countLevelsRecursive();
            if(this.getRoot().getRight()!= null)
                right = this.getRoot().getRight().countLevelsRecursive();
            return 1 + Integer.max(left,right);
        }
    }

    public boolean isLeftyRecursive(){
        if(this.isEmpty())
            return true;
        else if(this.isLeaf())
            return true;
        else{
            boolean left = false;
            boolean right = false;
            if(this.getRoot().getLeft()!=null){
                left = this.getRoot().getLeft().isLeftyRecursive();
            }
            if(this.getRoot().getRight()!=null){
                right = this.getRoot().getLeft().isLeftyRecursive();
            }
            return left && right && (this.getRoot().getLeft().countDescendantsRecursive()>=this.getRoot().getRight().countDescendantsRecursive());
        }
    }

    public boolean isLeftyIterative(){
        if(this.isEmpty() || this.isLeaf())
            return true;
        else{
            Stack<BinaryTree<E>> stack = new Stack<>();
            stack.push(this);
            while(!stack.isEmpty()){
                BinaryTree<E> tree = stack.pop();
                if(!tree.isLeaf()){
                    int left = 0;
                    int right = 0;
                    if(tree.getRoot().getLeft()!=null)
                        left = tree.getRoot().getLeft().countDescendantsRecursive()+1;
                    if(tree.getRoot().getRight()!=null)
                        right = tree.getRoot().getRight().countDescendantsRecursive()+1;
                    if(right>left)
                        return false;
                }
            }
        }
        return true;
    }

    public boolean isIdenticalRecursive(BinaryTree<E> otherTree){
        if(this.isEmpty() || otherTree.isEmpty()){
            return false;
        }else if(this.isLeaf() && !otherTree.isLeaf()){
            return false;
        }else if(this.isLeaf() && otherTree.isLeaf() && this.getRoot().getContent().equals(otherTree.getRoot().getContent())){
            return true;
        } else{
            boolean subTreeLeft = this.getRoot().getLeft() !=null && otherTree.getRoot().getLeft() !=null;
            boolean subTreeRight = this.getRoot().getRight() !=null && otherTree.getRoot().getRight() !=null;
            boolean left = true;
            boolean right = true;
            if(subTreeLeft){
                left = this.getRoot().getLeft().isIdenticalRecursive(otherTree.getRoot().getLeft());
            }
            if(subTreeRight){
                right = this.getRoot().getRight().isIdenticalRecursive(otherTree.getRoot().getRight());
            }
            return (subTreeLeft && left) || (subTreeRight && right);
        }
    }

    public boolean isIdenticalIterative(BinaryTree<E> otherTree){
        if(this.countDescendantsIterative() != otherTree.countDescendantsIterative()){
            return false;
        }
        Stack<BinaryTree<E>> stack1 = new Stack<>();
        Stack<BinaryTree<E>> stack2 = new Stack<>();
        stack1.push(this);
        stack2.push(otherTree);
        while(!stack1.isEmpty() && !stack2.isEmpty()){
            BinaryTree<E> tree1 = stack1.pop();
            BinaryTree<E> tree2 = stack2.pop();
            if(!tree1.getRoot().getContent().equals(tree2.getRoot().getContent())){
                return false;
            }
        }
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public void largestValueOfEachLevelRecursive(){
        List<E> maxValues = new ArrayList<>();
        largestValueOfEachLevel(this, 0, maxValues);
        for(E value: maxValues){
            System.out.println(value);
        }
    }

    public void largestValueOfEachLevel(BinaryTree<E> tree, int level, List<E> maxValues){
        if(tree ==null || tree.isEmpty()){
            return;
        }
        if(level== maxValues.size()){
            maxValues.add(tree.getRoot().getContent());
        }
        else{
            E max  = maxValues.get(level);
            if(tree.getRoot().getContent().compareTo(max)>0){
                maxValues.set(level, tree.getRoot().getContent());
            }
        }
        largestValueOfEachLevel(tree.getRoot().getLeft(),level+1,maxValues);
        largestValueOfEachLevel(tree.getRoot().getRight(),level+1,maxValues);
    }    

    public void largestValueOfEachLevelIterative(){
        if(this.isEmpty()){
            return;
        }
        Queue<BinaryTree<E>> queue = new LinkedList<>();
        queue.offer(this);
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            E max = null;
            for(int i=0;i<levelSize;i++){
                BinaryTree<E> bt = queue.poll();
                E current_value = bt.getRoot().getContent();
                if(max==null ||  current_value.compareTo(max)>0){
                    max=current_value;
                }
                if(bt.getRoot().getLeft()!=null){
                    queue.offer(bt.getRoot().getLeft());
                }
                if(bt.getRoot().getRight()!=null){
                    queue.offer(bt.getRoot().getRight());
                }
            }
            System.out.println(max);
        }
    }

    public int countNodesWithOnlyChildIterative(){
        int singleChildNodes = 0;
        Stack<BinaryTree<E>> stack = new Stack<>();
        stack.push(this);
        while(!stack.isEmpty()){
            BinaryTree<E> tree = stack.pop();
            boolean left = tree.getRoot().getLeft() == null; 
            boolean right = tree.getRoot().getRight() == null; 
            if((left && !right)||(!left && right)){
                singleChildNodes ++;
            }
            if(!left){
                stack.push(tree.getRoot().getLeft());
            }
            if(!right){
                stack.push(tree.getRoot().getRight());
            }
        }
        return singleChildNodes;
    }

    public int countNodesWithOnlyChildRecursive(){
        boolean left = this.getRoot().getLeft() == null; 
        boolean right = this.getRoot().getRight() == null;
        if(this.isEmpty()){
            return 0;
        }else if((left && !right)||(!left && right)){
            return 1;
        }else{
            int leftChilds = 0;
            int rightChilds = 0;
            if(!left){
                leftChilds = this.getRoot().getLeft().countNodesWithOnlyChildRecursive();
            }
            if(!right){
                rightChilds = this.getRoot().getRight().countNodesWithOnlyChildRecursive();
            }
            return leftChilds + rightChilds;
        }
    }

    public boolean isHeightBalancedRecursive(){
        if(this.isEmpty())
            return false;
        else{
            int left = 0;
            int right = 0;
            if(this.getRoot().getLeft()!= null)
                left = this.getRoot().getLeft().countLevelsRecursive();
            if(this.getRoot().getRight()!= null)
                right = this.getRoot().getRight().countLevelsRecursive();
            return Math.abs(left-right) <= 1;
        }
    }

    public boolean isHeightBalancedIterative(){
        Stack<BinaryTree<E>> stack = new Stack<>();
        stack.push(this);
        while(!stack.isEmpty()){
            BinaryTree<E> tree = stack.pop();
            int left = 0;
            int right = 0;
            if(tree.getRoot().getLeft()!= null)
                left = tree.getRoot().getLeft().countLevelsRecursive();
            if(tree.getRoot().getRight()!= null)
                right = tree.getRoot().getRight().countLevelsRecursive();
            if(Math.abs(left-right) > 1)
                return false;
        }
        return true;
    }
    public void crearArbol(List<E> preguntas, Map<E, List<String>> animales){
        for(Map.Entry<E, List<String>> entry: animales.entrySet()){
            root = addNode(root, preguntas, entry.getKey(), entry.getValue(), 0);

        }
    } 
    private NodeBinaryTree<E> addNode(NodeBinaryTree<E> nodo, List<E> preguntas, E contenido, List<String> respuestas, int indice){
        if (indice == preguntas.size()){
            return new NodeBinaryTree<>(contenido);
        }
        if (nodo == null){
            nodo = new NodeBinaryTree<>(preguntas.get(indice));
        }
        if (respuestas.get(indice).equalsIgnoreCase("si")) {
            if (nodo.getLeft() == null) {
                nodo.setLeft(new BinaryTree<>());
            }
            NodeBinaryTree<E> leftRoot = addNode(nodo.getLeft().getRoot(), preguntas, contenido, respuestas, indice + 1);
            nodo.getLeft().setRoot(leftRoot);
        } else {
            if (nodo.getRight() == null) {
                nodo.setRight(new BinaryTree<>());
            }
            NodeBinaryTree<E> rightRoot = addNode(nodo.getRight().getRoot(), preguntas, contenido, respuestas, indice + 1);
            nodo.getRight().setRoot(rightRoot);
        }
        return nodo;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(sb, this.root, 0);
        return sb.toString();
    }

    private void buildString(StringBuilder sb, NodeBinaryTree<E> node, int level) {
        if (node == null) {
            return;
        }
        // Añadir indentación según el nivel del nodo
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
        sb.append(node.getContent()).append("\n");

        // Recorrer subárbol izquierdo
        if (node.getLeft() != null) {
            buildString(sb, node.getLeft().getRoot(), level + 1);
        } else {
            // Indentar y agregar un placeholder para el hijo izquierdo nulo
            for (int i = 0; i < level + 1; i++) {
                sb.append("  ");
            }
            sb.append("null\n");
        }

        // Recorrer subárbol derecho
        if (node.getRight() != null) {
            buildString(sb, node.getRight().getRoot(), level + 1);
        } else {
            // Indentar y agregar un placeholder para el hijo derecho nulo
            for (int i = 0; i < level + 1; i++) {
                sb.append("  ");
            }
            sb.append("null\n");
        }
    }
}