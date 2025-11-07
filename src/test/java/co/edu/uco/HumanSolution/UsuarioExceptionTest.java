package co.edu.uco.HumanSolution;

import co.edu.uco.HumanSolution.crosscutting.messagecatalog.MessagesEnum;
import co.edu.uco.HumanSolution.crosscutting.exception.BusinessException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class UsuarioExceptionTest {

    @Test
    public void businessExceptionTieneMensajeCompleto() {
        BusinessException ex = new BusinessException(
                "Error técnico creando usuario",
                "Error al crear usuario"
        );
        assertNotNull(ex.getMessage());
        assertNotNull(ex.getTechnicalMessage());
        assertNotNull(ex.getUserMessage());
        assertTrue(ex.getMessage().contains("Error"));
    }

    @Test
    void businessExceptionConMensajeEnum() {
        BusinessException ex = new BusinessException(
                MessagesEnum.TECHNICAL_GENERAL_PROBLEM.getMessage(),
                MessagesEnum.USER_GENERAL_PROBLEM.getMessage()
        );
        assertNotNull(ex.getUserMessage());
        assertEquals(MessagesEnum.USER_GENERAL_PROBLEM.getMessage(), ex.getUserMessage());
    }

    @Test
    void businessExceptionConExcepcionRaiz() {
        Exception rootException = new RuntimeException("Error de prueba");
        BusinessException ex = new BusinessException(
                "Error técnico",
                "Error al procesar",
                rootException
        );
        assertNotNull(ex.getRootException());
        assertEquals(rootException, ex.getRootException());
    }
}

