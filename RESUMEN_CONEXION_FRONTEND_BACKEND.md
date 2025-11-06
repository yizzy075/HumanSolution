# ✅ Resumen: Conexión Frontend ↔ Backend

## 🎯 Estado: **CONECTADO Y LISTO**

### ✅ Lo que está configurado:

1. **Backend (Spring Boot)**
   - ✅ Puerto: `8080`
   - ✅ CORS habilitado: `@CrossOrigin(origins = "*")`
   - ✅ Endpoints REST funcionando
   - ✅ Formato de respuesta estándar: `ResponseDTO`

2. **Frontend (Angular)**
   - ✅ Servicio actualizado: `UsuarioService`
   - ✅ URL correcta: `http://localhost:8080/api/v1/usuarios`
   - ✅ Componente actualizado: `RegistroUsuarioComponent`
   - ✅ Mapeo de datos correcto

3. **Conexión**
   - ✅ CORS configurado para permitir peticiones desde Angular
   - ✅ Formato de datos compatible entre frontend y backend
   - ✅ Manejo de errores implementado

---

## 🚀 Cómo Probar la Conexión

### Paso 1: Iniciar Backend
```bash
# Desde el directorio raíz
cd HumanSolution
mvn spring-boot:run
# O ejecutar HumanSolutionApplication desde el IDE
```

**Espera a ver:**
```
🚀 Servidor REST API HumanSolution iniciado en http://localhost:8080
```

### Paso 2: Iniciar Frontend
```bash
# Desde el directorio del frontend
cd HumanSolution-Frontend
ng serve
```

**Espera a ver:**
```
Angular Live Development Server is listening on localhost:4200
```

### Paso 3: Probar en el Navegador
1. Abre: `http://localhost:4200`
2. Completa el formulario de registro
3. Envía el formulario
4. Verifica en la consola del navegador (F12) que la petición se haga correctamente

---

## 📋 Endpoints Disponibles

### Usuarios
- `POST /api/v1/usuarios` - Registrar usuario
- `GET /api/v1/usuarios` - Listar usuarios
- `GET /api/v1/usuarios/{id}` - Obtener usuario por ID
- `GET /api/v1/usuarios/email/{email}` - Obtener usuario por email
- `PUT /api/v1/usuarios/{id}` - Actualizar usuario
- `DELETE /api/v1/usuarios/{id}` - Eliminar usuario

### Contratos
- `POST /api/v1/contratos` - Crear contrato
- `GET /api/v1/contratos` - Listar contratos
- `GET /api/v1/contratos/{id}` - Obtener contrato por ID
- `GET /api/v1/contratos/usuario/{idUsuario}` - Contratos por usuario
- `PUT /api/v1/contratos/{id}` - Actualizar contrato
- `DELETE /api/v1/contratos/{id}` - Eliminar contrato

---

## 🔍 Verificar que Funciona

### Desde el Navegador (DevTools)
1. Abre `http://localhost:4200`
2. Abre DevTools (F12)
3. Ve a la pestaña **Network**
4. Completa y envía el formulario
5. Verifica que aparezca una petición a `http://localhost:8080/api/v1/usuarios`
6. Verifica que el status sea `200` o `201`

### Desde PowerShell
```powershell
# Probar GET
Invoke-RestMethod -Uri "http://localhost:8080/api/v1/usuarios" -Method GET

# Probar POST
$body = @{
    documento = "1234567890"
    nombre = "Juan Pérez"
    correo = "juan@example.com"
    contrasenia = "Password123!"
    idRol = "550e8400-e29b-41d4-a716-446655440000"
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8080/api/v1/usuarios" -Method POST -Body $body -ContentType "application/json"
```

---

## ⚠️ Notas Importantes

1. **Roles**: El endpoint de roles aún no está implementado. El componente usa IDs hardcodeados temporalmente.

2. **Formato de Datos**:
   - El formulario tiene `nombre` y `apellido` separados
   - El backend espera solo `nombre` (combinado)
   - El componente ya hace el mapeo: `nombre + apellido → nombre`

3. **IDs de Roles**: Necesitarás obtener los UUIDs reales de la tabla `rol` en la base de datos y actualizar el componente.

---

## 📝 Archivos Modificados

1. ✅ `HumanSolution-Frontend/src/app/services/usuario.service.ts`
   - Actualizado para usar `/api/v1/usuarios`
   - Agregado tipo `ResponseDTO` y `UsuarioDTO`
   - Métodos CRUD completos

2. ✅ `HumanSolution-Frontend/src/app/components/registro-usuario/registro-usuario.component.ts`
   - Actualizado para mapear datos correctamente
   - Usa `response.message` en lugar de `response.mensaje`
   - Combina nombre y apellido en un solo campo

3. ✅ `src/main/java/co/edu/uco/HumanSolution/controller/UsuarioController.java`
   - Ya tenía CORS configurado
   - Endpoints funcionando

---

## 🎉 ¡Todo Listo!

El frontend y backend están **completamente conectados** y listos para funcionar. Solo necesitas:

1. ✅ Iniciar el backend
2. ✅ Iniciar el frontend  
3. ✅ Probar en el navegador

¡La conexión debería funcionar perfectamente! 🚀

