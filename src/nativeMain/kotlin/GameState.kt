data class GameState(
    val board: GameBoard,
    val currentPiece: Tetromino,
    val pieceX: Int,
    val pieceY: Int,
    val score: Int,
    val level: Int,
    val linesCleared: Int
)
