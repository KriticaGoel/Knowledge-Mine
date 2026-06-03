package designPattern.structural.flyweight;

import org.openjdk.jol.info.ClassLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.min;

public class WithFlyweightAsteroid {

    static void main(String[] args) {
        int count = 1000000;
        System.out.println("with Flyweight pattern");
        SpaceWeightFlyweight game = new SpaceWeightFlyweight();
        game.createAsteroid(count);
        game.getTotalAsteroids();
        game.renderAll();
        game.getTotalMemory();
    }
}

class AsteroidIntrinsic{
    // values not change with object
    int length;
    int width;
    int height;
    double weight;
    String color;
    String texture;
    String material;

    private AsteroidIntrinsic(){
        System.out.println("Object can be created using Builder");
    }

    public AsteroidIntrinsic(AsteroidIntrinsicBuilder builder){
        this.length = builder.length;
        this.width = builder.width;
        this.height = builder.height;
        this.weight = builder.weight;
        this.color = builder.color;
        this.texture = builder.texture;
        this.material = builder.material;

    }
    public static AsteroidIntrinsicBuilder builder(){
        return new AsteroidIntrinsicBuilder();
    }


    public static class AsteroidIntrinsicBuilder{
        int length;
        int width;
        int height;
        double weight;
        String color;
        String texture;
        String material;



        public AsteroidIntrinsicBuilder setMaterial(String material) {
            this.material = material;
            return this;
        }

        public AsteroidIntrinsicBuilder setTexture(String texture) {
            this.texture = texture;
            return this;
        }

        public AsteroidIntrinsicBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public AsteroidIntrinsicBuilder setWeight(double weight) {
            this.weight = weight;
            return this;
        }

        public AsteroidIntrinsicBuilder setHeight(int height) {
            this.height = height;
            return this;
        }

        public AsteroidIntrinsicBuilder setWidth(int width) {
            this.width = width;
            return this;
        }

        public AsteroidIntrinsicBuilder setLength(int length) {
            this.length = length;
            return this;
        }

        public AsteroidIntrinsic build(){
            return new AsteroidIntrinsic(this);
        }
    }

    static long getMemoryUsage(){
        int totalMemory = (Integer.BYTES* 3) + (Double.BYTES * 1)+ (10*3) + (32*3);
        System.out.println("Memory usage per asteroid: " + totalMemory + " bytes");
        return totalMemory;
    }

    void render(int posx,int posy,int velx,int vely){
        System.out.printf(
                "Color %s, texture %s, material %s, \n Asteroid at position %s , %s, \n Size %s * %s *%s, \n Velocity %s , %s\n"
                ,color,texture,material,posx,posy,length,width,height,velx,vely);
    }
}

class AsteroidIntrinsicFactory{
    static Map<String, AsteroidIntrinsic> asteroidMap = new HashMap<>();

    static AsteroidIntrinsic getInstance(int length,
    int width,
    int height,
    double weight,
    String color,
    String texture,
    String material) {
        String key = length + "_" + width + "_" + height + "_" + weight + "_" + color + "_" + texture + "_" + material;
        if(asteroidMap.get(key) == null){
            AsteroidIntrinsic asteroidIntrinsic = new AsteroidIntrinsic.AsteroidIntrinsicBuilder()
                    .setLength(length)
                    .setWidth(width)
                    .setHeight(height)
                    .setWeight(weight)
                    .setColor(color)
                    .setTexture(texture)
                    .setMaterial(material)
                    .build();
            asteroidMap.put(key, asteroidIntrinsic);
        }

        return asteroidMap.get(key);
    }

    static int getAsteroidCount(){
        return asteroidMap.size();
    }

    static long getTotalAsteroidMemory(){
        return asteroidMap.size()*AsteroidIntrinsic.getMemoryUsage();
    }

    static void cleanUp(){
        asteroidMap.clear();
    }
}


//Context - Store Extrinsic state only
class AsteriodContext{
   static  AsteroidIntrinsic asteroidIntrinsic;
    int posx,posy;
    int velx,vely;

    public AsteriodContext(AsteroidIntrinsic asteroidIntrinsic, int posx, int posy, int velx, int vely) {
        this.asteroidIntrinsic = asteroidIntrinsic;
        this.posx = posx;
        this.posy = posy;
        this.velx = velx;
        this.vely = vely;
    }

    void render(){
        asteroidIntrinsic.render(posx,posy,velx,vely);
    }

    static String getAsteroidMemoryUsage(){
        return  ClassLayout.parseInstance(asteroidIntrinsic)
                .toPrintable() + (Integer.BYTES*4);
    }

}

class SpaceWeightFlyweight{
    List<AsteriodContext> contexts = new ArrayList<>();

    public void createAsteroid( int count) {

        List<String> colors = List.of(new String[]{"Red", "Blue", "Gray"});
        List<String> textures = List.of(new String[]{"Rough", "Smooth", "Icy"});
        List<String> materials = List.of(new String[]{"Iron", "Nickel", "Ice"});
        int[] size = {25, 35, 45};

        System.out.println("Creating " + count + " asteroids...");
        for (int i = 0; i < count; i++) {
            int type = i % 3;
            AsteroidIntrinsic asteroidIntrinsic = AsteroidIntrinsicFactory.getInstance(size[type],size[type],size[type]
                    ,size[type]*10,colors.get(type),textures.get(type),materials.get(type));
            AsteriodContext context = new AsteriodContext(asteroidIntrinsic,100+i*50,200+i*30,1,2);
            contexts.add(context);
        }
        System.out.println("Created " + contexts.size() + " asteroids.");
        System.out.println("Total unique asteroid types: " + AsteroidIntrinsicFactory.getAsteroidCount());

    }
    void renderAll(){
        System.out.println("Rendering asteroids...");
        for (int i=0 ;i< min(5,contexts.size());i++) {
            contexts.get(i).render();
        }
    }

    void getTotalMemory(){
        System.out.println("Context size "+AsteriodContext.getAsteroidMemoryUsage());
        long contextMemory = 48 + contexts.size() ;
        long flyweightMemory = AsteroidIntrinsicFactory.getTotalAsteroidMemory();
        System.out.println(" total memory Usage " +contextMemory + flyweightMemory +" bytes");
        System.out.println(" total memory Usage " +(contextMemory + flyweightMemory)/(1024.0*1024.0) +" MB");
    }

    void getTotalAsteroids(){
        System.out.println(" total asteroids usage " +contexts.size());
    }


}


//Memory usage per asteroid: 146 bytes
//total memory Usage 1000048438 bytes
//total memory Usage 0.9541378021240234 MB
//total memory Usage 154.4952392578125 MB