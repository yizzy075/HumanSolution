
@echo off
echo ========================================
echo Iniciando HumanSolution API
echo ========================================
echo.
<<<<<<< HEAD

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
=======
>>>>>>> parent of 8c8c848 (Merge branch 'master' of https://github.com/yizzy075/HumanSolution)

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

