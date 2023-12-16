package View.EntitiesGraphics.EnemyGraphics;

import Model.EntityModel.Hitbox;
import View.EntitiesGraphics.EntityGraphics;

import java.awt.image.BufferedImage;
import java.util.Observer;

public abstract class EnemyGraphics extends EntityGraphics implements Observer {
    protected Hitbox hitbox;
    protected BufferedImage[] deathAnimation;
    protected boolean death;
    protected int deathIndex, deathTick, deathSpeed =  10;
    protected int sx, sy;
    public EnemyGraphics(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.sx= x;
        this.sy = y;
        loadDeathAnimation();
    }

    private void loadDeathAnimation() {
        BufferedImage temp = loadImg("/entitySprites/enemySprite/morte_nemici/Morte_Nemici_DOWN.png");
        BufferedImage temp1 = loadImg("/entitySprites/enemySprite/morte_nemici/Morte_Nemici_UP.png");

        deathAnimation = new BufferedImage[8];

        deathAnimation[0] = temp1.getSubimage(0,0, 14, 32);
        deathAnimation[1] = temp1.getSubimage(14+1,0, 14, 32);
        deathAnimation[2] = temp1.getSubimage(14+1+15,0, 14, 32);
        deathAnimation[3] = temp.getSubimage(0,0, 22, 32);
        deathAnimation[4] = temp.getSubimage(22,0, 22, 32);
        deathAnimation[5] = temp.getSubimage(43,0, 22, 32);
        deathAnimation[6] = temp.getSubimage(64,0, 18, 32);
        deathAnimation[7] = temp.getSubimage(81,0, 16, 32);

    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public void resetPos() {
        x = sx;
        y = sy;
    }

    public void setDeath(boolean death) {
        this.death = death;
        deathIndex = 0;
        deathTick = 0;
    }
}
