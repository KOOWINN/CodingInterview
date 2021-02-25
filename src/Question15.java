/**
 * 面试题15：二进制中1的个数
 *
 * 请实现一个函数，输入一个整数，输出该数二进制表示中1的个数。例如，把9表示成二进制是1001，有2位是1.
 * 因此，如果输入9，则该函数输出2.
 *
 */
public class Question15 {

    //先判断该整数二进制表示中最右边1位是否为1；接着把该整数右移一位，此时原来处于从右边数起
    //的第二位被已到了最右边，再判断是否为1；这样每次移动一位，直到整个整数变为0为止。
    //当输入的是一个负数，例如0x80000000，则每次右移最高位都会被设为1，这样一直右移下去这个数字会变成0xFFFFFFFF而陷入死循环。
    static int fun1(int n){
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n>>1;
        }
        return count;
    }

    //为了避免死循环，不右移输入的数字n。首先把n和1做位与运算，判断n的最低位是不是为1；接着
    //把1左移一位得到2，再和n做与运算，就能判断n的次低位是不是1……这样反复左移，每次都能判断
    //n的其中一位是不是1。这种解法循环的次数等于整数二进制的位数。
    static int fun2(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0) {
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

    // 把一个整数减去1，都是把最右边的1变成0。如果它的右边还有0，则所有的0都变成1，而它左边的
    // 所有位都保持不变。所以，把一个整数减去1，再和原整数做位于运算，相当于把它最右边的1变成0。
    // 利用这个规律，就可以使用以下解法，而且循环的次数等于整数二进制表示中1的个数。
    static int fun3(int n) {
        int count = 0;
        while (n > 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("number of 1: " + fun1(9));
        System.out.println("number of 1: " + fun2(9));
        System.out.println("number of 1: " + fun3(9));
    }

}