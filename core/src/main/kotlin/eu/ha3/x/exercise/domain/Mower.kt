package eu.ha3.x.exercise.domain

/**
 * Represents a Mower placed within a surface.
 * Instances of this class are immutable.
 *
 * Created on 2018-04-11
 *
 * @author Ha3
 */
data class Mower(private val surface: Surface, private val state: State) {
    init {
        if (!state.isInside(surface)) {
            throw IllegalArgumentException("Initial state is out of bounds: $state cannot be inside $surface")
        }
    }

    fun getCurrentState() = state

    /**
     * Applies a command to this mower, returning a new instance.
     *
     * The state may not change if the command would not result in a new position.
     */
    fun execute(command: Command): Mower {
        val newState = when (command) {
            Command.FORWARD -> moveForward()
            Command.RIGHT -> spinClockwise()
            Command.LEFT -> spinAnticlockwise()
        }

        return Mower(surface, newState)
    }

    fun executeAll(commands: List<Command>): Mower {
        return commands.fold(this, Mower::execute)
    }

    private fun moveForward(): State {
        val candidateState = when (state.orientation) {
            Orientation.NORTH -> State(state.xPosition, state.yPosition + 1, state.orientation)
            Orientation.EAST -> State(state.xPosition + 1, state.yPosition, state.orientation)
            Orientation.SOUTH -> State(state.xPosition, state.yPosition - 1, state.orientation)
            Orientation.WEST -> State(state.xPosition - 1, state.yPosition, state.orientation)
        }

        if (candidateState.isInside(surface)) {
            return candidateState

        } else {
            return state
        }
    }

    private fun spinClockwise() = State(state.xPosition, state.yPosition, state.orientation.right())

    private fun spinAnticlockwise() = State(state.xPosition, state.yPosition, state.orientation.left())

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
        fun isInside(surface: Surface): Boolean {
            return this.xPosition >= 0
                && this.yPosition >= 0
                && this.xPosition < surface.xSize
                && this.yPosition < surface.ySize
        }
    }

    enum class Orientation {
        NORTH, EAST, SOUTH, WEST;

        private lateinit var internal: InternalOrientation;

        fun left() = internal.left
        fun right() = internal.right

        private enum class InternalOrientation(val left: Orientation, val right: Orientation) {
            NORTH(Orientation.WEST, Orientation.EAST),
            EAST(Orientation.NORTH, Orientation.SOUTH),
            SOUTH(Orientation.EAST, Orientation.WEST),
            WEST(Orientation.SOUTH, Orientation.NORTH)
        }

        companion object {
            init {
                NORTH.internal = InternalOrientation.NORTH
                EAST.internal = InternalOrientation.EAST
                SOUTH.internal = InternalOrientation.SOUTH
                WEST.internal = InternalOrientation.WEST
            }
        }
    }
}