class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int[][] litter = new int[m][n];

        int sr = 0, sc = 0;
        int count = 0;

        // Find S and number/index every L.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    litter[i][j] = count++;
                }
            }
        }

        // No litter.
        if (count == 0) {
            return 0;
        }

        int totalMask = (1 << count) - 1;

        /*
         * visited[row][col][energy][mask]
         *
         * mask tells which litter has already been collected.
         */
        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << count];

        // Queue state:
        // {row, col, remainingEnergy, mask}
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[] {
            sr, sc, energy, 0
        });

        visited[sr][sc][energy][0] = true;

        int moves = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int size = queue.size();

            // BFS level = number of moves
            while (size-- > 0) {

                int[] cur = queue.poll();

                int r = cur[0];
                int c = cur[1];
                int e = cur[2];
                int mask = cur[3];

                // All litter collected.
                if (mask == totalMask) {
                    return moves;
                }

                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Outside grid
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    // Need energy to make a move.
                    if (e == 0) {
                        continue;
                    }

                    int newEnergy = e - 1;

                    /*
                     * If we arrive at R, energy is
                     * immediately restored.
                     */
                    if (classroom[nr].charAt(nc) == 'R') {
                        newEnergy = energy;
                    }

                    int newMask = mask;

                    // Collect litter.
                    if (classroom[nr].charAt(nc) == 'L') {
                        int id = litter[nr][nc];
                        newMask |= (1 << id);
                    }

                    if (!visited[nr][nc][newEnergy][newMask]) {

                        visited[nr][nc][newEnergy][newMask] = true;

                        queue.offer(new int[] {
                            nr, nc, newEnergy, newMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}