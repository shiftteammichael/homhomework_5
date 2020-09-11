/**
 * The AdventureDesigner class acts as the main driver for the program and it guides
 * the user through making and possibly playing the game
 * @author Michael Hom
 * @id 112536073
 * Recitation 09
 */


import java.util.Scanner;

public class AdventureDesigner {
    static SceneTree test = new SceneTree();
    public static void main(String[] args) throws FullSceneException {

            Scanner in = new Scanner(System.in);
            SceneTree test = new SceneTree();
            System.out.println("Creating a Story...");
            System.out.println("Please enter a title: ");
            String titlee = in.nextLine();
            System.out.println("Please enter a scene");
            String scenee = in.nextLine();
            test.addNewNode(titlee, scenee);
            while(true){
                System.out.println("A) Add Scene \n R) Remove Scene \n S) Show Current Scene \n P) Print Adventure Tree \n B) Go Back A Scene \n F) Go Forward A Scene \n G) Play Game \n N) Print Path To Cursor \n M) Move scene \n Q) Quit");
                System.out.println("Please Enter a Selection:");
                String selection=in.nextLine();
                if(selection.equalsIgnoreCase("A")){
                    try {
                        System.out.println("Please enter a title: ");
                        String title1 = in.nextLine();
                        System.out.println("Please enter a scene");
                        String scene1 = in.nextLine();
                        test.addNewNode(title1, scene1);
                    }
                    catch(FullSceneException ex){
                        System.out.println(ex.getMessage());
                    }

                }
                if(selection.equalsIgnoreCase("R")){
                    try {
                        System.out.println("Please enter an option: ");
                        String op = in.nextLine();
                        test.removeScene(op);
                    }
                    catch(NoSuchNodeException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                if(selection.equalsIgnoreCase("S")){
                    test.getCursor().displayFullScene();
                }
                if(selection.equalsIgnoreCase("P")){
                    test.printTree(test.getRoot());
                }
                if(selection.equalsIgnoreCase("B")){
                    try{
                        test.moveCursorBackwards();
                        System.out.println("Successfully moved back to "+test.getCursor().getTitle());
                    }
                    catch(NoSuchNodeException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                if(selection.equalsIgnoreCase("F")){
                    try {
                        System.out.println("Which option do you wish to go to: ");
                        String op = in.nextLine();
                        test.moveCursorForward(op);
                        System.out.println("Successfully moved to "+test.getCursor().getTitle());
                    }
                    catch(NoSuchNodeException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                if(selection.equalsIgnoreCase("G")){
                    playGame(test.getRoot());
                }
                if(selection.equalsIgnoreCase("N")){
                    System.out.println(test.getPathFromRoot());
                }
                if(selection.equalsIgnoreCase("M")){
                    try {
                        System.out.println("Move current scene to: ");
                        int yay = in.nextInt();
                        test.moveScene(yay);

                    }
                    catch(NoSuchNodeException ex){
                        System.out.println(ex.getMessage());
                    }
                    catch(FullSceneException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                if(selection.equalsIgnoreCase("Q")) {
                    System.exit(0);

                }
            }

    }

    /**
     * This method starts at the root of the tree and asks the user to select an option and
     * prints out the scene. It essentially allows the user to play the game
     * @param s
     */
    public static void playGame(SceneNode s){
        Scanner in= new Scanner(System.in);
        if(s.getSceneID()==1) {
            System.out.println("Now beginning game...");
        }
        s.displayScene();
        if(s.isEnding()){
            System.out.println("The end");
            System.out.println("Returning back to creation mode...");
        }

        System.out.println("Please enter an option");
        String option=in.nextLine();
        if(option.equalsIgnoreCase("A") && s.getLeft()!=null){
            s=s.getLeft();
            playGame(s);
        }
        if(option.equalsIgnoreCase("B") && s.getMiddle()!=null){
            s=s.getMiddle();
            playGame(s);
        }
        if(option.equalsIgnoreCase("C") && s.getRight()!=null){
            s=s.getRight();
            playGame(s);
        }

    }
}
