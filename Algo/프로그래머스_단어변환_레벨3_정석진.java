import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_단어변환_레벨3_정석진 {
	class Solution {
		public static boolean getDiff(String own, String target) {
			int result = 0;
			int len = own.length();
			for (int i = 0; i < len; i++) {
				if (own.charAt(i) != target.charAt(i)) {
					result++;
				}
				if(result>1) return false;
			} 
			return result == 1;
		}

		public static class Element {
			String s;
			int step;

			Element(String s, int step) {
				this.s = s;
				this.step = step;
			}
		}

		public int solution(String begin, String target, String[] words) {
			Queue<Element> q = new LinkedList<>();
			q.add(new Element(begin, 0));
			boolean contain = false;
			long visited = 0;
			int len = words.length;
			for (String word : words) {
				if (word.equals(target)) {
					contain = true;
					break;
				}
			}
			if (!contain)
				return 0;

			while (!q.isEmpty()) {
				Element e = q.poll();
				if (e.s.equals(target)) {
					return e.step;
				}
				for (int i = 0; i < len; i++) {
					if (getDiff(e.s, words[i]) && !((visited & (1 << i)) == (1 << i))) {
						visited |= (1 << i);
						q.add(new Element(words[i], e.step + 1));
					}
				}

			}
			return 0;
		}
	}

}
