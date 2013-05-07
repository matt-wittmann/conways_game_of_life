package com.mattwittmann.gameoflife

import scala.collection.Seq
import scala.collection.mutable.Map

/**
 * TODO How about some kind of graph that allows connections to the next closest living cells
 * in all eight neighboring directions? Right now we're storing redundant information since
 * a dead cell is the default case; also, we are artificially restricted to a presized grid.
 */
class GraphGrid extends Grid {
  class CellNode(var living: Boolean = true) {
    /**
     * Nearest living-cell neighbors.
     */
    val closestNeighbors: Map[Cell, CellNode] = Map.empty

    /**
     * Adds a living-cell neighbor with coordinates relative to this node.
     *
     * @param relativeCell The cell to add to closestNeighbors
     */
    def addNeighbor(relativeCell: Cell) {
      closestNeighbors += ((relativeCell, new CellNode()))
      // TODO
    }

    /**
     * Removes a living-cell neighbor with coordinates relative to this node.
     *
     * @param relativeCell The cell to remove from closestNeighbors
     */
    def removeNeighbor(relativeCell: Cell) {
      closestNeighbors -= relativeCell
      // TODO
    }
  }

  /**
   * Absolute coordinates of the root node of the graph are (0, 0).
   */
  val root = new CellNode(false)

  def +=(cell: Cell): GraphGrid.this.type = {
    // TODO
    this
  }

  def -=(cell: Cell): GraphGrid.this.type = {
    // TODO
    this
  }

  def contains(cell: Cell): Boolean = {
    // TODO
    false
  }

  def iterator(): Iterator[Cell] = {
    // TODO
    null
  }

  def tick(): Grid = {
    // TODO
    this
  }

  def getLivingCellCoordinates(): Seq[Cell] = {
    // TODO
    Nil
  }
}