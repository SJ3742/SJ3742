import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_17070_파이프옮기기1 {
	// 파이프 가짓수
		public static void main(String[] args) throws NumberFormatException, IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];

			// map 정보 입력
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			int[][][] dp = new int[N][N][3];

			// 초기 상태: 파이프가 (0, 0)에서 (0, 1)까지 가로로 놓여 있음
			dp[0][1][0] = 1;

			// DP 테이블 갱신
			for (int r = 0; r < N; r++) {
			    for (int c = 1; c < N; c++) {
			        // 가로 파이프
			        if (c-1 >= 0 && map[r][c] == 0) {
			            dp[r][c][0] += dp[r][c-1][0]; // 이전 위치에서 가로로 온 경우
			            dp[r][c][0] += dp[r][c-1][1]; // 이전 위치에서 대각선으로 온 경우
			        }

			        // 세로 파이프
			        if (r-1 >= 0 && map[r][c] == 0) {
			            dp[r][c][2] += dp[r-1][c][2]; // 이전 위치에서 세로로 온 경우
			            dp[r][c][2] += dp[r-1][c][1]; // 이전 위치에서 대각선으로 온 경우
			        }

			        // 대각선 파이프
			        if (r-1 >= 0 && c-1 >= 0 && map[r][c] == 0 && map[r-1][c] == 0 && map[r][c-1] == 0) {
			            dp[r][c][1] += dp[r-1][c-1][0]; // 이전 위치에서 가로로 온 경우
			            dp[r][c][1] += dp[r-1][c-1][1]; // 이전 위치에서 대각선으로 온 경우
			            dp[r][c][1] += dp[r-1][c-1][2]; // 이전 위치에서 세로로 온 경우
			        }
			    }
			}

			// N-1, N-1 위치에서 가로, 세로, 대각선 모든 경우의 수 합
			int cnt = dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2];
			System.out.println(cnt);

		}
}
