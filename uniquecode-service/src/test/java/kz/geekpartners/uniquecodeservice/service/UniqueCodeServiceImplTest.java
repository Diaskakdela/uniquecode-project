package kz.geekpartners.uniquecodeservice.service;

import kz.geekpartners.uniquecodeservice.entity.UniqueCode;
import kz.geekpartners.uniquecodeservice.exception.UniqueCodeException;
import kz.geekpartners.uniquecodeservice.repository.UniqueCodeRepository;
import kz.geekpartners.uniquecodeservice.service.impl.UniqueCodeServiceImpl;
import kz.geekpartners.uniquecodeservice.util.UniqueCodeGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UniqueCodeServiceImplTest {
    @InjectMocks
    private UniqueCodeServiceImpl uniqueCodeService;
    @Mock
    private UniqueCodeRepository uniqueCodeRepository;
    @Mock
    private UniqueCodeGenerator uniqueCodeGenerator;

    private UniqueCode uniqueCode;

    @BeforeEach
    public void setUp() {
        uniqueCode = new UniqueCode();
        uniqueCode.setId(1L);
        uniqueCode.setCode("a0a0");
    }

    @Test
    public void getUniqueCodeAndUpdate_success() {
        when(uniqueCodeRepository.findFirstByOrderById()).thenReturn(Optional.of(uniqueCode));
        when(uniqueCodeGenerator.generateNewCode("a0a0")).thenReturn("a0a1");

        String newCode = uniqueCodeService.getUniqueCodeAndUpdate();

        assertThat(newCode).isEqualTo("a0a1");
        verify(uniqueCodeRepository, times(1)).findFirstByOrderById();
        verify(uniqueCodeRepository, times(1)).save(any(UniqueCode.class));
    }

    @Test
    public void getUniqueCodeAndUpdate_codeNotFound() {
        when(uniqueCodeRepository.findFirstByOrderById()).thenReturn(Optional.empty());

        assertThatThrownBy(() -> uniqueCodeService.getUniqueCodeAndUpdate())
                .isInstanceOf(UniqueCodeException.class)
                .hasMessage("No unique code found in database");
        verify(uniqueCodeRepository, times(1)).findFirstByOrderById();
        verify(uniqueCodeRepository, never()).save(any(UniqueCode.class));
    }
}
