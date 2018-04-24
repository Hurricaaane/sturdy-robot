## sturdy-robot-example

- **core** defines the domain model objects and their rules
- **consumer** turns files or standard input into a raw structure
- **parser** converts a raw structure into a program
- **runner** executes a program and emits events
- **listener** processes events to produce an output
- **executable-unix** provides an executable jar combining all of the above
  - accepting piped input, or file input as first parameter
  - prints to standard output

## executing

- With Unix pipes

`cat ./input.txt | java -jar exercise.jar`

- With file argument

`java -jar exercise.jar ./input.txt`
