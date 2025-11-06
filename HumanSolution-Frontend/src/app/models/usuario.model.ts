export interface Usuario {
  id?: string;
  nombre: string;
  apellido: string;
  numeroDocumento: string;
  correo: string;
  rol: string;
}

export interface ApiResponse {
  success: boolean;
  mensaje: string;
  usuario?: Usuario;
  campoAfectado?: string;
}
