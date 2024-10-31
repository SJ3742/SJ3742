import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_14567_선수과목 {
	public static class Node {
		int name;
		HashSet<Node> link;
		int r;

		public Node(int name) {
			this.name = name;
			this.link = new HashSet<>();
			this.r = 0;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Node> q = new LinkedList<>();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] time = new int[N + 1];
		Node[] nodes = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			nodes[start].link.add(nodes[end]);
			nodes[end].r++;
		}

		for (int i = 1; i <= N; i++) {
			if (nodes[i].r == 0) {
				q.add(nodes[i]);
				time[i] = 1;
			}
		}

		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (Node n : cur.link) {
				n.r--;
				if (n.r == 0 && time[n.name] == 0) {
					q.add(n);
					time[n.name] = time[cur.name] + 1;
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			System.out.print(time[i] + " ");
		}
	}
}
