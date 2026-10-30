package tn.esprit.autoloc.service;

import java.util.List;

public interface IGenericService<T, ID>
        extends IReadService<T, ID>, IWriteService<T, ID>, IDeleteService<ID> {
}
