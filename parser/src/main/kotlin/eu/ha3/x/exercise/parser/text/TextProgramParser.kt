package eu.ha3.x.exercise.parser.text

import eu.ha3.x.exercise.domain.Mower
import eu.ha3.x.exercise.domain.Program
import eu.ha3.x.exercise.domain.Surface

/**
 * (Default template)
 * Created on 2018-04-12
 *
 * @author Ha3
 */
class TextProgramParser {
    fun deserialize(linesOfText: List<String>): Program {
        if (linesOfText.isEmpty()) {
            throw TextProgramParseException("Expected at least one line")
        }

        if (linesOfText.size % 2 == 0) {
            throw TextProgramParseException("Expected an odd number of lines")
        }

        val surface = parseSurface(linesOfText.get(0))
        val sequences = parseSequences(linesOfText.subList(1, linesOfText.size))

        return Program(surface, sequences)
    }

    private fun parseSequences(onlySequences: List<String>): List<Program.MowingInstruction> {
        val mutableSequences = mutableListOf<Program.MowingInstruction>()

        val iterator = onlySequences.iterator()
        while (iterator.hasNext()) {
            val state = iterator.next()
            val commands = iterator.next()

            mutableSequences.add(parseSequences(state, commands))
        }

        return mutableSequences.toList()
    }

    private fun parseSequences(stateStr: String, commandsStr: String): Program.MowingInstruction {
        val split = stateStr.split(" ")
        if (split.size != 3) {
            throw TextProgramParseException("Expected three items for mower initialization")
        }

        val (x, y, orientation) = split

        val initialState = try {
            Mower.State(Integer.parseInt(x), Integer.parseInt(y), parseOrientation(orientation))

        } catch (e: NumberFormatException) {
            throw TextProgramParseException(e)
        }
        val commands = parseCommands(commandsStr)

        return Program.MowingInstruction(initialState, commands)
    }

    private fun parseOrientation(orientationStr: String) = when (orientationStr) {
        "N" -> Mower.Orientation.NORTH
        "E" -> Mower.Orientation.EAST
        "S" -> Mower.Orientation.SOUTH
        "W" -> Mower.Orientation.WEST
        else -> throw TextProgramParseException("Unexpected orientation value: $orientationStr")
    }

    private fun parseCommands(commandsStr: String): List<Mower.Command> {
        return commandsStr.toCharArray().map(this::parseCommand)
    }

    private fun parseCommand(commandChar: Char) = when (commandChar) {
        'G' -> Mower.Command.LEFT
        'D' -> Mower.Command.RIGHT
        'A' -> Mower.Command.FORWARD
        else -> throw TextProgramParseException("Unexpected command value: $commandChar")
    }

    private fun parseSurface(surfaceStr: String): Surface {
        val split = surfaceStr.split(" ")
        if (split.size != 2) {
            throw TextProgramParseException("Expected two items for the first line")
        }

        val (x, y) = try {
            split.map(Integer::parseInt)

        } catch (e: NumberFormatException) {
            throw TextProgramParseException(e)
        }

        return Surface.withUpperRightCoordinate(x, y)
    }

    class TextProgramParseException : RuntimeException {
        constructor(s: String) : super(s)
        constructor(e: NumberFormatException) : super(e)
    }
}