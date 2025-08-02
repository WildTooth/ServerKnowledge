package com.github.wildtooth.knowledge.api.regions

import com.github.wildtooth.knowledge.api.math.BlockVector3

class CuboidRegion : Region {

  constructor(
    id: String,
    displayName: String,
    weight: Int,
    min: BlockVector3,
    max: BlockVector3
  ) : super(id, displayName, weight) {
    this.setMinMaxPoints(min, max)
  }

  private fun setMinMaxPoints(min: BlockVector3, max: BlockVector3) {
    this.setMinMaxPoints(listOf(min, max))
  }

  override fun contains(point: BlockVector3): Boolean {
    val x: Int = point.x
    val y: Int = point.y
    val z: Int = point.z
    return (((x >= min!!.x) && (x < max!!.x + 1)) && ((y >= min!!.y) && (y < max!!.y + 1)) && ((z >= min!!.z) && (z < max!!.z + 1)))
  }
}