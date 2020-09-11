/**
 * The SceneNode class represents a specific scene in the SceneTree class
 * @author Michael Hom
 * @id 112536073
 * Recitation 09
 */

public class SceneNode {
    String title;
    String sceneDescription;
    int sceneID;
    SceneNode left;
    SceneNode middle;
    SceneNode right;
    SceneNode parentNode;
    static int numScenes=1;
    int depth;
    int lmr;

    /**
     * This is a helper method that I implemented to determine if the node is left, middle or right
     * @return lmr
     */
    public int getLmr() {
        return lmr;
    }

    /**
     * This is a helper setter method that sets if the node is left, middle, or right
     * @param lmr
     */
    public void setLmr(int lmr) {
        this.lmr = lmr;
    }

    /**
     * This is a helper method to get the depth of the node
     * @return depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * This is a helper method to set the depth of the node
     * @param depth
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * This is a helper method that gets the parent node of the node
     * @return parentNode
     */
    public SceneNode getParentNode() {
        return parentNode;
    }

    /**
     * This is a helper method that gets the parent node of the node
     * @param parentNode
     */
    public void setParentNode(SceneNode parentNode) {
        this.parentNode = parentNode;
    }

    /**
     * This is the constructor for the SceneNode class
     */
    public SceneNode(){

    }

    /**
     * This method sets the scene to the first available slot in the child Nodes. It adds
     * the child node to the left-most open spot
     * @param scene
     * @throws FullSceneException
     */
    public void addSceneNode(SceneNode scene) throws FullSceneException {
        if(this.getLeft()!=null&& this.getMiddle()!=null && this.getRight()!=null){
            throw new FullSceneException("It is full");
        }
        if(this.getLeft()==null){
            this.setLeft(scene);
            numScenes++;
            scene.setSceneID(numScenes);
            scene.setLmr(1);
        }
        else if(this.getLeft()!=null && this.getMiddle()==null){
            this.setMiddle(scene);
            numScenes++;
            scene.setSceneID(numScenes);
            scene.setLmr(2);
        }
        else if(this.getLeft()!=null && this.getMiddle()!=null && this.getRight()==null){
            this.setRight(scene);
            numScenes++;
            scene.setSceneID(numScenes);
            scene.setLmr(3);
        }
        else{
            throw new FullSceneException("It is full");
        }
    }

    /**
     * This method determines if the scene is an ending which means there are no children
     * @return
     */
    public boolean isEnding(){
        if(this.getLeft()==null && this.getMiddle()==null && this.getRight()==null){
            return true;
        }
        return false;
    }

    /**
     * This method ouptuts the scene information
     */
    public void displayScene(){
        System.out.println(title);
        System.out.println(sceneDescription);
        if(this.getLeft()!=null){
            System.out.println("A) "+ this.getLeft().getTitle());
        }
        if(this.getMiddle()!=null){
            System.out.println("B) "+ this.getMiddle().getTitle());
        }
        if(this.getRight()!=null ){
            System.out.println("C) "+ this.getRight().getTitle());
        }
    }

    /**
     * This method outputs all information about a scene
     */
    public void displayFullScene(){
        System.out.println("Scene ID: #"+this.getSceneID());
        System.out.println("Title: "+this.getTitle());
        System.out.println("Scene: "+this.getSceneDescription());
        String yay="";
        if(this.isEnding()){
            yay+="none";
        }
        if(this.getLeft()!=null){
            yay+="'"+this.getLeft().getTitle()+"' (#"+ this.getLeft().getSceneID()+ "), ";
        }
        if(this.getMiddle()!=null){
            yay+="'"+this.getMiddle().getTitle()+"' (#"+ this.getMiddle().getSceneID()+ "), ";
        }
        if(this.getRight()!=null){
            yay+="'"+this.getRight().getTitle()+"' (#"+ this.getRight().getSceneID()+ "), ";
        }

        System.out.println("Leads to: "+yay);
    }

    /**
     * The toString method represents a string representation of the node
     * @return
     */
    public String toString(){
        return title + "(#"+numScenes+")";
    }

    /**
     * This is a getter method that gets the middle node
     * @return middle
     */
    public SceneNode getMiddle() {
        return middle;
    }

    /**
     * This is a setter method that sets the middle node
     * @param middle
     */
    public void setMiddle(SceneNode middle) {
        this.middle = middle;
    }

    /**
     * This is a getter method that gets the right node
     * @return right
     */
    public SceneNode getRight() {
        return right;
    }

    /**
     * This is a setter method that sets the right ndoe
     * @param right
     */
    public void setRight(SceneNode right) {
        this.right = right;
    }

    /**
     * This is a getter method that gets the left node
     * @return left
     */
    public SceneNode getLeft() {
        return left;
    }

    /**
     * This is a setter method that sets the left node
     * @param left
     */
    public void setLeft(SceneNode left) {
        this.left = left;
    }

    /**
     * This is a getter method that gets the scene Id of the node
     * @return
     */
    public int getSceneID() {
        return sceneID;
    }

    /**
     * This is a setter method that sets the scene id of the node
     * @param sceneID
     */
    public void setSceneID(int sceneID) {
        this.sceneID = sceneID;
    }

    /**
     * This is a getter method that gets the scene description of the node
     * @return sceneDescription
     */
    public String getSceneDescription() {
        return sceneDescription;
    }

    /**
     * This is a setter method that sets the scene description of the node
     * @param sceneDescription
     */
    public void setSceneDescription(String sceneDescription) {
        this.sceneDescription = sceneDescription;
    }

    /**
     * This is a getter method that gets the title of the node
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * This is a setter method that sets the title of the node
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
