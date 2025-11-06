# 🔌 Configuración de Bruno para HumanSolution API

## 📋 Información de Conexión

- **Base URL**: `http://localhost:8080`
- **Puerto**: `8080`
- **Protocolo**: `HTTP`

## 🚀 Endpoints Disponibles

### 1. **Usuarios** (`/api/v1/usuarios`)

#### Crear Usuario (POST)
```
POST http://localhost:8080/api/v1/usuarios
Content-Type: application/json

{
  "documento": "1234567890",
  "nombre": "Juan Pérez",
  "correo": "juan.perez@example.com",
  "contrasenia": "Password123!",
  "idRol": "550e8400-e29b-41d4-a716-446655440000"
}
```

#### Listar Usuarios (GET)
```
GET http://localhost:8080/api/v1/usuarios
```

#### Obtener Usuario por ID (GET)
```
GET http://localhost:8080/api/v1/usuarios/{id}
```

#### Obtener Usuario por Email (GET)
```
GET http://localhost:8080/api/v1/usuarios/email/{email}
```

#### Actualizar Usuario (PUT)
```
PUT http://localhost:8080/api/v1/usuarios/{id}
Content-Type: application/json

{
  "documento": "1234567890",
  "nombre": "Juan Pérez Actualizado",
  "correo": "juan.perez@example.com",
  "contrasenia": "NewPassword123!",
  "idRol": "550e8400-e29b-41d4-a716-446655440000"
}
```

#### Eliminar Usuario (DELETE)
```
DELETE http://localhost:8080/api/v1/usuarios/{id}
```

---

### 2. **Contratos** (`/api/v1/contratos`)

#### Crear Contrato (POST)
```
POST http://localhost:8080/api/v1/contratos
Content-Type: application/json

{
  "idUsuario": "550e8400-e29b-41d4-a716-446655440000",
  "fechaInicio": "2024-01-01",
  "fechaFin": "2024-12-31",
  "salario": 5000000,
  "idPuesto": "550e8400-e29b-41d4-a716-446655440001"
}
```

#### Listar Contratos (GET)
```
GET http://localhost:8080/api/v1/contratos
```

#### Obtener Contrato por ID (GET)
```
GET http://localhost:8080/api/v1/contratos/{id}
```

#### Obtener Contratos por Usuario (GET)
```
GET http://localhost:8080/api/v1/contratos/usuario/{idUsuario}
```

#### Actualizar Contrato (PUT)
```
PUT http://localhost:8080/api/v1/contratos/{id}
Content-Type: application/json

{
  "idUsuario": "550e8400-e29b-41d4-a716-446655440000",
  "fechaInicio": "2024-01-01",
  "fechaFin": "2024-12-31",
  "salario": 6000000,
  "idPuesto": "550e8400-e29b-41d4-a716-446655440001"
}
```

#### Eliminar Contrato (DELETE)
```
DELETE http://localhost:8080/api/v1/contratos/{id}
```

---

## 📝 Formato de Respuesta

Todas las respuestas siguen este formato:

```json
{
  "success": true,
  "message": "Mensaje descriptivo",
  "data": { ... }
}
```

### Ejemplo de Respuesta Exitosa:
```json
{
  "success": true,
  "message": "Usuario registrado exitosamente",
  "data": null
}
```

### Ejemplo de Respuesta con Datos:
```json
{
  "success": true,
  "message": "Usuarios consultados exitosamente",
  "data": [
    {
      "id": "550e8400-e29b-41d4-a716-446655440000",
      "documento": "1234567890",
      "nombre": "Juan Pérez",
      "correo": "juan.perez@example.com",
      "contrasenia": "***",
      "idRol": "550e8400-e29b-41d4-a716-446655440001"
    }
  ]
}
```

### Ejemplo de Respuesta de Error:
```json
{
  "success": false,
  "message": "El email ya está registrado en el sistema",
  "data": null
}
```

---

## ⚙️ Configuración en Bruno

1. **Crear Nueva Colección**:
   - Nombre: `HumanSolution API`
   - Base URL: `http://localhost:8080`

2. **Headers por Defecto**:
   ```
   Content-Type: application/json
   Accept: application/json
   ```

3. **Variables de Entorno** (opcional):
   - `baseUrl`: `http://localhost:8080`
   - `port`: `8080`

---

## ✅ Verificación de Conexión

Antes de hacer peticiones, verifica:

1. ✅ **Servidor iniciado**: La aplicación debe estar corriendo en `http://localhost:8080`
2. ✅ **Base de datos**: PostgreSQL debe estar corriendo en `localhost:5432`
3. ✅ **Base de datos existe**: La base de datos `sistema_usuarios` debe existir
4. ✅ **Credenciales**: Usuario `postgres` con contraseña `dino2020`

---

## 🔍 Troubleshooting

### Error: "Connection refused"
- Verifica que el servidor esté corriendo
- Verifica el puerto 8080

### Error: "Database connection failed"
- Verifica que PostgreSQL esté corriendo
- Verifica las credenciales en `application.properties`
- Verifica que la base de datos `sistema_usuarios` exista

### Error: "CORS policy"
- Los controllers ya tienen `@CrossOrigin(origins = "*")` configurado
- Si persiste, verifica que Bruno esté haciendo peticiones desde un origen permitido

---

## 📚 Notas Importantes

- Los IDs son UUIDs (formato: `550e8400-e29b-41d4-a716-446655440000`)
- Las fechas deben estar en formato ISO: `YYYY-MM-DD`
- Todos los endpoints soportan CORS
- Las respuestas siempre incluyen `success`, `message` y `data`

