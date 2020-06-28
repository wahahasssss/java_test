package com.hdu.base;

import java.util.Arrays;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/24
 * @Time 下午5:55
 */
public class BTree<T extends Comparable<T>,V> {
    private int factor;
    private static final int DEFAULT_FACTOR = 5;//b-tree的阶数 指节点子树最大的值。  b树中关键字是=m-1，其中最小的分支数为最大分支数/2
    private int MIN_CHILDREN_BRANCH_NUMBER;
    private int MAX_CHILDREN_BRANCH_NUMBER;
    private int MAX_NODE_NUMBER;
    private int MIN_NODE_NUMBER;

    private Node<T,V> root;

    public BTree(){
        this(DEFAULT_FACTOR);
    }

    public BTree(int factor) {
        this.factor = factor;
        this.MIN_CHILDREN_BRANCH_NUMBER = ((Double)(Math.ceil(1.0*this.factor/2))).intValue();
        this.MAX_CHILDREN_BRANCH_NUMBER = this.factor;
        this.MAX_NODE_NUMBER = this.factor - 1;
        this.MIN_NODE_NUMBER = this.MIN_CHILDREN_BRANCH_NUMBER - 1;
        this.root = new BTreeLeafNode<>();
    }

    public void put(T key,V value){
        Node<T,V> tmpRoot = this.root.insert(key,value);
        if(tmpRoot != null){
            this.root = tmpRoot;
        }
    }
    abstract class Node<T,V>{
        protected Node parent;
        protected Object[] keys;
        protected int size;
        abstract Node<T,V> insert(T key,V value);

    }

    class BtreeInternalNode<T extends Comparable<T>,V> extends Node<T,V> {
        Node<T, V>[] pointers;
        Object[] values;

        public BtreeInternalNode() {
            this.parent = null;
            this.size = 0;
            this.keys = new Object[MAX_NODE_NUMBER];
            this.pointers = new Node[MAX_CHILDREN_BRANCH_NUMBER];
            this.values = new Object[MAX_NODE_NUMBER];
        }

        @Override
        Node<T, V> insert(T key, V value) {
            int position = 0;
            for(;position<this.size;position++){
                if (key.compareTo((T)this.keys[position])<0){
                    break;
                }

            }
            return this.pointers[position].insert(key,value);
        }


        private Node<T, V> insert(T key, V value, Node<T, V> leftChild, Node<T, V> rightChild) {
            if (this.size == 0) {
                this.keys[0] = key;
                this.values[0] = value;
                this.pointers[0] = leftChild;
                this.pointers[1] = rightChild;
                leftChild.parent = this;
                rightChild.parent = this;
                this.size++;
                return this;
            }
            int position = 0;
            for (;position<size;position++){
                if (key.compareTo((T)this.keys[position])<0)
                    break;
            }
            Object[] newKeys = new Object[MAX_NODE_NUMBER+1];
            System.arraycopy(this.keys,0,newKeys,0,size);
            newKeys[position] = key;
            this.keys = newKeys;

            Object[] newValues = new Object[MAX_NODE_NUMBER+1];
            System.arraycopy(this.values,0,newValues,0,size);
            newValues[position] = value;
            this.values = newValues;

            Node[] newPointers = new Node[MAX_CHILDREN_BRANCH_NUMBER+1];
            System.arraycopy(this.pointers,0,newPointers,0,position);
            newPointers[position] = leftChild;
            newPointers[position+1] = rightChild;
            this.pointers = newPointers;
            leftChild.parent = this;
            rightChild.parent = this;
            this.size ++;
            if (this.size>MAX_NODE_NUMBER){
                BtreeInternalNode parentNode = new BtreeInternalNode();
                if (this.parent != null){
                    parentNode = (BtreeInternalNode) this.parent;
                }

                this.keys = new Object[MAX_NODE_NUMBER];
                this.values = new Object[MAX_NODE_NUMBER];
                this.pointers = new Node[MAX_NODE_NUMBER + 1];
                int split_position = ((Double)Math.floor(1.0*size/2)).intValue();
                System.arraycopy(newKeys,0,this.keys,0,split_position);
                System.arraycopy(newValues,0,this.values,0,split_position);
                System.arraycopy(newPointers,0,this.pointers,0,split_position+1);



                BtreeInternalNode newRightNode = new BtreeInternalNode();
                System.arraycopy(newKeys,split_position+1,newRightNode.keys,0,size-split_position-1);
                System.arraycopy(newValues,split_position+1,newRightNode.values,0,size-split_position-1);
                System.arraycopy(newPointers,split_position+1,newRightNode.pointers,0,size - split_position);
                newRightNode.size = 2;
                for (Node n : newRightNode.pointers){
                    if (n!=null){
                    n.parent = newRightNode;
                    }
                }
                for (Node n :this.pointers){
                    if (n!=null) {
                        n.parent = this;
                    }
                }
                this.size = 2;

                return parentNode.insert((T)newKeys[split_position],newValues[split_position],this,newRightNode);
            }
            return null;
        }


