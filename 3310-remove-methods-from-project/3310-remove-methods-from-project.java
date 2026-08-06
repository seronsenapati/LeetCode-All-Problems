class Solution {
    List<Integer>[] graph, rev;
    boolean[] suspicious, vis;

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        graph = new ArrayList[n];
        rev = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            rev[i] = new ArrayList<>();
        }

        for (int[] e : invocations) {
            graph[e[0]].add(e[1]);
            rev[e[0]].add(e[1]);
            rev[e[1]].add(e[0]);
        }

        suspicious = new boolean[n];
        vis = new boolean[n];

        dfs(k);

        for (int i = 0; i < n; i++) {
            if (!suspicious[i] && !vis[i]) {
                dfs2(i);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }
        return ans;
    }

    private void dfs(int u) {
        suspicious[u] = true;
        for (int v : graph[u]) {
            if (!suspicious[v]) {
                dfs(v);
            }
        }
    }

    private void dfs2(int u) {
        vis[u] = true;
        suspicious[u] = false;
        for (int v : rev[u]) {
            if (!vis[v]) {
                dfs2(v);
            }
        }
    }
}