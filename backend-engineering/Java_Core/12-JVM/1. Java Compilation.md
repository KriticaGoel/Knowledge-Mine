
Compiler convert the high level language to target language which can be machine level(c,c++) or intermediate level (bytecode In case of JAVA) or other progamming language ().

Machine level instructions are platform dependent. So, if we compile the high level language to machine level language then it will be platform dependent. 

Platform Dependent  - High level language compiled on Windows will not run on linux or mac. It will run only on window. to make it compatible on linux we need to recompile the high level code on linux machine. So, we need to compile the code on the target platform.

Interpreter - Interpreter is a program which takes the source code and executes it line by line. It does not convert the high level language to machine level language. It directly executes the code. It is platform dependent. 
The source code needs to be interpreted on the target platform.

        Compilation
            |
            |------------------>fast Execution   ------------------------|
            |                                                            |
            |------------------>No platform independence                 |
                                                                         |
        Interpretation                                                   |-------- Java      
            |                                                            |
            |------------------>slow Execution                           |                
            |    (Execute source code directly line by line              |
            |     load program and interpretator on memory)              |
            |                                                            |
            |------------------>Platform independence  ------------------|


                Source Code (Hello.java)  - (Platform Independent) - High level language (JAVA)
                    |
                Compiler (javac Hello.java)  -  verify Syntax and semantic errors, optimised the code and convert to bytecode
                    |
                Bytecode (Hello.class)  - (Platform Independent) - Intermediate level language (Bytecode)
                    |
                JVM (Interpreter) (Java Hello) - (Platform Dependent) - Convert bytecode to machine level language and execute the program
                    |
                Machine Level Language - Platform dependent language (Machine level instructions)


Bytecode interpretation is done by JVM. 

JVM is platform dependent. 

So, we need to have different JVM for different platforms. For example, we need to have JVM for Windows, Linux, Mac etc.

Bytecode interpretation is much faster than source code interpretation. 

Because, in source code interpretation, the source code is interpreted line by line. 

But in bytecode interpretation, the bytecode is already compact, compiled and optimized. 
So, it can be executed directly by JVM.

JVM is a virtual machine which is responsible for executing the bytecode. and make java platform independent as .class created on window can be executed on linux or mac.

JVM also provide very fast execution speed as it is optimized for the target platform.

It is platform dependent. 

It converts the bytecode to machine level language and executes the program.

