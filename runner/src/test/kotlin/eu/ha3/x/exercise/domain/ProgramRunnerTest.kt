package eu.ha3.x.exercise.domain

import eu.ha3.x.exercise.domain.Mower.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * (Default template)
 * Created on 2018-04-23
 *
 * @author Ha3
 */
public class ProgramRunnerTest {
    @Test
    public fun `it should execute the program`() {
        // Setup
        val sequences = listOf(
                Program.MowingInstruction(State(0, 0, Orientation.NORTH), listOf(Command.FORWARD, Command.RIGHT, Command.FORWARD, Command.FORWARD)),
                Program.MowingInstruction(State(0, 5, Orientation.WEST), listOf(Command.FORWARD, Command.LEFT, Command.FORWARD))
        )
        val program = Program(Surface(5, 10), sequences)

        val recordedStates = mutableListOf<Mower.State>()
        val onMowingExecuted: OnMowingExecutedListener = { recordedStates.add(it) }

        // Execute
        ProgramRunner(onMowingExecuted).execute(program)

        // Verify
        assertThat(recordedStates).isEqualTo(listOf(
                Mower.State(2, 1, Orientation.EAST),
                Mower.State(0, 4, Orientation.SOUTH)
        ))
    }
}