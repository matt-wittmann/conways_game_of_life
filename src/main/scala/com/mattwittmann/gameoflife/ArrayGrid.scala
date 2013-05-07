package com.mattwittmann.gameoflife

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.StringBuilder

/**
 * Provides an array implementation of the [[com.mattwittmann.gameoflife.Grid]] trait.
 */
class ArrayGrid(val cells: Array[Array[Boolean]]) extends Grid {
	def iterator() = new Iterator[Cell] {
	  var cursor = (0, -1)

	  def nextLivingCell(cursor: Cell): Option[Cell] = {
	    def innerNextLivingCell(cursor: Cell): Option[Cell] = {
	      val x = cursor._1
	      val y = cursor._2

	      if (x < cells.length)
	    	  if (y < cells(x).length)
	    		  if (cells(x)(y))
	    			  Some(cursor)
	    		  else
	    			  innerNextLivingCell((x, y + 1))
	    	  else
	    		  innerNextLivingCell(x + 1, 0)
	    else
	    	None
	    }

	    innerNextLivingCell(cursor._1, cursor._2 + 1)
	  }

	  def hasNext(): Boolean = !nextLivingCell(cursor).isEmpty

	  def next(): Cell = {
	    cursor = nextLivingCell(cursor).getOrElse { throw new ArrayIndexOutOfBoundsException("No next living cell in ArrayGrid.") }
	    cursor
	  }
	}

	/**
	 * @param cell The coordinates of the cell
	 * @return Whether the cell coordinates would be outside the 100x100 grid
	 */
	protected def cellOutOfBounds(cell: Cell) = cell._1 < 0 || cell._2 < 0 || cell._1 >= cells.length || cell._2 >= cells(cell._1).length

	/**
	 * @param cell The coordinates of the cell
	 * @param alive true for alive and false for dead
	 */
	protected def set(cell: Cell, alive: Boolean): ArrayGrid.this.type = {
		if (!cellOutOfBounds(cell))
			cells(cell._1)(cell._2) = alive
		this
	}

	def +=(cell: Cell): ArrayGrid.this.type = set(cell, true)
	def -=(cell: Cell): ArrayGrid.this.type = set(cell, false)

	def contains(cell: Cell) =
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

	def getLivingCellCoordinates(): Seq[Cell] = {
		val coordinates = new ArrayBuffer[Cell]
		for (x <- cells.indices) {
			for (y <- cells(x).indices) {
				val cell = (x, y)
				if (contains(cell))
					coordinates += cell
			}
		}
		coordinates.toList
	}

	override def mkString(): String = {
	  val buffer = new StringBuilder
	  for (x <- cells.indices) {
	    for (y <- cells(x).indices) {
	      if (contains(x, y))
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
	def apply(livingCells: Cell*): ArrayGrid = {
		// Defaulting to a 100x100 grid
		val grid = new ArrayGrid(Array.ofDim[Boolean](100, 100))
		livingCells.foreach(grid +=)
		grid
	}
}