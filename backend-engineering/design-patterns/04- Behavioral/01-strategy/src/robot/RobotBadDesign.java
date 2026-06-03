package designPattern.behaviour.strategy.robot;

public class RobotBadDesign {

    static void main(String[] args) {

        RobotIn campanion = new CampanionIn("Campanion");
        RobotIn worker = new WorkerIn("Worker");
        RobotIn sparrow = new SparrowIn("Sparrow");
    }

}

class RobotIn{
    private String name;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}

class TalkableIn extends RobotIn{

    public void talk(){
        System.out.println("I am talking");
    }
}

class NonTalkableIn extends RobotIn{

    public void nonTalk(){
        System.out.println("I am not talking");
    }
}

class WalkableIn extends TalkableIn{
    public void walk(){
        System.out.println("I am walking");
    }
}

class NonWalkableIn extends TalkableIn{
    public void nonWalk(){
        System.out.println("I am not walking");
    }
}
class WalkableNonTalkableIn extends NonTalkableIn{
    public void walk(){
        System.out.println("I am walking");
    }
}

class NonWalkableNonTalkableIn extends NonTalkableIn{
    public void nonWalk(){
        System.out.println("I am not walking");
    }
}

class CampanionIn extends WalkableIn{
    public CampanionIn(String name){
        System.out.println("I am camping");
        setName(name);
        System.out.println("My name is "+getName());
        walk();
        talk();
    }

}
class WorkerIn extends WalkableNonTalkableIn{
    public WorkerIn(String name){
        System.out.println("I am working");
        setName(name);
        System.out.println("My name is "+getName());
        walk();
        nonTalk();
    }

}
class SparrowIn extends NonWalkableNonTalkableIn{
    public SparrowIn(String name){
        System.out.println("I am sparrowing");
        setName(name);
        System.out.println("My name is "+getName());
        nonWalk();
        nonTalk();
    }
    
}

