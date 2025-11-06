@echo off
echo ========================================
echo Iniciando HumanSolution API
echo ========================================
echo.

REM Verificar si Maven está instalado
where mvn >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Maven no está instalado o no está en el PATH
    echo.
    echo Por favor:
    echo 1. Instala Maven desde https://maven.apache.org/download.cgi
    echo 2. O ejecuta desde tu IDE (IntelliJ, Eclipse, VS Code)
    echo.
    pause
    exit /b 1
)

echo Compilando proyecto...
call mvn clean compile

if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Error al compilar el proyecto
    pause
    exit /b 1
)

echo.
echo Iniciando servidor en http://localhost:8080
echo Presiona Ctrl+C para detener el servidor
echo.

call mvn spring-boot:run

pause

