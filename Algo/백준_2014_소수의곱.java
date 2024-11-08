import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_2014_소수의곱 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = Integer.MAX_VALUE;
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[K];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int idx = 0;
		PriorityQueue<Long> pq = new PriorityQueue<>();

		for (int i = 0; i < K; i++) {
			pq.add((long) arr[i]);
		}

		while (!pq.isEmpty()) {
			Long temp = pq.poll();
			idx++;
			if (idx == N) {
				System.out.println(temp);
				return;
			}

			for (int i = 0; i < K; i++) {
				Long l = (temp * arr[i]);
				if (l >= max) {
					break;
				}
				pq.add(l);

				if (temp % arr[i] == 0) {
					break;
				}

			}
		}
	}
}