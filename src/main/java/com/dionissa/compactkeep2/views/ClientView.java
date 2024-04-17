package com.dionissa.compactkeep2.views;

import com.dionissa.compactkeep2.model.Client;
import com.dionissa.compactkeep2.services.ClientService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("clients")
public class ClientView extends VerticalLayout {

    private final ClientService clientService;

    private final Grid<Client> grid = new Grid<>(Client.class);
    private final TextField nameField = new TextField("Nome");
    private final TextField phoneNumberField = new TextField("Telefone");
    private final TextField notesField = new TextField("Notas");
    Button addButton = new Button("Adicionar clientes");

    public ClientView(ClientService clientService) {
        this.clientService = clientService;
        configureGrid();
        configureSearchFields();
        updateGrid("", "", "");

        addButton.addClickListener(e -> {
            // Navega para a rota "clients"
            UI.getCurrent().navigate("manage-clients");
        });

        add(nameField, phoneNumberField, notesField, grid, addButton);
    }

    private void configureGrid() {
        grid.setColumns("name", "phoneNumber", "notes");
        grid.getColumnByKey("name").setHeader("Nome");
        grid.getColumnByKey("phoneNumber").setHeader("Telefone");
        grid.getColumnByKey("notes").setHeader("Informações Adicionais");
        grid.addComponentColumn(client -> {
            Button deleteButton = new Button("Excluir", event -> {
                confirmAndDelete(client);
            });
            return deleteButton;
        }).setHeader("Ações");
    }

    private void confirmAndDelete(Client client) {
        Dialog confirmDialog = new Dialog();
        confirmDialog.setCloseOnEsc(false);
        confirmDialog.setCloseOnOutsideClick(false);

        Span messageSpan = new Span("Tem certeza que deseja excluir o cliente " + client.getName() + "?");
        Button confirmButton = new Button("Confirmar", event -> {
            clientService.deleteClient(client);
            grid.setItems(clientService.findAllClients());
            confirmDialog.close();
        });
        Button cancelButton = new Button("Cancelar", event -> confirmDialog.close());

        confirmDialog.add(messageSpan);
        confirmDialog.add(confirmButton);
        confirmDialog.add(cancelButton);

        confirmDialog.open();
    }

    private void configureSearchFields() {
        nameField.addValueChangeListener(
                event -> updateGrid(event.getValue(), phoneNumberField.getValue(), notesField.getValue()));
        phoneNumberField.addValueChangeListener(
                event -> updateGrid(nameField.getValue(), event.getValue(), notesField.getValue()));
        notesField.addValueChangeListener(
                event -> updateGrid(nameField.getValue(), phoneNumberField.getValue(), event.getValue()));
    }

    private void updateGrid(String name, String phoneNumber, String notes) {
        grid.setItems(clientService.findClientsByNameAndPhoneNumberAndNotes(name, phoneNumber, notes));
    }
}
