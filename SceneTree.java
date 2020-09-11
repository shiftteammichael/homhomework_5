/**
 * The SceneTree class represents a collection of SceneNode objects arranged as a tree
 * @author Michael Hom
 * @id 112536073
 * Recition 09
 */
public class SceneTree {
    SceneNode root;
    SceneNode cursor;

    /**
     * This getter method gets the cursor
     * @return cursor
     */
    public SceneNode getCursor() {
        return cursor;
    }

    /**
     * This setter method sets the cursor
     * @param cursor
     */
    public void setCursor(SceneNode cursor) {
        this.cursor = cursor;
    }

    /**
     * This is a getter method that gets the root
     * @return root
     */
    public SceneNode getRoot() {
        return root;
    }

    /**
     * This is a setter method that sets the root
     * @param root
     */
    public void setRoot(SceneNode root) {
        this.root = root;
    }

    /**
     * This is the constructor for SceneTree class
     */
    public SceneTree(){

    }

    /**
     * This method moves the cursor to the parent node
     * @throws NoSuchNodeException
     */
    public void moveCursorBackwards() throws NoSuchNodeException{
        if(cursor==root){
            throw new NoSuchNodeException("The current node does not have a parent");
        }
        cursor=cursor.getParentNode();
    }

    /**
     * This method moves the cursor to the appropriate child node
     * @param option
     * @throws NoSuchNodeException
     */
    public void moveCursorForward(String option) throws NoSuchNodeException{
        if(cursor.getLeft()!=null&& option.equalsIgnoreCase("A")){
            cursor=cursor.getLeft();

        }
        else if(cursor.getMiddle()!=null&& option.equalsIgnoreCase("B")){
            cursor=cursor.getMiddle();

        }
        else if(cursor.getRight()!=null&& option.equalsIgnoreCase("C")){
            cursor=cursor.getRight();

        }
        else{
            throw new NoSuchNodeException("This option does not exist");
        }
    }

    /**
     * This method creates a new SceneNode object as a child of the current node with supplied values
     * @param title
     * @param sceneDescription
     * @throws FullSceneException
     */
    public void addNewNode(String title, String sceneDescription) throws FullSceneException {

        SceneNode bruh= new SceneNode();
        bruh.setTitle(title);
        bruh.setSceneDescription(sceneDescription);
        bruh.setParentNode(cursor);

        if(root==null){
            root=bruh;
            cursor=root;
            bruh.setSceneID(1);
            bruh.setDepth(0);
        }
        else{
            bruh.setDepth(cursor.getDepth()+1);
            cursor.addSceneNode(bruh);
        }
        System.out.println("Scene #"+bruh.getSceneID()+ " added");
    }

    /**
     * This method removes the specified child node from the tree
     * @param option
     * @throws NoSuchNodeException
     */
    public void removeScene(String option) throws NoSuchNodeException {
        if(cursor.getLeft()==null && cursor.getMiddle()==null && cursor.getRight()==null){
            throw new NoSuchNodeException("There are no child nodes");
        }
        if (cursor.getLeft() != null && option.equalsIgnoreCase("A")) {
            cursor.setLeft(cursor.getMiddle());
            cursor.setMiddle(cursor.getRight());
            cursor.setRight(null);
        } else if (cursor.getMiddle() != null && option.equalsIgnoreCase("B")) {
            cursor.setMiddle(cursor.getRight());
            cursor.setRight(null);
        } else if (cursor.getRight() != null && option.equalsIgnoreCase("C")) {
            cursor.setRight(null);
        }
    }


    /**
     * This method moves the node at cursor to be a child of the node with the specified id
     * @param sceneIDToMoveTo
     * @throws NoSuchNodeException
     * @throws FullSceneException
     */
    public void moveScene(int sceneIDToMoveTo) throws NoSuchNodeException,FullSceneException{
        moveScene(sceneIDToMoveTo,root);
    }

    /**
     * This method checks if the node is a descendant of the cursor
     * @param s
     * @return
     */
    public boolean isDescendant(SceneNode s){
        while(s.getParentNode()!=null){
            if (s == cursor){
                return true;
            }
            s=s.getParentNode();
        }
        return false;
    }

