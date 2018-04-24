package eu.ha3.x.exercise.listener.stdout

import eu.ha3.x.exercise.domain.Mower
import eu.ha3.x.exercise.domain.OnMowingExecutedListener

/**
 * (Default template)
 * Created on 2018-04-11
 *
 * @author Ha3
 */
class StdoutListener : OnMowingExecutedListener {
    override fun invoke(state: Mower.State) {
        val (xPosition, yPosition, orientation) = state

        val orientationToDisplay = when (orientation) {
            Mower.Orientation.NORTH -> "N"
            Mower.Orientation.EAST -> "E"
            Mower.Orientation.SOUTH -> "S"
            Mower.Orientation.WEST -> "W"
        }

        println("$xPosition $yPosition $orientationToDisplay")
    }
}