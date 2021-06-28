package figuras;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Cone;
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
 * No es original mio, pero lo modifique para hacer dos y ponerles texturas
 * @author Kevin
 */
public class Magito extends javax.swing.JInternalFrame
{

    BranchGroup root = new BranchGroup();

    /**
     * Creates new form Magito
     */
    public Magito()
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
        Appearance dark = new Appearance();
        Material sombra = new Material();
        Appearance cape = new Appearance();
        Material capa = new Material();
        Appearance hat = new Appearance();
        Material sombrero = new Material();
        Appearance eyes = new Appearance();
        Material ojos = new Material();

        TransformGroup spin = new TransformGroup();
        spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(spin);

        sombra.setShininess(0);
        sombra.setDiffuseColor(0, 0, 0);
        sombra.setSpecularColor(new Color3f(new Color(87, 35, 100)));
        dark.setMaterial(sombra);

        capa.setShininess(0.5f);
        capa.setSpecularColor(new Color3f(new Color(63, 72, 204)));
        capa.setSpecularColor(new Color3f(Color.GREEN));
        cape.setMaterial(capa);

        sombrero.setShininess(0.3f);
        sombrero.setDiffuseColor(new Color3f(new Color(185, 122, 87)));
        sombrero.setSpecularColor(new Color3f(new Color(125, 62, 27)));
        sombrero.setEmissiveColor(new Color3f(new Color(105, 42, 07)));
        hat.setMaterial(sombrero);

        ojos.setShininess(2.5f);
        ojos.setDiffuseColor(new Color3f(Color.ORANGE));
        ojos.setSpecularColor(new Color3f(Color.white));
        eyes.setMaterial(ojos);

        TransformGroup tg = new TransformGroup();
        Transform3D t3d = new Transform3D();
        spin.addChild(tg);

        BoundingBox limites
                = new BoundingBox(new Point3d(-100, -100, -100),
                        new Point3d(100, 100, 100));
        TransformGroup fondo = new TransformGroup();
        TextureLoader cargador = new TextureLoader("src\\imgDM\\tot.jpeg", this);
        Background back = new Background();
        back.setImage(cargador.getImage());
        back.setImageScaleMode(Background.SCALE_FIT_ALL);
        back.setApplicationBounds(limites);
        fondo.addChild(back);
        root.addChild(fondo);

        DirectionalLight luz = new DirectionalLight(new Color3f(Color.LIGHT_GRAY), new Vector3f(40f, -40f, -40f));
        luz.setInfluencingBounds(new BoundingSphere());
        root.addChild(luz);

        TextureLoader loader = new TextureLoader("src\\imgDM\\text1.jpg", "INTENSITY", new Container());
        Texture textura = loader.getTexture();
        Appearance apariencia = new Appearance();
        apariencia.setTexture(textura);
        Material mat = new Material();
        apariencia.setMaterial(mat);
        int paratextura = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;

        Sphere Planeta = new Sphere(0.5f, paratextura, apariencia);
        Planeta.setAppearance(apariencia);
        t3d.setTranslation(new Vector3f(0.0f, 0.5f, 0f));
        TransformGroup tgh8 = new TransformGroup(t3d);
        tgh8.addChild(Planeta);
        tg.addChild(tgh8);

        Sphere cabeza = new Sphere(0.1f);
        cabeza.setAppearance(dark);
        t3d.setTranslation(new Vector3f(0f, -0.1f, -0.70f));
        TransformGroup tghead = new TransformGroup(t3d);
        tghead.addChild(cabeza);
        tg.addChild(tghead);

        Sphere ojo1 = new Sphere(0.01f);
        ojo1.setAppearance(eyes);
        t3d.setTranslation(new Vector3f(0.03f, -0.1f, -0.59f));
        TransformGroup tgEye1 = new TransformGroup(t3d);
        tgEye1.addChild(ojo1);
        tg.addChild(tgEye1);

        Sphere ojo2 = new Sphere(0.01f);
        ojo2.setAppearance(eyes);
        t3d.setTranslation(new Vector3f(-0.03f, -0.1f, -0.59f));
        TransformGroup tgEye2 = new TransformGroup(t3d);
        tgEye2.addChild(ojo2);
        tg.addChild(tgEye2);

