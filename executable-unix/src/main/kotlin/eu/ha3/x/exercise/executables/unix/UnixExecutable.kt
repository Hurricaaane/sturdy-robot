package eu.ha3.x.exercise.executables.unix

import eu.ha3.x.exercise.consumers.Consumer
import eu.ha3.x.exercise.consumers.file.FileConsumer
import eu.ha3.x.exercise.consumers.stdin.StdinConsumer
import eu.ha3.x.exercise.listener.stdout.StdoutListener
import eu.ha3.x.exercise.parser.text.TextProgramParser
import eu.ha3.x.exercise.runner.ProgramRunner
import java.nio.file.Paths


/**
 * (Default template)
 * Created on 2018-04-11
 *
 * @author Ha3
 */
object UnixExecutable {
    @JvmStatic
    fun main(args: Array<String>) {
        val consumer = if (args.isEmpty()) {
            StdinConsumer()

        } else {
            FileConsumer(Paths.get(args.get(0)))
        }
        val parser = TextProgramParser()
        val listener = StdoutListener()
        val programRunner = ProgramRunner(listener)

        execute(consumer, parser, programRunner)
    }

    private fun execute(consumer: Consumer, parser: TextProgramParser, programRunner: ProgramRunner) {
        val linesToParse = consumer.readLines()
        val program = parser.deserialize(linesToParse)
        programRunner.execute(program)
    }
}