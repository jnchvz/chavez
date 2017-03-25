# RPG Principal functionalities

Welcome ! This is a RPG game in which you can walk using your keyboard, you can go up, down, right and left. You can attack using the space bar, you can run using shift, and pause the game using the key i in order to look your inventory menu. In this one, you can see your weapons and some objects that you can eat (an apple for example). This inventory will show you what type of object do you have, and will let you know the weight of it, so you know how much objects you can stock or if necessary, you can delete some of them and destock your inventory. Or in case of weapons, you can choose one, for example a gun and then use it to attack.

During the game, there will be some Zombies that will follow you and try to attack you (only if they got to see you or if you are near from them) so you can attack them too, using the space bar. Also, while you walk in the game, you can found objects and pick them so you can add them and update to your inventory, using the key e.

## Principal problems

During the creation and production of the game, I found specially difficult the graphic part, because I'm not familiar with maps, sprites and tiles. I have implemented JSON to read more easily the sprites sheet, but anyway it was hard to understand because I wasn't familiarized with it at all. There's a showing bug, I'm getting a IO exception but after trying a lot, I couldn't find the solution although, the code seems to be okay.

Another problem was the time. I thought I would finished the proyect before the 23 mars, but there where some things during coding the game that made me stay longer and lose time etc, for example, this report I started it willing to explain every step I did and try to make it usable for other persons in the future (if they wanted to make a RPG game too) but I lost a lot of time redacting it so I couldn't finished it as I wanted or expected to. 

And also, I started coding the game in spanish as it was more easily for me at the beginning, and then I started to make the english version of it, but surprise.. the time was killing me so I couldn't finish this last version. So I tried to put somme english comments in the original spanish code.

In the other hand, I found very interesting using the Dijkstra Algorithm explained at the end of the report, because it allowed me to achieve making the game more interactive (because thanks to it, the zombies can follow the principal character)

## This is not the end

I'm very happy that I had the opportunity of creating this RPG game because I think I have learned a lot, more than I expected and this experience is giving me the willing of continue exploring this field. Upset by not managing efficiently the time when producing the game, I think this is also giving me the experience to better manage it for future proyects.

## Practical information

As the original code I wrote it in spanish, I tried to leave comments with their meaning in english. For the packages and classes inside the proyect you will find:

The package principal contains: 

* Gestor principal = lead manager of the game
* ElementosPrincipales = principal elements of the game
* Constantes = constants

* Package control and contains:

-GestorControles = controls manager

-Raton = mouse

-Tecla = key

-Teclado = keyboard


* Package dijkstra and contains:

-Dijkstra = algorithm 

-Nodo = node


* Package entidades = entities and contains:

-AlmacenEquipo = stockage equipment

-Enemigo = enemy

-Jugador = player

-RegistroEnemigos = enemies register

-Zombie = zombie


* Package graficos = graphics and contains:

-SuperficeDibujo = draw surface

-Ventana = window


* Package herramientas = tools and contains:

-CalculadoraDistancia = distance calculator

-CargadorRecursos = resources loader

-DatosDebug = debug data information

-DibujoDebug = debug draw

-EscaladorElementos  = scale elements

-GeneradorTooltip = tooltip generator

-MedidorStrings = strings meter


* Package interfaz_usuario = user interface and contains:

-MenuInferior = lower menu


* Package inventario = inventory and contains:

-ContenedorObjetos = objects container

-Inventario = inventory

-Objeto = object

-ObjetoUnicoTiled = unique tiled object

-RegistrosObjetos = objects register

-Armas package = weapons package

-Consumibles package = objects you can eat package


* Package mapas = maps and contains:

-CapaColisiones = collisions layer

-CapaSprites = sprites layer

-CapaTiled = tiled layer

-Mapa = map

-MapaTiled = tiled ma

-Tile = tile


* Package maquinaestado = state of the machine and contains:

-Estadojuego = game state or status

-GestorEstados = states manager

-Estados package = states or status package and contains:

 -GestorJuego = game manager
 
 -EstructuraMenu = menu structure
 
 -MenuEquipo = menu equipment
 
 -MenuInventario = inventory menu
 
 -SeccionMenu = menu section


* Package sonidos = sounds and contains:

-Sonido = sound

* Package sprites and contains:

-HojaSprites = sprites sheet

-Sprite = sprite

# Making the RPG Game: 

* Create a new java project
* Create a new **package** called **principal**
* Create the class **LeadManager** which will contain the **main method**

Inside the "principal" package we will create other packages as well:

* principal.**control**, which will contain the following classes:

-**KeyBoard**

We continue adding other packages as:

* **principal.entities** 
* **principal.maps**
* **principal.sprites**
* **principal.graphics**
* **principal.tools**
* **principal.statemachine**

(we will add some classes later into these packages)

Now, inside the LeadManager class: we will **set the title, the width and the height** of the window's game and also, we will create a constructor using:
* **private LeadManager (final String title, final int width, final int height);**

