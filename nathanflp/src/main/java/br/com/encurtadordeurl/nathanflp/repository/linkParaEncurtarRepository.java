package br.com.encurtadordeurl.nathanflp.repository;

import br.com.encurtadordeurl.nathanflp.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface linkParaEncurtarRepository extends JpaRepository<LinkParaEncurtar,String> {

}
