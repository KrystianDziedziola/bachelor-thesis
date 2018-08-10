package edu.uz.inz.application;

import edu.uz.inz.application.query.GetByIdQuery;
import edu.uz.inz.port.adapter.repository.read.CustomerReadModel;
import java.util.List;

public interface CustomersReadApplicationService {

    List<CustomerReadModel> getCustomers();

    CustomerReadModel getCustomer(GetByIdQuery query);
}
