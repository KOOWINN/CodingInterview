/**
 * 面试题20：表示数值的字符串
 *
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串“+100”、“5e2"、"-123"、"3.1416"
 * 及"-1E-16"都表示数值，但”12e"、”1a3.14“、”1.2.3“、”+-5“及”12e+5.4"都不是。
 *
 */
public class Question20 {
    // 数字的格式可以用A[.[B]][(e|E)C]或.B[(e|E)C]表示，其中A和C都是整数（可以有正负号，也可以没有），而B是一个无符号整数
    // 因此，需要判断一个字符串是否满足上述模式，首先要尽可能多地扫描0~9的数位，也就是表示数值整数的A部分；如果遇到小数点'.'，
    // 则开始扫描表示数值小数的B部分；如果遇到'e'或'E'，则开始扫描表示数值指数的C部分
    static boolean isNumeric(String input) {
        if (input == null) {
            return false;
        }
        char[] str = input.toCharArray();
        int index = 0;
        int aCount = scanInteger(str, 0);
        boolean numeric = aCount > 0;
        index += aCount;
        //如果出现小数点，则接下来时数字的小数部分
        if (index < str.length && str[index] == '.') {
            int bCount = scanUnsignedInteger(str, ++index);
            // 用||的原因：
            // 1. 小数部分可以没有整数部分，如.123等于0.123
            // 2. 小数点后面可以没有数字，如233.等于233.0
            // 3. 当然，小数点前面和后面可以都有数字，如233.666
            numeric = numeric || bCount > 0;
            index += bCount;
        }

        // 如果出现'e'或'E'，则接下来是数字的指数部分
        if (index < str.length && (str[index] == 'e' || str[index] == 'E')) {
            int cCount = scanInteger(str, ++index);
            if (cCount == 1 && (str[index] < '0' || str[index] > '9')) {
                return false;
            }
            // 用&&的原因：
            // 当e或E前面没有数字时，整个字符串不能表示数字，如.e1、e1;
            // 当e或E后面没有数字时，整个字符串不能表示数字，如12e、12e+5.4
            numeric = numeric && cCount > 0;
            index += cCount;
        }

        return numeric && index == str.length;
    }

    //用来扫描可能以表示正负的'+'或'-'为起始的0~9的数位（类似于一个可能带正负符号的整数），
    //用来匹配前面数值模式中的A和C部分。
    private static int scanInteger(char[] str, int index) {
        int forward = 0;
        if (str[index] == '+' || str[index] == '-') {
            forward++;
        }
        return forward + scanUnsignedInteger(str, index+forward);
    }

    //扫描字符串中0~9的位数（类似于一个无符号整数），可以用来匹配前面数值模式中的B部分
    private static int scanUnsignedInteger(char[] str, int index) {
        int forward = 0;
        while (index < str.length && str[index] >= '0' && str[index] <= '9') {
            index++;
            forward++;
        }
        return forward;
    }

    public static void main(String[] args) {
        boolean ret = isNumeric("-1E-16");
        System.out.println("isNumeric: " + ret);
    }
}