        TextureLoader loaderhat1 = new TextureLoader("src\\imgDM\\hat1.jpg", "INTENSITY", new Container());
        Texture texturehat1 = loaderhat1.getTexture();
        Appearance aparienciahat1 = new Appearance();
        aparienciahat1.setTexture(texturehat1);
        Material materialhat1 = new Material();
        aparienciahat1.setMaterial(materialhat1);

        Cone hat1 = new Cone(0.2f, 0.01f, paratextura, aparienciahat1);
        hat1.setAppearance(aparienciahat1);
        Cone hat2 = new Cone(0.12f, 0.3f, paratextura, aparienciahat1);
        hat2.setAppearance(aparienciahat1);

        Transform3D RH1 = new Transform3D();
        Transform3D TH1 = new Transform3D();
        Transform3D TH2 = new Transform3D();
        TH1.setTranslation(new Vector3f(0f, -0.1f, -0.7f));
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

        TextureLoader loaderBody1 = new TextureLoader("src\\imgDM\\pt.jpg", "INTENSITY", new Container());
        Texture textureB2 = loaderBody1.getTexture();
        Appearance aparienciaBody1 = new Appearance();
        aparienciaBody1.setTexture(textureB2);
        Material materialB1 = new Material();
        aparienciaBody1.setMaterial(materialB1);

        Cone body = new Cone(0.16f, 0.4f, paratextura, aparienciaBody1);
        body.setAppearance(aparienciaBody1);
        t3d.setTranslation(new Vector3f(0f, -0.25f, -0.7f));
        TransformGroup tgb = new TransformGroup(t3d);
        tgb.addChild(body);
        tg.addChild(tgb);

        TextureLoader loaderhom = new TextureLoader("src\\imgDM\\h22.png", "INTENSITY", new Container());
        Texture texturehom = loaderhom.getTexture();
        Appearance aparienciahom = new Appearance();
        aparienciahom.setTexture(texturehom);
        Material materialhom = new Material();
        aparienciahom.setMaterial(materialhom);

        Sphere hombro1 = new Sphere(0.04f, paratextura, aparienciahom);
        hombro1.setAppearance(aparienciahom);
        t3d.setTranslation(new Vector3f(0.095f, -0.22f, -0.7f));
        TransformGroup tgh1 = new TransformGroup(t3d);
        tgh1.addChild(hombro1);
        tg.addChild(tgh1);

        Sphere hombro2 = new Sphere(0.04f, paratextura, aparienciahom);
        hombro2.setAppearance(aparienciahom);
        t3d.setTranslation(new Vector3f(-0.095f, -0.22f, -0.7f));
        TransformGroup tgh2 = new TransformGroup(t3d);
        tgh2.addChild(hombro2);
        tg.addChild(tgh2);

        Transform3D SBR0 = new Transform3D();
        Transform3D TBR0 = new Transform3D();
        Transform3D RBR0 = new Transform3D();
        Transform3D RBR1 = new Transform3D();

        SBR0.setScale(new Vector3d(0.4, 1, 1));
        RBR0.rotX(-1.94);
        RBR1.rotY(0.52);
        TBR0.setTranslation(new Vector3f(0.12f, -0.2f, -0.64f));
        TransformGroup tgb0 = new TransformGroup(SBR0);
        TransformGroup tgb1 = new TransformGroup(RBR0);
        TransformGroup tgb2 = new TransformGroup(RBR1);
        TransformGroup tgb3 = new TransformGroup(TBR0);

        Cone brazo1 = new Cone(0.1f, 0.15f, paratextura, aparienciaBody1);
        brazo1.setAppearance(aparienciaBody1);
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
        RBR2.rotX(-1.94);
        RBR3.rotY(-0.52);

        TBR1.setTranslation(new Vector3f(-0.12f, -0.2f, -0.64f));
        TransformGroup tgb4 = new TransformGroup(SBR1);
        TransformGroup tgb5 = new TransformGroup(RBR2);
        TransformGroup tgb6 = new TransformGroup(RBR3);
        TransformGroup tgb7 = new TransformGroup(TBR1);

        Cone brazo2 = new Cone(0.1f, 0.15f, paratextura, aparienciaBody1);
        brazo2.setAppearance(aparienciaBody1);
        tgb4.addChild(brazo2);
        tgb5.addChild(tgb4);
        tgb6.addChild(tgb5);
        tgb7.addChild(tgb6);
        tg.addChild(tgb7);

