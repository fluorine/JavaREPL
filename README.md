JavaREPL
========

A simple useful utility class that can supply a simple REPL (Read–eval–print loop) to any command-line application. It is actually provided as a *jar.

How to use
==========
You have to add the *.jar into the project's path, and then import it:

    import javarepl.REPL;

You can use any JavaREPL's utility methods through the `REPL` identifier. For example:

    int value = REPL.getInt("Your age")    // It displays "Your age: _" and asks
                                           // the user for an integer.

You can see other methods in documetation, under `/doc/` directory.
