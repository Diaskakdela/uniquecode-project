package kz.geekpartners.uniquecodeservice.entity;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
public class UniqueCode extends BaseUniqueCode{
}