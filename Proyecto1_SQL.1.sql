-- 1. Creación de la base de datos 
CREATE DATABASE IF NOT EXISTS PlataformaDonaciones;
USE PlataformaDonaciones;

-- Tabla de Direcciones
create table Direcciones (
	ID_Direccion int auto_increment primary key,
	Calle VARCHAR(100),
    Numero VARCHAR(20),
    Colonia VARCHAR(100)
);


-- 2. Tabla de Donadores
CREATE TABLE Donadores (
    ID_Donador INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Tipo VARCHAR(50) NOT NULL,
    Telefono VARCHAR(20),
    Correo_Electronico VARCHAR(100),
    ID_Direccion int,
    foreign key (ID_Direccion) references Direcciones(ID_Direccion)
    
);

-- 3. Tabla de Organizaciones Beneficiarias 
CREATE TABLE Organizaciones (
    ID_Organizacion INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Organizacion VARCHAR(100) NOT NULL,
    Nombre_Responsable VARCHAR(50),
    Apellido_Paterno_Responsable VARCHAR(50),
    Apellido_Materno_Responsable VARCHAR(50),
    Telefono VARCHAR(20),
    Correo_Electronico VARCHAR(100),
    ID_Direccion int,
    foreign key (ID_Direccion) references Direcciones(ID_Direccion)
);

-- 4. Tabla de Alimentos 
CREATE TABLE Alimentos (
    ID_Alimento INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Alimento VARCHAR(100) NOT NULL,
    Categoria VARCHAR(50),
    Fecha_Caducidad DATE,
    Cantidad INT,
    ID_Donador INT,
    FOREIGN KEY (ID_Donador) REFERENCES Donadores(ID_Donador) ON DELETE CASCADE
);

-- 5. Tabla de Entregas 
CREATE TABLE Entregas (
    ID_Entrega INT AUTO_INCREMENT PRIMARY KEY,
    Fecha_Entrega DATETIME DEFAULT CURRENT_TIMESTAMP,
    Estado ENUM('pendiente', 'en tránsito', 'completada', 'cancelada') DEFAULT 'pendiente',
    ID_Organizacion INT,
    ID_Alimento INT,
    FOREIGN KEY (ID_Organizacion) REFERENCES Organizaciones(ID_Organizacion),
	FOREIGN KEY (ID_Alimento) REFERENCES Alimentos(ID_Alimento)
);