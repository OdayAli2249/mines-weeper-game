/* هاد كلاس بيعمل النافذة اللي بدنا ياها بتابع الكرييت      */
/*  استدعي هاد التابع بالمكان اللي بتشوفي مناسب */
/* بناخد المعومات من المتحولات اللي عرفتا تحت بس تروح النافذة*/
package utils;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Emperor
 */
public class FieldWindow {
/* متحولات الها علاقة بعناصر النافذة فقط */
    public static ImageIcon MyImage;
    public String Name;
    public static JFrame frame = null;
    public static JPanel panel = null;
    public static JTextField field = null;
    public static JTextField field1 = null;
    public static JTextField field2 = null;
    public static JTextField field3 = null;
    public static JTextField field4 = null;
    public static JTextField field6 = null;

    public static JLabel label = null;
    public static JLabel label1 = null;
    public static JLabel label2 = null;
    public static JLabel label3 = null;
    public static JLabel label4 = null;
    public static JLabel label5 = null;
    public static JLabel label6 = null;
    public static JButton button = null;
    public static JButton button1 = null;
    
    /* متحولات الها علاقة بداتا اللي بدنا نجيبها من المستخدم */

    public static String StartGameDate;
    public static String EndGameDate;
    public static String FirstPlayerName;
    public static String SecondPlayerNamel;
    public static String SaveFile="Rano.ser";
    public static Integer SaveOrder=1;
    public static boolean SaveOrNotForDisplay;
    
    public static boolean WindowAlive = true; // هاد متحول بدلنا ازا النافذة شغالة او لا
 /* تابع انشاء النافذة */
    public static void CreateWindow() {

        frame = new JFrame();

        frame.setSize(new Dimension(300, 300));
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setSize(new Dimension(500, 500));

        panel = new JPanel();
        label = new JLabel("Start Game Date : ");
        label.setLocation(120, 0);

        field = new JTextField(40);
        field.setLocation(120, 20);
        field.setSize(10, 10);

        label1 = new JLabel("End Game Date : ");
        label1.setLocation(120, 100);

        field1 = new JTextField(40);
        field1.setLocation(120, 120);
        field1.setSize(10, 10);

        label2 = new JLabel("First Player Name : ");
        label2.setLocation(120, 200);

        field2 = new JTextField(40);
        field2.setLocation(120, 220);
        field2.setSize(10, 10);

        label3 = new JLabel("Second Player Name (if found) : ");
        label3.setLocation(120, 300);

        field3 = new JTextField(40);
        field3.setLocation(120, 320);
        field3.setSize(10, 10);

        label4 = new JLabel("Save File Path : ");
        label4.setLocation(120, 400);

        field4 = new JTextField(40);
        field4.setLocation(120, 420);
        field4.setSize(30, 30);

        label6 = new JLabel("Save File Location (Order) : ");
        label6.setLocation(120, 500);

        field6 = new JTextField(40);
        field6.setLocation(120, 520);
        field6.setSize(30, 30);

        JLabel label5 = new JLabel("Save game for display ? ");
        label5.setLocation(120, 600);

        button = new JButton("NO");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                /* هون بنحدد شوبيصير لما بنضغط عزر ال نو يعني بناخد البيانات من الحقول وبنحطا بمتحولا مشان بس تروح النافذة ناخد المعلومات من هي المتحولات */
                
                StartGameDate = field.getText();
                EndGameDate = field1.getText();
                FirstPlayerName = field2.getText();
                SecondPlayerNamel = field3.getText();
                SaveFile = "Rano.ser"; // هون بما انو اختا انو ما يحفظ معناها ما لازم ناخد اللي دخلو بالحقل
                SaveOrder = -1; // هون حولنا السترينغ لعدد انتيجر
                SaveOrNotForDisplay = false;
                WindowAlive = false; // حطينا هاد المتحول هيك لانو التعليمة اللي بعدا بنحذف النافذة وهيك بنعرف انو النافذة راحت مشان نوقف الحلقة تبع السليب متل ما عملنا قبل
                frame.dispose();     // ولازم نرجعو ترو بعد ما تنتهي الحلقة مشان بس نشغل النافذة مرة تانية متل ما عملنا بالسيف
                PrintData();
            }

        });

        button.setLocation(300, 600);

        button1 = new JButton("YES");
        button1.setLocation(100, 600);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartGameDate = field.getText();
                EndGameDate = field1.getText();
                FirstPlayerName = field2.getText();
                SecondPlayerNamel = field3.getText();
                SaveFile = field4.getText();
                SaveOrder = Integer.parseInt(field6.getText());
                SaveOrNotForDisplay = true;
                 WindowAlive = false; 
                frame.dispose();
                PrintData();
            }

        });

        //panel.add(label);
        //panel.add(field);
        //panel.add(label1);
        //panel.add(field1);
        panel.add(label2);
        panel.add(field2);
        panel.add(label3);
        panel.add(field3);
        panel.add(label4);
        panel.add(field4);
        panel.add(label6);
        panel.add(field6);
        panel.add(label5);
        panel.add(button);
        panel.add(button1);

        frame.add(panel);

        System.out.println("End");

    }  /* نهاية تابع انشاء النافذة */
    
    public static void PrintData(){
    
        System.out.println(StartGameDate);
        System.out.println(EndGameDate);
        System.out.println(FirstPlayerName);
        System.out.println(SecondPlayerNamel);
        System.out.println(SaveFile);
        System.out.println(SaveOrder);
        System.out.println(SaveOrNotForDisplay);
    
    }
   // ملاحظة لازم نعمل بعد استدعاء تابع الكرييت حلقة قيها سليب تضل شغالة لحتى نتاكد انو راحت النافذة متل ما عملنا سابقا
}
