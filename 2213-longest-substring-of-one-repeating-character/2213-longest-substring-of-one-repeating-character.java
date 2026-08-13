class Solution {

    class Node {
        char leftChar;
        char rightChar;

        int leftCount;
        int rightCount;
        int maxCount;
        int length;

        Node(char leftChar, char rightChar,
             int leftCount, int rightCount,
             int maxCount, int length) {

            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.leftCount = leftCount;
            this.rightCount = rightCount;
            this.maxCount = maxCount;
            this.length = length;
        }
    }

    Node[] tree;
    char[] arr;

    public int[] longestRepeating(
            String s,
            String queryCharacters,
            int[] queryIndices) {

        int n = s.length();

        arr = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            arr[index] = ch;

            update(1, 0, n - 1, index);

            ans[i] = tree[1].maxCount;
        }

        return ans;
    }

    void build(int node, int start, int end) {

        if (start == end) {
            tree[node] = new Node(
                arr[start],
                arr[start],
                1,
                1,
                1,
                1
            );
            return;
        }

        int mid = start + (end - start) / 2;

        build(node * 2, start, mid);
        build(node * 2 + 1, mid + 1, end);

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    void update(int node, int start, int end, int index) {

        if (start == end) {
            tree[node] = new Node(
                arr[index],
                arr[index],
                1,
                1,
                1,
                1
            );
            return;
        }

        int mid = start + (end - start) / 2;

        if (index <= mid) {
            update(node * 2, start, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, end, index);
        }

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    Node merge(Node left, Node right) {

        Node result = new Node(
            left.leftChar,
            right.rightChar,
            left.leftCount,
            right.rightCount,
            Math.max(left.maxCount, right.maxCount),
            left.length + right.length
        );

        // Merge across the middle
        if (left.rightChar == right.leftChar) {

            result.maxCount = Math.max(
                result.maxCount,
                left.rightCount + right.leftCount
            );

            // Entire left segment has same character
            if (left.leftCount == left.length) {
                result.leftCount =
                    left.length + right.leftCount;
            }

            // Entire right segment has same character
            if (right.rightCount == right.length) {
                result.rightCount =
                    right.length + left.rightCount;
            }
        }

        return result;
    }
}