The constructor is private because thanks to that, any other class won't be able to create another Lead Manager in the game.

The next step is to initialize the game using the main method and **setting the values:**
* **LeadManager ld = new LeadManager("Chavez-RPG-game", 640, 360);**

Then create 2 methods for:
* **ld.startGame();**
* **ld.startPrincipalLoop();**

-The first method will be:
**private void startGame()** and in here we'll set as true the "workingWell" boolean and initialize the **initialize()** method

-The second method will be:
**private void startPrincipalLoop()**

Then we are gonna create as well the private void initialize() method

Now, we go to the graphics package and create:
* A new **class** called **DrawSurface** which will **extends** the **Canvas** (in here we will put all the graphics of the game)
* Another **class** called **Window** which will **extends** the **JFrame** from swing

In the statemachine package we are going to create a new class called StateManager

We get back into the LeadManager class and we add the 3 components:
* **private SurfaceDraw sd;**
* **private Window window;**
* **private StateManager sm;**

We import each one and then we initialize them (using initialize() method that we have just created before):
* **sd = new SurfaceDraw();**
* **window = new Window();**
* **sm = new StateManager();**

For the moment we'll leave them like this but we are going to get back and add some parameters later

## The game loop

We initialize the **startPrincipalLoop()** method

The while statement continually executes a block of statements while a particular condition is true. 
Using this method at this point is important because it will allow us to run indefinitely 2 methods, one of them, in charge of giving us the updates of the game, and the other one, will be drawing the graphics of it.  

So here, we will need to: 
* Use a **boolean** that tell us if the game is running or not (initialized as **false by default**) using : **private boolean workingWell = false**
* Initialize it as **true** into the **start method** 
* Create the **while** statement into the **main method**

## Timer and FPS counter

We will start by creating 2 methods that will be in charge of controlling almost all the content of the game (variables and graphics):
* **private void update();** method in which we will put all the variables related with the **life** of the game characters, etc
* **private void draw();** method in which we will put all the necessary methods in order to show all the **graphics**

Once we do that, we will need to create a timer. Why ? Because if we want the game to works uniformly in every computer, we should run these 2 methods (update and draw) at a specific number of times so we could be able to control it.

In order to achieve this, we will need to use: 
* The method **System.nanoTime()**

The advantage of using this method is that it counts in nano seconds (taking the number of clock cycles processor CPU as reference) and it doesn't depend on the operating system but the microprocessor.

1 Second = 1,000,000,000 Nanoseconds

Having this in mind, we will create some variables in order to define :
* The equivalent of nanoseconds per second, using **final int NS_PER_SECOND = 1000000000;**
* The objective of the number of times we want to update per second, using **final byte UPS_OBJECTIVE = 60;**
* Calculating how many nanoseconds we must need in every update, using **final double NS_PER_UPDATE = NS_PER_SECOND / UPS_OBJECTIVE;**

ns= Nanoseconds

ups= Updates per second

We use final because it means they won't change during the whole execution of the program, so it will starts in every start of the loop. Then, in order to give or atribute a number of nanoseconds given in a specific moment or instant, we will need to use:
* **long updateReference = System.nanoTime();**

In graphics programming, the term Delta is usually used for variably updating scenery based on the elapsed time since the game last updated. Source: https://en.wikipedia.org/wiki/Delta_timing

We will:
* Stock inside Delta, the result of dividing the time lapsed between the nanoseconds per update. And if it comply the condition (be superior or equal to 1) it will run the while statement. So, while Delta is = or superior to 1 it will update the game and then sustract 1 to delta. This means the game will update exactly 60 times per second.

Once we have done all this, we will create:
* an **UPS counter**
* a **FPS counter** 
* show them with a **System.out.println("FPS: " + fps +  " || UPS: " + ups);**

## State Manager

StateManager directs the process of saving and restoring the view between requests. An implementation of this class must be thread-safe. The StateManager instance for an application is retrieved from the Application instance, and thus cannot know any details of the markup language created by the RenderKit being used to render a view. The StateManager utilizes a helper object (ResponseStateManager), that is provided by the RenderKit implementation and is therefore aware of the markup language details.

Source: http://docs.oracle.com/javaee/6/api/javax/faces/application/StateManager.html

In a few words, the state manager is a mechanism that will control the part of the game in which we are (in the local map, in a menu, etc). So inside the state manager we will:
* Control in which state of the game we are
* Update this state and make it draw itself

In order to achieve this, we are going to:
* Use an **interface called GameState** inside the statemanager package

An interface is a group of related methods with empty bodies. objects define their interaction with the outside world through the methods that they expose. Methods form the object's interface with the outside world; the buttons on the front of your television set, for example, are the interface between you and the electrical wiring on the other side of its plastic casing. You press the "power" button to turn the television on and off.

Source: https://docs.oracle.com/javase/tutorial/java/concepts/interface.html

