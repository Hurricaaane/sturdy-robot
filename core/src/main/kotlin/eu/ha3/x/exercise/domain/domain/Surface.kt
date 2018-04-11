package eu.ha3.x.exercise.domain.domain

/**
 * Rectangular surface on which the Mower will roam through.
 *
 * The x size is the width of the rectangle, East-West.
 * The y size is the height of the rectangle, North-South.
 *
 * Created on 2018-04-11
 *
 * @author Ha3
 */
data class Surface(val xSize: Int, val ySize: Int) {
    init {
        if (xSize <= 0 || ySize <= 0) {
            throw IllegalArgumentException("Size must be strictly positive")
        }
    }
}