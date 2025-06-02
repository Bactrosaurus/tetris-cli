enum class TetrominoType(val value: Int) {
    I(1), O(2), T(3), S(4), Z(5), J(6), L(7)
}

data class Tetromino(
    val shape: Array<IntArray>,
    val type: TetrominoType
) {
    fun rotate(): Tetromino {
        val n = shape.size
        val rotated = Array(n) { IntArray(n) }

        for (i in 0 until n) {
            for (j in 0 until n) {
                rotated[j][n - 1 - i] = shape[i][j]
            }
        }

        return Tetromino(rotated, type)
    }

    companion object {
        fun createRandom(): Tetromino {
            val types = TetrominoType.values()
            val randomType = types.random()
            return createPiece(randomType)
        }

        private fun createPiece(type: TetrominoType): Tetromino {
             val shape = when (type) {
                 TetrominoType.I -> arrayOf(
                     intArrayOf(0, 0, 0, 0),
                     intArrayOf(1, 1, 1, 1),
                     intArrayOf(0, 0, 0, 0),
                     intArrayOf(0, 0, 0, 0)
                 )
                 TetrominoType.O -> arrayOf(
                     intArrayOf(1, 1),
                     intArrayOf(1, 1)
                 )
                 TetrominoType.T -> arrayOf(
                     intArrayOf(0, 1, 0),
                     intArrayOf(1, 1, 1),
                     intArrayOf(0, 0, 0)
                 )
                 TetrominoType.S -> arrayOf(
                     intArrayOf(0, 1, 1),
                     intArrayOf(1, 1, 0),
                     intArrayOf(0, 0, 0)
                 )
                 TetrominoType.Z -> arrayOf(
                     intArrayOf(1, 1, 0),
                     intArrayOf(0, 1, 1),
                     intArrayOf(0, 0, 0)
                 )
                 TetrominoType.J -> arrayOf(
                     intArrayOf(1, 0, 0),
                     intArrayOf(1, 1, 1),
                     intArrayOf(0, 0, 0)
                 )
                 TetrominoType.L -> arrayOf(
                     intArrayOf(0, 0, 1),
                     intArrayOf(1, 1, 1),
                     intArrayOf(0, 0, 0)
                 )
             }
            return Tetromino(shape, type)
        }
    }
}