The interface is alwas public and abstract, so the good practice is to not write it in the following step

The **GameState interface** will contain **2 methods**:
* **void update();**
* **void draw();**

The method draw will need to acept a graphic object which we will use to draw:
* **void draw(final Graphics g);**
* then we **import Graphics**

Why use an interface instead of a super class ? 

Because an interface defines common functionality across unrelated classes. For example, all sorts of classes that look nothing like each other may have the need to safely get rid of the resources they use. (like what we're doing with update and draw) so an interface is a guarantee that certain functionality will work in a standardized way. 

Source: http://www.techrepublic.com/blog/software-engineer/when-to-use-interfaces-instead-of-classes/

Now we are going to:
* Create an array conteining all the states of the game inside the StateManager class, using **private GameState[] states;**
* Use **private GameState actualState;** in order to make the state we are using at the moment, the actual state of the game (the menu state etc)
* Build the **StateManager constructor**
* Create a startStates method, using **states = new GameState[]** and giving it a value (for the array)
* Create a new state in each position of the array, using:

-For the zero position, we'll use the **GameManager class** contained in a new package called "game"

(for this new package, we are going to create it inside the statemachine package, creating a sub-package called "states" and then containing also others packages inside "states" as exemple: intitialmenu, **game**, gamemenu ...

The GameManager class will execute the game while we are moving or fighting in the game that means while we are not in the principal menu etc but in order to use it also inside our array, we need **implement GameState** in the GameManager class and **add the abstract methods** of the interface: **update** and **draw**. 

Once inside the StateManager we will use **states[0] = new GameManager();** and we'll import the GameManager class using: **import principal.statemachine.states.game.GameManager;**

We will initialize the **private GameState actualState;** which is the variable that will execute the current state of the whole game using **startActualState();**. Then create also the method **private void startActualState()** in which we'll put **actualState = states[0];** in order to indicate that the actual or current state will correspond to the GameManager value.

Now, we are going to add the methods: 
* **public void update()**
* **public void draw(final Graphics g)**

We add these 2 methods so we can update and draw in our actualState variable using: 
* **actualState.update();**
* **actualState.draw(g);**

So far, we can know what state is the actualState, but if we cant to change it we'll need to:
* Create a **public void changeActualState()** which will accept a **(final int newState)** and in here we are going to have acces to a new state from the racine states, using **actualState = states[newState];** (for future states in the game)

Getting back to the **LeadManager** class, we are going to make the Statemanager **sm** able to **update** and **draw** using:
* **sd.getKeyBoard().update();** //the keyboard updates 60 times per second
* **sm.update();** // updating the StateManager
* **sd.draw(sm);** // we'll use it later at the SurfaceDraw step

## The Window

We're going to need the serialID (the identification number which indicates java if the class has been changed or not), getting : **private static final long serialVersionUID = -9158027519536291681L;**

Inside the window we'll have a **String** for the **title** and then we are going to create a constructor for Window, containing the title and the SurfaceDraw sd, using : **public Window(final String title, final SurfaceDraw sd)** (final because they won't change their value).

We will also create another method called **configurateWindow** with sd as a parameter, using: **private void configurateWindow(final SurfaceDraw sd)** and **configurateWindow(sd);** inside the constructor.

Inside the method configurateWindow we will be able to:
* Set the tittle of the window, using **setTitle(title);**
* Close and stop the program once we close the window, using **setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);**
* Unable the user to resize the window, using **setResizable(false);**
* Set the icon of the game, using **setIconImage();**
* Set the layout, using **setLayout(new BorderLayout());** and importing it (for the intern disposition of the window) and also we are going to add it the surface draw **sd**. sd will be the Canvas we are going to use for drawing the game, and will be placed in the center of the window, using for it: **add(sd, BorderLayout.CENTER);**, so this way the canvas will have the same size as the window.
* Erase the window borders using **setUndecorated();**
* Use **pack();** in order to give the right format to the window
* Stablish the window in the center, using **setLocationRelativeTo(null);**
* Make the window visible using **setVisible(true);**

Now we get back into the **LeadManager class** and we add in the object **new Window** the parameters title and sd, using: **window = new Window(title, sd);**

## Tools

Inside the tools package we are going to create a new class called **ResourcesCharger** in which class we'll obtain future images, sounds ... so for the moment we will put images, using **public static BufferedImage loadCompatibleOpaqueImage(final String path)**, the path is necessary so we can be able to load the image. Also we need to import the BufferedImage as well.

The BufferedImage needs to be configurate according the graphic card and monitor.

In order to read the image we will need to use **image = ImageIO.read(ClassLoader.class.getResource(path));** path as a parameter of the method.

So we import as well: 
* **import java.awt.Image;**
* **import javax.imageio.ImageIO;**

