package vms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vms.domain.IdentityType;

@RepositoryRestResource(path = "identity-type")
public interface IdentityTypeRepository extends JpaRepository<IdentityType, Long> {}
