data class MinesweeperBoard(val inputBoard: List<String>) {
    companion object {
        private const val MINE = '*'
    }
    private val outputBoard = mutableListOf<String>()

    fun withNumbers(): List<String> {
        var minesCount: Int
        for (row in inputBoard.indices){
            outputBoard.add("")
            for (col in 0 until inputBoard[row].length){
                if (inputBoard[row][col] == MINE){
                    outputBoard[row] = outputBoard[row] + MINE
                }
                else {
                    minesCount = mine(row - 1, col - 1) + mine(row - 1, col) + mine(row - 1, col + 1) +
                            mine(row, col - 1) + mine(row, col + 1) +
                            mine(row + 1, col - 1) + mine(row + 1, col) + mine(row + 1, col + 1)
                    val minesCountString: String = if (minesCount==0) " " else minesCount.toString()
                    outputBoard[row] = outputBoard[row] + minesCountString
                }
            }
        }
        return outputBoard
    }

    private fun mine(row: Int, col: Int): Int {
        if ((row>-1) and (row<inputBoard.size) and
            (col>-1) and (col<inputBoard[0].length)){
                if (inputBoard[row][col] == MINE) return 1
            }
            return 0
    }
}
