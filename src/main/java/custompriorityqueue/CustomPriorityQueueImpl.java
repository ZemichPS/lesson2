package custompriorityqueue;

import custompriorityqueue.api.CustomPriorityQueue;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class CustomPriorityQueueImpl<E> extends TypeToken<E> implements CustomPriorityQueue<E> {

    private Comparator<E> comparator;
    private Object[] heap;
    private int size = 0;
    private int cursor = 0;


    public CustomPriorityQueueImpl(Comparator<E> comparator) {
        this.comparator = comparator;
        heap = new Object[8];
    }

    public CustomPriorityQueueImpl(Comparator<E> comparator, int initialCapacity) {
        this.comparator = comparator;
        heap = new Object[initialCapacity];
    }

    public CustomPriorityQueueImpl(Class<E> clazz, int initialCapacity) {
        if (!(Comparable.class.isAssignableFrom(clazz))) {
            throw new IllegalArgumentException("Type %s must implement Comparable interface".formatted(clazz));
        }
        heap = new Object[initialCapacity];
    }

    public CustomPriorityQueueImpl(Class<E> clazz) {

        if (!(Comparable.class.isAssignableFrom(clazz))) {
            throw new IllegalArgumentException("Type %s must implement Comparable interface".formatted(clazz));
        }
        heap = new Object[8];
    }

    @Override
    public E peek() {
        return (E) heap[0];
    }

    @Override
    public E poll() {
        if (size == 0) return null;

        E value = (E) heap[0];
        heap[0] = null;
        size = size - 1;
        cursor = size;
        Object lastElement = heap[cursor];
        heap[cursor] = null;
        cursor = 0;
        heap[cursor] = lastElement;
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
        int cursor = size - 1;

        while (cursor != 0) {
            int parentIndex = findParentIndex(cursor);
            E parentNode = (E) findParentNode(cursor);
            E newNode = (E) heap[cursor];

            if (compare(newNode, parentNode) < 0) {
                heap[cursor] = parentNode;
                heap[parentIndex] = newNode;
                cursor = parentIndex;
            } else return;
        }
    }

    private void siftDown() {
        cursor = 0;

        while (!(cursor == size - 1)) {

            if (findLeftChildIndex(cursor) > heap.length) return;
            if (findLeftChildNode(cursor) == null) return;

            E parent = (E) heap[cursor];
            int minimalChildIndex = findMinimalChildIndex();
            E minimalChild = (E) heap[minimalChildIndex];

            if (compare(minimalChild, parent) < 0) {
                heap[cursor] = minimalChild;
                cursor = minimalChildIndex;
                heap[cursor] = parent;
            } else return;
        }
    }

    private int findMinimalChildIndex() {
        E leftChild = (E) findLeftChildNode(cursor);
        E rightChild = (E) findRightChildNode(cursor);

        if (rightChild == null && leftChild != null) return findLeftChildIndex(cursor);

        if (compare(leftChild, rightChild) == 0) {
            return findLeftChildIndex(cursor);
        } else if (compare(leftChild, rightChild) < 0) {
            return findLeftChildIndex(cursor);
        }

        return findRightChildIndex(cursor);
    }

    private Object findParentNode(int childIndex) {
        int parentIndex = findParentIndex(childIndex);
        return heap[parentIndex];
    }

    private Object findLeftChildNode(int parentIndex) {
        int leftChildIndex = findLeftChildIndex(parentIndex);
        return heap[leftChildIndex];
    }

    private Object findRightChildNode(int parentIndex) {
        int rightChildIndex = findRightChildIndex(parentIndex);
        return heap[rightChildIndex];
    }

    private int findParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private int findLeftChildIndex(int parentIndex) {
        return (2 * parentIndex) + 1;
    }

    private int findRightChildIndex(int parentIndex) {
        return (2 * parentIndex) + 2;
    }

    private int compare(E element1, E element2) {
        if (Objects.nonNull(comparator)) return comparator.compare(element1, element2);
        return ((Comparable<E>) element1).compareTo(element2);
    }

    private Class<?> getGenericTypeClass() {
        return (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public String toString() {
        return Arrays.toString(heap);
    }


}
