# ✅ Guía Rápida: Ejecutar Backend y Frontend

## 🎯 Opción Más Fácil (Recomendada)

### **Ejecuta este archivo:**
```
iniciar-todo.bat
```

Este script hace TODO automáticamente:
- ✅ Inicia el backend en una ventana
- ✅ Instala dependencias del frontend si es necesario
- ✅ Inicia el frontend en otra ventana
- ✅ Abre el navegador automáticamente

---

## 📋 Ejecución Manual (Alternativa)

### **1. Backend** (Terminal 1):
```bash
# En la raíz del proyecto
mvnw.cmd spring-boot:run
```

**O desde tu IDE:**
- Ejecuta `HumanSolutionApplication.java`

**Espera:** `🚀 Servidor REST API HumanSolution iniciado en http://localhost:8080`

---

### **2. Frontend** (Terminal 2):
```bash
# Ir a la carpeta del frontend
cd HumanSolution-Frontend

# Instalar dependencias (solo primera vez)
npm install

# Iniciar Angular
npm start
```

**Espera:** `** Angular Live Development Server is listening on localhost:4200 **`

---

## 🔍 Verificar que Funciona

1. **Backend:** Abre `http://localhost:8080/api/v1/usuarios` → Debe mostrar JSON
2. **Frontend:** Abre `http://localhost:4200` → Debe mostrar la aplicación
3. **Probar:** Usa el formulario de registro → Debe conectarse al backend

---

## ✅ Respuesta Directa

**NO necesitas separar el frontend del backend.**

El frontend ya está dentro de `HumanSolution-Frontend/` y está correctamente configurado.

**Solo ejecuta:**
- `iniciar-todo.bat` (lo más fácil)
- O ejecuta backend y frontend en terminales separadas

**¡Todo debería funcionar perfectamente!** 🚀

---

## 📝 Estructura Actual

```
HumanSolution/
├── src/                    ← Backend (Spring Boot)
├── HumanSolution-Frontend/ ← Frontend (Angular) ✅
├── pom.xml
├── iniciar-todo.bat        ← Script para iniciar todo
└── iniciar-servidor.bat    ← Script solo backend
```

**Todo está correcto, solo necesitas ejecutarlo.**

