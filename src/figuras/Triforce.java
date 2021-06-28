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
 * Cree una trifuerza porque quiero jugar Zelda BoTW 2 pero aun no sale
 * y no tengo dinero
 * @author Kevin
 */
public class Triforce extends javax.swing.JInternalFrame
{

    BranchGroup root = new BranchGroup();

    /**
     * Creates new form Magito
     */
    public Triforce()
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
        BranchGroup escenario = Trifuerza();
        escenario.compile();
        SimpleUniverse su = new SimpleUniverse(canvas);

        Vector3f posVis = new Vector3f();
        posVis.z = 5f;
        posVis.x = 0f;
        posVis.y = 0f;

        Transform3D traVis = new Transform3D();
        traVis.setTranslation(posVis);

        Transform3D rotacionY = new Transform3D();
        rotacionY.rotY(Math.toRadians(25));
        rotacionY.mul(traVis);

        Transform3D rotacionX = new Transform3D();
        rotacionX.rotX(Math.toRadians(-15));
        rotacionX.mul(rotacionY);

        su.getViewingPlatform().getViewPlatformTransform().setTransform(rotacionX);
        su.getViewingPlatform().getViewPlatformTransform().getTransform(rotacionX);

        su.addBranchGraph(escenario);

        BoundingSphere bs = new BoundingSphere(new Point3d(), 100.0);
        OrbitBehavior orbita = new OrbitBehavior(canvas, OrbitBehavior.STOP_ZOOM);
        orbita.setSchedulingBounds(bs);
        su.getViewingPlatform().setViewPlatformBehavior(orbita);
    }

    public BranchGroup Trifuerza()
    {
        BoundingBox limites
                = new BoundingBox(new Point3d(-200, -200, -200),
                        new Point3d(200, 200, 200));
        TransformGroup fondo = new TransformGroup();
        TextureLoader cargador = new TextureLoader("src\\imgTF\\Fondo.jpg", this);
        Background back = new Background();
        back.setImage(cargador.getImage());
        back.setImageScaleMode(Background.SCALE_FIT_ALL);
        back.setApplicationBounds(limites);
        fondo.addChild(back);
        root.addChild(fondo);

        DirectionalLight luz = new DirectionalLight(new Color3f(Color.LIGHT_GRAY), new Vector3f(40f, -40f, -40f));
        luz.setInfluencingBounds(new BoundingSphere());
        root.addChild(luz);

        TextureLoader loader = new TextureLoader("src\\imgTF\\dorado.jpg", "INTENSITY", new Container());
        Texture textura = loader.getTexture();
        Appearance aparienciaDor = new Appearance();
        aparienciaDor.setTexture(textura);
        Material mat = new Material();
        aparienciaDor.setMaterial(mat);

        TextureLoader loaderd = new TextureLoader("src\\imgTF\\muros.jpg", "INTENSITY", new Container());
        Texture texturad = loaderd.getTexture();
        Appearance aparienciaAzul = new Appearance();
        aparienciaAzul.setTexture(texturad);
        Material matd = new Material();
        aparienciaAzul.setMaterial(matd);

        TextureLoader loaderb = new TextureLoader("src\\imgTF\\base.jpg", "INTENSITY", new Container());
        Texture texturab = loaderb.getTexture();
        Appearance Apariencialadrillo = new Appearance();
        Apariencialadrillo.setTexture(texturab);
        Material matb = new Material();
        Apariencialadrillo.setMaterial(matb);

        TextureLoader loadera = new TextureLoader("src\\imgTF\\picos.jpg", "INTENSITY", new Container());
        Texture texturaa = loadera.getTexture();
        Appearance Aparienciafuego = new Appearance();
        Aparienciafuego.setTexture(texturaa);
        Material mata = new Material();
        Aparienciafuego.setMaterial(mata);

        TextureLoader loaderp = new TextureLoader("src\\imgTF\\pilar.jpg", "INTENSITY", new Container());
        Texture texturap = loaderp.getTexture();
        Appearance Aparienciapilar = new Appearance();
        Aparienciapilar.setTexture(texturap);
        Material matp = new Material();
        Aparienciapilar.setMaterial(matp);

        int paratextura = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;

        //Primer triangulo
        TransformGroup spin = new TransformGroup();
        spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(spin);

        TransformGroup tg = new TransformGroup();
        spin.addChild(tg);

        Transform3D SBR0 = new Transform3D();
        Transform3D TBR0 = new Transform3D();
        Transform3D RBR0 = new Transform3D();
        Transform3D RBR1 = new Transform3D();

        SBR0.setScale(new Vector3d(0.4, 1, 1));
        TBR0.setTranslation(new Vector3f(0.0f, 0.15f, 0.0f));
        TransformGroup tgb0 = new TransformGroup(SBR0);
        TransformGroup tgb1 = new TransformGroup(RBR0);
        TransformGroup tgb2 = new TransformGroup(RBR1);
        TransformGroup tgb3 = new TransformGroup(TBR0);

        Cone tri1 = new Cone(0.1f, 0.15f, paratextura, aparienciaDor);
        tri1.setAppearance(aparienciaDor);
        tgb0.addChild(tri1);
        tgb1.addChild(tgb0);
        tgb2.addChild(tgb1);
        tgb3.addChild(tgb2);
        tg.addChild(tgb3);

        Alpha alpha = new Alpha(-1, 5500);
        RotationInterpolator rotator = new RotationInterpolator(alpha, spin);
        BoundingSphere bounds = new BoundingSphere();
        rotator.setSchedulingBounds(bounds);
        spin.addChild(rotator);
        //Primer triangulo

        //Segundo triangulo
        TransformGroup spin2 = new TransformGroup();
        spin2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(spin2);

        TransformGroup tg2 = new TransformGroup();
        spin2.addChild(tg2);

        Transform3D SBR01 = new Transform3D();
        Transform3D TBR01 = new Transform3D();
        Transform3D RBR01 = new Transform3D();
        Transform3D RBR11 = new Transform3D();

        SBR01.setScale(new Vector3d(0.4, 1, 1));
        TBR01.setTranslation(new Vector3f(0.0f, 0.0f, 0.1f));
        TransformGroup tgb01 = new TransformGroup(SBR01);
        TransformGroup tgb11 = new TransformGroup(RBR01);
        TransformGroup tgb21 = new TransformGroup(RBR11);
        TransformGroup tgb31 = new TransformGroup(TBR01);

        Cone tri2 = new Cone(0.1f, 0.15f, paratextura, aparienciaDor);
        tri2.setAppearance(aparienciaDor);
        tgb01.addChild(tri2);
        tgb11.addChild(tgb01);
        tgb21.addChild(tgb11);
        tgb31.addChild(tgb21);
        tg2.addChild(tgb31);

        Alpha alpha1 = new Alpha(-1, 5500);
        RotationInterpolator rotator1 = new RotationInterpolator(alpha1, spin2);
        BoundingSphere bounds1 = new BoundingSphere();
        rotator1.setSchedulingBounds(bounds1);
        spin2.addChild(rotator1);
        //Segundo triangulo

        //Tercer triangulo
        TransformGroup spin3 = new TransformGroup();
        spin3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(spin3);

        TransformGroup tg3 = new TransformGroup();
        spin3.addChild(tg3);

        Transform3D SBR02 = new Transform3D();
        Transform3D TBR02 = new Transform3D();
        Transform3D RBR02 = new Transform3D();
        Transform3D RBR12 = new Transform3D();

        SBR02.setScale(new Vector3d(0.4, 1, 1));
        TBR02.setTranslation(new Vector3f(0.0f, 0.0f, -0.1f));
        TransformGroup tgb02 = new TransformGroup(SBR02);
        TransformGroup tgb12 = new TransformGroup(RBR02);
        TransformGroup tgb22 = new TransformGroup(RBR12);
        TransformGroup tgb32 = new TransformGroup(TBR02);

        Cone tri3 = new Cone(0.1f, 0.15f, paratextura, aparienciaDor);
        tri3.setAppearance(aparienciaDor);
        tgb02.addChild(tri3);
        tgb12.addChild(tgb02);
        tgb22.addChild(tgb12);
        tgb32.addChild(tgb22);
        tg3.addChild(tgb32);

        Alpha alpha2 = new Alpha(-1, 5500);
        RotationInterpolator rotator2 = new RotationInterpolator(alpha2, spin3);
        BoundingSphere bounds2 = new BoundingSphere();
        rotator2.setSchedulingBounds(bounds2);
        spin3.addChild(rotator2);
        //Tercer triangulo

        //Base trifuerza
        Transform3D tr2 = new Transform3D();
        tr2.set(new Vector3d(new Vector3f(0.0f, -0.3f, 0.0f)));
        Transform3D rot2 = new Transform3D();
        rot2.rotX(Math.toRadians(180));
        tr2.mul(rot2);
        TransformGroup tc2 = new TransformGroup(tr2);
        Cone c2 = new Cone(0.5f, 0.3f, paratextura, aparienciaAzul);
        c2.setAppearance(aparienciaAzul);
        tc2.addChild(c2);

        Transform3D col1 = new Transform3D();
        col1.set(new Vector3d(new Vector3f(0.0f, -0.35f, 0.0f)));
        TransformGroup dc1 = new TransformGroup(col1);
        Box coc1 = new Box(0.4f, 0.1f, 0.4f, paratextura, Apariencialadrillo);
        coc1.setAppearance(Apariencialadrillo);
        dc1.addChild(coc1);

        Transform3D col2 = new Transform3D();
        col2.set(new Vector3d(new Vector3f(0.0f, -0.45f, 0.3f)));
        TransformGroup dc2 = new TransformGroup(col2);
        Box coc2 = new Box(0.4f, 0.1f, 0.4f, paratextura, Apariencialadrillo);
        coc2.setAppearance(Apariencialadrillo);
        dc2.addChild(coc2);

        Transform3D col3 = new Transform3D();
        col3.set(new Vector3d(new Vector3f(0.0f, -0.55f, 0.6f)));
        TransformGroup dc3 = new TransformGroup(col3);
        Box coc3 = new Box(0.4f, 0.1f, 0.4f, paratextura, Apariencialadrillo);
        coc3.setAppearance(Apariencialadrillo);
        dc3.addChild(coc3);

        Transform3D col4 = new Transform3D();
        col4.set(new Vector3d(new Vector3f(0.0f, -0.65f, 0.9f)));
        TransformGroup dc4 = new TransformGroup(col4);
        Box coc4 = new Box(0.4f, 0.1f, 0.4f, paratextura, Apariencialadrillo);
        coc4.setAppearance(Apariencialadrillo);
        dc4.addChild(coc4);

        root.addChild(tc2);
        root.addChild(dc1);
        root.addChild(dc2);
        root.addChild(dc3);
        root.addChild(dc4);
        //Base trifuerza

        //Antorchas1
        Transform3D p1 = new Transform3D();
        p1.set(new Vector3d(new Vector3f(0.5f, -0.57f, 1.2f)));
        TransformGroup pi1 = new TransformGroup(p1);
        Cylinder pilar1 = new Cylinder(0.06f, 0.35f, paratextura, Aparienciapilar);
        pilar1.setAppearance(Aparienciapilar);
        pi1.addChild(pilar1);

        TransformGroup girof1 = new TransformGroup();
        girof1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(girof1);

        Transform3D fu1 = new Transform3D();
        fu1.set(new Vector3f(0.5f, -0.32f, 1.2f));
        TransformGroup fueg1 = new TransformGroup(fu1);
        Cone fuego1 = new Cone(0.06f, 0.15f, paratextura, Aparienciafuego);
        fuego1.setAppearance(Aparienciafuego);
        fueg1.addChild(fuego1);
        girof1.addChild(fueg1);

        Alpha alphaP1 = new Alpha(-1, 5500);
        RotationInterpolator rotatorP1 = new RotationInterpolator(alphaP1, girof1);
        BoundingSphere boundsP1 = new BoundingSphere();
        rotatorP1.setSchedulingBounds(boundsP1);
        rotatorP1.setTransformAxis(p1);
        girof1.addChild(rotatorP1);

        root.addChild(pi1);
        //Antorchas1

        //Antorchas2
        Transform3D p2 = new Transform3D();
        p2.set(new Vector3d(new Vector3f(-0.5f, -0.57f, 1.2f)));
        TransformGroup pi2 = new TransformGroup(p2);
        Cylinder pilar2 = new Cylinder(0.06f, 0.35f, paratextura, Aparienciapilar);
        pilar2.setAppearance(Aparienciapilar);
        pi2.addChild(pilar2);

        TransformGroup girof2 = new TransformGroup();
        girof2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(girof2);

        Transform3D fu2 = new Transform3D();
        fu2.set(new Vector3f(-0.5f, -0.32f, 1.2f));
        TransformGroup fueg2 = new TransformGroup(fu2);
        Cone fuego2 = new Cone(0.06f, 0.15f, paratextura, Aparienciafuego);
        fuego2.setAppearance(Aparienciafuego);
        fueg2.addChild(fuego2);
        girof2.addChild(fueg2);

        Alpha alphaP2 = new Alpha(-1, 5500);
        RotationInterpolator rotatorP2 = new RotationInterpolator(alphaP2, girof2);
        BoundingSphere boundsP2 = new BoundingSphere();
        rotatorP2.setSchedulingBounds(boundsP2);
        rotatorP2.setTransformAxis(p2);
        girof2.addChild(rotatorP2);

        root.addChild(pi2);
        //Antorchas2

        //Antorchas3
        Transform3D p3 = new Transform3D();
        p3.set(new Vector3d(new Vector3f(0.5f, -0.40f, 0.6f)));
        TransformGroup pi3 = new TransformGroup(p3);
        Cylinder pilar3 = new Cylinder(0.06f, 0.7f, paratextura, Aparienciapilar);
        pilar3.setAppearance(Aparienciapilar);
        pi3.addChild(pilar3);

        TransformGroup girof3 = new TransformGroup();
        girof3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(girof3);

        Transform3D fu3 = new Transform3D();
        fu3.set(new Vector3f(0.5f, 0.02f, 0.6f));
        TransformGroup fueg3 = new TransformGroup(fu3);
        Cone fuego3 = new Cone(0.06f, 0.15f, paratextura, Aparienciafuego);
        fuego3.setAppearance(Aparienciafuego);
        fueg3.addChild(fuego3);
        girof3.addChild(fueg3);

        Alpha alphaP3 = new Alpha(-1, 5500);
        RotationInterpolator rotatorP3 = new RotationInterpolator(alphaP3, girof3);
        BoundingSphere boundsP3 = new BoundingSphere();
        rotatorP3.setSchedulingBounds(boundsP3);
        rotatorP3.setTransformAxis(p3);
        girof3.addChild(rotatorP3);
        root.addChild(pi3);
        //Antorchas3

        //Antorchas4
        Transform3D p4 = new Transform3D();
        p4.set(new Vector3d(new Vector3f(-0.5f, -0.40f, 0.6f)));
        TransformGroup pi4 = new TransformGroup(p4);
        Cylinder pilar4 = new Cylinder(0.06f, 0.7f, paratextura, Aparienciapilar);
        pilar4.setAppearance(Aparienciapilar);
        pi4.addChild(pilar4);

        TransformGroup girof4 = new TransformGroup();
        girof4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(girof4);

        Transform3D fu4 = new Transform3D();
        fu4.set(new Vector3f(-0.5f, 0.02f, 0.6f));
        TransformGroup fueg4 = new TransformGroup(fu4);
        Cone fuego4 = new Cone(0.06f, 0.15f, paratextura, Aparienciafuego);
        fuego4.setAppearance(Aparienciafuego);
        fueg4.addChild(fuego4);
        girof4.addChild(fueg4);

        Alpha alphaP4 = new Alpha(-1, 5500);
        RotationInterpolator rotatorP4 = new RotationInterpolator(alphaP4, girof4);
        BoundingSphere boundsP4 = new BoundingSphere();
        rotatorP4.setSchedulingBounds(boundsP4);
        rotatorP4.setTransformAxis(p4);
        girof4.addChild(rotatorP4);

        root.addChild(pi4);
        //Antorchas4

        Background background = new Background(10f, 10f, 10f);
        background.setColor(new Color3f(new Color(199, 199, 199)));
        root.addChild(background);

        AmbientLight light = new AmbientLight(true, new Color3f(Color.black));
//        light.setInfluencingBounds(bounds);
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
/**
 *      Transform3D SBR0 = new Transform3D();
        Transform3D TBR0 = new Transform3D();
        Transform3D RBR0 = new Transform3D();
        Transform3D RBR1 = new Transform3D();     
 *      SBR0.setScale(new Vector3d(0.4, 1, 1));
        RBR0.rotX(-Math.PI / 3);
        RBR1.rotY(Math.PI / 6);
        TBR0.setTranslation(new Vector3f(0.12f, 0.0f, 0.06f));
        TransformGroup tgb0 = new TransformGroup(SBR0);
        TransformGroup tgb1 = new TransformGroup(RBR0);
        TransformGroup tgb2 = new TransformGroup(RBR1);
        TransformGroup tgb3 = new TransformGroup(TBR0);
        Cone brazo1 = new Cone(0.1f, 0.15f, paratextura, apaTunica);
        brazo1.setAppearance(apaTunica);
        tgb0.addChild(brazo1);
        tgb1.addChild(tgb0);
        tgb2.addChild(tgb1);
        tgb3.addChild(tgb2);
        tg.addChild(tgb3);
 */
