package com.kopylov.datastructures.list;

import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList implements List {
    Node head;
    Node tail;
    int size;

    @Override
    public void add(Object value) {
        add(value,size);
    }
    @Override
    public void add(Object value, int index) {
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException("Index is larger than size ( "+ size +" )");
        }
        Node newNode = new Node(value);
        if(isEmpty()){
            head = newNode;
            tail = newNode;
        }else  if(index == 0){
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }else if(index == size){
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }else {
            Node current = (Node) getByIndex(index);
            Node previous;
            previous = current.prev;
            current.prev = newNode;
            newNode.next = current;
            newNode.prev = previous;
            previous.next = newNode;
        }
        size++;
    }

    @Override
    public Object remove(int index) {
        if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("You can delete a value with an index from 0 to " + (size-1) );
        }
        if(index == 0){
            Node nextNode;
            nextNode = head.next;
            nextNode.prev = null;
            head = nextNode;
        }else if(index == size - 1){
            Node previousNode;
            previousNode = tail.prev;
            previousNode.next = null;
            tail = previousNode;
        }else {
            Node nodeToRemove = (Node) getByIndex(index);
            Node previousNode;
            Node nextNode;
            previousNode = nodeToRemove.prev;
            nextNode = nodeToRemove.next;
            previousNode.next = nextNode;
            nextNode.prev = previousNode;
        }
        size--;
        return null;
    }

    @Override
    public Object get(int index) {
        if(index > size-1 || index < 0){
            throw new IndexOutOfBoundsException("Value with this index is null");
        }
            if(isEmpty()){
                throw new IllegalStateException("List is empty");
            }else if(index == size - 1){
                return tail.value;
            }else  if(index == 0){
                return head.value;
            }else if(index <= size/2){
                Node current = head;
                for(int i = 0;i < index;i++){
                    current = current.next;
                }
                return current.value;
            }else if(index > size/2){
                Node current = tail;
                for (int i = size - 1; i > index ; i--) {
                    current = current.prev;
                }
                return current.value;
            }
        return null;
    }

    @Override
    public Object set(Object value, int index) {
        if(index > size-1 || index < 0){
            throw new IndexOutOfBoundsException("Value with this index is null");
        }
        Node current = (Node) getByIndex(index);
        Object valueBeforeSet = current.value;
        current.value = value;
        return valueBeforeSet;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(Object value) {
        Node current = head;
        for(int i = 0;i < size;i++){
            if(Objects.equals(current.value,value)){
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        Node current = tail;
        for(int i = size - 1;i >= 0;i--){
            if(Objects.equals(current.value,value)){
                return i;
            }
            current = current.prev;
        }
        return -1;
    }
    private Object getByIndex(int index) {
        if(isEmpty()){
            throw new IllegalStateException("List is empty");
        }else if(index == size - 1){
            return tail;
        }else  if(index == 0){
            return head;
        }else if(index <= size/2){
            Node current = head;
            for(int i = 0;i < index;i++){
                current = current.next;
            }
            return current;
        }else if(index > size/2){
            Node current = tail;
            for (int i = size - 1; i > index ; i--) {
                current = current.prev;
            }
            return current;
        }
        return null;
    }
    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",","[","]");
        Node current = head;
        for (int i = 0; i < size; i++) {
            stringJoiner.add(current.value.toString());
            current = current.next;
        }
        return stringJoiner.toString();
    }
}