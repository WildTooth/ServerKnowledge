package com.github.wildtooth.knowledge.api.regions

import com.github.wildtooth.knowledge.api.math.BlockVector3

abstract class Region {
  val id: String
  val displayName: String

  protected var min: BlockVector3? = null
  protected var max: BlockVector3? = null

  var weight: Int

  internal constructor(id: String, displayName: String, weight: Int) {
    this.id = id
    this.displayName = displayName
    this.weight = weight
  }

  protected fun setMinMaxPoints(points: List<BlockVector3>) {
    var minX: Int = points.first().x
    var minY: Int = points.first().y
    var minZ: Int = points.first().z
    var maxX = minX
    var maxY = minY
    var maxZ = minZ

    for (v in points) {
      val x: Int = v.x
      val y: Int = v.y
      val z: Int = v.z

      if (x < minX) minX = x
      if (y < minY) minY = y
      if (z < minZ) minZ = z

      if (x > maxX) maxX = x
      if (y > maxY) maxY = y
      if (z > maxZ) maxZ = z
    }

    min = BlockVector3.at(minX, minY, minZ)
    max = BlockVector3.at(maxX, maxY, maxZ)
  }

  abstract fun contains(point: BlockVector3): Boolean

  fun contains(x: Int, y: Int, z: Int): Boolean {
    return contains(BlockVector3.at(x, y, z))
  }

  val minimumPoint: BlockVector3?
    get() = min

  val maximumPoint: BlockVector3?
    get() = max
}