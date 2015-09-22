# GingerSampleApp
A sample app demonstrating how to use my Ginger framework.

This is just a sample app showing how to use Ginger with H2 Database. Arguably, you could swap that out for just about anything that can be hit with a JDBC connection.

It's important to note, all of the routing and parameter requiring is handled entirely with `Handler.java` and `Todo.java`

The H2Sample doesn't hook into anything from Ginger, it just executes queries when called from `Todo.java` (our Ginger model)
