import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

import javax.management.timer.Timer;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends PApplet {
    int scoreId = 0;
    boolean menu = false;
    boolean checkX = true;
    boolean gameOver = false;
    public int score = 0;
    public boolean win = true;
    public static PApplet processing;
    public static ArrayList<Block> blocks = new ArrayList<>();
    PImage pImageCar;
    PImage pImageBg;
    HashMap<Integer, String> hashBg = new HashMap<>();
    String car1 = "target/generated-sources/car/pngfind.com-police-car-png-163831.png";
    String car2 = "target/generated-sources/car/pngfind.com-car-top-view-png-750094.png";
    String car3 = "target/generated-sources/car/pngfind.com-corvette-png-791466.png";
    String car4 = "target/generated-sources/car/pngfind.com-police-car-png-16383.png";
    String car5 = "target/generated-sources/car/PngItem_53999.png";
    String car6 = "target/generated-sources/car/PngItem_5112270.png";
    String car7 = "target/generated-sources/car/PngItem_5121945.png";
    String car8 = "target/generated-sources/car/PngItem_5122003.png";
    String bg1 = "target/generated-sources/Background/peakpx (2).jpg";
    String bg2 = "target/generated-sources/Background/peakpx.jpg";
    String bg3 = "target/generated-sources/Background/peakpx (1).jpg";
    String bg4 = "target/generated-sources/Background/peakpx (3).jpg";
    String bg5 = "target/generated-sources/Background/peakpx (4).jpg";
    String bg6 = "target/generated-sources/Background/peakpx (10).jpg";
    String bg7 = "target/generated-sources/Background/peakpx (7).jpg";
    String bg8 = "target/generated-sources/Background/peakpx (9).jpg";
    String bg9 = "target/generated-sources/Background/peakpx (5).jpg";
    String bg10 = "target/generated-sources/Background/peakpx (6).jpg";

    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    @Override
    public void setup() {
        ///////////////////////////////////////////////////////////////////////
        hashBg.put(1, bg1);
        hashBg.put(2, bg2);
        hashBg.put(3, bg3);
        hashBg.put(4, bg4);
        hashBg.put(5, bg5);
        hashBg.put(6, bg6);
        hashBg.put(7, bg7);
        hashBg.put(8, bg8);
        hashBg.put(9, bg9);
        hashBg.put(10, bg10);
        pImageBg = loadImage(bg6);
        Block.makeBlocks();
        processing = this;
    }

    @Override
    public void settings() {
        size(400, 700);
    }

    @Override
    public void draw() {
        menu();
        if (menu) {
            changeBg();
            image(pImageBg, -20, -20, 440, 730);
            for (Block block : blocks) {
                showBlock(block.getBlockX(), block.getBlockY(),
                        block.getBlockColorR(), block.getBlockColorG(), block.getBlockColorB());
            }
            //////////////////////////////  : text
            fill(30, 255, 0);
            textSize(35);
            text(String.format("score: %s", score), 40, 59);
            text("DogeUp", 230, 59);
            ////////////////////////////////
            scoreUp();
            moveBlock();
            ////////////////////////////// : line
            stroke(100, 200, 50);
            strokeWeight(10);
            line(0, 670, 400, 670);
            //////////////////////////////////
            noFill();
            image(pImageCar, mouseX, 600, 30, 70);
            crashed();
            win();
        }
    }

    public void moveBlock() {
        for (Block block : blocks) {
            block.setBlockY((int) (block.getBlockY() + random((int) 5, 6)));
        }
        if (gameOver) {
            for (Block block : blocks) {
                block.setBlockY(0);
            }
        }
    }

    public void showBlock(int x, int y, int R, int G, int B) {
        fill(R, G, B);
        noStroke();
        rect(x, y, Block.BlockWidth, Block.BlockHeight, 20);
    }

    public void scoreUp() {
        for (Block block : blocks) {
            if ((block.getBlockY() > 670) && (!block.checkBlock)) {
                score += 1;
                block.checkBlock = true;
            }
        }
    }

    public void crashed() {
        for (Block block : blocks) {
            if (!gameOver && block.getBlockX() < mouseX + 25 && block.getBlockX() > mouseX - 25 && block.getBlockY() < 670 && block.getBlockY() > 555) {
                gameOver = true;
                win = false;
            }
        }
        if (gameOver) {
            frameRate(2);
            image(loadImage("target/generated-sources/Background/peakpx (8).jpg"), -20, -20, 440, 730);
            fill(212, 20, 6);
            textSize(50);
            text(String.format("Game Over!\nYour Score: %s", score), 60, 300);
            fill(200, 0, 0);
            textSize(40);
            text("Exit", 165, 615);
            if (mouseX > 140 && mouseX < 250 && mouseY > 560 && mouseY < 640) {
                fill(245, 61, 61);
                textSize(40);
                text("Exit", 165, 615);
                if (mousePressed) {
                    System.exit(0);
                }
            }
        }

    }

    public void win() {
        if (score == blocks.size() && win) {
            frameRate(2);
            image(loadImage("target/generated-sources/Background/peakpx (11).jpg"), -20, -20, 440, 730);
            //////////////////////////////////////////////////////////////////////
            fill(180, 24, 237);
            textSize(40);
            text(String.format("You won!\nCongratulations!\nYour Score: %s", score), 70, 70);
            /////////////////////////////////////////////////////
            image(loadImage("target/generated-sources/a/82573-sonic-boy-shiva-nickelodeon-black-hair-cartoon.png"), 100, 270, 220, 290);
            ///////////////////////////////////////////////////
            fill(200, 0, 0);
            textSize(40);
            text("Exit", 165, 615);
            if (mouseX > 140 && mouseX < 250 && mouseY > 560 && mouseY < 640) {
                fill(245, 61, 61);
                textSize(40);
                text("Exit", 165, 615);
                if (mousePressed) {
                    System.exit(0);
                }
            }
        }
    }

    public void changeBg() {
        if (keyPressed) {
            pImageBg = loadImage((hashBg.get((int) random(1.0F, 10.0F))).toString());
        }
    }

    public void menu() {
        if (!menu) {
            image(loadImage("target/generated-sources/Background/peakpx (10).jpg"), -20, -20, 440, 730);
//            background(63, 204, 209);
            fill(9, 21, 181);
            textSize(70);
            text("DogeUpe!", 55, 100);
            fill(200, 0, 0);
            textSize(35);
            text("Select a car to play!", 60, 185);
            /////////////////////////
            fill(200, 0, 0);
            textSize(40);
            text("Exit", 165, 615);
            /////////////////////////////////
            image(loadImage(car1), 70, 235, 40, 90);
            image(loadImage(car2), 140, 235, 40, 90);
            image(loadImage(car3), 225, 235, 40, 90);
            image(loadImage(car4), 285, 235, 70, 90);
            image(loadImage(car5), 70, 375, 40, 90);
            image(loadImage(car7), 140, 375, 40, 90);
            image(loadImage(car6), 225, 375, 40, 90);
            image(loadImage(car8), 295, 375, 45, 95);
            if (mouseX > 70 && mouseX < 120 && mouseY > 235 && mouseY < 325 && mousePressed) {
                pImageCar = loadImage(car1);
                menu = true;
            } else if (mouseX > 140 && mouseX < 190 && mouseY > 235 && mouseY < 325 && mousePressed) {
                pImageCar = loadImage(car2);
                menu = true;
            } else if (mouseX > 225 && mouseX < 275 && mouseY > 235 && mouseY < 325 && mousePressed) {
                pImageCar = loadImage(car3);
                menu = true;
            } else if (mouseX > 280 && mouseX < 340 && mouseY > 235 && mouseY < 325 && mousePressed) {
                pImageCar = loadImage(car4);
                menu = true;
            } else if (mouseX > 70 && mouseX < 120 && mouseY > 375 && mouseY < 465 && mousePressed) {
                pImageCar = loadImage(car5);
                menu = true;
            } else if (mouseX > 140 && mouseX < 190 && mouseY > 375 && mouseY < 465 && mousePressed) {
                pImageCar = loadImage(car7);
                menu = true;
            } else if (mouseX > 220 && mouseX < 275 && mouseY > 375 && mouseY < 465 && mousePressed) {
                pImageCar = loadImage(car6);
                menu = true;
            } else if (mouseX > 290 && mouseX < 340 && mouseY > 375 && mouseY < 465 && mousePressed) {
                pImageCar = loadImage(car8);
                menu = true;
            }
            if (mouseX > 140 && mouseX < 250 && mouseY > 560 && mouseY < 640) {
                fill(245, 61, 61);
                textSize(40);
                text("Exit", 165, 615);
                if (mousePressed) {
                    System.exit(0);
                }
            }
        }
    }
}
