
CREATE TABLE IF NOT EXISTS public.unique_code (
    id BIGINT NOT NULL,
    code VARCHAR(255) UNIQUE,
    PRIMARY KEY (id)
);

INSERT INTO unique_code (id, code)
SELECT 1, 'a0a0'
WHERE NOT EXISTS (SELECT 1 FROM unique_code WHERE id = 1);
