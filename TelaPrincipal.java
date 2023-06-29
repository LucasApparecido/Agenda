import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class TelaPrincipal extends JFrame {

	// Dimensões da tela
	public static final int largura = 1024;
	public static final int altura = 500;

	// Armazenamento dos dados
	private Armazenamento arquivoDados;

	// Obte os contatos do arquivo de armazenamento
	private Iterator<ItemParaArmazenar> dadosIterator;

	// Construtor do Contato
	private PessoaAgenda pessoa;

	// Painel Principal
	private JPanel jpCentral;

	// Painel de dados pessoais
	private JPanel jpTituloDadosPessoais;
	private JPanel jpDados;
	private JLabel jlDados;
	private JPanel jpDadosWestVertical;

	// Campo código
	private JPanel jpCodigo;
	private JLabel jlCodigo;
	private JTextField jtfCodigo;

	// Campo nome
	private JPanel jpNome;
	private JLabel jlNome;
	private JTextField jtfNome;

	// Campo endereço
	private JPanel jpEndereco;
	private JLabel jlEndereco;
	private JTextField jtfEndereco;

	// Campo telefone
	private JPanel jpTelefone;
	private JLabel jlTelefone;
	private JTextField jtfTelefone;

	// Campo anotações
	private JPanel jpTituloAnotacoes;
	private JPanel jpAnotacoes;
	private JLabel jlAnotacoes;
	private JTextArea jtaAnotacoes;
	private JScrollPane jspAnotacoes;

	// Botoes
	private JPanel jpNavegadores;
	private JPanel jpCentralBotoes;
	private JPanel jpBotoes;
	private JButton jbInserir;
	private JButton jbConsultar;
	private JButton jbLimpar;
	private JButton jbAlterar;
	private JButton jbExcluir;
	private JButton jbAnterior;
	private JButton jbProximo;
	private JButton jbFimNavegacao;

	// Puxar a ação dos botões
	private ActionListener actionListener;

	// Tabela
	private JPanel jpTabelaContatos;
	private JPanel jpTituloTabelaContatos;
	private JLabel jlTabelaContatos;
	private JTable jtTabelaContatos;
	private DefaultTableModel dtmModeloTabelaContatos;
	private JScrollPane jspTabelaContatos;

	// Informações do usuario para controle
	private String codigo;
	private String nome;
	private String endereco;
	private String telefone;
	private String anotacoes;

	// Bordas
	private int borderWidth = 1;
	private int padding = 1;
	private Color borderColor = Color.GRAY;

	// Variavel para coletar a linha selecionada
	private int linhaSelecionada = -1;

	private boolean consultaFeita = false;

	// Construtor da tela principal
	public TelaPrincipal() {

		setSize(TelaPrincipal.largura, TelaPrincipal.altura);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Agenda");
		setResizable(false);
		setLocation(100, 100);

		getContentPane().setLayout(new BorderLayout());
		setIconImage(new ImageIcon("./logo.png").getImage());

		actionListener = new ActionListenerBotoes();
		arquivoDados = new Armazenamento("AGENDA.dat");
		montarLayout();
		setVisible(true);

	}

	// Função para montar o layout da tela
	private void montarLayout() {

		// adiciona o banner
		JLabel jlBanner = new JLabel(new ImageIcon("./banner.png"));

		getContentPane().add("North", jlBanner);

		// adiciona o painel central
		jpCentral = new JPanel();
		jpCentral.setLayout(new BorderLayout());

		// adiciona o painel de dados
		jpDados = new JPanel(); // Border 2
		jpDados.setLayout(new BorderLayout());
		jpCentral.add(jpDados, "West");

		// adiciona o painel dos dados JTEXTFIELD
		jpDadosWestVertical = new JPanel();
		jpDadosWestVertical.setLayout(new BoxLayout(jpDadosWestVertical, BoxLayout.Y_AXIS));

		// adiciona o titulo dos dados
		jlDados = new JLabel("DADOS PESSOAIS");
		jpTituloDadosPessoais = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpTituloDadosPessoais.add(jlDados);
		jpDadosWestVertical.add(jpTituloDadosPessoais, BorderLayout.NORTH);

		// cria o campo codigo
		jpCodigo = new JPanel();
		jlCodigo = new JLabel("Código:");
		jtfCodigo = new JTextField(10);

		// configura o campo codigo
		jpCodigo.setLayout(new FlowLayout());
		jpCodigo.add(jlCodigo);
		jpCodigo.add(jtfCodigo);
		jpDadosWestVertical.add(jpCodigo);

		// cria o campo nome
		jpNome = new JPanel();
		jlNome = new JLabel("Nome:");
		jtfNome = new JTextField(10);

		// configura o campo nome
		jpNome.setLayout(new FlowLayout());
		jpNome.add(jlNome);
		jpNome.add(jtfNome);
		jpDadosWestVertical.add(jpNome);

		// cria o campo endereço
		jpEndereco = new JPanel();
		jlEndereco = new JLabel("Endereço");
		jtfEndereco = new JTextField(10);

		// configura o campo endereço
		jpEndereco.setLayout(new FlowLayout());
		jpEndereco.add(jlEndereco);
		jpEndereco.add(jtfEndereco);
		jpDadosWestVertical.add(jpEndereco);

		// cria o campo telefone
		jpTelefone = new JPanel();
		jlTelefone = new JLabel("Telefone");
		jtfTelefone = new JTextField(10);

		// configura o campo telefone
		jpTelefone.setLayout(new FlowLayout());
		jpTelefone.add(jlTelefone);
		jpTelefone.add(jtfTelefone);
		jpDadosWestVertical.add(jpTelefone);

		// cria uma margem
		jpDadosWestVertical.add(Box.createVerticalStrut(500));

		// cria e configura o campo anotações
		jpAnotacoes = new JPanel();
		jpAnotacoes.setLayout(new BorderLayout());
		jlAnotacoes = new JLabel("ANOTAÇÕES");
		jpTituloAnotacoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpTituloAnotacoes.add(jlAnotacoes);
		jtaAnotacoes = new JTextArea(5, 20);
		jpAnotacoes.add(jpTituloAnotacoes, BorderLayout.NORTH);
		jpAnotacoes.add(jtaAnotacoes);

		// cria e configura o scroll do campo anotações
		jspAnotacoes = new JScrollPane(jtaAnotacoes);
		jspAnotacoes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspAnotacoes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jpAnotacoes.add(jspAnotacoes);

		// adiciona o campo anotações ao painel de dados
		jpDados.add("Center", jpAnotacoes);

		// cria o painel dos botões
		jpBotoes = new JPanel();
		jpBotoes.setLayout(new FlowLayout());

		// cria o botão de inserir
		jbInserir = new JButton("Inserir");
		jbInserir.addActionListener(actionListener);

		// cria o botão de consultar
		jbConsultar = new JButton("Consultar");
		jbConsultar.addActionListener(actionListener);

		// cria o botão de alterar
		jbAlterar = new JButton("Alterar");
		jbAlterar.addActionListener(actionListener);

		// cria o botão de excluir
		jbExcluir = new JButton("Excluir");
		jbExcluir.addActionListener(actionListener);

		// cria o botão de limpar
		jbLimpar = new JButton("Limpar");
		jbLimpar.addActionListener(actionListener);

		// adiciona os botoes a jpBotoes
		jpBotoes.add(jbInserir);
		jpBotoes.add(jbConsultar);
		jpBotoes.add(jbAlterar);
		jpBotoes.add(jbExcluir);
		jpBotoes.add(jbLimpar);

		// Adiciona uma borda ao painel de botoes
		jpBotoes.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(borderColor, borderWidth),
				BorderFactory.createEmptyBorder(padding, padding, padding, padding)));

		// cria o painel dos navegação
		jpNavegadores = new JPanel();
		jpNavegadores.setLayout(new FlowLayout());

		// cria o botão proximo
		jbProximo = new JButton("Próximo");
		jbProximo.addActionListener(actionListener);

		// cria o botão anterior
		jbAnterior = new JButton("Anterior");
		jbAnterior.addActionListener(actionListener);

		// cria o botão fim da navegação
		jbFimNavegacao = new JButton("Fim Navegação");
		jbFimNavegacao.addActionListener(actionListener);

		// adiciona os botões de navegação ao painel de navegação
		jpNavegadores.add(jbAnterior);
		jpNavegadores.add(jbProximo);
		jpNavegadores.add(jbFimNavegacao);

		// desliga o painel de navegação
		jpNavegadores.setVisible(false);

		// Configura um grupo comum para os botões e adiciona os dois paienis ao mesmo
		jpCentralBotoes = new JPanel();
		jpCentralBotoes.setLayout(new BoxLayout(jpCentralBotoes, BoxLayout.Y_AXIS));
		jpCentralBotoes.add(jpNavegadores, BorderLayout.NORTH);
		jpCentralBotoes.add(jpBotoes, BorderLayout.SOUTH);

		// adiciona os botoes ao grupo do formulario
		jpDados.add("South", jpCentralBotoes);

		// adiciona os campos ao grupo do formulario
		jpDados.add("West", jpDadosWestVertical);

		// adiciona o formulario a oeste no painel principal
		jpCentral.add(jpDados, "West");

		// inicia o modelo da tabela 
		criaTabela();

		// cria o painel da tabela
		jpTabelaContatos = new JPanel();
		jpTabelaContatos.setLayout(new BoxLayout(jpTabelaContatos, BoxLayout.Y_AXIS));

		// cria o titulo da tabela
		jlTabelaContatos = new JLabel("LISTA CONTATOS");
		jpTituloTabelaContatos = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpTituloTabelaContatos.add(jlTabelaContatos);
		jpTabelaContatos.add(jpTituloTabelaContatos, BorderLayout.NORTH);

		// cria a tabela
		jtTabelaContatos = new JTable(dtmModeloTabelaContatos);
		jtTabelaContatos.setDefaultRenderer(Object.class, new TabelaRenderer());
		jtTabelaContatos.getSelectionModel().addListSelectionListener(new TabelaSelecaoListener());

		// preenche a tabela
		inicializarTabela();

		// cria o scroll da tabela
		jspTabelaContatos = new JScrollPane(jtTabelaContatos);
		jpTabelaContatos.add(jspTabelaContatos, BorderLayout.CENTER);

		// adiciona a tabela ao painel principal
		jpCentral.add(jpTabelaContatos, "Center");

		// adiciona o painel principal a tela
		getContentPane().add("Center", jpCentral);
	}

	// inicia o modelo da tabela como padrão e barra a edição das linhas da mesma
	private DefaultTableModel criaTabela() {
		dtmModeloTabelaContatos = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};

		// configura as colunas
		dtmModeloTabelaContatos.addColumn("Código");
		dtmModeloTabelaContatos.addColumn("Nome");
		dtmModeloTabelaContatos.addColumn("Endereco");
		dtmModeloTabelaContatos.addColumn("Telefone");
		dtmModeloTabelaContatos.addColumn("Anotações");

		return dtmModeloTabelaContatos;
	}

	/*
	 * Função para armazenar os dados
	 * Ela recebe quantas linhas a tabela tem
	 * Verifica antes de criar a variavel se a tabela possui linhas
	 * Cria um array para armazenar todos os dados poe linha
	 * Enquanto o i for menor do que a quantidade de linhas da tabela:
	 * As variaveis recebem o valor de cada linha/coluna
	 * Salva uma nova pessoa usando o constrututor pessoa
	 * adiciona ao array list a pessoa
	 * Armazena todo o array list no arquivo apagando o anterior e criando um novo
	 */
	private void armazenarDados() {
		int quantasLinhas = dtmModeloTabelaContatos.getRowCount();

		if (quantasLinhas > 0) {
			ArrayList<ItemParaArmazenar> dados = new ArrayList<>();

			for (int i = 0; i < quantasLinhas; i++) {
				codigo = (String) dtmModeloTabelaContatos.getValueAt(i, 0);
				nome = (String) dtmModeloTabelaContatos.getValueAt(i, 1);
				telefone = (String) dtmModeloTabelaContatos.getValueAt(i, 2);
				endereco = (String) dtmModeloTabelaContatos.getValueAt(i, 3);
				anotacoes = (String) dtmModeloTabelaContatos.getValueAt(i, 4);

				pessoa = new PessoaAgenda(codigo, nome, endereco, telefone, anotacoes);
				dados.add(pessoa);
			}

			arquivoDados.armazenar(dados);
		}
	}

	/*
	 * Função para preencher a tabela
	 * Ela limpa a tabela com o comando .setRowCount
	 * Recebe todos os usuarios da tabela
	 * Enquanto tiver usuarios no arquivo ela:
	 * Obtem o proximo usuario
	 * Adiciona o usuario a tabela
	 */
	private void inicializarTabela() {
		dtmModeloTabelaContatos = criaTabela();
		jtTabelaContatos.setModel(dtmModeloTabelaContatos);

		dadosIterator = arquivoDados.obterTodos();
		while (dadosIterator.hasNext()) {
			pessoa = (PessoaAgenda) dadosIterator.next();
			Object[] dados = { pessoa.getCodigo(), pessoa.getNome(), pessoa.getEndereco(), pessoa.getTelefone(),
					pessoa.getAnotacoes() };
			dtmModeloTabelaContatos.addRow(dados);
		}
	}

	/*
	 * Função para buscar o contato na tabela
	 * Ela recebe o termo da consulta
	 * Limpa a tabela
	 * Obtem todos os contatos do arquivo de armazenamento
	 * Enquanto tiver contatos no arquivo:
	 * Obtem o proximo contato
	 * Verifica se o nome ou o codigo do contato é igual ao termo da consulta
	 * Se for igual adiciona o contato a tabela
	 * Se a tabela não tiver linhas:
	 * Chama a função para preencher a tabela
	 * Mostra uma mensagem de erro
	 * Se a tabela tiver linhas:
	 * Mostra os navegadores
	 * Seleciona a primeira linha da tabela
	 */
	private void realizarConsulta(String termoConsulta) {
		dtmModeloTabelaContatos.setRowCount(0); // Limpa a tabela de contatos
		dadosIterator = arquivoDados.obterTodos(); // Obtém todos os contatos do arquivo de armazenamento

		// Realiza a consulta pelo nome ou pelo código do contato
		while (dadosIterator.hasNext()) {
			pessoa = (PessoaAgenda) dadosIterator.next();
			nome = pessoa.getNome();
			codigo = pessoa.getCodigo();

			if (nome.equalsIgnoreCase(termoConsulta) || codigo.equalsIgnoreCase(termoConsulta)) {
				String[] dados = { pessoa.getCodigo(), pessoa.getNome(), pessoa.getEndereco(),
						pessoa.getTelefone(), pessoa.getAnotacoes() };
				dtmModeloTabelaContatos.addRow(dados);
			}
		}
		if (dtmModeloTabelaContatos.getRowCount() == 0) {
			inicializarTabela();
			JOptionPane.showMessageDialog(null, "Nenhum contato encontrado");
		} else {
			jpNavegadores.setVisible(true);
			jtTabelaContatos.setRowSelectionInterval(0, 0);
			consultaFeita = true;
		}
	}

	// Classe para receber se ouve alguma ação de seleção
	public class TabelaSelecaoListener implements ListSelectionListener {
		/*
		 * Função para verificar se alguma linha da tabela foi selecionada
		 * Ela recebe o evento de seleção
		 * A primeira linha verifica se o evento não está em processo de ajuste,
		 * utilizando o método "getValueIsAdjusting()" do objeto "event".
		 * Essa verificação é feita para evitar que o código seja executado várias vezes
		 * durante uma seleção contínua.
		 * A variavel linha selecionada recebe se alguma linha realmente esta
		 * selecionada
		 * Se a linha tiver selecionada
		 * Seta o texto da tabela no formulario para consulta
		 * liga os navegadores
		 */
		public void valueChanged(ListSelectionEvent event) {
			if (!event.getValueIsAdjusting()) {
				linhaSelecionada = jtTabelaContatos.getSelectedRow();
				if (linhaSelecionada != -1) {
					jtfCodigo.setText((String) dtmModeloTabelaContatos.getValueAt(linhaSelecionada, 0));
					jtfNome.setText((String) dtmModeloTabelaContatos.getValueAt(linhaSelecionada, 1));
					jtfTelefone.setText((String) dtmModeloTabelaContatos.getValueAt(linhaSelecionada, 2));
					jtfEndereco.setText((String) dtmModeloTabelaContatos.getValueAt(linhaSelecionada, 3));
					jtaAnotacoes.setText((String) dtmModeloTabelaContatos.getValueAt(linhaSelecionada, 4));
					jpNavegadores.setVisible(true);
				}
			}
		}
	}

	// Classe para receber se ouve alguma ação
	class ActionListenerBotoes implements ActionListener {

		// Variaveis de auxilio
		private int novaLinha;
		private int totalLinhas;

		// função que verifica o tipo de ação
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == jbInserir) {
				inserir();
			}

			if (e.getSource() == jbLimpar) {
				limpar();
			}

			if (e.getSource() == jbConsultar) {
				consultar();
			}

			if (e.getSource() == jbExcluir) {
				excluir();
			}

			if (e.getSource() == jbAlterar) {
				alterar();
			}

			if (e.getSource() == jbAnterior) {
				anterior();
			}

			if (e.getSource() == jbProximo) {
				proximo();
			}

			if (e.getSource() == jbFimNavegacao) {
				fimNavegacao();
			}

		}

		/*
		 * Função da ação de inserir uma linha na tabela
		 * Se não tiver linha selecionada
		 * Obtem os dados do campo nome e telefone
		 * Se o um dos dois campos estiver vazio ele da um aviso informando para o
		 * usuario inserir o nome/telefone
		 * Se os dois dados estiver preenchido ele coleta as informações restantes do
		 * formulari
		 * no codigo ele chama a função da classe GeradorCodigo para criar um novo
		 * codigo para esse contado
		 * e adiociona um P na hora de salvar o codigo
		 * cria um vetor de string para salvar esses dados
		 * adiciona os dados informados a linha da tabela
		 * armazena os dados no arquivo
		 * se tiver linha selecionada é mostrado uma mensagem de erro
		 */
		public void inserir() {
			linhaSelecionada = jtTabelaContatos.getSelectedRow();

			if (linhaSelecionada == -1) {
				nome = jtfNome.getText();
				telefone = jtfTelefone.getText();

				if (nome.equals("") || telefone.equals("")) {
					JOptionPane.showMessageDialog(null, "Nome e telefone não podem ser vazios");
				} else {
					codigo = "P" + Integer.toString(GeradorCodigo.gerarCodigo());
					endereco = jtfEndereco.getText();
					anotacoes = jtaAnotacoes.getText();

					String[] dados = { codigo, nome, telefone, endereco, anotacoes };
					dtmModeloTabelaContatos.addRow(dados);

					pessoa = new PessoaAgenda(codigo, nome, endereco, telefone, anotacoes);
					arquivoDados.inserir(pessoa);

				}
			} else {
				JOptionPane.showMessageDialog(null, "Termine a navegação para inserir um novo contato");
			}
		}

		/*
		 * Função para limpar os campos do formulario
		 * Seta os campos do formulario em branco
		 * Desativa a consulta
		 */
		public void limpar() {
			jtfCodigo.setText("");
			jtfNome.setText("");
			jtfEndereco.setText("");
			jtfTelefone.setText("");
			jtaAnotacoes.setText("");
		}

		/*
		 * Função de consulta
		 * Se o campo codigo e o campo nome estiverem preenchidos
		 * ele retorna informando que so um pode ser usado para consulta
		 * se não ele verifica qual campo é o selecionado e chama a função propria para
		 * realizar uma consulta na tabela
		 * Passando como parametro o campo selecionado
		 */
		public void consultar() {

			if (!jtfCodigo.getText().equalsIgnoreCase("") && !jtfNome.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(null, "Apenas um campo deve ser\npreenchido para a consulta.");
			} else {
				if (!jtfCodigo.getText().equalsIgnoreCase("")) {
					String termoConsulta = jtfCodigo.getText();
					realizarConsulta(termoConsulta);
				} else {
					if (!jtfNome.getText().equalsIgnoreCase("")) {
						String termoConsulta = jtfNome.getText();
						realizarConsulta(termoConsulta);
					}
				}
			}
		}

		/*
		 * Função de alterar uma linha da tabela
		 * coleta qual a linha selecionada
		 * se não tiver linha selecionada ele retorna uma mensagem para ele selecionar
		 * uma linha
		 * se não, ele coleta as informações atuais do formulario
		 * verifica se o nome ou telegone estão vazios
		 * Se estiverem ele retorna uma mensagem de erro
		 * se não ele coleta o restante das informações do formulario
		 * seta o valor de cada informação na linha da tabela selecionada
		 * cria uma nova pessoa
		 * chama a função de alterar a pessoa no arquivo
		 * retorna uma mensagem de sucesso
		 */
		public void alterar() {
			linhaSelecionada = jtTabelaContatos.getSelectedRow();

			if (linhaSelecionada == -1) {
				JOptionPane.showMessageDialog(null, "Nenhum contato selecionado");
			} else {
				nome = jtfNome.getText();
				telefone = jtfTelefone.getText();

				if (nome.equals("") || telefone.equals("")) {
					JOptionPane.showMessageDialog(null, "Nome ou telefone não podem ser vazios");
				} else {
					codigo = jtfCodigo.getText();
					endereco = jtfEndereco.getText();
					anotacoes = jtaAnotacoes.getText();

					dtmModeloTabelaContatos.setValueAt(codigo, linhaSelecionada, 0);
					dtmModeloTabelaContatos.setValueAt(nome, linhaSelecionada, 1);
					dtmModeloTabelaContatos.setValueAt(endereco, linhaSelecionada, 2);
					dtmModeloTabelaContatos.setValueAt(telefone, linhaSelecionada, 3);
					dtmModeloTabelaContatos.setValueAt(anotacoes, linhaSelecionada, 4);

					pessoa = new PessoaAgenda(codigo, nome, endereco, telefone, anotacoes);
					arquivoDados.alterar(pessoa);

					JOptionPane.showMessageDialog(null, "Contato alterado com sucesso");
				}
			}
		}

		/*
		 * Função de exclusão
		 * coleta a linha selecionada atualmente
		 * se a linha estiver selecionada
		 * ele chama uma uma janela de confirmação
		 * se o usuario realmente quiser excluir
		 * remove da tabela a linha secionadachama
		 * chama a função de limpar
		 * armazena os dados no arquivo sobrescrevendo a ultima informação (logo
		 * excluindo)
		 * avisa que foi excluido com sucesso
		 * desativa o painel de navegação
		 * desceleciona a linha
		 * se não tiver linha selecionada ele da uma mensagem de erro
		 */
		public void excluir() {
			linhaSelecionada = jtTabelaContatos.getSelectedRow();

			if (linhaSelecionada != -1) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o contato selecionado?",
						"Excluir Contato",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					dtmModeloTabelaContatos.removeRow(linhaSelecionada);
					limpar();
					armazenarDados();
					JOptionPane.showMessageDialog(null, "Contato excluído com sucesso.");
					jpNavegadores.setVisible(false);
					jtTabelaContatos.clearSelection();
					if(consultaFeita) {
						inicializarTabela();
						consultaFeita = false;
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Nenhum contato selecionado.");
			}
		}

		/*
		 * Função de navegação para tras
		 * obtem a linha selecionada
		 * obtem o total de linhas
		 * realiza um operação ternaria para poder voltar infinitamente (indo pro topo
		 * da tabela)
		 * se o total de linhas for maior que zero ele seleciona a nova linha
		 */
		public void anterior() {
			linhaSelecionada = jtTabelaContatos.getSelectedRow();
			totalLinhas = dtmModeloTabelaContatos.getRowCount();
			novaLinha = (linhaSelecionada == 0) ? totalLinhas - 1 : linhaSelecionada - 1;
			if (totalLinhas > 0) {
				jtTabelaContatos.setRowSelectionInterval(novaLinha, novaLinha);
			}
		}

		/*
		 * Função de navegação para frente
		 * obtem a linha selecionada
		 * obtem o total de linhas
		 * realiza um operação ternaria para poder voltar infinitamente (indo pro inicio
		 * da tabela)
		 * se o total de linhas for maior que zero ele seleciona a nova linha
		 */
		public void proximo() {
			linhaSelecionada = jtTabelaContatos.getSelectedRow();
			totalLinhas = dtmModeloTabelaContatos.getRowCount();
			novaLinha = (linhaSelecionada == totalLinhas - 1) ? 0 : linhaSelecionada + 1;
			if (totalLinhas > 0) {
				jtTabelaContatos.setRowSelectionInterval(novaLinha, novaLinha);
			}
		}

		/*
		 * Função de fim de navegação
		 * desativa o painel de navegação
		 * chama a função de preencher a tabela para reiniciar a mesma caso a consulta
		 * tenha mudado algo dela
		 */
		public void fimNavegacao() {
			jpNavegadores.setVisible(false);
			limpar();
			jtTabelaContatos.clearSelection();
			if (consultaFeita) {
				inicializarTabela();
				consultaFeita = false;
			} 
		}

	}

}

/*
 * caso ele pergunte da equação ternaria ternaria
 * A expressão começa com "(linhaSelecionada == 0)". Isso verifica se a variável
 * "linhaSelecionada" é igual a zero.
 * 
 * Se a condição "(linhaSelecionada == 0)" for verdadeira, o valor atribuído a
 * "novaLinha" será "totalLinhas - 1". Isso significa que, se a linha
 * selecionada for a primeira linha (índice 0), "novaLinha" receberá o valor de
 * "totalLinhas - 1". Isso provavelmente é usado para obter o índice da última
 * linha da tabela.
 * 
 * Se a condição "(linhaSelecionada == 0)" for falsa, o valor atribuído a
 * "novaLinha" será "linhaSelecionada - 1". Isso significa que, se a linha
 * selecionada não for a primeira linha, "novaLinha" receberá o valor de
 * "linhaSelecionada - 1". Isso provavelmente é usado para obter o índice da
 * linha anterior à linha selecionada.
 */
