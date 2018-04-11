package eu.ha3.x.exercise.domain

/**
 * Represents a Mower placed within a surface.
 * When given commands, it will mutate through various states.
 *
 * Created on 2018-04-11
 *
 * @author Ha3
 */
class Mower(private val surface: Surface, private var state: State) {
    init {
        if (!state.isInside(surface)) {
            throw IllegalArgumentException("Initial state is out of bounds: $state cannot be inside $surface")
        }
    }

    /**
     * Applies a command to this Mower, mutating its state.
     *
     * The state may not change if the command would not result in a new position.
     */
    fun execute(command: Command) {
        when (command) {
            Command.FORWARD -> {
                val candidateState = when (state.orientation) {
                    Orientation.NORTH -> CandidateState(state.xPosition, state.yPosition + 1, state.orientation)
                    Orientation.EAST -> CandidateState(state.xPosition + 1, state.yPosition, state.orientation)
                    Orientation.SOUTH -> CandidateState(state.xPosition, state.yPosition - 1, state.orientation)
                    Orientation.WEST -> CandidateState(state.xPosition - 1, state.yPosition, state.orientation)
                }

                if (candidateState.isInside(surface)) {
                    state = candidateState.asState()
                }

            }
            Command.RIGHT -> {
                val newOrientation = when (state.orientation) {
                    Orientation.NORTH -> Orientation.EAST
                    Orientation.EAST -> Orientation.SOUTH
                    Orientation.SOUTH -> Orientation.WEST
                    Orientation.WEST -> Orientation.NORTH
                }

                state = State(state.xPosition, state.yPosition, newOrientation)

            }
            Command.LEFT -> {
                val newOrientation = when (state.orientation) {
                    Orientation.NORTH -> Orientation.WEST
                    Orientation.WEST -> Orientation.SOUTH
                    Orientation.SOUTH -> Orientation.EAST
                    Orientation.EAST -> Orientation.NORTH
                }

                state = State(state.xPosition, state.yPosition, newOrientation)
            }
        }
    }

    fun getCurrentState() = state

    enum class Command {
        LEFT, RIGHT, FORWARD;
    }

    /**
     * State of the Mower.
     *
     * For the x position, 0 represents the extreme West of the surface, positive values are to the East.
     * For the y position, 0 represents the extreme South of the surface, positive values are to the North.
     */
    data class State(val xPosition: Int, val yPosition: Int, val orientation: Orientation) {
        init {
            if (xPosition < 0 || yPosition < 0) {
                throw IllegalArgumentException("Position cannot have negative values")
            }
        }

        fun isInside(surface: Surface): Boolean {
            return this.xPosition < surface.xSize && this.yPosition < surface.ySize
        }
    }

    data class CandidateState(val xPosition: Int, val yPosition: Int, val orientation: Orientation) {
        fun asState() = State(xPosition, yPosition, orientation)


        fun isInside(surface: Surface): Boolean {
            return this.xPosition >= 0
                && this.yPosition >= 0
                && this.xPosition < surface.xSize
                && this.yPosition < surface.ySize
        }
    }

    enum class Orientation {
        NORTH, EAST, SOUTH, WEST;
    }
}