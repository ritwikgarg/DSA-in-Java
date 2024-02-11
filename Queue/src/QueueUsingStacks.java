import java.util.Stack;

public class QueueUsingStacks {

    static class QueueUsingStacksImplementation{

        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();

        public boolean isEmpty() {
            return s1.isEmpty();
        }

        public void add(Integer data) {
            while(!s1.isEmpty()) {
                s2.push(s1.pop());
            }

            s1.push(data);

            while(!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }

        public Integer remove() {
            if(isEmpty())
            {
                throw new QueueImplementation.QueueEmptyException();
            }
            return s1.pop();
        }

    }

    public static void main(String[] args) {
        QueueUsingStacksImplementation queue = new QueueUsingStacksImplementation();
        queue.add(10);
        queue.add(20);
        queue.add(30);
        queue.add(40);
        queue.add(50);

        for(int i=0; i<5; i++){
            System.out.println(queue.remove());
        }
    }
}
