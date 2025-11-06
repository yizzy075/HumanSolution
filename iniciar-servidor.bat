@echo off
echo ========================================
echo Iniciando HumanSolution API Backend
echo ========================================
echo.
echo NOTA: Si prefieres iniciar desde tu IDE,
echo       ejecuta la clase: HumanSolutionApplication
echo       ubicada en: co.edu.uco.HumanSolution.initializer
echo.
pause

REM Verificar si existe el wrapper de Maven
if not exist "mvnw.cmd" (
    echo [ERROR] No se encuentra mvnw.cmd
    echo Por favor ejecuta el backend desde tu IDE usando HumanSolutionApplication
    pause
    exit /b 1
)

echo Compilando proyecto...
call mvnw.cmd clean compile

if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Error al compilar el proyecto
    pause
    exit /b 1
)

echo.
echo Iniciando servidor en http://localhost:8080
echo Presiona Ctrl+C para detener el servidor
echo.

call mvnw.cmd spring-boot:run

pause

