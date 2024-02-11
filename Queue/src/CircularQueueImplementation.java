public class CircularQueueImplementation {

    public static class CustomCircularQueue<T> {

        private static final int DEFAULT_SIZE = 10;

        private int front = -1;
        private int rear = -1;
        private int size;
        private Object[] queue;

        CustomCircularQueue() {
            queue = new Object[DEFAULT_SIZE];
        }

        CustomCircularQueue(int size) {
            this.size = size;
            queue = new Object[size];
        }

        public boolean add(T item) {
            if(isFull()) {
                throw new QueueImplementation.QueueFullException(size);
            }


            if(front == -1)
            {
                front = 0;
            }


            rear = (rear + 1) % size;
            queue[rear] = item;
            return true;
        }

        public T remove() {
            if(isEmpty()) {
                throw new QueueImplementation.QueueEmptyException();
            }

            T removedItem = (T) queue[front];
            queue[front] = null;

            if(rear == front) {
                rear = front = -1;
            } else {
                front = (front + 1) % size;
            }

            return removedItem;
        }

        public T peek() {
            if(isEmpty()){
                System.out.println("Queue is Empty");
            }
            return (T) queue[front];
        }

        public boolean isFull() {
            return (rear + 1) % size == front;
        }

        public boolean isEmpty() {
            return (front == -1 && rear == -1);
        }

        public void printQueue() {
            for (int i = 0; i < size; i++) {
                if (i < size-1)
                    System.out.print(queue[i] + "<--");
                else
                    System.out.print(queue[i]);
            }
            System.out.println();
        }

    }

    public static void main(String args[]) {
        CustomCircularQueue<Integer> queue = new CustomCircularQueue<Integer>(5);

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

        for (int i = 0; i < 3; i++) {
            System.out.println("Removed Item: " + queue.remove());
            queue.printQueue();
            System.out.println();
        }

        queue.add(60);
        queue.printQueue();
        queue.add(70);
        queue.printQueue();
        queue.add(80);
        queue.printQueue();

        queue.remove();
        queue.printQueue();


        try {
            queue.remove();
        } catch (Exception e) {
            System.out.println(e);
        }



    }
}
