package solid.dip;

public class DIPViolation {

    static void main(String[] args) {
        Application app = new Application();
        app.saveData();
    }


}

//HLD
class Application{

    SaveToSQL sql = new SaveToSQL();
    SaveToMangoDB mangoDB = new SaveToMangoDB();

    public void saveData(){
        sql.save();
        mangoDB.save();
    }



}

//LLD
class SaveToSQL{
    public void save(){
        System.out.println("Data is saving to sql database");
    }
}

class SaveToMangoDB{
    public void save(){
        System.out.println("Data is saving to Mango database");
    }
}
