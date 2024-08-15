package custompriorityqueue.api;

public interface CustomPriorityQueue<E>{
    void add(E e);

    E peek();

    E poll();

    int getSize();

}
