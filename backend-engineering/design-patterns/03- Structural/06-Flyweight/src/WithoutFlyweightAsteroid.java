package designPattern.structural.flyweight;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

public class WithoutFlyweightAsteroid{

    static void main(String[] args) {
        int count = 1000000;
        System.out.println("without Flyweight pattern");
        SpaceGame game = new SpaceGame();
        game.createAsteroid(count);
        game.getTotalAsteroids();
        game.renderAll();
        game.getTotalMemory();


    }

}
class Asteroid{
    int length;
    int width;
    int height;
    double weight;
    String color;
    String texture;
    String material;

    int posx,posy;
    int velx,vely;

    private Asteroid(){
        System.out.println("Object can be created using Builder");
    }

    public Asteroid(AsteroidBuilder builder){
        this.length = builder.length;
        this.width = builder.width;
        this.height = builder.height;
        this.weight = builder.weight;
        this.color = builder.color;
        this.texture = builder.texture;
        this.material = builder.material;
        this.posx = builder.posx;
        this.posy = builder.posy;
        this.velx = builder.velx;
        this.vely = builder.vely;
    }
    public static AsteroidBuilder builder(){
        return new AsteroidBuilder();
    }


    public static class AsteroidBuilder{
        int length;
        int width;
        int height;
        double weight;
        String color;
        String texture;
        String material;

        int posx,posy;
        int velx,vely;

        public AsteroidBuilder setVely(int vely) {
            this.vely = vely;
            return this;
        }

        public AsteroidBuilder setVelx(int velx) {
            this.velx = velx;
            return this;
        }

        public AsteroidBuilder setPosy(int posy) {
            this.posy = posy;
            return this;
        }

        public AsteroidBuilder setPosx(int posx) {
            this.posx = posx;
            return this;
        }

        public AsteroidBuilder setMaterial(String material) {
            this.material = material;
            return this;
        }

        public AsteroidBuilder setTexture(String texture) {
            this.texture = texture;
            return this;
        }

        public AsteroidBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public AsteroidBuilder setWeight(double weight) {
            this.weight = weight;
            return this;
        }

        public AsteroidBuilder setHeight(int height) {
            this.height = height;
            return this;
        }

        public AsteroidBuilder setWidth(int width) {
            this.width = width;
            return this;
        }

        public AsteroidBuilder setLength(int length) {
            this.length = length;
            return this;
        }

        public Asteroid build(){
            return new Asteroid(this);
        }
    }

    static int getMemoryUsage(){
        int totalMemory = (Integer.BYTES* 7) + (Double.BYTES * 1)+ (10*3) + (32*3);
        System.out.println("Memory usage per asteroid: " + totalMemory + " bytes");
        return totalMemory;
    }

    void render(){
        System.out.printf(
                "Color %s, texture %s, material %s, \n Asteroid at position %s , %s, \n Size %s * %s *%s, \n Velocity %s , %s\n"
                ,color,texture,material,posx,posy,length,width,height,velx,vely);
    }

}


//Game Class

class SpaceGame{

    List<Asteroid> asteroids = new ArrayList<>();

    public void createAsteroid( int count) {
        List<String> colors = List.of(new String[]{"Red", "Blue", "Gray"});
        List<String> textures = List.of(new String[]{"Rough", "Smooth", "Icy"});
        List<String> materials = List.of(new String[]{"Iron", "Nickel", "Ice"});
        int[] size = {25, 35, 45};

        System.out.println("Creating " + count + " asteroids...");
        for (int i = 0; i < count; i++) {
            int type = i % 3;
            Asteroid asteroid = new Asteroid.AsteroidBuilder()
                    .setColor(colors.get(type))
                    .setHeight(size[type])
                    .setLength(size[type])
                    .setMaterial(materials.get(type))
                    .setPosx(100 +i*50)
                    .setPosy(200+i*30)
                    .setVelx(1)
                    .setVely(2)
                    .setTexture(textures.get(type))
                    .setWeight(size[type]*10)
                    .setWidth(size[type])
                    .build();
            asteroid.render();
            asteroids.add(asteroid);
        }
        System.out.println("Created " + asteroids.size() + " asteroids.");
    }
     void renderAll(){
         System.out.println("Rendering asteroids...");
        for (int i=0 ;i< min(5,asteroids.size());i++) {
           asteroids.get(i).render();
        }
    }

    void getTotalMemory(){
        System.out.println(" total memory Usage " +asteroids.size() * Asteroid.getMemoryUsage() +" bytes");
         System.out.println(" total memory Usage " +asteroids.size() * Asteroid.getMemoryUsage()/(1024.0*1024.0) +" MB");
    }

    void getTotalAsteroids(){
        System.out.println(" total asteroids usage " +asteroids.size());
    }
}

