package br.unicesumar.restserver.veiculo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veiculos")
@Transactional
public class VeiculoController {

    @Autowired
    private EntityManager em;

    @RequestMapping(method = RequestMethod.GET)
    public List<Veiculo> getVeiculos() {
        Query consulta = em.createQuery("from Veiculo");
        return consulta.getResultList();
        
    }
    @RequestMapping(method = RequestMethod.POST)
    public void criarVeiculo(@RequestBody Veiculo veiculo) {
        em.persist(veiculo);
    } 
    
    @RequestMapping(method = RequestMethod.PUT)
    public void alterarVeiculo(@RequestBody Veiculo veiculo) {
        em.merge(veiculo);
        em.persist(veiculo);         
    }        
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void excluirVeiculo(@PathVariable Long id) {
        Veiculo veiculo = em.find(Veiculo.class, id);
        em.remove(veiculo);
    }        
    
}
