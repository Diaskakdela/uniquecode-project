package kz.geekpartners.uniquecodeservice.service.impl;

import jakarta.transaction.Transactional;
import kz.geekpartners.uniquecodeservice.entity.LastGeneratedUniqueCode;
import kz.geekpartners.uniquecodeservice.entity.UniqueCode;
import kz.geekpartners.uniquecodeservice.exception.UniqueCodeException;
import kz.geekpartners.uniquecodeservice.repository.LastGeneratedUniqueCodeRepository;
import kz.geekpartners.uniquecodeservice.repository.UniqueCodeRepository;
import kz.geekpartners.uniquecodeservice.service.UniqueCodeService;
import kz.geekpartners.uniquecodeservice.util.UniqueCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UniqueCodeServiceImpl implements UniqueCodeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UniqueCodeServiceImpl.class);

    private final UniqueCodeRepository uniqueCodeRepository;
    private final LastGeneratedUniqueCodeRepository lastUniqueCodeRepository;
    private final UniqueCodeGenerator uniqueCodeGenerator;

    @Override
    @Transactional
    public String getUniqueCodeAndUpdate() {
        Optional<UniqueCode> optionalUniqueCode = uniqueCodeRepository.findFirstByOrderById();
        Optional<LastGeneratedUniqueCode> optionalLastGeneratedUniqueCode = lastUniqueCodeRepository.findFirstByOrderById();
        LastGeneratedUniqueCode lastGeneratedUniqueCode = optionalLastGeneratedUniqueCode.orElseGet(this::generateNewLastGeneratedCodeAndSave);
        UniqueCode uniqueCode = optionalUniqueCode.orElseGet(() -> getLastGeneratedCode(lastGeneratedUniqueCode));

        String lastCode = uniqueCode.getCode();
        String newCode = uniqueCodeGenerator.generateNewCode(uniqueCode.getCode());

        if(newCode.isEmpty()){
            throw new UniqueCodeException("New unique code has not generated");
        }

        if(!lastCode.equals(lastGeneratedUniqueCode.getCode())){
            lastGeneratedUniqueCode.setCode(lastCode);
            lastUniqueCodeRepository.save(lastGeneratedUniqueCode);
        }
        uniqueCode.setCode(newCode);

        uniqueCodeRepository.save(uniqueCode);

        return uniqueCode.getCode();
    }

    private UniqueCode getLastGeneratedCode(LastGeneratedUniqueCode lastGeneratedUniqueCode){
        UniqueCode uniqueCode = new UniqueCode();
        String newCode = lastGeneratedUniqueCode.getCode();

        if(!newCode.equals("a0a0")){
            newCode = uniqueCodeGenerator.generateNewCode(newCode);
        }

        uniqueCode.setCode(newCode);
        LOGGER.info("UniqueCode is not present, using last generated code");
        return uniqueCode;
    }
    private LastGeneratedUniqueCode generateNewLastGeneratedCodeAndSave(){
        LastGeneratedUniqueCode lastGeneratedUniqueCode = new LastGeneratedUniqueCode();
        lastGeneratedUniqueCode.setCode("a0a0");
        LOGGER.info("LastGeneratedUniqueCode is not present, creating new one");
        return lastUniqueCodeRepository.save(lastGeneratedUniqueCode);
    }

}
