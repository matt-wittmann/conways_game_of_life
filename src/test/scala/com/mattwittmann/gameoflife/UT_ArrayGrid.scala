package com.mattwittmann.gameoflife

import org.scalatest.FunSuite

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
}