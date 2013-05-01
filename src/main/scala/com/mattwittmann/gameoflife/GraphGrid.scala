package com.mattwittmann.gameoflife

import scala.collection.Seq

/**
 * TODO How about some kind of graph that allows connections to the next closest living cells
 * in all eight neighboring directions? Right now we're storing redundant information since
 * a dead cell is the default case; also, we are artificially restricted to a presized grid.
 */
class GraphGrid extends Grid {
  def enliven(cell: Tuple2[Int, Int]) {
    // TODO
  }

  def kill(cell: Tuple2[Int, Int]) {
    // TODO
  }

  def alive(cell: Tuple2[Int, Int]): Boolean = {
    // TODO
    false
  }

  def tick(): Grid = {
    // TODO
    this
  }

  def getLivingCellCoordinates(): Seq[Tuple2[Int, Int]] = {
    // TODO
    Nil
  }
}