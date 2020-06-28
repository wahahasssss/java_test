package com.hdu.base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/21
 * @Time 下午5:33
 */
public class BPlusTree<T extends Comparable<T>,V>{
    private int factor;
    private static final int DEFAULT_FACTOR = 5;
    private int MIN_CHILDREN_FOR_INTERNAL;
    private int MAX_CHILDREN_FOR_INTERNAL;
    private int MIN_FOR_LEAF;
    private int MAX_FOR_LEAF;

    private Node<T,V> root = null;

    public BPlusTree() {
        this(DEFAULT_FACTOR);
    }


    public BPlusTree(int factor){
        this.factor = factor;
        this.MIN_CHILDREN_FOR_INTERNAL = Double.valueOf(Math.ceil(1.0 * this.factor/2)).intValue();
        this.MAX_CHILDREN_FOR_INTERNAL = this.factor;
        this.MIN_FOR_LEAF = Double.valueOf(Math.floor(1.0 * this.factor/2)).intValue();
        this.MAX_FOR_LEAF = this.factor - 1;
        this.root = new LeafNode<>();

    }


    public void set(T key,V value){
        if (key == null)
            throw new NullPointerException("key must not be null");
        Node node = this.root.insert(key,value);
        if (node != null){
            this.root = node;
        }
    }

    public V get(T key){
        return this.root.get(key);
    }

    public int height(){
        int height = 1;
        Node node = this.root;
        while (!(node instanceof  LeafNode)){
            height ++ ;
            node = ((InternalNode)node).pointers[0];
        }
        return height;
    }

    abstract class Node<T extends Comparable<T>,V>{
        protected Node<T,V> parent;
        protected Object[] keys;
        protected int size;
        abstract Node<T,V> insert(T key,V value);
        abstract V get(T key);
    }


    class InternalNode<T extends Comparable<T>,V> extends Node<T,V>{
        private Node<T,V>[] pointers;

        public InternalNode() {
            this.size = 0;
            this.pointers = new Node[MAX_CHILDREN_FOR_INTERNAL + 1];
            this.keys = new Object[MAX_CHILDREN_FOR_INTERNAL];
        }


        public Node<T,V> insert(T key,V value){
            int i = 0;
            for(;i < this.size;i++){
                if (key.compareTo((T)this.keys[i])<0) break;
            }
            return this.pointers[i].insert(key,value);
        }

        public V get(T key){
            int i = 0;
            for (;i < this.size;i++){
                if (key.compareTo((T)this.keys[i])<0);
            }
            return this.pointers[i].get(key);
        }

        private Node<T,V> insert(T key,Node<T,V> leftChild,Node<T,V> rightChild){
            if (this.size == 0){
                this.size ++;
                this.pointers[0] = leftChild;
                this.pointers[1] = rightChild;
                this.keys[0] = key;

                leftChild.parent = this;
                rightChild.parent = this;
                return this;
            }
            Object[] newKeys = new Object[MAX_CHILDREN_FOR_INTERNAL + 1];
            Node[] newPointers = new Node[MAX_CHILDREN_FOR_INTERNAL + 2];
            int i = 0;
            for (;i<this.size;i++){
                T curKey = (T)this.keys[i];
                if (curKey.compareTo(key) > 0) break;
            }
            System.arraycopy(this.keys,0,newKeys,0,i);
            newKeys[i] = key;
            System.arraycopy(this.keys,i,newKeys,i+1,this.size-i);

            System.arraycopy(this.pointers,0,newPointers,0,i+1);
            newPointers[i+1] = rightChild;
            System.arraycopy(this.pointers,i+1,newPointers,i+2,this.size-i);
            this.size++;
            if (this.size<=MAX_CHILDREN_FOR_INTERNAL){
                System.arraycopy(newKeys,0,this.keys,0,this.size);
                System.arraycopy(newPointers,0,this.pointers,0,this.size+1);
                return null;
            }
            int m = (this.size / 2);
            InternalNode<T,V> newNode = new InternalNode<>();
            newNode.size = this.size - m - 1;
            System.arraycopy(newKeys,m + 1,newNode.keys,0,this.size-m-1);
            System.arraycopy(newPointers,m+1,newNode.pointers,0,this.size - m);
            for (int j = 0; j<=newNode.size;j++){
                newNode.pointers[j].parent = newNode;
            }
            this.size = m;
            this.keys = new Object[MAX_CHILDREN_FOR_INTERNAL];
            this.pointers = new Node[MAX_CHILDREN_FOR_INTERNAL];
            System.arraycopy(newKeys,0,this.keys,0,m);
            System.arraycopy(newPointers,0,this.pointers,0,m+1);
            if (this.parent == null){
                this.parent = new InternalNode<>();
            }
            newNode.parent = this.parent;
            return ((InternalNode)this.parent).insert((T) newKeys[m], this, newNode);
        }

