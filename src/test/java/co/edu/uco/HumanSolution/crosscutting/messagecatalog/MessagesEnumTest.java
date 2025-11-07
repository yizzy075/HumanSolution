package co.edu.uco.HumanSolution.crosscutting.messagecatalog;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessagesEnumTest {

    @Test
    void messagesEnumTieneCodigoYMensaje() {
        MessagesEnum message = MessagesEnum.TECHNICAL_GENERAL_PROBLEM;
        
        assertNotNull(message.getCode());
        assertNotNull(message.getMessage());
        assertEquals("HS-00001", message.getCode());
        assertFalse(message.getMessage().isEmpty());
    }

    @Test
    void todosLosMensajesTienenCodigoValido() {
        for (MessagesEnum message : MessagesEnum.values()) {
            assertNotNull(message.getCode());
            assertNotNull(message.getMessage());
            assertTrue(message.getCode().startsWith("HS-"));
            assertFalse(message.getMessage().isEmpty());
        }
    }

    @Test
    void messagesEnumNoSonNull() {
        assertNotNull(MessagesEnum.TECHNICAL_GENERAL_PROBLEM);
        assertNotNull(MessagesEnum.USER_GENERAL_PROBLEM);
        assertNotNull(MessagesEnum.CONNECTION_PROBLEM);
    }
}
