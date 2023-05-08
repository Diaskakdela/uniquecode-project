package kz.geekpartners.uniquecodeservice.repository;

import kz.geekpartners.uniquecodeservice.entity.LastGeneratedUniqueCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LastGeneratedUniqueCodeRepository extends JpaRepository<LastGeneratedUniqueCode, Long> {
    Optional<LastGeneratedUniqueCode> findFirstByOrderById();

}
