import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { UsuarioService, UsuarioDTO } from '../../services/usuario.service';
import { RolService, RolDTO } from '../../services/rol.service';

/**
 * Componente para el registro de usuarios
 * Implementa las validaciones Usuario-PO-01 a Usuario-PO-07
 */
@Component({
  selector: 'app-registro-usuario',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './registro-usuario.component.html',
  styleUrls: ['./registro-usuario.component.css']
})
export class RegistroUsuarioComponent implements OnInit {
  formularioRegistro: FormGroup;
  roles: RolDTO[] = [];
  mensaje: { texto: string, tipo: 'success' | 'error' } | null = null;
  cargando = false;
  mostrarContrasenia = false;
  cargandoRoles = false;

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private rolService: RolService
  ) {
    // Inicializar formulario con validaciones
    this.formularioRegistro = this.fb.group({
      // Usuario-PO-01: Nombre obligatorio, solo letras
      nombre: ['', [
        Validators.required,
        Validators.pattern(/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/)
      ]],

      // Usuario-PO-01: Apellido obligatorio, solo letras
      apellido: ['', [
        Validators.required,
        Validators.pattern(/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/)
      ]],

      // Usuario-PO-02: Número de documento obligatorio, 6-20 dígitos
      numeroDocumento: ['', [
        Validators.required,
        Validators.pattern(/^\d{6,20}$/)
      ]],

      // Usuario-PO-01 y PO-05: Correo obligatorio y formato válido
      correo: ['', [
        Validators.required,
        Validators.email
      ]],

      // Usuario-PO-06: Contraseña con todos los requisitos
      contrasenia: ['', [
        Validators.required,
        Validators.minLength(6),
        Validators.pattern(/^(?=.*[A-Z])(?=.*\d).+$/)  // Al menos 1 mayúscula y 1 número
      ]],

      // Usuario-PO-07: Rol obligatorio
      rol: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.cargarRoles();
  }

  /**
   * Carga los roles disponibles desde el backend
   */
  cargarRoles(): void {
    this.cargandoRoles = true;
    this.rolService.listarRoles().subscribe({
      next: (response) => {
        console.log('Respuesta de roles:', response);
        if (response.success && response.data && response.data.length > 0) {
          this.roles = response.data;
          console.log('Roles cargados desde backend:', this.roles);
          // Limpiar mensaje de error si había uno
          if (this.mensaje?.tipo === 'error' && this.mensaje.texto.includes('roles')) {
            this.mensaje = null;
          }
        } else {
          console.warn('No se pudieron cargar los roles desde el backend, usando roles de respaldo');
          this.usarRolesDeRespaldo();
        }
        this.cargandoRoles = false;
      },
      error: (error) => {
        console.warn('No se pudo conectar con el backend para cargar roles:', error);
        // Usar roles de respaldo automáticamente sin mostrar error alarmante
        this.usarRolesDeRespaldo();
        this.cargandoRoles = false;
      }
    });
  }

  /**
   * Usa roles de respaldo cuando el backend no está disponible
   */
  private usarRolesDeRespaldo(): void {
    this.roles = [
      { id: '550e8400-e29b-41d4-a716-446655440000', nombre: 'Postulante' },
      { id: '550e8400-e29b-41d4-a716-446655440001', nombre: 'Empleado' },
      { id: '550e8400-e29b-41d4-a716-446655440002', nombre: 'RRHH' }
    ];
    console.log('Usando roles de respaldo:', this.roles);
  }

  /**
   * Registra un nuevo usuario
   * Valida el formulario antes de enviar
   */
  registrarUsuario(): void {
    console.log('Intentando registrar usuario...');
    console.log('Formulario válido:', this.formularioRegistro.valid);
    console.log('Valores:', this.formularioRegistro.value);

    // Validar formulario
    if (this.formularioRegistro.invalid) {
      this.formularioRegistro.markAllAsTouched();
      console.log('Formulario inválido - mostrando errores');
      return;
    }

    this.cargando = true;
    this.mensaje = null;

    // Preparar datos para el backend
    const usuarioDTO: UsuarioDTO = {
      documento: this.formularioRegistro.value.numeroDocumento,
      nombre: `${this.formularioRegistro.value.nombre} ${this.formularioRegistro.value.apellido}`.trim(),
      correo: this.formularioRegistro.value.correo,
      contrasenia: this.formularioRegistro.value.contrasenia,
      idRol: this.formularioRegistro.value.rol
    };

    // Enviar al backend
    this.usuarioService.registrarUsuario(usuarioDTO).subscribe({
      next: (response) => {
        console.log('Respuesta del servidor:', response);

        if (response.success) {
          this.mensaje = {
            texto: '✅ ' + response.message,
            tipo: 'success'
          };
          this.formularioRegistro.reset();

          // Ocultar mensaje después de 5 segundos
          setTimeout(() => this.mensaje = null, 5000);
        } else {
          this.mensaje = {
            texto: '❌ ' + response.message,
            tipo: 'error'
          };
        }

        this.cargando = false;
      },
      error: (error) => {
        console.error('Error completo:', error);
        console.error('Error status:', error.status);
        console.error('Error message:', error.message);
        console.error('Error error:', error.error);

        let mensajeError = 'Error al registrar usuario';
        
        // Error de conexión (backend no disponible)
        if (error.status === 0 || error.status === undefined) {
          mensajeError = 'No se pudo conectar con el servidor. Verifique que el backend esté corriendo en http://localhost:8080';
        } 
        // Error HTTP 400 (Bad Request) - errores de validación
        else if (error.status === 400) {
          if (error.error?.message) {
            mensajeError = error.error.message;
          } else {
            mensajeError = 'Error en los datos enviados. Por favor verifique los campos del formulario.';
          }
        }
        // Error HTTP 500 (Internal Server Error) - errores del servidor
        else if (error.status === 500) {
          if (error.error?.message) {
            mensajeError = error.error.message;
          } else {
            mensajeError = 'Error interno del servidor. Por favor intente más tarde.';
          }
        }
        // Otros errores HTTP
        else if (error.error?.message) {
          mensajeError = error.error.message;
        } else if (error.message) {
          mensajeError = error.message;
        }

        this.mensaje = {
          texto: '❌ ' + mensajeError,
          tipo: 'error'
        };

        this.cargando = false;
      }
    });
  }

  /**
   * Limpia el formulario y los mensajes
   */
  limpiarFormulario(): void {
    console.log('Limpiando formulario');
    this.formularioRegistro.reset();
    this.mensaje = null;
  }

  /**
   * Alterna la visibilidad de la contraseña
   */
  toggleMostrarContrasenia(): void {
    this.mostrarContrasenia = !this.mostrarContrasenia;
  }

  /**
   * Obtiene el mensaje de error apropiado para un campo
   * @param campo Nombre del campo a validar
   * @returns Mensaje de error descriptivo
   */
  obtenerMensajeError(campo: string): string {
    const control = this.formularioRegistro.get(campo);

    if (control?.hasError('required')) {
      const articulo = campo === 'contrasenia' ? 'La' : 'El';
      return `${articulo} ${campo} es obligatori${campo === 'contrasenia' ? 'a' : 'o'}`;
    }

    if (control?.hasError('pattern')) {
      if (campo === 'nombre' || campo === 'apellido') {
        return 'Solo se permiten letras y espacios';
      }
      if (campo === 'numeroDocumento') {
        return 'Debe tener entre 6 y 20 dígitos';
      }
      if (campo === 'contrasenia') {
        return 'Debe contener al menos una mayúscula y un número';
      }
    }

    if (control?.hasError('email')) {
      return 'El correo no tiene un formato válido';
    }

    if (control?.hasError('minlength')) {
      const minLength = control.errors?.['minlength'].requiredLength;
      return `Debe tener al menos ${minLength} caracteres`;
    }

    return '';
  }

  /**
   * Verifica si un campo es inválido y debe mostrar error
   * @param campo Nombre del campo
   * @returns true si el campo es inválido y fue tocado
   */
  esInvalido(campo: string): boolean {
    const control = this.formularioRegistro.get(campo);
    return !!(control?.invalid && (control?.dirty || control?.touched));
  }

  /**
   * Verifica si un campo es válido y debe mostrar marca verde
   * @param campo Nombre del campo
   * @returns true si el campo es válido y fue modificado
   */
  esValido(campo: string): boolean {
    const control = this.formularioRegistro.get(campo);
    return !!(control?.valid && control?.dirty);
  }

  /**
   * Verifica si la contraseña cumple con el requisito de mínimo 6 caracteres
   * @returns true si cumple
   */
  cumpleMinCaracteres(): boolean {
    const contrasenia = this.formularioRegistro.get('contrasenia')?.value || '';
    return contrasenia.length >= 6;
  }

  /**
   * Verifica si la contraseña contiene al menos una letra mayúscula
   * @returns true si cumple
   */
  cumpleMayuscula(): boolean {
    const contrasenia = this.formularioRegistro.get('contrasenia')?.value || '';
    return /[A-Z]/.test(contrasenia);
  }

  /**
   * Verifica si la contraseña contiene al menos un número
   * @returns true si cumple
   */
  cumpleNumero(): boolean {
    const contrasenia = this.formularioRegistro.get('contrasenia')?.value || '';
    return /\d/.test(contrasenia);
  }
}
