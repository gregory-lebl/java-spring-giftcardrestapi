CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS "user" (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );
CREATE TABLE IF NOT EXISTS company (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    siren VARCHAR(50) NOT NULL
    );
CREATE TABLE IF NOT EXISTS card (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    company_id UUID NOT NULL,
    amount INTEGER,
    expire_at TIMESTAMP,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE CASCADE,
    CONSTRAINT fk_company FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE
    );

CREATE INDEX IF NOT EXISTS idx_user_email ON "user"(email);
CREATE INDEX IF NOT EXISTS idx_card_user_id ON card(user_id);
CREATE INDEX IF NOT EXISTS idx_card_company_id ON card(company_id);
