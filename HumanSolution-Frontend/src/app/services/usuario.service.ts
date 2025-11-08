import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private apiUrl = 'http://localhost:8080/api/v1/usuarios';

  constructor(private http: HttpClient) {}

  // ✅ Registrar usuario
  registrarUsuario(usuario: any): Observable<any> {

    // Mapeo correcto al DTO del backend
    const usuarioDTO = {
      id: usuario.id || '',
      documento: usuario.numeroDocumento || usuario.documento || '',
      nombre: `${usuario.nombre || ''} ${usuario.apellido || ''}`.trim(),
      correo: usuario.correo || usuario.correoElectronico || '',
      contrasena: usuario.contrasenia || usuario.contrasena || '',
      rol: {
        id: usuario.rol?.id || usuario.idRol || ''

      }
    };

    console.log('📤 Enviando usuarioDTO al backend:', usuarioDTO);

    return this.http.post<any>(`${this.apiUrl}`, usuarioDTO);

    // Si el usuario ya viene en formato DTO (con contrasena y rol como objeto), enviarlo directamente

  }

  // ✅ Obtener lista de usuarios
  obtenerUsuarios(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}`);
  }

  // ✅ Obtener usuario por ID
  obtenerUsuarioPorId(id: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  // ✅ Actualizar usuario
  actualizarUsuario(id: string, usuario: any): Observable<any> {
    const usuarioDTO = {
      id: id,
      documento: usuario.numeroDocumento || usuario.documento || '',
      nombre: `${usuario.nombre || ''} ${usuario.apellido || ''}`.trim(),
      correo: usuario.correo || usuario.correoElectronico || '',
      contrasena: usuario.contrasenia || usuario.contrasena || '',
      rol: {
        id: typeof usuario.rol === 'string' ? usuario.rol : usuario.rol?.id || usuario.idRol || ''
      }
    };

    console.log('📤 Actualizando usuarioDTO:', usuarioDTO);

    return this.http.put<any>(`${this.apiUrl}/${id}`, usuarioDTO);
  }

  // ✅ Eliminar usuario
  eliminarUsuario(id: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }

  // ✅ Obtener lista de roles
  obtenerRoles(): Observable<any> {
    return this.http.get<any>('http://localhost:8080/api/v1/roles');
  }

  // ✅ Obtener rol por ID
  obtenerRolPorId(id: string): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/api/v1/roles/${id}`);
  }
}
