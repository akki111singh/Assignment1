# Assignment 1
## Akhil Singh
## 20171210

 # Schema:
 - char    - Boolean arg.
 - char*   - String arg.
 - char#   - Integer arg.
 - char##  - double arg.
 - char[*] - one element of a string array.

* Example schema: (f,s*,n#,a##,p[*])
Corresponding command line: "-f -s Bob -n 1 -a 3.2 -p e1 -p e2 -p e3

# Clean Code Practices

The source code makes use of the following clean coding prtices:-

* Added Clean Code Practices in Source Code

### Folder structure/Modularity
+ All marshalers kept in a separate directory.
+ Classes addressing different aspects have been seperated by packages(The classes defining marshalers and arguments are kept in a seperate packages), to enable modularity and seperation of concerns.

### For loop
+ For loops were refactored to iterator format

### If-else to switch
+ Multiple nested if-else statements were changed to switch case statements.
+ Switch statement executes faster than if_else because the switch statement checks the      condition first and then jumps to the suitable case statement.

### Added object binding for variable
+ local variables were bound to objects using this keyword.

### Validator Function
+ Added a separate validator function in each marshaler file.

### Handled null checking using Optional class
+ Refactored code to avoid using too many null checks using optional class. By using Optional, we can specify alternate values to return or alternate code to run.
+ Optional.ofNullable() method of the Optional class, returns a Non-empty Optional if the given object has a value, otherwise it returns an empty Optional.

###Added Test Cases
+ Added Test cases for the added Optional class.
