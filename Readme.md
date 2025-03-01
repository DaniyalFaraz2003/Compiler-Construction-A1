# Programming Language Documentation

## Table of Contents
1. [Data Types](#data-types)
2. [Variables and Constants](#variables-and-constants)
3. [Program Structure](#program-structure)
4. [Operators](#operators)
5. [Input/Output](#inputoutput)
6. [Comments](#comments)
7. [Syntax Rules](#syntax-rules)
8. [Naming Conventions](#naming-conventions)
9. [Language Constraints](#language-constraints)
10. [Symbol Table Structure](#symbol-table-structure)

## Data Types

The language supports four primitive data types:

| Data Type  | Description | Valid Values/Format |
|------------|-------------|-------------------|
| Boolean    | Logical value | `true` or `false` |
| Integer    | Whole numbers | Regular: `0-9+` <br> Negative: `-1, -2, -345` etc. |
| Decimal    | Floating-point numbers | Format: `[-]digits.digits{1,5}` <br> Examples: `123.45`, `-0.12345` |
| Character  | Single character | Format: `'x'` where x can be any letter, digit |
| Void       | Used for functions | No value |

## Variables and Constants

### Variables
- Must start with lowercase letter
- Can contain only lowercase letters
- Format: `[a-z][a-z]*`

### Constants
- Must start with uppercase letter
- Can contain only uppercase letters
- Format: `[A-Z][A-Z]*`

## Program Structure

### Main Function (Entry Point)
```
Void main() {
    // Program code here
}
```

### Function Definition
```
<return_type> <function_name>() {
    // Function code here
}
```

### Variable Declaration
```
<datatype> <variable_name> = <value|expression|variable>;
```

### Assignment Statement
```
<variable_name> = <value|expression|variable>;
```

## Operators

### Arithmetic Operators
Listed in order of precedence (highest to lowest):

| Operator | Operation | Associativity |
|----------|-----------|---------------|
| ^        | Exponentiation | Right to Left |
| %        | Modulus | Left to Right |
| * /      | Multiplication, Division | Left to Right |
| + -      | Addition, Subtraction | Left to Right |
| =        | Assignment | Right to Left |

### Expression Rules
- Can combine multiple operators
- Follows operator precedence
- Format: `<operand> <operator> <operand>`
- Operands can be: variables, values, or expressions

## Input/Output

### Input
```
scan(<variable_name>);
```

### Output
```
log(<variable|value|expression>);
```

## Comments

### Single-line Comments
```
// This is a single line comment
```

### Multi-line Comments
```
/= 
This is a multi-line comment
Multiple lines can be written here
=/
```

## Syntax Rules

1. Every statement must end with a semicolon (;)
2. Code blocks are enclosed in curly braces { }
3. Function parameters are enclosed in parentheses ( )
4. Array indices are enclosed in square brackets [ ]

## Naming Conventions

1. Variable names: lowercase letters only
2. Function names: lowercase letters only
3. Constants: uppercase letters only
4. Built-in keywords: `log`, `scan`, `void`, `main`

## Language Constraints

1. Functions must be defined outside the main function
2. Global variables must be declared outside main
3. Local variables can be defined inside any function
4. Nested function definitions are not allowed
5. Operators can only be used with compatible data types
6. Main function serves as the program entry point

## Symbol Table Structure

The symbol table maintains the following information for each identifier:

| Column  | Description |
|---------|-------------|
| Name    | Identifier name |
| Type    | Data type or identifier type |
| Scope   | Variable/function scope |
| Line No | Declaration line number |

## Example Program
```
/= Simple program to demonstrate language features =/

Integer x = 10;

void main() {
    Integer y;
    scan(y);
    Integer z = x + y;
    log(z);
}
```
