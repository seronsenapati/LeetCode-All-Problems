import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class State {
        long score;
        List<Integer> ids;

        State(long score, List<Integer> ids) {
            this.score = score;
            this.ids = ids;
        }
    }

    private List<Interval> arr;
    private State[][] memo;
    private int n;

    public int[] maximumWeight(List<List<Integer>> intervals) {
        n = intervals.size();
        arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> in = intervals.get(i);
            arr.add(new Interval(in.get(0), in.get(1), in.get(2), i));
        }

        arr.sort((a, b) -> {
            if (a.l != b.l) return Integer.compare(a.l, b.l);
            return Integer.compare(a.r, b.r);
        });

        memo = new State[n][5];

        List<Integer> result = solve(0, 4).ids;

        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }

    private State solve(int i, int remaining) {
        if (i == n || remaining == 0) {
            return new State(0, new ArrayList<>());
        }

        if (memo[i][remaining] != null) {
            return memo[i][remaining];
        }

        // Option 1: Skip current interval
        State skip = solve(i + 1, remaining);

        // Option 2: Take current interval
        Interval curr = arr.get(i);
        int next = findNext(i + 1, curr.r);

        State nextState = solve(next, remaining - 1);

        List<Integer> selected = new ArrayList<>(nextState.ids);
        selected.add(curr.idx);
        Collections.sort(selected);

        State take = new State(
            curr.w + nextState.score,
            selected
        );

        State best;

        if (take.score > skip.score) {
            best = take;
        } else if (take.score < skip.score) {
            best = skip;
        } else {
            best = compareLexicographically(take.ids, skip.ids) < 0
                ? take
                : skip;
        }

        memo[i][remaining] = best;
        return best;
    }

    private int findNext(int start, int end) {
        int left = start;
        int right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr.get(mid).l > end) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private int compareLexicographically(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());

        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}