package br.com.qm.api.pecas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.qm.api.pecas.entity.Venda;

@Repository
public interface VendaRepository extends CrudRepository<Venda, Long>{

	
	@Query("select sum(v.valorVenda) from Venda v")
	Float calcularFaturamento();
	
	List<Venda> findAllByNomeVendedor(String vendedor);
	
	List<Venda> findAllByFormaPagamento(String pagamento);
	
}
