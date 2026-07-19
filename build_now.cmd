@echo off
cd /d "D:\projects\sales-alert-app"

if exist "out\classes" rmdir /s /q "out\classes"
mkdir "out\classes"

set "CLASSPATH=D:\projects\sales-alert-app\lib\kafka-clients-3.8.0.jar;D:\projects\sales-alert-app\lib\kafka-streams-3.8.0.jar"

"C:\Program Files\Eclipse Adoptium\jdk-25.0.3.9-hotspot\bin\javac.exe" -d "out\classes" "src\main\java\com\example\SalesAlertApp.java"

if %errorlevel% equ 0 (
    echo === УСПЕХ: компиляция прошла без ошибок ===
) else (
    echo === ОШИБКА: компиляция не удалась ===
)
pause
