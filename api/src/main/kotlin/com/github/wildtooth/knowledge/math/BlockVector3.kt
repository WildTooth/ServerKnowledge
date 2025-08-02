package com.github.wildtooth.knowledge.math

import kotlin.math.*

class BlockVector3(val x: Int, val y: Int, val z: Int) {

  fun withX(x: Int): BlockVector3 {
    return BlockVector3(x, this.y, this.z)
  }

  fun withY(y: Int): BlockVector3 {
    return BlockVector3(this.x, y, this.z)
  }

  fun withZ(z: Int): BlockVector3 {
    return BlockVector3(this.x, this.y, z)
  }

  fun add(other: BlockVector3): BlockVector3 {
    return add(other.x, other.y, other.z)
  }

  fun add(x: Int, y: Int, z: Int): BlockVector3 {
    return at(this.x + x, this.y + y, this.z + z)
  }

  fun add(vararg others: BlockVector3): BlockVector3 {
    var newX = x
    var newY = y
    var newZ = z

    for (other in others) {
      newX += other.x
      newY += other.y
      newZ += other.z
    }

    return at(newX, newY, newZ)
  }

  fun subtract(other: BlockVector3): BlockVector3 {
    return subtract(other.x, other.y, other.z)
  }

  fun subtract(x: Int, y: Int, z: Int): BlockVector3 {
    return at(this.x - x, this.y - y, this.z - z)
  }

  fun subtract(vararg others: BlockVector3): BlockVector3 {
    var newX = x
    var newY = y
    var newZ = z

    for (other in others) {
      newX -= other.x
      newY -= other.y
      newZ -= other.z
    }

    return at(newX, newY, newZ)
  }

  fun multiply(other: BlockVector3): BlockVector3 {
    return multiply(other.x, other.y, other.z)
  }

  fun multiply(x: Int, y: Int, z: Int): BlockVector3 {
    return at(this.x * x, this.y * y, this.z * z)
  }

  fun multiply(vararg others: BlockVector3): BlockVector3 {
    var newX = x
    var newY = y
    var newZ = z

    for (other in others) {
      newX *= other.x
      newY *= other.y
      newZ *= other.z
    }

    return at(newX, newY, newZ)
  }

  fun multiply(n: Int): BlockVector3 {
    return multiply(n, n, n)
  }

  fun divide(other: BlockVector3): BlockVector3 {
    return divide(other.x, other.y, other.z)
  }


  fun divide(x: Int, y: Int, z: Int): BlockVector3 {
    return at(this.x / x, this.y / y, this.z / z)
  }

  fun divide(n: Int): BlockVector3 {
    return divide(n, n, n)
  }

  fun length(): Double {
    return sqrt(lengthSq().toDouble())
  }

  fun lengthSq(): Int {
    return x * x + y * y + z * z
  }

  fun distance(other: BlockVector3): Double {
    return sqrt(distanceSq(other).toDouble())
  }

  fun distanceSq(other: BlockVector3): Int {
    val dx = other.x - x
    val dy = other.y - y
    val dz = other.z - z
    return dx * dx + dy * dy + dz * dz
  }

  fun containedWithin(min: BlockVector3, max: BlockVector3): Boolean {
    return (((x >= min.x) && (x <= max.x)) && ((y >= min.y) && (y <= max.y)) && ((z >= min.z) && (z <= max.z)))
  }

  fun toPitch(): Double {
    val x = this.x.toDouble()
    val z = this.z.toDouble()

    if (x == 0.0 && z == 0.0) {
      return (if (y > 0) -90 else 90).toDouble()
    } else {
      val x2 = x * x
      val z2 = z * z
      val xz = sqrt(x2 + z2)
      return Math.toDegrees(atan(-y / xz))
    }
  }

  fun toYaw(): Double {
    val x = this.x.toDouble()
    val z = this.z.toDouble()

    val t = atan2(-x, z)
    val tau = 2 * Math.PI

    return Math.toDegrees(((t + tau) % tau))
  }

  fun getMinimum(v2: BlockVector3): BlockVector3 {
    return BlockVector3(
      min(x, v2.x),
      min(y, v2.y),
      min(z, v2.z)
    )
  }

  fun getMaximum(v2: BlockVector3): BlockVector3 {
    return BlockVector3(
      max(x, v2.x),
      max(y, v2.y),
      max(z, v2.z)
    )
  }

  fun toBlockVector2(): BlockVector2 {
    return BlockVector2.at(x, z)
  }

  override fun toString(): String {
    return "BlockVector3(x=$x, y=$y, z=$z)"
  }

  operator fun plus(other: BlockVector3): BlockVector3 {
    return add(other.x, other.y, other.z)
  }

  operator fun minus(other: BlockVector3): BlockVector3 {
    return subtract(other.x, other.y, other.z)
  }

  operator fun times(other: BlockVector3): BlockVector3 {
    return multiply(other.x, other.y, other.z)
  }

  operator fun times(n: Int): BlockVector3 {
    return multiply(n)
  }

  operator fun div(other: BlockVector3): BlockVector3 {
    return divide(other.x, other.y, other.z)
  }

  operator fun div(n: Int): BlockVector3 {
    return divide(n)
  }

  operator fun unaryMinus(): BlockVector3 {
    return at(-x, -y, -z)
  }

  companion object {
    val ZERO: BlockVector3 = BlockVector3(0, 0, 0)
    val ONE: BlockVector3 = BlockVector3(1, 1, 1)

    fun at(x: Double, y: Double, z: Double): BlockVector3 {
      return at(floor(x).toInt(), floor(y).toInt(), floor(z).toInt())
    }

    fun at(x: Int, y: Int, z: Int): BlockVector3 {
      when (y) {
        0 -> if (x == 0 && z == 0) {
          return ZERO
        }
        1 -> if (x == 1 && z == 1) {
          return ONE
        }
        else -> {}
      }
      return BlockVector3(x, y, z)
    }
  }
}