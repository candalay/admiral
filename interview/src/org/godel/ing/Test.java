package org.godel.ing;

import java.util.HashSet;

public class Test {

	public static void main(String[] args) {
		// System.out.println(solution(new int[] { 5, 4, 0, 3, 1, 6, 2 }));
		// System.out.println(solution(new int[] { 1, 2, 3, 4, 4, 6, 4}));

		// System.out.println(solution(5,17));
		System.out.println(solution(0, 37));
		// System.out.println(solution(9736));
		// System.out.println(Integer.toBinaryString(1<<29));
		// System.out.println(solution(Integer.parseInt("110000000000000000000000000000",
		// 2)));
		// System.out.println(solution(536870912));
	}

	public static int solution(int[] A) {

		int maxSoFar = 0;

		if (A == null || A.length == 0) {
			return maxSoFar;
		}
		int totalVisit = 0;
		Integer[] memory = new Integer[A.length];
		for (int i = 0; i < A.length && totalVisit != A.length; i++) {
			int j = i;
			if (memory[j] != null) {
				continue;
			}
			int count = 0;
			HashSet<Integer> set = new HashSet<>();
			while (!set.contains(j)) {
				count++;
				set.add(j);
				j = A[j];
			}
			for (int k : set) {
				memory[k] = count;
			}
			totalVisit += set.size();
			maxSoFar = Math.max(maxSoFar, count);
		}

		return maxSoFar;
	}

	static int solution(int N) {
		int largest = N;
		int shift = 0;
		int temp = N;
		for (int i = 1; i < 30; ++i) {
			int index = (temp & 1);
			temp = ((temp >> 1) | (index << 29));
			if (temp > largest) {
				largest = temp;
				shift = i;
			}
		}
		return shift;
	}

	public static int solution(int a, int b) {

		if (a <= 0) {
			if (b <= 0) {
				return 0;
			}
			return (int) Math.sqrt(b);
		}

		double sqrt = Math.sqrt(a);
		int start = (int) sqrt;
		if ((int) sqrt < sqrt) {
			start++;
		}
		int end = (int) Math.sqrt(b);
		return end - start + 1;
	}

}
