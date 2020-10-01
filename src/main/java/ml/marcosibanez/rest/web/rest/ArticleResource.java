package ml.marcosibanez.rest.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
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

@CrossOrigin(origins = "https://marcos-ibanez.ml/")
@RestController
@RequestMapping("/api/")
public class ArticleResource {
    
    private static final String SUCCES = "Succes";
	private static final String OK = "OK";
	@Autowired
	ArticleService articleService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "security/article", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> createPostArticle(@Valid @RequestBody ArticleDTO  articleDTO) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				articleService.createArticle( articleDTO));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "security/article", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<List<ArticleDTO>> readGetArticle() throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				articleService.findAllArticle());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "web/article/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<ArticleDTO> findByLinkarticleOne(@RequestParam String linkarticle) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				articleService.findByLinkarticle(linkarticle));
	}
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "web/initializ/", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> existsByLinkarticle() throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				articleService.existsByLinkarticle());
	}
	@PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "security/article", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<ArticleDTO> updatePutArticle(@Valid @RequestBody ArticleDTO  articleDTO) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				articleService.updateArticle( articleDTO));
	}	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "security/article", produces = MediaType.APPLICATION_JSON_VALUE)
	public MensajeResponse<String> deleteArticle(@RequestParam Long id) throws MensajeException {
		return new MensajeResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				articleService.deleteArticle(id));
	}
}
