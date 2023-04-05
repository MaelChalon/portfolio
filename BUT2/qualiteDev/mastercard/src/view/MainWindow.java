package view;

import model.Card;
import model.CardList;
import model.FiftyTwoCards;
import model.WereWolf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class MainWindow extends JFrame {
    CardList cardList;
    JPanel addPanel;
    JPanel listCardPanel;

    public MainWindow(CardList list){
        super("MasterCard");
        this.cardList = list;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(tailleMoniteur);

        setVisible(true);

        JPanel contentPane = (JPanel) getContentPane();

        addPanel = new JPanel();
        listCardPanel = new JPanel();

        initAddPanel();
        initListCardPanel();

        JSplitPane rightSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, addPanel, listCardPanel);
        rightSplitPane.setResizeWeight( 0.2 );

        contentPane.add(rightSplitPane);
        contentPane.revalidate();
    }

    private void initAddPanel(){
        JTextField nom = new JTextField();
        nom.setText("entrer le nom");
        nom.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                nom.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(Objects.equals(nom.getText(), "")){
                    nom.setText("entrer le nom");
                }
            }
        });

        JComboBox<String> type = new JComboBox<>();
        type.addItem("Selectionner un Item");
        for(Card.TypeEnum t : Card.TypeEnum.values()){
            type.addItem(t.toString());
        }

        JPanel specificCardAttribute = new JPanel();

        type.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String) type.getSelectedItem();
                specificCardAttribute.removeAll();
                switch (Objects.requireNonNull(selectedItem)){
                    case "WERE_WOLF" -> {
                        JTextField description = new JTextField();
                        description.setText("entrer une description");
                        description.addFocusListener(new FocusListener() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                description.setText("");
                            }

                            @Override
                            public void focusLost(FocusEvent e) {
                                if (Objects.equals(description.getText(), "")) {
                                    description.setText("entrer une description");
                                }
                            }
                        });

                        JComboBox<String> listFaction = new JComboBox<>();
                        for(WereWolf.FactionsEnum f : WereWolf.FactionsEnum.values()){
                            listFaction.addItem(f.toString());
                        }

                        Button addFaction = new Button("ajouter une faction");
                        addFaction.addActionListener(e1 -> {
                            JComboBox<String> listFaction1 = new JComboBox<>();
                            for(WereWolf.FactionsEnum f : WereWolf.FactionsEnum.values()){
                                listFaction1.addItem(f.toString());
                            }
                            specificCardAttribute.add(listFaction1);
                            specificCardAttribute.revalidate();
                        });

                        specificCardAttribute.add(description);
                        specificCardAttribute.add(listFaction);
                        specificCardAttribute.add(addFaction);

                    }
                    case "FIFTY_TWO_CARD" -> {
                        JComboBox<String> suit = new JComboBox<>();
                        JComboBox<String> rank = new JComboBox<>();
                        for (FiftyTwoCards.Suit s : FiftyTwoCards.Suit.values()) {
                            suit.addItem(s.toString());
                        }
                        for (FiftyTwoCards.Rank r : FiftyTwoCards.Rank.values()) {
                            rank.addItem(r.toString());
                        }
                        specificCardAttribute.add(suit);
                        specificCardAttribute.add(rank);
                    }
                    default -> {

                    }
                }
                specificCardAttribute.revalidate();
            }
        });

        addPanel.add(nom);
        addPanel.add(type);
        addPanel.add(specificCardAttribute);

        JButton button = new JButton();
        button.setText("ajouter");
        button.addActionListener(e -> {
            System.out.println("ca marche");

            switch (type.getItemAt(type.getSelectedIndex())){
                case "WERE_WOLF":
                    String nomCard = nom.getText();
                    String desciptionCard = null;
                    for(Component c : specificCardAttribute.getComponents()){
                        if(c.getClass() == JTextField.class){
                            desciptionCard = ((JTextField) c).getText();
                        }
                    }
                    if(desciptionCard != null){
                        cardList.addCard(new WereWolf(nomCard, desciptionCard));

                    }
                    break;
                case "FIFTY_TWO_CARD":

                    break;
                default:
                    break;
            }

        });

        addPanel.add(button);
    }

    private void initListCardPanel(){

    }
}
