package src.main;
import src.entity.entity;

public class CollisionChecker {
    
    GamePanel gp;

    public CollisionChecker(GamePanel gp){//init
        this.gp = gp;
    }
    
    public void checkTile(entity entity){//checks tile and npc collision
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;// gets box in the collision of body
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
        case "up":
            entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;//predicting where the player will be moved
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];//checks left side to see where it ends up
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];//checks right side to see where it ends up
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){//if touch
                entity.collisionOn = true;
            }
            break;

        case "down":
            entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;//predicting where the player will be moved
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];//checks left side to see where it ends up
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];//checks right side to see where it ends up
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){//if touch
                entity.collisionOn = true;
            }

            break;
        case "left":
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;//predicting where the player will be moved
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];//checks left side to see where it ends up
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];//checks right side to see where it ends up
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){//if touch
                entity.collisionOn = true;
            }
            break;
        case"right":

            entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;//predicting where the player will be moved
            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];//checks left side to see where it ends up
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];//checks right side to see where it ends up
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){//if touch
                entity.collisionOn = true;
            }
            break;
        }


    }
}
