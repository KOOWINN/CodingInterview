import java.util.Arrays;

/**
 * 面试题13：机器人的运动范围
 *
 * 地上有一个m行n列的方格。一个机器人从坐标(0,0)的格子开始移动，它每次可以向左、右、上、下移动一格，
 * 但不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k=18时，机器人能够进入方格（35，37），
 * 因为3+5+3+7=18。但它你能进入方格（35，38），因为3+5+3+8=19，请问该机器人能够到达多少个格子？
 *
 */
public class Question13 {

    static int movingCount(int threshold, int rows, int columns){
        if (threshold < 0 || rows <= 0 || columns <= 0) {
            return -1;
        }
        boolean[][] visited = new boolean[rows][columns];
        for (int row = 0; row < rows; row++) {
            Arrays.fill(visited[row], false);
        }
        //从坐标（0，0）开始
        return movingCountCore(threshold, rows, columns, 0, 0, visited);
    }

    static int movingCountCore(int threshold, int rows, int columns, int row, int col, boolean[][] visited) {

        int count = 0;
        if (check(threshold, rows, columns, row, col, visited)) {
            visited[row][col] = true;
            //判断能够进入当前格子的4个相邻格子
            count = 1 + movingCountCore(threshold, rows, columns, row, col - 1, visited)
                    + movingCountCore(threshold, rows, columns, row - 1, col, visited)
                    + movingCountCore(threshold, rows, columns, row, col + 1, visited)
                    + movingCountCore(threshold, rows, columns, row + 1, col, visited);
        }
        return count;
    }

    //检查当前格子坐标是否在指定范围内、行坐标和列坐标的数位之和不超过阈值，且未被访问过
    private static boolean check(int threshold, int rows, int columns, int row, int col, boolean[][] visited) {
        return row >= 0 && row < rows && col >= 0 && col < columns
                && getDigitSum(row) + getDigitSum(col) <= threshold
                && !visited[row][col];
    }

    //用来得到一个数字的数位之和
    private static int getDigitSum(int number) {
        int sum = 0;
        while (number>0) {
            sum += number % 10;
            number = number / 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("Moving count: " + movingCount(18, 10, 10));
    }

}