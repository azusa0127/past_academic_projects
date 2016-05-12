package view.gui.MainFrame;

import controller.actionListeners.OptionListener;
import controller.actionListeners.HelpListener;
import controller.actionListeners.NewGameListener;
import controller.actionListeners.ResetGameListener;
import controller.validate.CheckWinCondition;
import module.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Phoenix on 2014-11-17.
 *
 * MainFrame class:
 * Core GUI Class for the main window of the game, provides 5 sub containers vertically on the frame.
 *
 * Specified methods:
 * (void) initComponents(): initialize the frame components.
 * (void) disableBtns(): disable Reset and Start Game button.
 * (void) enableBtns(): enable Reset and Start Game button.
 * (void) update*(Var args): to update a specific panel display with varies arguments.
 * (void) borderFocusOn*(): highlight the border of a certain bordered panel.
 * (void) normalizeAllBorders(): normalize all borders.
 * (void) refresh(): refresh and repaint all components on the frame.
 *
 * ActionListener:
 * Actions sent to this will exit the program with exit code 0.
 */
public class MainFrame extends JFrame implements ActionListener{
    private HandPanel dealerHandPanel, playerHandPanel1, playerHandPanel2;
    private StatusPanel statusPanel;
    private BorderedPanelWithALabel playerBalancePanel;
    private BorderedPanelWithALabel balanceChangePanel;
    private BorderedPanelWithALabel deckPanel;
    private BorderedPanelWithALabel playerBetPanel1;
    private BorderedPanelWithALabel playerBetPanel2;
    private BorderedPanelWithALabel dealerPointPanel;
    private BorderedPanelWithALabel playerPointPanel1;
    private BorderedPanelWithALabel playerPointPanel2;
    private JButton resetBtn, startBtn, exitBtn, helpBtn, optionBtn;
    private NewGameListener newGameListener;
    private ResetGameListener resetGameListener;
    private HelpListener helpListener;
    private OptionListener optionListener;

    public MainFrame(NewGameListener newGameListener, ResetGameListener resetGameListener, HelpListener helpListener, OptionListener optionListener) throws InterruptedException {
        super("BlackJack Beta, by Phoenix");
        this.newGameListener = newGameListener;
        this.resetGameListener = resetGameListener;
        this.helpListener = helpListener;
        this.optionListener = optionListener;
        initComponents();
    }

