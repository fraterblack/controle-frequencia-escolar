package br.com.app01.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.app01.model.entities.Aluno;

@Named(value="gerFrequenciaPorTurmaMB")
@ViewScoped
public class GerenciarFrequenciaPorTurmaMB extends GerenciarFrequenciaMB implements Serializable {

	private static final long serialVersionUID = -5023274207097502355L;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void populaFrequencia(Integer codTurma) {
		List<FrequenciaWrapper> lista = new ArrayList();
		
		if (codTurma == null) {
			setListaFrequencia(lista);
			return;
		}
		
		List<Object[]> listaFrequencia = frequenciaAlunoDAO.getFrequenciaPorTurma(codTurma);
		
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
			
			populaFrequencia(getCodTurma());
		}
	}
}
