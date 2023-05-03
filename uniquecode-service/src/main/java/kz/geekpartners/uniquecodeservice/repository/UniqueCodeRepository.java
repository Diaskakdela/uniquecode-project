package kz.geekpartners.uniquecodeservice.repository;

import kz.geekpartners.uniquecodeservice.entity.UniqueCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UniqueCodeRepository extends JpaRepository<UniqueCode, Long> {
    Optional<UniqueCode> findFirstByOrderById();
}
