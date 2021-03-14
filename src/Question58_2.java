/**
 * 面试题58_2：左旋转字符串
 *
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 */
public class Question58_2 {

    // 从解决第一个问题的思路中得到启发：
    // 以"abcdefg"为例，我们可以把它分为两部分。由于想把它的前两个字符移到后面，我们可以把前两个字符分到第一部分，
    // 把后面的所有字符分到第二部分。我们先分别翻转这两部分，于是就得到"bafgedc"。接下来翻转整个字符串，得到的
    // "cdegfab"刚好就是把原始字符串左旋转两位的结果。
    // 通过上述分析，我们发现只需要调用3次58_1中的reverse函数就可以实现原始字符串的左旋转功能。
    static String leftRotateString(String pStr, int n) {
        if (pStr == null || n<0 || n>=pStr.length()) {
            return null;
        }
        char[] pData = pStr.toCharArray();
        int pFirstStart = 0;
        int pFirstEnd = n-1;
        int pSecondStart = n;
        int pSecondEnd = pData.length-1;

        // 翻转字符串的前n个字符
        Question58_1.reverse(pData, pFirstStart, pFirstEnd);
        // 翻转字符串的后面部分
        Question58_1.reverse(pData, pSecondStart, pSecondEnd);
        // 翻转整个字符串
        Question58_1.reverse(pData, pFirstStart, pSecondEnd);

        return String.copyValueOf(pData);
    }

    public static void main(String[] args) {
        System.out.println(leftRotateString("abcdefg", 2));
    }
}