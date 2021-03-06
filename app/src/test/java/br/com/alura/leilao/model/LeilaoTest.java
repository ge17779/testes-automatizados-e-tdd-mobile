package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario GERSON = new Usuario("Gerson");

    @Test
    public void deve_DevolverDescricao_QuandoRecebeDescricao() {
        // Executar ação esperada
        String descricaoDevolvida = CONSOLE.getDescricao();

        // Testar execução esperada
        assertEquals("Console", descricaoDevolvida);
    }
    
    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeApenasUmLance(){
        CONSOLE.propoe(new Lance(GERSON, 200.0));
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        CONSOLE.propoe(new Lance(GERSON, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Juan"), 200.0));
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){
        CONSOLE.propoe(new Lance(new Usuario("Juan"), 1000.0));
        CONSOLE.propoe(new Lance(GERSON, 900.0));
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();
        assertEquals(1000.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeApenasUmLance(){
        CONSOLE.propoe(new Lance(GERSON, 200.0));
        double menorLanceDevolvido = CONSOLE.getMenorLance();
        assertEquals(200.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        CONSOLE.propoe(new Lance(GERSON, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Juan"), 200.0));
        double menorLanceDevolvido = CONSOLE.getMenorLance();
        assertEquals(100.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){
        CONSOLE.propoe(new Lance(new Usuario("Juan"), 10000.0));
        CONSOLE.propoe(new Lance(GERSON, 9000.0));
        double menorLanceDevolvido = CONSOLE.getMenorLance();
        assertEquals(9000.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances(){
        CONSOLE.propoe(new Lance(GERSON, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Juan"), 300.0));
        CONSOLE.propoe(new Lance(new Usuario("Renato"), 200.0));

        // Test Driven Development
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();
        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(100.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
        assertEquals(GERSON.getNome(), tresMaioresLancesDevolvidos.get(2).getUsuario().getNome());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances(){
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance(){
        CONSOLE.propoe(new Lance(GERSON, 200.0));
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(1, tresMaioresLancesDevolvidos.size(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeDoisLances(){
        CONSOLE.propoe(new Lance(GERSON, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Thiago"), 400.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(100.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisQueTresLances(){
        CONSOLE.propoe(new Lance(GERSON, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Thiago"), 200.0));
        CONSOLE.propoe(new Lance(new Usuario("João"), 300.0));
        CONSOLE.propoe(new Lance(new Usuario("Felipe"), 400.0));

        List<Lance> tresMaioresLancesDevolvidosParaQuatroLances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaQuatroLances.size(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidosParaQuatroLances.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidosParaQuatroLances.get(1).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidosParaQuatroLances.get(2).getValor(), DELTA);

        CONSOLE.propoe(new Lance(new Usuario("Judas"), 500.0));

        List<Lance> tresMaioresLancesDevolvidosParaCincoLances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaCincoLances.size(), DELTA);
        assertEquals(500.0, tresMaioresLancesDevolvidosParaCincoLances.get(0).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidosParaCincoLances.get(1).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidosParaCincoLances.get(2).getValor(), DELTA);
    }
}