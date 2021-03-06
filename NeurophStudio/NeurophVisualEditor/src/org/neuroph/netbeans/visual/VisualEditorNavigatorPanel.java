package org.neuroph.netbeans.visual;

import java.util.Collections;
import javax.swing.JComponent;
import javax.swing.JLabel;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.openide.util.Lookup;
import org.openide.util.Lookup.Result;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author Boris Perović <borisvperovic@gmail.com>
 */
@NavigatorPanel.Registration(mimeType = "text/x-nnet", displayName = "Nnet File Content")
public class VisualEditorNavigatorPanel extends javax.swing.JPanel implements NavigatorPanel, LookupListener {

    InstanceContent content = new InstanceContent();
    AbstractLookup aLookup = new AbstractLookup(content);

    Result<Scene> scenesInLookup;

    /**
     * Creates new form VisualEditorNavigatorPanel
     */
    public VisualEditorNavigatorPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navigatorScrollPane = new javax.swing.JScrollPane();

        setLayout(new java.awt.BorderLayout());
        add(navigatorScrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane navigatorScrollPane;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getDisplayName() {
        return "Nnet File Content";
    }

    @Override
    public String getDisplayHint() {
        return "Nnet File Content";
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    @Override
    public void panelActivated(Lookup context) {
//        scenesInLookup = context.lookupResult(Scene.class); // TODO make consistent
        scenesInLookup = Utilities.actionsGlobalContext().lookupResult(Scene.class);
        scenesInLookup.addLookupListener(this);
        resultChanged(new LookupEvent(scenesInLookup));
    }

    @Override
    public void panelDeactivated() {
        scenesInLookup.removeLookupListener(this);
    }

    @Override
    public Lookup getLookup() {
        return aLookup;
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        if (!scenesInLookup.allInstances().isEmpty()) {
            Scene s = scenesInLookup.allInstances().iterator().next();
            if (s != null) {
                navigatorScrollPane.setViewportView(s.createSatelliteView());
                content.set(Collections.singleton(s), null);
            }
        }else {
//            navigatorScrollPane.setViewportView(new JLabel("<no scene selected>"));
        }
    }
}
