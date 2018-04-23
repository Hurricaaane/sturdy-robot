package eu.ha3.x.exercise.domain

import eu.ha3.x.exercise.domain.Mower.Command.*
import eu.ha3.x.exercise.domain.Mower.Orientation
import eu.ha3.x.exercise.domain.Mower.State
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/**
 * (Default template)
 * Created on 2018-04-23
 *
 * @author Ha3
 */
public class AcceptanceProgramRunnerTest {
    @Test
    public fun `acceptance case`() {
        // Given
        val program = Program(Surface.withUpperRightCoordinate(5, 5), listOf(
                Program.MowingInstruction(State(1, 2, Orientation.NORTH), listOf(LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, FORWARD)),
                Program.MowingInstruction(State(3, 3, Orientation.EAST), listOf(FORWARD, FORWARD, RIGHT, FORWARD, FORWARD, RIGHT, FORWARD, RIGHT, RIGHT, FORWARD)
                )))

        // When
        val states = mutableListOf<Mower.State>()
        ProgramRunner({ states.add(it) }).execute(program)

        // Then
        Assertions.assertThat(states).isEqualTo(listOf(
                State(1, 3, Orientation.NORTH),
                State(5, 1, Orientation.EAST)
        ))
    }
}