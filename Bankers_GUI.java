import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Main Window of InterFace
public class Bankers_GUI extends JFrame{
    private int totalProcess = 1;
    private int totalResources = 0;
    public JPanel HomeTop;
    public JPanel HomeBottomL;
    public JPanel HomeBottomR;
    public JPanel HomeMiddle;
    public JLabel resourceMaxLabel;
    public JLabel Label1;
    public JLabel Label2;
    public JLabel Label3;
    public JLabel Label4;
    public JLabel Label5;
    public JLabel Label6;
    public Integer[][] AllocationMatrix;
    public Integer[][] MaxMatrix;
    public Integer[] MaxAvailableResource;
    public JTextField[] maxAvailableResource;
    public JTextField[][] allocationMatrix;
    public JTextField[][] maxMatrix;
    
    // It contain total 4 Panels namely HomeTop, HomeMiddle, HomeBottomL, HomeBottomR
    Bankers_GUI(){
        GridBagConstraints gd = new GridBagConstraints(); // GridBagLayout is used as it is more Advance
        setTitle("Banker's Algorithm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // It will Close all Windows if it is Closed
        setLayout(new GridBagLayout());
        HomeTop = new InputTopPanel();
            gd.gridx = 0;     // Position in Row
            gd.gridy = 0;     // Position in Column
            gd.gridwidth = 2; // like Column Span
            gd.weightx = 1;   // relative width of Cell
            gd.weighty = 0.3; // relative height of Cell
            HomeTop.setBackground(new Color(51,51,51)); // Set Background SoftBlack
            gd.fill = GridBagConstraints.BOTH; // It shall Extend to fill space in Both Direction
        add(HomeTop, gd); // add Panel to Frame
        HomeMiddle = new InputMaxPanel();
            gd.gridx = 0;
            gd.gridy = 1;
            gd.gridwidth = 2;
            gd.weightx = 1;
            gd.weighty = 0.1;
            HomeMiddle.setBackground(new Color(51,51,51));
            gd.fill = GridBagConstraints.BOTH;
        add(HomeMiddle, gd);
        // BottomLeft
        HomeBottomL = new InputBottomLPanel();
            gd.gridx = 0;
            gd.gridy = 2;
            gd.gridwidth = 1;
            gd.weighty = 0.7;
            gd.fill = GridBagConstraints.BOTH;
            HomeBottomL.setBackground(new Color(51,51,51));
        add(HomeBottomL, gd);
        // BottomLeft
        HomeBottomR = new InputBottomRPanel();
            gd.gridx = 1;
            gd.gridy = 2;
            gd.gridwidth = 1;
            gd.weighty = 0.7;
            gd.fill = GridBagConstraints.BOTH;
            HomeBottomR.setBackground(new Color(51,51,51));
        add(HomeBottomR, gd);
        setResizable(true); // Enable Resizing Window
        setMinimumSize(new Dimension(1024, 680)); // Set Minimum Dimensions of Window
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize it by Default
        setVisible(true); // Make Frame Visible
    }

    // Jpanel Extended Class for HomeBottomL
    class InputBottomLPanel extends JPanel{
        // It has 3 Labels namely Title(ex "Allocation Matrix"), Row Legend(ex. Processes) & Column Legend(ex. Resources ->)
        // It also Contains Array/Collection of TextFields for Input of Max/Allocation Matrix
        InputBottomLPanel(){
            setLayout(new GridLayout(totalProcess, totalResources, 5, 5));
            Label1 = new JLabel("Processes \u2191 ");
            Label1.setVisible(false);

            Label2 = new JLabel("Allocation Matrix ");
            Label2.setVisible(false);

            Label3 = new JLabel("Resources \u2192 ");
            Label3.setVisible(false);
            createAllocationMatrix(totalProcess, totalResources);
        }
        // Written separate to update by Refreshing/Repainting
        public void createAllocationMatrix(int totalProcess, int totalResources) {
            this.removeAll(); // Clear existing components
            this.setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();
            Insets padding = new Insets(2, 2, 2, 2); // Padding Between Items
            // Config Label4 having text "Processes \u2191 "
                gc.gridx = 0;
                gc.gridy = 0;
                gc.gridheight = totalProcess + 2;
                Label1.setForeground(Color.WHITE); // Used to set Color of Font
            this.add(Label1, gc);
            gc.gridheight = 1;
            // Config Label5 having text "Allocation Matrix "
                gc.gridx = 1;
                gc.gridy = 0;
                gc.gridwidth = totalResources+1;
                Label2.setFont(new Font("Arial", Font.PLAIN, 16));
                Label2.setForeground(Color.WHITE);
            this.add(Label2, gc);
            // Config Label6 having text "Resources \u2192 "
                gc.gridx = 1;
                gc.gridy = 1;
                gc.gridwidth = totalResources+1;
                Label3.setForeground(Color.WHITE);
            this.add(Label3, gc);
            gc.gridwidth = 1;
            // Create the new matrix based on current InputField Values
            allocationMatrix = new JTextField[totalProcess][totalResources];
            for (int i = 0; i < totalProcess; i++) {
                for (int j = 0; j < totalResources; j++) {
                    // Assigned to Global Public 2D List/Array
                    allocationMatrix[i][j] = new JTextField(2);
                    allocationMatrix[i][j].setFont(new Font("Arial", Font.PLAIN, 12));
                    allocationMatrix[i][j].setBorder(new EmptyBorder(5, 5, 5, 5));
                    gc.gridx = j+1;
                    gc.gridy = i+2;
                    gc.insets = padding;
                    this.add(allocationMatrix[i][j], gc);
                }
            }
            revalidate(); // Refresh the layout
            repaint(); // Repaint the panel
        }
    }

    // Jpanel Extended Class for HomeBottomR
    class InputBottomRPanel extends JPanel{
        // It has 3 Labels namely Title(ex "Allocation Matrix"), Row Legend(ex. Processes) & Column Legend(ex. Resources ->)
        // It also Contains Array/Collection of TextFields for Input of Max/Allocation Matrix
        InputBottomRPanel(){
            setLayout(new GridLayout(totalProcess, totalResources, 5, 5));
            Label4 = new JLabel("Processes \u2191 ");
            Label4.setVisible(false);

            Label5 = new JLabel("Max Matrix");
            Label5.setVisible(false);

            Label6 = new JLabel("Resources \u2192 ");
            Label6.setVisible(false);
            createMaxMatrix(totalProcess, totalResources);
        }

        public void createMaxMatrix(int totalProcess, int totalResources) {
            this.removeAll(); // Clear existing components
            this.setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();
            Insets padding = new Insets(2, 2, 2, 2);
            // Config Label4 having text "Processes \u2191 "
                gc.gridx = 0;
                gc.gridy = 0;
                gc.gridheight = totalProcess + 2;
                Label4.setForeground(Color.WHITE);
            this.add(Label4, gc);
            gc.gridheight = 1;
            // Config Label5 having text "Max Matrix "
                gc.gridx = 1;
                gc.gridy = 0;
                gc.gridwidth = totalResources + 1;
                Label5.setFont(new Font("Arial", Font.PLAIN, 16));
                Label5.setForeground(Color.WHITE);
            this.add(Label5, gc);
            // Config Label6 having text "Resources \u2192 "
                gc.gridx = 1;
                gc.gridy = 1;
                gc.gridwidth = totalResources +1;
                Label6.setForeground(Color.WHITE);
            this.add(Label6, gc);
            gc.gridwidth = 1;
            // Create the new matrix based on current values
            maxMatrix = new JTextField[totalProcess][totalResources];
            for (int i = 0; i < totalProcess; i++) {
                for (int j = 0; j < totalResources; j++) {
                    // Assigned to Global Public 2D Array/List
                    maxMatrix[i][j] = new JTextField(2);
                    maxMatrix[i][j].setFont(new Font("Arial", Font.PLAIN, 12));
                    maxMatrix[i][j].setBorder(new EmptyBorder(5, 5, 5, 5));
                    gc.gridx = j+1;
                    gc.gridy = i+2;
                    gc.insets = padding;
                    this.add(maxMatrix[i][j], gc);
                }
            }   
            revalidate(); // Refresh the layout
            repaint(); // Repaint the panel
        }
    }
    // Jpanel Extended Class for HomeMiddle
    class InputMaxPanel extends JPanel {
        // It conatins only 1 TextField(namely "maxAvailableResource") and corresponding Label(namely "resourceMaxLabel")
        InputMaxPanel() {
            setLayout(new GridBagLayout());
            resourceMaxLabel = new JLabel("Maximum Available Resources:");
            // By default Hidden will Show after OK Button Click Event & Validation
            resourceMaxLabel.setVisible(false);
            createMaxResources();
        }
        public void createMaxResources() {
            this.removeAll(); // Clear existing components
            setLayout(new GridBagLayout()); // Ensure the layout is consistent
            GridBagConstraints gc = new GridBagConstraints();
            Insets padding = new Insets(2, 2, 2, 2);
                gc.gridx = 0;
                gc.gridy = 0;
                gc.insets = padding;
                resourceMaxLabel.setFont(new Font("Arial", Font.BOLD, 14));
                resourceMaxLabel.setForeground(Color.WHITE);
            this.add(resourceMaxLabel, gc);
            // Initialize array based on totalResources
            maxAvailableResource = new JTextField[totalResources]; 
            gc.gridy = 0; // Starting at row 1 for the resource fields
            for (int i = 0; i < totalResources; i++) {
                // Assigned to global 1D Array/List
                maxAvailableResource[i] = new JTextField(2);
                maxAvailableResource[i].setFont(new Font("Arial", Font.PLAIN, 12));
                maxAvailableResource[i].setBorder(new EmptyBorder(5, 5, 5, 5));
                gc.gridx = i+1; // Position each resource field in the row
                this.add(maxAvailableResource[i], gc);
            }
            revalidate(); // Refresh the layout after updates
            repaint(); // Repaint the panel
        }
    }
    // Jpanel Extended Class for HomeTop
    class InputTopPanel extends JPanel{
        // It Consist of 2 TextFields("processTxt" & "resourceTxt"), their corresponding Label("processLabel" & "resourceLabel") & two buttons("Execute" & "OK")
        InputTopPanel(){
            setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints(); 
            Insets padding = new Insets(10, 10, 10, 10);
            // Its is Used to Execute Final Result in "Result" Frame after Collecting Max/Allocation Matrix & Max Available Resources
            JButton Execute = new Button("EXECUTE", 2);
                gc.gridx = 0; // Starting column
                gc.gridy = 0; // First row
                gc.gridwidth = 5; // Span across 5 columns
                gc.insets = padding;
            this.add(Execute, gc);
            
            JLabel processLabel = new JLabel("Total No. of Process:");
                gc.gridx = 0;
                gc.gridy = 1;
                gc.gridwidth = 1;
                gc.insets = padding;
                processLabel.setFont(new Font("Arial", Font.BOLD, 14));
                processLabel.setForeground(Color.WHITE);
            this.add(processLabel, gc);

            JTextField processTxt = new JTextField(15);
                gc.gridx = 1;
                gc.gridy = 1;
                gc.gridwidth = 1;
                gc.insets = padding;
                addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowOpened(WindowEvent e) {
                        processTxt.requestFocusInWindow();  // Requests focus on the text field
                    }
                });
                processTxt.setFont(new Font("Arial", Font.PLAIN, 12));
                processTxt.setBorder(new EmptyBorder(5, 5, 5, 5));
            this.add(processTxt, gc);

            JLabel resourceLabel = new JLabel("Total No. of Resources:");
                gc.gridx = 2;
                gc.gridy = 1;
                gc.gridwidth = 1;
                gc.insets = padding;
                resourceLabel.setFont(new Font("Arial", Font.BOLD, 14));
                resourceLabel.setForeground(Color.WHITE);
            this.add(resourceLabel, gc);

            JTextField resourceTxt = new JTextField(15);
                gc.gridx = 3;
                gc.gridy = 1;
                gc.gridwidth = 1;
                gc.insets = padding;
                resourceTxt.setFont(new Font("Arial", Font.PLAIN, 12));
                resourceTxt.setBorder(new EmptyBorder(5, 5, 5, 5));
            this.add(resourceTxt, gc);
            
            // This Validates the Data(Should be Integer, Not Empty, Greater than 0) in "resourceTxt" & "processTxt" 
            // It Dynamically Generates Input TextFields(in "HomeBottomR" & "HomeBottomL" Panel) Based on Inputs
            JButton OK = new Button("OK", 1);
                gc.gridx = 4; // Starting column
                gc.gridy = 1; // First row
                gc.gridwidth = 1; // Span across 5 columns
                gc.insets = padding;
            this.add(OK, gc);               

            // When "OK" button is Clicked
            OK.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String tmp1 = resourceTxt.getText();
                    String tmp2 = processTxt.getText();
                    
                    // Check the value Must be Integer
                    if(tmp1.matches("^[0-9]+$") && tmp2.matches("^[0-9]+$")){
                        // Store to class Variable
                        totalProcess = Integer.parseInt(tmp2);
                        totalResources = Integer.parseInt(tmp1);
                        // Validate Value > 0
                        if(totalProcess>0 && totalResources>0){
                            // Show Dynamic Labels
                            // Make Labels in "HomeMiddle", "HomeBottomR" & "HomeBottomL" Visible
                            resourceMaxLabel.setVisible(true);
                            Label1.setVisible(true);
                            Label2.setVisible(true);
                            Label3.setVisible(true);
                            Label4.setVisible(true);
                            Label5.setVisible(true);
                            Label6.setVisible(true);
                            // Restrict Editing of TextFields in "HomeTop"
                            resourceTxt.setEditable(false);
                            processTxt.setEditable(false);
                            // Change Name of "OK" to "CLEAR"
                            OK.setText("CLEAR");
                            // initialize 2D Integer Arrays
                            AllocationMatrix = new Integer[totalProcess][totalResources];
                            MaxMatrix = new Integer[totalProcess][totalResources];
                            MaxAvailableResource = new Integer[totalResources];
                            // Update the InputBottomPanel
                            ((InputBottomLPanel) HomeBottomL).createAllocationMatrix(totalProcess, totalResources);
                            ((InputBottomRPanel) HomeBottomR).createMaxMatrix(totalProcess, totalResources);
                            ((InputMaxPanel) HomeMiddle).createMaxResources();
                        }
                        else{
                            // Error Message is Validation Failed
                            JOptionPane.showMessageDialog(InputTopPanel.this ,
                                                            "Invalid input! Please Enter an Integer Greater than 0", 
                                                                "Input Error",
                                                                 JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        // Error Message is Validation Failed    
                        JOptionPane.showMessageDialog(InputTopPanel.this ,
                                                        "Invalid input! Please Enter an Integer Greater than 0", 
                                                            "Input Error",
                                                             JOptionPane.ERROR_MESSAGE);
                    }
            }
            });

