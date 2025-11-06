
@echo off
echo ========================================
echo Iniciando HumanSolution API Backend
echo ========================================
echo.

REM Verificar JAVA_HOME
if "%JAVA_HOME%"=="" (
    echo [ADVERTENCIA] JAVA_HOME no esta configurado
    echo.
    echo OPCION 1 (RECOMENDADO): Ejecutar desde IntelliJ IDEA
    echo   1. Abre el proyecto en IntelliJ IDEA
    echo   2. Ejecuta: HumanSolutionApplication.java
    echo      ubicado en: src\main\java\co\edu\uco\HumanSolution\initializer
    echo.
    echo OPCION 2: Configurar JAVA_HOME manualmente
    echo   1. Encuentra la ruta de tu JDK (ej: C:\Program Files\Java\jdk-21)
    echo   2. Configura JAVA_HOME en Windows:
    echo      - Panel de Control ^> Sistema ^> Configuracion avanzada del sistema
    echo      - Variables de entorno ^> Nueva variable del sistema
    echo      - Nombre: JAVA_HOME
    echo      - Valor: C:\Program Files\Java\jdk-21
    echo   3. Reinicia este script
    echo.
    pause
    exit /b 1
)

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