    private void initComponents(){
        JPanel panel1,panel2,panel3,panel4,panel5;

        setSize(600,750);
        setPreferredSize(new Dimension(600,750));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,1,5,5));

        //panel1 building.
        panel1 = new JPanel(new FlowLayout());
        add(panel1);

        JPanel dealerPanel = new JPanel();
        dealerPanel.setBorder(BorderFactory.createTitledBorder("Dealer"));
        panel1.add(dealerPanel);

        dealerHandPanel = new HandPanel();
        dealerPointPanel = new BorderedPanelWithALabel("Point:", 90,100);
        dealerPanel.add(dealerHandPanel);
        dealerPanel.add(dealerPointPanel);

        deckPanel = new BorderedPanelWithALabel("Deck", 90, 100);
        dealerPanel.add(deckPanel);

        //panel2 building
        panel2 = new JPanel(new FlowLayout());
        add(panel2);

        JPanel playerPanel1 = new JPanel();
        playerPanel1.setBorder(BorderFactory.createTitledBorder("Player Hand 1:"));
        panel2.add(playerPanel1);

        playerHandPanel1 = new HandPanel(1);
        playerPointPanel1 = new BorderedPanelWithALabel("Point:", 90,100);
        playerBetPanel1 = new BorderedPanelWithALabel("Bet:",90,100);
        playerPanel1.add(playerHandPanel1);
        playerPanel1.add(playerPointPanel1);
        playerPanel1.add(playerBetPanel1);

        //panel3 building
        panel3 = new JPanel(new FlowLayout());
        add(panel3);

        JPanel playerPanel2 = new JPanel();
        playerPanel2.setBorder(BorderFactory.createTitledBorder("Player Hand 2:"));
        panel3.add(playerPanel2);

        playerHandPanel2 = new HandPanel(2);
        playerPointPanel2 =  new BorderedPanelWithALabel("Point:", 90,100);
        playerBetPanel2 = new BorderedPanelWithALabel("Bet:",90,100);
        playerPanel2.add(playerHandPanel2);
        playerPanel2.add(playerPointPanel2);
        playerPanel2.add(playerBetPanel2);


        //panel4 building
        panel4 = new JPanel(new BorderLayout());
        add(panel4);

        JPanel balancePanel = new JPanel(new FlowLayout());
        playerBalancePanel = new BorderedPanelWithALabel("Player Balance:");
        balanceChangePanel = new BorderedPanelWithALabel("Change in current round:");
        balancePanel.add(playerBalancePanel);
        balancePanel.add(balanceChangePanel);
        panel4.add(balancePanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout());
        resetBtn = new JButton("Reset Game");
        optionBtn = new JButton("Options");
        startBtn = new JButton("Start new round");
        helpBtn = new JButton("Help");
        exitBtn = new JButton("Quit");

        resetBtn.addActionListener(resetGameListener);
        optionBtn.addActionListener(optionListener);
        startBtn.addActionListener(newGameListener);
        helpBtn.addActionListener(helpListener);
        exitBtn.addActionListener(this);

        btnPanel.add(resetBtn);
        btnPanel.add(optionBtn);
        btnPanel.add(startBtn);
        btnPanel.add(helpBtn);
        btnPanel.add(exitBtn);
        panel4.add(btnPanel, BorderLayout.SOUTH);

        //panel5 building
        statusPanel = new StatusPanel();
        panel5 = statusPanel;
        add(panel5);

        pack();
        setVisible(true);
    }

    public void refresh() {
        for (Component c : this.getContentPane().getComponents()) {
            if (c instanceof JPanel)
                ((JPanel) c).updateUI();
        }
        this.repaint();
    }

    public void normalizeAllBorders(){
        dealerHandPanel.normalizeBorder();
        playerHandPanel1.normalizeBorder();
        playerHandPanel2.normalizeBorder();
        playerBalancePanel.normalizeBorder();
        balanceChangePanel.normalizeBorder();
        deckPanel.normalizeBorder();
        dealerPointPanel.normalizeBorder();
        playerPointPanel1.normalizeBorder();
        playerPointPanel2.normalizeBorder();
        playerBetPanel1.normalizeBorder();
        playerBetPanel2.normalizeBorder();
    }

    public void borderFocusOnDeck(){
        normalizeAllBorders();
        deckPanel.highlightBoarder();
    }

    public void borderFocusOnPlayerZone1(){
        normalizeAllBorders();
        playerHandPanel1.highlightBoarder();
        playerPointPanel1.highlightBoarder();
        playerBetPanel1.highlightBoarder();
    }

    public void borderFocusOnPlayerZone2(){
        normalizeAllBorders();
        playerHandPanel2.highlightBoarder();
        playerPointPanel2.highlightBoarder();
        playerBetPanel2.highlightBoarder();
    }

    public void borderFocusOnDealer(){
        normalizeAllBorders();
        dealerHandPanel.highlightBoarder();
        dealerPointPanel.highlightBoarder();
    }

    public void borderFocusOnBalanceChangePanel(){
        normalizeAllBorders();
        balanceChangePanel.highlightBoarder();
    }

    private void updateAPointPanel(final Hand h, final BorderedPanelWithALabel pointPanel){
            if (h.getHandValue() > 21)
                pointPanel.updateText(h.getHandValue() + " " + "Busted!");
            else if (CheckWinCondition.isBlackJack(h))
                pointPanel.updateText("BlackJack!");
            else
                pointPanel.updateText(Integer.toString(h.getHandValue()));
            pointPanel.updateUI();
    }

    private void updateAHandPanel(Hand h, HandPanel hp, boolean isDealer) {
        hp.removeCards();
        if (!h.getCards().empty()) {
            if ((isDealer) && (h.getCards().size() == 1)) {
                hp.addAFaceDownCard();}
            for (Card c : h.getCards()) {
                hp.addCard(c);
            }
        }
        hp.updateUI();
    }

    public void updatePlayerHand(final Player p, final int zoneIndex) {
        if (zoneIndex == 0){
            updateAHandPanel(p.getHands()[0],playerHandPanel1,false);
            updateAPointPanel(p.getHands()[0], playerPointPanel1);
        } else {
            updateAHandPanel(p.getHands()[1],playerHandPanel2,false);
            updateAPointPanel(p.getHands()[1], playerPointPanel2);
        }
    }

    public void updateDealerHand(final Dealer d)  {
        updateAHandPanel(d.getHand(), dealerHandPanel, true);
        updateAPointPanel(d.getHand(), dealerPointPanel);
    }

    public void updatePlayerBet(Player p, int zoneIndex){
        if (zoneIndex == 0){
            if (p.getBetZones()[zoneIndex].getBet().getValue() !=0)
                playerBetPanel1.updateText("$" + p.getBetZones()[zoneIndex].getBet().getValue());
            else
                playerBetPanel1.updateText("");
            playerBetPanel1.updateUI();

        } else {
            if (p.getBetZones()[zoneIndex].getBet().getValue() !=0)
                playerBetPanel2.updateText("$" + p.getBetZones()[zoneIndex].getBet().getValue());
            else
                playerBetPanel2.updateText("");
            playerBetPanel2.updateUI();
        }
    }

    public void updateStatus(final String statusMsg){
        statusPanel.updateText(statusMsg);
        statusPanel.updateUI();
    }

    public void updatePlayerBalance(Player p){
        playerBalancePanel.updateText("$" + p.getBalance());
        playerBalancePanel.updateUI();
    }

    public void updateBalanceChange(double rewards, double initialSpend){
        balanceChangePanel.textLabel.setForeground(Color.BLACK);
        if (initialSpend == rewards){
            balanceChangePanel.updateText("$ 0");
        } else if (initialSpend > rewards){
            balanceChangePanel.textLabel.setForeground(Color.RED);
            balanceChangePanel.updateText("$ " + (rewards - initialSpend));
        } else {
            balanceChangePanel.textLabel.setForeground(Color.BLUE);
            balanceChangePanel.updateText("$ +" + (rewards - initialSpend));
        }
        balanceChangePanel.updateUI();
    }

    public void updateDeckPanel(Deck deck){
        deckPanel.updateText(deck.getIndex() + " left");
        deckPanel.updateUI();
    }

    public void disableBtns(){
        resetBtn.setEnabled(false);
        optionBtn.setEnabled(false);
        startBtn.setEnabled(false);
        refresh();
    }

    public void enableBtns(){
        resetBtn.setEnabled(true);
        optionBtn.setEnabled(true);
        startBtn.setEnabled(true);
        refresh();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
