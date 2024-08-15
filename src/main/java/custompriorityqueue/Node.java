package custompriorityqueue;

import lombok.*;

@Getter
@Setter
@Builder
public class Node<E> {
    private E value;
    private int index;
    //private Node<E> leftChild;
    //private Node<E> rightChild;

    public Node(E value) {
        this.value = value;
    }

    public int getParentIndex(){
        return (index-1) / 2;
    }

    public int getLeftChildIndex(){
        return (index * 2) + 1;
    }

    public int getRightChildIndex(){
        return (index * 2) + 2;
    }






}
