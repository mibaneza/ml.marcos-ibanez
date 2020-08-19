package ml.marcosibanez.rest.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ml.marcosibanez.rest.service.ArticleService;
import ml.marcosibanez.rest.service.dto.ArticleDTO;
import ml.marcosibanez.rest.service.exception.MensajeException;
import ml.marcosibanez.rest.web.rest.response.MensajeResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/web/")
public class ArticleResource {
    
    private final static String SUCCES = "Succes";
	private final static String OK = "OK";
	@Autowired
	ArticleService articleService;
	
	@PostMapping(value = "article", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> createPostArticle(@Valid @RequestBody ArticleDTO  articleDTO) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				articleService.createArticle( articleDTO));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "article", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<List<ArticleDTO>> readGetArticle() throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				articleService.findAllArticle());
	}
    @PutMapping(value = "article", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<ArticleDTO> updatePutArticle(@Valid @RequestBody ArticleDTO  articleDTO) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				articleService.updateArticle( articleDTO));
	}	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "article", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> deleteArticle(@RequestParam Long id) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				articleService.deleteArticle(id));
	}
}
