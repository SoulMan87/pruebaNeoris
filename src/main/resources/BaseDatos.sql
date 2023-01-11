CREATE TABLE Persona
(
    idPersona      INTEGER PRIMARY KEY,
    nombre         VARCHAR(255) NOT NULL,
    genero         CHAR(1)      NOT NULL,
    edad           INTEGER      NOT NULL,
    identificacion VARCHAR(255) NOT NULL,
    direccion      VARCHAR(255) NOT NULL,
    telefono       VARCHAR(255) NOT NULL
);

CREATE TABLE Cliente
(
    idCliente  INTEGER PRIMARY KEY,
    contraseña VARCHAR(255) NOT NULL,
    estado     BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (idCliente) REFERENCES Persona (idPersona)
);

CREATE TABLE Cuenta
(
    numeroDeCuenta VARCHAR(255) PRIMARY KEY,
    tipoDeCuenta   VARCHAR(255) NOT NULL,
    saldoInicial   DECIMAL      NOT NULL,
    estado         BOOLEAN DEFAULT TRUE,
    idCliente      INTEGER      NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES Cliente (customerId)
);

CREATE TABLE Movimiento
(
    idMovimiento   INTEGER PRIMARY KEY,
    date           DATE         NOT NULL,
    tipoMovimiento VARCHAR(255) NOT NULL,
    valor          DECIMAL      NOT NULL,
    saldo          DECIMAL      NOT NULL,
    numeroDeCuenta VARCHAR(255) NOT NULL,
    FOREIGN KEY (numeroDeCuenta) REFERENCES Cuenta (numeroDeCuenta)
);
-------------Join----------------------
INSERT INTO Persona (nombre, direccion, telefono)
VALUES (:nombre, :direccion, :telefono);

INSERT INTO Cliente (idCliente, contraseña, estado)
SELECT p.idPersona, :contraseña, TRUE
FROM Persona p
         JOIN (SELECT LAST_INSERT_ID() AS ultimoId) tmp ON 1 = 1;

-------------Creacion de usuarios-------

INSERT INTO Persona (0 ,nombre, direccion, telefono, estado)
VALUES ('Jose Lema', 'Otalvaro sn y principal', '098254785'),
       ('Marianela Otalvaro', 'Amazonas y NNUU', '097548965'),
       ('Juan Osorio', '13 junio y Equinoccial', '098874587');

INSERT INTO Cliente (idCliente, contraseña, estado)
SELECT p.idPersona, :contraseña, TRUE
FROM Persona p
         JOIN (SELECT LAST_INSERT_ID() AS ultimoId) tmp ON 1 = 1
WHERE p.idPersona >= (SELECT ultimoId - 2);

---------------Creación de Cuenta usuario-------
INSERT INTO Cuenta (numeroDeCuenta, tipoDeCuenta, saldoInicial, estado, idCliente)
SELECT :numeroDeCuenta, :tipoDeCuenta, :saldoInicial, TRUE, c.idCliente
FROM Cliente c
         JOIN Persona p ON c.idCliente = p.idPersona
WHERE p.nombre = :nombre;
-----------------Creacion de cuentas usuario--------
INSERT INTO Cuenta (numeroDeCuenta, tipoDeCuenta, saldoInicial, estado, idCliente)
SELECT '478758', 'Ahorro', '2000', TRUE, c.idCliente
FROM Cliente c
         JOIN Persona p ON c.idCliente = p.idPersona
WHERE p.nombre = 'Jose Lema';

---------creacion de movimientos------------------

INSERT INTO Movimiento (fecha, tipoMovimiento, valor, saldo, numeroDeCuenta)
VALUES (:date, :tipoMovimiento, :valor, :saldo, :numeroDeCuenta);

--------- movimientos----------------------------

INSERT INTO Movimiento (fecha, tipoMovimiento, valor, saldo, numeroDeCuenta)
VALUES (:date, :tipoMovimiento, :valor, :saldo, :numeroDeCuenta);

INSERT INTO Movimiento (fecha, tipoMovimiento, valor, saldo, numeroDeCuenta)
VALUES (CURDATE(), 'Retiro de 575', 100, 100, '478758');

---------- Listado de movimientos por fechas y por usuario ---------

SELECT m.fecha,
       m.tipoMovimiento,
       m.valor,
       m.saldo,
       a.numeroDeCuenta,
       a.tipoDeCuenta,
       a.saldoInicial,
       a.estado,
       p.nombre
FROM Movimiento m
         JOIN Cuenta a ON m.numeroDeCuenta = a.numeroDeCuenta
         JOIN Cliente c ON a.idCliente = c.idCliente
         JOIN Persona p ON c.idCliente = p.idPersona
WHERE m.numeroDeCuenta = :numeroDeCuenta
ORDER BY m.date;

------------------------------------

SELECT movimiento.fecha, persona.nombre, cuenta.numeroDeCuenta, cuenta.tipoDeCuenta, cuenta.saldoInicial, cuenta.estado,
       movimiento.tipoDeMovimiento, movimiento.saldo
FROM Movimiento movimiento
         JOIN Cuenta cuenta ON m.accountNumber = a.accountNumber
         JOIN Cliente cliente ON cuenta.idCliente = cliente.idCliente
         JOIN Persona personal ON cliente.idCliente = personal.idPersona;

---------------Reporte Estado de cuenta------------

SELECT c.idCliente as id, c.numeroDeCuenta, c.tipoDeCuenta, c.saldoInicial,
       SUM(CASE WHEN m.tipoMovimiento = 'DEBITO' THEN m.valor ELSE 0 END) as totalDebitos,
       SUM(CASE WHEN m.tipoMovimiento = 'CREDITO' THEN m.valor ELSE 0 END) as totalCreditos,
       (c.saldoInicial + SUM(CASE WHEN m.tipoMovimiento = 'CREDITO' THEN m.valor ELSE -m.valor END)) as saldoFinal,
       p.nombre as nombre
FROM Cuenta c
         JOIN Cliente cl ON c.idCliente = cl.idCliente
         JOIN Persona p ON cl.idCliente = p.idPersona
         JOIN Movimiento m ON c.numeroDeCuenta = m.numeroDeCuenta
WHERE p.nombre = ?1 AND m.fecha BETWEEN ?2 AND ?3
GROUP BY c.idCliente, c.numeroDeCuenta, c.tipoDeCuenta, c.saldoInicial;