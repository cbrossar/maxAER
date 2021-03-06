package com.maxaer.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.Random;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.maxaer.constants.GameConstants;

public class Block extends Shape {
	private Sprite sprite;
	private Body body;
	private Texture texture;   
	final float PIXELS_TO_METERS = GameConstants.PIXEL_TO_METERS;
	private Boolean isSmall = false;
	private Random rand;
	private int pckage;
	
	public Block(World world, int height, Random rand, int pckage)
	{
		this.rand = rand;
		this.pckage = pckage;
		
		//Create the player to have the block image
		texture = new Texture(randomBlockImage());
		sprite = new Sprite(texture);
		//Initialize with position in the middle of the screen
	  
		//Randomly determine whether block is small or large
		float small = rand.nextFloat();
		if(small <= .50f) isSmall = true;
		if(isSmall) sprite.setSize(sprite.getWidth()/2, sprite.getHeight()/2);
		
		int p = rand.nextInt(Gdx.graphics.getWidth());
		sprite.setPosition(/*Gdx.graphics.getWidth()/6, 0*/ p, height);
	  
		//Set the body definition for the player
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.fixedRotation = true;
		
		//Randomize drop location on screen

		int pos = rand.nextInt(Gdx.graphics.getWidth());
		bodyDef.position.set((pos) / PIXELS_TO_METERS,
              (sprite.getY() + sprite.getHeight()/2) / PIXELS_TO_METERS);
      
		//Create the body for the player
		body = world.createBody(bodyDef);
		body.setGravityScale(0);		
		body.setLinearVelocity(0, 3f);
		
      
		//Create the shape for our player
		PolygonShape shape = new PolygonShape();
		shape.setAsBox((sprite.getWidth() - 2)/2 / PIXELS_TO_METERS, (sprite.getHeight() - 3)/2 / PIXELS_TO_METERS);
		//Now add that shape to the body

		FixtureDef boxDef = new FixtureDef();
		boxDef.shape = shape;
		boxDef.density = 1000000f;
		boxDef.restitution = 0f;
		boxDef.friction = 0.1f;
//		boxDef.filter.categoryBits = GameConstants.CATEGORY_BLOCK;
//		boxDef.filter.maskBits = GameConstants.MASK_BLOCK;
		
		body.createFixture(boxDef);

		//Free up the shape here
		shape.dispose();
		
	}
	
	public void updatePosition(){
	      sprite.setPosition((body.getPosition().x * PIXELS_TO_METERS) - sprite.
	            getWidth()/2 ,
	    (body.getPosition().y * PIXELS_TO_METERS) - sprite.getHeight()/2 );
	   }
	
	public Sprite getSprite(){
	      return sprite;
    }
	
	public boolean isSmall() {
		return isSmall;
	}
	
	public Body getBody()
	{
		return body;
	}

	
	public void dispose(){
	   texture.dispose();
	}
	
	@Override
	public String toString(){
		return "Block";
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setPackage(int i) {
		   pckage = i;
	 }
	   
	 public int getPackage() {
		   return pckage;
	 }

	
	
	private String randomBlockImage(){
	   int randomNum = rand.nextInt((8 - 1) + 1) + 1;
	   if(pckage == 1){
		   switch(randomNum){
		   case 1:
			   return GameConstants.ThinMintImg;
		   case 2:
			   return GameConstants.BerryImg;
		   case 3:
			   return GameConstants.ThanksALotImg;
		   case 4:
			   return GameConstants.TrefoilImg;
		   case 5:
			   return GameConstants.DoSiDoImg;
		   case 6:
			   return GameConstants.TagalongImg;
		   case 7:
			   return GameConstants.LemonImg;
		   case 8:
			   return GameConstants.SamoaImg;
			   
		   default:
			   return GameConstants.SamoaImg;
		   	}
		} else if (pckage == 2){
			switch(randomNum){
			   case 1:
				   return GameConstants.KempeImg;
			   case 2:
				   return GameConstants.MillerImg;
			   case 3:
				   return GameConstants.CoteImg;
			   case 4:
				   return GameConstants.ShindlerImg;
			   case 5:
				   return GameConstants.DughmiImg;
			   case 6:
				   return GameConstants.SukhatmeImg;
			   case 7:
				   return GameConstants.RosenbloomImg;
			   case 8:
				   return GameConstants.RedekoppImg;
				   
			   default:
				   return GameConstants.RedekoppImg;
			   	}
		} else if (pckage == 3){
			switch(randomNum){
			   case 1:
				   return GameConstants.SideTrumpImg;
			   case 2:
				   return GameConstants.StraightTrumpImg;
			   case 3:
				   return GameConstants.TrumpImg;
			   case 4:
				   return GameConstants.TrumpAlongImg;
			   case 5:
				   return GameConstants.SmirkImg;
			   case 6:
				   return GameConstants.TrumpALotImg;
			   case 7:
				   return GameConstants.TrumpMouthImg;
			   case 8:
				   return GameConstants.TrumpBerryImg;
				   
			   default:
				   return GameConstants.TrumpBerryImg;
			   	}
		}
		
	   //should never execute
	   return "";
	}

}
