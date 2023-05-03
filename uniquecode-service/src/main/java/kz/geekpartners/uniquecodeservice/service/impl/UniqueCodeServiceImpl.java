package kz.geekpartners.uniquecodeservice.service.impl;

import kz.geekpartners.uniquecodeservice.entity.UniqueCode;
import kz.geekpartners.uniquecodeservice.exception.UniqueCodeException;
import kz.geekpartners.uniquecodeservice.repository.UniqueCodeRepository;
import kz.geekpartners.uniquecodeservice.service.UniqueCodeService;
import kz.geekpartners.uniquecodeservice.util.UniqueCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UniqueCodeServiceImpl implements UniqueCodeService {
    private final UniqueCodeRepository uniqueCodeRepository;
    private final UniqueCodeGenerator uniqueCodeGenerator;

    @Override
    public String getUniqueCodeAndUpdate() {
        UniqueCode uniqueCode = uniqueCodeRepository.findFirstByOrderById().orElseThrow(()->new UniqueCodeException("No unique code found in database"));

        String newCode = uniqueCodeGenerator.generateNewCode(uniqueCode.getCode());

        if(newCode.isEmpty()){
            throw new UniqueCodeException("New unique code has not generated");
        }

        uniqueCode.setCode(newCode);
        uniqueCodeRepository.save(uniqueCode);
        return newCode;
    }


}
