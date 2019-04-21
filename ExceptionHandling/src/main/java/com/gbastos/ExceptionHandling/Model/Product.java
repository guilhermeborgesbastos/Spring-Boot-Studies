package com.gbastos.ExceptionHandling.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Know more about the JavaX Validation at:
 * https://www.baeldung.com/javax-validation
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = FieldName.ID)
  private Long id;

  @Column(name = FieldName.NAME)
  @NotNull
  private String name;

  @Column(name = FieldName.DESCRIPTION)
  @Size(min = 10, max = 200, message  = "Product Description must be between 10 and 200 characters.")
  private String description;

  @Column(name = FieldName.WEIGHT)
  @Min(value = 10, message = "The weight should not be less than 10 grams.")
  @Max(value = 30000, message = "The weight should not be greater than 3 kilograms.")
  private Integer weight;

  @Column(name = FieldName.ACTIVE)
  @NotNull
  private Boolean active;

  public static final class FieldName {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String WEIGHT = "weight";
    public static final String ACTIVE = "active";

    private FieldName() {}
  }

}