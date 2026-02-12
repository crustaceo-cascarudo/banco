-- Inserciones iniciales: users
INSERT INTO `user` (`id`, `name`, `surname1`, `surname2`, `dni`, `password`) VALUES
(1, 'Alice', 'González', 'Martínez', '11111111A', '$2a$12$9kMYTkiTnHVA5uUn9fJKb.9iNKUXs6AyzfmjCQgDA6h4rNWL.7fv6'),
(2, 'Virginia', 'Puff', 'Señorita', '11111111B', '$2a$12$9kMYTkiTnHVA5uUn9fJKb.9iNKUXs6AyzfmjCQgDA6h4rNWL.7fv6');

-- Cuentas asociadas a usuarios
INSERT INTO `account` (`iban`, `balance`, `user_id`) VALUES
('ES7600123456789012345678', 1000.000000, 1),
('ES6600987654321098765432', 0.00, 2);

-- Tarjetas vinculadas a cuentas (usar card_number dentro del rango INT)
INSERT INTO `card` (`card_number`, `cvc`, `expiration_date`, `full_name`, `type`, `account_iban`) VALUES
(4123456789, 123, '2026-12-31', 'Alice González', 'CREDIT', 'ES7600123456789012345678');

-- Sesiones de usuario
INSERT INTO `session` (`token`, `user_id`, `created_at`) VALUES
('s', 1, '2025-01-01 10:00:00'),
('sess_token_bob', 2, '2025-01-02 11:30:00');


INSERT INTO `api_client` (`client_name`, `api_key_hash`, `status`, `created_at`)
VALUES (
    'store',
    '$2a$12$9I20Cas4QBvELXDV53WvFu4yMytldJHbuNmU0roulLf0KMpHq/Z3e',
    'ACTIVE',
    '2025-01-01 10:00:00'
);
