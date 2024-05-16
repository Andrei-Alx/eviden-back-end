// package nl.fontys.atosgame.Authentication.controller;
// import nl.fontys.atosgame.Authentication.service.CodeGeneratorService;
// import org.springframework.beans.factory.annotation.Autowired;

// import org.springframework.web.bind.annotation.*;



// @RestController
// @RequestMapping("/api/gamemaster")
// @CrossOrigin(origins = "*")
// public class CodeGeneratorController {
//     private final CodeGeneratorService codeService;

//     @Autowired
//     public CodeGeneratorController(CodeGeneratorService codeService) {
//         this.codeService = codeService;
//     }

//     @GetMapping("/generate-code")
//     public String generateCode(@RequestParam(defaultValue = "6") int length) {
//         return codeService.generateRandomCode(length);
//     }
    
// }
