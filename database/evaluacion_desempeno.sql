-- =====================================================
-- Script de creación de tabla: evaluacion_desempeno
-- Proyecto: HumanSolution
-- Módulo: Evaluación de Desempeño
-- =====================================================

CREATE TABLE evaluacion_desempeno (
    id UUID PRIMARY KEY,
    id_usuario UUID NOT NULL,
    id_evaluador UUID NOT NULL,
    id_contrato UUID NOT NULL,
    fecha DATE NOT NULL,
    calificacion INTEGER NOT NULL,
    observacion VARCHAR(500) NOT NULL,
    criterios VARCHAR(1000) NOT NULL,

    -- Constraints
    CONSTRAINT fk_evaluacion_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario(id) ON DELETE RESTRICT,

    CONSTRAINT fk_evaluacion_evaluador FOREIGN KEY (id_evaluador)
        REFERENCES usuario(id) ON DELETE RESTRICT,

    CONSTRAINT fk_evaluacion_contrato FOREIGN KEY (id_contrato)
        REFERENCES contrato(id) ON DELETE RESTRICT,

    CONSTRAINT unique_usuario_fecha UNIQUE(id_usuario, fecha),

    CONSTRAINT chk_calificacion_rango
        CHECK (calificacion >= 1 AND calificacion <= 10),

    CONSTRAINT chk_fecha_no_futura
        CHECK (fecha <= CURRENT_DATE),

    CONSTRAINT chk_observacion_longitud
        CHECK (LENGTH(observacion) >= 5 AND LENGTH(observacion) <= 500),

    CONSTRAINT chk_criterios_longitud
        CHECK (LENGTH(criterios) >= 10 AND LENGTH(criterios) <= 1000)
);

-- Índices para mejorar rendimiento
CREATE INDEX idx_evaluacion_usuario ON evaluacion_desempeno(id_usuario);
CREATE INDEX idx_evaluacion_evaluador ON evaluacion_desempeno(id_evaluador);
CREATE INDEX idx_evaluacion_contrato ON evaluacion_desempeno(id_contrato);
CREATE INDEX idx_evaluacion_fecha ON evaluacion_desempeno(fecha);

-- Comentarios de documentación
COMMENT ON TABLE evaluacion_desempeno IS 'Almacena las evaluaciones de desempeño de los usuarios';
COMMENT ON COLUMN evaluacion_desempeno.id IS 'Identificador único de la evaluación (UUID)';
COMMENT ON COLUMN evaluacion_desempeno.id_usuario IS 'Referencia al usuario evaluado';
COMMENT ON COLUMN evaluacion_desempeno.id_evaluador IS 'Referencia al usuario que realiza la evaluación';
COMMENT ON COLUMN evaluacion_desempeno.id_contrato IS 'Referencia al contrato vigente del usuario evaluado';
COMMENT ON COLUMN evaluacion_desempeno.fecha IS 'Fecha de la evaluación (no puede ser futura)';
COMMENT ON COLUMN evaluacion_desempeno.calificacion IS 'Calificación del desempeño (1-10)';
COMMENT ON COLUMN evaluacion_desempeno.observacion IS 'Observaciones de la evaluación (5-500 caracteres)';
COMMENT ON COLUMN evaluacion_desempeno.criterios IS 'Criterios utilizados en la evaluación (10-1000 caracteres)';
