package com.hdu.leetcodedemo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName：TreeMain
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/4/20 3:33 下午
 * @Versiion：1.0
 */
public class TreeMain {
    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.setValue(1);


        TreeNode l1 = new TreeNode();
        l1.setValue(2);


        TreeNode r1 = new TreeNode();
        r1.setValue(3);

        TreeNode l21 = new TreeNode();
        l21.setValue(4);
        TreeNode l22 = new TreeNode();
        l22.setValue(5);

        TreeNode l31 = new TreeNode();
        l31.setValue(8);
        TreeNode l41 = new TreeNode();
        l41.setValue(9);
        l31.left = l41;
//        l21.left = l31;


        TreeNode r21 = new TreeNode();
        r21.setValue(6);
        TreeNode r22 = new TreeNode();
        r22.setValue(7);


        root.setLeft(l1);
        root.setRight(r1);
        l1.setLeft(l21);
//        l1.setRight(l22);

        r1.setLeft(r21);
        r1.setRight(r22);


        System.out.println(root.toString());


//        TreeAlgorithmUtil.swapTree(root);
//        System.out.println(root);


        //前序遍历
        List<Integer> sortList = new ArrayList<>();
        TreeAlgorithmUtil.preOrder(root, sortList);
        System.out.println(sortList);


        //中序遍历
        List<Integer> midSortList = new ArrayList<>();
        TreeAlgorithmUtil.midOrder(root, midSortList);
        System.out.println(midSortList);


        //后序遍历
        List<Integer> postSortList = new ArrayList<>();
        TreeAlgorithmUtil.postOrder(root, postSortList);
        System.out.println(postSortList);


        System.out.println(TreeAlgorithmUtil.depth(root, 0));


        System.out.println(TreeAlgorithmUtil.secure(root));


        List<TreeNode> treeNodes = TreeAlgorithmUtil.generateTrees(3);
        System.out.println(treeNodes);


        List<String> paths = TreeAlgorithmUtil.binaryTreePaths(root);
        System.out.println(paths);


        int minDepth = TreeAlgorithmUtil.minDepth(root);
        System.out.println("min depth is " + minDepth);


        //二叉搜索树
        List<TreeNode> searchTree = TreeAlgorithmUtil.generateTreesSearch(4);
        System.out.println(searchTree);

        //二叉搜索树 种类
        int num = TreeAlgorithmUtil.numTrees(19);
        System.out.println("二叉搜索树的数量为： " + num);

        // 最大秋活
        int max = TreeAlgorithmUtil.maxCoins(new int[]{3, 1, 5, 8});
        System.out.println("最大硬币数为：" + max);

    }


}