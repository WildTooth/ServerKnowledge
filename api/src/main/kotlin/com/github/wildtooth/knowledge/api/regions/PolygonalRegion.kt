package com.github.wildtooth.knowledge.api.regions

import com.github.wildtooth.knowledge.api.math.BlockVector2
import com.github.wildtooth.knowledge.api.math.BlockVector3
import java.util.List

class PolygonalRegion : Region {
  private var points: MutableList<BlockVector2>? = null
  private var minY = 0
  private var maxY = 0

  constructor(
    id: String,
    displayName: String,
    weight: Int,
    points: MutableList<BlockVector2>,
    minY: Int,
    maxY: Int
  ) : super(id, displayName, weight) {
    init(points, minY, maxY)
  }

  private fun init(points: MutableList<BlockVector2>, minY: Int, maxY: Int) {
    val immutablePoints: MutableList<BlockVector2> = List.copyOf<BlockVector2>(points)
    setMinMaxPoints(immutablePoints, minY, maxY)
    this.points = immutablePoints
    this.minY = min!!.y
    this.maxY = max!!.y
  }

  private fun setMinMaxPoints(points2D: MutableList<BlockVector2>, minY: Int, maxY: Int) {
    val points: MutableList<BlockVector3> = ArrayList()
    var y = minY
    for (point2D in points2D) {
      points.add(BlockVector3.at(point2D.x, y, point2D.z))
      y = maxY
    }
    setMinMaxPoints(points)
  }

  override fun contains(point: BlockVector3): Boolean {
    val targetX: Int = point.x
    val targetY: Int = point.y
    val targetZ: Int = point.z

    if (targetY < minY || targetY > maxY) {
      return false
    }

    if (targetX < min!!.x || targetX > max!!.x || targetZ < min!!.z || targetZ > max!!.z) {
      return false
    }
    var inside = false
    val npoints = points!!.size
    var xNew: Int
    var zNew: Int
    var xOld: Int
    var zOld: Int
    var x1: Int
    var z1: Int
    var x2: Int
    var z2: Int
    var crossProduct: Long

    xOld = points!![npoints - 1].x
    zOld = points!![npoints - 1].z
    for (i in 0 until npoints) {
      xNew = points!![i].x
      zNew = points!![i].z
      if (xNew == targetX && zNew == targetZ) {
        return true
      }
      if (xNew > xOld) {
        x1 = xOld
        x2 = xNew
        z1 = zOld
        z2 = zNew
      } else {
        x1 = xNew
        x2 = xOld
        z1 = zNew
        z2 = zOld
      }
      if (x1 <= targetX && targetX <= x2) {
        crossProduct = ((targetZ.toLong() - z1.toLong()) * (x2 - x1).toLong()
            - (z2.toLong() - z1.toLong()) * (targetX - x1).toLong())
        if (crossProduct == 0L) {
          if ((z1 <= targetZ) == (targetZ <= z2)) return true
        } else if (crossProduct < 0 && (x1 != targetX)) {
          inside = !inside
        }
      }
      xOld = xNew
      zOld = zNew
    }
    return inside
  }
}