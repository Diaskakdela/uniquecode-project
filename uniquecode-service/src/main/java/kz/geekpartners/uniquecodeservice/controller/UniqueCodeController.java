package kz.geekpartners.uniquecodeservice.controller;

import kz.geekpartners.uniquecodeservice.entity.UniqueCode;
import kz.geekpartners.uniquecodeservice.service.UniqueCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/unique-code")
@RequiredArgsConstructor
public class UniqueCodeController {
    private final UniqueCodeService uniqueCodeService;

    @GetMapping
    public ResponseEntity<String> getNewCode(){
        return ResponseEntity.ok(uniqueCodeService.getUniqueCodeAndUpdate());
    }
}
