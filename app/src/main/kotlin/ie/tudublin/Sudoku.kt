package ie.tudublin

class Sudoku(private val board: Array<IntArray>) {
    var iterations = 0
    private val maxIterations = 2_000_000

    fun solve(): Boolean {
        iterations = 0
        return solveRecursive()
    }

    private fun findEmpty(): Pair<Int, Int>? {
        for (r in 0 until 9)
            for (c in 0 until 9)
                if (board[r][c] == 0) return r to c
        return null
    }

    private fun isValid(r: Int, c: Int, n: Int): Boolean {
        for (i in 0 until 9)
            if (board[r][i] == n || board[i][c] == n) return false
        val boxRow = r - r % 3
        val boxCol = c - c % 3
        for (i in 0 until 3)
            for (j in 0 until 3)
                if (board[boxRow + i][boxCol + j] == n) return false
        return true
    }

    private fun solveRecursive(): Boolean {
        if (++iterations > maxIterations) return false
        val empty = findEmpty() ?: return true
        val (r, c) = empty
        for (n in 1..9) {
            if (isValid(r, c, n)) {
                board[r][c] = n
                if (solveRecursive()) return true
                board[r][c] = 0
            }
        }
        return false
    }

    fun getBoard(): Array<IntArray> = board
}
