
# Home Banking

Un sistema hecho 100% en java que corre con h2, usa MySQL y el front con JSwing. 

Resolucion de trabajo final para la materia Laboratorio I de la Universidad de Palermo. 

// Aca va un gifsito de como funciona


## Para correrlo Local

Clonar el proyecto (Yo use intellij)

```bash
  git clone https://link-to-project
```

- Descargar h2 y agregarlo como libreria al proyecto.
- Creas las tablas en la db del h2, podes tambien crear una base de datos local asi no usas ~test: 

```bn
CREATE TABLE USUARIO (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    admin BOOLEAN DEFAULT FALSE
);
CREATE TABLE CUENTA (
    idCuenta INT AUTO_INCREMENT PRIMARY KEY,
    idUsuario INT,
    cbu VARCHAR(22) UNIQUE NOT NULL,
    alias VARCHAR(50) UNIQUE NOT NULL,
    balance DECIMAL(10, 2) DEFAULT 0.00,
    tipo VARCHAR(50) NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);
CREATE TABLE TRANSFER (
    idTransfer INT AUTO_INCREMENT PRIMARY KEY,
    aliasDesde VARCHAR(50) NOT NULL,
    aliasHasta VARCHAR(50) NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    monto DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (aliasDesde) REFERENCES CUENTA(alias),
    FOREIGN KEY (aliasHasta) REFERENCES CUENTA(alias)
);
CREATE TABLE TARJETA_DEBITO (
    idTarjeta INT AUTO_INCREMENT PRIMARY KEY,
    fechaVencimiento DATE NOT NULL,
    numeroTarjeta BIGINT AUTO_INCREMENT(5031755734530604,1) NOT NULL UNIQUE,
    cvv INT AUTO_INCREMENT(268)NOT NULL UNIQUE,
    idCuenta INT NOT NULL,
    idUsuario INT NOT NULL,
    FOREIGN KEY (idCuenta) REFERENCES CUENTA(idCuenta),
    FOREIGN KEY (idUsuario) REFERENCES USUARIO(idUsuario)
);
CREATE TABLE TARJETA_CREDITO (
    idTarjeta INT AUTO_INCREMENT PRIMARY KEY,
    disponible DECIMAL(10, 2) NOT NULL,
    aPagar DECIMAL(10, 2) NOT NULL,
    fechaCierre DATE NOT NULL,
    fechaVencimiento DATE NOT NULL,
    numeroTarjeta BIGINT AUTO_INCREMENT(4509953566233704,1) NOT NULL UNIQUE,
    cvv INT AUTO_INCREMENT(768,1)NOT NULL UNIQUE,
    idCuenta INT NOT NULL,
    idUsuario INT NOT NULL,
    FOREIGN KEY (idCuenta) REFERENCES CUENTA(idCuenta),
    FOREIGN KEY (idUsuario) REFERENCES USUARIO(idUsuario)
);

CREATE TABLE MOVIMIENTO (
    idMovimientoTarjeta INT PRIMARY KEY,
    numeroTarjeta BIGINT NOT NULL,
    fecha DATE NOT NULL,
    monto DECIMAL(10, 2) NOT NULL
);

```

Listo! Ya lo podes correr :) 


