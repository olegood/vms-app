package vms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vms.domain.IdentityDocument;

@RepositoryRestResource(path = "identity-document")
public interface IdentityDocumentRepository extends JpaRepository<IdentityDocument, Long> {}