Then, in case the file is not available, we will surround it with a **try catch**.
Now, we are going to start creating the compatible image by using:
* The **GraphicsConfiguration gc**, which will return us the value of the graphic configuration of the monitor we are using at that moment : **GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();**
* Creating a BufferedImage with the same size as the image we have just loaded before, using: **BufferedImage acceleratedImage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null));** (Width and Height as parameters)
* Creating some **Graphics g** (and being draw inside the acceleratedImage), using : **Graphics g = acceleratedImage.getGraphics();** and then drawing it using **g.drawImage(image, 0, 0, null); // image, x, y, observer** and finally, we'll remove g because we don't need it anymore, using **g.dispose();** and **return** the **acceleratedImage;**

The we do the same thing but with **public static BufferedImage loadCompatibleTranslucentImage(final String path)**

Why OpaqueImage ? for the scenario
Why TranslucentImage ? for the characters

## SurfaceDraw

A Canvas component represents a blank rectangular area of the screen onto which the application can draw or from which the application can trap input events from the user. An application must subclass the Canvas class in order to get useful functionality such as creating a custom component. The paint method must be overridden in order to perform custom graphics on the canvas.

Source: https://docs.oracle.com/javase/7/docs/api/java/awt/Canvas.html

First of all, we need to include the serialID inside the SurfaceDraw class, resulting in: **private static final long serialVersionUID = 5134851650990907399L;** and then use at least 3 variables which are:
* **private int width;**
* **private int height;**
* **private KeyBoard keybard;**, then import the Key class using **import principal.control.KeyBoard;**

We proceed to create th **constructor** using **public SurfaceDraw(final int width, final int height)** (width and height as parameters) and then we initialize them inside using the following: 
* **this.width = width;**
*	**this.height = height;**
* **keyboard = new KeyBoard();**

And then we'll do the following actions as well:
* **setIgnoreRepaint(true);** for optimizations and other versions of java
* **setPreferredSize(new Dimension(width,height));** using as parameters width and height, and then importing Dimension.
* **addKeyListener(keyboard);** to start recording which keys are being use
* **setFocusable(true);**for compatibility
* **requestFocus();** once the game started the canvas will be automatically focus

Now, we will use the **method draw** (with a stateManager called **sm** inside as a parameter) and then importing it as well. So we have: **public void draw(final StateManager sm)**

Then we'll make the configuration for the existence of a buffer in the canvas, using : 
* **BufferStrategy buffer = getBufferStrategy();** and importing it (this will be a space of memory in which we will draw the images shown in the monitor later)
* An **if statement** to know if the buffer is null or not. If yes, we will create the bufferStrategy using **createBufferStrategy(3);** and then returning it. The number inside the (3) indicates how many images will be stocked in the memory before showing them in the monitor.

Next, we are going to create an object, using:
* **Graphics g = buffer.getDrawGraphics();** in which we'll be drawing inside the buffer, and this will decide in which of the many or in this case (3)images will do it
* **g.setColor(Color.blue);** and importing Color
* **g.fillRect(0, 0, width, height);** //(x, y, width, height)

Then we need to:
* call the method draw from StateManager, using **sm.draw(g);** // we are using the same object g, saving memory
* **Toolkit.getDefaultToolkit().sync();** here this will try to draw only in the updates ofthe window, this is usefull because helps to prevent graphic bugs.
* **g.dispose();**, remove g because we don't need it anymore and to liberate space
* **buffer.show();** show the buffer content dynamically 
* get access to the private variables using: 
-**public KeyBoard getKeyBoard()**, then **return keyboard;**
-**public int getWidth()**, then **return width;**
-**public int getHeight()** then **return height;**

Now we get back to the LeadManager and add width and height inside the **sd = new SurfaceDraw(width,height);** as parameters
then filling the update and draw methods: 
* **sd.getKeyBoard().update();** //the keyboard updates 60 times per second
* **sm.update();** // updating the StateManager
* **sd.draw(sm);** // we'll use it later at the SurfaceDraw step

## Sprites

In the sprite package we will:
* Create a new class called Sprite, which will stock the sprites

A sprite is a single graphic image that is incorporated into a larger scene so that it appears to be part of the scene. Sprites are a popular way to create large, complex scenes as you can manipulate each sprite separately from the rest of the scene. This allows for greater control over how the scene is rendered, as well as over how the players can interact with the scene.

It is not uncommon for games to have tens to hundreds of sprites. Loading each of these as an individual image would consume a lot of memory and processing power. To help manage sprites and avoid using so many images, many games use spritesheets. When you put many sprites into a single image, you get a spritesheet. Spritesheets are used to speed up the process of displaying images to the screen; It is much faster to fetch one image and display only a part of that image than it is to fetch many images and display them.

Source:https://gamedevelopment.tutsplus.com/tutorials/an-introduction-to-spritesheet-animation--gamedev-13099

Inside the Sprite class we will write: 
* **private final BufferedImage image;** in order to stock the sprite as an image, then import BufferedImage
* **private final int width;**
* **private final int height;**
* Build the constructor, using **public Sprite (final BufferedImage image)**
* Initialize the image using **this.image = image;**
* Get width using **width = image.getWidth();**
* Get height using **height = image.getHeight();**

