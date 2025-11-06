# 🚀 Guía de Ejecución - HumanSolution

## 📋 Requisitos Previos

1. **PostgreSQL** corriendo en `localhost:5432`
2. **Base de datos** `sistema_usuarios` creada
3. **Java 21** instalado
4. **Node.js y npm** instalados (para el frontend)

## 🔧 Configuración de Base de Datos

Ejecuta en PostgreSQL:

```sql
-- Crear base de datos si no existe
CREATE DATABASE sistema_usuarios;

-- Conectar a la base de datos
\c sistema_usuarios

-- Insertar roles básicos (después de que las tablas se creen automáticamente)
INSERT INTO rol (id, nombre) VALUES 
('550e8400-e29b-41d4-a716-446655440000', 'Postulante'),
('550e8400-e29b-41d4-a716-446655440001', 'Empleado'),
('550e8400-e29b-41d4-a716-446655440002', 'RRHH')
ON CONFLICT (id) DO NOTHING;
```

## 🖥️ Ejecutar Backend

### Opción 1: Desde IntelliJ IDEA (Recomendado)
1. Abre el proyecto en IntelliJ IDEA
2. Ejecuta `HumanSolutionApplication.java` ubicado en:
   ```
   src/main/java/co/edu/uco/HumanSolution/initializer/HumanSolutionApplication.java
   ```
3. Espera a ver el mensaje: `🚀 Servidor REST API HumanSolution iniciado en http://localhost:8080`

### Opción 2: Desde Terminal
```bash
mvnw.cmd clean compile
mvnw.cmd spring-boot:run
```

### Opción 3: Script Batch
```bash
iniciar-servidor.bat
```

## 🌐 Ejecutar Frontend

### Opción 1: Script Batch (Más Fácil)
```bash
ejecutar-frontend.bat
```

### Opción 2: Angular CLI Directamente
```bash
cd HumanSolution-Frontend
npm install  # Solo la primera vez
npm start
```

El frontend estará disponible en: `http://localhost:4200`

## ✅ Verificar que Funciona

1. **Backend**: Abre `http://localhost:8080/api/v1/roles` → Debe devolver JSON
2. **Frontend**: Abre `http://localhost:4200` → Debe mostrar el formulario de registro

## 📝 Endpoints Disponibles

- `GET    /api/v1/roles` - Listar todos los roles
- `GET    /api/v1/roles/{id}` - Obtener rol por ID
- `GET    /api/v1/usuarios` - Listar usuarios
- `POST   /api/v1/usuarios` - Crear usuario
- `GET    /api/v1/usuarios/{id}` - Obtener usuario por ID
- `GET    /api/v1/contratos` - Listar contratos
- `POST   /api/v1/contratos` - Crear contrato

## 🔍 Solución de Problemas

### Error: "Puerto 8080 ya está en uso"
```bash
netstat -ano | findstr :8080
taskkill /PID [NUMERO_PID] /F
```

### Error: "No se puede conectar a la base de datos"
- Verifica que PostgreSQL esté corriendo
- Verifica que la base de datos `sistema_usuarios` exista
- Verifica las credenciales en `application.properties`

### Frontend no conecta con Backend
- Verifica que el backend esté corriendo en `http://localhost:8080`
- Verifica que la URL en `usuario.service.ts` y `rol.service.ts` sea correcta

