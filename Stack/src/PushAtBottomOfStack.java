import java.util.*;
public class PushAtBottomOfStack {

    public static void pushAtBottom(Stack<Integer> stack, int data) {

        if(stack.isEmpty()) {
            stack.push(data);
            return;
        }
        int top = stack.pop();
        pushAtBottom(stack, data);
        stack.push(top);

    }

    public static void reverseStack(Stack<Integer> stack) {
        if(stack.isEmpty()) {
            return;
        }
        int top = stack.pop();
        reverseStack(stack);
        pushAtBottom(stack, top);
    }
    public static void main(String args[]) {
        Stack<Integer> stack = new Stack<Integer>();


        stack.push(1);
        stack.push(2);
        stack.push(3);

        reverseStack(stack);

        while(!stack.empty()) {
            System.out.println(stack.peek());
            stack.pop();
        }
    }

}