Then, we will use 2 auxiliar methods:
* **public BufferedImage getImage()** in order to get access to the image and return it
* get width, using ** public int getWidth()** and then returning it
* get height, using **public int getHeight()** and then returning it

## Maps and Tiles

Inside the maps package we will create a new **class** called **Tile**. The tile represents a space in the map and usually has a sprite as it's draw.

* **private final Sprite sprite;** here each tile will have it's own sprite
* **private final int id;**, a unique identification for each tile
* **private boolean solid;**, that defines if the tile will be collidable or not

Now, we are going to build a constructor that will require the sprite and and id as parameter :
* **public Tile(final Sprite sprite, final int id)**
* Initialize the sprite using **this.sprite = sprite;**
* Initialize the id using **this.id = id;**
* Setting the solid as false, in order to make the tiles not collidables by default

Next, we will create a second constructor with the same features but, we will add the boolean if we want to change the solid.
Adding : **this.solid = solid;**

So, in order to get the sprites and the id, we will need to use: 
* **public Sprite getSprite()** and then return it
* **public int getId()**, then return it

And also, use a method in order to change a tile into a solid one if it's necessary later, using **public void stablishSolid(final boolean solid)** and initializing it using **this.solid = solid;**

## Sprites sheet

Inside the sprites package, we are going to create a new class called SpritesSheet. In this class we'll be trying to manage all our sheets of sprites. We are going to create some variables as inside this class:
* **final private int widthSheetInPixels;** // image measures
*	**final private int heightSheetInPixels;** // image measures
*	**final private int widthSheetInSprites;**
*	**final private int heightSheetInSprites;** // will tell us how many sprites we have in x and y
*	**final private int widthSprites;** // measures of every sprite
*	**final private int heightSprites;** // measures of every sprite

We will also need an array of sprites:
* **final private Sprite[] sprites;**, so we take the image, the load it and read all the sprites on it and then save them into an array

Now, we can build the constructor using: 
* **public SpritesSheet(final String path, final int SpritesSize, final boolean opaqueSheet)**
* Then initializing the image: **final BufferedImage image;**
* Using an if statement in order to know if we will be using an opaque or translucent image
* Get the width of the sheet in pixels using **widthSheetInPixels = image.getWidth();**
* Get the height of the sheet in pixels using **heightSheetInPixels = image.getHeight();**
* Get the width of the sheet in sprites using **widthSheetInSprites = widthSheetInPixels/SpritesSize;**
* Get the height of the sheet in sprites using **heightSheetInSprites = heightSheetInPixels/SpritesSize;**
* Initializing the width and the height of the sprites, using **widthSprites = SpritesSize;** and **heightSprites = SpritesSize;**
* Initializing the array, using **sprites = new Sprite[widthSheetInSprites * heightSheetInSprites];**

Next, we will use a new method for extracting/filling the sprites from the sheet and send them into the array:
* **private void fillSpritesFromImage(final BufferedImage image)** and initialize the method in each constructor using **fillSpritesFromImage(image);**
* Then, we will use 2 for, which will allow us tu know the ubication of the sheet from 0,0
* **final int positionX = x * widthSprites;** in order to know the position x of the sprite
* **final int positionY = y * heightSprites;** in order to know the position y of the sprite
* **sprites[x+y*widthSheetInSprites] = new Sprite(image.getSubimage(positionX, positionY, widthSprites, heightSprites));**  creating a new sprite, subimage that extracts a smaller image from other image

Also, we will have 2 methods: 
* The first one, a get method in order to know to which sprite we want to get access directly: **public Sprite getSprite(final int index)** and then asking it to **return sprites[index];**
* A second one, a overloaded method using coordinates: **public Sprite getSprite(final int x, final int y)** and asking it to **return sprites[x+y*widthSprites];**

So far, the code will look like this for the **LeadManager Class** :

