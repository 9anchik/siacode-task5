package Various1;

import java.util.ArrayList;

public class PriorityQueue <T> implements Queue <T> {
    private ArrayList<Node<T>> heap;

    public PriorityQueue() {
        heap = new ArrayList<>();
    }

    public void add(T value, int priority) {
        Node<T> newNode = new Node<>(value, priority);
        heap.add((newNode));
        siftUp(heap.size() - 1);
    }

    public Node<T> poll() {
        if (heap.isEmpty()) {
            return null;
        }
        Node<T> result = heap.get(0);
        Node<T> lastItem = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastItem);
            siftDown(0);
        }
        return result;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).getPriority() >= heap.get(parentIndex).getPriority()) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void siftDown(int index) {
        int leftChild, rightChild, smallChild;
        while ((leftChild = 2 * index + 1) < heap.size()) {
            rightChild = leftChild + 1;
            smallChild = leftChild;
            if (rightChild < heap.size() && heap.get(rightChild).getPriority() > heap.get(leftChild).getPriority()) {
                smallChild = rightChild;
            }
            if (heap.get(index).getPriority() <= heap.get(smallChild).getPriority()) {
                break;
            }
            swap(index, smallChild);
            index = smallChild;
        }
    }

    private void swap(int i, int j) {
        Node<T> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
