###LOG4J

Log4j is one of many logging libraries. It's java specific.
There is also Log4j2, it's a new version of Log4j.

Some slf4j is also a logging library.

What is logging?
Logging is a process of writing the flow of an application - what it's doing, 
which blocks the app is executing, what kind of information it's processing,
when is the app processing this info, is there any problem app is encountering? etc.,.

###Log levels
//Logs in all programming languages follow the same conventions for level.

    //DEBUG -> we put very detailed information in DEBUG level
    //INFO -> we put general info in INFO level
    //WARN -> We put possible issues in warn level
    //ERROR -> when errors occur we put in ERROR level
    //FATAL -> when something went horribly wrong we put in fatal level
####Debug
These are basically information types. There is some information I want to log,
which can help me, as a dev or qa, to debug issues faster with detailed,
sometimes sensitive, info.

####INFO
There are some info, like general high level log of app activity.
