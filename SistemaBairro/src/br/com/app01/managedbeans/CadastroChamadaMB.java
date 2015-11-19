package br.com.app01.managedbeans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.app01.context.SessionContext;
import br.com.app01.model.dao.DiscTurmaDAOImpl;
import br.com.app01.model.dao.DisciplinaDAOImpl;
import br.com.app01.model.dao.FrequenciaAlunoDAOImpl;
import br.com.app01.model.dao.FrequenciaDAOImpl;
import br.com.app01.model.dao.MatriculaDAOImpl;
import br.com.app01.model.dao.TurmaDAOImpl;
import br.com.app01.model.entities.Aluno;
import br.com.app01.model.entities.DiscTurma;
import br.com.app01.model.entities.Frequencia;
import br.com.app01.model.entities.FrequenciaAluno;
import br.com.app01.model.entities.Matricula;
import br.com.app01.model.entities.Turma;
import br.com.app01.model.entities.Usuario;
import br.com.app01.util.Message;

@Named(value="cadChamadaMB")
@ViewScoped
public class CadastroChamadaMB implements Serializable {
	
	private static final long serialVersionUID = -1334737992460958040L;
	
	private Integer codChamada;
	private Integer codTurma;
	private Integer codDiscTurma;
	private Date dataAula;
	private Date dataChamada;
	
	private Boolean desabilitaPresenca;
	private Boolean desabilitaFaltaJustificada;
	private Boolean desabilitaObservacao;
	
	private Usuario usuarioLogado;
	private String chamadaRealizada;

	private List<Frequencia> chamadasRealizadas;
	
	private List<ChamadaWrapper> listaDePresenca;
	
	@Inject
	MatriculaDAOImpl matriculaDao;
	
	@Inject
	TurmaDAOImpl turmaDao;
	
	@Inject
	DisciplinaDAOImpl disciplinaDao;
	
	@Inject
	DiscTurmaDAOImpl discTurmaDao;
	
	@Inject
	FrequenciaDAOImpl frequenciaDao;
	
	@Inject
	FrequenciaAlunoDAOImpl frequenciaAlunoDao;
	
	@PostConstruct
	public void init() {
		usuarioLogado = (Usuario) SessionContext.getInstance().getUsuarioLogado();
	}
	
	public Integer getCodChamada() {
		return codChamada;
	}
	public void setCodChamada(Integer codChamada) {
		this.codChamada = codChamada;
	}

	public Integer getCodTurma() {
		return codTurma;
	}
	public void setCodTurma(Integer codTurma) {
		this.codTurma = codTurma;
	}

	public Integer getCodDiscTurma() {
		return codDiscTurma;
	}
	public void setCodDiscTurma(Integer codDiscTurma) {
		this.codDiscTurma = codDiscTurma;
	}

	public Date getDataAula() {
		if (dataAula == null) {
			dataAula = dataHoje();
		}
		
		return dataAula;
	}
	public void setDataAula(Date dataAula) {
		this.dataAula = dataAula;
	}

	public Date getDataChamada() {
		return dataChamada;
	}
	public void setDataChamada(Date dataChamada) {
		this.dataChamada = dataChamada;
	}

	public String getChamadaRealizada() {
		return chamadaRealizada;
	}

	public void setChamadaRealizada(Date dataChamada) {
		if (dataChamada != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("'Chamada realizada em' dd/MM/yyyy");
			String dataFormatada = sdf.format(dataChamada);
		
			this.chamadaRealizada = dataFormatada;
		} else {
			this.chamadaRealizada = null;
		}
	}
	
	public Boolean getDesabilitaPresenca() {
		return desabilitaPresenca;
	}
	public void setDesabilitaPresenca(Boolean desabilitaPresenca) {
		this.desabilitaPresenca = desabilitaPresenca;
	}

	public Boolean getDesabilitaFaltaJustificada() {
		return desabilitaFaltaJustificada;
	}
	public void setDesabilitaFaltaJustificada(Boolean desabilitaFaltaJustificada) {
		this.desabilitaFaltaJustificada = desabilitaFaltaJustificada;
	}

	public Boolean getDesabilitaObservacao() {
		return desabilitaObservacao;
	}
	public void setDesabilitaObservacao(Boolean desabilitaObservacao) {
		this.desabilitaObservacao = desabilitaObservacao;
	}

	private Date dataHoje() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataHoje = sdf.format(new Date());
		
