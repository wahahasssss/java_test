package com.hdu.leetcodedemo.tree;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

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


    /**
     * 所有路径
     *
     * @param root
     * @return
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();

        binaryTreePaths(root, paths, "");
        return paths;
    }

    private static void binaryTreePaths(TreeNode root, List<String> paths, String path) {
        if (root.left == null && root.right == null) {
            path = path + root.value;
            paths.add(path);
            return;
        } else {
            path = path + root.value + "->";
        }
        if (root.left != null) {
            binaryTreePaths(root.getLeft(), paths, path);
        }
        if (root.right != null) {
            binaryTreePaths(root.getRight(), paths, path);
        }
    }


    /**
     * 路径和
     *
     * @param root
     * @return
     */
    public static int sumRootToLeaf(TreeNode root) {
        return 0;
    }

    private static void sumRootToLeft(TreeNode root, Integer sum) {

    }


    public int sumOfLeftLeaves(TreeNode root) {

        return sumLeftLeaves(root, 0, false);
    }

    private static int sumLeftLeaves(TreeNode root, int sum, boolean flag) {
        if (flag && root.left == null && root.right == null) {
            sum = sum + root.value;
        }
        if (root.left != null) {
            sum = sumLeftLeaves(root.left, sum, true);
        }
        if (root.right != null) {
            sum = sumLeftLeaves(root.right, sum, false);
        }
        return sum;
    }


    /**
     * 最小深度 - bfs 宽度优先搜索
     *
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        Queue<TreeNode> treeQueue = new LinkedBlockingDeque<>();
        treeQueue.offer(root);
        int depth = 0;
        while (!treeQueue.isEmpty()) {
            int size = treeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = treeQueue.poll();
                if (node.right == null && node.left == null) {
                    return depth;
                }
                if (node.getLeft() != null) {
                    treeQueue.offer(node.getLeft());
                }
                if (node.getRight() != null) {
                    treeQueue.offer(node.getRight());
                }
            }
            depth++;
        }
        return depth;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> treeQueue = new LinkedBlockingDeque<>();
        treeQueue.offer(root);
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        while (!treeQueue.isEmpty()) {
            int size = treeQueue.size();
            List<Integer> levelResult = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = treeQueue.poll();
                levelResult.add(node.getValue());
                if (node.left != null) {
                    treeQueue.offer(node.left);
                }
                if (node.right != null) {
                    treeQueue.offer(node.right);
                }
            }
            results.add(levelResult);
        }
        return results;
    }

    public static List<List<Integer>> levelOrderTwo(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        int depth = 0;
        levelOrderTwo(root, levels, depth);
        return levels;
    }

    private static void levelOrderTwo(TreeNode root, List<List<Integer>> levels, int depth) {
        if (root == null) {
            return;
        }
        if (levels.get(depth) == null) {
            levels.add(new ArrayList<>());
        }
        levels.get(depth).add(root.value);
        if (root.left != null) {
            levelOrderTwo(root.left, levels, depth + 1);
        }
        if (root.right != null) {
            levelOrderTwo(root.right, levels, depth + 1);
        }
    }


    /**
     * 不同的二叉搜索树给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。， 二叉搜索树， 左根右 递增
     * todo:优化方向一，缓存
     *
     * @param
     * @return
     */
    public static List<TreeNode> generateTreesSearch(int n) {
        List<TreeNode> nodes = new ArrayList<>();
        for (int i = 1; i <= n; i++) {

            TreeNode[] leftNodes = generateChildTreeSearch(1, i - 1);
            for (TreeNode treeNode : leftNodes) {
                TreeNode[] rightNodes = generateChildTreeSearch(i + 1, n);
                for (TreeNode rt : rightNodes) {
                    TreeNode root = new TreeNode();
                    root.left = treeNode;
                    root.value = i;
                    root.right = rt;
                    nodes.add(root);
                }
            }
        }
        return nodes;
    }

    private static TreeNode[] generateChildTreeSearch(int begin, int end) {
        if (end < begin) {
            return new TreeNode[]{null};
        }
        List<TreeNode> nodes = new ArrayList<>();
        for (int i = begin; i <= end; i++) {
            TreeNode[] leftNodes = generateChildTreeSearch(begin, i - 1);
            TreeNode[] rightNodes = generateChildTreeSearch(i + 1, end);
            for (TreeNode lt : leftNodes) {
                for (TreeNode rt : rightNodes) {
                    TreeNode root = new TreeNode();
                    root.setValue(i);
                    root.setLeft(lt);
                    root.setRight(rt);
                    nodes.add(root);
                }
            }
        }
        TreeNode[] results = new TreeNode[nodes.size()];
        nodes.toArray(results);
        return results;
    }


    /**
     * 96. 不同的二叉搜索树
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     *
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        int sum = 0;
        Map<String, Integer> numMap = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int l = generateChildTreeSearchNums(1, i - 1, numMap);
            int r = generateChildTreeSearchNums(i + 1, n, numMap);
            String leftKey = String.format("%s_%s", 1, i - 1);
            String rightKey = String.format("%s_%s", i + 1, n);
            if (!numMap.containsKey(leftKey)) {
                numMap.put(leftKey, l);
            }
            if (!numMap.containsKey(rightKey)) {
                numMap.put(rightKey, r);
            }
            sum = sum + l * r;
        }
        return sum;
    }

    private static int generateChildTreeSearchNums(int begin, int end, Map<String, Integer> numMap) {
        if (end < begin) {
            return 1;
        }
        int sum = 0;
        for (int i = begin; i <= end; i++) {
            String leftKey = String.format("%s_%s", begin, i - 1);
            String rightKey = String.format("%s_%s", i + 1, end);
            int l;
            if (numMap.containsKey(leftKey)) {
                l = numMap.get(leftKey);
            } else {
                l = generateChildTreeSearchNums(begin, i - 1, numMap);
                numMap.put(leftKey, l);
            }
            int r;
            if (numMap.containsKey(rightKey)) {
                r = numMap.get(rightKey);
            } else {
                r = generateChildTreeSearchNums(i + 1, end, numMap);
                numMap.put(rightKey, r);
            }


            sum = sum + l * r;
        }

        return sum;
    }


    /**
     * Leecode 戳气球-回溯算法
     *
     * @param nums
     * @return
     */
    public static int maxCoins(int[] nums) {
        int max = 0;
        int deep = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = i - 1 < 0 ? 1 : nums[i - 1];
            int mid = nums[i];
            int right = i + 1 >= nums.length ? 1 : nums[i + 1];
            int s = left * mid * right;
            int[] subNums = removeElement(nums, i);
            String key = StringUtils.join(subNums, "_");
            String path = String.valueOf(mid);
            max = Math.max(max, calMaxCoins(subNums, max, s, path));
        }
        return max;
    }

    private static int[] removeElement(int[] nums, int index) {
        int[] b = new int[nums.length - 1];
        int bIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (index == i) {
                continue;
            }
            b[bIndex] = nums[i];
            bIndex++;
        }
        return b;
    }

    private static int calMaxCoins(int[] childNums, Integer max, int sum, String path) {
        int[] nums = Arrays.copyOf(childNums, childNums.length);
        if (nums.length == 0) {
            max = Math.max(max, sum);
            System.out.println(path);
            return max;
        }
        for (int i = 0; i < nums.length; i++) {
            int left = i - 1 < 0 ? 1 : nums[i - 1];
            int mid = nums[i];
            int right = i + 1 >= nums.length ? 1 : nums[i + 1];
            int s = left * mid * right;
            int[] subNums = removeElement(nums, i);
            max = Math.max(max, calMaxCoins(subNums, max, s + sum, path + ">" + mid));
        }
        return max;
    }


}