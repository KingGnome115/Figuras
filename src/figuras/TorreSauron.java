package figuras;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingBox;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 * Torre de sauron la cree como un monolito para dominar el código y llevarlo
 * a la oscuridad
 * @author Kevin
 */
public class TorreSauron extends javax.swing.JInternalFrame
{

    BranchGroup root = new BranchGroup();

    /**
     * Creates new form Magito
     */
    public TorreSauron()
    {
        initComponents();
        init();
    }

    public void init()
    {
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas = new Canvas3D(config);
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
        BranchGroup escenario = Torre();
        escenario.compile();
        SimpleUniverse su = new SimpleUniverse(canvas);

        Vector3f posVis = new Vector3f();
        posVis.z = 23f;
        posVis.x = 0f;
        posVis.y = 0f;
        
        Transform3D traVis = new Transform3D();
        traVis.setTranslation(posVis);
        
        Transform3D rotacion = new Transform3D();
        rotacion.rotX(Math.toRadians(13));
        rotacion.mul(traVis);
        
        su.getViewingPlatform().getViewPlatformTransform().setTransform(rotacion);
        su.getViewingPlatform().getViewPlatformTransform().getTransform(rotacion);

        su.addBranchGraph(escenario);

        BoundingSphere bs = new BoundingSphere(new Point3d(), 100.0);
        OrbitBehavior orbita = new OrbitBehavior(canvas, OrbitBehavior.STOP_ZOOM);
        orbita.setSchedulingBounds(bs);
        su.getViewingPlatform().setViewPlatformBehavior(orbita);
    }

