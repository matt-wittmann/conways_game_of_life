package com.mattwittmann.gameoflife

import scala.collection.mutable.Set

/**
 * Represents the grid of living and dead cells in a two-dimensional, rectangular space
 * for Conway's Game of Life.
 */
trait Grid extends Set[Cell] {
	/**
	 * Brings the cell to life in the current state if it is not already.
	 *
	 * @param cell The coordinates of the cell
	 * @return This Grid object
	 */
	override def +=(cell: Cell): Grid.this.type

	/**
	 * Kills the cell in the current state if it is not dead already.
	 *
	 * @param cell The coordinates of the cell
	 * @return This Grid object
	 */
	override def -=(cell: Cell): Grid.this.type

	/**
	 * @param cell The coordinates of the cell
	 * @return Whether the cell at this position is alive
	 */
	override def contains(cell: Cell): Boolean

	/**
	 * Iterates over each living cell in this Grid.
	 *
	 * @return Enables iteration over this Grid
	 */
	override def iterator(): Iterator[Cell]

	/**
	 * @param cell The coordinates of the cell
	 * @return Whether the Cell (x, y+1) is alive
	 */
	def north(cell: Cell) = contains(cell._1, cell._2 + 1)

	/**
	 * @param cell The coordinates of the cell
	 * @return Whether the Cell (x+1, y+1) is alive
	 */
	def northeast(cell: Cell) = contains(cell._1 + 1, cell._2 + 1)

	/**
	 * @param cell The coordinates of the cell
	 * @return Whether the Cell (x+1, y) is alive
	 */
	def east(cell: Cell) = contains(cell._1 + 1, cell._2)

	/**
	 * @param cell The coordinates of the cell
	 * @return Whether the Cell (x+1, y-1) is alive
	 */
	def southeast(cell: Cell) = contains(cell._1 + 1, cell._2 - 1)

	/**
	 * @param cell The coordinates of the cell
	 * @return Whether the Cell (x, y-1) is alive
	 */
	def south(cell: Cell) = contains(cell._1, cell._2 - 1)

	/**
	 * @param cell The coordinates of the cell
	 * @return Whether the Cell (x-1, y-1) is alive
	 */
	def southwest(cell: Cell) = contains(cell._1 - 1, cell._2 - 1)

	/**
	 * @param cell The coordinates of the cell
	 * @return Whether the Cell (x-1, y) is alive
	 */
	def west(cell: Cell) = contains(cell._1 - 1, cell._2)

	/**
	 * @param cell The coordinates of the cell
	 * @return Whether the Cell (x-1, y+1) is alive
	 */
	def northwest(cell: Cell) = contains(cell._1 - 1, cell._2 + 1)

	/**
	 * @param cell The coordinates of the cell
	 * @return The number of living neighbors of the cell
	 */
	def countNeighbors(cell: Cell) =
		List(north _, northeast _, east _, southeast _, south _, southwest _, west _, northwest _).filter(_(cell)).length

	/**
	 * @param cell The coordinates of a cell whose next state is to be determined
	 * @return true for alive and false for dead
	 */
	def nextState(cell: Cell): Boolean = (contains(cell), countNeighbors(cell)) match {
		// Any live cell with two or three live neighbors lives on to the next generation.
		// Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
		case (_, 3) => true
		// Any live cell with two or three live neighbors lives on to the next generation.
		case (true, 2) => true
		// Any live cell with fewer than two live neighbors dies, as if caused by under-population.
		// Any live cell with more than three live neighbors dies, as if by overcrowding.
		// Dead cells stay dead by default: Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
		case _ => false
	}

	/**
	 * @return The grid in its next state after the game's rules have been applied
	 */
	def tick(): Grid

	/**
	 * @return A sequence of tuples containing the coordinates of all living cells in this grid
	 */
	def getLivingCellCoordinates(): Seq[Cell]


	/**
	 * @return The grid as a string with X representing living cells and O representing dead cells.
	 */
	override def mkString(): String = {
	  val buffer = new StringBuilder
	  val livingCellCoordinates = getLivingCellCoordinates
	  val maxX = livingCellCoordinates.maxBy(_._1)._1
	  val maxY = livingCellCoordinates.maxBy(_._2)._2
	  val minX = livingCellCoordinates.minBy(_._1)._1
	  val minY = livingCellCoordinates.minBy(_._2)._2
	  for (y <- Range(minY - 2, maxY + 2)) {
	    for (x <- Range(minX - 2, maxX + 2)) {
	      if (livingCellCoordinates.contains((x, y)))
	        buffer ++= "X"
	      else
	        buffer ++= "O"
	    }
	    buffer ++= "\n"
	  }
	  buffer.toString
	}
}