            // When "Execute" button is Clicked
            Execute.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e){
                    try{
                        for (int i = 0; i < totalProcess; i++) {
                            for (int j = 0; j < totalResources; j++) {
                                // Validate Occurance of Integer Only in 2D Max/Allocation Matrix
                                AllocationMatrix[i][j] = Integer.parseInt(allocationMatrix[i][j].getText());
                                MaxMatrix[i][j] = Integer.parseInt(maxMatrix[i][j].getText());

                            }
                        }
                        for (int j = 0; j < totalResources; j++) {
                            // Validate Occurance of Integer Only in 1D MaxAvailable Matrix
                            MaxAvailableResource[j] = Integer.parseInt(maxAvailableResource[j].getText());
                        }
                        // Create an Instance Window "Result"
                        JFrame result = new Result();
                    }catch(Exception error){
                                // Error Message If Validation Failed
                                JOptionPane.showMessageDialog(InputTopPanel.this ,
                                                                "Invalid input! Please enter an integer.", 
                                                                    "Input Error",
                                                                    JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
    }
    // JButton Extended Class to Design Custom Button
    class Button extends JButton {
        // type = 1 represent Red Button & type = 2 represent Bluish Button
        public Button(String text, int type) {
            // Calling Parent Constructor(constructor of JButton)
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setFont(new Font("Arial", Font.BOLD, 16)); // Change Font Familiy,Size,Type
            setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor-type on Hover on Button
            if(type == 1)
                setBackground(Color.RED);
            else if(type == 2)
                setBackground(new Color(53, 126,199));
            setForeground(Color.WHITE);
            
            addMouseListener(new MouseAdapter() {
                @Override
                // Change background color on hover 
                public void mouseEntered(MouseEvent e) {
                    if(type == 1)
                        setBackground(new Color(200,0,0));
                    else if(type == 2)
                        setBackground(new Color(41,98,115));
                }

                @Override
                // Restore background color after hover
                public void mouseExited(MouseEvent e) {
                    if(type == 1)
                        setBackground(Color.RED);
                    else if(type == 2)
                        setBackground(new Color(53, 126,199));
                }
                @Override
                // Event when Mouse Pressed
                public void mousePressed(MouseEvent e) {
                    setBackground(Color.WHITE);
                    if(type == 1)
                        setForeground(Color.RED);
                    else if(type == 2)
                        setForeground(new Color(53, 126,199));
                }
                @Override
                // Event when Mouse Released
                // Change back to original color
                public void mouseReleased(MouseEvent e) {
                    // Change back to original color
                    setForeground(Color.WHITE);
                    if(type == 1)
                        setBackground(Color.RED);
                    else if(type == 2)
                        setBackground(new Color(53, 126,199));
                }
            });

            // Event Listener when Button is selected With Keyboard
            addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if(type == 1)
                        setBackground(new Color(200,0,0));// Change background color on hover
                    else if(type == 2)
                        setBackground(new Color(41,98,115));
                }
    
                @Override
                public void focusLost(FocusEvent e) {
                    if(type == 1)
                        setBackground(Color.RED);
                    else if(type == 2)
                        setBackground(new Color(53, 126,199));
                }
            });
        }
        

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            // to improve rendering quality
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // Create a smoother rounded rectangle
            g2d.setColor(getBackground());
            // Round Corners
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 13, 13); // Smooth curves
            super.paintComponent(g);
        }
    }
    // Second Frame That is Used To Show The Simulation of Result
    class Result extends JFrame{
        Backend backend;
        JButton[][] allocationResultMatrix;
        JButton[][] needResultMatrix;
        // It mainly Contains 3 Section namely two Panels(P1 & P2) & a TextArea(P3) contained in ScrollPane(P4)
        Result(){
            // Algorithm from Backend
            backend = new Backend(MaxMatrix, AllocationMatrix);
            backend.calcAvailableResource(MaxAvailableResource);

            setTitle("Banker's Algorithm Result");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // when Closed it will exit only Current Window
            setBackground(new Color(51,51,51));
            setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();
            JPanel P1 = new ResultPanel1();
                gc.gridx = 0;
                gc.gridy = 0;
                gc.gridheight = 1;
                gc.weighty = 0.5;
                gc.weightx = 0.5;
            add(P1, gc);
            JPanel P2 = new ResultPanel2();
                gc.gridx = 0;
                gc.gridy = 1;
                gc.gridwidth = 1;
                gc.weighty = 0.5;
                gc.weightx = 0.5;
            add(P2, gc);
            // TextArea to Show Output of Algorithm other than Color Animation
            JTextArea P3 = new JTextArea();
            // Adding ScrollPane over TextArea
            JScrollPane P4 = new JScrollPane(P3);
                gc.gridx = 1;
                gc.gridy = 0;
                gc.gridheight = 2;
                gc.fill = GridBagConstraints.BOTH;
                P3.setEditable(false);
                P3.setFont(new Font("Arial",Font.ITALIC,15));
                P3.setForeground(Color.WHITE);
                P3.setBackground(new Color(51,51,51));
                P4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(P4, gc);
            P3.setMargin(new Insets(10, 10, 10, 10));
            
            // Backend Output
            ArrayList<Integer> toPrint = backend.executeAlgoSingle();

            // Showing Output by means of TextArea
            if(toPrint.size() < totalProcess){
                P3.setText("This Condition Will Lead to DeadLock State\n\n");
            }
            else{
                P3.setText("This will Produce a Safe Sequence\n\n");
            }
            P3.append("Currently Available Resources:\n");
            for(int i=0; i<backend.availableResource.length; i++){
                P3.append(backend.availableResource[i].toString());
                if(i!=backend.availableResource.length-1)
                    P3.append(", ");
            }
            P3.append("\n\n");

            setResizable(true);
            setMinimumSize(new Dimension(1024, 680));
            setExtendedState(JFrame.MAXIMIZED_BOTH); // by default Maximize Window

            // Timer to show the Animation having gap of 2 sec
            Timer timer = new Timer(2000, new ActionListener() {
                // variable to keep Track of Animation Termaination
                int currentIndex = 0;
    
                @Override
                // This is triggered after every 2sec
                public void actionPerformed(ActionEvent e) {
                    // Check of termination of Animation
                    if (currentIndex < toPrint.size()) {
                        int processIndex = toPrint.get(currentIndex);
                        // Change color of Process to whom next resources are Given  
                        for(int i=0; i<allocationMatrix[0].length; i++){
                            needResultMatrix[processIndex][i].setBackground(new Color(53, 126,199));
                        }
                        // DeadLock Condition
                        if(toPrint.size() < totalProcess){
                            P3.append("P"+toPrint.get(currentIndex).toString()+" -> ");
                        }
                        // Safe Sequence
                        else{
                            P3.append("P"+toPrint.get(currentIndex).toString());
                            if(currentIndex!=toPrint.size()-1)
                                P3.append(" -> ");
                        }
                        // Counter Increment
                        currentIndex++;
                    } else {
                        // Animation Terminated
                        // Stop Timer
                        ((Timer) e.getSource()).stop(); // Stop the timer when done
                        // DeadLock Condition
                        if(toPrint.size() < totalProcess){
                            P3.append("DEADLOCK\n\n");
                            P3.append("Available Resources:\n");
                            // Print Available Resources at DeadLock
                            for(int j=0;j<totalResources;j++){
                                P3.append(backend.tmpAvailResource[j].toString());
                                if(j!=totalResources-1)
                                    P3.append(", ");
                            }
                        }
                        // Safe Sequence
                        else{
                            P3.append("\n\nFinally Available Resources:\n");
                            // Print Available Resources at Last
                            for(int j=0;j<totalResources;j++){
                                P3.append(backend.tmpAvailResource[j].toString());
                                if(j!=totalResources-1)
                                    P3.append(", ");
                            }
                        }
                    }
                }
            });
            timer.start();  // Start the timer
            setVisible(true);

        }
        // Jpanel Extended Class to implement P1 Panel
        class ResultPanel1 extends JPanel{
            ResultPanel1(){
                // It has 3 Labels namely Title(ex "Allocation Matrix"), Row Legend(ex. Processes) & Column Legend(ex. Resources ->)
                // It also Contains Array/Collection of JButtons to show Allocation Matrix
                setLayout(new GridBagLayout());
                GridBagConstraints gc = new GridBagConstraints();
                Insets padding = new Insets(2, 2, 2, 2);

                JLabel Label1 = new JLabel("Processes \u2191 ");
                    gc.gridx = 0;
                    gc.gridy = 0;
                    gc.gridheight = totalProcess + 2;
                this.add(Label1, gc);
                gc.gridheight = 1;
                JLabel Label2 = new JLabel("Allocation Matrix");
                    gc.gridx = 1;
                    gc.gridy = 0;
                    gc.gridwidth = totalResources+1;
                    Label2.setFont(new Font("Arial", Font.PLAIN, 16));
                this.add(Label2, gc);
                JLabel Label3 = new JLabel("Resources \u2192 ");
                    gc.gridx = 1;
                    gc.gridy = 1;
                    gc.gridwidth = totalResources+1;
                this.add(Label3, gc);
                gc.gridwidth = 1;

                // Global & public 2D array initialization
                allocationResultMatrix = new JButton[totalProcess][totalResources];
                for (int i = 0; i < totalProcess; i++) {
                    for (int j = 0; j < totalResources; j++) {
                        allocationResultMatrix[i][j] = new JButton(AllocationMatrix[i][j].toString());
                        allocationResultMatrix[i][j].setFont(new Font("Arial", Font.PLAIN, 12));
                        allocationResultMatrix[i][j].setBorder(new EmptyBorder(8, 10, 8, 10));
                        allocationResultMatrix[i][j].setFocusPainted(false);
                        allocationResultMatrix[i][j].setBorderPainted(false);
                        allocationResultMatrix[i][j].setBackground(Color.RED);
                        allocationResultMatrix[i][j].setForeground(Color.WHITE);
                            gc.gridx = j+1;
                            gc.gridy = i+2;
                            gc.weightx = 1;
                            gc.weighty = 1;
                            gc.insets = padding;
                        add(allocationResultMatrix[i][j], gc);
                    }
                }
            }
        }
        // Jpanel Extended Class to implement P2 Panel
        class ResultPanel2 extends JPanel{
            // It has 3 Labels namely Title(ex "Need Matrix"), Row Legend(ex. Processes) & Column Legend(ex. Resources ->)
            // It also Contains Array/Collection of JButtons to show Output Need Matrix
            ResultPanel2(){
                setLayout(new GridBagLayout());
                GridBagConstraints gc = new GridBagConstraints();
                Insets padding = new Insets(2, 2, 2, 2);
                JLabel Label1 = new JLabel("Processes \u2191 ");
                gc.gridx = 0;
                gc.gridy = 0;
                gc.gridheight = totalProcess + 2;
            this.add(Label1, gc);
            gc.gridheight = 1;
            JLabel Label2 = new JLabel("Need Matrix");
                gc.gridx = 1;
                gc.gridy = 0;
                gc.gridwidth = totalResources+1;
                Label2.setFont(new Font("Arial", Font.PLAIN, 16));
            this.add(Label2, gc);
            JLabel Label3 = new JLabel("Resources \u2192 ");
                gc.gridx = 1;
                gc.gridy = 1;
                gc.gridwidth = totalResources+1;
            this.add(Label3, gc);
            gc.gridwidth = 1;

            // Global & public 2D array initialization
            needResultMatrix = new JButton[totalProcess][totalResources];
            for (int i = 0; i < totalProcess; i++) {
                for (int j = 0; j < totalResources; j++) {
                    needResultMatrix[i][j] = new JButton(backend.needMatrix[i][j].toString());
                    needResultMatrix[i][j].setFont(new Font("Arial", Font.PLAIN, 12));
                    needResultMatrix[i][j].setBorder(new EmptyBorder(8, 10 , 8, 10));
                    needResultMatrix[i][j].setFocusPainted(false);
                    needResultMatrix[i][j].setBorderPainted(false);
                    needResultMatrix[i][j].setBackground(Color.RED);
                    needResultMatrix[i][j].setForeground(Color.WHITE);
                        gc.gridx = j+1;
                        gc.gridy = i+2;
                        gc.weightx = 1;
                        gc.weighty = 1;
                        gc.insets = padding;
                    add(needResultMatrix[i][j], gc);
                }
            }
        }
    }
}

    // Entry Point Of GUI Interface
    public static void main(String[] args) {
        new Bankers_GUI();
    }

}

