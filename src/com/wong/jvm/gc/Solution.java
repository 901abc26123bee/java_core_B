package com.wong.jvm.gc;

class Solution {
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.totalNQueens(5));
	}
	public int totalNQueens(int n) {
		int res = 0;
		char[][] board = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = '.';
			}
		}

		backtrack(res, board, 0, n);
		return res;
	}

	public void backtrack(int res, char[][] board, int row, int n) {
		if (row == n) {
			res++;
			System.out.println(res);
			return;
		}
		for (int col = 0; col < n; col++) {
			// 判断该位置能否放置
			if (!isValid(board, row, col))
				continue;
			// 做選擇
			board[row][col] = 'Q';
			// 進入下一行決策
			backtrack(res, board, row + 1, n);
			// 撤銷選擇
			board[row][col] = '.';
		}

	}

	public boolean isValid(char[][] board, int row, int col) {
		int len = board.length;
		// 檢查列是否有皇后互相衝突(是否有之前的row有將'Q'放置在當前col --> define in backtrack method)
		for (int i = 0; i < len; i++) {
			if (board[i][col] == 'Q')
				return false;
		}
		// 檢查右上方是否有皇后互相衝突
		int m = row - 1, n = col + 1;
		while (m >= 0 && n < len) {
			if (board[m][n] == 'Q')
				return false;
			m--;
			n++;
		}
		// 檢查左上方是否有皇后互相衝突
		m = row - 1;
		n = col - 1;
		while (m >= 0 && n >= 0) {
			if (board[m][n] == 'Q')
				return false;
			m--;
			n--;
		}
		return true;
	}
}