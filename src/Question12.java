import java.util.Arrays;

/**
 * 面试题12：矩阵中的路径
 *
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格
 * 开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进
 * 入该格子。例如在下面的3X4的矩阵中包含一条字符串“bfc‘e"的路径，但矩阵中不包含字符串”abfb"的路径，
 * 因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 *
 * a    b   t   g
 * c    f   c'  s
 * j    d   e   h
 *
 */
public class Question12 {

    static boolean hasPath(char[][] matrix, int rows, int columns, char[] str){
        if (matrix == null || rows < 1 || columns < 1 || str == null) {
            return false;
        }
        //由于路径不能重复进入矩阵的格子，所以需要定义一个和字符矩阵大小一样的布尔值矩阵，用来标识路径是否已经进入了每个格子
        boolean[][] visited = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(visited[i], false);
        }

        int pathLength = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (hasPathCore(matrix, rows, columns, row, col, str, pathLength, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean hasPathCore(char[][] matrix, int rows, int columns, int row, int col, char[] str, int pathLength, boolean[][] visited) {

        if (pathLength == str.length) {
            return true;
        }
        boolean hasPath = false;
        if (row >= 0 && row < rows && col >= 0 && col < columns
                && matrix[row][col] == str[pathLength] && !visited[row][col]) {
            ++pathLength;
            visited[row][col] = true;
            hasPath = hasPathCore(matrix, rows, columns, row, col - 1, str, pathLength, visited)   //左边
                    || hasPathCore(matrix, rows, columns, row - 1, col, str, pathLength, visited)  //上边
                    || hasPathCore(matrix, rows, columns, row, col + 1, str, pathLength, visited)   //右边
                    || hasPathCore(matrix, rows, columns, row + 1, col, str, pathLength, visited);  //下边
            //如果4个相邻的格子都没有匹配字符串中下标为pathLength+1的字符，则
            //表明当前路径字符串中下标为pathLength的字符在矩阵中的定位不正确，
            //需要回退到前一个字符（pathLength-1），然后重新定位
            if (!hasPath) {
                --pathLength;
                visited[row][col] = false;
            }
        }
        return hasPath;
    }
    public static void main(String[] args) {
        char[][] matrix = {{'a', 'b', 't', 'g'}, {'c', 'f', 'c', 's'}, {'j', 'd', 'e', 'h'}};
        System.out.println("hasPath bfce: " + hasPath(matrix, 3, 4, "bfce".toCharArray()));
        System.out.println("hasPath abfb: " + hasPath(matrix, 3, 4, "abfb".toCharArray()));
    }

}