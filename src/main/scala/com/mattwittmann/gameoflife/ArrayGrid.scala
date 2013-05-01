package com.mattwittmann.gameoflife

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.StringBuilder

/**
 * Provides an array implementation of the [[com.mattwittmann.gameoflife.Grid]] trait.
 */
class ArrayGrid(val cells: Array[Array[Boolean]]) extends Grid {
	/**
	 * @param cell The coordinates of the cell
	 * @return Whether the cell coordinates would be outside the 100x100 grid
	 */
	protected def cellOutOfBounds(cell: Tuple2[Int, Int]) = cell._1 < 0 || cell._2 < 0 || cell._1 >= cells.length || cell._2 >= cells(cell._1).length

	/**
	 * @param cell The coordinates of the cell
	 * @param alive true for alive and false for dead
	 */
	protected def set(cell: Tuple2[Int, Int], alive: Boolean) {
		if (!cellOutOfBounds(cell))
			cells(cell._1)(cell._2) = alive
	}

	def enliven(cell: Tuple2[Int, Int]) {
		set(cell, true)
	}

	def kill(cell: Tuple2[Int, Int]) {
		set(cell, false)
	}

	def alive(cell: Tuple2[Int, Int]) =
		if (cellOutOfBounds(cell))
			false
		else
			cells(cell._1)(cell._2)

	def tick() = {
		val grid = new ArrayGrid(Array.ofDim[Boolean](cells.length, cells(0).length))
		for (x <- cells.indices) {
			for (y <- cells(x).indices) {
				val cell = (x, y)
				grid.set(cell, nextState(cell))
			}
		}
		grid
	}

	def getLivingCellCoordinates(): Seq[Tuple2[Int, Int]] = {
		val coordinates = new ArrayBuffer[Tuple2[Int, Int]]
		for (x <- cells.indices) {
			for (y <- cells(x).indices) {
				val cell = (x, y)
				if (alive(cell))
					coordinates += cell
			}
		}
		coordinates.toList
	}

	override def mkString(): String = {
	  val buffer = new StringBuilder
	  for (x <- cells.indices) {
	    for (y <- cells(x).indices) {
	      if (alive(x, y))
	       buffer ++=  "X"
	      else
	        buffer ++= "O"
	    }
	    buffer ++= "\n"
	  }
	  buffer.toString
	}
}

/**
 * Companion object to ArrayGrid class.
 */
object ArrayGrid {
	/**
	 * @param livingCells Coordinates of each living cell in a 100x100 grid
	 * @return A new instance of an ArrayGrid
	 */
	def apply(livingCells: Tuple2[Int, Int]*): ArrayGrid = {
		// Defaulting to a 100x100 grid
		val grid = new ArrayGrid(Array.ofDim[Boolean](100, 100))
		livingCells.foreach(grid.enliven)
		grid
	}
}