package br.com.encurtadordeurl.nathanflp.repository;

import br.com.encurtadordeurl.nathanflp.models.linkParaEncurtar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface linkParaEncurtarRepository extends JpaRepository<linkParaEncurtar,String> {

}
