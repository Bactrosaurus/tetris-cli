class GameBoard(val width: Int = 10, val height: Int = 20) {
    private val board = Array(height) { IntArray(width) { 0 } }

    fun isValidPosition(piece: Tetromino, x: Int, y: Int): Boolean {
        for (row in piece.shape.indices) {
            for (col in piece.shape[row].indices) {
                if (piece.shape[row][col] != 0) {
                    val boardX = x + col
                    val boardY = y + row

                    // Check boundaries
                    if (boardX < 0 || boardX >= width || boardY >= height) return false
                    // Check collision with existing blocks
                    if (boardY >= 0 && board[boardY][boardX] != 0) return false
                }
            }
        }
        return true
    }

    fun placePiece(piece: Tetromino, x: Int, y: Int) {
        for (row in piece.shape.indices) {
            for (col in piece.shape[row].indices) {
                if (piece.shape[row][col] != 0) {
                    val boardX = x + col
                    val boardY = y + row
                    if (boardY >= 0) {
                        board[boardY][boardX] = piece.type.value
                    }
                }
            }
        }
    }

    fun clearLines(): Int {
        var linesCleared = 0
        for (row in height - 1 downTo 0) {
            if (board[row].all { it != 0 }) {
                // Remove full line
                for (moveRow in row downTo 1) {
                    board[moveRow] = board[moveRow - 1].copyOf()
                }
                board[0] = IntArray(width) { 0 }
                linesCleared++
            }
        }
        return linesCleared
    }

    fun getCell(x: Int, y: Int): Int = board[y][x]
    fun isEmpty(x: Int, y: Int): Boolean = board[y][x] == 0
}