```java 
package principal;

import principal.graphics.SurfaceDraw;
import principal.graphics.Window;
import principal.statemachine.StateManager;

public class LeadManager {
	
	private boolean workingWell = false;
	private String title;
	private int width; // width of the window
	private int height; // height of the window
	
	private SurfaceDraw sd;
	private Window window;
	private StateManager sm;

	//building the constructor
	
	private LeadManager(final String title, final int width, final int height) {
	
		this.title = title;
		this.width = width;
		this.height = height;
		
	}
	
	// initializing the game
	public static void main(String[] args) {
		LeadManager ld = new LeadManager("Chavez-RPG-game", 640, 360);
		
		ld.startGame();
		ld.startPrincipalLoop();
	}
	
	// creating 2 methods
	private void startGame() {
		
		workingWell = true;
		initialize();
		
	}
	
	private void initialize() {
		sd = new SurfaceDraw(width,height);
		window = new Window(title, sd);
		sm = new StateManager();
	}
	
	private void startPrincipalLoop() {
		
		int ups = 0;
		int fps = 0;
		
		final int NS_PER_SECOND = 1000000000;
		final byte UPS_OBJECTIVE = 60;
		final double NS_PER_UPDATE = NS_PER_SECOND / UPS_OBJECTIVE;

		long updateReference = System.nanoTime();
		long counterReference = System.nanoTime();

		double timeLapsed;
		double delta = 0;

		while (workingWell) {
			final long startLoop = System.nanoTime(); // initializing the
														// chronometer

			timeLapsed = startLoop - updateReference; // calculates the time
														// lapsed between both
														// system.nanoTimes
			updateReference = startLoop;

			delta += timeLapsed / NS_PER_UPDATE;

			while (delta >= 1) {
				update();
				ups++; //adding the ups
				delta--; // equivalent to delta = delta - 1
			}

			draw();
			fps++; //adding the fps
			if (System.nanoTime() - counterReference > NS_PER_SECOND) {
				System.out.println("FPS: " + fps +  " || UPS: " + ups);
				ups = 0;
				fps = 0;
				counterReference = System.nanoTime();
			}
		
	}

}
	
	private void update() {
		sd.getKeyBoard().update(); //the keyboard updates 60 times per second
		sm.update();// updating the StateManager
	}
	
	private void draw() {
		sd.draw(sm);
	}
	
	}

```

The code will look like this for the **ResourcesLoader Class** :

```java 

package principal.tools;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourcesLoader {
	public static BufferedImage loadCompatibleOpaqueImage(final String path) { //BufferedImage configuration according the graphic card and monitor 
		Image image = null;
		
		try {
			image = ImageIO.read(ClassLoader.class.getResource(path));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		// creating the compatible image
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
	
		BufferedImage acceleratedImage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), Transparency.OPAQUE); // creating a BufferedImage (that will never be transparent but opaque) with the same size as the image we have just loaded before
	
		Graphics g = acceleratedImage.getGraphics();
		g.drawImage(image, 0, 0, null); // image, x, y, observer 
		g.dispose(); // remove g because we don't need it anymore
		
		return acceleratedImage;
	}
	
	public static BufferedImage loadCompatibleTranslucentImage(final String path) {
		
Image image = null;
		
		try {
			image = ImageIO.read(ClassLoader.class.getResource(path));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		// creating the compatible image
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
	
		BufferedImage acceleratedImage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), Transparency.TRANSLUCENT); // creating a BufferedImage (that will be translucent/ some transparent areas) with the same size as the image we have just loaded before
	
		Graphics g = acceleratedImage.getGraphics();
		g.drawImage(image, 0, 0, null); // image, x, y, observer 
		g.dispose(); // remove g because we don't need it anymore
		
		return acceleratedImage;
		
	}
}

```

The code will look like this for the **StateManager Class** :

```java
package principal.statemachine;

import java.awt.Graphics;

import principal.statemachine.states.game.GameManager;


public class StateManager {
	
	private GameState[] states;
	private GameState actualState;
	
	// constructor
	
	public StateManager() {
		startStates();
		startActualState();
	}

	private void startStates() {
		
		states = new GameState[1]; //give a value to the array, increment it as we create new states
		states[0] = new GameManager(); // add and initialize the other states as we create them
	}
	
	private void startActualState() {
		
		actualState = states[0];
		
	}
	
	public void update() {
		actualState.update();
	}

	public void draw(final Graphics g) {
		actualState.draw(g);
	}
	
	public void changeActualState(final int newState) {
		actualState = states[newState];
	}
	
	public GameState getActualState() {  //to verify
		return actualState;
	}
}

```

The code will look like this for the **GameState interface** :

```java
package principal.statemachine;

import java.awt.Graphics;

public interface GameState {
	// 2 methods
	void update();
	void draw(final Graphics g);

}

```

The code will look like this for the **GameManager Class** :

```java

package principal.statemachine.states.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import principal.sprites.SpritesSheet;
import principal.statemachine.GameState;

public class GameManager implements GameState {

	public void update() {
		
	}

	public void draw(Graphics g) {
		
	}


}

```

The code will look like this for the **Sprite Class** :

```java

package principal.sprites;

import java.awt.image.BufferedImage;

public class Sprite {
	private final BufferedImage image;
	
	private final int width;
	private final int height;
	
	// constructor
	
	public Sprite (final BufferedImage image) { //each sprite will have it's own class Sprite
		this.image = image;
		
		width = image.getWidth();
		height = image.getHeight();
	}
	
	public BufferedImage getImage() { // get access to the image
		return image;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}

```

The code will look like this for the **SpritesSheet Class** :

