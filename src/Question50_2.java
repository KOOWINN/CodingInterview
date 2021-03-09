/**
 * 面试题50_2：字符流中第一个只出现一次的字符。
 *
 * 请实现一个函数，用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，
 * 第一个只出现一次的字符是'g'；当从该字符流中读出前6个字符"google"时，第一个只出现一次的字符时'l'。
 * 
 */
public class Question50_2 {
    // 字符只能一个接着一个从字符流中读出来。可以定义一个数据容器来保存字符在字符流中的位置。当一个字符第一次
    // 字符流中读出来时，把它在字符流中的位置保存到数据容器里。当这个字符再次从字符流中读出来时，那么它就不是
    // 只出现一次的字符，也就可以忽略了。这时把它在数据容器里保存的值更新为一个特殊的值。
    public static class CharStatistics{
        // 哈希表用occurrence数组实现。数组中的元素occurrence[i]和ASCII码的值为i的字符向对应。
        // 最开始的时候，数组中的所有元素都初始化为-1。当一个ASCII码为i的字符第一次从字符流中读出时，
        // occurrence[i]的值更新为它在字符流中的位置。当这个字符再次从字符流中读出时（occurrence[i]大于或等于0），
        // occurrence[i]的值更新为-2。
        private int[] occurrence = new int[256];
        private int index;

        public CharStatistics() {
            index = 0;
            for (int i = 0; i < 256; i++) {
                occurrence[i] = -1;
            }
        }

        public void insert(char ch) {
            if (occurrence[ch] == -1) {
                occurrence[ch] = index;
            } else if (occurrence[ch] >= 0) {
                occurrence[ch] = -2;
            }
            index++;
        }

        // 当我们需要找出目前为止从字符流中读出的所有字符中第一个不重复的字符时，只需要扫描整个数组，并从中找出最小的
        // 大于等于0的值对应的字符即可。
        public char firstAppearingOnce() {
            char ch = '\0';
            int minIndex = Integer.MAX_VALUE;
            for (int i = 0; i < 256; i++) {
                if (occurrence[i] >= 0 && occurrence[i] < minIndex) {
                    ch = (char) i;
                    minIndex = occurrence[i];
                }
            }
            return ch;
        }
    }

    public static void main(String[] args) {
        CharStatistics charStatistics = new CharStatistics();
        charStatistics.insert('g');
        charStatistics.insert('o');
        System.out.println("The first character only appeared once: " + charStatistics.firstAppearingOnce());
        charStatistics.insert('o');
        charStatistics.insert('g');
        charStatistics.insert('l');
        charStatistics.insert('e');
        System.out.println("The first character only appeared once: " + charStatistics.firstAppearingOnce());
    }
}