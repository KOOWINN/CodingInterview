/**
 * 面试题17：打印从1到最大的n位数
 *
 * 输入数字n，按顺序打印出从1到最大的n位十进制数。比如输入3，则打印出1、2、3一直到最大的3位数999.
 *
 */
public class Question17 {

    //当n很大时，用整型或长整型来存储数字就会溢出，此时更适合用字符串或字符数组来表示，用‘0’~‘9’之间的某个字符来表示数字中的一位
    //当用上述方式来表示所有的n位十进制数时，我们可以可以发现，其实就是n个'0'到'9'的全排列，也就是说，我们把数字的每一位从0到9排一遍，
    //就可以得到所有的十进制数。只是在打印的时候，排在前面的0不打印出来即可。
    static void print1ToMaxOfDigits(int n){
        if (n <= 0) {
            return;
        }
        char[] number = new char[n];
        print1ToMaxOfDigitsRecursively(number, n, 0);
    }

    //全排列用递归很容易表达，数字的每一位都可能时0~9中的一个数，然后设置下一位。递归结束的条件是我们已经设置了数字的最后一位。
    private static void print1ToMaxOfDigitsRecursively(char[] number, int length, int index) {
        if (index == length) {
            printNumber(number);
            return;
        }
        for (int i = 0; i < 10; i++) {
            number[index] = (char) (i + '0');
            //递归设置下一位
            print1ToMaxOfDigitsRecursively(number, length, index+1);
        }
    }

    //只有在碰到第一个非0的字符之后才开始打印，这样才符合我们的阅读习惯
    private static void printNumber(char[] number) {
        boolean isBeginning0 = true;
        int nLength = number.length;
        for (int i = 0; i < nLength; i++) {
            if (isBeginning0 && number[i] != '0') {
                isBeginning0 = false;
            }
            if (!isBeginning0) {
                System.out.print(number[i]);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        print1ToMaxOfDigits(5);
    }

}