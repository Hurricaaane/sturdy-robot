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
    public fun `it should not move the mower north because it is in the border`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(0, 9, Mower.Orientation.NORTH))

        // Exercise
        val result = SUT.execute(Mower.Command.FORWARD)
        val finalState = result.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(0, 9, Mower.Orientation.NORTH))
    }

    @Test
    public fun `it should move the mower north`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(0, 5, Mower.Orientation.NORTH))

        // Exercise
        val result = SUT.execute(Mower.Command.FORWARD)
        val finalState = result.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(0, 6, Mower.Orientation.NORTH))
    }

    @Test
    public fun `it should not move the mower south because it is in the border`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(0, 0, Mower.Orientation.SOUTH))

        // Exercise
        val result = SUT.execute(Mower.Command.FORWARD)
        val finalState = result.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(0, 0, Mower.Orientation.SOUTH))
    }

    @Test
    public fun `it should move the mower south`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(0, 5, Mower.Orientation.SOUTH))

        // Exercise
        val result = SUT.execute(Mower.Command.FORWARD)
        val finalState = result.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(0, 4, Mower.Orientation.SOUTH))
    }

    @Test
    public fun `it should not move the mower east because it is in the border`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(2, 0, Mower.Orientation.EAST))

        // Exercise
        val result = SUT.execute(Mower.Command.FORWARD)
        val finalState = result.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(2, 0, Mower.Orientation.EAST))
    }

    @Test
    public fun `it should move the mower east`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(1, 0, Mower.Orientation.EAST))

        // Exercise
        val result = SUT.execute(Mower.Command.FORWARD)
        val finalState = result.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(2, 0, Mower.Orientation.EAST))
    }

    @Test
    public fun `it should not move the mower west because it is in the border`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(0, 0, Mower.Orientation.WEST))

        // Exercise
        val result = SUT.execute(Mower.Command.FORWARD)
        val finalState = result.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(0, 0, Mower.Orientation.WEST))
    }

    @Test
    public fun `it should move the mower west`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(1, 0, Mower.Orientation.WEST))

        // Exercise
        val result = SUT.execute(Mower.Command.FORWARD)
        val finalState = result.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(0, 0, Mower.Orientation.WEST))
    }

    @Test
    public fun `it should rotate the mower to the right from north to east`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(0, 0, Mower.Orientation.NORTH))

        // Exercise
        val result = SUT.execute(Mower.Command.RIGHT)
        val finalState = result.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(0, 0, Mower.Orientation.EAST))
    }

    @Test
    public fun `it should rotate the mower to the right from east to south`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(0, 0, Mower.Orientation.EAST))

        // Exercise
        val result = SUT.execute(Mower.Command.RIGHT)
        val finalState = result.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(0, 0, Mower.Orientation.SOUTH))
    }

    @Test
    public fun `it should rotate the mower to the right from south to west`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(0, 0, Mower.Orientation.SOUTH))

        // Exercise
        val result = SUT.execute(Mower.Command.RIGHT)
        val finalState = result.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(0, 0, Mower.Orientation.WEST))
    }

    @Test
    public fun `it should rotate the mower to the right from west to north`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(0, 0, Mower.Orientation.WEST))

        // Exercise
        val result = SUT.execute(Mower.Command.RIGHT)
        val finalState = result.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(0, 0, Mower.Orientation.NORTH))
    }

    @Test
    public fun `it should rotate the mower to the left from north to west`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(0, 0, Mower.Orientation.NORTH))

        // Exercise
        val result = SUT.execute(Mower.Command.LEFT)
        val finalState = result.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(0, 0, Mower.Orientation.WEST))
    }

    @Test
    public fun `it should rotate the mower to the left from west to south`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(0, 0, Mower.Orientation.WEST))

        // Exercise
        val result = SUT.execute(Mower.Command.LEFT)
        val finalState = result.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(0, 0, Mower.Orientation.SOUTH))
    }

    @Test
    public fun `it should rotate the mower to the left from south to east`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(0, 0, Mower.Orientation.SOUTH))

        // Exercise
        val result = SUT.execute(Mower.Command.LEFT)
        val finalState = result.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(0, 0, Mower.Orientation.EAST))
    }
    @Test
    public fun `it should rotate the mower to the left from east to north`() {
        // Setup
        val SUT = Mower(Surface(3, 10), Mower.State(0, 0, Mower.Orientation.EAST))

        // Exercise
        val result = SUT.execute(Mower.Command.LEFT)
        val finalState = result.getCurrentState()

        // Verify
        assertThat(finalState).isEqualTo(Mower.State(0, 0, Mower.Orientation.NORTH))
    }
}