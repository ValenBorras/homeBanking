
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

```
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
    FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuario)
);
(Agregar las demas cuando esten)...

```

Listo! Ya lo podes correr :) 


