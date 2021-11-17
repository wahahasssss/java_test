package com.hdu.base.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName：TreeAlgorithmUtil
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/4/20 4:24 下午
 * @Versiion：1.0
 */
public class TreeAlgorithmUtil {
    /**
     * 二叉树翻转
     *
     * @param tree
     * @return
     */
    public static void swapTree(TreeNode tree) {
        if (tree != null) {
            TreeNode tmp = tree.getLeft();
            tree.setLeft(tree.getRight());
            tree.setRight(tmp);
        }
        if (tree.getLeft() != null) {
            swapTree(tree.getLeft());
        }
        if (tree.getRight() != null) {
            swapTree(tree.getRight());
        }
    }

    /**
     * 前序遍历
     *
     * @param tree
     * @return
     */
    public static void preOrder(TreeNode tree, List<Integer> treeList) {
        treeList.add(tree.getValue());

        if (tree.getLeft() != null) {
            preOrder(tree.getLeft(), treeList);

        }
        if (tree.getRight() != null) {
            preOrder(tree.getRight(), treeList);
        }
    }

    /**
     * 中序遍历
     *
     * @param tree
     * @return
     */
    public static void midOrder(TreeNode tree, List<Integer> treeList) {

        if (tree.getLeft() != null) {
            midOrder(tree.getLeft(), treeList);

        }
        treeList.add(tree.getValue());
        if (tree.getRight() != null) {
            midOrder(tree.getRight(), treeList);
        }
    }


    /**
     * 后续遍历
     *
     * @param tree
     * @return
     */
    public static void postOrder(TreeNode tree, List<Integer> treeList) {

        if (tree.getLeft() != null) {
            postOrder(tree.getLeft(), treeList);

        }
        if (tree.getRight() != null) {
            postOrder(tree.getRight(), treeList);
        }
        treeList.add(tree.getValue());
    }


    public static boolean isBalanced(TreeNode root) {
        if (root != null) {

        }
        return true;
    }


    public static Integer depth(TreeNode root, Integer depth) {
        if (root.left == null && root.right == null) {
            return depth;
        } else {
            depth++;
        }
        Integer leftDepth = 0;
        if (root.left != null) {
            leftDepth = depth(root.left, depth);
        }
        Integer rightDepth = 0;
        if (root.right != null) {
            rightDepth = depth(root.right, depth);
        }
        return Math.max(leftDepth.intValue(), rightDepth.intValue());

    }

    public static Boolean secure(TreeNode root) {

        Integer leftDepth = 0;
        if (root.left != null) {
            leftDepth = depth(root.left, 0);
        }
        Integer rightDepth = 0;
        if (root.right != null) {
            rightDepth = depth(root.right, 0);
        }
        int depthSub = Math.abs(leftDepth.intValue() - rightDepth.intValue());

        if (depthSub > 1) {
            return false;
        }

        if (root.left != null) {
            return secure(root.left);
        }

        if (root.right != null) {
            return secure(root.right);
        }
        return true;
    }


    /**
     * 平衡二叉树
     *
     * @param n
     * @return
     */
    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }

    public static List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<TreeNode>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);

            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }

}