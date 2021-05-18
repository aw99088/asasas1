package asasas1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

class calc implements ActionListener {	
	JFrame frame;     //JFrame ���� ����   
	JTextField textfieldNorth; 
	JTextField textField;
	JMenuBar menuBar;
	JMenu menu;
	JMenu menu2;
	JMenu menu3;
	JPanel panel1;  //Border 
	JPanel panelNorth;  //Border 
	GridLayout grid;
	JButton JBu;
	JButton button1;
	
	String[] strGrid={"7","8","9","/","4","5","6","X","1","2","3","-" ,"0","C","+","="};
	String[] strGrid2= {"7", "8", "9","km","4","5","6","ton","1","2","3","��","0","C","kbyte","="};
	
	private String first="";  		// ���� ����
	private String result=""; 		// �����  "=" ���
	ArrayList<Integer> ee=new ArrayList<Integer>();  		// ���� �������� �ޱ�.
	ArrayList<String> store=new ArrayList<String>();  		// ������ �������� ����.

	
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
            // ������� ��ư "�� â ����"�� ��ư�� �������� �߻��ϴ� �ൿ�� ����
            @Override
            public void actionPerformed(ActionEvent e) {
                new newWindow(); // Ŭ���� newWindow�� ���� ������
            }
        });
		
		
		panel1=new JPanel();
		panelNorth=new JPanel();
		textField=new JTextField("0"); 			// �ʱⰪ �ƹ��͵� �Ⱥ��̰�
		textfieldNorth=new JTextField(""); 		// �����ڸ� ���̴� �ؽ�Ʈ 
	}// ������ calc()
		

	public void gui(){
		menuBar.add(menu);		// �޴��ٿ� �޴��� 1,2,3 �ٿ��� 
		menuBar.add(menu2);	
		menuBar.add(menu3);
		menuBar.add(button1);
		
		textField.setHorizontalAlignment(JTextField.RIGHT);   // ��������
		textField.setEditable(false); 		// �ؽ�Ʈ�ʵ�â�� �ؽ�Ʈ�������ϰ� ���
		
		textfieldNorth.setHorizontalAlignment(JTextField.RIGHT);  // ��������
		textfieldNorth.setEditable(false); 	// �ؽ�Ʈ�ʵ�â�� �ؽ�Ʈ�������ϰ� ���
		
		panelNorth.setLayout(new BorderLayout());    		// ���̾ƿ� ����.
		panelNorth.add(BorderLayout.NORTH,textfieldNorth);   // �гο� �ؽ�Ʈ�ʵ带 �ΰ� ����.
		panelNorth.add(BorderLayout.CENTER,textField);
		
		panel1.setLayout(new GridLayout(4,3,6,6));  		// �׸��� ���̾ƿ� �Ӽ�����
		panel1.setBackground(new Color( 222,232,244));  // �гλ���
	

		for(int i=0; i<strGrid.length; i++){
			JBu=new JButton(strGrid[i]);  				// ��ư ���� 
			JBu.addActionListener(this);	 			// �� ��ư���� ������ ���̱�
			JBu.setBackground(new Color( 241,244,249));    // ��ư ����ֱ�
			panel1.add(JBu); 							// �гο� ������ ��ư�� ���̱�
		}
		
	
		frame.add(BorderLayout.NORTH,panelNorth); 
		frame.add(BorderLayout.CENTER,panel1); 		 // �ؽ�Ʈ�ʵ� ���ʿ�
		frame.setJMenuBar(menuBar);  				// �޴��� ���̱�
		
		frame.setResizable(false);                                  //â ũ�� ���� ���ϰ� ���´�.
		frame.setSize(330,310);                                    //frame �� ũ��  
		frame.setVisible(true);                                   //frame�� ȭ�鿡 ��Ÿ������ ����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //X��ư Ȱ��ȭ 
	}//gui()

	public void actionPerformed(ActionEvent e){
		String str=e.getActionCommand();  		// ���ڿ��� �̺�Ʈ�ҷ���
		String read;				// �ؽ�Ʈ�ʵ忡 �ִ� �ؽ�Ʈ �б�뵵
		
		try{	
			//�����ȣ�� �ƴ� �����̸� true �̴�.
			if(str!="/" && str!= "X"&& str!= "-"&& str!= "+"&& str!= "C"&& str!= "="){	
				textField.setText(first);  		// 0������� ���ڴ����ϱ����� �ʿ�.
				textField.getText();  			// ���� ���
				textfieldNorth.setText("");		// ������ �ؽ�Ʈ �ʱ�ȭ
				textfieldNorth.getText(); 		// ���
				
				read=textField.getText();  		// �ؽ�Ʈ�� �ִ°� �ҷ���.
				first=read+str;
				textField.setText(first);		// ���� ����
				textField.getText(); 			// ���� ���
			}// if
			
			
			
			//�����ȣ�� �������� true.
			if(str=="/" || str== "X"|| str== "-"|| str== "+"|| str== "="){
				textfieldNorth.setText(str); 				 // ������ ��ȣ ����
				textfieldNorth.getText();  					// ������ ��ȣ ���
				
				ee.add(Integer.parseInt(first));  // �����Ϸ��� �� �ϳ��� ArrayList�� ����
				first="";						// �ٽ� �����ϵ��� �ʱ�ȭ
				
				store.add(str); 			   // ������ ArrayList�� ����		
			}// if
			
			//�����ȣ "=" ����� ��������.
			if(str=="="){		
				int sum=0;
				sum=ee.get(0); 		// ���� ���۰��� �ް� ���� , 1(+2)(+3)(+4)=result �ʱⰪ�� �˸� 3���� "()" �������ָ� �ȴ�. 
				
				for(int a=0,h=1; a<ee.size(); a++,h++){   // ArrayList ũ�⸸ŭ �ݺ�
					int ve=store.size(); 				// ������ ���� Ƚ�� ����
					if(ve>0){							// ������ ���� ��� ����Ҷ����� �ݺ�.
						ve--;			
						//if�� �Ǵ� switch������ ����.
						if(store.get(a)=="+"){			// ������ + �϶�
							sum=sum+ee.get(1);  			// (+2) �ι�° ���ڿ� ����
						}else if(store.get(a)=="-"){	// �Ʒ����� �ݺ�..	
							sum=sum-ee.get(1);	
						}else if(store.get(a)=="X"){
							sum=sum*ee.get(1);
						}else if(store.get(a)=="/"){
							try{				
								sum=sum/ee.get(1);
							}catch(Exception exc){		// �������� 0���� ������ ���ܹ߻��Ѵ�.
								sum=0;  //�����߻��� 0���� ���.
							}
						}
					}// if
				}// for
				result=sum+"";						//�� �����
				textField.setText(result);  		// ����� ����
				textField.getText(); 				// ������	
			}// if
			
			//��� �� �ʱ�ȭ ��Ű�� �ٽ� ����.
			if(str=="C"){
				first="";
				textField.setText("0"); 			 // �ؽ�Ʈâ ���� ����� 0���
				textField.getText();  			
				textfieldNorth.setText(""); 	// ������ �ؽ�Ʈâ �ʱ�ȭ
				textfieldNorth.getText(); 
				ee.clear();  					// ��� ��ҵ� ����
				store.clear();  				// ��� ��ҵ� ����
			}
			
		}catch(Exception ex){
			textField.setText("Error �ٽ��Է� CŬ��.");  // �ؽ�Ʈâ ���� ���� �����
			textField.getText(); 
		}
	
	}// actionPerformed()
	
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
		panelNorth2=new JPanel();
		textField2=new JTextField("0"); 			// �ʱⰪ �ƹ��͵� �Ⱥ��̰�
		textFieldNorth2=new JTextField(""); 		// �����ڸ� ���̴� �ؽ�Ʈ 
        
		textField2.setHorizontalAlignment(JTextField.RIGHT);   // ��������
		textField2.setEditable(false); 		// �ؽ�Ʈ�ʵ�â�� �ؽ�Ʈ�������ϰ� ���
		
		textFieldNorth2.setHorizontalAlignment(JTextField.RIGHT);  // ��������
		textFieldNorth2.setEditable(false); 	// �ؽ�Ʈ�ʵ�â�� �ؽ�Ʈ�������ϰ� ���
		
		panelNorth2.setLayout(new BorderLayout());    		// ���̾ƿ� ����.
		panelNorth2.add(BorderLayout.NORTH,textfieldNorth);   // �гο� �ؽ�Ʈ�ʵ带 �ΰ� ����.
		panelNorth2.add(BorderLayout.CENTER,textField);
	 	
		panel2.setLayout(new GridLayout(4,3,6,6));  		// �׸��� ���̾ƿ� �Ӽ�����
		panel2.setBackground(new Color( 222,232,244));  // �гλ���
		
		for(int i=0; i<strGrid.length; i++){
			button2=new JButton(strGrid2[i]);  				// ��ư ���� 
			button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}				//actionPerformed()
			});	 			// �� ��ư���� ������ ���̱�
			button2.setBackground(new Color( 241,244,249));    // ��ư ����ֱ�
			panel2.add(button2); 							// �гο� ������ ��ư�� ���̱�
		}
        
		panelf.setLayout(new BorderLayout());
		panelf.add(BorderLayout.CENTER,panelNorth2);
		panelf.add(BorderLayout.SOUTH,panel2);
		NewWindowContainer.add(panelf);

		
        setSize(330,310);
        setResizable(false);
        setVisible(true);
		}
	}
	

	public static void main(String[] args){
		calc cal=new calc();
		cal.gui();
	}// main()


}// class