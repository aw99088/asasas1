package asasas1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

class calc implements ActionListener {	
	JFrame frame;      
	JTextField textfieldNorth; 
	JTextField textField;
	JMenuBar menuBar;
	JMenu menu;
	JMenu menu2;
	JMenu menu3;
	JPanel panel1;  
	JPanel panelNorth;   
	GridLayout grid;
	JButton JBu;
	JButton button1;
	JButton button2;
	
	String[] strGrid={"7","8","9","*","/","4","5","6","^","��","1","2","3","-","+","-/+","0",".","C","=" };
	String[] strGrid2= {"km>mile", "mile>km", "km>m","m>km","tonne>kg","kg>tonne","pound>kg","kg>pound","��>��","��>��","��>��","��>��","Mbyte>Kbyte","Kbyte>Mbyte","Gbyte>Mbyte","Mbyte>Gbyte"};
	
	private String first="";  	
	private String result=""; 		
	ArrayList<Integer> ee=new ArrayList<Integer>();  		
	ArrayList<String> store=new ArrayList<String>();  		
	
	
	private String result2=""; 		
	ArrayList<String> store2=new ArrayList<String>();  		
	
	public calc(){
		frame=new JFrame("����");
		menuBar=new JMenuBar();

		menu=new JMenu("����(V)");
		menu.setFont(new Font("Sans-serif", 0, 12));
		
		menu2=new JMenu("����(E)");
		menu2.setFont(new Font("Sans-serif", 0, 12));
		
		menu3=new JMenu("����(H)");
		menu3.setFont(new Font("Sans-serif", 0, 12));
		
		button1=new JButton("���� ��ȯ");
		button1.setFont(new Font("Sans-serif", 0, 12));
		button1.setBackground(new Color( 222,232,244));
		button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new newWindow();
            }
        });
		
		button2=new JButton("help");
		button2.setFont(new Font("Sans-serif", 0, 12));
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new newWindow2();
			}
		});
		
		
		panel1=new JPanel();
		panelNorth=new JPanel();
		textField=new JTextField("0"); 			
		textfieldNorth=new JTextField(""); 		
	}
		

	public void gui(){
		menuBar.add(menu);		
		menuBar.add(menu2);	
		menuBar.add(menu3);
		menuBar.add(button1);
		menuBar.add(button2);
		
		textField.setHorizontalAlignment(JTextField.RIGHT);   
		textField.setEditable(false); 		
		
		textfieldNorth.setHorizontalAlignment(JTextField.RIGHT); 
		textfieldNorth.setEditable(false); 	
		
		panelNorth.setLayout(new BorderLayout());    		
		panelNorth.add(BorderLayout.NORTH,textfieldNorth);  
		panelNorth.add(BorderLayout.CENTER,textField);
		
		panel1.setLayout(new GridLayout(4,3,6,6));  		
		panel1.setBackground(new Color( 222,232,244)); 
	

		for(int i=0; i<strGrid.length; i++){
			JBu=new JButton(strGrid[i]);  				 
			JBu.addActionListener(this);	 			
			JBu.setBackground(new Color( 241,244,249));   
			panel1.add(JBu); 							
		}
		
	
		frame.add(BorderLayout.NORTH,panelNorth); 
		frame.add(BorderLayout.CENTER,panel1); 		
		frame.setJMenuBar(menuBar);  				
		
		frame.setResizable(false);                                 
		frame.setSize(500,420);                                   
		frame.setVisible(true);                                  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	}

	public void actionPerformed(ActionEvent e){
		String str=e.getActionCommand();  	
		String read;				
		
		try{	
			
			if(str!="/" && str!= "*"&& str!= "-"&& str!= "+"&& str!= "C"&& str!= "="&& str!= "^" && str!= "��" && str!= "-/+"){	
				textField.setText(first);  		
				textField.getText();  		
				textfieldNorth.setText("");		
				textfieldNorth.getText(); 		
				
				read=textField.getText();  	
				first=read+str;
				textField.setText(first);		
				textField.getText(); 			
			}
			
			
			
			
			if(str=="/" || str== "*"|| str== "-"|| str== "+"|| str== "=" || str== "^" || str== "��" || str== "-/+"){
				textfieldNorth.setText(str); 				
				textfieldNorth.getText();  					
				
				ee.add(Integer.parseInt(first));  
				first="";					
				
				store.add(str); 			  		
			}// if
			if(str=="-/+"){
				double sum=0;
				sum=ee.get(0); 
				sum = sum * -1;
				result=sum+"";						
				textField.setText(result);  	
				textField.getText();
			}


			if(str=="^"){
				double sum=0;
				sum=ee.get(0); 
				sum = sum * sum;
				result=sum+"";					
				textField.setText(result);  		
				textField.getText();
			}


			if(str=="��"){
				double sum=0;
				sum=ee.get(0); 
				sum = sum * 3.14;
				result=sum+"";					
				textField.setText(result);  		
				textField.getText();
			}
			
			
			if(str=="="){		
				double sum=0;
				sum=ee.get(0); 		
				
				for(int a=0,h=1; a<ee.size(); a++,h++){ 
					int ve=store.size(); 			
					if(ve>0){						
						ve--;			
					
						if(store.get(a)=="+"){		
							sum=sum+ee.get(1);  			
						}else if(store.get(a)=="-"){		
							sum=sum-ee.get(1);	
						}else if(store.get(a)=="X"){
							sum=sum*ee.get(1);
						}else if(store.get(a)=="/"){
							try{				
								sum=sum/ee.get(1);
							}catch(Exception exc){		
								sum=0; 
							}
						}
					}
				}
				result=sum+"";					
				textField.setText(result);  		
				textField.getText(); 				
			}
			
			
			if(str=="C"){
				first="";
				textField.setText("0"); 			
				textField.getText();  			
				textfieldNorth.setText(""); 
				textfieldNorth.getText(); 
				ee.clear();  					
				store.clear();  				
			}
			
		}catch(Exception ex){
			textField.setText("Error �ٽ��Է� CŬ��."); 
			textField.getText(); 
		}
	
	}
	
	class newWindow2 extends JFrame {
		newWindow2() {
			setTitle("help");
			JPanel HelpContainer = new JPanel();
			setContentPane(HelpContainer);
			JLabel explanation = new JLabel("������ȯ �����Դϴ�");
			JLabel chkmmile = new JLabel("1. km>mile�� km�� mile�� �ٲ��ݴϴ�");
			JLabel chmilekm = new JLabel("2. mile>km�� mile�� km�� �ٲ��ݴϴ�");
			JLabel chkmm = new JLabel("3. km>m�� km�� m�� �ٲ��ݴϴ�");
			JLabel chmkm = new JLabel("4. m>km�� m�� km�� �ٲ��ݴϴ�");
			JLabel chtonnekg = new JLabel("5. tonne>kg�� tonne�� kg���� �ٲ��ݴϴ�");
			JLabel chkgtonne = new JLabel("6. kg>tonne�� kg�� tonne�� �ٲ��ݴϴ�");
			JLabel chpoundkg = new JLabel("7. pound>kg�� pound�� kg���� �ٲ��ݴϴ�");
			JLabel chkgpound = new JLabel("8. kg>pound�� kg�� pound�� �ٲ��ݴϴ�");
			JLabel ch�ʺ� = new JLabel("9. ��>���� �ʸ� ������ �ٲ��ݴϴ�");
			JLabel ch���� = new JLabel("10. ��>�ʴ� ���� �ʷ� �ٲ��ݴϴ�");
			JLabel ch�ú� = new JLabel("11. ��>���� �ø� ������ �ٲ��ݴϴ�");
			JLabel ch�н� = new JLabel("12. ��>�ô� ���� �÷� �ٲ��ݴϴ� ");
			JLabel chmbkb = new JLabel("13. Mbyte>Kbyte�� Mbyte�� Kbyte�� �ٲ��ݴϴ�");
			JLabel chkbmb = new JLabel("14. Kbyte>Mbyte�� Kbyte�� Mbyte�� �ٲ��ݴϴ�");
			JLabel chgbmb = new JLabel("15. Gbyte>Mbyte�� Gbyte�� Mbyte�� �ٲ��ݴϴ�");
			JLabel chmbgb = new JLabel("16. Mbyte>Gbyte�� Mbyte�� Gbyte�� �ٲ��ݴϴ�");
			
			HelpContainer.setLayout(new GridLayout(17,1,3,0));
			HelpContainer.add(explanation);
			HelpContainer.add(chkmmile);
			HelpContainer.add(chmilekm);
			HelpContainer.add(chkmm);
			HelpContainer.add(chmkm);
			HelpContainer.add(chtonnekg);
			HelpContainer.add(chkgtonne);
			HelpContainer.add(chpoundkg);
			HelpContainer.add(chkgpound);
			HelpContainer.add(ch�ʺ�);
			HelpContainer.add(ch����);
			HelpContainer.add(ch�ú�);
			HelpContainer.add(ch�н�);
			HelpContainer.add(chmbkb);
			HelpContainer.add(chkbmb);
			HelpContainer.add(chgbmb);
			HelpContainer.add(chmbgb);
			
	        setSize(330,310);
	        setResizable(false);
	        setVisible(true);
		}
	}
	
	class newWindow extends JFrame {
		newWindow() {
			setTitle("������ȯ");
        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);
        
        JPanel panelf;
        JPanel panel2;
        JPanel panelNorth2;
        JTextField textField2;
        JTextField textFieldNorth2;
        JButton button2;
        
        panelf=new JPanel();
		panel2=new JPanel();

	 	
		panel2.setLayout(new GridLayout(8,2,6,6));  		
		panel2.setBackground(new Color( 222,232,244));  
		

		
		for(int i=0; i<strGrid2.length; i++){
			button2=new JButton(strGrid2[i]);  				
			button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent k) {
					

					
					String str2=k.getActionCommand();  		
					String read2;			
					
					try{	
						if(str2!="km>mile" && str2!="mile>km" && str2!="km>m" && str2!="m>km" && str2!="tonne>kg" && str2!="kg>tonne" && str2!="pound>kg" && str2!="kg>pound" && str2!="��>��" && str2!="��>��" 
								&& str2!="��>��" && str2!="��>��" && str2!="Mbyte>Kbyte" && str2!="Kbyte>Mbyte" && str2!="Gbyte>Mbyte" && str2!="Mbyte>Gbyte"){	
							textField.setText(first);  		
							textField.getText();  			
							textfieldNorth.setText("");		
							textfieldNorth.getText(); 		
							
							read2=textField.getText();  	
							first=read2+str2;
							textField.setText(first);		
							textField.getText(); 			
						}
						
						
						
					
						if(str2=="km>mile"|| str2=="mile>km"|| str2=="km>m"|| str2=="m>km"|| str2=="tonne>kg"|| str2=="kg>tonne"|| str2=="pound>kg"|| str2=="kg>pound"|| str2=="��>��"|| str2=="��>��"
								|| str2=="��>��"|| str2=="��>��"|| str2=="Mbyte>Kbyte"|| str2=="Kbyte>Mbyte"|| str2=="Gbyte>Mbyte"|| str2=="Mbyte>Gbyte"){
							textfieldNorth.setText(str2); 				 
							textfieldNorth.getText();  					
							
							ee.add(Integer.parseInt(first));  
							first="";						
							
							store2.add(str2); 			  	
						}
						

						
						
						if(str2=="km>mile") {		
							double sum=0;
							sum=ee.get(0);
							sum=sum/1.609;
							result2=sum+""+"mile";
							textField.setText(result2);  		
							textField.getText(); 
						}
						
						if(str2=="mile>km") {		
							double sum=0;
							sum=ee.get(0);
							sum=sum*1.609;
							result2=sum+""+"km";
							textField.setText(result2);  		
							textField.getText(); 
						}
						
						if(str2=="km>m") {		
							double sum=0;
							sum=ee.get(0);
							sum=sum*1000;
							result2=sum+""+"m";
							textField.setText(result2);  		
							textField.getText(); 
						}
						
						if(str2=="m>km") {		
							double sum=0;
							sum=ee.get(0);
							sum=sum/1000;
							result2=sum+""+"km";
							textField.setText(result2);  		
							textField.getText(); 
						}
						

						if(str2=="tonne>kg") {		
							double sum=0;
							sum=ee.get(0);
							sum=sum*1000;
							result2=sum+""+"kg";
			        		textField.setText(result2);  		
							textField.getText(); 
						}
						
						if(str2=="kg>tonne") {		
							double sum=0;
							sum=ee.get(0);
							sum=sum/1000;
							result2=sum+""+"tonne";
			        		textField.setText(result2);  		
							textField.getText(); 
						}
						
						if(str2=="pound>kg") {		
							double sum=0;
							sum=ee.get(0);
							sum=sum/2.205;
							result2=sum+""+"kg";
			        		textField.setText(result2);  		
							textField.getText(); 
						}
						
						if(str2=="kg>pound") {	
							double sum=0;
							sum=ee.get(0);
							sum=sum*2.205;
							result2=sum+""+"pound";
			        		textField.setText(result2);  		
							textField.getText(); 
						}

						if(str2=="��>��") {		
							double sum=0;
							sum=ee.get(0);
							sum=sum/60;
							result2=sum+""+"��";
			       			textField.setText(result2);  		
							textField.getText(); 
						}
						
						if(str2=="��>��") {	
							double sum=0;
							sum=ee.get(0);
							sum=sum*60;
							result2=sum+""+"��";
			       			textField.setText(result2);  		
							textField.getText(); 
						}
						
						if(str2=="��>��") {		
							double sum=0;
							sum=ee.get(0);
							sum=sum*60;
							result2=sum+""+"��";
			       			textField.setText(result2);  		
							textField.getText(); 
						}
						
						if(str2=="��>��") {		
							double sum=0;
							sum=ee.get(0);
							sum=sum/60;
							result2=sum+""+"��";
			       			textField.setText(result2);  		
							textField.getText(); 
						}

						if(str2=="Mbyte>Kbyte") {		
							double sum=0;
							sum=ee.get(0);
							sum=sum*1000;
							result2=sum+""+"Kbyte";
							textField.setText(result2);  		
							textField.getText(); 
						}

						if(str2=="Kbyte>Mbyte") {		
							double sum=0;
							sum=ee.get(0);
							sum=sum/1000;
							result2=sum+""+"Mbyte";
							textField.setText(result2);  		
							textField.getText(); 
						}
						
						if(str2=="Gbyte>Mbyte") {		
							double sum=0;
							sum=ee.get(0);
							sum=sum*1000;
							result2=sum+""+"Mbyte";
							textField.setText(result2);  		
							textField.getText(); 
						}
						
						if(str2=="Mbyte>Gbyte") {	
							double sum=0;
							sum=ee.get(0);
							sum=sum/1000;
							result2=sum+""+"Gbyte";
							textField.setText(result2);  		
							textField.getText(); 
						}

						
					
						if(str2=="C"){
							first="";
							textField.setText("0"); 			
							textField.getText();  			
							textfieldNorth.setText(""); 	
							textfieldNorth.getText(); 
							ee.clear();  					
							store2.clear();  				
						}
						
					}catch(Exception ex){
						textField.setText("Error �ٽ��Է� CŬ��."); 
						textField.getText(); 
					}
				}				
			});	 			
			button2.setBackground(new Color( 241,244,249));    
			panel2.add(button2); 							
		}
        
		panelf.setLayout(new BorderLayout());
		panelf.add(BorderLayout.SOUTH,panel2);
		NewWindowContainer.add(panelf);

		
        setSize(360,310);
        setResizable(false);
        setVisible(true);
		}
	}
	

	public static void main(String[] args){
		calc cal=new calc();
		cal.gui();
	}


}