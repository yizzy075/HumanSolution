# 🔗 Configuración Frontend ↔ Backend

## ✅ Estado Actual

### Backend (Spring Boot)
- ✅ **Puerto**: `8080`
- ✅ **Base URL**: `http://localhost:8080`
- ✅ **CORS**: Habilitado para todos los orígenes (`@CrossOrigin(origins = "*")`)
- ✅ **Endpoints REST**: Configurados y funcionando

### Frontend (Angular)
- ✅ **Puerto**: `4200` (por defecto)
- ✅ **Servicio HTTP**: Configurado con `provideHttpClient()`
- ✅ **Servicio Usuario**: Actualizado para usar `/api/v1/usuarios`

---

## 🔌 Endpoints Disponibles

### Usuarios (`/api/v1/usuarios`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/api/v1/usuarios` | Registrar nuevo usuario |
| `GET` | `/api/v1/usuarios` | Listar todos los usuarios |
| `GET` | `/api/v1/usuarios/{id}` | Obtener usuario por ID |
| `GET` | `/api/v1/usuarios/email/{email}` | Obtener usuario por email |
| `PUT` | `/api/v1/usuarios/{id}` | Actualizar usuario |
| `DELETE` | `/api/v1/usuarios/{id}` | Eliminar usuario |

### Contratos (`/api/v1/contratos`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/api/v1/contratos` | Crear nuevo contrato |
| `GET` | `/api/v1/contratos` | Listar todos los contratos |
| `GET` | `/api/v1/contratos/{id}` | Obtener contrato por ID |
| `GET` | `/api/v1/contratos/usuario/{idUsuario}` | Obtener contratos por usuario |
| `PUT` | `/api/v1/contratos/{id}` | Actualizar contrato |
| `DELETE` | `/api/v1/contratos/{id}` | Eliminar contrato |

---

## 📝 Formato de Datos

### UsuarioDTO (Backend)
```java
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "documento": "1234567890",
  "nombre": "Juan Pérez",
  "correo": "juan.perez@example.com",
  "contrasenia": "Password123!",
  "idRol": "550e8400-e29b-41d4-a716-446655440001"
}
```

### UsuarioDTO (Frontend - TypeScript)
```typescript
interface UsuarioDTO {
  id?: string;
  documento: string;
  nombre: string;
  correo: string;
  contrasenia: string;
  idRol: string;
}
```

### Respuesta Estándar
```typescript
interface ResponseDTO<T> {
  success: boolean;
  message: string;
  data: T | null;
}
```

---

## 🚀 Cómo Ejecutar

### 1. Iniciar Backend
```bash
# Desde el directorio raíz del proyecto
cd HumanSolution
mvn spring-boot:run
# O ejecutar desde el IDE: HumanSolutionApplication.java
```

**Verificar**: Deberías ver:
```
🚀 Servidor REST API HumanSolution iniciado en http://localhost:8080
```

### 2. Iniciar Frontend
```bash
# Desde el directorio del frontend
cd HumanSolution-Frontend
npm install  # Solo la primera vez
ng serve
```

**Verificar**: Deberías ver:
```
✔ Compiled successfully.
** Angular Live Development Server is listening on localhost:4200 **
```

### 3. Acceder a la Aplicación
Abre tu navegador en: `http://localhost:4200`

---

## 🔄 Ejemplo de Uso en el Componente

```typescript
import { Component } from '@angular/core';
import { UsuarioService, UsuarioDTO } from '../services/usuario.service';

@Component({
  selector: 'app-mi-componente',
  template: `...`
})
export class MiComponente {
  
  constructor(private usuarioService: UsuarioService) {}

  registrarUsuario() {
    const nuevoUsuario: UsuarioDTO = {
      documento: '1234567890',
      nombre: 'Juan Pérez',
      correo: 'juan@example.com',
      contrasenia: 'Password123!',
      idRol: '550e8400-e29b-41d4-a716-446655440000'
    };

    this.usuarioService.registrarUsuario(nuevoUsuario)
      .subscribe({
        next: (response) => {
          if (response.success) {
            console.log('Usuario registrado:', response.message);
          } else {
            console.error('Error:', response.message);
          }
        },
        error: (error) => {
          console.error('Error de conexión:', error);
        }
      });
  }

  listarUsuarios() {
    this.usuarioService.listarUsuarios()
      .subscribe({
        next: (response) => {
          if (response.success && response.data) {
            console.log('Usuarios:', response.data);
          }
        },
        error: (error) => {
          console.error('Error:', error);
        }
      });
  }
}
```

---

## ⚠️ Problemas Comunes y Soluciones

### 1. Error CORS
**Síntoma**: `Access to XMLHttpRequest has been blocked by CORS policy`

**Solución**: 
- Los controllers ya tienen `@CrossOrigin(origins = "*")` configurado
- Si persiste, verifica que el backend esté corriendo en `localhost:8080`

### 2. Error de Conexión
**Síntoma**: `Http failure response for http://localhost:8080/api/v1/usuarios`

**Solución**:
- Verifica que el backend esté corriendo
- Verifica la URL en el servicio: `http://localhost:8080/api/v1/usuarios`
- Verifica que no haya firewall bloqueando el puerto

### 3. Error 404
**Síntoma**: `404 Not Found`

**Solución**:
- Verifica que la ruta del endpoint sea correcta: `/api/v1/usuarios` (no `/api/usuarios`)
- Verifica que el método HTTP sea correcto (POST, GET, PUT, DELETE)

### 4. Error 500 (Internal Server Error)
**Síntoma**: `500 Internal Server Error`

**Solución**:
- Revisa los logs del backend
- Verifica que la base de datos esté corriendo
- Verifica que las credenciales de la BD sean correctas

---

## 🔍 Verificar Conexión

### Desde el Frontend (Angular)
```typescript
// En el componente o servicio
this.usuarioService.listarUsuarios()
  .subscribe({
    next: (response) => console.log('✅ Conexión exitosa:', response),
    error: (error) => console.error('❌ Error:', error)
  });
```

### Desde el Navegador (DevTools)
1. Abre las DevTools (F12)
2. Ve a la pestaña **Network**
3. Realiza una acción que haga una petición HTTP
4. Verifica que la petición aparezca y tenga status 200

### Desde PowerShell
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/v1/usuarios" -Method GET
```

---

## 📋 Checklist de Configuración

- [x] Backend configurado en puerto 8080
- [x] Frontend configurado para apuntar a `http://localhost:8080`
- [x] CORS habilitado en todos los controllers
- [x] Servicio de usuario actualizado con endpoints correctos
- [x] Formato de respuesta estándar (ResponseDTO)
- [x] Manejo de errores global implementado

---

## 🎯 Próximos Pasos

1. **Actualizar el componente de registro** para usar el nuevo formato del servicio
2. **Agregar más servicios** para otras entidades (Contrato, Rol, etc.)
3. **Implementar interceptores** para manejar errores globalmente
4. **Agregar autenticación** si es necesario

---

## 📚 Archivos Clave

- **Backend**: `src/main/java/co/edu/uco/HumanSolution/controller/UsuarioController.java`
- **Frontend Service**: `HumanSolution-Frontend/src/app/services/usuario.service.ts`
- **Frontend Component**: `HumanSolution-Frontend/src/app/components/registro-usuario/registro-usuario.component.ts`

