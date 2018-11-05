@echo off
md jar
mvn package && mvn javadoc:javadoc && copy /Y .\target\*.jar .\jar\*.jar
