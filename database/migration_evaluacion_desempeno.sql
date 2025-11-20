-- =====================================================
-- Script de migración: evaluacion_desempeno
-- Proyecto: HumanSolution
-- Descripción: Agrega columnas id_evaluador, id_contrato y criterios
-- =====================================================

-- Agregar columnas nuevas
ALTER TABLE evaluacion_desempeno
ADD COLUMN IF NOT EXISTS id_evaluador UUID,
ADD COLUMN IF NOT EXISTS id_contrato UUID,
ADD COLUMN IF NOT EXISTS criterios VARCHAR(1000);

-- Actualizar columnas existentes para que tengan valores por defecto temporales
-- (esto es para datos existentes, si los hay)
UPDATE evaluacion_desempeno
SET
    id_evaluador = id_usuario,  -- Usar el mismo usuario como evaluador temporalmente
    id_contrato = (SELECT id FROM contrato WHERE id_usuario = evaluacion_desempeno.id_usuario LIMIT 1),  -- Tomar el primer contrato del usuario
    criterios = 'Criterios no especificados - Migración automática'
WHERE id_evaluador IS NULL OR id_contrato IS NULL OR criterios IS NULL;

-- Hacer las columnas NOT NULL después de llenarlas
ALTER TABLE evaluacion_desempeno
ALTER COLUMN id_evaluador SET NOT NULL,
ALTER COLUMN id_contrato SET NOT NULL,
ALTER COLUMN criterios SET NOT NULL;

-- Agregar foreign keys
ALTER TABLE evaluacion_desempeno
ADD CONSTRAINT IF NOT EXISTS fk_evaluacion_evaluador
    FOREIGN KEY (id_evaluador) REFERENCES usuario(id) ON DELETE RESTRICT;

ALTER TABLE evaluacion_desempeno
ADD CONSTRAINT IF NOT EXISTS fk_evaluacion_contrato
    FOREIGN KEY (id_contrato) REFERENCES contrato(id) ON DELETE RESTRICT;

-- Agregar constraint de longitud para criterios
ALTER TABLE evaluacion_desempeno
ADD CONSTRAINT IF NOT EXISTS chk_criterios_longitud
    CHECK (LENGTH(criterios) >= 10 AND LENGTH(criterios) <= 1000);

-- Crear índices para mejorar rendimiento
CREATE INDEX IF NOT EXISTS idx_evaluacion_evaluador ON evaluacion_desempeno(id_evaluador);
CREATE INDEX IF NOT EXISTS idx_evaluacion_contrato ON evaluacion_desempeno(id_contrato);

-- Agregar comentarios
COMMENT ON COLUMN evaluacion_desempeno.id_evaluador IS 'Referencia al usuario que realiza la evaluación';
COMMENT ON COLUMN evaluacion_desempeno.id_contrato IS 'Referencia al contrato vigente del usuario evaluado';
COMMENT ON COLUMN evaluacion_desempeno.criterios IS 'Criterios utilizados en la evaluación (10-1000 caracteres)';
