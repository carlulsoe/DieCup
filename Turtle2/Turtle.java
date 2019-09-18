/**
 * Turtle for drawing pictures on a World object.
 *
 * @author Carl Ulsøe Christensen og Freja Østerbøg
 * @version 2019-09-09.
 */
public class Turtle extends Actor {
    /**
     * Instantiates a new Turtle object at the specified coordinates.
     */
    public Turtle(int x, int y) {
        super(x, y, 0);
        addToCanvas();
    }

    /**
     * Instantiates a new Turtle object at coordinates (100,100).
     */
    public Turtle() {
        this(100,100);
    }

    /**
     * Draws a regular triangle.
     *
     * @param size   Length of each side.
     */
    public void triangle(double size) {
        polygon(3, size);
    }

    /**
     * Draws a square.
     *
     * @param size   Length of each side.
     */
    public void square(double size) {
        polygon(4, size);
    }

    /**
     * Draws a regular polygon.
     *
     * @param n       Number of sides.
     * @param size    Length of each side.
     */
    public void polygon(int n, double size) {
        for (int i=0; i<n; i++) {
            move(size);
            turn(360.0/n);
        }
    }

    /**
     * Draws a circle.
     *
     * @param radius    Length of radius.
     */
    public void circle(double radius) {
        polygon(100, 2*radius*Math.PI/100);
    }

    /**
     * Draws a star with five corners.
     *
     * @param size   Length of each side.
     */
    public void star(double size) {
        for (int i=1; i<=5; i++) {
            move(size);
            turn(180-(180.0/5));
        }
    }

    /**
     * Draws a spiral.
     *
     * @param n        Number of sides.
     * @param delta    Difference in length between two succeeding sides
     *                 (equal to shortest side).
     */
    public void spiral(int n, double delta) {
        double width = delta;
        for (int i = 0; i < n; i++) {
            move(width);
            turn(360.0/4);
            width = width + delta;
        }
    }

    /**
     * Jumps to specified coordinates without drawing and without turning.
     * Ends with a penDown.
     *
     * @param x    x coordinate.
     * @param y    y coordinate.
     */
    public void jumpTo(double x, double y) {
        penUp();
        moveTo(x, y);
        penDown();
    }

    /**
     * Jumps as specified without drawing and without turning.
     * Ends with a penDown.
     *
     * @param x     Distance forward.
     * @param y     Distance sideways (+ is right and - is left).
     */
    public void jump(double x, double y) {
        penUp();
        move(x);
        turn(90);
        move(y);
        turn(-90);
        penDown();
    }

    /**
     * Draws a number of squares "behind" each other.
     *
     * @param n       Number of squares.
     * @param size    Size of squares.
     * @param gap     Horizontal and vertical gap between adjacent squares.
     */
    public void squares(int n, double size, double gap) {
        for (int i=0; i<n; i++) {
            square(size);
            jump(gap, gap);
        }
    }

    /**
     * Draws a number of squares inside each other.
     *
     * @param n      Number of squares.
     * @param gap    Gap between adjacent squares.
     *               (equal to size of smallest square).
     */
    public void squares2(int n, double gap) {
        double size = gap;
        for (int i=0; i<n; i++) {
            square(size);
            size += gap * 2;
            jump(-gap, -gap);
        }
    }

    /**
     * Draws a number of squares on a "horizontal" line.
     *
     * @param n       Number of squares.
     * @param size    Size of squares.
     * @param gap     Distance between adjacent squares.
     */
    public void squaresHorizontal(int n, double size, double gap) {
        if (n > 0)  {
            square(size);
            jump(size + gap, 0);
            squaresHorizontal(n-1, size, gap);
        }
    }

    /**
     * Draws a number of squares inside each other so that
     * all upper left corners are in the same point.
     *
     * @param n       Number of squares.
     * @param size    Size of largest square.
     */
    public void squaresCornered(int n, double size) {
        if (n > 0)  {
            square(size);
            squaresCornered(n-1, size - size/n);
        }
    }

    /**
     * Draws a number of triangles inside each other.
     *
     * @param n       Number of triangles.
     * @param size    Size of largest triangle.
     */
    public void triangles(int n, double size) {
        if (n > 0)  {
            triangle(size);
            jump(size*3/4, Math.sqrt(Math.pow(size/2,2) - Math.pow(size/4,2)));
            turn(180);
            triangles(n-1, size/2);
        }
    }

    /**
     * Draws a number of squares inside each other.
     *
     * @param n      Number of squares.
     * @param gap    Gap between adjacent squares
     *               (equal to size of smallest square).
     */
    public void squaresCentered(int n, double gap) {
        if (n > 0)  {
            square(n*gap*2 - gap);
            jump(gap, gap);
            squaresCentered(n-1, gap);
        }
    }

    /**
     * Draws a Koch curve.
     *
     * @param n       Degree of Koch curve.
     * @param size    Length of Koch curve.
     */
    public void kochCurve(int n, double size) {
        if (n > 1)  {
            kochCurve(n-1, size/3);
            turn(-60);
            kochCurve(n-1, size/3);
            turn(120);
            kochCurve(n-1, size/3);
            turn(-60);
            kochCurve(n-1, size/3);
        }
        else if (n == 1)    {
            move(size/3);
            turn(-60);
            move(size/3);
            turn(120);
            move(size/3);
            turn(-60);
            move(size/3);
        }
    }

