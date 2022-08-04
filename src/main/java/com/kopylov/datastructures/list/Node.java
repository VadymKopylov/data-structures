package com.kopylov.datastructures.list;

public class Node {
    public Node next;
    Node prev;
    public Object value;

    public Node(Object value){
        this.value = value;
    }
}