        Sphere mano1 = new Sphere(0.04f);
        mano1.setAppearance(dark);
        t3d.setTranslation(new Vector3f(-0.16f, -0.13f, -0.57f));
        TransformGroup tgh3 = new TransformGroup(t3d);
        tgh3.addChild(mano1);
        tg.addChild(tgh3);

        Sphere mano2 = new Sphere(0.04f);
        mano2.setAppearance(dark);
        t3d.setTranslation(new Vector3f(0.16f, -0.13f, -0.57f));
        TransformGroup tgh4 = new TransformGroup(t3d);
        tgh4.addChild(mano2);
        tg.addChild(tgh4);

        //////////////////////////////////////////////////////////////////////
        Sphere cabezaM2 = new Sphere(0.1f);
        cabezaM2.setAppearance(dark);
        t3d.setTranslation(new Vector3f(0f, -0.1f, 0.70f));
        TransformGroup tgheadM2 = new TransformGroup(t3d);
        tgheadM2.addChild(cabezaM2);
        tg.addChild(tgheadM2);

        Sphere ojo1M2 = new Sphere(0.01f);
        ojo1M2.setAppearance(eyes);
        t3d.setTranslation(new Vector3f(0.03f, -0.1f, 0.59f));
        TransformGroup tgEye1M2 = new TransformGroup(t3d);
        tgEye1M2.addChild(ojo1M2);
        tg.addChild(tgEye1M2);

        Sphere ojo2M2 = new Sphere(0.01f);
        ojo2M2.setAppearance(eyes);
        t3d.setTranslation(new Vector3f(-0.03f, -0.1f, 0.59f));
        TransformGroup tgEye2M2 = new TransformGroup(t3d);
        tgEye2M2.addChild(ojo2M2);
        tg.addChild(tgEye2M2);

        TextureLoader loaderhat2 = new TextureLoader("src\\imgDM\\hat2.jpg", "INTENSITY", new Container());
        Texture texturehat2 = loaderhat2.getTexture();
        Appearance aparienciahat2 = new Appearance();
        aparienciahat2.setTexture(texturehat2);
        Material materialhat2 = new Material();
        aparienciahat2.setMaterial(materialhat2);

        Cone hat1M2 = new Cone(0.2f, 0.01f, paratextura, aparienciahat2);
        hat1M2.setAppearance(aparienciahat2);
        Cone hat2M2 = new Cone(0.12f, 0.3f, paratextura, aparienciahat2);
        hat2M2.setAppearance(aparienciahat2);

        Transform3D RH1M2 = new Transform3D();
        Transform3D TH1M2 = new Transform3D();
        Transform3D TH2M2 = new Transform3D();
        TH1M2.setTranslation(new Vector3f(0f, -0.1f, 0.7f));
        TH2M2.setTranslation(new Vector3f(0f, 0.15f, 0f));
        RH1M2.rotX(Math.PI / 5);
        TransformGroup tghat0M2 = new TransformGroup(TH2M2);
        tghat0M2.addChild(hat2M2);
        TransformGroup tghat1M2 = new TransformGroup(RH1M2);
        TransformGroup tghat2M2 = new TransformGroup(TH1M2);
        tghat1M2.addChild(hat1M2);
        tghat1M2.addChild(tghat0M2);
        tghat2M2.addChild(tghat1M2);
        tg.addChild(tghat2M2);

        TextureLoader loaderBody2 = new TextureLoader("src\\imgDM\\pt2.png", "INTENSITY", new Container());
        Texture textureB22 = loaderBody2.getTexture();
        Appearance aparienciaBody2 = new Appearance();
        aparienciaBody2.setTexture(textureB22);
        Material materialB2 = new Material();
        aparienciaBody2.setMaterial(materialB2);

        Cone bodyM2 = new Cone(0.16f, 0.4f, paratextura, aparienciaBody2);
        bodyM2.setAppearance(aparienciaBody2);
        t3d.setTranslation(new Vector3f(0f, -0.25f, 0.7f));
        TransformGroup tgbM2 = new TransformGroup(t3d);
        tgbM2.addChild(bodyM2);
        tg.addChild(tgbM2);

        TextureLoader loaderhom2 = new TextureLoader("src\\imgDM\\h2.png", "INTENSITY", new Container());
        Texture texturehom2 = loaderhom2.getTexture();
        Appearance aparienciahom2 = new Appearance();
        aparienciahom2.setTexture(texturehom2);
        Material materialhom2 = new Material();
        aparienciahom2.setMaterial(materialhom2);

