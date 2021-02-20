/**
 * 面试题5：替换空格
 *
 * 请实现一个函数，把字符串中的每个空格替换成“%20”。例如输入“We are happy."，则输出"We%20are%20happy."。
 *
 * 时间复杂度O(n)，空间复杂度O(1)
 */
public class Question5 {
    //如果从前往后复制每个数字（或字符）需要重复移动数字（或字符）多次，
    //那么我们可以考虑从后往前复制，这样就能减少移动的次数，从而提高效率
    static void replaceBlank(StringBuffer string) {
        if (string == null || string.length() <= 0) {
            return;
        }
        //p1指向原始字符串的末尾
        int p1 = string.length() - 1;
        for (int i = 0; i <= p1; i++) {
            if (string.charAt(i) == ' ') {
                string.append("  ");
            }
        }
        //p2指向替换之后的字符串的末尾
        int p2 = string.length() -1;
        while (p1 >= 0 && p2 > p1) {
            char c = string.charAt(p1);
            if (c == ' ') {
                string.setCharAt(p2--, '0');
                string.setCharAt(p2--, '2');
                string.setCharAt(p2--, '%');
            } else {
                string.setCharAt(p2--, c);
            }
            --p1;
        }
    }

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer();
        str.append("We are happy.");
        replaceBlank(str);
        System.out.println("result: " + str.toString());
    }

}