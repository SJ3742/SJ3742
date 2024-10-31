import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_아이템줍기_레벨3_정석진 {
    class Solution {
        public static int[][] map;
        public static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        public static boolean[][] visited = new boolean[102][102];

        public static class Element {
            int y;
            int x;
            int step;

            public Element(int y, int x, int step) {
                this.y = y;
                this.x = x;
                this.step = step;
            }

        }

        public static boolean canMove(int r, int c) {
            return map[r][c] == 1;
        }

        public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
            map = new int[102][102]; // 좌표를 2배로 확장했으므로 102x102 크기의 map 사용
            characterX *= 2;
            characterY *= 2;
            itemX *= 2;
            itemY *= 2;

            // 1. 사각형의 경계와 내부 설정하기
            for (int[] rect : rectangle) {
                int x1 = rect[0] * 2;
                int y1 = rect[1] * 2;
                int x2 = rect[2] * 2;
                int y2 = rect[3] * 2;

                // 경계선 표시
                for (int x = x1; x <= x2; x++) {
                    if(map[y1][x]!=-1){
                        map[y1][x] = 1;
                    }
                    if(map[y2][x]!=-1){
                        map[y2][x] = 1;
                    }               
                }
                for (int y = y1; y <= y2; y++) {
                    if(map[y][x1]!=-1){
                        map[y][x1] = 1;
                    }
                    if(map[y][x2]!=-1){
                        map[y][x2] = 1;
                    }
                }

                // 내부 채우기
                for (int x = x1 + 1; x < x2; x++) {
                    for (int y = y1 + 1; y < y2; y++) {
                        map[y][x] = -1; // 내부는 -1로 설정
                    }
                }
            }

            // 2. BFS로 가장 바깥쪽 테두리만 걸을 수 있도록 탐색
            Queue<Element> q = new LinkedList<>();
            q.add(new Element(characterY, characterX, 0));
            visited[characterY][characterX] = true;

            while (!q.isEmpty()) {
                Element e = q.poll();
                if (e.y == itemY && e.x == itemX) {
                    return e.step / 2; // 결과 좌표도 원래 스케일로 맞추기 위해 2로 나눔
                }

                for (int[] d : dir) {
                    int ny = e.y + d[0];
                    int nx = e.x + d[1];
                    // 바깥쪽 경계선(1)만 이동 가능, 내부(-1)는 이동 불가
                    if (map[ny][nx] == 1 && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        q.add(new Element(ny, nx, e.step + 1));
                    }
                }
            }
            return -1;
        }

    }


}
