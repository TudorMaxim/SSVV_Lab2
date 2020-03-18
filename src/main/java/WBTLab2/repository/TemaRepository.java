package WBTLab2.repository;


import WBTLab2.domain.Tema;
import WBTLab2.validation.Validator;

public class TemaRepository extends AbstractCRUDRepository<String, Tema> {
    public TemaRepository(Validator<Tema> validator) {
        super(validator);
    }
}

