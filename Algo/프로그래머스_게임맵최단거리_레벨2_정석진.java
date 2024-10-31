import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_게임맵최단거리_레벨2_정석진 {

	class Solution {
		public static int N;
		public static int M;
		public static boolean[][] visited;

		public static boolean isBound(int r, int c) {
			return r >= 0 && r < N && c >= 0 && c < M;
		}

		public static class Location {
			int r;
			int c;
			int dep;

			Location(int r, int c, int dep) {
				this.r = r;
				this.c = c;
				this.dep = dep;
			}
		}

		public static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

		public int solution(int[][] maps) {
			N = maps.length;
			M = maps[0].length;
			visited = new boolean[N][M];
			Queue<Location> q = new LinkedList<>();
			q.add(new Location(0, 0, 1));
			visited[0][0] = true;

			while (!q.isEmpty()) {
				Location loc = q.poll();
				if (loc.r == N - 1 && loc.c == M - 1) {
					return loc.dep;
				}

				for (int[] d : dir) {
					int nr = loc.r + d[0];
					int nc = loc.c + d[1];
					if (isBound(nr, nc) && !visited[nr][nc] && maps[nr][nc] == 1) {
						visited[nr][nc] = true;
						q.add(new Location(nr, nc, loc.dep + 1));
					}
				}
			}
			return -1;
		}
	}
}
