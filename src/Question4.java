/**
 * 面试题4：二维数组中的查找
 *
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 例如下面的二维数组就是每行、每列都递增排序。如果在这个数组中查找数字7，则返回true;
 * 如果查找数字5，由于数组不含有该数字，则返回false。
 * 1    2   8   9
 * 2    4   9   12
 * 4    7   10  13
 * 6    8   11  15
 *
 * 时间复杂度O(n)，空间复杂度O(1)
 */
public class Question4 {
    //首先选取数组中右上角的数字。如果该数字等于要查找的数字，则查找过程结束；
    //如果该数字大于要查找的数字，则剔除这个数字所在的列；
    //如果该数字小于要查找的的数字，则剔除这个数字所在的行。
    //也就是说，如果要查找的数字不在数组的右上角，则每一次都在数组的查找范围内剔除一行或者一列，这样每一步都可以缩小查找的范围。
    static boolean find(int[][] matrix, int rows, int columns, int number) {
        boolean found = false;
        if (matrix != null && rows > 0 && columns > 0) {
            int row = 0;
            int column = columns -1;
            while (row < rows && column >= 0) {
                if (matrix[row][column] == number) {
                    found = true;
                    break;
                } else if (matrix[row][column] > number) {
                    --column;
                } else {
                    ++row;
                }
            }
        }
        return found;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        boolean ret = find(matrix, 4, 4, 5);
        System.out.println("result: " + ret);
    }

}