export interface Rol {
  id: string;
  nombre?: string;
}

export interface Usuario {
  id?: string;
  nombre: string;
  apellido: string;
  numeroDocumento: string;
  correo: string;
  contrasena: string;
  rol: Rol;
}
