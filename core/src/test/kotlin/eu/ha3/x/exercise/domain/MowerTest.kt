package eu.ha3.x.exercise.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

/**
 * (Default template)
 * Created on 2018-04-12
 *
 * @author Ha3
 */
public class MowerTest {
    @Test
    public fun `it should not create a mower out of bounds`() {
        // Exercise and Verify
        assertThrows(IllegalArgumentException::class.java) {
            Mower(Surface(1, 1), Mower.State(1, 1, Mower.Orientation.NORTH))
        }
    }

    @Test
    public fun `it should create a mower within bounds`() {
        // Exercise
        val SUT = Mower(Surface(10, 5), Mower.State(9, 4, Mower.Orientation.NORTH))
        val state = SUT.getCurrentState()

        // Verify
        assertThat(state).isEqualTo(Mower.State(9, 4, Mower.Orientation.NORTH))
    }

    @Test
    public fun `it should not move the mower north`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(0, 9, Mower.Orientation.NORTH))

        // Exercise
        SUT.execute(Mower.Command.FORWARD)
        val finalState = SUT.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(0, 9, Mower.Orientation.NORTH))
    }

    @Test
    public fun `it should move the mower north`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(0, 5, Mower.Orientation.NORTH))

        // Exercise
        SUT.execute(Mower.Command.FORWARD)
        val finalState = SUT.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(0, 6, Mower.Orientation.NORTH))
    }
}