package UserInterface.Screen;

import javax.swing.*;

import DataAccessComponent.DAOs.QuestionDAO;
import DataAccessComponent.DTOs.AnswerDTO;
import DataAccessComponent.DTOs.QuestionDTO;
import Infrastructure.AppException;
import DataAccessComponent.DAOs.AnswerDAO;
import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.StyleConfig;

import java.awt.*;
import java.util.ArrayList;

public class GameScreen {
    private static String question;
    private static String correctAnswer;
    private static Integer indexAnswer;
    private static String[] answers;

    public static JPanel game() throws AppException {
        JPanel gamePanel = new JPanel(new BorderLayout());
        JLabel gameLabel = StyleConfig.tittleConfig();
        gamePanel.add(gameLabel, BorderLayout.NORTH);
        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel(
                "src\\UserInterface\\Resources\\ImagenBackGroundLogin.png");
        gamePanel.add(backgroundPanel, BorderLayout.CENTER);
        try {
            ArrayList<QuestionDTO> questionCurrent = getQuestion();
            int score[] = { 0 };
            int index[] = { randomNumber(questionCurrent.size()) };
            indexAnswer = questionCurrent.get(index[0]).getIdQuestion();
            question = questionCurrent.get(index[0]).getQuestion();
            answers = getOptions(indexAnswer);
            questionCurrent.remove(index[0]);
            correctAnswer = getCorrectAnswer(indexAnswer);
            JLabel questJLabel = StyleConfig.questionLabel(question);
            JPanel options = new JPanel();
            options.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            options.add(questJLabel);
            options.setLayout(new GridLayout(5, 1, 10, 10));
            options.setOpaque(false);
            JButton[] optionButtons = new JButton[4];
            for (int i[] = { 0 }; i[0] < 4; i[0]++) {
                JButton optionButton = StyleConfig.createButton(answers[i[0]], StyleConfig.ButtonSecondary(),
                        50,
                        50);
                optionButton.setOpaque(false);
                optionButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
                optionButtons[i[0]] = optionButton;
                options.add(optionButton);
                optionButton.addActionListener(e -> {
                    try {
                        String selectedAnswer = optionButton.getText();
                        if (questionCurrent.size() > 0) {
                            if (selectedAnswer.equals(correctAnswer)) {
                                score[0] += 10;
                                index[0] = randomNumber(questionCurrent.size());
                                question = questionCurrent.get(index[0]).getQuestion();
                                indexAnswer = questionCurrent.get(index[0]).getIdQuestion();
                                answers = getOptions(indexAnswer);
                                questionCurrent.remove(index[0]);
                                questJLabel.setText(question);
                                correctAnswer = getCorrectAnswer(indexAnswer);
                                for (int j = 0; j < 4; j++) {
                                    optionButtons[j].setText(answers[j]);
                                }
                                System.out.println("Pregunta: " + correctAnswer);
                                options.revalidate();
                                options.repaint();
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Respuesta Incorrecta. La respuesta correcta es: " + correctAnswer,
                                        "Resultado", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Juego terminado! Tu puntaje final es: " + score[0],
                                    "Juego Terminado", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception e1) {

                        JOptionPane.showMessageDialog(null,
                                "Error al procesar la respuesta: " + e1.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
            }
            backgroundPanel.add(options);
            JComponent[][] buttons = new JComponent[][] { { optionButtons[0] }, { optionButtons[1] },
                    { optionButtons[2] }, {
                            optionButtons[3] } };
            ControllerDualsense ControllerDualsense = new ControllerDualsense();
            ControllerDualsense.setupKeyBindings(gamePanel, buttons);
            ControllerDualsense.focusComponent(ControllerDualsense.getCurrentIndexX(),
                    ControllerDualsense.getCurrentIndexY(), buttons);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la pantalla de juego: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            throw new AppException("Error en Iniciar el juego");

        }

        return gamePanel;
    }

    public static int randomNumber(int max) {
        return (int) (Math.random() * max);
    }

    public static ArrayList<QuestionDTO> getQuestion() throws Exception {
        QuestionDAO qdao = new QuestionDAO();
        ArrayList<QuestionDTO> questionCurrent = new ArrayList<>(qdao.readAllQuestion());
        return questionCurrent;
    }

    public static String[] getOptions(int index) throws Exception {
        AnswerDAO adao = new AnswerDAO();
        ArrayList<AnswerDTO> options = new ArrayList<>(adao.readOptions(index, true));
        String[] optionTexts = new String[options.size()];
        for (int i = 0; i < options.size(); i++) {
            optionTexts[i] = options.get(i).getAnswer();
        }
        return optionTexts;
    }

    public static String getCorrectAnswer(int questionId) throws Exception {
        AnswerDAO adao = new AnswerDAO();
        String correctAnswer = adao.readCorrectAns(questionId);
        return correctAnswer;
    }

}
