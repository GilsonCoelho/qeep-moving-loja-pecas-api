package br.com.qm.api.pecas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qm.api.pecas.dto.ResponseDTO;
import br.com.qm.api.pecas.dto.VendaDTO;
import br.com.qm.api.pecas.entity.Peca;
import br.com.qm.api.pecas.entity.Venda;
import br.com.qm.api.pecas.exception.ErroDeNegocioException;
import br.com.qm.api.pecas.repository.VendaRepository;

@Service
public class VendaService {

	
	@Autowired
	VendaRepository vendaRepository;
	
	@Autowired
	PecaService pecaService;
	
	
	public ResponseDTO realizaVenda(VendaDTO vendaDto) throws ErroDeNegocioException {
		
		Venda venda = vendaDto.toObject();
		
		Optional<Peca> pecaOpt = pecaService.consultarPeca(venda.getCodBarras());
		
		if (pecaOpt.isEmpty()) {
			throw new ErroDeNegocioException("A venda não pode ser realizada pois a peça não existe!");
		}
			
		Peca peca = pecaOpt.get();
		int QtdEstoque = peca.getQtdEstoque();
		int QtdVenda = venda.getQuantidade();
		
		if (QtdEstoque < QtdVenda) {
			throw new ErroDeNegocioException(
					"A venda não pode ser realizada pois a quantidade em estoque é menor do que a requisita! Hoje há em estoque "
							+ QtdEstoque + " peças");
		}
		
		peca.setQtdEstoque(QtdEstoque - QtdVenda);
		venda.setValorVenda(QtdVenda * peca.getPrecoVenda());
		
		pecaService.alterarPeca(peca.getCodBarras(), peca);
		vendaRepository.save(venda);
		
		return new ResponseDTO("Venda realizada com sucesso!");
	}

	public List<Venda> listarVendas() {
		return (List<Venda>) vendaRepository.findAll();
	}

	public Float calcularFaturamento() {
		return vendaRepository.calcularFaturamento();
	}

	public List<Venda> listarVendasPorVendedor(String vendedor) {
		return vendaRepository.findAllByNomeVendedor(vendedor);
	}

	public List<Venda> listarVendasPorPagamento(String formaPagamento) {
		return vendaRepository.findAllByFormaPagamento(formaPagamento);
	}

	public boolean removerVenda(Long idVenda) {
		
		if (!vendaRepository.existsById(idVenda)) {
			return false;
		}
		
		vendaRepository.deleteById(idVenda);
		return true;
	}

}
