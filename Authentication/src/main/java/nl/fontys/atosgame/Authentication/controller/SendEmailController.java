// package nl.fontys.atosgame.Authentication.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

// import nl.fontys.atosgame.Authentication.service.EmailService;
// import org.springframework.web.bind.annotation.RestController;
// import nl.fontys.atosgame.Authentication.service.MailSenderService;

// @RestController
// @RequestMapping("/email")
// public class SendEmailController {
//     private MailSenderService mailService;

//     public void Foo(){
//     mailService.sendNewMail("test@gmail.com", "Subject right here", "Body right there!");
//     }
//     @Autowired
//    // private EmailService emailService;
//     //private final EmailService service;



//     @GetMapping("/send-test-email")
//     public String sendTestEmail() {
//         String to = "evidendev@gmail.com";
//         String subject = "Test Email";
//         String body = "This is a test email sent from my Spring Boot application.";
        
//         // Invoke the email sending logic
//         Foo();
        
//         return "Test email sent successfully!";
//     }
// }
