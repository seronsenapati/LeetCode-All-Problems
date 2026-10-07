import java.util.Stack;

class MinStack {

    Stack<Long> obj;
    long min;

    public MinStack() {
        obj = new Stack<>();
        min = Long.MAX_VALUE;
    }

    public void push(int value) {
        long val = value;

        if (obj.isEmpty()) {
            min = val;
            obj.push(val);
        } 
        else if (val >= min) {
            obj.push(val);
        } 
        else {
            obj.push(2 * val - min);
            min = val;
        }
    }

    public void pop() {
        if (obj.isEmpty()) {
            return;
        }

        long x = obj.pop();

        if (x < min) {
            min = 2 * min - x;
        }
    }

    public int top() {
        if (obj.isEmpty()) {
            return -1;
        }

        long x = obj.peek();

        if (x >= min) {
            return (int) x;
        }
        return (int) min;
    }

    public int getMin() {
        return (int) min;
    }
}