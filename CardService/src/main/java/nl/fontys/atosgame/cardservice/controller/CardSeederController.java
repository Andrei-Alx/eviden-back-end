package nl.fontys.atosgame.cardservice.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.seeder.CardSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/cards-seeder")
public class CardSeederController {
	private final CardSeeder cardSeeder;

	@Autowired
	public CardSeederController(CardSeeder cardSeeder){
		this.cardSeeder = cardSeeder;
	}

	@GetMapping("/seed")
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = "Cards seeded",
							content = @io.swagger.v3.oas.annotations.media.Content(
									mediaType = "application/json",
									schema = @Schema(implementation = Card.class)
							)
					)
			}
	)
	public ResponseEntity<String> seedCards() {
		try {
			cardSeeder.seedCards();
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
