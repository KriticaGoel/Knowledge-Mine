package solid.dip;

public class DIPFollowed {
    static void main(String[] args) {
        DIPApplication app = new DIPApplication( new DIPSaveToSQL());
        app.saveData();
        DIPApplication app1 = new DIPApplication( new DIPSaveToMongo());
        app1.saveData();
    }
}

//HLD
class DIPApplication{

    private Persistence persistence;

    public DIPApplication(Persistence persistence) {
        this.persistence = persistence;
    }
    public void saveData(){
        persistence.save();
    }
}


//Interface

interface Persistence{
     public void save();
}

//LLD
class DIPSaveToSQL implements Persistence{
    public void save(){
        System.out.println("Data is saving to sql database");
    }
}

class DIPSaveToMongo implements Persistence{
    public void save(){
        System.out.println("Data is saving to Mongo database");
    }
}