```java

package principal.sprites;

import java.awt.image.BufferedImage;

import principal.tools.ResourcesLoader;

public class SpritesSheet {
	
	final private int widthSheetInPixels; // image measures
	final private int heightSheetInPixels; // image measures
	
	final private int widthSheetInSprites;
	final private int heightSheetInSprites; // will tell us how many sprites we have in x and y

	final private int widthSprites; // measures of every sprite
	final private int heightSprites; // measures of every sprite
	
	final private Sprite[] sprites; // we take the image, the load it and read all the sprites on it and then save them into an array
	
	// Constructor
	
	public SpritesSheet(final String path, final int SpritesSize, final boolean opaqueSheet) {
	
		// initializing image
		final BufferedImage image;
		
		if(opaqueSheet) {
			image = ResourcesLoader.loadCompatibleOpaqueImage(path);
		} else {
			image = ResourcesLoader.loadCompatibleTranslucentImage(path);
		}
		
		widthSheetInPixels = image.getWidth();
		heightSheetInPixels = image.getHeight();
		
		widthSheetInSprites = widthSheetInPixels/SpritesSize;
		heightSheetInSprites = heightSheetInPixels/SpritesSize;
		
		widthSprites = SpritesSize;
		heightSprites = SpritesSize;
		
		// initialize array
		sprites = new Sprite[widthSheetInSprites * heightSheetInSprites];
		
		// initialize method extract
		fillSpritesFromImage(image);
	}
	
	// Constructor 2 if the sprite is not a square
	
		public SpritesSheet(final String path, final int widthSprites, final int heightSprites, final boolean opaqueSheet) {
		
			// initializing image
			final BufferedImage image;
			
			if(opaqueSheet) {
				image = ResourcesLoader.loadCompatibleOpaqueImage(path);
			} else {
				image = ResourcesLoader.loadCompatibleTranslucentImage(path);
			}
			
			widthSheetInPixels = image.getWidth();
			heightSheetInPixels = image.getHeight();
			
			widthSheetInSprites = widthSheetInPixels/widthSprites;
			heightSheetInSprites = heightSheetInPixels/heightSprites;
			
			this.widthSprites = widthSprites;
			this.heightSprites = heightSprites;
			
			// initialize array
			sprites = new Sprite[widthSheetInSprites * heightSheetInSprites];
			
			// initialize method extract
			fillSpritesFromImage(image);
		}
		
		
		// method for extracting/ filling the sprites from the sheet and send them into the array
		private void fillSpritesFromImage(final BufferedImage image) {
			for (int y = 0; y < heightSheetInSprites; y++) {
				for (int x = 0; y < widthSheetInSprites; x++) { //ubication 0,0 of the sheet
					
					final int positionX = x * widthSprites;
					final int positionY = y * heightSprites;
					
					sprites[x+y*widthSheetInSprites] = new Sprite(image.getSubimage(positionX, positionY, widthSprites, heightSprites)); // creating a new sprite, subimage extract a smaller image from other image
				}
					
			}
		}
		
		//get method in order to know to which sprite we want to get access directly
		public Sprite getSprite(final int index) {
			return sprites[index];
		}
		
		//overloaded method using coordinates
				public Sprite getSprite(final int x, final int y) {
					return sprites[x + y * widthSheetInSprites];
				}
}

```

The code will look like this for the **Tile Class** :

```java

package principal.maps;

import java.awt.Rectangle;

import principal.sprites.Sprite;

public class Tile {
	
	private final Sprite sprite; // each tile will have it's own sprite
	private final int id; // unique identification for each tile
	private boolean solid;// defines if the tile will be collidable or not
	
	// constructors 
	public Tile(final Sprite sprite, final int id) {
		this.sprite = sprite;
		this.id = id;
		solid = false; // the tiles aren't collidables by default
	}
	
	public Tile(final Sprite sprite, final int id, final boolean solid) {
		this.sprite = sprite;
		this.id = id;
		this.solid = solid; // the tile will be solid, true
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getId(){
		return id;
	}
	
	public void stablishSolid(final boolean solid) { // method to change a tile into a solid one
		this.solid = solid;
	}
	
	// à revenir sur ce sujet pour les collisions
	public Rectangle getLimits(final int x, final int y) {
		return new Rectangle(x,y, sprite.getWidth(), sprite.getHeight());
	}
}

```

The code will look like this for the **Window Class** :

```java

package principal.graphics;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = -9158027519536291681L;

	private String title;
	
	// constructor
	public Window(final String title, final SurfaceDraw sd) {
		this.title = title;
		
		configurateWindow(sd);
	}

	private void configurateWindow(final SurfaceDraw sd) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		//setIconImage();
		setLayout(new BorderLayout());
		add(sd, BorderLayout.CENTER); // sd will be the Canvas we are going to use for drawing the game, and will be placed in the center of the window
		//setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

```

The code will look like this for the **SurfaceDraw Class** :

