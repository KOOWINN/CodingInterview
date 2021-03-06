import java.util.TreeSet;

/**
 * 面试题40：最小的k个数
 *
 * 输入n个整数，找出其中最小的k个数。例如，输入4、5、1、6、2、7、3、8
 * 这8个数字，则最小的4个数字是1、2、3、4。
 * 
 */
public class Question40 {
    // 解法一：时间复杂度为O(n)的算法，只有当我们可以修改输入的数组时可用
    static int[] getLeastNumbers1(int[] input, int k) {
        if (input == null || k > input.length || input.length<=0 || k <= 0) {
            return null;
        }
        int start = 0;
        int end = input.length-1;
        int index = partition(input, start, end);
        while (index != k - 1) {
            if (index > k - 1) {
                end = index - 1;
            } else {
                start = index + 1;
            }
            index = partition(input, start, end);
        }
        int[] output = new int[k];
        for (int i = 0; i < k; i++) {
            output[i] = input[i];
        }
        return output;
    }

    // 解法二：时间复杂度为O(nlogk)的算法，特别适合处理海量数据
    // 我们可以先创建一个大小为k的数据容器来存储最小的k个数字，接下来每次从输入的n个整数中读入一个数。
    // 如果容器中已有的数字少于k个，则直接把这次读入的整数放入容器之中；如果容器中已有k个数字了，也就
    // 是容器已满，此时我们不能再插入新的数字而只能替换已有的数字。找出这已有的k个数中的最大值，然后
    // 拿这次待插入的整数和最大值进行比较。如果待插入的值比当前已有的值小，则用这个数替换当前已有的最大值；
    // 如果待插入的值比当前已有的最大值还要大，那么这个数不可能是最小的k个整数之一，于是我们可以抛弃这个整数。
    //
    // 由于每次都需要找到k个整数中的最大数字，我们很容易想到最大堆。在最大堆中，根节点的值总是大于它的子树中
    // 任意节点的值。于是我们每次可以在O(1)时间内得到已有的k个数字中的最大值，但需要O(logk)时间完成删除及插入操作。
    // jdk中的TreeSet和TreeMap就是实现了红黑树数据结构的容器类

    static TreeSet<Integer> getLeastNumbers2(int[] input, int k) {

        if (input == null || k > input.length || input.length<=0 || k <= 0) {
            return null;
        }
        TreeSet<Integer> leastNumbers = new TreeSet<>();
        for (int i = 0; i < input.length; i++) {
            if (leastNumbers.size() < k) {
                leastNumbers.add(input[i]);
            } else {
                int biggestNumber = leastNumbers.last();
                if (input[i] < biggestNumber) {
                    leastNumbers.remove(biggestNumber);
                    leastNumbers.add(input[i]);
                }
            }
        }
        return leastNumbers;
    }

    // 把数组分为两部分，比选择的数字小的数字移到数组的左边，比选择的数字大的数字移到数组的右边
    private static int partition(int[] data, int start, int end) {
        if (data == null || data.length <= 0 || start < 0 || end >= data.length) {
            return -1;
        }
        int index = start;
        swap(data, index, end);
        int small = start-1;
        for (index = start; index < end; index++) {
            if (data[index] < data[end]) {
                ++small;
                if (small != index) {
                    swap(data, index, small);
                }
            }
        }
        ++small;
        swap(data, small, end);
        return small;
    }

    // 交换数组中下标为index1和index2的元素
    private static void swap(int[] data, int index1, int index2) {
        int tmp = data[index1];
        data[index1] = data[index2];
        data[index2] = tmp;
    }

    // 解法二相比解法一慢一点，但是它有两个明显的优点：①没有修改输入的数据 ②适合海量数据的输入（由于内存的
    // 大小是有限的，有可能不能把这些海量的数据一次性全部载入内存，这个时候我们就可以从辅助存储空间中每次只
    // 读入一个数字）
    public static void main(String[] args) {
        int[] numbers = {4, 5, 1, 6, 2, 7, 3, 8};
        System.out.println("The least k numbers are: " + getLeastNumbers2(numbers, 4).toString());
    }

}