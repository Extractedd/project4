//// Arrays of objects.


int nb=16;
Ball[] b=  new Ball[nb];         // Array of Ball objects.

int nt=3;
Button[] t=  new Button[nt];    // Array of Button objects.

float up, right, down, left;
float center;
float mright, mleft;
float surface;
float horizon;

boolean wall=true;

//// SETUP:  create arrays, initialize, ec.
void setup() {
  size( 800,700 );
  horizon=  height/4;
  right= width-100;
  left=  95;
  up=  165;
  down= height-130;
  center= left + (right-left) / 2;
  mleft= 410;
  mright= 340;
  
  // Create cue and 15 balls.
  b[0]=  new Ball( 0, 255, 255, 255 );
  b[0].x=  200;
  b[0].y=  200;
  for (int i=1; i<nb; i++) {
    b[i]=  new Ball( 0, 255, 255, 255 );
  }
  

}

void reset() {
  b[0].x= left + (right-left) / 4;
  b[0].y= up + (down-up) / 2;
  b[0].dx= b[0].dy=0;
  mleft= 370;
  mright= 340;
  wall = true;
  
  reset();
  
}
//// NEXT:  Draw ball, after moving and colliding.
void draw() {
  /* background( 100,150,250 ); */
  background ( 150,200,250 );
  rectMode(CENTER);
  table ( left, up, right, down );
  show();
  action();
  messages();
}

//// Table, buttons, etc.
void table( float left, float up, float right, float down ) {
  fill( 0,150,100 );
  strokeWeight(15);
  stroke( 200,200,200 );
  rect( 395,370,645,445 );
  stroke(0);
  strokeWeight(8);


    if (!wall) {
    float center= left + (right-left) / 2;    
    line( 0,0,0,0 );
  }
  
    if (wall) {
    float center= left + (right-left) / 2;    
     line( 395,575,395,165 );
  }
}

//// ACTION:  move, collide, etc.
void action() {
  for( int i=0; i<nb-1; i++) {
    for( int j=i+1; j<nb; j++) {    
      collide( b[i], b[j] );
    }
  }
  //// Move all balls.
  for( int i=0; i<nb; i++) {
    b[i].move();
  }
}
//// Elastic collisions -- swap dx,dy
void collide( Ball p, Ball q ) {
  if ( p.hit( q.x,q.y ) ) {
    float tmp; float r,g,b;
    tmp=p.dx;  p.dx=q.dx;  q.dx=tmp;      // Swap the velocities.
    tmp=p.dy;  p.dy=q.dy;  q.dy=tmp;
    p.move();  p.move();   p.move();
    q.move();  q.move();   q.move();
  }
  if ( p.hit( q.x,q.y ) ) {
    float r,g,b;
    r= 255;
    g= 255;
    b= 255;
  }
  //++++
}

//// Display 
void show() {
  for( int i=0; i<nb; i++) {
    b[i].show();
  }
}

//// ????
void messages() {
}


/* void mousePressed() {
  
   if ( hit( mouseX,mouseY,  b[1].x,  b[1].y, 30,30  ) ) {  reset(); }
}
*/

void keyPressed() {
  
  if (key == 'w') { mleft = left; mright = right; wall = false; }
}


//////// CLASSES ////////
class Ball {
  float x,y, dx,dy;
  float r,g,b;
  int number;
  //// CONSTRUCTORS:
  Ball( int n ) {
    number=  n;
    randomize();
  }
  Ball( int n, float r, float g, float b ) {
    number=  n;
    this.r=  r;
    this.g=  g;
    this.b=  b;
    randomize();
  }
  Ball( int n, float x, float y ) {
    number=  n;
    randomize();
  }
  void randomize() {
    r=  random( 50, 255 );
    g=  random( 150, 255 );
    b=  random( 50, 255 );
    x=  random( width/2, width-100 );
    y=  random( horizon+0, height-50 );
    dx=  random( -2, 2 );
    dy=  random( -2, 2 );
  }
  
  void reset() {
    x=  random( width/2, width-100 );
    y=  random( horizon+0, height-50 );
    dx=  random( 1,5 );
    dy=  random( 1,3 );
  }
  
  //// METHODS ////
  void move() {
    if (x<mleft || x>right) {  dx *=  -1; }
    if (y<up || y>down) {  dy *=  -1; }
    x=  x+dx;
    y=  y+dy;
  }
  void show() {
    //+++++
    strokeWeight(1);
    fill(r,g,b);
    ellipse( x, y, 30, 30 );
  }
  
  boolean hit( float x, float y ) {
    if (  dist( x,y, this.x,this.y ) < 30 ) return true;
    else return false;
  }
}


class Button {
  float x,y,w,h;
  int r,g,b;
  String name="s";
  String s ;
  
  void show() {
    fill(r+100,g+21,b+9);
    strokeWeight(1);
    rect( x,y, 80,40 );
    fill(0);
    text( name, x-5,y );
    
    t[1].x=150; t[1].y=40; t[1].w=100; t[1].h=100; 
    /* b2.x=600; b2.y=40;  b2.w=100; b2.h=100; */
    t[3].x=450; t[3].y=40; t[3].w=100; t[3].h=100;
    t[0].x=28;  t[0].y=100;  t[0].w=100; t[0].h=100;
  }
}


boolean hit( float x1, float y1, float x2, float y2, float w, float h ) {
  boolean result;

  // +++++ STUB ALWAYS RETURNS TRUE!
  if ( abs(x1-x2) < w && abs(y1-y2)<h ) {
    result=  true;
  } else {
    result=false;
  }
  return result;
}

