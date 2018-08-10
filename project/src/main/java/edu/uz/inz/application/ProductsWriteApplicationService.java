package edu.uz.inz.application;

import edu.uz.inz.application.command.CreateProductCommand;
import edu.uz.inz.domain.model.Product;
import edu.uz.inz.domain.model.event.ProductCreatedEvent;
import edu.uz.inz.port.adapter.repository.write.ProductWriteRepository;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ProductsWriteApplicationService {

    private final ProductWriteRepository writeRepository;
    private final ApplicationEventPublisher eventPublisher;

    public ProductsWriteApplicationService(
        final ProductWriteRepository writeRepository,
        final ApplicationEventPublisher eventPublisher) {
        this.writeRepository = writeRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public UUID create(final CreateProductCommand command) {
        final Product savedEntity = saveProduct(command);
        eventPublisher.publishEvent(ProductCreatedEvent.from(savedEntity));
        return savedEntity.getUuid();
    }

    private Product saveProduct(final CreateProductCommand command) {
        final Product entity = Product.fromCommand(command);
        return writeRepository.save(entity);
    }
}
