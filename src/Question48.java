/**
 * 面试题48：最长不含重复字符的子字符串
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。假设字符串中只包含‘a'~'z'的字符。
 * 例如，在字符串”arabcacfr"中，最长的不含重复字符的子字符串是“acfr"，长度为4。
 * 
 */
public class Question48 {
    // 依然用动态规划进行求解。
    // 首先定义函数f(i)表示第i个字符为结尾的不包含重复字符的子字符串的最长长度。我们从左到右逐一扫描字符串中的每一个字符。
    // 当我们计算以第i个字符为结尾的不包含重复字符的子字符串的最长长度为f(i)时，我们已经直到f(i-1)了。
    // 如果第i个字符之前没有出现过，那么f(i)=f(i-1)+1。
    // 如果第i个字符之前出现过，情况就比较复杂一点。我们先计算第i个字符和它上次出现在字符串中的位置的距离，并记为d，接着分两种情形分析：
    // ① d小于或者等于f(i-1)，此时第i个字符上次出现在f(i-1)对应的最长子字符串之中，因此f(i)=d;
    // ② d大于f(i-1)，此时第i个字符上次出现在f(i-1)对应的最长子字符串之前，因此仍然有f(i)=f(i-1)+1。
    static int longestSubstringWithoutDuplication(String str) {
        if (str == null) {
            return 0;
        }

        int[] position = new int[26];
        for (int i = 0; i < 26; i++) {
            position[i] = -1;
        }

        int currentLength = 0;
        int maxLength = 0;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // 获取这个字符最近一次出现的位置
            int prevIndex = position[chars[i] - 'a'];
            if (prevIndex < 0 || i - prevIndex > currentLength) {
                // 如果这个字符之前没有出现过或者这次出现和上次出现的位置间隔大于f(i-1)，则f(i)=f(i-1)+1
                ++currentLength;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                }
                currentLength = i - prevIndex;
            }
            // 记录当前字符出现的位置
            position[chars[i]-'a'] = i;
        }
        if (currentLength > maxLength) {
            maxLength = currentLength;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String str = "arabcacfr";
        System.out.println("The length of the longest substring without duplication is: " + longestSubstringWithoutDuplication(str));
    }
}