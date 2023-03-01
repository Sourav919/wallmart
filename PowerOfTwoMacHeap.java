import java.util.ArrayList;

public class PowerOfTwoMaxHeap<T extends Comparable<T>> {
    private int numChildren;
    private ArrayList<T> heap;

    public PowerOfTwoMaxHeap(int numChildren) {
        this.numChildren = numChildren;
        this.heap = new ArrayList<T>();
    }

    public void insert(T value) {
        heap.add(value);
        siftUp(heap.size() - 1);
    }

    public T popMax() {
        if (heap.isEmpty()) {
            return null;
        }
        T max = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            siftDown(0);
        }
        return max;
    }

    private void siftUp(int index) {
        T value = heap.get(index);
        while (index > 0) {
            int parentIndex = (index - 1) / numChildren;
            T parentValue = heap.get(parentIndex);
            if (value.compareTo(parentValue) > 0) {
                heap.set(index, parentValue);
                index = parentIndex;
            } else {
                break;
            }
        }
        heap.set(index, value);
    }

    private void siftDown(int index) {
        T value = heap.get(index);
        while (true) {
            int childIndex = getLargestChildIndex(index);
            if (childIndex == -1) {
                break;
            }
            T childValue = heap.get(childIndex);
            if (childValue.compareTo(value) > 0) {
                heap.set(index, childValue);
                index = childIndex;
            } else {
                break;
            }
        }
        heap.set(index, value);
    }

    private int getLargestChildIndex(int index) {
        int leftChildIndex = index * numChildren + 1;
        if (leftChildIndex >= heap.size()) {
            return -1;
        }
        int largestChildIndex = leftChildIndex;
        for (int i = 2; i <= numChildren; i++) {
            int childIndex = index * numChildren + i;
            if (childIndex < heap.size()) {
                T childValue = heap.get(childIndex);
                T largestChildValue = heap.get(largestChildIndex);
                if (childValue.compareTo(largestChildValue) > 0) {
                    largestChildIndex = childIndex;
                }
            } else {
                break;
            }
        }
        return largestChildIndex;
    }
}
