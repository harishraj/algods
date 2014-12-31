package com.interview.dynamic;

/**
 * http://www.geeksforgeeks.org/longest-common-substring/
 */
public class LongestCommonSubsequence {

	public int lcs(char str1[], char str2[], int len1, int len2) {

		if (len1 == str1.length) {
			return 0;
		}
		if (len2 == str2.length) {
			return 0;
		}

		if (str1[len1] == str2[len2]) {
			return 1 + lcs(str1, str2, len1 + 1, len2 + 1);
		} else {
			return Math.max(lcs(str1, str2, len1 + 1, len2),
					lcs(str1, str2, len1, len2 + 1));
		}
	}

	public int lcsDynamic(char str1[], char str2[]) {

		int c[][] = new int[str1.length + 1][str2.length + 1];
		int max = 0;
		for (int i = 1; i < c.length; i++) {
			for (int j = 1; j < c[i].length; j++) {
				c[i][j] = Math.max(c[i][j - 1], c[i - 1][j]);
				if (str1[i - 1] == str2[j - 1]) {
					c[i][j] = c[i - 1][j - 1] + 1;
					if (c[i][j] > max) {
						max = c[i][j];
					}
				}
			}
		}
		return max;

	}

	public static void main(String args[]) {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		String str1 = "ABCDGHLQR";
		String str2 = "AEDPHR";

		int result = lcs.lcsDynamic(str1.toCharArray(), str2.toCharArray());
		System.out.print(result);
	}

}
