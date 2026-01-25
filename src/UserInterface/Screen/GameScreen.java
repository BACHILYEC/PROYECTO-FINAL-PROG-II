package UserInterface.Screen;

import javax.swing.*;

import BusinessLogic.AnswerBL;
import BusinessLogic.QuestionBL;
import BusinessLogic.UserPlayerBL;
import DataAccessComponent.DTOs.AnswerDTO;
import DataAccessComponent.DTOs.QuestionDTO;
import Infrastructure.AppException;
import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.StyleConfig;

import java.awt.*;
import java.util.ArrayList;

public class GameScreen {
    private static String question;
    private static String correctAnswer;
    private static Integer indexAnswer;
    private static String[] answers;
    private static Integer indexCategory;
    private static String category;

    public static JPanel game() throws AppException {
        JPanel gamePanel = new JPanel(new BorderLayout());
        JLabel gameLabel = StyleConfig.tittleConfig();
        gamePanel.add(gameLabel, BorderLayout.NORTH);
        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel(
                "/UserInterface/Resources/ImagenBackGroundLogin.png");
        gamePanel.add(backgroundPanel, BorderLayout.CENTER);
        try {
            ArrayList<QuestionDTO> questionCurrent = getQuestion();
            int score[] = { 0 };
            int index[] = { randomNumber(questionCurrent.size()) };
            indexAnswer = questionCurrent.get(index[0]).getIdQuestion();
            question = questionCurrent.get(index[0]).getQuestion();
            indexCategory = questionCurrent.get(index[0]).getIdCategory();
            answers = getOptions(indexAnswer);
            category = getCategory(indexCategory);
            questionCurrent.remove(index[0]);
            correctAnswer = getCorrectAnswer(indexAnswer);
            JLabel questJLabel = StyleConfig.questionLabel(question);
            JLabel categoryLabel = StyleConfig.questionLabel("Categoría: " + category);
            categoryLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            categoryLabel.setOpaque(false);
            categoryLabel.setForeground(Color.white);
            JPanel Category = new JPanel(new FlowLayout(FlowLayout.LEFT));
            Category.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
            Category.setOpaque(false);
            Category.add(categoryLabel);
            JPanel options = new JPanel();
            options.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
            options.add(Category);
            options.add(questJLabel);
            options.setLayout(new GridLayout(6, 1, 10, 10));
            options.setOpaque(false);
            JButton[] optionButtons = new JButton[4];
            for (int i[] = { 0 }; i[0] < 4; i[0]++) {
                JButton optionButton = StyleConfig.createButton(answers[i[0]], new Color(255, 255, 255),
                        50,
                        50);
                optionButton.setOpaque(true);
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
                                indexCategory = questionCurrent.get(index[0]).getIdCategory();
                                category = getCategory(indexCategory);
                                questionCurrent.remove(index[0]);
                                questJLabel.setText(question);
                                categoryLabel.setText("Categoría: " + category);
                                correctAnswer = getCorrectAnswer(indexAnswer);
                                for (int j = 0; j < 4; j++) {
                                    optionButtons[j].setText(answers[j]);
                                }
                                options.revalidate();
                                options.repaint();
                            } else {
                                UserPlayerBL.create(CreatePlayer.nameField.getText(), score[0]);
                                MainFrame.setContentPane(ScreenLosing.losingScreen(score[0], false));
                            }
                        } else {
                            MainFrame.setContentPane(ScreenLosing.losingScreen(score[0], true));
                            UserPlayerBL.create(CreatePlayer.nameField.getText(), score[0]);
                        }
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null,
                                "Error al procesar la respuesta: " + e1.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
            }
            JButton gotoback = StyleConfig.createButton("Salir del Juego", StyleConfig.ButtonSecondary(), 40, 40);
            gotoback.addActionListener(e -> {
                MainFrame.setContentPane(ExitGame.confirmExitPanel(gamePanel));
            });
            backgroundPanel.add(options);
            gamePanel.add(gotoback, BorderLayout.SOUTH);
            JComponent[][] buttons = new JComponent[][] { { optionButtons[0] }, { optionButtons[1] },
                    { optionButtons[2] }, {
                            optionButtons[3] },
                    { gotoback } };
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
        QuestionBL qBL = new QuestionBL();
        ArrayList<QuestionDTO> questionCurrent = new ArrayList<>(qBL.readAllQuestion());
        return questionCurrent;
    }

    public static String[] getOptions(int index) throws Exception {
        AnswerBL abL = new AnswerBL();
        ArrayList<AnswerDTO> options = new ArrayList<>(abL.readOptions(index, true));
        String[] optionTexts = new String[options.size()];
        for (int i = 0; i < options.size(); i++) {
            optionTexts[i] = options.get(i).getAnswer();
        }
        return optionTexts;
    }

    public static String getCorrectAnswer(int questionId) throws Exception {
        AnswerBL abL = new AnswerBL();
        String correctAnswer = abL.readCorrectAns(questionId);
        return correctAnswer;
    }

    public static String getCategory(int questionId) throws Exception {
        String category = switch (questionId) {
            case 1 -> "Cultura General";
            case 2 -> "Disney";
            case 3 -> "Electrónica";
            case 4 -> "Espacio Exterior";
            case 5 -> "Programación";
            case 6 -> "Ecuador";
            case 7 -> "Geografia";
            case 8 -> "VideoJuegos";
            default -> "Desconocida";
        };
        return category;
    }
}
