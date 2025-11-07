import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private apiUrl = 'http://localhost:8080/api/v1';

  constructor(private http: HttpClient) {}

  // Registrar usuario
  registrarUsuario(usuario: any): Observable<any> {
    // Si el usuario ya viene en formato DTO (con contrasena y rol como objeto), enviarlo directamente
    if (usuario.contrasena !== undefined || usuario.rol?.id !== undefined) {
      console.log('Enviando usuarioDTO al backend:', usuario);
      return this.http.post<any>(`${this.apiUrl}/usuarios`, usuario);
    }
    
    // Si viene del formulario, mapear los campos al formato del backend
    const usuarioDTO = {
      id: usuario.id || '',
      documento: usuario.numeroDocumento || usuario.documento || '',
      nombre: usuario.nombre || '',
      correo: usuario.correo || '',
      contrasena: usuario.contrasenia || usuario.contrasena || '',
      rol: usuario.rol ? (typeof usuario.rol === 'string' ? { id: usuario.rol } : usuario.rol) : null
    };
    
    console.log('Enviando usuarioDTO al backend:', usuarioDTO);
    return this.http.post<any>(`${this.apiUrl}/usuarios`, usuarioDTO);
  }

  // Obtener lista de usuarios
  obtenerUsuarios(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/usuarios`);
  }

  // Obtener usuario por ID
  obtenerUsuarioPorId(id: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/usuarios/${id}`);
  }

  // Obtener usuario por email
  obtenerUsuarioPorEmail(email: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/usuarios/email/${email}`);
  }

  // Actualizar usuario
  actualizarUsuario(id: string, usuario: any): Observable<any> {
    const usuarioDTO = {
      id: id,
      documento: usuario.numeroDocumento || usuario.documento || '',
      nombre: usuario.nombre || '',
      correo: usuario.correo || '',
      contrasenia: usuario.contrasenia || '',
      idRol: usuario.rol || usuario.idRol || ''
    };
    return this.http.put<any>(`${this.apiUrl}/usuarios/${id}`, usuarioDTO);
  }

  // Eliminar usuario
  eliminarUsuario(id: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/usuarios/${id}`);
  }

  // Obtener lista de roles
  obtenerRoles(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/roles`);
  }

  // Obtener rol por ID
  obtenerRolPorId(id: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/roles/${id}`);
  }
}
