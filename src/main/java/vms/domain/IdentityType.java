package vms.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "identity_type")
public class IdentityType implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "identity_type_id", nullable = false)
  private UUID id;

  @JsonProperty("public_ref")
  private String publicRef;

  private String label;
  private String description;
}