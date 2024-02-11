public class QueueImplementation {

    static class CustomQueueUsingArray<T> {

        private static final int DEFAULT_SIZE = 10;

        private static int size = 0;
        private int rear = -1; //Stores the index of the last element in the queue

        //Front is always going to be 0 when implementing queue using array

        static Object[] queue;

        public CustomQueueUsingArray() {
            size = DEFAULT_SIZE;
            queue = new Object[DEFAULT_SIZE];
        }

        public CustomQueueUsingArray(int n) {
            size = n;
            queue = new Object[size];
        }

        //enqueue
        public boolean add(T item) {
            if (isFull()) {
                throw new QueueFullException(size);
            }
            rear++;
            queue[rear] = item;
            return true;
        }

        //dequeue
        public T remove() {
            if (isEmpty()) {
                throw new QueueEmptyException();
            }
            T front = (T) queue[0];

            for (int i = 0; i < rear; i++) {
                queue[i] = queue[i+1];
            }
            rear--;
            return front;
        }

        public T peek() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
            }
            return (T) queue[0];
        }

        public boolean isFull() {
            return (rear == size - 1);
        }

        public boolean isEmpty() {
            return (rear == -1);
        }

        public void printQueue() {
            for (int i = 0; i <= rear; i++) {
                if (i < rear)
                    System.out.print(queue[i] + "<--");
                else
                    System.out.print(queue[i]);
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        CustomQueueUsingArray<Integer> queue = new CustomQueueUsingArray<>(5);

        queue.add(10);
        queue.add(20);
        queue.add(30);
        queue.add(40);
        queue.add(50);

        queue.printQueue();

        try {
            queue.add(60);
        } catch (Exception e) {
            System.out.println(e);
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(queue.remove());
            queue.printQueue();
            System.out.println();
        }

        queue.printQueue();

        try {
            queue.remove();
        } catch (Exception e) {
            System.out.println(e);
        }

        queue.printQueue();

    }

    public static class QueueFullException extends RuntimeException {

        QueueFullException(int size) {
            super("Queue is full and has a maximum size of " + size + " elements.");
        }

    }

    public static class QueueEmptyException extends RuntimeException {
        QueueEmptyException() {
            super("Queue is empty");
        }
    }
}
