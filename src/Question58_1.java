/**
 * 面试题58_1：翻转单词顺序
 *
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
 * 例如输入字符串"I am a student."，则输出"student. a am I"。
 *
 */
public class Question58_1 {

    // 两次翻转字符串：第一步翻转句子中所有的字符。比如翻转”I am a student.“中所有的字符得到”.tneduts a ma I“，
    // 此时不但翻转了句子中单词的顺序，连单词内的字符顺序也被翻转了。
    // 第二步再翻转每个单词中字符的顺序，就得到了”student. a am I"。
    static void reverse(char[] chars, int pBegin, int pEnd) {
        if (chars == null) {
            return;
        }
        while (pBegin < pEnd) {
            char tmp = chars[pBegin];
            chars[pBegin] = chars[pEnd];
            chars[pEnd] = tmp;
            pBegin++;
            pEnd--;
        }
    }

    // 在英语句子中，单词被空格符号分隔，因此我们可以通过扫描空格来确定每个单词的起始和终止为止。
    // 在上述代码的翻转每个单词阶段，指针pBegin指向单词的第一个字符，而指针pEnd指向单词的最后一个字符。
    static String reverseSentence(String sentence) {
        if (sentence == null) {
            return null;
        }
        char[] pData = sentence.toCharArray();
        int pBegin = 0;
        int pEnd = pData.length - 1;
        // 翻转整个句子
        reverse(pData, pBegin, pEnd);
        // 翻转句子中的每个单词
        pBegin = pEnd = 0;
        while (pBegin != pData.length) {
            if (pData[pBegin]==' '){
                // 下一个单词
                pBegin++;
                pEnd++;
            } else if (pEnd == pData.length || pData[pEnd] == ' ') {
                // 处理当前单词
                reverse(pData, pBegin, --pEnd);
                pBegin = ++pEnd;
            } else {
                // 还没到这个单词的末尾
                pEnd++;
            }
        }
        return String.copyValueOf(pData);
    }

    public static void main(String[] args) {
        System.out.println(reverseSentence("I am a student."));
    }
}