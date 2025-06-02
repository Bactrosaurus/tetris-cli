class Renderer {
    fun render(gameState: GameState) {
        clearScreen()

        // Render game board with current piece
        for (y in 0 until gameState.board.height) {
            print("|")
            for (x in 0 until gameState.board.width) {
                val char = when {
                    isPieceAt(gameState.currentPiece, gameState.pieceX, gameState.pieceY, x, y) -> "▓"
                    gameState.board.getCell(x, y) != 0 -> "█"
                    else -> " "
                }
                print(char)
            }
            println("|")
        }

        // Bottom border
        print("+")
        repeat(gameState.board.width) { print("-") }
        println("+")

        // Stats
        println("Score: ${gameState.score}")
        println("Level: ${gameState.level}")
        println("Lines: ${gameState.linesCleared}")
        println("Controls: A/D=move, S=rotate, W=drop, Q=quit")
    }

    private fun isPieceAt(piece: Tetromino, pieceX: Int, pieceY: Int, x: Int, y: Int): Boolean {
        val localX = x - pieceX
        val localY = y - pieceY

        return localX >= 0 && localX < piece.shape[0].size &&
                localY >= 0 && localY < piece.shape.size &&
                piece.shape[localY][localX] != 0
    }

    private fun clearScreen() {
        print("\u001b[2J\u001b[H") // ANSI clear screen and move cursor to top
    }

}