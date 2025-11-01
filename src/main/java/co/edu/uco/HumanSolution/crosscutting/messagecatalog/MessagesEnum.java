package co.edu.uco.HumanSolution.crosscutting.messagecatalog;

public enum MessagesEnum {

    // GENERALES
    TECHNICAL_GENERAL_PROBLEM("HS-00001", "Se ha presentado un problema inesperado. Por favor intente de nuevo."),
    USER_GENERAL_PROBLEM("HS-00002", "Se ha presentado un problema procesando su solicitud."),

    // TIPO DOCUMENTO
    TIPO_DOCUMENTO_CREADO("HS-01001", "Tipo de documento creado exitosamente"),
    TIPO_DOCUMENTO_ACTUALIZADO("HS-01002", "Tipo de documento actualizado exitosamente"),
    TIPO_DOCUMENTO_ELIMINADO("HS-01003", "Tipo de documento eliminado exitosamente"),
    TIPO_DOCUMENTO_CONSULTADO("HS-01004", "Tipos de documento consultados exitosamente"),
    TIPO_DOCUMENTO_NOMBRE_DUPLICADO("HS-01005", "Ya existe un tipo de documento con ese nombre"),
    TIPO_DOCUMENTO_NOMBRE_VACIO("HS-01006", "El nombre del tipo de documento es obligatorio"),
    TIPO_DOCUMENTO_DESCRIPCION_VACIA("HS-01007", "La descripción del tipo de documento es obligatoria"),
    TIPO_DOCUMENTO_NOMBRE_LARGO("HS-01008", "El nombre no puede exceder 50 caracteres"),
    TIPO_DOCUMENTO_DESCRIPCION_LARGA("HS-01009", "La descripción no puede exceder 200 caracteres"),
    TIPO_DOCUMENTO_NO_EXISTE("HS-01010", "El tipo de documento no existe"),

    // CONEXIÓN
    CONNECTION_PROBLEM("HS-09001", "Se ha presentado un problema con la conexión a la base de datos"),
    CONNECTION_OPEN("HS-09002", "Conexión abierta exitosamente"),
    CONNECTION_CLOSE("HS-09003", "Conexión cerrada exitosamente");

    private String code;
    private String message;

    private MessagesEnum(String code, String message) {
        setCode(code);
        setMessage(message);
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}