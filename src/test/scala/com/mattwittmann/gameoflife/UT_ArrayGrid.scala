package com.mattwittmann.gameoflife

import org.scalatest.FunSuite
import scala.collection.mutable.Map

class UT_ArrayGrid extends FunSuite {
	test("A grid of all dead cells has no living-cell coordinates.") {
		val grid = ArrayGrid()
		assert(Nil == grid.getLivingCellCoordinates)
	}

	test("A grid of all dead cells generates no living cells on the next tick.") {
		var grid = ArrayGrid()
		grid = grid.tick
		assert(Nil == grid.getLivingCellCoordinates)
	}

	test("Seed with some living cells and verify coordinates.") {
		val grid = ArrayGrid((1, 4), (2, 3), (2, 4), (5, 4))
		assert(List((1,4), (2,3), (2,4), (5,4)) == grid.getLivingCellCoordinates)
	}

	test("Seed with some living cells and let the game tick!") {
		var grid = ArrayGrid((1, 4), (2, 3), (2, 4), (5, 4))
		grid = grid.tick
		assert(List((1,3), (1,4), (2,3), (2,4)) == grid.getLivingCellCoordinates)
	}

	test("Test implementation of iterator().") {
		val cells = Map((1, 3) -> false, (7, 9) -> false, (1, 2) -> false, (2, 3) -> false)
		val grid = ArrayGrid((1, 3), (7, 9), (1, 2), (2, 3))
		grid.foreach {cell => if (cells.isDefinedAt(cell)) cells(cell) = true; else fail(s"Unexpected living cell: $cell")}
		assert(cells.filterNot {_._2}.isEmpty)
	}
}