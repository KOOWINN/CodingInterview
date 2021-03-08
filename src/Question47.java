/**
 * 面试题47：礼物的最大值
 * 在一个mxn的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于0）。你可以从棋盘的左上角开始拿格子里的礼物，
 * 并每次向右或向下移动一格，直到到达棋盘的右下角。给定一个棋盘及其上面的礼物，请计算你最多能拿到多少价值的礼物？
 * 
 */
public class Question47 {
    // 这是一个典型的动态规划问题。先用递归的思路来分析。我们先定义一个函数f(i,j)表示到达坐标为(i,j)的格子时
    // 能拿到的礼物总和的最大值。根据题目要求，我们有两种可能的途径到达坐标为(i,j)的格子：通过格子(i-1.j)或
    // (i,j-1)。所以f(i,j)=max(f(i-1,j), f(i,j-1))+gift[i,j]。gift[i,j]表示坐标为(i,j)的格子里礼
    // 物的价值。
    // 尽管我们用递归分析问题，但是由于有大量重复的计算，导致递归代码不是最优的。相对而言，基于循环的代码效率要
    // 高很多。为了缓存中间结果，我们需要一个辅助的二维数组。数组种坐标为(i,j)的元素表示到达坐标为(i,j)的格子时
    // 能拿到的礼物价值总和的最大值。
    static int getMaxValueSolution1(int[][] values, int rows, int cols) {
        if (values == null || rows<=0 || cols<=0) {
            return 0;
        }
        int[][] maxValues = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int left = 0;
                int up = 0;
                if (i > 0) {
                    up = maxValues[i - 1][j];
                }
                if (j > 0) {
                    left = maxValues[i][j - 1];
                }
                maxValues[i][j] = Math.max(up, left) + values[i][j];
            }
        }
        return maxValues[rows - 1][cols - 1];
    }

    // 前面提到，到达坐标为(i,j)的格子时能够拿到的礼物的最大价值只依赖坐标为(i-1,j)和(i,j-1)的两个格子，
    // 因此第i-2行及更上面的所有格子礼物的最大价值实际上没有必要保存下来。我们可以用一个一维数组来替代前面
    // 代码种的二维矩阵maxValues。该一维数组的长度为棋盘的列数n。当我们计算到达坐标为(i,j)的格子时能够拿
    // 到的礼物最大价值f(i,j)，数组中前j个数字分别是f(i,0)，f(i,1)，……，f(i,j-1)，数组从下标为j的数字
    // 开始到最后一个数字，分别为f(i-1，j)，f(i-1,j+1)，……，f(i-1,n-1)。也就是说，该数组前面j个数字分别
    // 是当前第i行前面j个格子礼物的最大价值，而之后的数字分别保存前面第i-1行n-j个格子礼物的最大价值。
    static int getMaxValueSolution2(int[][] values, int rows, int cols) {
        if (values == null || rows <= 0 || cols <= 0) {
            return 0;
        }
        int[] maxValues = new int[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int left = 0;
                int up = 0;
                if (i > 0) {
                    left = maxValues[j];
                }
                if (j > 0) {
                    up = maxValues[j - 1];
                }
                maxValues[j] = Math.max(left, up) + values[i][j];
            }
        }
        return maxValues[cols - 1];
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 10, 3, 8}, {12, 2, 9, 6}, {5, 7, 4, 11}, {3, 7, 16, 5}};
        System.out.println("The max value=" + getMaxValueSolution1(matrix, 4, 4));
        System.out.println("The max value=" + getMaxValueSolution2(matrix, 4, 4));
    }
}