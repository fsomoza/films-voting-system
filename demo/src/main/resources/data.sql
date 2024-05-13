CREATE OR REPLACE FUNCTION cleverpy_test.update_scores()
RETURNS TRIGGER AS '
BEGIN
    IF TG_OP = ''INSERT'' THEN
        UPDATE cleverpy_test.production
        SET total_votes = total_votes + 1
        WHERE id = NEW.production_id;
    ELSIF TG_OP = ''DELETE'' THEN
        UPDATE cleverpy_test.production
        SET total_votes = GREATEST(0, total_votes - 1)  -- Prevent negative votes
        WHERE id = OLD.production_id;
        RETURN OLD;
    END IF;
    RETURN NEW;
EXCEPTION
    WHEN OTHERS THEN
        RAISE WARNING ''Unexpected error: %'', SQLERRM;
        RETURN NULL;
END;
' LANGUAGE plpgsql;


-- Trigger for handling INSERT operations
CREATE TRIGGER trigger_after_insert_votes
AFTER INSERT ON cleverpy_test.votes
FOR EACH ROW
EXECUTE FUNCTION cleverpy_test.update_scores();

-- Trigger for handling DELETE operations
CREATE TRIGGER trigger_before_delete_votes
BEFORE DELETE ON cleverpy_test.votes
FOR EACH ROW
EXECUTE FUNCTION cleverpy_test.update_scores();
