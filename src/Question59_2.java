import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 面试题59_2：队列的最大值
 *
 * 请定义一个队列并实现函数max得到队列里的最大值，要求函数max、push_back和pop_front的时间复杂度都是O(1)。
 *
 */
public class Question59_2 {

    // 如59_1所述，滑动窗口可以看成一个队列，因此上题的解法可以用来实现带max函数的队列。
    static class QueueWithMax{
        private int currentIndex;
        Deque<InternalData> data;
        Deque<InternalData> maximums;

        public QueueWithMax() {
            this.currentIndex = 0;
            data = new ArrayDeque<>();
            maximums = new ArrayDeque<>();
        }

        public void push_back(int number) {
            // 移除辅助队列maximums中比number小的数
            while (!maximums.isEmpty() && number >= maximums.peekLast().number) {
                maximums.removeLast();
            }
            InternalData internalData = new InternalData(number, currentIndex);
            data.addLast(internalData);
            maximums.addLast(internalData);
            ++currentIndex;
        }

        public void pop_front() throws Exception {
            if (maximums.isEmpty()) {
                throw new Exception("queue is empty!");
            }
            // 如果当前要移除的数同样位于最大值辅助队列的头部，那也同步删除
            if (maximums.peekFirst().index == data.peekFirst().index) {
                maximums.removeFirst();
            }
            data.removeFirst();
        }
        int max() throws Exception {
            if (maximums.isEmpty()) {
                throw new Exception("queue is empty");
            }
            return maximums.peekFirst().number;
        }
    }

    static class InternalData{
        int number;
        int index;

        public InternalData(int number, int index) {
            this.number = number;
            this.index = index;
        }
    }
}