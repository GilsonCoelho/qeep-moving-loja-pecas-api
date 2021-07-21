package br.com.qm.api.pecas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.qm.api.pecas.entity.Peca;

@Repository
public interface PecaRepository extends CrudRepository<Peca, Long>{

	List<Peca> findAllByNomeStartingWith(String texto);
	
	List<Peca> findAllByModeloCarro(String modeloCarro);
	
	List<Peca> findAllByCategoria(String categoria);

}
