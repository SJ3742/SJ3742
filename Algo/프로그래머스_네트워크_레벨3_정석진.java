
public class 프로그래머스_네트워크_레벨3_정석진 {
	class Solution {
		public static boolean[] visited;
		public static int[][] com;
		public static int N; 

		public int solution(int n, int[][] computers) {
			int answer = 0;
			N = n;
			visited = new boolean[n];
			com = computers;
			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					traverse(i);
					answer++;
				}
			}
			return answer;
		}

		public static void traverse(int start) {
			visited[start] = true;
			for (int i = 0; i < N; i++) {
				if (com[start][i] == 1 && !visited[i]) {
					traverse(i);
				}
			}
		}
	}
}
