/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uppercaserestclient;

import apiREST.apiRESTUppercase;
import entity.Uppercase;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Marcel
 */
public class UpperCaseRESTClient {
    
    JFrame frame;
    JTextArea sentencesTextArea;
    JTextField enterSentenceTextField;    
    JButton sendSentence;
    JButton update;
    JButton closeApp;

    private UpperCaseRESTClient(){
        frame = new JFrame("Uppercase");
        frame.setSize(300,300);
        //frame.addWindowListener(new CloseWindowHandler());
        sentencesTextArea = new JTextArea(10,20);
        enterSentenceTextField = new JTextField(15);
        sendSentence = new JButton("Send Sentence");
        update = new JButton("Refresh");

        closeApp = new JButton("Close App");

        sendSentence.addActionListener(new SendSentenceHandler());
        update.addActionListener(new UpdateSentencesHandler());
        closeApp.addActionListener(new CloseAppHandler());

        JPanel buttonsPannel = new JPanel(new FlowLayout());
        buttonsPannel.add(closeApp);

        
        JPanel enterSentencePanel = new JPanel();
        enterSentencePanel.setLayout(new BoxLayout(enterSentencePanel, BoxLayout.Y_AXIS));

        enterSentencePanel.add(new JLabel("Enter sentence:"));
        enterSentencePanel.add(enterSentenceTextField);
        
        enterSentencePanel.add(sendSentence);
        enterSentencePanel.add(update);

        JPanel showSentencesPanel = new JPanel();
        showSentencesPanel.setLayout(new BoxLayout(showSentencesPanel, BoxLayout.Y_AXIS));
        showSentencesPanel.add(new JLabel("Sentences : "));
        showSentencesPanel.add(new JScrollPane(sentencesTextArea));
        showSentencesPanel.add(update);

      

        Container mainPanel = frame.getContentPane();
        mainPanel.add(buttonsPannel, BorderLayout.PAGE_START);
        mainPanel.add(enterSentencePanel,BorderLayout.PAGE_END);
        mainPanel.add(showSentencesPanel,BorderLayout.CENTER);

        
        // Retrieve sentences at start up
           List<Uppercase> ul = apiRESTUppercase.findAll();
           updateSentences(ul);        
        
        frame.pack();
        frame.setVisible(true);        
    }
    
    private void updateSentences(List<Uppercase> lc){
        sentencesTextArea.setText("");
        for (Uppercase cm : lc) {
            System.out.println(cm);

            sentencesTextArea.append(cm.toString());
            sentencesTextArea.append("\n");
        }
    }
    

    public static UpperCaseRESTClient createAndShowGUI() {
        return new UpperCaseRESTClient();
    }

    class SendSentenceHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
                      
           String inputSentence = enterSentenceTextField.getText();
           // we filter empty sentences
           if(!inputSentence.equals("")){
                String uppercaseSentence = inputSentence.toUpperCase();
                Uppercase up = new Uppercase();
                up.setContent(uppercaseSentence);
                apiRESTUppercase.postUppercase(up);
                enterSentenceTextField.setText("");
           }
        }
    }

    class UpdateSentencesHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
           List<Uppercase> ul = apiRESTUppercase.findAll();
           updateSentences(ul);
        }
    }  
    
    static class CloseAppHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //...
            System.exit(0);
        }
    }
    class CloseWindowHandler implements WindowListener{
        public void windowDeactivated(WindowEvent e) {}
        public void windowActivated(WindowEvent e) {}
        public void windowIconified(WindowEvent e) {}
        public void windowDeiconified(WindowEvent e) {}
        public void windowClosed(WindowEvent e) {}
        public void windowOpened(WindowEvent e) {}
        public void windowClosing(WindowEvent e) {
            //...
            System.out.println("app closed");
            System.exit(0);
        }
    }
    
    public static void main(String[] args){
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });  
        

    }
}




