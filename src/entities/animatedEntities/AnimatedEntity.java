package entities.animatedEntities;

import entities.Entity;

public abstract class AnimatedEntity extends Entity {
    
    protected int _animate = 0;

    public AnimatedEntity(int x, int y) {
        super(x, y);
    }

    protected void animate() {
        if (_animate < 60) _animate++;
        else _animate = 0;
    }

}
