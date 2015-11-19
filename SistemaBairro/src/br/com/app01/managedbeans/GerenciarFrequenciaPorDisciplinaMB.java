package br.com.app01.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.app01.model.entities.Aluno;

@Named(value="gerFrequenciaPorDisciplinaMB")
@ViewScoped
public class GerenciarFrequenciaPorDisciplinaMB extends GerenciarFrequenciaMB implements Serializable {
	private static final long serialVersionUID = 5597563601428491307L;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void populaFrequencia(Integer codDiscTurma) {
		List<FrequenciaWrapper> lista = new ArrayList();
		
		if (codDiscTurma == null) {
			setListaFrequencia(lista);
			return;
		}
		
		List<Object[]> listaFrequencia = frequenciaAlunoDAO.getFrequenciaPorDisciplina(codDiscTurma);
		
		for (Object[] dados: listaFrequencia) {
			Aluno a = new Aluno();
			a.setNomeAluno(dados[0].toString());
			
			lista.add( new FrequenciaWrapper(a, Integer.valueOf(dados[1].toString()), Integer.valueOf(dados[2].toString()), Integer.valueOf(dados[3].toString()), Integer.valueOf(dados[4].toString())));
		}
		
		setListaFrequencia(lista);
	}
	
	public void changeListener(ValueChangeEvent e) {
		if ("filtroTurma".equals(e.getComponent().getId())) {
			setCodTurma((e.getNewValue() != null) ? Integer.valueOf(e.getNewValue().toString()) : null);
			
			setCodDiscTurma(null);
			setListaFrequencia(null);
		} else if ("filtroDisciplina".equals(e.getComponent().getId())) {
			setCodDiscTurma((e.getNewValue() != null) ? Integer.valueOf(e.getNewValue().toString()) : null);
			
			populaFrequencia(getCodDiscTurma());
		}
	}
}
