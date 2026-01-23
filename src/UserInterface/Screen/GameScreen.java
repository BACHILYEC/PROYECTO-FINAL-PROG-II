package UserInterface.Screen;

import javax.swing.*;

import DataAccessComponent.DAOs.QuestionDAO;
import DataAccessComponent.DTOs.AnswerDTO;
import DataAccessComponent.DTOs.QuestionDTO;
import DataAccessComponent.DAOs.AnswerDAO;
import UserInterface.Utility.ImageBackgroundPanel;
import UserInterface.Utility.StyleConfig;

import java.awt.*;
import java.util.ArrayList;

public class GameScreen {
    public static JPanel game() {
        JPanel gamePanel = new JPanel(new BorderLayout());
        JLabel gameLabel = StyleConfig.tittleConfig();
        gamePanel.add(gameLabel, BorderLayout.NORTH);
        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel(
                "src\\UserInterface\\Resources\\ImagenBackGroundLogin.png");
        gamePanel.add(backgroundPanel, BorderLayout.CENTER);
        try {
            int max = randomNumber(49);
            System.out.println(max);
            QuestionDAO qdao = new QuestionDAO();
            ArrayList<QuestionDTO> question = new ArrayList<>(qdao.readAllQuestion());
            System.out.println(question.size());
            String questionCurrent = question.get(max).getQuestion();
            JLabel questJLabel = StyleConfig.questionLabel(questionCurrent);
            JPanel options = new JPanel();
            options.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            options.add(questJLabel);
            options.setLayout(new GridLayout(5, 1, 10, 10));
            options.setOpaque(false);
            AnswerDAO answer = new AnswerDAO();
            for (AnswerDTO dto : answer.readOptions(max, true)) {
                JButton option = StyleConfig.createButton(dto.getAnswer(), StyleConfig.ButtonSecondary(), 50, 50);
                options.add(option);
            }
            backgroundPanel.add(options);
            question.remove(question.get(max));
            System.out.println(question.size());
            max--;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la pantalla de juego: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);

        }
        return gamePanel;
    }

    public static int randomNumber(int max) {
        return (int) (Math.random() * max);
    }
}
