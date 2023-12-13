import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

public class Deck {
    public String[] availableCards;
    private Player owner;

    private AsyncPopUp display;

    public boolean deckSpawningSelected;

    public String lastButtonPressed;

    private int elixir;

    public Deck(Player owner) {
        lastButtonPressed = "";
        this.owner = owner;
        availableCards = new String[4];
        generateRandomAvailableCards();

        deckSpawningSelected = true;

        display  = new AsyncPopUp(this);

        elixir = 5;
    }



    private void generateRandomAvailableCards() {

        for (int i = 0; i < 4; i++) {
            availableCards[i] = Troop.CARDS[i];
        }
    }

    public void update() {
        if(owner.getName().equals("AI")) useCard(availableCards[0], owner.game.width/2, owner.game.height/4);
        if(deckSpawningSelected) {
            display.showMessage();
        }

        if (owner.game.tick % 168 == 0) elixir++;
        display.showElixir(elixir);
        System.out.println(lastButtonPressed);
    }

    public boolean useCard(String text, int x, int y) {
        for(int i = 0; i < availableCards.length; i++) {
            String t = availableCards[i];
            if(!text.equals(t)) continue;

            int price = Integer.MAX_VALUE;
            //check if enough elixir is present
            if(t.equals("HogRider")) {
                price = HogRider.PRICE;
            }
            else if(t.equals("Skeletons")) {
                price = Skeleton.PRICE;
            }
            else if(t.equals("Cannon")) {
                price = Cannon.PRICE;
            }
            else if(t.equals("Musketeer")) {
                price = Musketeer.PRICE;
            }
            else if(t.equals("IceGolem")) {
                price = IceGolem.PRICE;
            }
            else if(t.equals("EliteBarbarians")) {
                price = EliteBarbarians.PRICE;
            }
            else if(t.equals("Knight")) {
                price = Knight.PRICE;
            }

            if(elixir-price <0 ) return false;

            lastButtonPressed = text;

            String newCard;
            boolean repeat = true;
            loop: do{
                newCard = Troop.CARDS[(int)(Math.random()*Troop.CARDS.length)];

                for(String card: availableCards) {
                    if(card.equals(newCard)) continue loop;
                }
                repeat = false;
            }while(repeat);

            availableCards[i] = newCard;

            elixir-= price;

            display.updatePane();

            owner.spawn(x,y);
        }
        return true;
    }
    public boolean useCard(String text) {
        for(int i = 0; i < availableCards.length; i++) {
            String t = availableCards[i];
            if(!text.equals(t)) continue;

            int price = Integer.MAX_VALUE;
            //check if enough elixir is present
            if(t.equals("HogRider")) {
                price = HogRider.PRICE;
            }
            else if(t.equals("Skeletons")) {
                price = Skeleton.PRICE;
            }
            else if(t.equals("Cannon")) {
                price = Cannon.PRICE;
            }
            else if(t.equals("Musketeer")) {
                price = Musketeer.PRICE;
            }
            else if(t.equals("IceGolem")) {
                price = IceGolem.PRICE;
            }
            else if(t.equals("EliteBarbarians")) {
                price = EliteBarbarians.PRICE;
            }
            else if(t.equals("Knight")) {
                price = Knight.PRICE;
            }

            if(elixir-price <0 ) return false;

            lastButtonPressed = text;

            String newCard;
            boolean repeat = true;
            loop: do{
                newCard = Troop.CARDS[(int)(Math.random()*Troop.CARDS.length)];

                for(String card: availableCards) {
                    if(card.equals(newCard)) continue loop;
                }
                repeat = false;
            }while(repeat);

            availableCards[i] = newCard;

            elixir-= price;

            display.updatePane();
        }
        return true;
    }

    public Player getOwner() {
        return owner;
    }
}

class AsyncPopUp {
    JOptionPane pane;
    JProgressBar jProgressBar;
    JFrame frame;
    Deck deck;
    AsyncPopUp(Deck d) {
        this.deck = d;
        frame = new JFrame("");
        pane = new JOptionPane("", JOptionPane.QUESTION_MESSAGE);
        pane.setLayout(new GridLayout(5,1));
        pane.setSize(100,1000);
        pane.remove(0);
        pane.remove(0);
         jProgressBar = new JProgressBar(0, 10);
         jProgressBar.setValue(0);
        jProgressBar.setStringPainted(true);
        pane.add(jProgressBar);

        JButton[] buttonArray = new JButton[4];

        for(int i = 0; i < buttonArray.length; i ++) {
            addCard(deck.availableCards[i]);
        }

    }
    public void showMessage() {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                deck.deckSpawningSelected = false;
                JDialog dialog = pane.createDialog(frame, deck.getOwner().getName() + ": Select a Troop to Spawn:");
                if(deck.getOwner().getName().equals("Human Player") && deck.lastButtonPressed.equals("")) dialog.setVisible(true);

                deck.deckSpawningSelected = true;
            }
        });
        t.start();
    }

    public void showElixir(int i) {
        jProgressBar.setValue(i);

    }

    public void updatePane() {
        pane.removeAll();
        pane.add(jProgressBar);
        for(String card: deck.availableCards) {
            addCard(card);
        }

    }

    private void addCard(String text) {
        JButton button = new JButton();

        button.setText(text);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JDialog) (SwingUtilities.getRoot((Component)e.getSource()))).dispose();

                if(!deck.useCard(((JButton)e.getSource()).getText())) {
                    deck.deckSpawningSelected = false;
                }
            }
        });

        pane.add(button);
    }
}
