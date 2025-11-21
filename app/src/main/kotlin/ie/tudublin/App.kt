package ie.tudublin

import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: sudoku <path-to-board.txt>")
        return
    }

    val path = args[0]
    val board = File(path).readLines().map {
        it.trim().split(Regex("\\s+")).map(String::toInt).toIntArray()
    }.toTypedArray()

    println("Input board:")
    board.forEach { println(it.joinToString(" ")) }

    val solver = Sudoku(board)
    if (solver.solve()) {
        println("\nSolved board:")
        solver.getBoard().forEach { println(it.joinToString(" ")) }
    } else {
        println("Could not find a solution (iterations=${solver.iterations})")
    }
}
