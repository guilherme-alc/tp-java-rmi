package com.tpemailrmi.tpjavarmi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tpemailrmi.tpjavarmi.comum.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CadastroController {
    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoEmail;

    @FXML
    private TextField campoSenha;

    private static final String CAMINHO_ARQUIVO = "usuarios.json";

    @FXML
    private Label labelFeedback;

    @FXML
    private void cadastrarUsuario() {
        String nome = campoNome.getText();
        String email = campoEmail.getText();
        String senha = campoSenha.getText();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            labelFeedback.setText("Preencha todos os campos!");
            labelFeedback.setStyle("-fx-text-fill: red;");
            return;
        }

        Usuario novoUsuario = new Usuario(nome, email, senha);

        try {
            // Lê usuários existentes
            List<Usuario> usuarios = new ArrayList<>();
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();

            try (Reader reader = new FileReader(CAMINHO_ARQUIVO)) {
                usuarios = gson.fromJson(reader, tipoLista);
                if (usuarios == null) usuarios = new ArrayList<>();
            } catch (Exception e) {
                // Arquivo não existe ainda, tudo bem
            }

            // Adiciona novo usuário
            usuarios.add(novoUsuario);

            // Escreve tudo de volta no arquivo
            try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
                gson.toJson(usuarios, writer);
            }

            labelFeedback.setText("Usuário cadastrado com sucesso!");
            labelFeedback.setStyle("-fx-text-fill: green;");
            campoNome.clear();
            campoEmail.clear();
            campoSenha.clear();

            System.out.println("Usuário cadastrado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            labelFeedback.setText("Erro ao salvar o usuário.");
            labelFeedback.setStyle("-fx-text-fill: red;");
        }
    }
}
