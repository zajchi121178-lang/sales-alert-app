cd /d D:\projects\sales-alert-app
rmdir /s /q out\classes
mkdir out\classes

set CLASSPATH=lib\kafka-clients-3.8.0.jar;lib\kafka-streams-3.8.0.jar

"C:\Program Files\Eclipse Adoptium\jdk-25.0.3.9-hotspot\bin\javac.exe" -d out\classes src\main\java\com\example\*.java
