package kz.geekpartners.uniquecodeservice.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseUniqueCode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id = 1L;
    protected String code;
}
