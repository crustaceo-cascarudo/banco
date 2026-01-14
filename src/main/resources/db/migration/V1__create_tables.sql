CREATE TABLE `user` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `surname1` VARCHAR(255) NOT NULL,
    `surname2` VARCHAR(255),
    `dni` VARCHAR(255) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `api_token` VARCHAR(255) ,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `session` (
    `token` VARCHAR(255) NOT NULL,
    `user_id` INT(11) NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`token`),
    CONSTRAINT `fk_sessions_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `account` (
    `iban` VARCHAR(34) NOT NULL ,
    `balance` DECIMAL(16, 6) NOT NULL,
    `user_id` INT(11) NOT NULL,
    PRIMARY KEY (`iban`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `card` (
    `card_number` VARCHAR(19) NOT NULL,
    `cvc` int(3) NOT NULL,
    `expiration_date` DATE NOT NULL,
    `full_name` VARCHAR(255) NOT NULL,
    `type` ENUM('CREDIT', 'DEBIT', 'PREPAY') NOT NULL,
    `account_iban` VARCHAR(34) NOT NULL,
    PRIMARY KEY (`card_number`),
    FOREIGN KEY (`account_iban`) REFERENCES `account`(`iban`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `banking_movement` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `origin_account` VARCHAR(34) NOT NULL,
    `origin_card` VARCHAR(19),
    `recipient_account` VARCHAR(34) NOT NULL,
    `movement_date` DATE NOT NULL,
    `amount` DECIMAL(16, 6) NOT NULL,
    `concept` VARCHAR(255),
    `payment_method` ENUM('TRANSFER', 'DIRECT_DEBIT', 'CARD_PAYMENT') NOT NULL,
    `type` ENUM('DEBIT', 'CREDIT') NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