// Backend Algorithm for Banker's Algorithm
class Backend {
    Integer[][] maxMatrix;
    Integer[][] allocationMatrix;
    Integer[][] needMatrix;
    Integer[] usedResource;
    Integer[] availableResource;
    Integer[] maximumResource;
    Integer[] tmpAvailResource;
    ArrayList<ArrayList<Integer>> safeSequences;
    // Initial Inputs are Max-Matrix[][] & Allocation-Matrix[][]
    // Secondary Input is Maximum-Available-Resource[] taken in calcAvailableResource() Function
    Backend(Integer[][] maxMatrix, Integer[][] allocationMatrix) {
        this.maxMatrix = maxMatrix;
        this.allocationMatrix = allocationMatrix;
        
        // Initialize arrays with correct sizes
        this.needMatrix = new Integer[maxMatrix.length][maxMatrix[0].length];
        this.usedResource = new Integer[allocationMatrix[0].length];
        this.availableResource = new Integer[allocationMatrix[0].length];
        this.safeSequences = new ArrayList<>();

        // Initialize usedResource and availableResource to 0
        for (int i = 0; i < usedResource.length; i++) {
            usedResource[i] = 0;
            availableResource[i] = 0;
        }
        calcUsedResource(); // Calculate the used resources
        calcNeed(); // Calculate the need matrix
    }
    // Function to Calculate Need Matrix = MaxMatrix - AllocationMatrix
    void calcNeed() {
        for (int i = 0; i < maxMatrix.length; i++) {
            for (int j = 0; j < maxMatrix[0].length; j++) {
                needMatrix[i][j] = maxMatrix[i][j] - allocationMatrix[i][j];
            }
        }
    }
    // Function to Calculate Used Resource by Processes at the given Instance(by User)
    void calcUsedResource() {
        for (int i = 0; i < allocationMatrix[0].length; i++) {
            int tmp = 0;
            for (int j = 0; j < allocationMatrix.length; j++) {
                tmp += allocationMatrix[j][i];
            }
            usedResource[i] = tmp;
        }
    }
    // Function to Calculate Available Resource at the given Instance(by User)
    void calcAvailableResource(Integer[] maximumResource) {
        this.maximumResource = maximumResource;
        for (int i = 0; i < maximumResource.length; i++) {
            availableResource[i] = maximumResource[i] - usedResource[i];
        }
    }
    // Function to Test that Available Resource is Sufficient to Serve the Need of a Process
    boolean larger(Integer[] avail, Integer[] need) {
        for (int i = 0; i < need.length; i++) {
            if (avail[i] < need[i])
                return false;
        }
        return true;
    }
    // Function to ADD two list and return their Sum List
    Integer[] add(Integer[] a, Integer[] b) {
        for (int i = 0; i < a.length; i++) {
            a[i] += b[i];
        }
        return a;
    }
    // Will return a Dynamic list of all Posssible Remaining Process that can be choosen to Execute
    ArrayList<Integer> findProcess(Integer[] currentlyAvailable, ArrayList<Integer> processUsed) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < needMatrix.length; i++) {
            if (larger(availableResource, needMatrix[i])) {
                if (!processUsed.contains(i))
                    tmp.add(i);
            }
        }
        return tmp;
    }
    // Will return an Index of a Process(That is Nearer to Start of List) that can be choosen to Execute
    int find1Process(Integer[] currentlyAvailable, ArrayList<Integer> processUsed) {
        for (int i = 0; i < needMatrix.length; i++) {
            if (larger(currentlyAvailable, needMatrix[i])) {
                if (!processUsed.contains(i)){
                    return i;
                }
            }
        }
        return -1;
    }
    // This is used to Trace a Safe Sequence if Possible
    ArrayList<Integer> executeAlgoSingle() {
        Integer[] currentlyAvailable = availableResource.clone();
        ArrayList<Integer> processUsed = new ArrayList<>();

        while (processUsed.size() < needMatrix.length) {
            int tmp = find1Process(currentlyAvailable, processUsed);
            if (tmp == -1) {                 
                break;
            }
            processUsed.add(tmp);
            currentlyAvailable = add(allocationMatrix[tmp], currentlyAvailable);
        }
        tmpAvailResource = currentlyAvailable;
        return processUsed;

    }
    // To Print 2D Matrix on Terminal
    void printMatrix2D(String label, Integer[][] matrix) {
        System.out.println(label + ":");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    // To Print 1D Matrix on Terminal
    void printMatrix1D(String label, Integer[] matrix) {
        System.out.println(label + ":");
        for (int j = 0; j < matrix.length; j++) {
            System.out.print(matrix[j] + " ");
        }
        System.out.println();   
    }
}