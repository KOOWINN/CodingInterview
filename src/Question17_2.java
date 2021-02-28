/**
 * 面试题17_2：实现任意两个正整数的加法
 *
 * 由于没有限定两个数的大小范围，我们也要把它当作大数问题来处理。
 *
 */
public class Question17_2 {

    static char[] add(char[] a, char[] b) throws Exception{
        if (a == null || b == null || a.length <= 0 || b.length <= 0) {
            throw new Exception("Invalid input!");
        }
        if (a.length > b.length) {
            char[] tmp = b;
            b = a;
            a = tmp;
        }
        char[] result = new char[b.length + 1];
        int nTakeOVer = 0;
        for (int i = result.length - 1; i >= 0; i--) {
            if (i==0)
                result[i] = '0';
            else
                result[i] = b[i-1];
            int j = a.length - (result.length - i);
            if (j < 0) {
                if (nTakeOVer == 0) {
                    break;
                }
                result[i] = (char)(result[i] + nTakeOVer);
            } else {
                result[i] = (char)(result[i] + a[j] - '0' + nTakeOVer);
            }

            if (result[i] > '9') {
                result[i] = (char)(result[i] - 10);
                nTakeOVer = 1;
            } else {
                nTakeOVer = 0;
            }
        }
        return result;
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
        try {
            printNumber(add("12345".toCharArray(),"99999".toCharArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}