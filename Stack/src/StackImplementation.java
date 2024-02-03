import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Stack;

public class StackImplementation {

    static class CustomStackUsingArray<T> {
        private int top = 0;
        private int size;
        static Object[] stack;

        public CustomStackUsingArray(int size) throws IllegalSizeException {
            if (size > 0) {
                this.size = size;
                stack = new Object[size];
            } else {
                throw new IllegalSizeException(size);
            }
        }

        public boolean push(T item) throws StackOverflowException {
            if(top == size) {
                throw new StackOverflowException(size);
            }
            stack[top] = item;
            top++;
            return true;
        }

        @SuppressWarnings("unchecked")
        public T pop() throws StackUnderflowException {
            if(top == 0) {
                throw new StackUnderflowException();
            }
            return (T) stack[--top]; //Unchecked cast
        }

        @SuppressWarnings("unchecked")
        public T peek() {
            return (T) stack[top-1];
        }

        public boolean isEmpty() {
            return (top==0);
        }

        public void printStack() {
            for(int i=top-1; i>=0; i--) {
                System.out.println(stack[i]);
            }
        }


    }

    static class CustomStackUsingArrayList<T> {

        private int top;
        ArrayList<T> stack;
        public CustomStackUsingArrayList(){
             stack = new ArrayList<>();
        }
        public CustomStackUsingArrayList(int initialSize){
            stack = new ArrayList<>(initialSize);
        }

        public boolean push(T item) {
            stack.add(item);
            top++;
            return true;
        }

        public T pop() throws StackUnderflowException {
            if(top == 0){
                throw new StackUnderflowException();
            }
            return stack.remove(--top);
        }

        public boolean isEmpty(){
            return top == 0;
        }

        public T peek() {
            if(isEmpty())
                return null;
            return stack.get(top-1);
        }

        public void printStack() {
            for(int i=top-1; i>=0; i--) {
                System.out.println(stack.get(i));
            }
        }


    }

    static class CustomStackUsingLinkedList<T> {

        static class Node<T>{
            T data;
            Node next;

            public Node(T data)
            {
                this.data =data;
                next = null;
            }
        }

        public static Node head;

        public boolean isEmpty(){
            return head == null;
        }
        public boolean push(T item) {
            Node newNode = new Node(item);
            if(isEmpty()){
                head = newNode;
                return true;
            }
            newNode.next = head;
            head = newNode;
            return true;
        }

        public T pop() throws StackUnderflowException{
            if(isEmpty()){
                throw new StackUnderflowException();
            }
            T top = (T) head.data;
            head = head.next;
            return top;
        }

        public T peek() {
            if(isEmpty())
                return null;
            return (T) head.data;
        }

        public void printStack(){

            Node curr = head;
            while(curr != null){
                System.out.println(curr.data);
                curr= curr.next;
            }
        }
    }



    public static void main(String[] args) {

        CustomStackUsingArray<Integer> arrayStack = new CustomStackUsingArray<>(5);
        arrayStack.push(10);
        arrayStack.push(20);
        arrayStack.push(30);
        arrayStack.push(40);
        arrayStack.push(50);

        System.out.println("Stack with array:");
        arrayStack.printStack();
        System.out.println();

        System.out.println("Pop:");
        System.out.println(arrayStack.pop());
        System.out.println();

        System.out.println("Peek:");
        System.out.println(arrayStack.peek());
        System.out.println();

        System.out.println("Is empty:");
        System.out.println(arrayStack.isEmpty());
        System.out.println();
        System.out.println();

        CustomStackUsingArrayList<String> arrayListStack = new CustomStackUsingArrayList<>();
        arrayListStack.push("Hello");
        arrayListStack.push("World");

        System.out.println("Stack with array list:");
        arrayListStack.printStack();
        System.out.println();

        System.out.println("Pop:");
        System.out.println(arrayListStack.pop());
        System.out.println(arrayListStack.pop());
        System.out.println();

        System.out.println("Peek:");
        System.out.println(arrayListStack.peek());
        arrayListStack.push("Ritwik");
        System.out.println(arrayListStack.peek());
        System.out.println();


        CustomStackUsingLinkedList<Double> linkedListStack = new CustomStackUsingLinkedList<>();
        linkedListStack.push(111.11);
        linkedListStack.push(222.22);
        linkedListStack.push(333.33);

        System.out.println("Stack with linked list:");
        linkedListStack.printStack();
        System.out.println();

        System.out.println("Pop:");
        System.out.println(linkedListStack.pop());
        System.out.println(linkedListStack.pop());
        System.out.println();

        System.out.println("Peek:");
        System.out.println(linkedListStack.peek());


    }

    public static class IllegalSizeException extends RuntimeException {
        public IllegalSizeException() {
            super();
        }
        public IllegalSizeException(int size) {
            System.out.println(size + " is an invalid size. Please enter a valid size.");
        }
    }

    public static class StackOverflowException extends RuntimeException {
        public StackOverflowException(){
            super();
        }

        public StackOverflowException(int size) {
            System.out.println("Stack has a maximum capacity of "+ size+ ". Stack cannot hold anymore items");
        }
    }

    public static class StackUnderflowException extends RuntimeException{
        public StackUnderflowException(){
            super();
            System.out.println("Cannot pop from Stack as it is empty");
        }
    }
}
