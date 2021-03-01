/**
 * 面试题29：顺时针打印矩阵
 *
 * 输入一个矩阵，按照从外到里以顺时针的顺序依次打印出每一个数字。例如，如果输入如下矩阵：
 *  1   2   3   4
 *  5   6   7   8
 *  9   10  11  12
 *  13  14  15  16
 *
 * 则依次打印出数字：1，2，3，4，8，12，16，15，14，13，9，5，6，7，11，10。
 *
 */
public class Question29 {

    // 由于是以从外圈到内圈的顺序依次打印的，所以我们可以把矩阵想象成若干个圈，从而可以通过一个循环来打印矩阵中的一个圈。
    // 假设一个矩阵的行数是rows，列数是columns。打印第一圈的左上角的坐标是（0，0），第二圈的左上角的坐标是（1，1），
    // 以此类推。我们注意到，左上角的坐标中行标和列表总是相同的，于是可以在矩阵中选取左上角为(start,start)的一圈作为
    // 我们的分析目标。
    // 对于一个5x5的矩阵而言，最后一圈只有一个数字，对应的坐标为(2，2)。我们发现5>2x2。对于一个6x6的矩阵而言，最后一圈
    // 有4个数字，其左上角的坐标仍然为(2,2)，6>2x2依然成立。于是，可以得出让循环继续的条件是columns>startX*2并且rows>startY*2。
    static void printMatrixClockwisely(int[][] matrix, int columns, int rows) {
        if (matrix == null) {
            return;
        }
        int start = 0;
        while (columns > start * 2 && rows > start * 2) {
            printMatrixInCircle(matrix, columns, rows, start);
            ++start;
        }
    }

    // 我们可以把打印一圈分为四步：①从左到右打印一行 ②从上到下打印一列 ③从右往左打印一行 ④从下到上打印一列。
    // 每一步我们根据起始坐标和终止坐标用一个循环就能打印出一行或者一列。但是，最后一圈有可能退化成只有一行、只有一列，
    // 甚至只有一个数字，因此打印这样的一圈就不再需要四步。因此，我们需要仔细分析打印时每一步的前提条件。
    // 第一步总是需要的，因为打印一圈至少有一步。第二步的前提条件是终止行号要大于起始行号。第三步的前提条件是圈内至少
    // 有两行两列，也就是说，除了要求终止行号大于起始行号，还要求终止列号大于起始列号。同理，第四步的前提条件是至少
    // 有三行两列，因此要求终止行号比起始行号至少大2，同时终止列号大于起始列号。
    private static void printMatrixInCircle(int[][] matrix, int columns, int rows, int start) {
        int endX = columns - 1 - start;
        int endY = rows - 1 - start;

        // 从左到右打印一行
        for (int i = start; i <= endX; i++) {
            int number = matrix[start][i];
            System.out.print(number + "\t");
        }

        // 从上到下打印一列
        if (start < endY) {
            for (int i = start+1; i <= endY; i++) {
                int number = matrix[i][endX];
                System.out.print(number + "\t");
            }
        }

        // 从右到左打印一行
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; i--) {
                int number = matrix[endY][i];
                System.out.print(number + "\t");
            }
        }

        // 从下到上打印一列
        if (start < endX && start < endY - 1) {
            for (int i = endY - 1; i >= start + 1; i--) {
                int number = matrix[i][start];
                System.out.print(number + "\t");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        printMatrixClockwisely(matrix,4,4);
    }

}