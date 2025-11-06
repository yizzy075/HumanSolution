import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { UsuarioService, UsuarioDTO } from '../../services/usuario.service';

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
  roles: string[] = [];
  mensaje: { texto: string, tipo: 'success' | 'error' } | null = null;
  cargando = false;
  mostrarContrasenia = false;

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService
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
   * Carga los roles disponibles
   * TODO: Implementar endpoint en backend para obtener roles desde /api/v1/roles
   */
  cargarRoles(): void {
    // Por ahora usar roles hardcodeados
    // TODO: Implementar cuando el endpoint de roles esté disponible
    // this.usuarioService.obtenerRoles().subscribe({
    //   next: (roles) => {
    //     this.roles = roles;
    //   },
    //   error: (error) => {
    //     console.error('Error al cargar roles:', error);
    //     this.roles = ['Postulante', 'Empleado', 'RRHH'];
    //   }
    // });
    
    // Roles temporales - necesitarás obtener los UUIDs reales de la base de datos
    this.roles = [
      '550e8400-e29b-41d4-a716-446655440000', // Postulante
      '550e8400-e29b-41d4-a716-446655440001', // Empleado
      '550e8400-e29b-41d4-a716-446655440002'  // RRHH
    ];
    console.log('Roles cargados (temporales):', this.roles);
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

        const mensajeError = error.error?.message || error.message || 'Error al registrar usuario';
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
