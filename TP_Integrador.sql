/* CREACION DE BASE DE DATOS*/
SET sql_mode='PIPES_AS_CONCAT';
DROP DATABASE IF EXISTS `TP_Integrador`;
CREATE DATABASE `TP_Integrador`;
USE `TP_Integrador`;


/* TABLA DE TIPO ESTADO */
CREATE TABLE TiposEstado (
	tipoEstado		INT			AUTO_INCREMENT,
    Descripcion		VARCHAR(40)	NOT NULL,
    CONSTRAINT PK_TiposEstado PRIMARY KEY (tipoEstado)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO TiposEstado (Descripcion) VALUES ('DE BAJA'), ('DE ALTA');



/* TIPOS CUENTA */
CREATE TABLE TiposCuenta (
	tipoCuenta		INT			AUTO_INCREMENT,
    Descripcion		VARCHAR(40)	NOT NULL,
    
    CONSTRAINT PK_TiposCuenta PRIMARY KEY (tipoCuenta)
    
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO TiposCuenta (Descripcion) VALUES ('CORRIENTE'), ('CAJA DE AHORRO');



/* CLIENTES */
CREATE TABLE Clientes (
	idCliente		INT			AUTO_INCREMENT,
    Nombre			VARCHAR(30) NOT NULL,
    Apellido		VARCHAR(30) NOT NULL,
    DNI				VARCHAR(30)	NOT NULL,
    Direccion		VARCHAR(30)	NOT NULL,
    Email			VARCHAR(50)	NOT NULL,
    Telefono		VARCHAR(30)	NOT NULL,
    FechaNac		DATE		NOT NULL,
    Cuenta1			VARCHAR(30)	DEFAULT NULL,
    Cuenta2			VARCHAR(30)	DEFAULT NULL,
    Cuenta3			VARCHAR(30)	DEFAULT NULL,
    Cuenta4			VARCHAR(30)	DEFAULT NULL,
    tipoEstado		INT			NOT NULL,
    FechaRegistro	DATETIME	NOT NULL,
    
    CONSTRAINT PK_Clientes PRIMARY KEY (idCliente),
    CONSTRAINT FK_ClientesTiposEstado FOREIGN KEY (tipoEstado) REFERENCES TiposEstado (tipoEstado)
    
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



/* CUENTAS */
CREATE TABLE Cuentas (
	CBU				VARCHAR(30)		NOT NULL,
    Saldo			DECIMAL(15,7) 	NOT NULL DEFAULT 0,
    idCliente		INT				NOT NULL,
    tipoCuenta		INT				NOT NULL,
    tipoEstado		INT				NOT NULL,
    FechaRegistro	DATETIME		NOT NULL,
    
    CONSTRAINT PK_Cuentas PRIMARY KEY (CBU),
    CONSTRAINT FK_CuentasClientes FOREIGN KEY (idCliente) REFERENCES Clientes (idCliente),
    CONSTRAINT FK_CuentasTiposCuenta FOREIGN KEY (tipoCuenta) REFERENCES TiposCuenta (tipoCuenta),
    CONSTRAINT FK_CuentasTiposEstado FOREIGN KEY (tipoEstado) REFERENCES TiposEstado (tipoEstado)
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



/* USUARIOS */
CREATE TABLE Usuarios (
	login			VARCHAR(24)	NOT NULL,
    password		VARCHAR(24) NOT NULL,
    idCliente		INT			NULL,
    
    CONSTRAINT PK_Usuarios PRIMARY KEY (login),
    CONSTRAINT FK_UsuariosClientes FOREIGN KEY (idCliente) REFERENCES Clientes (idCliente)
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8;	



/* SERVICIOS */
CREATE TABLE Servicios (
	id_Servicio					INT				AUTO_INCREMENT,
    RazonSocial_Servicio		VARCHAR(100)	NOT NULL,
    Descripcion_Servicio		VARCHAR(200)	NULL,
    tipoEstado_Servicio			INT				NOT NULL,
    FechaRegistro_Servicio		DATETIME		NOT NULL,
    
    CONSTRAINT PK_Servicios PRIMARY KEY (id_Servicio),
    CONSTRAINT FK_ServiciosTiposEstado FOREIGN KEY (tipoEstado_Servicio) REFERENCES TiposEstado (tipoEstado)
    
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



/* TIPOS MOVIMIENTO */
CREATE TABLE TiposMovimiento (
	tipoMovimiento	INT			AUTO_INCREMENT,
    Descripcion		VARCHAR(40)	NOT NULL,
    
    CONSTRAINT PK_TiposMovimiento PRIMARY KEY (tipoMovimiento)
    
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO TiposMovimiento (Descripcion) VALUES ('PAGAR SERVICIO'), ('DEPOSITAR'), ('TRANSFERIR');


/* MOVIMIENTOS */
CREATE TABLE Movimientos (
	numMovimiento	INT			AUTO_INCREMENT,
    idCLiente		INT			NOT NULL,
    CBU_origen		VARCHAR(30)	NOT NULL,
    CBU_destino		VARCHAR(30)	NULL,
    Monto			DECIMAL(30,2) NOT NULL,
    tipoMovimiento	INT			NOT NULL,
    idServicio		INT			NULL,
    FechaMovimiento	DATETIME	NOT NULL,
    
    
    
    CONSTRAINT PK_Movimientos PRIMARY KEY (numMovimiento),
    CONSTRAINT FK_IDCliente	foreign key	(idCLiente) REFERENCES clientes (idCliente),
    CONSTRAINT FK_MovimientosCuentas1 FOREIGN KEY (CBU_origen) REFERENCES Cuentas (CBU),
    CONSTRAINT FK_MovimientosCuentas2 FOREIGN KEY (CBU_destino) REFERENCES Cuentas (CBU),
    CONSTRAINT FK_MovimientosTiposMovimiento FOREIGN KEY (tipoMovimiento) REFERENCES TiposMovimiento (tipoMovimiento),
    CONSTRAINT FK_MovimientosServicios FOREIGN KEY (idServicio) REFERENCES Servicios (id_Servicio)
    
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


/* FOREIGN KEY ClientesCuentas */
ALTER TABLE Clientes
ADD CONSTRAINT FK_ClientesCuentas1 FOREIGN KEY (Cuenta1) REFERENCES Cuentas (CBU);

ALTER TABLE Clientes
ADD CONSTRAINT FK_ClientesCuentas2 FOREIGN KEY (Cuenta2) REFERENCES Cuentas (CBU);

ALTER TABLE Clientes
ADD CONSTRAINT FK_ClientesCuentas3 FOREIGN KEY (Cuenta3) REFERENCES Cuentas (CBU);

ALTER TABLE Clientes
ADD CONSTRAINT FK_ClientesCuentas4 FOREIGN KEY (Cuenta4) REFERENCES Cuentas (CBU);



/* PROCEDIMIENTOS */
DELIMITER $$


/* USUARIOS */
CREATE PROCEDURE Proc_CrearUsuario(
	IN	v_login			VARCHAR(24),
    IN	v_password		VARCHAR(24),
    IN	v_idCliente		INT
)
BEGIN
	INSERT INTO Usuarios
		(
			login,
            password,
            idCliente
		)
    VALUES
		(
			v_login,
            v_password,
            v_idCliente
		);
END$$

CREATE PROCEDURE Proc_ActualizarUsuario(
	IN	v_login			VARCHAR(24),
    IN	v_password		VARCHAR(24),
    IN	v_idCliente		INT
)
BEGIN
	UPDATE Usuarios SET
		password = v_password,
		idCliente = v_idCliente
	WHERE login = v_login;
END$$

CREATE PROCEDURE Proc_TraerUsuarios()
BEGIN
	SELECT login, idCliente
    FROM Usuarios;
END$$

CREATE PROCEDURE Proc_TraerUsuario(
    IN	v_login		VARCHAR(24)
)
BEGIN
	SELECT login, password, idCliente
    FROM Usuarios
    WHERE login = v_login;
END$$

CREATE PROCEDURE Proc_TraerUsuarioPorId(
    IN	v_idCliente	INT
)
BEGIN
	SELECT login, password, idCliente
    FROM Usuarios
    WHERE idCliente = v_idCliente;
END$$



/* TIPOS ESTADO */
CREATE PROCEDURE Proc_TraerTiposEstado()
BEGIN
	SELECT tipoEstado, Descripcion
    FROM TiposEstado;
END$$



/* TIPOS CUENTA */
CREATE PROCEDURE Proc_TraerTiposCuenta()
BEGIN
	SELECT tipoCuenta, Descripcion
    FROM TiposCuenta;
END$$


/* TIPOS MOVIMIENTO */
CREATE PROCEDURE Proc_TraerTiposMovimiento()
BEGIN
	SELECT tipoMovimiento, Descripcion
    FROM TiposMovimiento;
END$$




/* CLIENTES */

CREATE PROCEDURE Proc_TraerCliente
(
    IN	v_idCliente		INT
)
BEGIN
	SELECT idCliente, Nombre, Apellido, DNI, Direccion, Email, Telefono, FechaNac, Cuenta1, Cuenta2, Cuenta3, Cuenta4, tipoEstado, FechaRegistro
    FROM Clientes
    WHERE idCliente = v_idCliente;
END$$

CREATE PROCEDURE Proc_TraerClientes
(
	IN	v_tipoEstado	INT
)
BEGIN
	SELECT idCliente, Nombre, Apellido, DNI, Direccion, Email, Telefono, FechaNac, Cuenta1, Cuenta2, Cuenta3, Cuenta4, tipoEstado, FechaRegistro
    FROM Clientes
    WHERE tipoEstado = v_tipoEstado OR v_tipoEstado = 0; -- Si la función recibe 0 como parametro, trae todos los clientes sin importar su estado
END$$

CREATE PROCEDURE Proc_CrearCliente
(
	IN	v_Nombre		VARCHAR(45),
    IN	v_Apellido		VARCHAR(45),
    IN	v_DNI			VARCHAR(10),
    IN	v_Direccion		VARCHAR(80),
    IN	v_Email			VARCHAR(45),
    IN	v_Telefono		VARCHAR(20),
    IN	v_FechaNac		DATE,
    IN	v_tipoEstado	INT
)
BEGIN
	INSERT INTO Clientes
		(
			Nombre,
            Apellido,
            DNI,
            Direccion,
            Email,
            Telefono,
            FechaNac,
            TipoEstado,
            FechaRegistro
		)
    VALUES
		(
			v_Nombre,
            v_Apellido,
            v_DNI,
            v_Direccion,
            v_Email,
            v_Telefono,
            v_FechaNac,
            v_TipoEstado,
            NOW()
		);
END$$

CREATE PROCEDURE Proc_ActualizarCliente
(
	IN	v_idCliente		INT,
	IN	v_Nombre		VARCHAR(45),
    IN	v_Apellido		VARCHAR(45),
    IN	v_DNI			VARCHAR(10),
    IN	v_Direccion		VARCHAR(80),
    IN	v_Email			VARCHAR(45),
    IN	v_Telefono		VARCHAR(20),
    IN	v_FechaNac		DATE,
    IN	v_Cuenta1		VARCHAR(30),
    IN	v_Cuenta2		VARCHAR(30),
    IN	v_Cuenta3		VARCHAR(30),
    IN	v_Cuenta4		VARCHAR(30),
    IN	v_tipoEstado	INT
)
BEGIN
	UPDATE Clientes SET
		Nombre = v_Nombre,
		Apellido = v_Apellido,
		DNI = v_DNI,
		Email = v_Email,
		Telefono = v_Telefono,
		FechaNac = v_FechaNac,
		Cuenta1 = v_Cuenta1,
		Cuenta2 = v_Cuenta2,
		Cuenta3 = v_Cuenta3,
		Cuenta4 = v_Cuenta4,
        tipoEstado = v_tipoEstado
	WHERE idCliente = v_idCliente;
END$$

CREATE PROCEDURE Proc_EliminarCliente
(
	IN	v_idCliente		INT
)
BEGIN
	UPDATE Clientes SET
		tipoEstado = 1
    WHERE idCliente = v_idCliente;
END$$

CREATE PROCEDURE Proc_RehabilitarCliente
(
	IN	v_idCliente		INT
)
BEGIN
	UPDATE Clientes SET
		tipoEstado = 2
    WHERE idCliente = v_idCliente;
END$$



/* CUENTAS */
CREATE PROCEDURE Proc_TraerCuenta
(
    IN	v_CBU		VARCHAR(30)
)
BEGIN
	SELECT CBU, Saldo, idCliente, tipoCuenta, tipoEstado, FechaRegistro
    FROM Cuentas
    WHERE CBU = v_CBU;
END$$

CREATE PROCEDURE Proc_TraerCuentas
(
    IN	v_tipoEstado	INT
)
BEGIN
	SELECT CBU, Saldo, idCliente, tipoCuenta, tipoEstado, FechaRegistro
    FROM Cuentas
    WHERE tipoEstado = v_tipoEstado OR v_tipoEstado = 0;
END$$

CREATE PROCEDURE Proc_CrearCuenta
(
	IN	v_idCliente		INT,
    IN	v_tipoCuenta	INT,
    IN	v_tipoEstado	INT
)
BEGIN
	INSERT INTO Cuentas
		(
			Saldo,
            idCliente,
            tipoCuenta,
            tipoEstado,
            FechaRegistro
		)
    VALUES
		(
			0,
            v_idCliente,
            v_tipoCuenta,
            v_tipoEstado,
            NOW()
		);
END$$

CREATE TRIGGER tr_CrearCuenta_CBU BEFORE INSERT ON Cuentas
FOR EACH ROW BEGIN
	IF (SELECT (ISNULL(c.Cuenta1) + ISNULL(c.Cuenta2) + ISNULL(c.Cuenta3) + ISNULL(c.Cuenta4)) FROM Clientes c WHERE c.idCliente = NEW.idCliente) > 0 THEN
		SET NEW.CBU = (SELECT DNI FROM Clientes WHERE idCliente = NEW.idCliente) || '-' || (FLOOR(RAND() * 6000) + 300) || (FLOOR(RAND() * 6000) + 300) || (FLOOR(RAND() * 6000) + 300) || (FLOOR(RAND() * 6000) + 300);
    ELSE
		SET NEW.CBU = NULL;
    END IF;
END$$

CREATE TRIGGER tr_CrearCuenta_AsociarClienteCBU AFTER INSERT ON Cuentas
FOR EACH ROW BEGIN
	IF (SELECT Cuenta1 FROM Clientes WHERE idCliente = NEW.idCliente) IS NULL THEN
		UPDATE Clientes SET Cuenta1 = NEW.CBU WHERE idCliente = NEW.idCliente;
	ELSEIF (SELECT Cuenta2 FROM Clientes WHERE idCliente = NEW.idCliente) IS NULL THEN
		UPDATE Clientes SET Cuenta2 = NEW.CBU WHERE idCliente = NEW.idCliente;
	ELSEIF (SELECT Cuenta3 FROM Clientes WHERE idCliente = NEW.idCliente) IS NULL THEN
		UPDATE Clientes SET Cuenta3 = NEW.CBU WHERE idCliente = NEW.idCliente;
	ELSEIF (SELECT Cuenta4 FROM Clientes WHERE idCliente = NEW.idCliente) IS NULL THEN
		UPDATE Clientes SET Cuenta4 = NEW.CBU WHERE idCliente = NEW.idCliente;
	END IF;
END$$

CREATE PROCEDURE Proc_ActualizarCuenta
(
	IN	v_CBU			VARCHAR(30),
    IN	v_Saldo			DECIMAL(19,4),
    IN	v_idCliente		INT,
    IN	v_tipoCuenta	INT,
    IN	v_tipoEstado	INT
)
BEGIN
	UPDATE Cuentas SET
		Saldo = v_Saldo,
		idCliente = v_idCliente,
		tipoCuenta = v_tipoCuenta,
        tipoEstado = v_tipoEstado
	WHERE CBU = v_CBU;
END$$

CREATE PROCEDURE Proc_EliminarCuenta
(
	IN	v_CBU			VARCHAR(30)
)
BEGIN
	UPDATE Cuentas SET
		tipoEstado = 1
	WHERE CBU = v_CBU;
END$$

CREATE TRIGGER tr_BorrarCuenta AFTER UPDATE ON Cuentas
FOR EACH ROW BEGIN
	IF NEW.tipoEstado = 1 AND OLD.tipoEstado = 2 THEN
		UPDATE Clientes SET Cuenta1 = NULL WHERE idCliente = OLD.idCliente AND Cuenta1 = OLD.CBU;
        UPDATE Clientes SET Cuenta2 = NULL WHERE idCliente = OLD.idCliente AND Cuenta2 = OLD.CBU;
        UPDATE Clientes SET Cuenta3 = NULL WHERE idCliente = OLD.idCliente AND Cuenta3 = OLD.CBU;
        UPDATE Clientes SET Cuenta4 = NULL WHERE idCliente = OLD.idCliente AND Cuenta4 = OLD.CBU;
    END IF;
END$$

CREATE PROCEDURE Proc_RehabilitarCuenta
(
	IN	v_CBU			VARCHAR(30)
)
BEGIN
	UPDATE Cuentas SET
		tipoEstado = 2
	WHERE CBU = v_CBU;
END$$




/* SERVICIOS */
CREATE PROCEDURE Proc_CrearServicio
(
	IN	v_RazonSocial	VARCHAR(100),
    IN	v_Descripcion	VARCHAR(200),
    IN	v_tipoEstado	INT
)
BEGIN
	INSERT INTO Servicios
		(
			RazonSocial_Servicio,
            Descripcion_Servicio,
            tipoEstado_Servicio,
            FechaRegistro_Servicio
		)
    VALUES
		(
			v_RazonSocial,
            v_Descripcion,
            v_tipoEstado,
            NOW()
		);
END$$


CREATE PROCEDURE Proc_ActualizarServicio
(
	IN	v_idServicio	INT,
    IN	v_RazonSocial	VARCHAR(100),
    IN	v_Descripcion	VARCHAR(200),
    IN	v_tipoEstado	INT
)
BEGIN
	UPDATE Servicios SET
		RazonSocial_Servicio = v_RazonSocial,
		Descripcion_Servicio = v_Descripcion,
        tipoEstado_Servicio = v_tipoEstado
	WHERE id_Servicio = v_idServicio;
END$$


CREATE PROCEDURE Proc_EliminarServicio
(
	IN	v_idServicio	INT
)
BEGIN
	UPDATE Servicios SET
		tipoEstado_Servicio = 1
	WHERE id_Servicio = v_idServicio;
END$$


CREATE PROCEDURE Proc_RehabilitarServicio
(
	IN	v_idServicio	INT
)
BEGIN
	UPDATE Servicios SET
		tipoEstado_Servicio = 2
	WHERE id_Servicio = v_idServicio;
END$$


CREATE PROCEDURE Proc_TraerServicios
(
    IN	v_tipoEstado	INT
)
BEGIN
	SELECT id_Servicio, RazonSocial_Servicio, Descripcion_Servicio, tipoEstado_Servicio, FechaRegistro_Servicio
    FROM Servicios
    WHERE tipoEstado_Servicio = v_tipoEstado;
END$$


CREATE PROCEDURE Proc_TraerServicio
(
    IN	v_idServicio	INT
)
BEGIN
	SELECT id_Servicio, RazonSocial_Servicio, Descripcion_Servicio, tipoEstado_Servicio, FechaRegistro_Servicio
    FROM Servicios
    WHERE id_Servicio = v_idServicio;
END$$


CREATE PROCEDURE Proc_TraerServicioNombre
(
    IN	v_RazonSocial_Servicio	INT
)
BEGIN
	SELECT id_Servicio, RazonSocial_Servicio, Descripcion_Servicio, tipoEstado_Servicio, FechaRegistro_Servicio
    FROM Servicios
    WHERE RazonSocial_Servicio = v_RazonSocial_Servicio;
END$$



/* MOVIMIENTOS */
CREATE PROCEDURE Proc_CrearMovimientoporCuenta
(
	IN  v_idCliente			INT,
    IN	v_CBU_origen		VARCHAR(30),
    IN	v_CBU_destino		VARCHAR(30),
    IN	v_Monto				DECIMAL(30,2),
    IN	v_tipoMovimiento	INT
)
BEGIN
	INSERT INTO Movimientos
		(
			idCliente,
            CBU_origen,
            CBU_destino,
            Monto,
            tipoMovimiento,
            FechaMovimiento
		)
    VALUES
		(
			v_idCliente,
            v_CBU_origen,
            v_CBU_destino,
            v_Monto,
            v_tipoMovimiento,
            NOW()
		);
END$$


CREATE PROCEDURE Proc_CrearMovimientoporServicio
(
	IN  v_idCliente			INT,
    IN	v_CBU_origen		VARCHAR(30),
    IN	v_Monto				DECIMAL(30,2),
    IN	v_tipoMovimiento	INT,
    IN  v_idServicio		INT
)
BEGIN
	INSERT INTO Movimientos
		(
			idCliente,
            CBU_origen,
            Monto,
			tipoMovimiento,
			idServicio,
            FechaMovimiento
		)
    VALUES
		(
			v_idCliente,
            v_CBU_origen,
            v_Monto,
            v_tipoMovimiento,
            v_idServicio,
            NOW()
		);
END$$


CREATE PROCEDURE Proc_ActualizarSaldoCBU
(
	IN	v_CBU			VARCHAR(30),
    IN	v_Saldo			DECIMAL(19,4)
)
BEGIN
	UPDATE Cuentas SET
		Saldo = v_Saldo
	WHERE CBU = v_CBU;
END$$
		

CREATE PROCEDURE Proc_Historial
(
    IN	v_idCliente	INT
)
BEGIN
	SELECT numMovimiento, idCLiente, CBU_origen, CBU_destino, Monto, tipoMovimiento, idServicio, FechaMovimiento
    FROM Movimientos
    WHERE idCLiente = v_idCliente;
END$$

DELIMITER ;

/* USE */
USE `TP_Integrador`;

CALL Proc_CrearServicio ('Telecentro', 'Cable, internet y teléfono', 2);
CALL Proc_CrearServicio ('Metrocom', 'Internet', 2);
CALL Proc_CrearServicio ('Movistar', 'Compañía móvil', 2);

/* DATOS PARA TEST */
CALL Proc_CrearCliente ('Pablo', 'Gomez Carrizo', '39740780', 'Brughetti 250', 'pablogomezcarrizo@gmail.com', '1140414835', '1996-05-15', 2);
CALL Proc_CrearCliente ('Raiden', 'Raiden', '452145785', 'Brughetti 250', 'pablogomezcarrizo@gmail.com', '1140414835', '1996-05-15', 2);
CALL Proc_CrearCliente ('Fernando', 'Gomez', '789456123', 'Brughetti 250', 'pablogomezcarrizo@gmail.com', '1140414835', '1996-05-15', 2);


CALL Proc_CrearUsuario ('admin', 'admin', 1);
CALL Proc_CrearUsuario ('raiden', 'raiden', 2);
CALL Proc_CrearUsuario ('fer', 'fer', 3);

CALL Proc_CrearCuenta (1, 1, 2);

CALL Proc_TraerClientes (0);

CALL Proc_TraerCuentas (0);

CALL Proc_TraerServicios (0);

CALL Proc_TraerTiposEstado;

CALL Proc_TraerTiposCuenta;

CALL Proc_TraerUsuarios;

