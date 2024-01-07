public class Task {
    private int id;
    private StringBuffer description;
    private boolean completed;

    public Task(int id, StringBuffer description){
        this.id = id;
        this.description = description;
        this.completed = false;
    }
    public int getTaskId(){ return this.id; }
    public StringBuffer getDescription(){ return this.description; }
    public void setDescription(StringBuffer description){ this.description = description; }
    public boolean getCompleted(){return this.completed;}
    public void setCompleted(boolean completed){this.completed = completed;
    }
}
