/**
 * 面试题19：正则表达式匹配
 *
 * 请实现一个函数用来匹配包含"."和"*"的正则表达式。模式中的字符"."表示任意一个字符，而"*"表示它前面的字符可以出现任意次（含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 *
 *
 */
public class Question19 {

    static boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        return matchCore(str, pattern, 0, 0);
    }

    // 当模式中的第二个字符不是'*'时，如果字符串中的第一个字符和模式中的第一个字符相匹配，
    // 那么在字符串和模式上都向后移动一个字符，然后匹配剩余的字符串和模式。如果字符串中的
    // 第一个字符和模式中的第一个字符不相匹配，则直接返回false。
    //
    // 当模式中的第二个字符是'*'时，问题要复杂一些，因为可能存在多种不同的匹配方式。
    // 一种选择是在模式上向后移动两个字符，这相当于'*'和它前面的字符被忽略了，因为'*'可以匹配字符串中的0个字符。
    // 如果模式中的第一个字符和字符串中的第一个字符相匹配，则在字符串上向后移动一个字符，而在模式上有两种选择：
    // 可以在模式上向后移动两个字符，也可以保持模式不变。
    private static boolean matchCore(char[] str, char[] pattern ,int strIndex, int patternIndex) {
        if (strIndex == str.length - 1 && patternIndex == pattern.length - 1) {
            return true;
        }
        if (strIndex != str.length - 1 && patternIndex == pattern.length - 1) {
            return false;
        }
        if (pattern[patternIndex + 1] == '*') {
            if (str[strIndex] == pattern[patternIndex] || (pattern[patternIndex] == '.' && strIndex != str.length - 1)) {
                return matchCore(str, pattern, strIndex + 1, patternIndex + 2)
                        || matchCore(str, pattern, strIndex + 1, patternIndex)
                        || matchCore(str, pattern, strIndex, patternIndex + 2);
            } else {
                return matchCore(str, pattern, strIndex, patternIndex + 2);
            }
        }
        if (str[strIndex] == pattern[patternIndex] || (pattern[patternIndex] == '.' && strIndex != str.length - 1)) {
            return matchCore(str, pattern, strIndex + 1, patternIndex + 1);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("match: " + match("aaa".toCharArray(), "aa.a".toCharArray()));
        System.out.println("match: " + match("aaa".toCharArray(), "ab*ac*a".toCharArray()));
    }
}