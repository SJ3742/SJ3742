import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_1555_소수만들기 {
    static final int MAX_N = 6, LIMIT = 500001;
    static boolean[] isNotPrime = new boolean[LIMIT];
    static List<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int minPrime = Integer.MAX_VALUE;
        int maxPrime = Integer.MIN_VALUE;
        
        // 소수 판별 배열 생성 (에라토스테네스의 체)
        initializePrimes();

        int numCount = Integer.parseInt(br.readLine());
        int[] numbers = new int[numCount];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numCount; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);
        
        // DP 배열 초기화
        HashSet<Integer>[][] dp = new HashSet[numCount][numCount];
        for (int i = 0; i < numCount; i++) {
            for (int j = 0; j < numCount; j++) {
                dp[i][j] = new HashSet<>();
            }
        }

        // DP 테이블을 이용해 연산 수행
        do {
            for (int i = 0; i < numCount; i++) dp[i][i].add(numbers[i]);

            for (int len = 2; len <= numCount; len++) {
                for (int start = 0; start + len - 1 < numCount; start++) {
                    int end = start + len - 1;
                    for (int mid = start; mid < end; mid++) {
                        for (int left : dp[start][mid]) {
                            for (int right : dp[mid + 1][end]) {
                                dp[start][end].add(left + right);
                                dp[start][end].add(left - right);
                                dp[start][end].add(left * right);
                                if (right != 0) {
                                    dp[start][end].add(left / right);
                                }
                            }
                        }
                    }
                }
            }
            
            // 결과값에서 소수 여부를 판별하여 최소/최대 소수 갱신
            for (int result : dp[0][numCount - 1]) {
                int absResult = Math.abs(result);
                if (absResult < 2) continue;
                
                if (isPrime(absResult)) {
                    minPrime = Math.min(minPrime, absResult);
                    maxPrime = Math.max(maxPrime, absResult);
                }
            }

            // DP 배열 초기화
            for (int i = 0; i < numCount; i++) {
                for (int j = 0; j < numCount; j++) {
                    dp[i][j].clear();
                }
            }
            
        } while (nextPermutation(numbers, numCount));
        
        // 결과 출력
        StringBuilder sb = new StringBuilder();
        if (minPrime == Integer.MAX_VALUE) {
            sb.append("-1\n");
        } else {
            sb.append(minPrime).append("\n").append(maxPrime);
        }
        System.out.println(sb.toString());
    }

    // 에라토스테네스의 체로 소수 판별 배열 초기화
    private static void initializePrimes() {
        for (int i = 2; i < LIMIT; i++) {
            if (!isNotPrime[i]) {
                primes.add(i);
                for (long j = (long) i * i; j < LIMIT; j += i) {
                    isNotPrime[(int) j] = true;
                }
            }
        }
    }

    // 소수 여부 확인
    private static boolean isPrime(int number) {
        if (number < LIMIT) {
            return !isNotPrime[number];
        }
        
        int sqrt = (int) Math.sqrt(number);
        for (int prime : primes) {
            if (prime > sqrt) break;
            if (number % prime == 0) return false;
        }
        return true;
    }

    // 다음 순열 생성 함수
    private static boolean nextPermutation(int[] arr, int length) {
        int i = length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) i--;
        if (i <= 0) return false;
        
        int j = length - 1;
        while (arr[j] <= arr[i - 1]) j--;
        
        int temp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = temp;
        
        j = length - 1;
        while (i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return true;
    }
}

