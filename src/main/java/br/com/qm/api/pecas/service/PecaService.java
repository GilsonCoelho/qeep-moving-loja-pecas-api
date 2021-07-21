package br.com.qm.api.pecas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qm.api.pecas.entity.Peca;
import br.com.qm.api.pecas.exception.ErroDeNegocioException;
import br.com.qm.api.pecas.repository.PecaRepository;

@Service
public class PecaService {

	
	@Autowired
	PecaRepository pecaRepository;
	
	public Peca cadastrarPeca(Peca peca) throws ErroDeNegocioException {
		
		if (pecaRepository.existsById(peca.getCodBarras())) {
			throw new ErroDeNegocioException("A peça já existe!");
		}
		
		return pecaRepository.save(peca);
	}

	public Optional<Peca> consultarPeca(Long codBarras) throws ErroDeNegocioException {
		
		if (!pecaRepository.existsById(codBarras)) {
			throw new ErroDeNegocioException("A peça não existe!");
		}
		
		return pecaRepository.findById(codBarras);
	}
	
	
	public List<Peca> listaPecas() {
		return (List<Peca>) pecaRepository.findAll();
	}
	
	public boolean removerPeca(Long codBarras) {
		
		if (!pecaRepository.existsById(codBarras)) {
			return false;
		}
		
		pecaRepository.deleteById(codBarras);
		return true;
	}
	
	public Peca alterarPeca(Long codBarras, Peca peca) throws ErroDeNegocioException {
		
		if (!codBarras.equals(peca.getCodBarras())) {
			throw new ErroDeNegocioException("O id da peça do recurso é diferente do corpo!");
		}
		
		if (!pecaRepository.existsById(codBarras)) {
			throw new ErroDeNegocioException("A peça não existe");
		}
		
		return pecaRepository.save(peca);
	}

	public List<Peca> listarPecasComecadasCom(String texto) {
		return pecaRepository.findAllByNomeStartingWith(texto);
	}

	public List<Peca> listarPecasPorModelo(String modeloCarro) {
		return pecaRepository.findAllByModeloCarro(modeloCarro);
	}

	public List<Peca> listarPecasPorCategoria(String categoria) {
		return pecaRepository.findAllByCategoria(categoria);
	}
	
}