```java

package principal.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import principal.control.KeyBoard;
import principal.statemachine.StateManager;

public class SurfaceDraw extends Canvas {
	
	// Attributes
	private static final long serialVersionUID = 5134851650990907399L;
	
	private int width;
	private int height;
	
	private KeyBoard keyboard;
	
	//Constructor
	
	public SurfaceDraw(final int width, final int height) {
		// Initialize them
		this.width = width;
		this.height = height;
		
		keyboard = new KeyBoard();
		
		setIgnoreRepaint(true);
		setPreferredSize(new Dimension(width,height));
		addKeyListener(keyboard);// to start recording which keys are being use
		setFocusable(true);// for compatibility
		requestFocus();//once the game started the canvas will be automatically focus
	}
	
	// draw method
	
	public void draw(final StateManager sm) {
		BufferStrategy buffer = getBufferStrategy(); // configuration for the existence of a buffer in the canvas
		
		if (buffer == null) { // prevent bugs with the buffer volatile image
			createBufferStrategy(3); // the number inside the () indicates how many images will be stocked in the memory before showing them in the monitor
			return;
		}
		
		// object
		
		Graphics g = buffer.getDrawGraphics(); // drawing inside the buffer, this will decide in which of the many (3)images will do it
	
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height); //(x, y, width, height)
	
		//calling the method draw from the StateManager
		
		sm.draw(g); // we are using the same object g, saving memory
		
		Toolkit.getDefaultToolkit().sync(); // will try to draw only in the updates of the window, prevents graphic bugs
		
		g.dispose();
		
		buffer.show(); //show the buffer content dynamically 
	}
	
	public KeyBoard getKeyBoard() {
		return keyboard;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}


```

The code will look like this for the **KeyBoard Class** :

```java
package principal.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
	private final static int NUMBER_KEYS = 256; // NUMBER_KEYS is a constant and 256 max standard number of keys in american keyboard
	private final boolean[] keys = new boolean[NUMBER_KEYS];
	
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	
	public boolean run;
	
	public boolean exit;
	
	public void update() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		
		run = keys[KeyEvent.VK_SHIFT];
		
		exit = keys[KeyEvent.VK_ESCAPE];
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
}
```
# Part 2 - RPG Game: 

## Map Manager

Inside the game package (which is inside the states and statemachine package as well) we are going to create a new class called MapManager.

The GameManager class will have a **private MapManager mapmanager;**

Now, we will create the resources folder and we add the spriteSheets we will be using on.

Once we have the path of the image, we go back into the MapManager class and write:
* **SpritesSheet ss = new SpritesSheet("/images/spriteSheets/001.png",32,true);**

We are doing this to verify if the class is working well. In the **public void draw(Graphics g)** method we will write: 
* **BufferedImage image = ss.getSprite(3, 3).getImage();**
* **g.drawImage(image,100,100, null);**

## Reading a file using inputStream
Inside the ResourcesLoader class we will add the following method:
* **public static String readTextFile(final String path)**, with this method we will read a text file then return a String with it's whole content.

So we need to create a variable where we're going to stock all the text file content, using:
* **String content = "";**

We need also to create some other variable where InputStream will work as a reader, getting the file thanks to the path and giving us the bytes flux, using:
* **InputStream bytesEntrance = ClassLoader.class.getResourceAsStream(path);**

Then, build a reader constructor, using:
* **BufferedReader reader = new BufferedReader(new InputStreamReader(bytesEntrance));** // the reader will read/interprate from bytes to text using the inputstream

And also creating a String called line (reading line by line), then asign line to the actual line in the text using multiple try catch and a while statement, adding the line again and again, and other try catch if the bytes entrance still existing then close it or if the reader still existing then close it. Finally return the content.

## Building a Map and extracting layers and the tiles using JSON

After trying different ways to read a map and then show it in the window I spent a lot of that at this point and I realize it was kind of difficult. Finally I decided to use JSON in order to simplify the work. I used an exported file with Tile software in a JSON format. And I built a path to the json library in order to be able to read it in the RPG project. I used JSONObject so I could get an object so I could define the width and height of the tiled map. Then using some JSONArray I got the layers of the tiled file and then each tile. 

## Including objects and enemies with JSON, then drawing the map

Making an arraylist with all the objects using getObjectJSON, we'll be able to get the id, the quantity of the object, where it is located (x,y), then we will draw the sprites and each tile of the map inside the draw() method, using for statements.

## Menu Inventory

We change the state game to the menu state pusling the key i in the keyboard letting us to gain FPS, so the game gets in pause.


## The Dijkstra Algorithm

Dijkstra determines the shortest path and distance from the source to all destinations in a graph. The core idea of the Dijkstra algorithm is to continuously eliminate longer paths between the starting node and all possible destinations.

To keep track of the process, we need to have two distinct sets of nodes, settled and unsettled.

Settled nodes are the ones with a known minimum distance from the source. The unsettled nodes set gathers nodes that we can reach from the source, but we don’t know the minimum distance from the starting node

Source : http://www.baeldung.com/java-dijkstra





	
				



































 
