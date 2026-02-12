-- Inserciones iniciales: users
INSERT INTO `user` (`id`, `name`, `surname1`, `surname2`, `dni`, `password`) VALUES
(1, 'Alice', 'González', 'Martínez', '11111111A', '$2a$12$9kMYTkiTnHVA5uUn9fJKb.9iNKUXs6AyzfmjCQgDA6h4rNWL.7fv6'),
(2, 'Virginia', 'Puff', 'Señorita', '11111111b', '$2a$12$9kMYTkiTnHVA5uUn9fJKb.9iNKUXs6AyzfmjCQgDA6h4rNWL.7fv6');

-- Cuentas asociadas a usuarios
INSERT INTO `account` (`iban`, `balance`, `user_id`) VALUES
('ES7600123456789012345678', 1000.000000, 1),
('ES7600123445779012345676', 1500.000000, 1),
('ES6600987654321098765432', 0.00, 2);

-- Tarjetas vinculadas a cuentas (usar card_number dentro del rango INT)
INSERT INTO `card` (`card_number`, `cvc`, `expiration_date`, `full_name`, `type`, `account_iban`) VALUES
(4123456789, 123, '2026-12-31', 'Alice González', 'CREDIT', 'ES7600123456789012345678');

-- Sesiones de usuario
INSERT INTO `session` (`token`, `user_id`, `created_at`) VALUES
('s', 1, '2025-01-01 10:00:00'),
('sess_token_bob', 2, '2025-01-02 11:30:00');

-- Movimientos bancarios de ejemplo
INSERT INTO `banking_movement`
(`origin_account`, `origin_card`, `recipient_account`, `movement_date`, `amount`, `concept`, `payment_method`, `type`) VALUES
('ES7600123456789012345678', NULL, 'ES6600987654321098765432', '2025-01-02', 150.000000, 'Rent payment', 'TRANSFER', 'DEBIT'),
('ES6600987654321098765432', '5123456780', 'ES7600123456789012345678', '2025-01-03', 20.000000, 'Refund', 'CARD_PAYMENT', 'CREDIT'),
('ES5600112233445566778899', NULL, 'ES7600123456789012345678', '2025-02-01', 200.000000, 'Salary', 'DIRECT_DEBIT', 'CREDIT');

INSERT INTO `api_client` (`client_name`, `api_key_hash`, `status`, `created_at`)
VALUES (
    'store',
    '$2a$12$9I20Cas4QBvELXDV53WvFu4yMytldJHbuNmU0roulLf0KMpHq/Z3e',
    'ACTIVE',
    '2025-01-01 10:00:00'
);
