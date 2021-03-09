/**
 * 面试题50：第一个只出现一次的字符
 *
 * 在字符串中找出第一个只出现一次的字符。如输入"abaccdeff"，则输出'b'。
 * 
 */
public class Question50 {
    // 我们可以用一个哈希表来存放每一个字符出现的次数，键值为字符，值就是该字符出现的次数。同时，
    // 我们还需要从头开始扫描字符串两次。第一次扫描字符串时，每扫描到一个字符，就在哈希表的对应项
    // 中把次数加1。接下来第二次扫描时，每扫描到一格字符，就能从哈希表中得到该字符出现的次数。这样，
    // 第一个只出现一次的字符就是符合要求的输出。
    static char firstNotRepeatingChar(String str) {
        if (str == null) {
            return '\0';
        }

        // 字符char是一个长度为8的数据类型，因此总共有256种可能。所以我们可以创建一个长度为256的数组，
        // 每个字母根据其ASCII码值作为数组的下标对应数组的一个数字，而数组种存储的是每个字符出现的次数。
        // 这样我们就创建了一格大小为256、以字符ASCII码为键值的哈希表。
        int[] hashTable = new int[256];
        for (int i = 0; i < 256; i++) {
            hashTable[i] = 0;
        }
        char[] hashKeys = str.toCharArray();
        for (char hashKey : hashKeys) {
            hashTable[hashKey]++;
        }
        for (char hashKey : hashKeys) {
            if (hashTable[hashKey] == 1) {
                return hashKey;
            }
        }
        return '\0';
    }

    public static void main(String[] args) {
        System.out.println("The first not repeating character is: " + firstNotRepeatingChar("abaccdeff"));
    }
}