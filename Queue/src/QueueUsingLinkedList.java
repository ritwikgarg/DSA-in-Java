public class QueueUsingLinkedList {

    public static class CustomQueueUsingLinkedList <T> {

        private static class Node <T> {
            T data;
            Node next;

            Node(T data) {
                this.data = data;
                next = null;
            }
        }

        static Node head = null;
        static Node tail = null;

        public boolean isEmpty() {
            return (head == null && tail == null);
        }

        public boolean add(T data) {
            Node newNode = new Node(data);
            if(tail == null) {
                tail = head = newNode;
                return true;
            }
            tail.next = newNode;
            tail = newNode;
            return true;
        }

        public T remove() {
            if(isEmpty())
            {
                throw new QueueImplementation.QueueEmptyException();
            }

            T removedItem = (T) head.data;

            if(head.next == null)
            {
                head = null;
                tail = null;
                return removedItem;
            }

            head = head.next;

            return removedItem;
        }

        public T peek() {
            if(isEmpty())
            {
                System.out.println("Queue is Empty");
            }
            return (T) head.data;
        }

        public void printQueue(){
            Node currNode = head;
            while(currNode != tail.next) {
                if(currNode == tail) {
                    System.out.println(currNode.data);
                }
                else {
                    System.out.print(currNode.data + "<--");
                }
                currNode = currNode.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CustomQueueUsingLinkedList<Integer> queue = new CustomQueueUsingLinkedList<Integer>();

        queue.add(10);
        queue.add(20);
        queue.add(30);
        queue.add(40);
        queue.add(50);

        queue.printQueue();

        System.out.println("Removed element: " + queue.remove());
        System.out.println("Removed element: " + queue.remove());
        System.out.println("Removed element: " + queue.remove());

        queue.add(60);
        queue.printQueue();

        System.out.println("Removed element: " + queue.remove());
        System.out.println("Removed element: " + queue.remove());
        System.out.println("Removed element: " + queue.remove());

        try{
            queue.remove();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
