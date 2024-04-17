package com.dionissa.compactkeep2.views;

import com.dionissa.compactkeep2.model.Client;
import com.dionissa.compactkeep2.services.ClientService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("manage-clients")
public class ManageClientsView extends VerticalLayout {

    private final ClientService clientService;

    private final TextField nameField = new TextField("Nome");
    private final TextField phoneNumberField = new TextField("Telefone");
    private final TextField notesField = new TextField("Notas");

    private final Button addButton = new Button("Adicionar Cliente");
    private final Button homeButton = new Button("Ver clientes");

    @Autowired
    public ManageClientsView(ClientService clientService) {

        homeButton.addClickListener(e -> {
            UI.getCurrent().navigate("clients");
        });

        this.clientService = clientService;

        configureFields();
        configureButtons();
        add(nameField, phoneNumberField, notesField, addButton, homeButton);
    }

    private void configureFields() {
        nameField.setPlaceholder("Nome*");
        phoneNumberField.setPlaceholder("Telefone*");
        notesField.setPlaceholder("Informação Adicional");
    }

    private void configureButtons() {
        addButton.addClickListener(event -> addClient());
    }

    private void addClient() {
        String name = nameField.getValue();
        String phoneNumber = phoneNumberField.getValue();
        String notes = notesField.getValue();

        if (name.isEmpty() || phoneNumber.isEmpty()) {
            Notification.show("Nome e Telefone são Obrigatórios!");
            return;
        }

        Client newClient = new Client();
        newClient.setName(name);
        newClient.setPhoneNumber(phoneNumber);
        newClient.setNotes(notes);

        clientService.addClient(newClient);
        Notification.show("Cliente adicionado com Sucesso");
    }

}