        Sphere hombro1M2 = new Sphere(0.04f, paratextura, aparienciahom2);
        hombro1M2.setAppearance(aparienciahom2);
        t3d.setTranslation(new Vector3f(0.095f, -0.22f, 0.7f));
        TransformGroup tgh1M2 = new TransformGroup(t3d);
        tgh1M2.addChild(hombro1M2);
        tg.addChild(tgh1M2);

        Sphere hombro2M2 = new Sphere(0.04f, paratextura, aparienciahom2);
        hombro2M2.setAppearance(aparienciahom2);
        t3d.setTranslation(new Vector3f(-0.095f, -0.22f, 0.7f));
        TransformGroup tgh2M2 = new TransformGroup(t3d);
        tgh2M2.addChild(hombro2M2);
        tg.addChild(tgh2M2);

        Transform3D SBR0M2 = new Transform3D();
        Transform3D TBR0M2 = new Transform3D();
        Transform3D RBR0M2 = new Transform3D();
        Transform3D RBR1M2 = new Transform3D();

        SBR0M2.setScale(new Vector3d(0.4, 1, 1));
        RBR0M2.rotX(1.94);
        RBR1M2.rotY(-0.52);
        TBR0M2.setTranslation(new Vector3f(0.12f, -0.2f, 0.64f));
        TransformGroup tgb0M2 = new TransformGroup(SBR0M2);
        TransformGroup tgb1M2 = new TransformGroup(RBR0M2);
        TransformGroup tgb2M2 = new TransformGroup(RBR1M2);
        TransformGroup tgb3M2 = new TransformGroup(TBR0M2);

        Cone brazo1M2 = new Cone(0.1f, 0.15f, paratextura, aparienciaBody2);
        brazo1M2.setAppearance(aparienciaBody2);
        tgb0M2.addChild(brazo1M2);
        tgb1M2.addChild(tgb0M2);
        tgb2M2.addChild(tgb1M2);
        tgb3M2.addChild(tgb2M2);
        tg.addChild(tgb3M2);

        Transform3D SBR1M2 = new Transform3D();
        Transform3D TBR1M2 = new Transform3D();
        Transform3D RBR2M2 = new Transform3D();
        Transform3D RBR3M2 = new Transform3D();

        SBR1M2.setScale(new Vector3d(0.4, 1, 1));
        RBR2M2.rotX(1.94);
        RBR3M2.rotY(0.52);

        TBR1M2.setTranslation(new Vector3f(-0.12f, -0.2f, 0.64f));
        TransformGroup tgb4M2 = new TransformGroup(SBR1M2);
        TransformGroup tgb5M2 = new TransformGroup(RBR2M2);
        TransformGroup tgb6M2 = new TransformGroup(RBR3M2);
        TransformGroup tgb7M2 = new TransformGroup(TBR1M2);

        Cone brazo2M2 = new Cone(0.1f, 0.15f, paratextura, aparienciaBody2);
        brazo2M2.setAppearance(aparienciaBody2);
        tgb4M2.addChild(brazo2M2);
        tgb5M2.addChild(tgb4M2);
        tgb6M2.addChild(tgb5M2);
        tgb7M2.addChild(tgb6M2);
        tg.addChild(tgb7M2);

        Sphere mano1M2 = new Sphere(0.04f);
        mano1M2.setAppearance(dark);
        t3d.setTranslation(new Vector3f(-0.16f, -0.13f, 0.57f));
        TransformGroup tgh3M2 = new TransformGroup(t3d);
        tgh3M2.addChild(mano1M2);
        tg.addChild(tgh3M2);

        Sphere mano2M2 = new Sphere(0.04f);
        mano2M2.setAppearance(dark);
        t3d.setTranslation(new Vector3f(0.16f, -0.13f, 0.57f));
        TransformGroup tgh4M2 = new TransformGroup(t3d);
        tgh4M2.addChild(mano2M2);
        tg.addChild(tgh4M2);

        //////////////////////////////////////////////////////////////////////
        Alpha alpha = new Alpha(-1, 5500);
        RotationInterpolator rotator = new RotationInterpolator(alpha, spin);
        BoundingSphere bounds = new BoundingSphere();
        rotator.setSchedulingBounds(bounds);
        spin.addChild(rotator);

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
            .addGap(0, 434, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
