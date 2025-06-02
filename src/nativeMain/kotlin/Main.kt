import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

fun main() = runBlocking(Dispatchers.Default) {
    val game = TetrisGame()
    game.start()
}