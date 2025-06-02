class GameEngine {
    private val board = GameBoard()
    private var currentPiece = Tetromino.createRandom()
    private var pieceX = 4
    private var pieceY = 0
    private var score = 0
    private var level = 1
    private var linesCleared = 0

    fun update(): Boolean {
        // Try to move piece down
        if (board.isValidPosition(currentPiece, pieceX, pieceY + 1)) {
            pieceY++
        } else {
            // Lock piece in place
            board.placePiece(currentPiece, pieceX, pieceY)

            // Clear lines and update score
            val cleared = board.clearLines()
            linesCleared += cleared
            score += cleared * 100 * level

            // Level up every 10 lines
            level = (linesCleared / 10) + 1

            // Spawn new piece
            currentPiece = Tetromino.createRandom()
            pieceX = 4
            pieceY = 0

            // Check game over
            if (!board.isValidPosition(currentPiece, pieceX, pieceY)) {
                return false // Game over
            }
        }
        return true
    }

    fun moveLeft() {
        if (board.isValidPosition(currentPiece, pieceX - 1, pieceY)) {
            pieceX--
        }
    }

    fun moveRight() {
        if (board.isValidPosition(currentPiece, pieceX + 1, pieceY)) {
            pieceX++
        }
    }

    fun rotate() {
        val rotated = currentPiece.rotate()
        if (board.isValidPosition(rotated, pieceX, pieceY)) {
            currentPiece = rotated
        }
    }

    fun hardDrop() {
        while (board.isValidPosition(currentPiece, pieceX, pieceY + 1)) {
            pieceY++
        }
    }

    fun getGameState() = GameState(board, currentPiece, pieceX, pieceY, score, level, linesCleared)
}