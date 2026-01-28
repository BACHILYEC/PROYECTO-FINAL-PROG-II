package Infrastructure;

import javax.swing.JOptionPane;

public abstract class AppMSG {
    
    private AppMSG() {}
    public static final void show(String msg){
        JOptionPane.showMessageDialog(null, msg, "🤖 Liminalis", JOptionPane.INFORMATION_MESSAGE);
    }
    public static final void showError(String msg){
        JOptionPane.showMessageDialog(null, msg, "🤖 Liminalis", JOptionPane.OK_OPTION);
    }
    public static final boolean showConfirmYesNo(String msg){
        return (JOptionPane.showConfirmDialog(null, msg, "🤖 Liminalis", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }
}