    public BranchGroup Torre()
    {
        TransformGroup spin = new TransformGroup();
        spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(spin);

        TransformGroup tg = new TransformGroup();
        Transform3D t3d = new Transform3D();
        spin.addChild(tg);

        BoundingBox limites
                = new BoundingBox(new Point3d(-200, -200, -200),
                        new Point3d(200, 200, 200));
        TransformGroup fondo = new TransformGroup();
        TextureLoader cargador = new TextureLoader("src\\imgTS\\Fondo.jpg", this);
        Background back = new Background();
        back.setImage(cargador.getImage());
        back.setImageScaleMode(Background.SCALE_FIT_ALL);
        back.setApplicationBounds(limites);
        fondo.addChild(back);
        root.addChild(fondo);

        DirectionalLight luz = new DirectionalLight(new Color3f(Color.LIGHT_GRAY), new Vector3f(40f, -40f, -40f));
        luz.setInfluencingBounds(new BoundingSphere());
        root.addChild(luz);

        TextureLoader loader = new TextureLoader("src\\imgTS\\ojo.jpg", "INTENSITY", new Container());
        Texture textura = loader.getTexture();
        Appearance apariencia = new Appearance();
        apariencia.setTexture(textura);
        Material mat = new Material();
        apariencia.setMaterial(mat);
        
        TextureLoader loaderC = new TextureLoader("src\\imgTS\\muros.jpg", "INTENSITY", new Container());
        Texture texturaC = loaderC.getTexture();
        Appearance apC = new Appearance();
        apC.setTexture(texturaC);
        Material matC = new Material();
        apC.setMaterial(matC);
        
        TextureLoader loaderd = new TextureLoader("src\\imgTS\\detalles.jpg", "INTENSITY", new Container());
        Texture texturad = loaderd.getTexture();
        Appearance apd = new Appearance();
        apd.setTexture(texturad);
        Material matd = new Material();
        apd.setMaterial(matd);
        
        TextureLoader loaderpi = new TextureLoader("src\\imgTS\\picos.jpg", "INTENSITY", new Container());
        Texture texturapi = loaderpi.getTexture();
        Appearance apPI = new Appearance();
        apPI.setTexture(texturapi);
        Material matpi = new Material();
        apPI.setMaterial(matpi);
        
        int paratextura = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;

        Sphere Ojo = new Sphere(0.6f, paratextura, apariencia);
        Ojo.setAppearance(apariencia);
        t3d.setTranslation(new Vector3f(0.0f, 5.0f, 0f));
        TransformGroup tgh8 = new TransformGroup(t3d);
        tgh8.addChild(Ojo);
        tg.addChild(tgh8);

        Alpha alpha = new Alpha(-1, 5500);
        RotationInterpolator rotator = new RotationInterpolator(alpha, spin);
        BoundingSphere bounds = new BoundingSphere();
        rotator.setSchedulingBounds(bounds);
        spin.addChild(rotator);


        //Puntas
        Transform3D translacion = new Transform3D();
        translacion.set(new Vector3d(new Vector3f(1.0f, 5.0f, 0.0f)));
        TransformGroup t1 = new TransformGroup(translacion);
        Cone punta1 = new Cone(0.4f, 2.0f, paratextura, apd);
        punta1.setAppearance(apd);

        Transform3D translacion2 = new Transform3D();
        translacion2.set(new Vector3d(new Vector3f(-1.0f, 5.0f, 0.0f)));
        TransformGroup t2 = new TransformGroup(translacion2);
        Cone punta2 = new Cone(0.4f, 2.0f, paratextura, apd);
        punta2.setAppearance(apd);

        t2.addChild(punta2);
        t1.addChild(punta1);

        root.addChild(t1);
        root.addChild(t2);
        //Puntas

        //Columna
        Transform3D trb = new Transform3D();
        trb.set(new Vector3d(new Vector3f(0.0f, 1.5f, 0.0f)));
        TransformGroup tb = new TransformGroup(trb);
        Cylinder base1 = new Cylinder(1.2f, 5.5f, paratextura, apC);
        base1.setAppearance(apC);
        tb.addChild(base1);

        Transform3D det1 = new Transform3D();
        det1.set(new Vector3d(new Vector3f(-1.0f, 2.5f, 0.0f)));
        TransformGroup dt = new TransformGroup(det1);
        Box p1 = new Box(0.4f, 1.5f, 0.4f, paratextura, apd);
        p1.setAppearance(apd);
        dt.addChild(p1);

        Transform3D det2 = new Transform3D();
        det2.set(new Vector3d(new Vector3f(1.0f, 2.5f, 0.0f)));
        TransformGroup dt2 = new TransformGroup(det2);
        Box p2 = new Box(0.4f, 1.5f, 0.4f, paratextura, apd);
        p2.setAppearance(apd);
        dt2.addChild(p2);

        Transform3D det3 = new Transform3D();
        det3.set(new Vector3d(new Vector3f(0.0f, 2.5f, 1.0f)));
        TransformGroup dt3 = new TransformGroup(det3);
        Box p3 = new Box(0.4f, 1.5f, 0.4f, paratextura, apd);
        p3.setAppearance(apd);
        dt3.addChild(p3);

        Transform3D det4 = new Transform3D();
        det4.set(new Vector3d(new Vector3f(0.0f, 2.5f, -1.0f)));
        TransformGroup dt4 = new TransformGroup(det4);
        Box p4 = new Box(0.4f, 1.5f, 0.4f, paratextura, apd);
        p4.setAppearance(apd);
        dt4.addChild(p4);

        Transform3D tr1 = new Transform3D();
        tr1.set(new Vector3d(new Vector3f(-0.8f, 1.1f, 0.79f)));
        Transform3D rot1 = new Transform3D();
        rot1.rotX(Math.toRadians(180));
        tr1.mul(rot1);
        TransformGroup tc1 = new TransformGroup(tr1);
        Cone c1 = new Cone(0.4f, 3.0f, paratextura, apd);
        c1.setAppearance(apd);
        tc1.addChild(c1);

        Transform3D tr2 = new Transform3D();
        tr2.set(new Vector3d(new Vector3f(0.8f, 1.1f, -0.79f)));
        Transform3D rot2 = new Transform3D();
        rot2.rotX(Math.toRadians(180));
        tr2.mul(rot2);
        TransformGroup tc2 = new TransformGroup(tr2);
        Cone c2 = new Cone(0.4f, 3.0f, paratextura, apd);
        c2.setAppearance(apd);
        tc2.addChild(c2);

        Transform3D tr3 = new Transform3D();
        tr3.set(new Vector3d(new Vector3f(-0.8f, 1.1f, -0.79f)));
        Transform3D rot3 = new Transform3D();
        rot3.rotX(Math.toRadians(180));
        tr3.mul(rot3);
        TransformGroup tc3 = new TransformGroup(tr3);
        Cone c3 = new Cone(0.4f, 3.0f, paratextura, apd);
        c3.setAppearance(apd);
        tc3.addChild(c3);

        Transform3D tr4 = new Transform3D();
        tr4.set(new Vector3d(new Vector3f(0.8f, 1.1f, 0.79f)));
        Transform3D rot4 = new Transform3D();
        rot4.rotX(Math.toRadians(180));
        tr4.mul(rot4);
        TransformGroup tc4 = new TransformGroup(tr4);
        Cone c4 = new Cone(0.4f, 3.0f, paratextura, apd);
        c4.setAppearance(apd);
        tc4.addChild(c4);

        root.addChild(dt);
        root.addChild(dt2);
        root.addChild(dt3);
        root.addChild(dt4);
        root.addChild(tc1);
        root.addChild(tc2);
        root.addChild(tc3);
        root.addChild(tc4);
        root.addChild(tb);
        //Columna

        //Base
        Transform3D trb2 = new Transform3D();
        trb2.set(new Vector3d(new Vector3f(0.0f, -2.5f, 0.0f)));
        TransformGroup tb2 = new TransformGroup(trb2);
        Cylinder base2 = new Cylinder(2.0f, 2.5f, paratextura, apC);
        base2.setAppearance(apC);
        tb2.addChild(base2);

        Transform3D pi1 = new Transform3D();
        pi1.set(new Vector3d(new Vector3f(-1.3f, -1.0f, 0.79f)));
        TransformGroup tp1 = new TransformGroup(pi1);
        Cone pico1 = new Cone(0.4f, 1.0f, paratextura, apPI);
        pico1.setAppearance(apPI);
        tp1.addChild(pico1);

        Transform3D pi2 = new Transform3D();
        pi2.set(new Vector3d(new Vector3f(1.3f, -1.0f, 0.79f)));
        TransformGroup tp2 = new TransformGroup(pi2);
        Cone pico2 = new Cone(0.4f, 1.0f, paratextura, apPI);
        pico2.setAppearance(apPI);
        tp2.addChild(pico2);

        Transform3D pi3 = new Transform3D();
        pi3.set(new Vector3d(new Vector3f(1.3f, -1.0f, -0.79f)));
        TransformGroup tp3 = new TransformGroup(pi3);
        Cone pico3 = new Cone(0.4f, 1.0f, paratextura, apPI);
        pico3.setAppearance(apPI);
        tp3.addChild(pico3);

        Transform3D pi4 = new Transform3D();
        pi4.set(new Vector3d(new Vector3f(-1.3f, -1.0f, -0.79f)));
        TransformGroup tp4 = new TransformGroup(pi4);
        Cone pico4 = new Cone(0.4f, 1.0f, paratextura, apPI);
        pico4.setAppearance(apPI);
        tp4.addChild(pico4);

        Transform3D pi5 = new Transform3D();
        pi5.set(new Vector3d(new Vector3f(0.0f, -1.0f, 1.5f)));
        TransformGroup tp5 = new TransformGroup(pi5);
        Cone pico5 = new Cone(0.4f, 1.0f, paratextura, apPI);
        pico5.setAppearance(apPI);
        tp5.addChild(pico5);

        Transform3D pi6 = new Transform3D();
        pi6.set(new Vector3d(new Vector3f(0.0f, -1.0f, -1.5f)));
        TransformGroup tp6 = new TransformGroup(pi6);
        Cone pico6 = new Cone(0.4f, 1.0f, paratextura, apPI);
        pico6.setAppearance(apPI);
        tp6.addChild(pico6);

        Transform3D deb11 = new Transform3D();
        deb11.set(new Vector3d(new Vector3f(-1.8f, -3.0f, 0.79f)));
        Transform3D rd1 = new Transform3D();
        rd1.rotX(Math.toRadians(180));
        deb11.mul(rd1);
        TransformGroup tdb11 = new TransformGroup(deb11);
        Cone r11 = new Cone(0.6f, 1.0f, paratextura, apd);
        r11.setAppearance(apd);
        tdb11.addChild(r11);

        Transform3D deb12 = new Transform3D();
        deb12.set(new Vector3d(new Vector3f(-1.8f, -2.0f, 0.79f)));
        TransformGroup tdb12 = new TransformGroup(deb12);
        Cone r12 = new Cone(0.6f, 1.0f, paratextura, apd);
        r12.setAppearance(apd);
        tdb12.addChild(r12);

        Transform3D deb21 = new Transform3D();
        deb21.set(new Vector3d(new Vector3f(1.8f, -3.0f, 0.79f)));
        Transform3D rd2 = new Transform3D();
        rd2.rotX(Math.toRadians(180));
        deb21.mul(rd2);
        TransformGroup tdb21 = new TransformGroup(deb21);
        Cone r21 = new Cone(0.6f, 1.0f, paratextura, apd);
        r21.setAppearance(apd);
        tdb21.addChild(r21);

        Transform3D deb22 = new Transform3D();
        deb22.set(new Vector3d(new Vector3f(1.8f, -2.0f, 0.79f)));
        TransformGroup tdb22 = new TransformGroup(deb22);
        Cone r22 = new Cone(0.6f, 1.0f, paratextura, apd);
        r22.setAppearance(apd);
        tdb22.addChild(r22);

        Transform3D deb31 = new Transform3D();
        deb31.set(new Vector3d(new Vector3f(1.8f, -3.0f, -0.79f)));
        Transform3D rd3 = new Transform3D();
        rd3.rotX(Math.toRadians(180));
        deb31.mul(rd3);
        TransformGroup tdb31 = new TransformGroup(deb31);
        Cone r31 = new Cone(0.6f, 1.0f, paratextura, apd);
        r31.setAppearance(apd);
        tdb31.addChild(r31);

        Transform3D deb32 = new Transform3D();
        deb32.set(new Vector3d(new Vector3f(1.8f, -2.0f, -0.79f)));
        TransformGroup tdb32 = new TransformGroup(deb32);
        Cone r32 = new Cone(0.6f, 1.0f, paratextura, apd);
        r32.setAppearance(apd);
        tdb32.addChild(r32);

        Transform3D deb41 = new Transform3D();
        deb41.set(new Vector3d(new Vector3f(-1.8f, -3.0f, -0.79f)));
        Transform3D rd4 = new Transform3D();
        rd4.rotX(Math.toRadians(180));
        deb41.mul(rd4);
        TransformGroup tdb41 = new TransformGroup(deb41);
        Cone r41 = new Cone(0.6f, 1.0f, paratextura, apd);
        r41.setAppearance(apd);
        tdb41.addChild(r41);

        Transform3D deb42 = new Transform3D();
        deb42.set(new Vector3d(new Vector3f(-1.8f, -2.0f, -0.79f)));
        TransformGroup tdb42 = new TransformGroup(deb42);
        Cone r42 = new Cone(0.6f, 1.0f, paratextura, apd);
        r42.setAppearance(apd);
        tdb42.addChild(r42);

        Transform3D deb51 = new Transform3D();
        deb51.set(new Vector3d(new Vector3f(0.0f, -3.0f, -1.9f)));//0.0f, -3.0f, -1.5f
        Transform3D rd5 = new Transform3D();
        rd5.rotX(Math.toRadians(180));
        deb51.mul(rd5);
        TransformGroup tdb51 = new TransformGroup(deb51);
        Cone r51 = new Cone(0.6f, 1.0f, paratextura, apd);
        r51.setAppearance(apd);
        tdb51.addChild(r51);

        Transform3D deb52 = new Transform3D();
        deb52.set(new Vector3d(new Vector3f(0.0f, -2.0f, -1.9f)));
        TransformGroup tdb52 = new TransformGroup(deb52);
        Cone r52 = new Cone(0.6f, 1.0f, paratextura, apd);
        r52.setAppearance(apd);
        tdb52.addChild(r52);

        Transform3D deb61 = new Transform3D();
        deb61.set(new Vector3d(new Vector3f(0.0f, -3.0f, 1.9f)));//0.0f, -3.0f, -1.5f
        Transform3D rd6 = new Transform3D();
        rd6.rotX(Math.toRadians(180));
        deb61.mul(rd6);
        TransformGroup tdb61 = new TransformGroup(deb61);
        Cone r61 = new Cone(0.6f, 1.0f, paratextura, apd);
        r61.setAppearance(apd);
        tdb61.addChild(r61);

        Transform3D deb62 = new Transform3D();
        deb62.set(new Vector3d(new Vector3f(0.0f, -2.0f, 1.9f)));
        TransformGroup tdb62 = new TransformGroup(deb62);
        Cone r62 = new Cone(0.6f, 1.0f, paratextura, apd);
        r62.setAppearance(apd);
        tdb62.addChild(r62);

        root.addChild(tb2);
        root.addChild(tp1);
        root.addChild(tp2);
        root.addChild(tp3);
        root.addChild(tp4);
        root.addChild(tp5);
        root.addChild(tp6);
        root.addChild(tdb11);
        root.addChild(tdb12);
        root.addChild(tdb21);
        root.addChild(tdb22);
        root.addChild(tdb31);
        root.addChild(tdb32);
        root.addChild(tdb41);
        root.addChild(tdb42);
        root.addChild(tdb51);
        root.addChild(tdb52);
        root.addChild(tdb61);
        root.addChild(tdb62);
        //Base

        //Base grande
        Transform3D trb3 = new Transform3D();
        trb3.set(new Vector3d(new Vector3f(0.0f, -5.0f, 0.0f)));
        TransformGroup tb3 = new TransformGroup(trb3);
        Cylinder base3 = new Cylinder(3.0f, 3.0f, paratextura, apC);
        base3.setAppearance(apC);
        tb3.addChild(base3);

        Transform3D col1 = new Transform3D();
        col1.set(new Vector3d(new Vector3f(3.0f, -5.0f, 0.0f)));
        TransformGroup dc1 = new TransformGroup(col1);
        Box coc1 = new Box(0.4f, 1.5f, 0.4f, paratextura, apd);
        coc1.setAppearance(apd);
        dc1.addChild(coc1);

        Transform3D col2 = new Transform3D();
        col2.set(new Vector3d(new Vector3f(-3.0f, -5.0f, 0.0f)));
        TransformGroup dc2 = new TransformGroup(col2);
        Box coc2 = new Box(0.4f, 1.5f, 0.4f, paratextura, apd);
        coc2.setAppearance(apd);
        dc2.addChild(coc2);

        Transform3D col3 = new Transform3D();
        col3.set(new Vector3d(new Vector3f(0.0f, -5.0f, 3.0f)));
        TransformGroup dc3 = new TransformGroup(col3);
        Box coc3 = new Box(0.4f, 1.5f, 0.4f, paratextura, apd);
        coc3.setAppearance(apd);
        dc3.addChild(coc3);

        Transform3D col4 = new Transform3D();
        col4.set(new Vector3d(new Vector3f(0.0f, -5.0f, -3.0f)));
        TransformGroup dc4 = new TransformGroup(col4);
        Box coc4 = new Box(0.4f, 1.5f, 0.4f, paratextura, apd);
        coc4.setAppearance(apd);
        dc4.addChild(coc4);

        Transform3D b1 = new Transform3D();
        b1.set(new Vector3d(new Vector3f(2.0f, -5.0f, 2.0f)));
        TransformGroup b = new TransformGroup(b1);
        Cone bc = new Cone(1.5f, 2.9f, paratextura, apPI);
        bc.setAppearance(apPI);
        b.addChild(bc);

        Transform3D b2 = new Transform3D();
        b2.set(new Vector3d(new Vector3f(-2.0f, -5.0f, -2.0f)));
        TransformGroup b21 = new TransformGroup(b2);
        Cone bc2 = new Cone(1.5f, 2.9f, paratextura, apPI);
        bc2.setAppearance(apPI);
        b21.addChild(bc2);

        Transform3D b3 = new Transform3D();
        b3.set(new Vector3d(new Vector3f(2.0f, -5.0f, -2.0f)));
        TransformGroup b22 = new TransformGroup(b3);
        Cone bc3 = new Cone(1.5f, 2.9f, paratextura, apPI);
        bc3.setAppearance(apPI);
        b22.addChild(bc3);

        Transform3D b4 = new Transform3D();
        b4.set(new Vector3d(new Vector3f(-2.0f, -5.0f, 2.0f)));
        TransformGroup b23 = new TransformGroup(b4);
        Cone bc4 = new Cone(1.5f, 2.9f, paratextura, apPI);
        bc4.setAppearance(apPI);
        b23.addChild(bc4);

        root.addChild(b);
        root.addChild(b21);
        root.addChild(b22);
        root.addChild(b23);
        root.addChild(dc1);
        root.addChild(dc2);
        root.addChild(dc3);
        root.addChild(dc4);
        root.addChild(tb3);

        //Base grande
        Background background = new Background(10f, 10f, 10f);
        background.setColor(new Color3f(new Color(199, 199, 199)));
        root.addChild(background);

        AmbientLight light = new AmbientLight(true, new Color3f(Color.black));
        light.setInfluencingBounds(bounds);
        root.addChild(light);

        return root;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 583, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
