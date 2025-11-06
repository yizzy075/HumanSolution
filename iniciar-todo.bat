@echo off
echo ========================================
echo HUMAN SOLUTION - Iniciar Todo
echo ========================================
echo.
echo Este script iniciara:
echo  1. Backend (Spring Boot) en puerto 8080
echo  2. Frontend (Angular) en puerto 4200
echo.
echo IMPORTANTE: Asegurate de que PostgreSQL este corriendo
echo              y que la base de datos 'sistema_usuarios' exista
echo.
pause

REM Verificar si Maven wrapper existe
if exist "mvnw.cmd" (
    echo [PASO 1] Iniciando Backend...
    start "Backend HumanSolution" cmd /k "mvnw.cmd spring-boot:run"
    echo Backend iniciando en ventana separada...
    echo.
) else (
    echo [ERROR] No se encuentra mvnw.cmd
    echo Por favor ejecuta el backend manualmente desde tu IDE
    echo.
)

REM Esperar antes de iniciar frontend
timeout /t 10 /nobreak >nul

REM Iniciar Frontend
echo [PASO 2] Iniciando Frontend...
cd HumanSolution-Frontend

if not exist "node_modules" (
    echo Instalando dependencias del frontend...
    call npm install
    if errorlevel 1 (
        echo [ERROR] Error al instalar dependencias
        pause
        exit /b 1
    )
)

echo.
echo ========================================
echo Servidores iniciando...
echo ========================================
echo Backend:  http://localhost:8080
echo Frontend: http://localhost:4200
echo.
echo Presiona Ctrl+C para detener el frontend
echo (El backend quedara corriendo en otra ventana)
echo.
echo Abriendo navegador automaticamente...
timeout /t 5 /nobreak >nul
start http://localhost:4200
echo.

call npm start

cd ..
pause
