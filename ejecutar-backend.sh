#!/bin/bash

# Script para ejecutar el backend de HumanSolution

echo "=========================================="
echo "  Iniciando Backend HumanSolution"
echo "=========================================="
echo ""

# Verificar si PostgreSQL está corriendo
echo "1. Verificando PostgreSQL..."
if ! pg_isready -h localhost -p 5432 > /dev/null 2>&1; then
    echo "❌ PostgreSQL no está corriendo"
    echo "   Inicia PostgreSQL con: sudo service postgresql start"
    echo ""
    read -p "¿Deseas continuar de todos modos? (s/n): " continuar
    if [ "$continuar" != "s" ]; then
        exit 1
    fi
else
    echo "✅ PostgreSQL está corriendo"
fi

echo ""
echo "2. Compilando y ejecutando backend..."
echo "   URL: http://localhost:8080"
echo "   Presiona Ctrl+C para detener"
echo ""

# Ejecutar el backend
if [ -f "./mvnw" ]; then
    chmod +x mvnw
    ./mvnw spring-boot:run
else
    mvn spring-boot:run
fi
