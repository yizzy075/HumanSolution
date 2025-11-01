package co.edu.uco.HumanSolution;

import co.edu.uco.HumanSolution.constants.MessagesEnum;
import co.edu.uco.HumanSolution.exception.UsuarioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioExceptionTest {

    @Test
    void usuarioExceptionTieneMensajeCompletoYCodigo() {
        UsuarioException ex = new UsuarioException(MessagesEnum.USUARIO_PO_01);
        assertTrue(ex.getMessage().contains(MessagesEnum.USUARIO_PO_01.getCodigo()));
        assertEquals(MessagesEnum.USUARIO_PO_01, ex.getMessageEnum());
    }

    @Test
    void documentoInvalidoUsaConstanteCorrecta() {
        UsuarioException ex = new UsuarioException.DocumentoInvalidoException("detalle");
        assertTrue(ex.getMessage().contains(MessagesEnum.USUARIO_PO_02.getCodigo()));
        assertTrue(ex.getMessage().contains("detalle"));
    }
}

