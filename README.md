# Mini Dart Interpreter

This repository contains an interpreter for a subset of a well-known programming language. The project's main focus is on creating an interpreter for `miniDart`, a toy programming language inspired by Dart (https://dart.dev).

## Overview

`miniDart` is a simplified programming language that supports dynamic types such as booleans, integers, strings, lists, and maps. Additionally, it places emphasis on null safety.

## Project Structure

The project is organized into the following directories:

- **examples**: This directory contains example `miniDart` code snippets showcasing different language features and constructs.

- **interpreter**: Here lies the core of the interpreter implementation. The interpreter processes `miniDart` code and executes it step by step.

- **lexical**: This directory contains the lexer (lexical analyzer) for `miniDart`. The lexer transforms the source code into a stream of tokens.

- **syntactic**: The syntactic directory houses the parser, which processes the token stream produced by the lexer and generates an abstract syntax tree (AST).

## Usage

1. Navigate to the desired section of the project based on your interest (e.g., interpreter, examples, etc.).

2. If necessary, compile the code according to the instructions provided in the respective directory.

3. Run the interpreter or the example code using the command-line interface or your preferred development environment.

## Contributing

Contributions to this project are welcome. If you encounter any bugs, have improvements, or want to add features, feel free to submit a pull request.

## Acknowledgments

This project was developed as part of the "Laboratory of Programming Languages" assignment, with the aim of gaining a deeper understanding of interpreters and language features.

## Disclaimer

`miniDart` is not intended for production use but rather serves as an educational tool for learning about programming language concepts.

Feel free to explore, experiment, and build upon the `miniDart` interpreter to further your understanding of language design and implementation.

