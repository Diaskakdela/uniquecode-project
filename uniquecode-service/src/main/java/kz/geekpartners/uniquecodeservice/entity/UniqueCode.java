package kz.geekpartners.uniquecodeservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UniqueCode {
    @Id
    private Long id = 1L;

    private String code;
}