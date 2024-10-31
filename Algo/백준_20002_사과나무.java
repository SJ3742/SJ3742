import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 백준_20002_사과나무 {
	public static final int MAX = 100000;

	public static class Element {
		int loc;
		int time;

		public Element(int loc, int time) {
			this.loc = loc;
			this.time = time;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int s;
		int t;
		StringTokenizer st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[MAX + 1];
		Deque<Element> q = new LinkedList<>();
		q.add(new Element(s, 0));
		boolean flag = false;
		while (!flag) {
			Element e = q.poll();
			//System.out.println(e.loc);
			if (e.loc >= 0 && e.loc <= MAX) {
				if (e.loc == t) {
					System.out.println(e.time);
					break;
				}
				visited[e.loc] = true;
				if (e.loc-1>=0 && e.loc-1<=MAX && !visited[e.loc - 1]) {
					q.add(new Element(e.loc - 1, e.time + 1));
				}
				if (e.loc+1>=0 && e.loc+1<=MAX &&!visited[e.loc + 1]) {
					q.add(new Element(e.loc + 1, e.time + 1));
				}
				
				if (e.loc*2>=0 && e.loc*2<=MAX &&!visited[e.loc * 2]) {
					q.addFirst(new Element(e.loc * 2, e.time));
				}
			}
		}
	}
}
