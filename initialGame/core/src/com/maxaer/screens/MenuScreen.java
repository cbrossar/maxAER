package com.maxaer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.maxaer.game.GameWindow;

public class MenuScreen implements Screen
{
   private final GameWindow window; 
   private SpriteBatch batch;
   private OrthographicCamera cam;
   private BitmapFont font;
   private GlyphLayout layout;
   private Skin skin;
   private Stage stage;
   private TextButton playBtn, scoresBtn, registerBtn;
   
   public MenuScreen(GameWindow window){
      cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      cam.setToOrtho(false);
      
      batch = new SpriteBatch();
      
      FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/MonaKo.ttf"));
      FreeTypeFontParameter parameter = new FreeTypeFontParameter();
      parameter.size = 26;
      parameter.color = Color.BLACK;
      generator.generateData(parameter);
      font = generator.generateFont(parameter);
      
      layout = new GlyphLayout();
      
      stage = new Stage();
      Gdx.input.setInputProcessor(stage);// Make the stage consume events

      createBasicSkin();
      playBtn = new TextButton("Play", skin); // Use the initialized skin
      playBtn.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight()/2);
      stage.addActor(playBtn);
      
      addActions(); 
      
      this.window = window; 
   
   }
   
   //Create a skin for the menu button's
   private void createBasicSkin(){
      //Create a font
      skin = new Skin();
      skin.add("default", font);

      //Create a texture
      Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
      pixmap.setColor(Color.WHITE);
      pixmap.fill();
      skin.add("background",new Texture(pixmap));

      //Create a button style
      TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
      textButtonStyle.up = skin.newDrawable("background", Color.WHITE);
      textButtonStyle.down = skin.newDrawable("background", Color.LIGHT_GRAY);
      textButtonStyle.checked = skin.newDrawable("background", Color.LIGHT_GRAY);
      textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
      textButtonStyle.font = skin.getFont("default");
      skin.add("default", textButtonStyle);

   }
   
   public void addActions(){
      playBtn.addListener(new ChangeListener()
      {
         
         @Override
         public void changed(ChangeEvent event, Actor actor)
         {
                window.setScreen(new GameScreen());
                dispose();
            
         }
      });
   }

   @Override
   public void show()
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void render(float delta) {
       Gdx.gl.glClearColor(0.361f, 0.749f, 0.231f, 1);
       
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

       stage.act();
       stage.draw();

       cam.update();
       batch.setProjectionMatrix(cam.combined);

       batch.begin();
       layout.setText(font, "MaxÆR");
       font.draw(batch, "MaxÆR", (Gdx.graphics.getWidth() - layout.width)/2, Gdx.graphics.getHeight()/2 + 100);
      
       batch.end();

      
   }

   @Override
   public void resize(int width, int height)
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void pause()
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void resume()
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void hide()
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void dispose()
   {
      // TODO Auto-generated method stub
      stage.dispose();
      batch.dispose();
   }

}
