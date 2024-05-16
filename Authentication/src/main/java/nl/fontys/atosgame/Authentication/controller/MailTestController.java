// package nl.fontys.atosgame.Authentication.controller;



// import nl.fontys.atosgame.Authentication.service.MailSenderService;
// import org.slf4j.LoggerFactory;
// import org.slf4j.Logger;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/gamemaster")
// @CrossOrigin(origins = "*")
// public class MailTestController {

//     private static final Logger logger = LoggerFactory.getLogger(MailTestController.class);
//     private final MailSenderService mailSenderService;

//     public MailTestController(MailSenderService mailSenderService) {
//         this.mailSenderService = mailSenderService;
//     }

//     @PostMapping("/send-test-email")
//     public String sendTestEmail() {
//         logger.info("endpoint hit: /api/gamemaster/send-test-email");
//         String to = "brandonlodowica@gmail.com";
//         String subject = "Fine Notification";
//         String body = "You have been fined 1000 euros. To Solve this issue, please deposit 1000 euros to the following bank account: NL12 INGB 1264 7624 9584 02. Thank you for your cooperation";
//         mailSenderService.sendNewMail(to, subject, body);
//         return "Test email sent successfully!";
//     }
// }
