import processing.core.PApplet;

public class Block implements Interface{
    public static PApplet a = Main.processing;
    public static int speedY = -80;
    public static int BlockWidth = 25;
    public static int BlockHeight = 60;
    public boolean checkBlock = false;
    private int BlockX;
    private int BlockY;
    private int BlockColorR;
    private int BlockColorG;
    private int BlockColorB;

    public Block(int blockX, int blockY, int blockColorR, int blockColorG, int blockColorB) {
        BlockX = blockX;
        BlockY = blockY;
        BlockColorR = blockColorR;
        BlockColorG = blockColorG;
        BlockColorB = blockColorB;
    }


    public static void makeBlocks() {
        for (int i = 0; i < 20; i++) {
            a = new PApplet();
//            int b =Integer.parseInt(String.valueOf(a.random(122,190)));
            Main.blocks.add(new Block((int) a.random(80), speedY, 112, 34, 56));
            speedY -= 50;
            Main.blocks.add(new Block((int) a.random(80,150), speedY, 170, 100, 56));
            speedY -= 50;
            Main.blocks.add(new Block((int) a.random(160,240), speedY, 190, 119, 200));
            speedY -= 50;
            Main.blocks.add(new Block((int) a.random(250,300), speedY, 110, 76, 100));
            speedY -= 50;
            Main.blocks.add(new Block((int) a.random(300,400), speedY, 110, 76, 100));
            speedY -= 50;
        }
    }

    public int getBlockY() {
        return BlockY;
    }

    public void setBlockY(int blockY) {
        BlockY = blockY;
    }

    public int getBlockX() {
        return BlockX;
    }

    public int getBlockColorR() {
        return BlockColorR;
    }

    public int getBlockColorG() {
        return BlockColorG;
    }

    public int getBlockColorB() {
        return BlockColorB;
    }

    @Override
    public void ShowObject() {

    }
}
