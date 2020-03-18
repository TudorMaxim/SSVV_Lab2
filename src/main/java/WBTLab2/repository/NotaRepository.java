package WBTLab2.repository;


import WBTLab2.domain.Nota;
import WBTLab2.domain.Pair;
import WBTLab2.validation.Validator;

public class NotaRepository extends AbstractCRUDRepository<Pair<String, String>, Nota> {
    public NotaRepository(Validator<Nota> validator) {
        super(validator);
    }
}
