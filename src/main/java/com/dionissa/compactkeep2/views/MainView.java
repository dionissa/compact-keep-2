package com.dionissa.compactkeep2.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.component.UI;

@SpringComponent
@UIScope
@Route("")
public class MainView extends VerticalLayout implements RouterLayout {

    public MainView() {
        // Define a mensagem de boas-vindas
        H1 welcomeMessage = new H1("Bem-vindo ao Compact Keep 2!");

        // Define os botões Iniciar Sistema e Ajuda
        Button iniciarSistemaButton = new Button("Iniciar Sistema");
        Button editButton = new Button("Adicionar clientes");

        // Adiciona a função de redirecionamento ao botão Iniciar Sistema
        iniciarSistemaButton.addClickListener(e -> {
            // Navega para a rota "clients"
            UI.getCurrent().navigate("clients");
        });

        editButton.addClickListener(e -> {
            UI.getCurrent().navigate("manage-clients");
        });

        // Adiciona informações sobre o criador do aplicativo no rodapé
        Div creatorInfo = new Div();
        creatorInfo.setText("App desenvolvido por Rodrigo Dionissa");

        // Adiciona os componentes à layout vertical
        add(welcomeMessage, iniciarSistemaButton, editButton, creatorInfo);

        // Define o alinhamento dos componentes
        setAlignItems(Alignment.CENTER);
    }
}
