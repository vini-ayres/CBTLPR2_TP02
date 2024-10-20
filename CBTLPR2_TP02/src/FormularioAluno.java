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
        listaAlunos = new ArrayList<>();

        setTitle("Cadastro de Alunos");
        setSize(400, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();
        JLabel idadeLabel = new JLabel("Idade:");
        idadeField = new JTextField();
        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoField = new JTextField();

        painelSuperior.add(nomeLabel);
        painelSuperior.add(nomeField);
        painelSuperior.add(idadeLabel);
        painelSuperior.add(idadeField);
        painelSuperior.add(enderecoLabel);
        painelSuperior.add(enderecoField);

        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new GridLayout(1, 4, 10, 0));

        JButton okButton = new JButton("Ok");
        JButton limparButton = new JButton("Limpar");
        JButton mostrarButton = new JButton("Mostrar");
        JButton sairButton = new JButton("Sair");

        painelInferior.add(okButton);
        painelInferior.add(limparButton);
        painelInferior.add(mostrarButton);
        painelInferior.add(sairButton);

        add(painelSuperior, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

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

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setIdade(idade);
        aluno.setEndereco(endereco);
        aluno.setUuid(UUID.randomUUID());

        listaAlunos.add(aluno);

        JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");

        limparFormulario();
    }

    private void limparFormulario() {
        nomeField.setText("");
        idadeField.setText("");
        enderecoField.setText("");
    }

    private void mostrarAlunos() {
        if (listaAlunos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum aluno cadastrado.");
            return;
        }

        StringBuilder alunosInfo = new StringBuilder();
        for (Aluno aluno : listaAlunos) {
            alunosInfo.append("ID: ").append(aluno.getUuid())
                      .append(" - Nome: ").append(aluno.getNome())
                      .append("\n");
        }

        JOptionPane.showMessageDialog(this, alunosInfo.toString(), "Alunos Cadastrados", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormularioAluno().setVisible(true);
            }
        });
    }
}