        @Override
        public String toString() {
            return "InternalNode{" +
                    "pointers=" + Arrays.toString(pointers) +
                    '}';
        }
    }
    class LeafNode<T extends Comparable<T>,V> extends Node<T,V>{
        private Object[] values;
        public LeafNode(){
            this.size = 0;
            this.keys = new Object[MAX_FOR_LEAF];
            this.values = new Object[MAX_FOR_LEAF];
            this.parent = null;
        }

        public Node<T,V> insert(T key,V value){
            Object[] newKeys = new Object[MAX_FOR_LEAF + 1];
            Object[] newValues = new Object[MAX_FOR_LEAF + 1];
            int i = 0;
            for (;i<this.size;i++){
                T curKey = (T)this.keys[i];
                if (curKey.compareTo(key) == 0){
                    this.values[i] = value;
                    return null;
                }
                if (curKey.compareTo(key)>0) break;
            }
            System.arraycopy(this.keys,0,newKeys,0,i);
            newKeys[i] = key;
            System.arraycopy(this.keys,i,newKeys,i+1,this.size - i);

            System.arraycopy(this.values,0,newValues,0,i);
            newValues[i] = value;
            System.arraycopy(this.values,i,newValues,i+1,this.size - i);

            this.size ++ ;
            if (this.size <= MAX_FOR_LEAF){
                System.arraycopy(newKeys,0,this.keys,0,this.size);
                System.arraycopy(newValues,0,this.values,0,this.size);
                return null;
            }
            int m = this.size / 2;
            this.keys = new Object[MAX_FOR_LEAF];
            this.values = new Object[MAX_FOR_LEAF];
            System.arraycopy(newKeys,0,this.keys,0,m);
            System.arraycopy(newValues,0,this.values,0,m);
            LeafNode<T,V> newNode = new LeafNode<>();
            newNode.size = this.size - m;
            System.arraycopy(newKeys,m,newNode.keys,0,newNode.size);
            System.arraycopy(newValues,m,newNode.values,0,newNode.size);
            this.size = m;
            if (this.parent == null){
                this.parent = new InternalNode<>();
            }
            newNode.parent = this.parent;
            return ((InternalNode<T,V>)this.parent).insert((T)newNode.keys[0],this,newNode);

        }

        public V get(T key){
            if (this.size == 0) return null;
            int start = 0;
            int end = this.size;
            int middle = (start + end) / 2;
            while (start < end){
                T middleKey = (T)this.keys[middle];
                if (key.compareTo(middleKey) == 0)break;
                if (key.compareTo(middleKey) < 0) end = middle;
                if (key.compareTo(middleKey) > 0) start = middle;
            }
            T middleKey = (T)this.keys[middle];
            return middleKey.compareTo(key) == 0 ?(V)this.values[middle]:null;
        }

        @Override
        public String toString() {
            return "LeafNode{" +
                    "values=" + Arrays.toString(values) +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BPlusTree{" +
                "factor=" + factor +
                ", MIN_CHILDREN_FOR_INTERNAL=" + MIN_CHILDREN_FOR_INTERNAL +
                ", MAX_CHILDREN_FOR_INTERNAL=" + MAX_CHILDREN_FOR_INTERNAL +
                ", MIN_FOR_LEAF=" + MIN_FOR_LEAF +
                ", MAX_FOR_LEAF=" + MAX_FOR_LEAF +
                ", root=" + root +
                '}';
    }

    public static void main(String[] args){
        BPlusTree<Integer,String> myTree = new BPlusTree<>();
        int max  = 10000;
        long start = System.currentTimeMillis();
        for (int i = 0;i<max;i++){
            myTree.set(i,String.valueOf(i));
            System.out.println(myTree.toString());
        }
        System.out.println("time cost with BPlusTree:" + (System.currentTimeMillis() -  start));
        System.out.println("Data has bee inserted into tree");
        System.out.println("height:" + myTree.height());
        System.out.println(myTree.toString());
        start = System.currentTimeMillis();
        Map<Integer,String> hasMap = new HashMap<>();
        for (int i = 0; i < max; i ++){
            hasMap.put(i,String.valueOf(i));
        }

        System.out.println("time cost with hashmap:" + (System.currentTimeMillis() - start));
        for (int i = 0;i < max;i++){
            if (!String.valueOf(i).equals(myTree.get(i))){
                System.err.println("error for :" + i);
            }
        }
        System.out.println("success");

    }
}