    /**
     * This is a helper method moves the node at cursor to be a child of the node with the specified ID
     * @param id
     * @param s
     * @throws FullSceneException
     * @throws NoSuchNodeException
     */
    public void moveScene(int id, SceneNode s) throws FullSceneException, NoSuchNodeException {
        if(s.getSceneID()==id){

            if(cursor.getParentNode().getLeft().equals(cursor)){
                cursor.getParentNode().setLeft(cursor.getParentNode().getMiddle());
                cursor.getParentNode().setMiddle(cursor.getParentNode().getRight());
            }
            else if(cursor.getParentNode().getMiddle().equals(cursor)){
                cursor.getParentNode().setMiddle(cursor.getParentNode().getRight());
                cursor.getParentNode().setRight(null);
            }
            else{
                cursor.getParentNode().setRight(null);
            }

            if(s.getLeft()==null){
                SceneNode temp=cursor;
                cursor=null;
                s.setLeft(temp);
                temp.setDepth(s.getDepth() + 1);
                temp.setLmr(1);
                cursor=temp;
                temp.setParentNode(s);
            }
            else if(s.getLeft()!=null && s.getMiddle()==null){
                SceneNode temp=cursor;
                cursor=null;
                s.setMiddle(temp);
                temp.setDepth(s.getDepth()+1);
                temp.setLmr(2);
                cursor=temp;
                temp.setParentNode(s);

            }
            else if(s.getLeft()!=null && s.getMiddle()!=null && s.getRight()==null){
                SceneNode temp=cursor;
                cursor=null;
                s.setRight(temp);
                temp.setDepth(s.getDepth()+1);
                temp.setLmr(3);
                cursor=temp;
                cursor.setParentNode(s);
            }
            else{
                throw new FullSceneException("There are no more child nodes to be added");
            }

        }
        if(isDescendant(s)){
            throw new NoSuchNodeException("Current node can't be moved since it is descendant of that node");
        }
        if(s.getLeft()!=null){
            moveScene(id,s.getLeft());
        }
        if(s.getMiddle()!=null){
            moveScene(id,s.getMiddle());
        }
        if(s.getRight()!=null){
            moveScene(id, s.getRight());
        }

    }

    /**
     * This is a helper method that helps print the whole tree
     */
    public void printTree(){
        printTree(root);
    }

    /**
     * This is a helper method that acts as the toString which constructs a string
     * representation of the tree
     * @param s
     */
    public void printTree(SceneNode s){
        if(s.getDepth()==0){
            if(s==cursor){
                System.out.print(" "+s.getTitle()+" *"+  " (#"+s.getSceneID()+")");
            }
            else {
                System.out.print(" " + s.getTitle() + " (#" + s.getSceneID() + ")");
            }
        }
            if(s.getLmr()==1){
                for(int i=0; i<s.getDepth();i++){
                    System.out.print("\t");
                }
                if(s==cursor){
                    System.out.print("A) "+s.getTitle()+" *"+  " (#"+s.getSceneID()+")");
                }else {
                    System.out.print("A) " + s.getTitle() + " (#" + s.getSceneID() + ")");
                }
            }
            if(s.getLmr()==2){
                for(int i=0; i<s.getDepth();i++){
                    System.out.print("\t");
                }if(s==cursor){
                    System.out.print("B) "+s.getTitle()+" *"+  " (#"+s.getSceneID()+")");
                }else {
                    System.out.print("B) " + s.getTitle() + " (#" + s.getSceneID() + ")");
                }
            }
            if(s.getLmr()==3){
                for(int i=0; i<s.getDepth();i++){
                    System.out.print("\t");
                }if(s==cursor){
                    System.out.print("C) "+s.getTitle()+" *"+  " (#"+s.getSceneID()+")");
                }
                else {
                    System.out.print("C) " + s.getTitle() + " (#" + s.getSceneID() + ")");
                }
            }
            System.out.print("\n");


        if(s.getLeft()!=null){
            printTree(s.getLeft());
        }
        if(s.getMiddle()!=null){
            printTree(s.getMiddle());
        }
        if(s.getRight()!=null){
            printTree(s.getRight());
        }
    }

    /**
     * This is a method that constructs the path from the root of the tree to the currently selected node
     * @return n
     */
    public String getPathFromRoot(){

        SceneNode temp=cursor;
        String n=temp.getTitle();
        while(temp.getParentNode()!=null){
            n=temp.getParentNode().getTitle()+", "+n;
            temp=temp.getParentNode();
        }
        return n;
    }
}
