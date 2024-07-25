CREATE DATABASE  IF NOT EXISTS `prisma`;
USE `prisma`;


--
-- Table structure for table `user`
--
DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id_user` bigint NOT NULL AUTO_INCREMENT,
                        `active` bit(1) NOT NULL,
                        `birth_date` datetime(6) DEFAULT NULL,
                        `dni` varchar(255) DEFAULT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `last_name` varchar(255) DEFAULT NULL,
                        `name` varchar(255) DEFAULT NULL,
                        `phone` varchar(255) DEFAULT NULL,
                        `type` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin`
--
DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
                         `id_admin` varchar(10) NOT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `profile` varchar(255) DEFAULT NULL,
                         `id_user` bigint DEFAULT NULL,
                         PRIMARY KEY (`id_admin`),
                         UNIQUE KEY `UK9vm4uu4nvcv1lgmgrqdo5vlrg` (`id_user`),
                         CONSTRAINT `FK4ert0ej65rd0y5e7b7ukmi82t` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `speciality`
--
DROP TABLE IF EXISTS `speciality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `speciality` (
                              `id_speciality` bigint NOT NULL AUTO_INCREMENT,
                              `name` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id_speciality`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `teacher`
--
DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
                           `id_teacher` varchar(10) NOT NULL,
                           `password` varchar(255) DEFAULT NULL,
                           `profile` varchar(255) DEFAULT NULL,
                           `id_speciality` bigint DEFAULT NULL,
                           `id_user` bigint DEFAULT NULL,
                           PRIMARY KEY (`id_teacher`),
                           UNIQUE KEY `UK1giqscy9vr0y3b2anvkyadcxe` (`id_user`),
                           KEY `FKmlud55bajemvfwr2rwqswgl5r` (`id_speciality`),
                           CONSTRAINT `FK1j8r4d0olybhmcj1r9bn3shuu` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
                           CONSTRAINT `FKmlud55bajemvfwr2rwqswgl5r` FOREIGN KEY (`id_speciality`) REFERENCES `speciality` (`id_speciality`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Table structure for table `applicant`
--
DROP TABLE IF EXISTS `applicant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applicant` (
                             `id_applicant` varchar(10) NOT NULL,
                             `id_user` bigint DEFAULT NULL,
                             PRIMARY KEY (`id_applicant`),
                             UNIQUE KEY `UKtgp7gqvjotvbt6879jyvlxbdn` (`id_user`),
                             CONSTRAINT `FKmn0pw9ch0kalw4qs306gux9uy` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student`
--

--DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
                           `id_student` varchar(10) NOT NULL,
                           `entry_date` datetime(6) DEFAULT NULL,
                           `grade` bigint DEFAULT NULL,
                           `password` varchar(255) DEFAULT NULL,
                           `payment_status` bit(1) NOT NULL,
                           `profile` varchar(255) DEFAULT NULL,
                           `section` varchar(255) DEFAULT NULL,
                           `shift` varchar(255) DEFAULT NULL,
                           `study_level` varchar(255) DEFAULT NULL,
                           `id_user` bigint DEFAULT NULL,
                           PRIMARY KEY (`id_student`),
                           UNIQUE KEY `UKgk5vu6ga9cu8ho09qs12cq91l` (`id_user`),
                           CONSTRAINT `FKb4lfwbonj876jqkfv3syhp06o` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Table structure for table `classroom`
--
DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classroom` (
                             `id_classroom` bigint NOT NULL AUTO_INCREMENT,
                             `code` varchar(255) DEFAULT NULL,
                             PRIMARY KEY (`id_classroom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course`
--
DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
                          `id_course` bigint NOT NULL AUTO_INCREMENT,
                          `name` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id_course`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `class`
--
DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
                         `id_class` bigint NOT NULL AUTO_INCREMENT,
                         `day` varchar(255) DEFAULT NULL,
                         `end_time` time(6) DEFAULT NULL,
                         `start_time` time(6) DEFAULT NULL,
                         `id_classroom` bigint DEFAULT NULL,
                         `id_course` bigint DEFAULT NULL,
                         `id_teacher` varchar(10) DEFAULT NULL,
                         PRIMARY KEY (`id_class`),
                         KEY `FK45omcp4kjjfm2rk43cccqbvv3` (`id_classroom`),
                         KEY `FKmgykbnc31e15wbwnms085l9tv` (`id_course`),
                         KEY `FKgt2pwx13so4rdtnjxh2wvtwhk` (`id_teacher`),
                         CONSTRAINT `FK45omcp4kjjfm2rk43cccqbvv3` FOREIGN KEY (`id_classroom`) REFERENCES `classroom` (`id_classroom`),
                         CONSTRAINT `FKgt2pwx13so4rdtnjxh2wvtwhk` FOREIGN KEY (`id_teacher`) REFERENCES `teacher` (`id_teacher`),
                         CONSTRAINT `FKmgykbnc31e15wbwnms085l9tv` FOREIGN KEY (`id_course`) REFERENCES `course` (`id_course`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
                           `id_message` bigint NOT NULL AUTO_INCREMENT,
                           `content` varchar(255) DEFAULT NULL,
                           `message_type` varchar(255) DEFAULT NULL,
                           `revised` bit(1) NOT NULL,
                           `sent_date` datetime(6) DEFAULT NULL,
                           `title` varchar(255) DEFAULT NULL,
                           `id_receiver` bigint DEFAULT NULL,
                           `id_sender` bigint DEFAULT NULL,
                           PRIMARY KEY (`id_message`),
                           KEY `FKqyxddlqh59ce3gqls6qu9w3lq` (`id_receiver`),
                           KEY `FK249t1hnaxlacjng5xtud18fi` (`id_sender`),
                           CONSTRAINT `FK249t1hnaxlacjng5xtud18fi` FOREIGN KEY (`id_sender`) REFERENCES `user` (`id_user`),
                           CONSTRAINT `FKqyxddlqh59ce3gqls6qu9w3lq` FOREIGN KEY (`id_receiver`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notification`
--
DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
                                `id_notification` bigint NOT NULL AUTO_INCREMENT,
                                `creation_date` datetime(6) DEFAULT NULL,
                                `message` varchar(255) DEFAULT NULL,
                                `revised` bit(1) NOT NULL,
                                `title` varchar(255) DEFAULT NULL,
                                `id_user` bigint DEFAULT NULL,
                                PRIMARY KEY (`id_notification`),
                                KEY `FKjsqpq32j3cp7sbi81on7xo3jg` (`id_user`),
                                CONSTRAINT `FKjsqpq32j3cp7sbi81on7xo3jg` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `qualification`
--
DROP TABLE IF EXISTS `qualification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qualification` (
                                 `id_qualification` bigint NOT NULL AUTO_INCREMENT,
                                 `bimester` int NOT NULL,
                                 `value` int NOT NULL,
                                 `id_class` bigint DEFAULT NULL,
                                 `course_id` bigint DEFAULT NULL,
                                 `id_student` varchar(10) DEFAULT NULL,
                                 PRIMARY KEY (`id_qualification`),
                                 KEY `FKke6larhanwcl5b7n7k6a7pqkr` (`id_class`),
                                 KEY `FK2jhml8esj4v5p3b6s1nrv2uhr` (`course_id`),
                                 KEY `FKmo0i63p3w514m60wmrap2uwvo` (`id_student`),
                                 CONSTRAINT `FK2jhml8esj4v5p3b6s1nrv2uhr` FOREIGN KEY (`course_id`) REFERENCES `class` (`id_class`),
                                 CONSTRAINT `FKke6larhanwcl5b7n7k6a7pqkr` FOREIGN KEY (`id_class`) REFERENCES `class` (`id_class`),
                                 CONSTRAINT `FKmo0i63p3w514m60wmrap2uwvo` FOREIGN KEY (`id_student`) REFERENCES `student` (`id_student`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `registration`
--
DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
                                `id_registration` bigint NOT NULL AUTO_INCREMENT,
                                `registration_date` datetime(6) DEFAULT NULL,
                                `status` varchar(255) DEFAULT NULL,
                                `id_user` bigint DEFAULT NULL,
                                PRIMARY KEY (`id_registration`),
                                KEY `FKfa8d7ap6efm32cn7kh5nu7gtk` (`id_user`),
                                CONSTRAINT `FKfa8d7ap6efm32cn7kh5nu7gtk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_class_assignment`
--
DROP TABLE IF EXISTS `student_class_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_class_assignment` (
                                            `id_assignment` int NOT NULL AUTO_INCREMENT,
                                            `id_class` bigint NOT NULL,
                                            `id_student` varchar(10) NOT NULL,
                                            PRIMARY KEY (`id_assignment`),
                                            KEY `FKk4w7fkoa893w6qs4phoai8a4w` (`id_class`),
                                            KEY `FKomft8if286nf1v5daapt5isqq` (`id_student`),
                                            CONSTRAINT `FKk4w7fkoa893w6qs4phoai8a4w` FOREIGN KEY (`id_class`) REFERENCES `class` (`id_class`),
                                            CONSTRAINT `FKomft8if286nf1v5daapt5isqq` FOREIGN KEY (`id_student`) REFERENCES `student` (`id_student`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DELIMITER ;;
CREATE PROCEDURE solicitar_vacante (
    IN p_name VARCHAR(100),
    IN p_last_name VARCHAR(100),
    IN p_birth_date VARCHAR(10),
    IN p_dni VARCHAR(20),
    IN p_email VARCHAR(100),
    IN p_phone VARCHAR(20)
)
BEGIN
    DECLARE v_id_user INT;

    -- Insertar usuario en la tabla user con tipo 'POSTULANTE'
    INSERT INTO `user` (name, last_name, birth_date, dni, email, phone, type, active)
    VALUES (p_name, p_last_name, p_birth_date, p_dni, p_email, p_phone, 'Postulante', 1);

    -- Obtener el ID del usuario insertado
    SET v_id_user = LAST_INSERT_ID();

    -- Insertar solicitud en la tabla registration
    INSERT INTO registration (registration_date, status, id_user)
    VALUES (CURDATE(), 'pendiente', v_id_user);

    -- Finalizar procedimiento
    COMMIT;
END;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE aceptar_vacante (
    IN p_id_user INT
)
BEGIN
    -- Actualizar el tipo de usuario a 'ESTUDIANTE'
    UPDATE `user`
    SET type = 'Estudiante'
    WHERE id_user = p_id_user;

    -- Actualizar el estado de la solicitud a 'aceptado'
    UPDATE registration
    SET status = 'aceptado'
    WHERE id_user = p_id_user;

    -- Finalizar procedimiento
    COMMIT;
END;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE registrarDocente (
    IN p_id_teacher INT,
    IN p_password VARCHAR(255),
    IN p_profile VARCHAR(255),
    IN p_name VARCHAR(255),
    IN p_last_name VARCHAR(255),
    IN p_birth_date VARCHAR(10),
    IN p_dni VARCHAR(20),
    IN p_email VARCHAR(100),
    IN p_phone VARCHAR(20),
    IN p_speciality_name VARCHAR(255)
)
BEGIN
    DECLARE v_user_id INT;
    DECLARE v_speciality_id INT;
    DECLARE v_registration_id INT;

    -- Insertar usuario
    INSERT INTO `user` (name, last_name, birth_date, dni, email, phone, type, active)
    VALUES (p_name, p_last_name, p_birth_date, p_dni, p_email, p_phone, 'Docente', 1);

    -- Obtener el ID del usuario insertado
    SET v_user_id = LAST_INSERT_ID();

    -- Insertar especialidad
    INSERT INTO speciality (name)
    VALUES (p_speciality_name);

    -- Obtener el ID de la especialidad insertada
    SET v_speciality_id = LAST_INSERT_ID();

    -- Insertar registro
    INSERT INTO registration (registration_date, status, id_user)
    VALUES (CURDATE(), 'activo', v_user_id);

    -- Insertar docente con el ID proporcionado
    INSERT INTO teacher (id_teacher, password, profile, id_speciality, id_user)
    VALUES (p_id_teacher, p_password, p_profile, v_speciality_id, v_user_id);

    -- Finalizar procedimiento
    COMMIT;
END;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `listarDocente` ()
BEGIN
    SELECT
        t.id_teacher,
        t.profile,
        t.password,
        s.name AS speciality_name,
        u.name,
        u.last_name,
        u.birth_date,
        u.dni,
        u.email,
        u.phone,
        r.registration_date
    FROM
        teacher t
            INNER JOIN `user` u ON t.id_user = u.id_user
            INNER JOIN speciality s ON t.id_speciality = s.id_speciality
            INNER JOIN registration r ON r.id_user = t.id_user
    WHERE
        u.type = 'Docente' AND r.status = 'activo';
END;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE actualizarDocente (
    IN p_teacher_id INT,
    IN p_password VARCHAR(255),
    IN p_profile VARCHAR(255),
    IN p_name VARCHAR(255),
    IN p_last_name VARCHAR(255),
    IN p_birth_date VARCHAR(10),
    IN p_dni VARCHAR(20),
    IN p_email VARCHAR(100),
    IN p_phone VARCHAR(20),
    IN p_speciality_name VARCHAR(255)
)
BEGIN
    DECLARE v_user_id INT;
    DECLARE v_speciality_id INT;

    -- Obtener el ID del usuario asociado al docente
    SELECT id_user INTO v_user_id FROM teacher WHERE id_teacher = p_teacher_id;

    -- Actualizar datos del usuario
    UPDATE `user`
    SET
        name = p_name,
        last_name = p_last_name,
        birth_date = p_birth_date,
        dni = p_dni,
        email = p_email,
        phone = p_phone
    WHERE id_user = v_user_id;

    -- Obtener el ID de la especialidad existente o insertar una nueva
    SELECT id_speciality INTO v_speciality_id FROM speciality WHERE name = p_speciality_name;
    IF v_speciality_id IS NULL THEN
        INSERT INTO speciality (name)
        VALUES (p_speciality_name);
        SET v_speciality_id = LAST_INSERT_ID();
    END IF;

    -- Actualizar especialidad del docente
    UPDATE teacher
    SET
        password = p_password,
        profile = p_profile,
        id_speciality = v_speciality_id
    WHERE id_teacher = p_teacher_id;

    -- Finalizar procedimiento
    COMMIT;
END;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE eliminarDocente (
    IN p_id_teacher INT
)
BEGIN
    DECLARE v_user_id INT;

    -- Obtener el ID de usuario asociado al docente
    SELECT id_user INTO v_user_id FROM teacher WHERE id_teacher = p_id_teacher;

    -- Verificar si se encontró un ID de usuario válido
    IF v_user_id IS NOT NULL THEN
        -- Eliminar docente de la tabla teacher
        DELETE FROM teacher WHERE id_teacher = p_id_teacher;

        -- Actualizar el estado del usuario a "eliminado" en la tabla user
        UPDATE `user` SET active = 0 WHERE id_user = v_user_id;

        -- Actualizar el registro de inscripción asociado al usuario a "eliminado"
        UPDATE registration SET status = 'eliminado' WHERE id_user = v_user_id;

        -- Finalizar procedimiento
        COMMIT;
    ELSE
        -- Mostrar mensaje de error o registrar un evento de error
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No se encontró un usuario asociado al docente';
    END IF;
END;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE buscarDocentes(IN nombre VARCHAR(255))
BEGIN
    SELECT
        t.id_teacher, t.profile, u.name, u.last_name, u.email, u.phone, s.name AS speciality_name
    FROM teacher t
    INNER JOIN user u ON t.id_user = u.id_user
    INNER JOIN speciality s ON t.id_speciality = s.id_speciality
    WHERE u.name LIKE CONCAT('%', nombre, '%');
END;;

DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE loginUsuario(
    IN p_profile VARCHAR(255), IN p_password VARCHAR(255), OUT p_type VARCHAR(255)
)
BEGIN
    -- Inicializar p_type como NULL
    SET p_type = NULL;

    -- Buscar en la tabla admin
    SELECT 'Administrador' INTO p_type
    FROM admin a
    INNER JOIN user u ON a.id_user = u.id_user
    WHERE a.profile = p_profile AND a.password = p_password
    LIMIT 1;

    -- Buscar en la tabla teacher si no se encontró en admin
    IF p_type IS NULL THEN
        SELECT 'Docente' INTO p_type
        FROM teacher t
        INNER JOIN user u ON t.id_user = u.id_user
        WHERE t.profile = p_profile AND t.password = p_password
        LIMIT 1;
    END IF;

    -- Buscar en la tabla student si no se encontró en admin ni teacher
    IF p_type IS NULL THEN
        SELECT 'Estudiante' INTO p_type
        FROM student s
        INNER JOIN user u ON s.id_user = u.id_user
        WHERE s.profile = p_profile AND s.password = p_password
        LIMIT 1;
    END IF;

END;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE obtenerIdUsuario(IN p_profile VARCHAR(255), IN p_password VARCHAR(255), OUT p_id_user VARCHAR(255))
BEGIN
    -- Buscar en la tabla de administradores
    SELECT id_user INTO p_id_user
    FROM admin
    WHERE profile = p_profile AND password = p_password;

    IF p_id_user IS NULL THEN
        -- Buscar en la tabla de docentes
        SELECT id_user INTO p_id_user
        FROM teacher
        WHERE profile = p_profile AND password = p_password;
    END IF;

    IF p_id_user IS NULL THEN
        -- Buscar en la tabla de estudiantes
        SELECT id_user INTO p_id_user
        FROM student
        WHERE profile = p_profile AND password = p_password;
    END IF;
END;;

DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `registrar_clase` (
    IN p_day VARCHAR(255),
    IN p_start_time TIME,
    IN p_end_time TIME,
    IN p_id_classroom BIGINT,
    IN p_id_course BIGINT,
    IN p_id_teacher VARCHAR(10)
)
BEGIN
    DECLARE conflicts INT;

    -- Verificar si hay un cruce de horarios para el mismo docente en el mismo día
    SELECT COUNT(*) INTO conflicts
    FROM `class`
    WHERE `id_teacher` = p_id_teacher
      AND `day` = p_day
      AND (
            (p_start_time BETWEEN `start_time` AND `end_time`) OR
            (p_end_time BETWEEN `start_time` AND `end_time`) OR
            (`start_time` BETWEEN p_start_time AND p_end_time) OR
            (`end_time` BETWEEN p_start_time AND p_end_time)
          );

    -- Si no hay conflictos, registrar la nueva clase
    IF conflicts = 0 THEN
        INSERT INTO `class` (`day`, `start_time`, `end_time`, `id_classroom`, `id_course`, `id_teacher`)
        VALUES (p_day, p_start_time, p_end_time, p_id_classroom, p_id_course, p_id_teacher);
    ELSE
        -- Si hay conflictos, devolver un mensaje de error
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Conflicto de horarios para el docente en el mismo día';
    END IF;
END;;

DELIMITER ;


insert into user (active, birth_date,dni,email,last_name,name,phone,type) values
    (1,'2001-04-09','74713885','kikecabanillas0003@gmail.com','Cabanillas Rojas','Victor Enrique','968099508','Administrador');
insert into admin (id_admin,password,profile,id_user) values (1,'123456','U21218723',1);
