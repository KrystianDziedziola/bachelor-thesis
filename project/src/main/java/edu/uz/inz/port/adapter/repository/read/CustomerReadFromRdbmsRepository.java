package edu.uz.inz.port.adapter.repository.read;

import edu.uz.inz.domain.model.Customer;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CustomerReadFromRdbmsRepository extends CrudRepository<Customer, UUID> {

}
