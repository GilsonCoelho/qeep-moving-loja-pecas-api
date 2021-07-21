package br.com.qm.api.pecas.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.qm.api.pecas.entity.Peca;
import br.com.qm.api.pecas.exception.ErroDeNegocioException;
import br.com.qm.api.pecas.service.PecaService;

@RestController
@RequestMapping("/pecas")
public class PecaController {

	@Autowired
	PecaService pecaService;
	
	@PostMapping
	public Peca cadastrarPeca(@Valid @RequestBody Peca peca) throws ErroDeNegocioException {
		return pecaService.cadastrarPeca(peca);
	}
	
	@GetMapping(path = "/{codBarras}")
	public Optional<Peca> consultarPeca(@PathVariable Long codBarras) throws ErroDeNegocioException {
		return pecaService.consultarPeca(codBarras);
	}

	@GetMapping
	public List<Peca> listaPecas() {
		return pecaService.listaPecas();
	}
	
	@DeleteMapping(path = "/{codBarras}")
	public boolean removerPeca(@PathVariable Long codBarras) {
		return pecaService.removerPeca(codBarras);
	}
	
	@PutMapping(path = "/{codBarras}")
	public Peca alterarPeca(@PathVariable Long codBarras, @RequestBody Peca peca) throws ErroDeNegocioException {
		return pecaService.alterarPeca(codBarras, peca);
	}
	
	@GetMapping(path = "/{texto}/comeco")
	public List<Peca> listarPecasComecadasCom(@PathVariable String texto) {
		return pecaService.listarPecasComecadasCom(texto);
	}
	
	@GetMapping(path = "/{modeloCarro}/modeloCarro")
	public List<Peca> listarPecasPorModelo(@PathVariable String modeloCarro) {
		return pecaService.listarPecasPorModelo(modeloCarro);
	}
	
	@GetMapping(path = "/{categoria}/categoria")
	public List<Peca> listarPecasPorCategoria(@PathVariable String categoria) {
		return pecaService.listarPecasPorCategoria(categoria);
	}
	
}
