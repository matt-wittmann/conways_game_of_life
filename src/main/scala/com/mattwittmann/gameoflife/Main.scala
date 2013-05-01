package com.mattwittmann.gameoflife

import collection.mutable.ArrayBuffer

/**
 * Conway's Game of Life.
 *
 * $ 1. Any live cell with fewer than two live neighbours dies, as if caused by under-population.</li>
 * $ 1. Any live cell with two or three live neighbours lives on to the next generation.</li>
 * $ 1. Any live cell with more than three live neighbours dies, as if by overcrowding.</li>
 * $ 1. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.</li>
 *
 * @see [[http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life]]
 */
object Main extends App {
	var grid = ArrayGrid((1, 4), (2, 3), (2, 4), (5, 4))
	println(grid.getLivingCellCoordinates)
	println(grid.mkString)
	grid = grid.tick
	println(grid.getLivingCellCoordinates)
	println(grid.mkString)
	grid = grid.tick
	println(grid.getLivingCellCoordinates)
	println(grid.mkString)
}