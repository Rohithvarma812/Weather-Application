CREATE TABLE IF NOT EXISTS user_profiles (
  id BIGSERIAL PRIMARY KEY,
  username VARCHAR(120) UNIQUE NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  heat_threshold DOUBLE PRECISION NOT NULL DEFAULT 34,
  pollution_threshold DOUBLE PRECISION NOT NULL DEFAULT 120,
  asthma BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX IF NOT EXISTS idx_user_profiles_username ON user_profiles(username);
