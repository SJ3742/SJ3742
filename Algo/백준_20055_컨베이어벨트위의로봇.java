import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_20055_컨베이어벨트위의로봇 {
	public static class Node {
		int HP;
		Node forth;
		Node back;
		boolean isRobot;

		public Node(int HP) {
			this.HP = HP;
			this.forth = null;
			this.back = null;
			this.isRobot = false;
		}

	}

	public static int count = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		Node head = new Node(Integer.parseInt(st.nextToken()));
		Node cur = head;
		for (int i = 1; i < 2 * N; i++) {
			Node temp = new Node(Integer.parseInt(st.nextToken()));
			cur.forth = temp;
			temp.back = cur;
			cur = cur.forth;
		}
		
		Node tail = cur;
		tail.forth = head;
		head.back = tail;
		
		int brokeCount = 0;
		Node nPosition = head;
		
		for (int i = 0; i < N - 1; i++) {
			nPosition = nPosition.forth;
		}
		while (true) {
			count++;
			// 돌리기
			head = head.back;
			tail = tail.back;
			nPosition = nPosition.back;
			// 로봇 내리기 (N번 자리)
			if (nPosition.isRobot) {
				nPosition.isRobot = false; // N번 자리에 도달한 로봇을 내림
			}
			cur = nPosition;
			while (cur != tail) {
				cur = cur.back;
				if (cur.isRobot && !cur.forth.isRobot && cur.forth.HP>0) {
					cur.isRobot = false;
					cur.forth.isRobot = true;
					cur.forth.HP--;
					if (cur.forth.HP == 0) {
						brokeCount++;
					}
				}
			}
			if (nPosition.isRobot) {
				nPosition.isRobot = false; // N번 자리에 도달한 로봇을 내림
			}
			
			// 올리기
			if (head.HP>0 && !head.isRobot) {
				head.isRobot = true;
				head.HP--;
				if (head.HP == 0) {
					brokeCount++;
				}

			}
			cur = head;
			// 마지막 확인
			if (brokeCount >= K) {
				break;
			}
		}
		System.out.println(count);

	}
}