        @Override
        public String toString() {
            return "BtreeInternalNode{" +
                    "pointers=" + Arrays.toString(pointers) +
                    ", values=" + Arrays.toString(values) +
                    '}';
        }
    }
    class BTreeLeafNode<T extends Comparable<T>, V> extends Node<T, V> {
        Object[] values;

        public BTreeLeafNode() {
            this.parent = null;
            size = 0;
            keys = new Object[MAX_NODE_NUMBER];
            values = new Object[MAX_NODE_NUMBER];
        }

        @Override
        Node<T, V> insert(T key, V value) {
            int position = 0;
            for (; position < size; position++) {
                if (key.compareTo((T) this.keys[position]) < 0)
                    break;
            }
            Object[] newKeys = new Object[MAX_NODE_NUMBER + 1];
            System.arraycopy(this.keys, 0, newKeys, 0, position);
            newKeys[position] = key;
            System.arraycopy(this.keys, position, newKeys, position + 1, size - position);
            this.keys = newKeys;

            Object[] newValues = new Object[MAX_NODE_NUMBER + 1];
            System.arraycopy(this.values, 0, newValues, 0, position);
            newValues[position] = value;
            System.arraycopy(this.values, position, newValues, position + 1, size - position);
            this.values = newValues;
            this.size++;


            if (size > MAX_NODE_NUMBER) {
                int split_position = ((Double) Math.floor(1.0 * size / 2)).intValue();
                BTreeLeafNode newLeaf = new BTreeLeafNode();
                System.arraycopy(newKeys, split_position + 1, newLeaf.keys, 0, size - split_position - 1);
                System.arraycopy(newValues, split_position + 1, newLeaf.values, 0, size - split_position -1);
                newLeaf.size = size - split_position - 1;

                this.keys = new Object[MAX_NODE_NUMBER];
                this.values = new Object[MAX_NODE_NUMBER];
                System.arraycopy(newKeys, 0, this.keys, 0, split_position);
                System.arraycopy(newValues, 0, this.values, 0, split_position);
                this.size = split_position;

                BtreeInternalNode internalNode = new BtreeInternalNode();
                if (this.parent != null){
                    internalNode =  (BtreeInternalNode) this.parent;
                }
                return internalNode.insert((T) newKeys[split_position], newValues[split_position], this, newLeaf);
            }
            return null;
        }

        @Override
        public String toString() {
            return "BTreeLeafNode{" +
                    "values=" + Arrays.toString(values) +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BTree{" +
                " root=" + root +
                '}';
    }

    public static void main(String[] args){
        BTree<Integer,String> bTree = new BTree();
        int max = 1000;
        for(int i = 0; i < max;i ++){
            bTree.put(i,String.format("%d_value",i));
        }
        System.out.println(bTree.toString());
    }
}
