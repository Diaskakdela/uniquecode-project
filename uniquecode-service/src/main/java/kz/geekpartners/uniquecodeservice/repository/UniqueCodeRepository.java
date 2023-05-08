package kz.geekpartners.uniquecodeservice.repository;

import kz.geekpartners.uniquecodeservice.entity.UniqueCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniqueCodeRepository extends JpaRepository<UniqueCode, Long> {
    Optional<UniqueCode> findFirstByOrderById();
}

