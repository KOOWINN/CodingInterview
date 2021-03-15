import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 面试题59_1：滑动窗口的最大值
 *
 * 给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。例如，如果输入{2，3，4，2，6，2，5，1}及
 * 滑动窗口的大小3，那么一共存在6个滑动窗口，它们的最大值分别为{4，4，6，6，6，5}。
 *
 */
public class Question59_1 {

    // 如果采用蛮力法，那么这个问题似乎不难解决：可以扫描每个滑动窗口的所有数字并找出其中的最大值。如果窗口的大小为k，
    // 则需要O(k)时间才能找出滑动窗口里的最大值。对于长度为n的输入数组，这种算法的总时间复杂度是O(nk)。
    // 换一种思路：把有可能成为滑动窗口最大值的数值存入一个两端开口的队列，以输入数组{2，3，4，2，6，2，5，1}为例：
    // ① 数组的第一个数字是2，把它存入队列。
    // ② 第二个数字是3，由于它比前一个数字2大，因此2不可能成为滑动窗口的最大值。先把2从队列里删除，再把3存入队列。此时队列中只有一个数字3.
    // ③ 针对第三个数字4的步骤类似，最终在队列中只剩下一个数字4。此时滑动窗口中已经有3个数字，而它的最大值4位于队列的头部。
    // ④ 第四个数字2：2比队列中的数字4小，当4滑出窗口之后，2还是有可能成为滑动窗口中的最大值，因此把2存入队列的尾部。现在队列中两个数字4和2，其中最大值4仍然位于队列的头部。
    // ⑤ 第五个数字6：由于它比队列中已有的两个数字4和2都大，因此这时4和2已经不可能成为滑动窗口中的最大值了。先把4和2从队列中删除，
    //    再把数字6存入队列。这时候最大值6仍然位于队列的头部。
    // ⑥ 第六个数字2：由于它比队列中已有的数字6小，所以把2也存入队列的尾部。此时队列中有两个数字，其中最大值6位于队列的头部。
    // ⑦ 第七个数字5：在队列中已有的两个数字6和2里，2小于5，因此2不可能是一个滑动窗口的最大值，可以把它从队列的尾部删除。删除
    //    数字2之后，再把数字5存入队列。此时队列里剩下两个数字6和5，其中位于队列头部的是最大值6。
    // ⑧ 数组的最后一个数字1：把1存入队列尾部。由于队列头部的数字6是数组的第五个数字，此时的滑动窗口已经不包括这个数字了，因此
    //    应该把数字6从队列里删除。那么怎么知道滑动窗口是否包含一个数字？应该在队列里存入数字在数组里的下标，而不是数值。当一个
    //    数字的下标与当前处理的数字的下标之差大于或者等于滑动窗口的大小时，这个数字已经从窗口中滑出，可以从队列中删除了。
    static List<Integer> maxInWindow(int[] numbers, int size) {
        List<Integer> maxInWindows = new ArrayList<>();
        if (numbers != null && numbers.length >= size && size >= 1) {
            Deque<Integer> index = new ArrayDeque<>();
            // 先确定数组中第0到size-1个数字中的最大值
            for (int i = 0; i < size; i++) {
                while (!index.isEmpty() && numbers[i] >= numbers[index.peekLast()]) {
                    index.removeLast();
                }
                index.addLast(i);
            }
            // 紧接着窗口一步一步向后滑动
            for (int i = size; i < numbers.length; i++) {
                maxInWindows.add(numbers[index.peekFirst()]);
                while (!index.isEmpty() && numbers[i] > numbers[index.peekLast()]) {
                    index.removeLast();
                }
                if (!index.isEmpty() && index.peekFirst() <= (i - size)) {
                    index.removeFirst();
                }
                index.addLast(i);
            }
            maxInWindows.add(numbers[index.peekFirst()]);
        }
        return maxInWindows;
    }

    public static void main(String[] args) {
        List<Integer> ret = maxInWindow(new int[]{2, 3, 4, 2, 6, 2, 5, 1}, 3);
        System.out.println("ret: " + ret.toString());
    }
}