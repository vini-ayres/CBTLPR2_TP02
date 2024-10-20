import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FormularioAluno extends JFrame {
    private JTextField nomeField, idadeField, enderecoField;
    private List<Aluno> listaAlunos;

    public FormularioAluno() {
        // Inicializando a lista de alunos
        listaAlunos = new ArrayList<>();

        // Configuração da janela
        setTitle("Cadastro de Alunos");
        setSize(400, 180);  // Ajuste para 400x180
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel superior com GridLayout 3x2 (para os campos de entrada)
        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new GridLayout(3, 2, 10, 10));  // hgap e vgap de 10

        // Campos de entrada
        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();
        JLabel idadeLabel = new JLabel("Idade:");
        idadeField = new JTextField();
        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoField = new JTextField();

        // Adicionando os componentes ao painel superior
        painelSuperior.add(nomeLabel);
        painelSuperior.add(nomeField);
        painelSuperior.add(idadeLabel);
        painelSuperior.add(idadeField);
        painelSuperior.add(enderecoLabel);
        painelSuperior.add(enderecoField);

        // Painel inferior com GridLayout para os botões (1x4)
        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new GridLayout(1, 4, 10, 0));  // hgap de 10 entre os botões

        // Botões
        JButton okButton = new JButton("Ok");
        JButton limparButton = new JButton("Limpar");
        JButton mostrarButton = new JButton("Mostrar");
        JButton sairButton = new JButton("Sair");

        // Adicionando os botões ao painel inferior
        painelInferior.add(okButton);
        painelInferior.add(limparButton);
        painelInferior.add(mostrarButton);
        painelInferior.add(sairButton);

        // Adicionando painéis à janela principal
        add(painelSuperior, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        // Configuração de eventos para os botões
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAluno();
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparFormulario();
            }
        });

        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAlunos();
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // Método para cadastrar o aluno e armazená-lo na lista
    private void cadastrarAluno() {
        String nome = nomeField.getText();
        int idade;
        try {
            idade = Integer.parseInt(idadeField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Idade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String endereco = enderecoField.getText();

        // Gerando UUID para o aluno
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setIdade(idade);
        aluno.setEndereco(endereco);
        aluno.setUuid(UUID.randomUUID());

        // Adicionando o aluno à lista
        listaAlunos.add(aluno);

        // Exibindo uma mensagem de confirmação
        JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");

        // Limpando o formulário após cadastro
        limparFormulario();
    }

    // Método para limpar o formulário
    private void limparFormulario() {
        nomeField.setText("");
        idadeField.setText("");
        enderecoField.setText("");
    }

    // Método para mostrar os IDs e nomes dos alunos cadastrados em um pop-up
    private void mostrarAlunos() {
        if (listaAlunos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum aluno cadastrado.");
            return;
        }

        StringBuilder alunosInfo = new StringBuilder();
        for (Aluno aluno : listaAlunos) {
            // Adicionando o UUID e o nome de cada aluno à string
            alunosInfo.append("ID: ").append(aluno.getUuid())
                      .append(" - Nome: ").append(aluno.getNome())
                      .append("\n");
        }

        // Exibindo a lista de alunos cadastrados no JOptionPane
        JOptionPane.showMessageDialog(this, alunosInfo.toString(), "Alunos Cadastrados", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Executando o formulário
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormularioAluno().setVisible(true);
            }
        });
    }
}