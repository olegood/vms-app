package vms.repo;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vms.domain.DocumentType;

@RepositoryRestResource(path = "document-type")
public interface DocumentTypeRepository extends JpaRepository<DocumentType, UUID> {}
