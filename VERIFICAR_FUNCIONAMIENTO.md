# ✅ PASOS PARA VERIFICAR QUE TODO FUNCIONE

## 1. VERIFICAR BASE DE DATOS

Ejecuta en PostgreSQL:

```sql
-- Conectar a la base de datos
\c sistema_usuarios

-- Verificar que la tabla rol existe
SELECT * FROM rol;

-- Si está vacía, insertar roles básicos
INSERT INTO rol (id, nombre) VALUES
('550e8400-e29b-41d4-a716-446655440000', 'Postulante'),
('550e8400-e29b-41d4-a716-446655440001', 'Empleado'),
('550e8400-e29b-41d4-a716-446655440002', 'RRHH')
ON CONFLICT (id) DO NOTHING;
```

## 2. EJECUTAR BACKEND DESDE INTELLIJ IDEA

1. Abre IntelliJ IDEA
2. Abre el proyecto HumanSolution
3. Ve a: `src/main/java/co/edu/uco/HumanSolution/initializer/HumanSolutionApplication.java`
4. Haz clic derecho → **Run 'HumanSolutionApplication.main()'**

## 3. VERIFICAR LOGS AL INICIAR

Deberías ver:

```
=== INICIANDO HumanSolutionApplication ===
=== RolController CONSTRUIDO ===
=== RolController INICIALIZADO - Endpoint /api/v1/roles DISPONIBLE ===
🚀 Servidor REST API HumanSolution iniciado
📋 Endpoints registrados (X):
   ✅ GET [/api/v1/roles]
   ✅ GET [/api/v1/roles/{id}]
   ✅ POST [/api/v1/usuarios]
   ✅ GET [/api/v1/usuarios]
```

Si ves `⚠️ NO SE ENCONTRARON ENDPOINTS REGISTRADOS`, hay un problema.

## 4. PROBAR ENDPOINT EN NAVEGADOR

Abre: `http://localhost:8080/api/v1/roles`

Deberías recibir:
```json
{
  "success": true,
  "message": "Roles consultados exitosamente",
  "data": [
    {
      "id": "550e8400-e29b-41d4-a716-446655440000",
      "nombre": "Postulante"
    },
    {
      "id": "550e8400-e29b-41d4-a716-446655440001",
      "nombre": "Empleado"
    },
    {
      "id": "550e8400-e29b-41d4-a716-446655440002",
      "nombre": "RRHH"
    }
  ]
}
```

## 5. SI SIGUE SALIENDO ERROR 404

1. **Detén completamente el servidor**
2. **En IntelliJ: Build → Clean Project**
3. **Build → Rebuild Project**
4. **Ejecuta de nuevo HumanSolutionApplication**

## 6. COMPARTIR LOGS COMPLETOS

Si aún no funciona, comparte:
- Todos los mensajes que aparecen al iniciar el servidor
- El mensaje completo de error si aparece alguno
- Especialmente el mensaje de "📋 Endpoints registrados"

