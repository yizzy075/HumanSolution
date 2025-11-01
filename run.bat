@echo off
echo Iniciando HumanSolution...
set JAVA_HOME=%USERPROFILE%\.jdks\openjdk-21
set PATH=%JAVA_HOME%\bin;%PATH%

call .\mvnw.cmd clean package -DskipTests
if errorlevel 1 (
    echo Error al compilar el proyecto
    pause
    exit /b 1
)

call .\mvnw.cmd javafx:run
if errorlevel 1 (
    echo Error al ejecutar la aplicacion
    pause
    exit /b 1
)

pause
