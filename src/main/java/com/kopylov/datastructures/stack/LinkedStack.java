package com.kopylov.datastructures.stack;

import java.util.Objects;
import java.util.StringJoiner;

public class LinkedStack implements Stack{
    private Node head;
    private Node tail;
    private int size;

    @Override
    public void push(Object value) {
        Node newNode = new Node(value);
        if(isEmpty()){
            head = newNode;
            tail = newNode;
        }else{
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    @Override
    public Object pop() {
        if(isEmpty()){
            throw new IllegalStateException();
        }else{
            Object result = head.value;
            head = head.next;
            size--;
            return result;
        }

    }

    @Override
    public Object peak() {
        return head.value;
    }

    @Override
    public boolean contains(Object value) {
        Node current = head;
            while (current != null){
                if(Objects.equals(current.value,value)){
                    return true;
            }
                current = current.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }
    @Override
    public String toString(){
        StringJoiner stringJoiner = new StringJoiner(",","[","]");
        Node current = head;
            while (current != null){
                stringJoiner.add(current.value.toString());
                current = current.next;
            }
            return stringJoiner.toString();
    }
}
