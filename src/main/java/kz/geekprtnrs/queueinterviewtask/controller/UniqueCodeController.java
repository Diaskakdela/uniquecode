package kz.geekprtnrs.queueinterviewtask.controller;

import kz.geekprtnrs.queueinterviewtask.entity.UniqueCode;
import kz.geekprtnrs.queueinterviewtask.service.UniqueCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/uniquecode")
public class UniqueCodeController {

    final UniqueCodeService uniqueCodeService;

    public UniqueCodeController(UniqueCodeService uniqueCodeService) {
        this.uniqueCodeService = uniqueCodeService;
    }

    @GetMapping
    public ResponseEntity<UniqueCode> getNewCode(){
        Optional<UniqueCode> uniqueCode = uniqueCodeService.getNewCode();
        if(uniqueCode.isEmpty()){
            throw new RuntimeException("Not found");
        }
        return ResponseEntity.ok(uniqueCode.get());
    }
}
