class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int comp[] = new int[n];
        // comp[0] = 0                             // first node anchors component 0

        for(int i=1; i<n; i++) {
            // O(n)
            int diff = Math.abs(nums[i]-nums[i-1]);
            if(diff <= maxDiff)
                comp[i] = comp[i-1];               // connected -> inherit previous label
            else comp[i] = i;                      // gap too big -> start a new component
        }

        boolean ans[] = new boolean[queries.length];
        for(int i=0; i<queries.length; i++) {
            int node1 = queries[i][0], node2 = queries[i][1];
            if(comp[node1]==comp[node2])
                ans[i]=true;                       // same label -> same component -> reachable
        }
        return ans;
    }
}