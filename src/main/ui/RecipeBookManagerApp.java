package ui;

import model.Recipe;
import model.RecipeBook;
import persistence.JsonWriter;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeBookManagerApp extends JFrame {
    private static final String JSON_STORE = "./data/recipeBook.json";
    private RecipeBook recipeBook;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    public static final int width = 800;
    public static final int height = 600;
    private JButton listButton;
    private JButton addRecipeButton;
    private JButton viewRecipeButton;
    private JButton changeRatingButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton backButton;
    private JButton createButton;
    private JButton viewAnExistRecipeButton;
    private JButton changeExactRatingButton;
    private JFrame changeRatingFrame;
    private JFrame welcomeFrame;
    private JFrame listFrame;
    private JFrame addRecipeFrame;
    private JFrame viewRecipeFrame;
    private JPanel welcomePanel;
    private JPanel listPanel;
    private JPanel addRecipePanel;
    private JPanel viewRecipePanel;
    private JPanel changeRatingPanel;



    //constructs recipeBook and runs application
    public RecipeBookManagerApp() {
        recipeBook = new RecipeBook("My Recipe Book");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setUpFrame();

    }

    //MODIFIES: this
    //EFFECTS: adds index to recipe book to make the recipe book look nicer.
    public static ArrayList<JLabel> printTitleOfRecipe(ArrayList<Recipe> recipes) {
        ArrayList<JLabel>  recipeList = new ArrayList<>();
        int count = 1;
        for (Recipe recipe : recipes) {
            JLabel label = new JLabel(count + " " + recipe.getRecipeTitle());
            recipeList.add(label);
            System.out.println(count + " " + recipe.getRecipeTitle());
            count++;
        }
        return recipeList;
    }


    //MODIFIES: this
    //EFFECTS: makes a new recipe and add it to the recipe book
    public static void addRecipe(RecipeBook recipeBook,String s1, String s2, int c) {
        recipeBook.makeRecipe(s1, c, s2);
    }


    //MODIFIES: this
    //EFFECTS: change ann existing recipe's rating
    public void changeRecipeRating(String name, int rating) {
        recipeBook.changeRecipeRating(recipeBook.containsRecipe(name), rating);
    }

    // EFFECTS: saves the workroom to file
    private void saveRecipeBook() {
        try {
            jsonWriter.open();
            jsonWriter.write(recipeBook);
            jsonWriter.close();
            System.out.println("Saved " + recipeBook.getBookTitle() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadRecipeBook() {
        try {
            recipeBook = jsonReader.read();
            System.out.println("Loaded " + recipeBook.getBookTitle() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private void back(JPanel jpanel) {
        backButton = new JButton("back");
        jpanel.add(backButton);
        backButtonAction(jpanel);
        backButton.setBounds(600,450,100,50);
    }

    private void setUpFrame() {
        welcomeFrame = new JFrame();
        welcomePanel = new JPanel(new GridLayout(6,0));
        JLabel welcomeLabel = new JLabel("My Recipe Manager App");
        initWelcomeButton();
        initWelcomePanel();
        welcomeFrame.add(welcomePanel);
        welcomePanel.add(welcomeLabel);
        welcomeFrame.setSize(width,height);
        welcomeFrame.setTitle("Recipe Book Manager App");
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setLocationRelativeTo(null);
        welcomeFrame.add(welcomeLabel, BorderLayout.NORTH);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setVerticalAlignment(JLabel.TOP);
        welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        welcomeFrame.setVisible(true);

    }


    private void initWelcomeButton() {
        listButton = new JButton("see the list of recipes");
        addRecipeButton = new JButton("add new recipe to recipe book");
        viewRecipeButton = new JButton("view an exist recipe");
        changeRatingButton = new JButton("change rating of an exist recipe");
        saveButton = new JButton("save recipe book");
        loadButton = new JButton("load recipe book");
    }

    private void initWelcomePanel() {
        listButtonAction();
        addRecipeButtonAction();
        viewRecipeButtonAction();
        changeRatingButtonAction();
        saveButtonAction();
        loadButtonAction();
        welcomePanel.add(listButton);
        welcomePanel.add(addRecipeButton);
        welcomePanel.add(viewRecipeButton);
        welcomePanel.add(changeRatingButton);
        welcomePanel.add(saveButton);
        welcomePanel.add(loadButton);

    }

    private void addRecipeFrame() {
        addRecipeFrame = new JFrame();
        addRecipePanel = new JPanel(null);

        addRecipeFrame.setSize(width,height);
        addRecipeFrame.setTitle("Recipe Book Manager App");
        addRecipeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addRecipeFrame.setLocationRelativeTo(null);
        addRecipeFrame.add(addRecipePanel);
        addRecipePanel();

        welcomeFrame.setVisible(false);
        addRecipeFrame.setVisible(true);


    }

    private void addRecipePanel() {
        ImageIcon image = new ImageIcon(getClass().getResource("food.jpg"));
        JLabel foodPicture = new JLabel();
        foodPicture.setIcon(image);
        JTextField tf1 = new JTextField("Enter Recipe Name");
        tf1.setBounds(50,50,150,20);
        JTextField tf2 = new JTextField("Enter Recipe");
        tf2.setBounds(50,100,150,20);
        JTextField tf3 = new JTextField("Enter Recipe Rating");
        tf3.setBounds(50,150,150,20);
        foodPicture.setBounds(300,0,300,300);
        addRecipePanel.add(foodPicture);
        createButton = new JButton("Create Recipe!");
        createButton.setBounds(50,200,150,20);
        addRecipePanel.add(tf1);
        addRecipePanel.add(tf2);
        addRecipePanel.add(tf3);
        addRecipePanel.add(createButton);
        createRecipeButtonAction(tf1,tf2,tf3);
        back(addRecipePanel);
    }

    private void viewRecipeFrame() {
        viewRecipeFrame = new JFrame();
        viewRecipePanel = new JPanel(null);

        viewRecipeFrame.setSize(width,height);
        viewRecipeFrame.setTitle("Recipe Book Manager App");
        viewRecipeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewRecipeFrame.setLocationRelativeTo(null);
        viewRecipePanel();
        viewRecipeFrame.add(viewRecipePanel);

        welcomeFrame.setVisible(false);
        viewRecipeFrame.setVisible(true);


    }

    private void viewRecipePanel() {
        int count = 0;
        for (JLabel j : printTitleOfRecipe(recipeBook.getRecipes())) {
            viewRecipePanel.add(j);
            j.setFont(new Font("Serif", Font.PLAIN, 20));
            j.setBounds(100,100,100,50 + count * 50);
            count++;
        }
        viewAnExistRecipeButton = new JButton("Enter Your Recipe");
        JTextField tf1 = new JTextField("Enter your recipe name");
        tf1.setBounds(600,100,100,50);
        viewAnExistRecipeButton.setBounds(600,350,150,50);
        viewRecipePanel.add(tf1);
        viewRecipePanel.add(viewAnExistRecipeButton);
        viewAnExistRecipeButtonAction(tf1);
        back(viewRecipePanel);

    }

    private void listFrame() {
        listFrame = new JFrame();
        listPanel = new JPanel(new GridLayout(6,1));

        listFrame.setSize(width,height);
        listFrame.setTitle("Recipe Book Manager App");
        listFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listFrame.setLocationRelativeTo(null);
        listPanel();
        listFrame.add(listPanel);

        welcomeFrame.setVisible(false);
        listFrame.setVisible(true);


    }

    private void listPanel() {
        for (JLabel j : printTitleOfRecipe(recipeBook.getRecipes())) {
            listPanel.add(j);
            j.setFont(new Font("Serif", Font.PLAIN, 30));
        }
        back(listPanel);
    }

    private void changeRatingFrame() {
        changeRatingFrame = new JFrame();
        changeRatingPanel = new JPanel(null);

        changeRatingFrame.setSize(width,height);
        changeRatingFrame.setTitle("Recipe Book Manager App");
        changeRatingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        changeRatingFrame.setLocationRelativeTo(null);
        changeRatingPanel();
        changeRatingFrame.add(changeRatingPanel);

        welcomeFrame.setVisible(false);
        changeRatingFrame.setVisible(true);


    }

    private void changeRatingPanel() {
        int count = 0;
        for (JLabel j : printTitleOfRecipe(recipeBook.getRecipes())) {
            changeRatingPanel.add(j);
            j.setFont(new Font("Serif", Font.PLAIN, 20));
            j.setBounds(100,100,100,50 + count * 50);
            count++;
        }
        changeExactRatingButton = new JButton("Change!");
        JTextField tf1 = new JTextField("Enter your recipe name");
        JTextField tf2 = new JTextField("Enter new Rating");
        tf1.setBounds(600,100,100,50);
        tf2.setBounds(600,160,100,50);
        changeExactRatingButton.setBounds(600,350,150,50);
        changeRatingPanel.add(tf1);
        changeRatingPanel.add(tf2);
        changeRatingPanel.add(changeExactRatingButton);
        changeExactRatingButtonAction(tf1,tf2);
        back(changeRatingPanel);

    }

    private void listButtonAction() {
        listButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    private void addRecipeButtonAction() {
        addRecipeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addRecipeFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    private void viewRecipeButtonAction() {
        viewRecipeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                viewRecipeFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    private void changeRatingButtonAction() {
        changeRatingButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeRatingFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    private void saveButtonAction() {
        saveButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                saveRecipeBook();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    private void loadButtonAction() {
        loadButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadRecipeBook();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    private void backButtonAction(JPanel panel) {
        backButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                welcomeFrame.setVisible(true);
                panel.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    private void createRecipeButtonAction(JTextField tf1,JTextField tf2,JTextField tf3) {
        createButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String s1 = tf1.getText();
                String s2 = tf2.getText();
                String s3 = tf3.getText();
                int c = Integer.parseInt(s3);
                addRecipe(recipeBook,s1,s2,c);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    private void viewAnExistRecipeButtonAction(JTextField tf1) {
        viewAnExistRecipeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String s1 = tf1.getText();
                viewRecipePanel.repaint();
                viewRecipePanel.add(recipeBook.displaySelectedRecipe(recipeBook.containsRecipe(s1)));
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    private void changeExactRatingButtonAction(JTextField tf1,JTextField tf2) {
        changeExactRatingButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String s1 = tf1.getText();
                String s2 = tf2.getText();
                int b = Integer.parseInt(s2);
                changeRecipeRating(s1,b);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }





}

