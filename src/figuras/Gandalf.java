package figuras;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.Alpha;
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
 * Un programador nunca llega tarde ni temprano, solo en el momento adecuado
 * @author Kevin
 */
public class Gandalf extends javax.swing.JInternalFrame
{
    
    BranchGroup root = new BranchGroup();
    int paratextura = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;

    /**
     * Creates new form Gandalf
     */
    public Gandalf()
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
        BranchGroup escenario = Mago();
        escenario.compile();
        SimpleUniverse su = new SimpleUniverse(canvas);
        su.getViewingPlatform().setNominalViewingTransform();
        su.addBranchGraph(escenario);
        
        BoundingSphere bs = new BoundingSphere(new Point3d(), 100.0);
        OrbitBehavior orbita = new OrbitBehavior(canvas, OrbitBehavior.STOP_ZOOM);
        orbita.setSchedulingBounds(bs);
        su.getViewingPlatform().setViewPlatformBehavior(orbita);
    }
    
    public BranchGroup Mago()
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
        TextureLoader cargador = new TextureLoader("src\\imgGF\\fondo.jpg", this);
        Background back = new Background();
        back.setImage(cargador.getImage());
        back.setImageScaleMode(Background.SCALE_FIT_ALL);
        back.setApplicationBounds(limites);
        fondo.addChild(back);
        root.addChild(fondo);
        
        Appearance eyes = new Appearance();
        Material ojos = new Material();
        
        ojos.setShininess(2.5f);
        ojos.setDiffuseColor(new Color3f(Color.yellow));
        ojos.setSpecularColor(new Color3f(Color.white));
        eyes.setMaterial(ojos);
        
        TextureLoader loaderRopa = new TextureLoader("src\\imgGF\\tunica.jpg", "INTENSITY", new Container());
        Texture texturTunica = loaderRopa.getTexture();
        Appearance apaTunica = new Appearance();
        apaTunica.setTexture(texturTunica);
        Material matpi = new Material();
        apaTunica.setMaterial(matpi);
        
        TextureLoader loaderSombrero = new TextureLoader("src\\imgGF\\Sombrero.png", "INTENSITY", new Container());
        Texture texturSobrero = loaderSombrero.getTexture();
        Appearance apaSombrero = new Appearance();
        apaSombrero.setTexture(texturSobrero);
        Material matSom = new Material();
        apaSombrero.setMaterial(matSom);
        
        TextureLoader loaderBarba = new TextureLoader("src\\imgGF\\barba.jpg", "INTENSITY", new Container());
        Texture textuBarba = loaderBarba.getTexture();
        Appearance apaBarba = new Appearance();
        apaBarba.setTexture(textuBarba);
        Material matBar = new Material();
        apaBarba.setMaterial(matBar);
        
        TextureLoader loaderBaston = new TextureLoader("src\\imgGF\\baseOr.jpg", "INTENSITY", new Container());
        Texture textuBaston = loaderBaston.getTexture();
        Appearance apaBaston = new Appearance();
        apaBaston.setTexture(textuBaston);
        Material matBas = new Material();
        apaBaston.setMaterial(matBas);
        
        TextureLoader loaderEsfe = new TextureLoader("src\\imgGF\\esfera.png", "INTENSITY", new Container());
        Texture textuEsfe = loaderEsfe.getTexture();
        Appearance apaEsfe = new Appearance();
        apaEsfe.setTexture(textuEsfe);
        Material matEsf = new Material();
        apaEsfe.setMaterial(matEsf);
        
        DirectionalLight luz = new DirectionalLight(new Color3f(Color.LIGHT_GRAY), new Vector3f(40f, -40f, -40f));
        luz.setInfluencingBounds(new BoundingSphere());
        root.addChild(luz);
        
        Transform3D rot1 = new Transform3D();
        Transform3D rot2 = new Transform3D();
        Transform3D ba = new Transform3D();
        ba.setTranslation(new Vector3f(-0.18f, 0.1f, 0.16f));
        rot1.rotX(-Math.PI / 3);
        rot2.rotY(Math.PI / 6);
        
        TransformGroup tgBston = new TransformGroup(ba);
        TransformGroup tgBstonr1 = new TransformGroup(ba);
        TransformGroup tgBstonr2 = new TransformGroup(ba);
        Cylinder baston = new Cylinder(0.01f, 0.7f, paratextura, apaBarba);
        baston.setAppearance(apaBarba);
        tgBston.addChild(baston);
        tg.addChild(tgBston);
        
        Transform3D tb = new Transform3D();
        tb.setTranslation(new Vector3f(-0.18f, 0.45f, 0.16f));
        Transform3D rot = new Transform3D();
        rot.rotX(Math.toRadians(180));
        tb.mul(rot);
        Cone baseBas = new Cone(0.04f, 0.05f, paratextura, apaBarba);
        baseBas.setAppearance(apaBarba);
        TransformGroup tgBaseBas = new TransformGroup(tb);
        tgBaseBas.addChild(baseBas);
        tg.addChild(tgBaseBas);
        
        TransformGroup girof1 = new TransformGroup();
        girof1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Transform3D esf = new Transform3D();
        esf.set(new Vector3f(-0.18f, 0.5f, 0.16f));
        TransformGroup esfe = new TransformGroup(esf);
        Sphere esfera = new Sphere(0.02f, paratextura, apaEsfe);
        esfera.setAppearance(apaEsfe);
        esfe.addChild(esfera);
        girof1.addChild(esfe);
        tg.addChild(girof1);
        
        Alpha alphaP1 = new Alpha(-1, 5500);
        RotationInterpolator rotatorP1 = new RotationInterpolator(alphaP1, girof1);
        BoundingSphere boundsP1 = new BoundingSphere();
        rotatorP1.setSchedulingBounds(boundsP1);
        rotatorP1.setTransformAxis(esf);
        girof1.addChild(rotatorP1);
        
        Sphere cabeza = new Sphere(0.1f);
        t3d.setTranslation(new Vector3f(0f, 0.15f, 0f));
        TransformGroup tghead = new TransformGroup(t3d);
        tghead.addChild(cabeza);
        tg.addChild(tghead);
        
        Sphere ojo1 = new Sphere(0.01f);
        ojo1.setAppearance(eyes);
        t3d.setTranslation(new Vector3f(0.03f, 0.15f, 0.1f));
        TransformGroup tgEye1 = new TransformGroup(t3d);
        tgEye1.addChild(ojo1);
        tg.addChild(tgEye1);
        
        Sphere ojo2 = new Sphere(0.01f);
        ojo2.setAppearance(eyes);
        t3d.setTranslation(new Vector3f(-0.03f, 0.15f, 0.1f));
        TransformGroup tgEye2 = new TransformGroup(t3d);
        tgEye2.addChild(ojo2);
        tg.addChild(tgEye2);
        
        Sphere bar1 = new Sphere(0.03f, paratextura, apaBarba);
        bar1.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(0f, 0.1f, 0.1f));
        TransformGroup tgBar1 = new TransformGroup(t3d);
        tgBar1.addChild(bar1);
        tg.addChild(tgBar1);
        
        Sphere bar2 = new Sphere(0.03f, paratextura, apaBarba);
        bar2.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(0.02f, 0.1f, 0.1f));
        TransformGroup tgBar2 = new TransformGroup(t3d);
        tgBar2.addChild(bar2);
        tg.addChild(tgBar2);
        
        Sphere bar3 = new Sphere(0.03f, paratextura, apaBarba);
        bar3.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(0.04f, 0.1f, 0.09f));
        TransformGroup tgBar3 = new TransformGroup(t3d);
        tgBar3.addChild(bar3);
        tg.addChild(tgBar3);
        
        Sphere bar4 = new Sphere(0.03f, paratextura, apaBarba);
        bar4.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(0.06f, 0.11f, 0.08f));
        TransformGroup tgBar4 = new TransformGroup(t3d);
        tgBar4.addChild(bar4);
        tg.addChild(tgBar4);
        
        Sphere bar5 = new Sphere(0.03f, paratextura, apaBarba);
        bar5.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(0.08f, 0.13f, 0.07f));
        TransformGroup tgBar5 = new TransformGroup(t3d);
        tgBar5.addChild(bar5);
        tg.addChild(tgBar5);
        
        Sphere bar6 = new Sphere(0.03f, paratextura, apaBarba);
        bar6.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(0.1f, 0.15f, 0.06f));
        TransformGroup tgBar6 = new TransformGroup(t3d);
        tgBar6.addChild(bar6);
        tg.addChild(tgBar6);

        //segunda mitad
        Sphere bar7 = new Sphere(0.03f, paratextura, apaBarba);
        bar7.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(-0.02f, 0.1f, 0.1f));
        TransformGroup tgBar7 = new TransformGroup(t3d);
        tgBar7.addChild(bar7);
        tg.addChild(tgBar7);
        
        Sphere bar8 = new Sphere(0.03f, paratextura, apaBarba);
        bar8.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(-0.04f, 0.1f, 0.09f));
        TransformGroup tgBar8 = new TransformGroup(t3d);
        tgBar8.addChild(bar8);
        tg.addChild(tgBar8);
        
        Sphere bar9 = new Sphere(0.03f, paratextura, apaBarba);
        bar9.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(-0.06f, 0.11f, 0.08f));
        TransformGroup tgBar9 = new TransformGroup(t3d);
        tgBar9.addChild(bar9);
        tg.addChild(tgBar9);
        
        Sphere bar10 = new Sphere(0.03f, paratextura, apaBarba);
        bar10.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(-0.08f, 0.13f, 0.07f));
        TransformGroup tgBar10 = new TransformGroup(t3d);
        tgBar10.addChild(bar10);
        tg.addChild(tgBar10);
        
        Sphere bar11 = new Sphere(0.03f, paratextura, apaBarba);
        bar11.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(-0.1f, 0.15f, 0.06f));
        TransformGroup tgBar11 = new TransformGroup(t3d);
        tgBar11.addChild(bar11);
        tg.addChild(tgBar11);
        //segundo nivel
        Sphere bar12 = new Sphere(0.03f, paratextura, apaBarba);
        bar12.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(-0.08f, 0.10f, 0.07f));
        TransformGroup tgBar12 = new TransformGroup(t3d);
        tgBar12.addChild(bar12);
        tg.addChild(tgBar12);
        
        Sphere bar13 = new Sphere(0.03f, paratextura, apaBarba);
        bar13.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(-0.06f, 0.09f, 0.08f));
        TransformGroup tgBar13 = new TransformGroup(t3d);
        tgBar13.addChild(bar13);
        tg.addChild(tgBar13);
        
        Sphere bar14 = new Sphere(0.03f, paratextura, apaBarba);
        bar14.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(-0.04f, 0.08f, 0.09f));
        TransformGroup tgBar14 = new TransformGroup(t3d);
        tgBar14.addChild(bar14);
        tg.addChild(tgBar14);
        
        Sphere bar15 = new Sphere(0.03f, paratextura, apaBarba);
        bar15.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(-0.02f, 0.07f, 0.1f));
        TransformGroup tgBar15 = new TransformGroup(t3d);
        tgBar15.addChild(bar15);
        tg.addChild(tgBar15);
        //segunda mitad segundo nivel
        
        Sphere bar16 = new Sphere(0.03f, paratextura, apaBarba);
        bar16.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(0.08f, 0.10f, 0.07f));
        TransformGroup tgBar16 = new TransformGroup(t3d);
        tgBar16.addChild(bar16);
        tg.addChild(tgBar16);
        
        Sphere bar17 = new Sphere(0.03f, paratextura, apaBarba);
        bar17.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(0.06f, 0.09f, 0.08f));
        TransformGroup tgBar17 = new TransformGroup(t3d);
        tgBar17.addChild(bar17);
        tg.addChild(tgBar17);
        
        Sphere bar18 = new Sphere(0.03f, paratextura, apaBarba);
        bar18.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(0.04f, 0.08f, 0.09f));
        TransformGroup tgBar18 = new TransformGroup(t3d);
        tgBar18.addChild(bar18);
        tg.addChild(tgBar18);
        
        Sphere bar19 = new Sphere(0.03f, paratextura, apaBarba);
        bar19.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(0.02f, 0.07f, 0.1f));
        TransformGroup tgBar19 = new TransformGroup(t3d);
        tgBar19.addChild(bar19);
        tg.addChild(tgBar19);
        
        Sphere bar20 = new Sphere(0.03f, paratextura, apaBarba);
        bar20.setAppearance(apaBarba);
        t3d.setTranslation(new Vector3f(0f, 0.06f, 0.1f));
        TransformGroup tgBar20 = new TransformGroup(t3d);
        tgBar20.addChild(bar20);
        tg.addChild(tgBar20);
        
        Cone hat1 = new Cone(0.2f, 0.01f, paratextura, apaSombrero);
        hat1.setAppearance(apaSombrero);
        Cone hat2 = new Cone(0.12f, 0.3f, paratextura, apaSombrero);
        hat2.setAppearance(apaSombrero);
        
        Transform3D RH1 = new Transform3D();
        Transform3D TH1 = new Transform3D();
        Transform3D TH2 = new Transform3D();
        TH1.setTranslation(new Vector3f(0f, 0.15f, 0f));
        TH2.setTranslation(new Vector3f(0f, 0.15f, 0f));
        RH1.rotX(-Math.PI / 5);
        TransformGroup tghat0 = new TransformGroup(TH2);
        tghat0.addChild(hat2);
        TransformGroup tghat1 = new TransformGroup(RH1);
        TransformGroup tghat2 = new TransformGroup(TH1);
        tghat1.addChild(hat1);
        tghat1.addChild(tghat0);
        tghat2.addChild(tghat1);
        tg.addChild(tghat2);
        
        Cone body = new Cone(0.16f, 0.4f, paratextura, apaTunica);
        body.setAppearance(apaTunica);
        t3d.setTranslation(new Vector3f(0f, 0.0f, 0.0f));
        TransformGroup tgb = new TransformGroup(t3d);
        tgb.addChild(body);
        tg.addChild(tgb);
        
        Sphere hombro1 = new Sphere(0.04f, paratextura, apaTunica);
        hombro1.setAppearance(apaTunica);
        t3d.setTranslation(new Vector3f(0.095f, 0.03f, 0.0f));
        TransformGroup tgh1 = new TransformGroup(t3d);
        tgh1.addChild(hombro1);
        tg.addChild(tgh1);
        
        Sphere hombro2 = new Sphere(0.04f, paratextura, apaTunica);
        hombro2.setAppearance(apaTunica);
        t3d.setTranslation(new Vector3f(-0.095f, 0.03f, 0.0f));
        TransformGroup tgh2 = new TransformGroup(t3d);
        tgh2.addChild(hombro2);
        tg.addChild(tgh2);
        
        Transform3D SBR0 = new Transform3D();
        Transform3D TBR0 = new Transform3D();
        Transform3D RBR0 = new Transform3D();
        Transform3D RBR1 = new Transform3D();
        
        SBR0.setScale(new Vector3d(0.4, 1, 1));
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
        
        Transform3D SBR1 = new Transform3D();
        Transform3D TBR1 = new Transform3D();
        Transform3D RBR2 = new Transform3D();
        Transform3D RBR3 = new Transform3D();
        
        SBR1.setScale(new Vector3d(0.4, 1, 1));
        RBR2.rotX(-Math.PI / 3);
        RBR3.rotY(-Math.PI / 6);
        TBR1.setTranslation(new Vector3f(-0.12f, 0.0f, 0.06f));
        TransformGroup tgb4 = new TransformGroup(SBR1);
        TransformGroup tgb5 = new TransformGroup(RBR2);
        TransformGroup tgb6 = new TransformGroup(RBR3);
        TransformGroup tgb7 = new TransformGroup(TBR1);
        Cone brazo2 = new Cone(0.1f, 0.15f, paratextura, apaTunica);
        brazo2.setAppearance(apaTunica);
        tgb4.addChild(brazo2);
        tgb5.addChild(tgb4);
        tgb6.addChild(tgb5);
        tgb7.addChild(tgb6);
        tg.addChild(tgb7);
        
        Sphere mano1 = new Sphere(0.04f);
        t3d.setTranslation(new Vector3f(-0.18f, 0.0f, 0.16f));
        TransformGroup tgh3 = new TransformGroup(t3d);
        tgh3.addChild(mano1);
        tg.addChild(tgh3);
        
        Sphere mano2 = new Sphere(0.04f);
        t3d.setTranslation(new Vector3f(0.18f, 0.0f, 0.16f));
        TransformGroup tgh4 = new TransformGroup(t3d);
        tgh4.addChild(mano2);
        tg.addChild(tgh4);
        Alpha alpha = new Alpha(-1, 5500);
        RotationInterpolator rotator = new RotationInterpolator(alpha, spin);
        BoundingSphere bounds = new BoundingSphere();
        rotator.setSchedulingBounds(bounds);
        spin.addChild(rotator);
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
            .addGap(0, 585, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
