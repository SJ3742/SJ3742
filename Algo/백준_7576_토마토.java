import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_7576_토마토 {
	public static int N;
	public static int M;

	public static boolean isBound(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	public static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static class Location {
		int r;
		int c;
		int time;

		Location(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Location> q = new LinkedList<>();
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int target = N * M;
		int cur = 0;
		int result = 0;
		int[][] map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					cur++;
					q.add(new Location(r, c, 0));
				} else if (map[r][c] == -1) {
					target--;
				}
			}
		}
		// 이미 다 익었으면
		if (cur == target) {
			System.out.println(0);
			return;
		}

		// bfs
		while (!q.isEmpty()) {
			Location loc = q.poll();
			// System.out.println(loc.r+" "+loc.c);
			result = loc.time;
			for (int i = 0; i < 4; i++) {
				int nr = loc.r + dir[i][0];
				int nc = loc.c + dir[i][1];
				if (isBound(nr, nc) && map[nr][nc] == 0) {
					map[nr][nc] = 1;
					cur++;
					q.add(new Location(nr, nc, loc.time + 1));
				}
			}
		}
		// 다 순회 돌렸는데 안 맞으면
		if (cur != target) {
			result = -1;
		}

		System.out.println(result);
	}
}
