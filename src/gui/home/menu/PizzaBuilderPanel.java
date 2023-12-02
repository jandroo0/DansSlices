package gui.home.menu;

import config.Config;
import customComponents.*;
import model.Ingredient;
import model.MenuItem;
import model.PrebuiltPizza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

// JPanel subclass for a pizza builder GUI
public class PizzaBuilderPanel extends JPanel {

    // Components for the pizza builder panel
    private final CustomMenuListComponent availableIngredientsList;
    private final CustomMenuListComponent selectedIngredientsList;
    private final CustomButton removePizzaButton;
    private final CustomMenuListComponent pizzasList;
    private final CustomPlaceholderField pizzaNameField;
    private final CustomPlaceholderField pizzaPriceField;
    private final CustomButton addButton;
    private final CustomButton removeButton;
    private final CustomButton savePizzaButton;
    private LinkedList<Ingredient> originalIngredientsList;

    private CustomLabel titleLabel;
    private CustomPanel titlePanel;
    private CustomPanel contentsPanel;
    private CustomPanel ingredientsPanel;
    private CustomPanel pizzaPanel;

    // Constructor for PizzaBuilderPanel
    public PizzaBuilderPanel() {
        setLayout(new BorderLayout());
        setBackground(Config.getBackgroundColor());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Config.getTextColor(), 2, true)));

        // Initialize panels and components
        titlePanel = new CustomPanel();
        contentsPanel = new CustomPanel();
        ingredientsPanel = new CustomPanel();
        pizzaPanel = new CustomPanel();

        titleLabel = new CustomLabel("Pizza Builder", 30);

        availableIngredientsList = new CustomMenuListComponent(13, new Dimension(220, 160));
        // Set border for the available ingredients list
        availableIngredientsList.setBorder(BorderFactory.createLineBorder(Config.getButtonBackgroundColor(), 2, true));

        selectedIngredientsList = new CustomMenuListComponent(13, new Dimension(220, 160));
        // Set border for the selected ingredients list
        selectedIngredientsList.setBorder(BorderFactory.createLineBorder(Config.getButtonBackgroundColor(), 2, true));

        // Set special format display for both ingredient lists
        availableIngredientsList.setDisplayInSpecialFormat(true);
        selectedIngredientsList.setDisplayInSpecialFormat(true);

        pizzasList = new CustomMenuListComponent(13, new Dimension(150, 100));
        // Set border for the pizzas list
        pizzasList.setBorder(BorderFactory.createLineBorder(Config.getButtonBackgroundColor(), 2, true));

        // Initialize buttons and placeholder fields
        addButton = new CustomButton("Add", Config.getTextFont(16), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));

        removeButton = new CustomButton("Remove", Config.getTextFont(16), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));

        savePizzaButton = new CustomButton("BUILD", Config.getTextFont(16), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));

        removePizzaButton = new CustomButton("Remove", Config.getTextFont(14), Config.getTextColor(),
                Config.getButtonBackgroundColor(), Config.getButtonHoverColor(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8));

        pizzaNameField = new CustomPlaceholderField("NEW PIZZA", 120, 24, 16);
        pizzaPriceField = new CustomPlaceholderField("$", 50, 24, 16);

        // Layout components on the panel
        layoutComponents();

        // Add components to the panel
        add(contentsPanel, BorderLayout.CENTER);

        // Add action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add selected ingredient to the selected list and remove it from available list
                Ingredient selectedIngredient = (Ingredient) availableIngredientsList.getSelectedItem();
                if (selectedIngredient != null) {
                    selectedIngredientsList.addItem(selectedIngredient);
                    availableIngredientsList.removeSelectedItem();
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add selected ingredient back to available list and remove it from the selected list
                Ingredient selectedIngredient = (Ingredient) selectedIngredientsList.getSelectedItem();
                if (selectedIngredient != null) {
                    availableIngredientsList.addItem(selectedIngredient);
                    selectedIngredientsList.removeSelectedItem();
                }
            }
        });

        removePizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove selected pizza from the pizzas list
                PrebuiltPizza selectedPizza = (PrebuiltPizza) pizzasList.getSelectedItem();
                if (selectedPizza != null) {
                    pizzasList.removeSelectedItem();
                    // Notify listener about the removal event (commented out for reference)
                    // editMenuListener.removePizzaEvent(selectedPizza);
                }
            }
        });

        // Apply styling immediately
        applyStyling();
    }

    // Layout components on the panel
    private void layoutComponents() {
        // Set up title panel
        titlePanel.add(titleLabel);

        // Constraints for GridBagLayout
        GridBagConstraints gc = new GridBagConstraints();

        // Set up ingredients panel with available and selected ingredient lists, and add/remove buttons
        ingredientsPanel.setLayout(new GridBagLayout());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 10, 10);
        ingredientsPanel.add(availableIngredientsList, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 10, 0);
        ingredientsPanel.add(selectedIngredientsList, gc);
        gc.gridx = 0;
        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 10);
        ingredientsPanel.add(addButton, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 10, 0, 0);
        ingredientsPanel.add(removeButton, gc);

        // Set up pizza panel with pizzas list, remove pizza button, pizza name, price, and build button
        pizzaPanel.setLayout(new GridBagLayout());
        pizzaPanel.setBorder(BorderFactory.createEmptyBorder());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.insets = new Insets(0, 0, 10, 0);
        pizzaPanel.add(pizzasList, gc);
        gc.gridy++;
        pizzaPanel.add(removePizzaButton, gc);
        gc.gridy++;
        gc.gridwidth = 1;
        pizzaPanel.add(pizzaNameField, gc);
        gc.gridx++;
        gc.insets = new Insets(0, 5, 10, 0);
        pizzaPanel.add(pizzaPriceField, gc);
        gc.gridx = 0;
        gc.gridy++;
        gc.gridwidth = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        pizzaPanel.add(savePizzaButton, gc);

        // Set up contents panel with pizza panel and ingredients panel
        GridLayout gridLayout = new GridLayout(2, 1);
        gridLayout.setVgap(0);
        contentsPanel.setLayout(gridLayout);
        contentsPanel.setBorder(BorderFactory.createEmptyBorder());
        contentsPanel.add(pizzaPanel);
        contentsPanel.add(ingredientsPanel);

        // Add title panel and contents panel to the main panel
        add(titlePanel, BorderLayout.NORTH);
        add(contentsPanel, BorderLayout.CENTER);
    }

    // Apply styling (empty for now, may be implemented later)
    private void applyStyling() {
        // Add styling code here if needed
    }

    // Set the available ingredients for the builder panel
    public void setAvailableIngredients(LinkedList<Ingredient> ingredients) {
        this.originalIngredientsList = ingredients;
        availableIngredientsList.clearList();
        for (Ingredient ingredient : ingredients) {
            availableIngredientsList.addItem(ingredient);
        }
    }

    // Set the list of prebuilt pizzas for the builder panel
    public void setPizzasList(LinkedList<PrebuiltPizza> pizzas) {
        pizzasList.clearList();
        for (PrebuiltPizza pizza : pizzas) {
            pizzasList.addItem(pizza);
        }
    }

    // Get the selected ingredients from the builder panel
    public LinkedList<MenuItem> getSelectedIngredients() {
        return selectedIngredientsList.getList();
    }

    // Get the list of pizzas from the builder panel
    public LinkedList<MenuItem> getPizzas() {
        return pizzasList.getList();
    }
}
