package com.github.wildtooth.knowledge.math

import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

class BlockVector2(val x: Int, val z: Int) {

  fun withX(x: Int): BlockVector2 {
    return at(x, this.z)
  }

  fun withZ(z: Int): BlockVector2 {
    return at(this.x, z)
  }

  fun add(other: BlockVector2): BlockVector2 {
    return add(other.x, other.z)
  }

  fun add(x: Int, z: Int): BlockVector2 {
    return at(this.x + x, this.z + z)
  }

  fun add(vararg others: BlockVector2): BlockVector2 {
    var newX = x
    var newZ = z

    for (other in others) {
      newX += other.x
      newZ += other.z
    }

    return at(newX, newZ)
  }

  fun subtract(other: BlockVector2): BlockVector2 {
    return subtract(other.x, other.z)
  }

  fun subtract(x: Int, z: Int): BlockVector2 {
    return at(this.x - x, this.z - z)
  }

  fun subtract(vararg others: BlockVector2): BlockVector2 {
    var newX = x
    var newZ = z

    for (other in others) {
      newX -= other.x
      newZ -= other.z
    }

    return at(newX, newZ)
  }

  fun multiply(other: BlockVector2): BlockVector2 {
    return multiply(other.x, other.z)
  }

  fun multiply(x: Int, z: Int): BlockVector2 {
    return at(this.x * x, this.z * z)
  }

  fun multiply(vararg others: BlockVector2): BlockVector2 {
    var newX = x
    var newZ = z

    for (other in others) {
      newX *= other.x
      newZ *= other.z
    }

    return at(newX, newZ)
  }

  fun multiply(n: Int): BlockVector2 {
    return multiply(n, n)
  }

  fun divide(other: BlockVector2): BlockVector2 {
    return divide(other.x, other.z)
  }

  fun divide(x: Int, z: Int): BlockVector2 {
    return at(this.x / x, this.z / z)
  }

  fun divide(n: Int): BlockVector2 {
    return divide(n, n)
  }

  fun length(): Double {
    return sqrt(lengthSquared().toDouble());
  }

  fun lengthSquared(): Int {
    return x * x + z * z
  }

  fun distance(other: BlockVector2): Double {
    return sqrt(distanceSq(other).toDouble())
  }

  fun distanceSq(other: BlockVector2): Int {
    val dx = other.x - x
    val dz = other.z - z
    return dx * dx + dz * dz
  }

  fun containedWithin(min: BlockVector2, max: BlockVector2): Boolean {
    return (((x >= min.x) && (x <= max.x)) && ((z >= min.z) && (z <= max.z)))
  }

  fun getMinimum(v2: BlockVector2): BlockVector2 {
    return BlockVector2(
      min(x, v2.x),
      min(z, v2.z)
    )
  }

  fun getMaximum(v2: BlockVector2): BlockVector2 {
    return BlockVector2(
      max(x, v2.x),
      max(z, v2.z)
    )
  }

  fun toBlockVector3(): BlockVector3 {
    return toBlockVector3(0)
  }

  fun toBlockVector3(y: Int): BlockVector3 {
    return BlockVector3.at(x, y, z)
  }

  override fun toString(): String {
    return "BlockVector2(x=$x, z=$z)"
  }

  operator fun plus(other: BlockVector2): BlockVector2 {
    return add(other.x, other.z)
  }

  operator fun minus(other: BlockVector2): BlockVector2 {
    return subtract(other.x, other.z)
  }

  operator fun times(other: BlockVector2): BlockVector2 {
    return multiply(other.x, other.z)
  }

  operator fun times(n: Int): BlockVector2 {
    return multiply(n)
  }

  operator fun div(other: BlockVector2): BlockVector2 {
    return divide(other.x, other.z)
  }

  operator fun div(n: Int): BlockVector2 {
    return divide(n)
  }

  operator fun unaryMinus(): BlockVector2 {
    return at(-x, -z)
  }

  companion object {
    val ZERO: BlockVector2 = BlockVector2(0, 0)
    val ONE: BlockVector2 = BlockVector2(1, 1)

    fun at(x: Double, z: Double): BlockVector2 {
      return at(floor(x).toInt(), floor(z).toInt())
    }

    fun at(x: Int, z: Int): BlockVector2 {
      when (x) {
        0 -> if (z == 0) {
          return ZERO
        }
        1 -> if (z == 1) {
          return ONE
        }
        else -> {}
      }
      return BlockVector2(x, z)
    }
  }
}