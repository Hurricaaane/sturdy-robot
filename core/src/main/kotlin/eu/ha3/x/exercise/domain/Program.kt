package eu.ha3.x.exercise.domain

data class Program(val surface: Surface, val mowingInstructions: List<MowingInstruction>) {
    data class MowingInstruction(val initialState: Mower.State, val commands: List<Mower.Command>)
}