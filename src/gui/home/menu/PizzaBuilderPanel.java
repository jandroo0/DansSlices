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

public class PizzaBuilderPanel extends JPanel {

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

//    private PizzaBuilderListener pizzaBuilderListener;

    public PizzaBuilderPanel() {
        setLayout(new BorderLayout());
        setBackground(Config.getBackgroundColor());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Config.getTextColor(), 2, true)));

        // panels
        titlePanel = new CustomPanel();
        contentsPanel = new CustomPanel();
        ingredientsPanel = new CustomPanel();
        pizzaPanel = new CustomPanel();

        titleLabel = new CustomLabel("Pizza Builder", 30);

        availableIngredientsList = new CustomMenuListComponent(13, new Dimension(220, 160));
        availableIngredientsList.setBorder(BorderFactory.createLineBorder(Config.getButtonBackgroundColor(), 2, true));

        selectedIngredientsList = new CustomMenuListComponent(13, new Dimension(220, 160));
        selectedIngredientsList.setBorder(BorderFactory.createLineBorder(Config.getButtonBackgroundColor(), 2, true));

        availableIngredientsList.setDisplayInSpecialFormat(true);
        selectedIngredientsList.setDisplayInSpecialFormat(true);

        pizzasList = new CustomMenuListComponent(13, new Dimension(150, 100));
        pizzasList.setBorder(BorderFactory.createLineBorder(Config.getButtonBackgroundColor(), 2, true));

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


        layoutComponents();

        add(contentsPanel, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                PrebuiltPizza selectedPizza = (PrebuiltPizza) pizzasList.getSelectedItem();
                if (selectedPizza != null) {
                    pizzasList.removeSelectedItem();
//                    editMenuListener.removePizzaEvent(selectedPizza);
                }
            }
        });

        applyStyling(); // Apply styling immediately
    }

    private void layoutComponents() {

        // titlePanel
        titlePanel.add(titleLabel);

        // constraints for gridbaglayout
        GridBagConstraints gc = new GridBagConstraints();

        // ingredients panel
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


        // pizza panel
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


        // contents panel
        GridLayout gridLayout = new GridLayout(2, 1);
        gridLayout.setVgap(0);
        contentsPanel.setLayout(gridLayout);
        contentsPanel.setBorder(BorderFactory.createEmptyBorder());
        contentsPanel.add(pizzaPanel);
        contentsPanel.add(ingredientsPanel);


        add(titlePanel, BorderLayout.NORTH);
        add(contentsPanel, BorderLayout.CENTER);

    }


    private void applyStyling() {

    }

    public void setAvailableIngredients(LinkedList<Ingredient> ingredients) {
        this.originalIngredientsList = ingredients;
        availableIngredientsList.clearList();
        for (Ingredient ingredient : ingredients) {
            availableIngredientsList.addItem(ingredient);
        }
    }

    public void setPizzasList(LinkedList<PrebuiltPizza> pizzas) {
        pizzasList.clearList();
        for (PrebuiltPizza pizza : pizzas) {
            pizzasList.addItem(pizza);
        }
    }

    public LinkedList<MenuItem> getSelectedIngredients() {
        return selectedIngredientsList.getList();
    }

    public LinkedList<MenuItem> getPizzas() {
        return pizzasList.getList();
    }


//    public void setEditMenuListener(EditMenuListener listener) {
//        this.editMenuListener = listener;
//    }
}