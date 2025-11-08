@echo off
echo ========================================
echo   Iniciando CrudApp Spring Boot
echo ========================================
echo.

set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-21.0.9.10-hotspot"
set "PATH=%JAVA_HOME%\bin;%PATH%"

cd /d "%~dp0"

echo Java Version:
java -version
echo.

echo Iniciando aplicacao...
echo.

java -jar target\crudapp-0.0.1-SNAPSHOT.jar

pause
