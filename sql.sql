CREATE TABLE IF NOT EXISTS creditos (
    id BIGSERIAL PRIMARY KEY,       -- Equivalente a GenerationType.IDENTITY en JPA
    fechaDesembolso VARCHAR(8) NOT NULL,  -- @NotNull
    departamento VARCHAR(50) NOT NULL,     -- @NotNull
    provincia VARCHAR(50) NOT NULL,        -- @NotNull
    distrito VARCHAR(50) NOT NULL,         -- @NotNull
    ifi VARCHAR(50) NOT NULL,              -- @NotNull
    tipoIfi VARCHAR(50) NOT NULL,          -- @NotNull
    montoCredito DOUBLE PRECISION NOT NULL,        -- @NotNull
    montoCuotaInicial DOUBLE PRECISION NOT NULL,   -- @NotNull
    plazos INT NOT NULL,                            -- @NotNull
    tasa DOUBLE PRECISION NOT NULL,                -- @NotNull
    montoValorVivienda DOUBLE PRECISION NOT NULL,  -- @NotNull
    fechaCorte VARCHAR(8) NOT NULL       -- @NotNull
);


