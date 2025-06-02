import kotlinx.coroutines.*

class TetrisGame {
    private val engine = GameEngine()
    private val renderer = Renderer()
    private var isRunning = true

    suspend fun start() {
        println("Starting Tetris...")
        println("Use WASD keys + Enter for controls")

        // Main game loop
        val gameJob = CoroutineScope(Dispatchers.Default).launch {
            while (isRunning) {
                val gameState = engine.getGameState()
                renderer.render(gameState)

                if (!engine.update()) {
                    println("Game Over!")
                    isRunning = false
                }

                delay((1000 - (gameState.level * 50).coerceAtMost(900)).toLong())
            }
        }

        val inputJob = CoroutineScope(Dispatchers.IO).launch {
            while (isRunning) {
                val input = readln().lowercase().firstOrNull() // This should be done different
                when (input) {
                    'a' -> engine.moveLeft()
                    'd' -> engine.moveRight()
                    's' -> engine.rotate()
                    'w' -> engine.hardDrop()
                    'q' -> isRunning = false
                }
            }
        }

        joinAll(gameJob, inputJob) // wait for game and input job to complete
    }
}