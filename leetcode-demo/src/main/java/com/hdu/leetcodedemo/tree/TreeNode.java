package com.hdu.leetcodedemo.tree;

/**
 * @ClassName：Tree
 * @Description：二叉树结构
 * @Author：ssf
 * @Date：2021/4/20 3:31 下午
 * @Versiion：1.0
 */
public class TreeNode {
    TreeNode left;
    TreeNode right;
    Integer value;

    public TreeNode(TreeNode left, TreeNode right, Integer value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public TreeNode() {
    }

    public TreeNode(int i) {
        this.value = i;
    }


    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


}