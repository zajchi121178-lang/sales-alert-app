@echo off
cd /d D:\projects\sales-alert-app

:: Удаляем старую папку классов и создаём новую
if exist out\classes rmdir /s /q out\classes
mkdir out\classes

:: Явно задаём classpath — cmd это понимает лучше всего
set "CLASSPATH=lib\kafka-clients-3.8.0.jar;lib\kafka-streams-3.8.0.jar"

:: Компилируем
"C:\Program Files\Eclipse Adoptium\jdk-25.0.3.9-hotspot\bin\javac.exe" -d out\classes src\main\java\com\example\*.java

if %errorlevel% equ 0 (
    echo.
    echo === УСПЕХ: всё скомпилировано без ошибок ===
) else (
    echo.
    echo === ОШИБКА при компиляции ===
)
