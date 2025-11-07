#!/bin/bash

# Script para ejecutar backend y frontend simultáneamente

echo "=========================================="
echo "  Iniciando HumanSolution COMPLETO"
echo "=========================================="
echo ""
echo "Este script ejecutará:"
echo "  - Backend en http://localhost:8080"
echo "  - Frontend en http://localhost:4200"
echo ""

# Verificar PostgreSQL
echo "Verificando PostgreSQL..."
if ! pg_isready -h localhost -p 5432 > /dev/null 2>&1; then
    echo "❌ ADVERTENCIA: PostgreSQL no está corriendo"
    echo "   Inicia PostgreSQL con: sudo service postgresql start"
    echo ""
fi

# Función para manejar Ctrl+C
cleanup() {
    echo ""
    echo "Deteniendo servicios..."
    kill $BACKEND_PID 2>/dev/null
    kill $FRONTEND_PID 2>/dev/null
    exit 0
}

trap cleanup SIGINT SIGTERM

# Iniciar backend en segundo plano
echo ""
echo "1. Iniciando backend..."
if [ -f "./mvnw" ]; then
    chmod +x mvnw
    ./mvnw spring-boot:run > logs/backend.log 2>&1 &
else
    mvn spring-boot:run > logs/backend.log 2>&1 &
fi
BACKEND_PID=$!

# Esperar a que el backend inicie
echo "   Esperando a que el backend inicie..."
sleep 15

# Iniciar frontend
echo ""
echo "2. Iniciando frontend..."
cd HumanSolution-Frontend

# Verificar si node_modules existe
if [ ! -d "node_modules" ]; then
    echo "   Instalando dependencias de npm..."
    npm install
fi

npm start &
FRONTEND_PID=$!

cd ..

echo ""
echo "=========================================="
echo "  ✅ Sistema iniciado correctamente"
echo "=========================================="
echo ""
echo "  Backend:  http://localhost:8080"
echo "  Frontend: http://localhost:4200"
echo ""
echo "  Logs del backend: logs/backend.log"
echo ""
echo "  Presiona Ctrl+C para detener ambos servicios"
echo ""

# Esperar indefinidamente
wait
