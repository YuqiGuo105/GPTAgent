CREATE TABLE IF NOT EXISTS mrpot_candidates (
  candidate_id           UUID PRIMARY KEY,
  created_at             TIMESTAMPTZ NOT NULL,

  dedupe_key             TEXT NOT NULL UNIQUE,

  status                 VARCHAR(32) NOT NULL,
  type                   VARCHAR(32) NOT NULL,
  session_id             UUID,
  model                  VARCHAR(64),
  top_k                  INTEGER,
  min_score              DOUBLE PRECISION,
  latency_ms             INTEGER,
  out_of_scope           BOOLEAN NOT NULL DEFAULT FALSE,
  error                  TEXT,

  question               TEXT NOT NULL,
  answer                 TEXT,
  canon_text             TEXT,

  scope_guard_scoped     BOOLEAN,
  scope_guard_reason     TEXT,
  scope_guard_rewrite    TEXT,
  intent                 VARCHAR(32),
  evidence_gap_status    VARCHAR(32),
  track_correct_on_track BOOLEAN,
  track_correct_status   VARCHAR(32),
  assumption_risk        VARCHAR(16),

  tool_results           JSONB
);

CREATE TABLE IF NOT EXISTS mrpot_candidate_key_steps (
  candidate_id UUID NOT NULL REFERENCES mrpot_candidates(candidate_id) ON DELETE CASCADE,
  ord          SMALLINT NOT NULL,
  step_text    TEXT NOT NULL,
  PRIMARY KEY (candidate_id, ord)
);

CREATE TABLE IF NOT EXISTS mrpot_candidate_key_points (
  candidate_id UUID NOT NULL REFERENCES mrpot_candidates(candidate_id) ON DELETE CASCADE,
  ord          SMALLINT NOT NULL,
  point_text   TEXT NOT NULL,
  PRIMARY KEY (candidate_id, ord)
);

CREATE TABLE IF NOT EXISTS mrpot_candidate_entity_terms (
  candidate_id UUID NOT NULL REFERENCES mrpot_candidates(candidate_id) ON DELETE CASCADE,
  term         TEXT NOT NULL,
  PRIMARY KEY (candidate_id, term)
);

CREATE TABLE IF NOT EXISTS mrpot_candidate_keywords_items (
  candidate_id UUID NOT NULL REFERENCES mrpot_candidates(candidate_id) ON DELETE CASCADE,
  ord          SMALLINT NOT NULL,
  item_text    TEXT NOT NULL,
  source       TEXT,
  PRIMARY KEY (candidate_id, ord)
);

CREATE TABLE IF NOT EXISTS mrpot_candidate_key_info_lines (
  candidate_id UUID NOT NULL REFERENCES mrpot_candidates(candidate_id) ON DELETE CASCADE,
  ord          SMALLINT NOT NULL,
  line_text    TEXT NOT NULL,
  PRIMARY KEY (candidate_id, ord)
);

CREATE INDEX IF NOT EXISTS idx_candidates_created_at   ON mrpot_candidates(created_at DESC);
CREATE INDEX IF NOT EXISTS idx_candidates_status       ON mrpot_candidates(status);
CREATE INDEX IF NOT EXISTS idx_candidates_session      ON mrpot_candidates(session_id);
CREATE INDEX IF NOT EXISTS idx_candidates_type         ON mrpot_candidates(type);
CREATE INDEX IF NOT EXISTS idx_candidates_out_of_scope ON mrpot_candidates(out_of_scope);

CREATE INDEX IF NOT EXISTS idx_candidates_tool_results_gin
  ON mrpot_candidates USING GIN (tool_results);

CREATE INDEX IF NOT EXISTS idx_entity_terms_term
  ON mrpot_candidate_entity_terms(term);
