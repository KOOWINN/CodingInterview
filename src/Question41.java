import java.util.TreeSet;

/**
 * 面试题41：数据流中的中位数
 *
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 
 */
public class Question41 {
    // 如果用一个数据容器来保存从流中读出来的数据，我们可以将这个数据容器分为两部分，位于容器左边部分的数据
    // 比右边的数据小，另外用一个指针P1指向左边部分最大的数，用另一个指针P2指向右边部分最小的数。
    // 所以可以用一个最大堆实现左边的数据容器，用一个最小堆实现右边的数据容器。往堆中插入一个数据的时间效率
    // 是O(logn)。由于只需要O(1)时间就可以得到位于堆顶的数据，因此得到中位数的时间复杂度是O(1)。
    //
    // 还需要考虑一些细节。首先要保证数据平均分配到两个堆中，因此两个堆中数据的数目之差不能超过1。为了实现平均
    // 分配，可以在数据的总数目为偶数时把新数据插入最小堆，否则插入最大堆。接下来就得保证最大堆中的所有数据都要
    // 小于最小堆中的数据。当数据的总数目是偶数，按照前面的分配规则会把新的数据插入最小堆。如果此时这个新的数据
    // 比最大堆中一些数据要小，那该怎么办？
    // 可以先把这个新的数据插入最大堆，接着把最大堆中最大的数字拿出来插入最小堆。由于最终插入最小堆的数字是原最大堆
    // 中最大的数字，这样就保证了最小堆中所有的数字都大于最大堆中的数字。
    // 当需要把一个数据插入最大堆，但这个数据大于最小堆里的一些数据时，分析思路类似。
    private static class DynamicArray{
        TreeSet<Integer> min = new TreeSet<>();
        TreeSet<Integer> max = new TreeSet<>();
        public void insert(int num) {
            System.out.println("max: " + max.toString() + ", min: " + min.toString());
            if (((min.size() + max.size()) & 1) == 0) {
                //在将一个新数据插入最小堆之前先判断这个数是否比最大堆中的最大值小
                if (max.size() > 0 && num < max.last()) {
                    max.add(num);
                    num = max.pollLast();
                }
                min.add(num);  //当数据的总数目为偶数时，新数据插入最小堆
            } else {
                //在将一个新输入插入最大堆之前，先判断这个数是否比最小堆中的最小值大
                if ((min.size() > 0 && num > min.first())) {
                    min.add(num);
                    num = min.pollFirst();
                }
                max.add(num); //当数据的总数目为奇数时，新数据插入最大堆
            }
        }

        public int getMedian() {
            int size = min.size() + max.size();
            if (size == 0) {
                return -1;
            }
            int median = 0;
            if ((size & 1) == 1) {
                median = min.first();
            } else {
                median = (max.last() + min.first()) / 2;
            }
            return median;
        }
    }
    public static void main(String[] args) {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.insert(1);
        dynamicArray.insert(2);
        dynamicArray.insert(7);
        System.out.println("The median is: " + dynamicArray.getMedian());
        dynamicArray.insert(8);
        System.out.println("The median is: " + dynamicArray.getMedian());
        dynamicArray.insert(5);
        System.out.println("The median is: " + dynamicArray.getMedian());
    }

}