		try {
			return sdf.parse(dataHoje);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public List<ChamadaWrapper> getListaDePresenca() {
		return listaDePresenca;
	}
	public void setListaDePresenca(List<ChamadaWrapper> listaDePresenca) {
		this.listaDePresenca = listaDePresenca;
	}

	public List<Turma> getListaTurmas() {
		return (List<Turma>) turmaDao.getList();
	}
	public List<Matricula> getListaMatriculas() {
		return matriculaDao.getListaMatriculas(getCodTurma());
	}
	
	public List<Frequencia> getChamadasRealizadas() {
		return chamadasRealizadas;
	}
	public void setChamadasRealizadas(List<Frequencia> chamadasRealizadas) {
		this.chamadasRealizadas = chamadasRealizadas;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void populaListaParaChamada() {
		ArrayList<ChamadaWrapper> list = new ArrayList();
		
		for (Matricula m: getListaMatriculas()) {
			list.add(new ChamadaWrapper(true, false, "", m.getAluno()));
		}
		
		setListaDePresenca(list);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void populaListaChamadaRealizada(Integer codChamada) {
		ArrayList<ChamadaWrapper> list = new ArrayList();
		
		for (FrequenciaAluno fa: frequenciaAlunoDao.getByProperty("fa.codChamada=", codChamada)) {
			Boolean presente = ("s".equals(fa.getPresente())) ? true : false;
			Boolean faltaJustificada = ("s".equals(fa.getFaltaJustificada()) && !presente) ? true : false;
			
			list.add(new ChamadaWrapper(presente, faltaJustificada, fa.getObservacao(), fa.getAluno()));
		}
		
		setListaDePresenca(list);
	}
	
	public void populaChamadasRealizadas(Integer codDiscTurma) {
		List<Frequencia> lista = frequenciaDao.getByProperty("f.codDiscTurma=", codDiscTurma, "f.dataAula DESC");
		
		setChamadasRealizadas(lista);
	}
	
	public String gravarChamada() {
		if (getDataAula().before(new Date())) {
			try {
				Frequencia frequenciaExistente = frequenciaDao.getChamada(getCodDiscTurma(), getDataAula());
	
				if (frequenciaExistente == null) {
					Frequencia frequencia = new Frequencia();
					frequencia.setCodDiscTurma(getCodDiscTurma());
					frequencia.setDataAula(getDataAula());
					frequencia.setDataChamada(new Date());
					frequencia.setCodUsuario(usuarioLogado.getCodUsuario());
					
					frequenciaDao.insert(frequencia);
					
					if (frequencia.getCodChamada() != null) {
						for (ChamadaWrapper c: getListaDePresenca()) {
							String presente = (c.estaPresente) ? "s" : "n";
							String faltaJustificada = (c.faltaJustificada && !c.estaPresente) ? "s" : "n";
							
							frequenciaAlunoDao.insert(new FrequenciaAluno(c.aluno.getCodAluno(), frequencia.getCodChamada(), presente, faltaJustificada, c.observacao));
						}
						
						//Ativa/desativa campos
						acessibilidadeCampos(frequencia.getCodUsuario(), dataHoje());
						
						populaListaChamadaRealizada(frequencia.getCodChamada());
						
						populaChamadasRealizadas(frequencia.getCodDiscTurma());
						
						Message.successMessage("Chamada gravada com sucesso");
					} else {
						Message.errorMessage("Houve um erro ao gravar chamada");
					}
				} else {
					//Garante que somente pessoas autorizadas podem editar a chamada
					if ("a".equals(usuarioLogado.getTipo()) || "s".equals(usuarioLogado.getTipo()) || usuarioLogado.getCodUsuario().equals(frequenciaExistente.getCodUsuario())) {
						
						for (ChamadaWrapper c: getListaDePresenca()) {
							String presente = (c.estaPresente) ? "s" : "n";
							String faltaJustificada = (c.faltaJustificada && !c.estaPresente) ? "s" : "n";
							
							frequenciaAlunoDao.update(new FrequenciaAluno(c.aluno.getCodAluno(), frequenciaExistente.getCodChamada(), presente, faltaJustificada, c.observacao));
						}
	
						populaListaChamadaRealizada(frequenciaExistente.getCodChamada());
						
						Message.successMessage("Chamada editada com sucesso");
					}
				}
			} catch (Exception e) {
				Message.errorMessage(e.getMessage());
			}
		} else {
			Message.warningMessage("Não é permitido fazer chamadas para datas futuras");
		}
		
		return "";
	}
	
	public List<DiscTurma> getListaDisciplinasDaTurma() {
		return discTurmaDao.getListaDisciplinasDaTurma(getCodTurma());
	}
	
	public void changeListener(ValueChangeEvent e) {
		if ("filtroTurma".equals(e.getComponent().getId())) {
			setCodTurma((e.getNewValue() != null) ? Integer.valueOf(e.getNewValue().toString()) : null);
			setCodDiscTurma(null);
			
			setListaDePresenca(null);
			populaListaChamadaRealizada(null);
			populaChamadasRealizadas(null);
			
			return;
			
		} else if ("filtroDisciplina".equals(e.getComponent().getId())) {
			setCodDiscTurma((e.getNewValue() != null) ? Integer.valueOf(e.getNewValue().toString()) : null);
			
			populaChamadasRealizadas(getCodDiscTurma());
		} else if ("filtroData".equals(e.getComponent().getId())) {
			setDataAula((e.getNewValue() != null) ? (Date) e.getNewValue() : null);
			
			if (getCodTurma() == null || getCodDiscTurma() == null) {
				setListaDePresenca(null);
				populaListaChamadaRealizada(null);
				populaChamadasRealizadas(null);
				
				return;
			}
		}
		
		Frequencia frequenciaExistente = frequenciaDao.getChamada(getCodDiscTurma(), getDataAula());

		if (frequenciaExistente == null) {
			//Ativa/desativa campos
			acessibilidadeCampos(null, null);
			
			//Esconde mensagem chamada realizada
			setChamadaRealizada(null);
			
			populaListaParaChamada();
		} else {
			//Ativa/desativa campos
			acessibilidadeCampos(frequenciaExistente.getCodUsuario(), frequenciaExistente.getDataChamada());
			
			//Mostra mensagem chamada realizada
			setChamadaRealizada(frequenciaExistente.getDataChamada());
			
			populaListaChamadaRealizada(frequenciaExistente.getCodChamada());
		}
	}
	
	private void acessibilidadeCampos(Integer codUsuarioChamada, Date dataChamada) {
		if (codUsuarioChamada != null && dataChamada != null) {
			/*Usuários "administrador" e "supervisor" podem editar a chamada quando quiserem
			 * Usuário padrão, pode editar presença chamada feita por ele, desde que no mesmo dia
			 * Justificativas pode ser feito pelo usuário que fez a chamada sem restrições de data
			 * */
			if ("a".equals(usuarioLogado.getTipo()) || "s".equals(usuarioLogado.getTipo()) || usuarioLogado.getCodUsuario().equals(codUsuarioChamada)) {
				
				if (usuarioLogado.getCodUsuario().equals(codUsuarioChamada) && !dataHoje().equals(dataChamada)) {
					setDesabilitaPresenca(true);
				} else {
					setDesabilitaPresenca(false);
				}
				
				setDesabilitaFaltaJustificada(false);
				setDesabilitaObservacao(false);
				
			} else {
				setDesabilitaPresenca(true);
				setDesabilitaFaltaJustificada(true);
				setDesabilitaObservacao(true);
			}
		} else {
			setDesabilitaPresenca(false);
			setDesabilitaFaltaJustificada(false);
			setDesabilitaObservacao(false);
		}
	}
	
	public static class ChamadaWrapper implements Serializable {
		private static final long serialVersionUID = 3159433023760694837L;
		
		private Boolean estaPresente;
		private Boolean faltaJustificada;
		private String observacao;
		private Aluno aluno;
		
		public ChamadaWrapper(Boolean estaPresente, Boolean faltaJustificada, String observacao, Aluno aluno) {
			this.estaPresente = estaPresente;
			this.faltaJustificada = faltaJustificada;
			this.observacao = observacao;
			this.aluno = aluno;
		}
		
		public Boolean getEstaPresente() {
			return estaPresente;
		}
		public void setEstaPresente(Boolean estaPresente) {
			this.estaPresente = estaPresente;
		}
		
		public Boolean getFaltaJustificada() {
			return faltaJustificada;
		}
		public void setFaltaJustificada(Boolean faltaJustificada) {
			this.faltaJustificada = faltaJustificada;
		}

		public String getObservacao() {
			return observacao;
		}
		public void setObservacao(String observacao) {
			this.observacao = observacao;
		}

		public Aluno getAluno() {
			return aluno;
		}
		public void setAluno(Aluno aluno) {
			this.aluno = aluno;
		}
	}
}
