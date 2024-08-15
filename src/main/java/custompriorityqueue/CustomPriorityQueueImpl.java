package custompriorityqueue;

import custompriorityqueue.api.CustomPriorityQueue;

import java.util.Arrays;
import java.util.Comparator;

public class CustomPriorityQueueImpl<E extends Comparable<E>> implements CustomPriorityQueue<E> {

    private Comparator<E> comparator;
    private Object[] heap;
    private int size = 0;


    public CustomPriorityQueueImpl(Comparator<E> comparator) {
        this.comparator = comparator;
        heap = new Object[8];

    }

    public CustomPriorityQueueImpl(Comparator<E> comparator, int initialCapacity) {
        this.comparator = comparator;
        heap = new Object[initialCapacity];
    }

    public CustomPriorityQueueImpl(int initialCapacity) {
        heap = new Object[initialCapacity];
    }

    public CustomPriorityQueueImpl() {
        heap = new Object[8];
    }

    @Override
    public E peek() {
        return (E) heap[0];
    }

    @Override
    public E poll() {
        int cursor = size-1;
        E value = (E) heap[0];
        heap[0] = null;
        Object lastElement = heap[cursor];
        heap[0] = lastElement;
        heap[cursor] = null;
        size = size - 1;
        siftDown();
        return value;
    }

    @Override
    public void add(E e) {
        if (size == heap.length) resize();
        heap[size] = e;
        size = size + 1;
        siftUp();
    }

    public int getSize() {
        return size;
    }

    private void resize() {
        int newCapacity = heap.length * 2;
        Object[] broadenHeap = new Object[newCapacity];
        System.arraycopy(heap, 0, broadenHeap, 0, heap.length);
        heap = broadenHeap;

    }

    private void siftUp() {
        // ???
        int cursor = size - 1;

        while (true) {
            //проверить не является ли новый элемент корнем дерева
            if (cursor == 0) return;

            int parentIndex = findParentIndex(cursor);

            E parentNode = (E) findParentNode(cursor);
            E newNode = (E) heap[cursor];

            if (newNode.compareTo(parentNode) < 0) {
                // меняем местами
                heap[cursor] = parentNode;
                heap[parentIndex] = newNode;
                cursor = parentIndex;
            } else return;
        }
    }

    private void siftDown() {
        int cursor = 0;
        while (true) {
            // если курсор на последей ноде значит закончили
            if (cursor == size -1 ) return;

            E parentNode = (E) heap[cursor];
            E leftChild = (E) findLeftChildNode(cursor);
            E rightChild = (E) findRightChildNode(cursor);

            if (leftChild.compareTo(rightChild) < 0) {
                if (leftChild.compareTo(parentNode) < 0) {
                    heap[cursor] = leftChild;
                    cursor = findLeftChildIndex(cursor);
                    heap[cursor] = parentNode;
                } else return;

            } else if (rightChild.compareTo(leftChild) < 0) {
                if (rightChild.compareTo(parentNode) < 0) {
                    heap[cursor] = rightChild;
                    cursor = findRightChildIndex(cursor);
                    heap[cursor] = parentNode;
                }
            } else return;
        }

    }

    private void incrementSize() {
        size++;
    }


    private Object findParentNode(int currentIndex) {
        int parentIndex = findParentIndex(currentIndex);
        return heap[parentIndex];
    }

    private Object findLeftChildNode(int currentIndex) {
        int leftChildIndex = findLeftChildIndex(currentIndex);
        return heap[leftChildIndex];
    }

    private Object findRightChildNode(int currentIndex) {
        int rightChildIndex = findRightChildIndex(currentIndex);
        return heap[rightChildIndex];
    }

    private int findParentIndex(int currentIndex) {
        return (currentIndex - 1) / 2;
    }

    private int findLeftChildIndex(int currentIndex) {
        return (2 * currentIndex) + 1;
    }

    private int findRightChildIndex(int currentIndex) {
        return (2 * currentIndex) + 2;
    }

    @Override
    public String toString() {
        return Arrays.toString(heap);
    }
}