    /**
     * Draws a Koch flake.
     *
     * @param n       Degree of Koch curves.
     * @param size    Length of each Koch curve.
     */
    public void kochFlake(int n, double size){
        kochCurve(n,size);
        turn(120);
        kochCurve(n,size);
        turn(120);
        kochCurve(n,size);
        turn(120);
    }

    /**
     * Draws a Sierpinski curve.
     *
     * @param n       Degree of Sierpinski curve.
     * @param size    Length of Sierpinski curve.
     */ 
    public void sierpinskiCurve(int n, double size) {
        if (n > 0)  {
            sierpinskiCurve(n-1, size/2);
            jump(size/2, 0);
            sierpinskiCurve(n-1, size/2);
            jump(-size/4, Math.sqrt(Math.pow(size/2, 2) - Math.pow(size/4, 2)));
            sierpinskiCurve(n-1, size/2);
            jump(-size/4, -Math.sqrt(Math.pow(size/2, 2) - Math.pow(size/4, 2)));
        }
        else if (n == 0)    {
            triangle(size);
        }
    }

    /**
     * Draws a pattern of squares where most are placed in the diagonal.
     *
     * @param n       Depth of pattern (for n=1 a sigle square is drawn).
     * @param size    Size of largest square.
     */
    public void squarePatternDiagonal(int n, double size) {
        if (n > 1)  {
            square(size);
            squarePatternDiagonal(n-1, size/2);
            jump(size/2, size/2);
            squarePatternDiagonal(n-1, size/2);
            jump(-size/2, -size/2);
        }
        else if (n == 1)   {
            square(size);
        }
    }

    /**
     * Draws a pattern of squares, where each square immediately
     * surrounds four other squares.
     * For each level of depth the size of the squares is divided by 3.
     *
     * @param n       Depth of pattern (for n=1 a sigle square is drawn).
     * @param size    Size of largest square.
     */
    public void squarePatternFour(int n, double size) {
        if (n > 1)   {
            double l = 4/9.0*size;
            square(size);
            jump(1/9.0 * size, 1/9.0 * size);
            squarePatternFour(n-1, size/3);
            jump(l, 0);
            squarePatternFour(n-1, size/3);
            jump(0, l);
            squarePatternFour(n-1, size/3);
            jump(-l, 0);
            squarePatternFour(n-1, size/3);
            jump(0,-l);
            jump(-1/9.0 * size, -1/9.0 * size);
        }
        else if (n == 1)  {
            square(size);
        }
    }

    /**
     * This method allows the instructors (and the students) to see
     * whether all the implemented methods in Turtle 1 work as expected.
     */  
    public void testTurtle1() {
        penDown();

        //test triangle, square, polygon and circle
        jumpTo(100,100);
        turnTo(-20);
        triangle(50);
        jump(75,20);
        square(50);
        jump(100,-20);
        polygon(7,50);
        jump(150,20);
        circle(37.5);

        //test star and spiral
        jumpTo(100,250);
        turnTo(-10);
        star(100);
        jump(350,25);
        spiral(24,5);

        //test squares
        jumpTo(300,300);
        turnTo(10);
        squares(6,75,15);

        //test squares2
        jumpTo(150,450);
        turnTo(40);
        squares2(8,12);

        //move away
        jumpTo(500,500);
        turnTo(0);
    }

    /**
     * This method allows the instructors (and the students) to see
     * whether all the implemented methods in Turtle 2 work as expected.
     */
    public void testTurtle2() {
        penDown();

        //test squaresHorizontal, squaresCornered, triangels and squaresCentered
        jumpTo(50,50);
        turnTo(-15);
        squaresHorizontal(3,40,10);
        turnTo(-15);
        jumpTo(200,100);
        squaresCornered(5,80);
        jumpTo(300,100);
        turnTo(-15);
        triangles(4,100);
        jumpTo(475,100);
        turnTo(-15);
        squaresCentered(5,10);

        //test kochFlake (and KochCurve)
        jumpTo(150,150);
        turnTo(105);
        kochFlake(3,100);

        //test sierpinskiCurve
        jumpTo(200,200);
        turnTo(20);
        sierpinskiCurve(3,150);

        //test squarePatternDiagonal
        jumpTo(100,300);
        turnTo(30);
        squarePatternDiagonal(4,200);

        //test squarePatternFour
        jumpTo(430,300);
        turnTo(40);
        squarePatternFour(3,200);

        //move away
        jumpTo(550,300);
        turnTo(0);
    }

    /**
     * This STATIC method allows the instructors (and the students) to see
     * whether all the implemented methods in Turtle 1 work as expected.
     */
    public static void testFirst() {
        Turtle t = new Turtle();   //Skaber et GUI-vindue
        t.getCanvas().clear();
        t.testTurtle1();
    }

    /**
     * This STATIC method allows the instructors (and the students) to see
     * whether all the implemented methods in Turtle 2 work as expected.
     */
    public static void testSecond() {
        Turtle t = new Turtle();   //Skaber et GUI-vindue
        t.getCanvas().clear();
        t.testTurtle2();
    }

    /**
     * This STATIC method allows the instructors (and the students) to see
     * whether all the implemented methods in Turtle 1 and Turtle 2 work
     * as expected.
     */
    public static void testAll() {
        Turtle t = new Turtle();   //Skaber et GUI-vindue
        t.getCanvas().clear();
        t.setColor("green");
        t.testTurtle1();
        t.setColor("red");
        t.testTurtle2();
    }

}
